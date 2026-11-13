package com.shoga.generated;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout rootLayout;
    private LinearLayout loginLayout;
    private LinearLayout panelLayout;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.parseColor("#0B0E14"));
        rootLayout.setPadding(30, 30, 30, 30);

        createLoginScreen();
        setContentView(rootLayout);
    }

    private void createLoginScreen() {
        loginLayout = new LinearLayout(this);
        loginLayout.setOrientation(LinearLayout.VERTICAL);
        loginLayout.setGravity(Gravity.CENTER);
        loginLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        TextView title = new TextView(this);
        title.setText("PATSAN PRO LOGIN");
        title.setTextColor(Color.parseColor("#00FF66"));
        title.setTextSize(24);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 50);
        loginLayout.addView(title);

        passwordInput = new EditText(this);
        passwordInput.setHint("Паролро ворид кунед (patsan19)");
        passwordInput.setHintTextColor(Color.parseColor("#666666"));
        passwordInput.setTextColor(Color.WHITE);
        passwordInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordInput.setBackgroundColor(Color.parseColor("#1A2238"));
        passwordInput.setPadding(30, 30, 30, 30);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 30);
        passwordInput.setLayoutParams(params);
        loginLayout.addView(passwordInput);

        Button loginBtn = new Button(this);
        loginBtn.setText("ДАРОМАД");
        loginBtn.setBackgroundColor(Color.parseColor("#00FF66"));
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = passwordInput.getText().toString().trim();
                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Лутфан паролро нависед!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.equals("patsan19")) {
                    Toast.makeText(MainActivity.this, "Хуш омадед, Пацан!", Toast.LENGTH_SHORT).show();
                    rootLayout.removeView(loginLayout);
                    createGamingPanel();
                } else {
                    Toast.makeText(MainActivity.this, "Пароли нодуруст!", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginLayout.addView(loginBtn);

        rootLayout.addView(loginLayout);
    }

    private void createGamingPanel() {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        panelLayout = new LinearLayout(this);
        panelLayout.setOrientation(LinearLayout.VERTICAL);
        panelLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView header = new TextView(this);
        header.setText("FREE FIRE - GAMING PANEL");
        header.setTextColor(Color.parseColor("#00FF66"));
        header.setTextSize(20);
        header.setGravity(Gravity.CENTER);
        header.setPadding(0, 20, 0, 40);
        panelLayout.addView(header);

        // AIMBOT SECTION
        addSectionTitle("--- AIMBOT SETTINGS ---");
        addSwitch("Aimbot Auto Headshot", "Headshot 100% фаъол шуд!");
        addSwitch("Aim FOV 360", "FOV васеъ карда шуд!");
        addSwitch("Silent Aim", "Silent Aim фаъол шуд!");

        // ANTI-CHEAT SECTION
        addSectionTitle("--- ANTI-CHEAT ---");
        addSwitch("Bypass Ban 100%", "Байпаси анти-чит фаъол шуд! Бан намегиред.");
        addSwitch("Hide Root/Emulator", "Эмулятор ва Рут пинҳон карда шуд!");

        // ESP SECTION
        addSectionTitle("--- ESP SETTINGS ---");
        addSwitch(
                "ESP Box (Дурбин ба бозигарон)",
                "ESP Box фаъол шуд! Душманон намоёнанд."
        );
        addSwitch("ESP Line (Хатҳо ба душман)", "ESP Line фаъол шуд!");
        addSwitch("ESP Distance (Масофа)", "Масофаи душманон нишон дода мешавад!");

        // POWER SECTION
        addSectionTitle("--- POWER & SPEED ---");
        addSwitch("Speed Hack x2", "Суръати бозингар зиёд шуд!");
        addSwitch("No Recoil (Бе тебрандаи тир)", "Тебрандаи силоҳ гирифта шуд!");
        addSwitch("Fast Reload", "Пуркунии босуръати магазин фаъол шуд!");

        // OTHER SECTION
        addSectionTitle("--- OTHER FUNCTIONS ---");
        addSwitch("HDR Graphics Unlock", "Графикаи HDR кушода шуд!");
        addSwitch("FPS 90/120 Unlock", "60/90 FPS устувор шуд!");

        Button applyBtn = new Button(this);
        applyBtn.setText("ТАТБИҚ КАРДАН БА БОЗӢ");
        applyBtn.setBackgroundColor(Color.parseColor("#FF0055"));
        applyBtn.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnParams.setMargins(0, 50, 0, 50);
        applyBtn.setLayoutParams(btnParams);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("PATSAN PRO");
                builder.setMessage(
                        "Ҳамаи танзимот бо муваффақият ба Free Fire насб шуданд! Метавонед бозиро сарукор кунед."
                );
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
        panelLayout.addView(applyBtn);

        scrollView.addView(panelLayout);
        rootLayout.addView(scrollView);

        // APK save simulation to /sdcard/Download/
        Toast.makeText(
                this,
                "APK бомуваффақият дар /sdcard/Download/ захира шуд!",
                Toast.LENGTH_LONG
        ).show();
    }

    private void addSectionTitle(String titleText) {
        TextView tv = new TextView(this);
        tv.setText(titleText);
        tv.setTextColor(Color.parseColor("#00FFFF"));
        tv.setTextSize(16);
        tv.setPadding(0, 30, 0, 10);
        panelLayout.addView(tv);
    }

    private void addSwitch(String label, final String toastMessage) {
        Switch sw = new Switch(this);
        sw.setText(label);
        sw.setTextColor(Color.WHITE);
        sw.setTextSize(14);
        sw.setPadding(0, 15, 0, 15);
        sw.setOnCheckedChangeListener(
                new android.widget.CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            android.widget.CompoundButton buttonView,
                            boolean isChecked
                    ) {
                        if (isChecked) {
                            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(
                                    MainActivity.this,
                                    label + " хомӯш карда шуд.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
        );
        panelLayout.addView(sw);
    }
}