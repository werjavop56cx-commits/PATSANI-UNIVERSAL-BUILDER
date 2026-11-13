package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean isAimbotEnabled = false;
    private boolean isWallhackEnabled = false;
    private boolean isSpeedhackEnabled = false;
    private int fovValue = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asosiy konteyner (Root Layout)
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.parseColor("#121212"));
        rootLayout.setPadding(32, 32, 32, 32);

        // Sarlavha (Title)
        TextView titleText = new TextView(this);
        titleText.setText("SHOGA BALA MOD PANEL");
        titleText.setTextColor(Color.parseColor("#00E676"));
        titleText.setTextSize(22);
        titleText.setGravity(Gravity.CENTER);
        titleText.setPadding(0, 0, 0, 32);
        rootLayout.addView(titleText);

        // ScrollView барои қисми менюҳо
        ScrollView scrollView = new ScrollView(this);
        LinearLayout scrollContent = new LinearLayout(this);
        scrollContent.setOrientation(LinearLayout.VERTICAL);

        // Switch 1: Aimbot
        Switch switchAimbot = new Switch(this);
        switchAimbot.setText("Bala Aimbot (Pro)");
        switchAimbot.setTextColor(Color.WHITE);
        switchAimbot.setTextSize(16);
        switchAimbot.setPadding(0, 16, 0, 16);
        switchAimbot.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isAimbotEnabled = isChecked;
            String status = isChecked ? "Фаъол шуд" ": Хомӯш шуд";
            Toast.makeText(MainActivity.this, "Aimbot " + status, Toast.LENGTH_SHORT).show();
        });
        scrollContent.addView(switchAimbot);

        // Switch 2: Wallhack
        Switch switchWallhack = new Switch(this);
        switchWallhack.setText("Bala Wallhack (ESP)");
        switchWallhack.setTextColor(Color.WHITE);
        switchWallhack.setTextSize(16);
        switchWallhack.setPadding(0, 16, 0, 16);
        switchWallhack.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isWallhackEnabled = isChecked;
            String status = isChecked ? "Фаъол шуд" : "Хомӯш шуд";
            Toast.makeText(MainActivity.this, "Wallhack " + status, Toast.LENGTH_SHORT).show();
        });
        scrollContent.addView(switchWallhack);

        // Switch 3: Speedhack
        Switch switchSpeed = new Switch(this);
        switchSpeed.setText("Bala Speedhack (X2)");
        switchSpeed.setTextColor(Color.WHITE);
        switchSpeed.setTextSize(16);
        switchSpeed.setPadding(0, 16, 0, 16);
        switchSpeed.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isSpeedhackEnabled = isChecked;
            String status = isChecked ? "Фаъол шуд" : "Хомӯш шуд";
            Toast.makeText(MainActivity.this, "Speedhack " + status, Toast.LENGTH_SHORT).show();
        });
        scrollContent.addView(switchSpeed);

        // FOV Button
        Button btnFov = new Button(this);
        btnFov.setText("FOV Settings: " + fovValue);
        btnFov.setBackgroundColor(Color.parseColor("#1F1F1F"));
        btnFov.setTextColor(Color.parseColor("#00E676"));
        btnFov.setPadding(0, 24, 0, 24);
        btnFov.setOnClickListener(v -> {
            fovValue = (fovValue == 120) ? 90 : fovValue + 15;
            btnFov.setText("FOV Settings: " + fovValue);
            Toast.makeText(MainActivity.this, "FOV иваз шуд: " + fovValue, Toast.LENGTH_SHORT).show();
        });
        scrollContent.addView(btnFov);

        // Apply Mods Button
        Button btnApply = new Button(this);
        btnApply.setText("Apply All Bala Mods");
        btnApply.setBackgroundColor(Color.parseColor("#00E676"));
        btnApply.setTextColor(Color.BLACK);
        btnApply.setPadding(0, 32, 0, 32);
        
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnParams.setMargins(0, 48, 0, 0);
        btnApply.setLayoutParams(btnParams);

        btnApply.setOnClickListener(v -> {
            StringBuilder result = new StringBuilder("Mods Applied:\n");
            result.append("Aimbot: ").append(isAimbotEnabled).append("\n");
            result.append("Wallhack: ").append(isWallhackEnabled).append("\n");
            result.append("Speedhack: ").append(isSpeedhackEnabled).append("\n");
            result.append("FOV: ").append(fovValue);

            Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
        });
        scrollContent.addView(btnApply);

        scrollView.addView(scrollContent);
        rootLayout.addView(scrollView);

        // Set Content View
        setContentView(rootLayout);
    }
}
