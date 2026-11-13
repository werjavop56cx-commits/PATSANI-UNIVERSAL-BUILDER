package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean isAimBotActive = false;
    private boolean isWallHackActive = false;
    private boolean isSpeedHackActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asosiy konteyner (Root Layout)
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        rootLayout.setBackgroundColor(Color.parseColor("#121212"));
        rootLayout.setPadding(32, 32, 32, 32);

        // Sarlavha (Title)
        TextView titleText = new TextView(this);
        titleText.setText("BALA MODS PANEL v10.0");
        titleText.setTextColor(Color.parseColor("#00FF66"));
        titleText.setTextSize(22);
        titleText.setGravity(Gravity.CENTER);
        titleText.setPadding(0, 20, 0, 40);
        rootLayout.addView(titleText);

        // ScrollView барои қисми модулҳо
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1.0f
        ));

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // 1. AimBot Switch
        final Switch switchAimBot = new Switch(this);
        switchAimBot.setText("AimBot (Auto Lock)");
        switchAimBot.setTextColor(Color.WHITE);
        switchAimBot.setTextSize(16);
        switchAimBot.setPadding(0, 20, 0, 20);
        switchAimBot.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isAimBotActive = isChecked;
            String msg = isChecked ? "AimBot фаъол шуд!" : "AimBot хомӯш шуд!";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });
        contentLayout.addView(switchAimBot);

        // 2. WallHack Switch
        final Switch switchWallHack = new Switch(this);
        switchWallHack.setText("WallHack (ESP)");
        switchWallHack.setTextColor(Color.WHITE);
        switchWallHack.setTextSize(16);
        switchWallHack.setPadding(0, 20, 0, 20);
        switchWallHack.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isWallHackActive = isChecked;
            String msg = isChecked ? "WallHack фаъол шуд!" : "WallHack хомӯш шуд!";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });
        contentLayout.addView(switchWallHack);

        // 3. SpeedHack Switch
        final Switch switchSpeedHack = new Switch(this);
        switchSpeedHack.setText("SpeedHack x2.0");
        switchSpeedHack.setTextColor(Color.WHITE);
        switchSpeedHack.setTextSize(16);
        switchSpeedHack.setPadding(0, 20, 0, 20);
        switchSpeedHack.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isSpeedHackActive = isChecked;
            String msg = isChecked ? "SpeedHack фаъол шуд!" : "SpeedHack хомӯш шуд!";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });
        contentLayout.addView(switchSpeedHack);

        // Tugman Inject (Inject Button)
        Button btnInject = new Button(this);
        btnInject.setText("INJECT MODS");
        btnInject.setTextColor(Color.BLACK);
        btnInject.setBackgroundColor(Color.parseColor("#00FF66"));
        btnInject.setPadding(0, 30, 0, 30);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        btnParams.setMargins(0, 40, 0, 0);
        btnInject.setLayoutParams(btnParams);
        btnInject.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Модулҳо бомуваффақият ворид карда шуданд!", Toast.LENGTH_LONG).show();
        });
        contentLayout.addView(btnInject);

        scrollView.addView(contentLayout);
        rootLayout.addView(scrollView);

        // Статусбар ё Маълумоти муаллиф
        TextView footerText = new TextView(this);
        footerText.setText("SHOGA ALL - Android Studio Engine API 35");
        footerText.setTextColor(Color.parseColor("#777777"));
        footerText.setTextSize(12);
        footerText.setGravity(Gravity.CENTER);
        footerText.setPadding(0, 20, 0, 0);
        rootLayout.addView(footerText);

        setContentView(rootLayout);
    }
}
