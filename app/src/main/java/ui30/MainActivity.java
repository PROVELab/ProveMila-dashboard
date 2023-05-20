package ui30;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;
//import java.swing.timer;
import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import com.example.ui30.R;

public class MainActivity extends AppCompatActivity {
    //Main page initilizers
    private View settingTab;
    //Tire pressure variables
    private TextView topRightTire;
    private TextView bottomRightTire;
    private TextView topLeftTire;
    private TextView bottomLeftTire;
    //main car attributes
    private TextView battery;
    private TextView speed;
    private TextView range;
    //car vitals
    private TextView batteryTemp;
    private TextView motorTemp;
    private Chronometer chronometer;
    private long savedTime = 0;
    private Group mainPage;

    //second Page initilizers
    private View mainTab;
    private TextView motorTemp2;
    private TextView coolantTemp;
    private TextView battery2;
    private TextView coolantPressure;
    private TextView amps;
    private Group secondPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region fullscreen code [to be placed before setContentView]
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Hiding navigation bar
        View overlay = findViewById(R.id.mainLayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        //Giving button attributes to top icons to move between settings and main pages
        settingTab = findViewById(R.id.secondScreen);
//        settingTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openSettings();
//            }
//        });

        // Initialize TextView variables here
        topRightTire = findViewById(R.id.rightTP_top);
        bottomRightTire = findViewById(R.id.rightTP_bottom);
        topLeftTire = findViewById(R.id.leftTP_top);
        bottomLeftTire = findViewById(R.id.leftTP_bottom);
        battery = (TextView)findViewById(R.id.battery_text);
        speed = findViewById(R.id.speed_text);
        range = findViewById(R.id.range_text);
        batteryTemp = findViewById(R.id.batteryTemp);
        motorTemp = findViewById(R.id.motorTemp);
        //testSwingTimer();
        chronometer = findViewById(R.id.timer);
        chronometer.setFormat("%s");
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                int hours = (int) (elapsedTime / 3600000);
                int minutes = (int) (elapsedTime - hours * 3600000) / 60000;
                int seconds = (int) (elapsedTime - hours * 3600000 - minutes * 60000) / 1000;
                String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                chronometer.setText(formattedTime);
            }
        });
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        //main group
        mainPage = findViewById(R.id.mainPage);
        mainPage.setVisibility(View.VISIBLE);

        //second group
        mainTab = findViewById(R.id.imageView19);
        mainTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.setVisibility(View.VISIBLE);
                secondPage.setVisibility(View.INVISIBLE);

            }
        });
        motorTemp = findViewById(R.id.motortemp6);
        coolantTemp = findViewById(R.id.coolantTemp6);
        battery = findViewById(R.id.battery6);
        coolantPressure = findViewById(R.id.coolantPressure6);
        amps = findViewById(R.id.amps6);
        secondPage = findViewById(R.id.secondPage);
        secondPage.setVisibility(View.INVISIBLE);

        //use settings to manage the visibility of different layers
        settingTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.setVisibility(View.INVISIBLE);
                secondPage.setVisibility(View.VISIBLE);

            }
        });




        //textView.setOnClickListener(new View.OnClickListener());
//        battery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean ye = true;
//                int i = 0;
//                battery.setText("69");
////                while (ye) {
////                    battery.setText(i);
////                    i++;
////                }
//            }
//        });

    }
    public void openSettings() {
        Intent intent = new Intent(this, MainSetting.class);
        startActivity(intent);
    }


    public void testSwingTimer() {
        final Handler handler = new Handler();
        TimerTask task = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        battery.setText(String.valueOf(sendNums()));

                    }

                    public int sendNums() {
                        int min = 0;
                        int max = 300;

                        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                        return random_int;
                    }
                });


            }

            };
            Timer t = new Timer("Timer");
            t.schedule(task, 0, 1000);
        }
    }
    
