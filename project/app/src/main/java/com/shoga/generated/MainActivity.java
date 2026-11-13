package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText passwordInput;
    private LinearLayout loginLayout, panelLayout;
    private Switch aimbotSwitch, headshotSwitch, noRecoilSwitch, speedSwitch;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main Root Layout
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.parseColor("#121212"));
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        rootLayout.setPadding(30, 30, 30, 30);

        // Title
        TextView title = new TextView(this);
        title.setText("SHOGA ALL VIP PANEL");
        title.setTextColor(Color.parseColor("#00FF66"));
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 20, 0, 40);
        rootLayout.addView(title);

        // 1. LOGIN SECTION
        loginLayout = new LinearLayout(this);
        loginLayout.setOrientation(LinearLayout.VERTICAL);
        loginLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        TextView labelPass = new TextView(this);
        labelPass.setText("Пароли даромадро ворид кунед (Парол: 777):");
        labelPass.setTextColor(Color.WHITE);
        labelPass.setTextSize(14);
        loginLayout.addView(labelPass);

        passwordInput = new EditText(this);
        passwordInput.setHint("Парол");
        passwordInput.setHintTextColor(Color.parseColor("#888888"));
        passwordInput.setTextColor(Color.WHITE);
        passwordInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        passwordInput.setBackgroundColor(Color.parseColor("#222222"));
        passwordInput.setPadding(20, 20, 20, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 20, 0, 20);
        passwordInput.setLayoutParams(params);
        loginLayout.addView(passwordInput);

        Button loginBtn = new Button(this);
        loginBtn.setText("Даромадан ба Панел");
        loginBtn.setBackgroundColor(Color.parseColor("#00FF66"));
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = passwordInput.getText().toString().trim();
                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Лутфан паролро ворид кунед!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.equals("777")) {
                    Toast.makeText(MainActivity.this, "Хуш омадед, Панел фаъол шуд!", Toast.LENGTH_LONG).show();
                    loginLayout.setVisibility(View.GONE);
                    panelLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Пароли нодуруст! Рамзи дуруст: 777", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginLayout.addView(loginBtn);
        rootLayout.addView(loginLayout);

        // 2. PANEL SECTION (Hidden initially)
        panelLayout = new LinearLayout(this);
        panelLayout.setOrientation(LinearLayout.VERTICAL);
        panelLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        panelLayout.setVisibility(View.GONE);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout scrollContent = new LinearLayout(this);
        scrollContent.setOrientation(LinearLayout.VERTICAL);

        statusText = new TextView(this);
        statusText.setText("Ҳолат: Бехатар (Anti-Ban 100%)");
        statusText.setTextColor(Color.parseColor("#00FF66"));
        statusText.setTextSize(16);
        statusText.setPadding(0, 10, 0, 20);
        scrollContent.addView(statusText);

        // Aimbot Switch
        aimbotSwitch = new Switch(this);
        aimbotSwitch.setText("AIMBOT 100% (Auto Headshot)");
        aimbotSwitch.setTextColor(Color.WHITE);
        aimbotSwitch.setTextSize(16);
        aimbotSwitch.setPadding(0, 20, 0, 20);
        aimbotSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Aimbot фаъол шуд", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Aimbot хомӯш шуд", Toast.LENGTH_SHORT).show();
            }
        });
        scrollContent.addView(aimbotSwitch);

        // Headshot Switch
        headshotSwitch = new Switch(this);
        headshotSwitch.setText("MAGIC HEADSHOT (No Miss)");
        headshotSwitch.setTextColor(Color.WHITE);
        headshotSwitch.setTextSize(16);
        headshotSwitch.setPadding(0, 20, 0, 20);
        headshotSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Headshot фаъол шуд", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Headshot хомӯш шуд", Toast.LENGTH_SHORT).show();
            }
        });
        scrollContent.addView(headshotSwitch);

        // No Recoil Switch
        noRecoilSwitch = new Switch(this);
        noRecoilSwitch.setText("NO RECOIL (Бе ларзиши силоҳ)");
        noRecoilSwitch.setTextColor(Color.WHITE);
        noRecoilSwitch.setTextSize(16);
        noRecoilSwitch.setPadding(0, 20, 0, 20);
        noRecoilSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "No Recoil фаъол шуд", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "No Recoil хомӯш шуд", Toast.LENGTH_SHORT).show();
            }
        });
        scrollContent.addView(noRecoilSwitch);

        // Speed Switch
        speedSwitch = new Switch(this);
        speedSwitch.setText("SPEED HACK x2 (Суръати баланд)");
        speedSwitch.setTextColor(Color.WHITE);
        speedSwitch.setTextSize(16);
        speedSwitch.setPadding(0, 20, 0, 30);
        speedSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Speed Hack фаъол шуд", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Speed Hack хомӯш шуд", Toast.LENGTH_SHORT).show();
            }
        });
        scrollContent.addView(speedSwitch);

        // Launch Game Button
        Button launchGameBtn = new Button(this);
        launchGameBtn.setText("КУШОДАНИ FREE FIRE");
        launchGameBtn.setBackgroundColor(Color.parseColor("#FF5722"));
        launchGameBtn.setTextColor(Color.WHITE);
        launchGameBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Free Fire бо танзимоти SHOGA ALL оғоз мешавад...", Toast.LENGTH_LONG).show();
        });
        scrollContent.addView(launchGameBtn);

        scrollView.addView(scrollContent);
        panelLayout.addView(scrollView);
        rootLayout.addView(panelLayout);

        setContentView(rootLayout);
    }
}