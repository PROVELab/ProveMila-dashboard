package ui30;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui30.R;

public class MainSetting extends AppCompatActivity {
    private View mainTab;
    private TextView motorTemp;
    private TextView coolantTemp;
    private ImageView coolant;
    private TextView battery;
    private ImageView battery_icon;
    private TextView coolantPressure;
    private TextView amps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region fullscreen code [to be placed before setContentView]
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        //Hiding navigation bar
        View settings = findViewById(R.id.settingsLayout);
        settings.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        //Giving button attributes to top icons to move between settings and main pages
        mainTab = findViewById(R.id.carIcon);
        mainTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TextViews
        motorTemp = findViewById(R.id.motortemp);
        coolantTemp = findViewById(R.id.coolantTemp2);
        battery = findViewById(R.id.battery3);
        coolantPressure = findViewById(R.id.coolantPressure);
        amps = findViewById(R.id.amps);

        // ImageViews
        battery_icon = findViewById(R.id.battery_icon);
        coolant = findViewById(R.id.coolant_icon);

    }

    private void updateDisplayValues(String motorTempValue, String coolantTempValue, String batteryValue, String coolantPressureValue, String ampsValue) {
        motorTemp.setText(motorTempValue);
        coolantTemp.setText(coolantTempValue);
        coolant.setImageLevel(6000);  //make these values part of method header.
        battery.setText(batteryValue);
        battery_icon.setImageLevel(6000); //
        coolantPressure.setText(coolantPressureValue);
        amps.setText(ampsValue);
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Clicked(View view) {
        openMain();
    }
}
