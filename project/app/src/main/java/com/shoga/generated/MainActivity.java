package com.shoga.generated;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean isAuthorized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkOverlayPermission();
    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Талаботи иҷозат / Permission Required");
                builder.setMessage("Барои кор кардани панел, лутфан иҷозаи 'Поверх других окон' -ро фаъол созед.");
                builder.setPositiveButton("Танзимот / Settings", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 1234);
                });
                builder.setNegativeButton("Баромад", (dialog, which) -> finish());
                builder.setCancelable(false);
                builder.show();
            } else {
                showLoginScreen();
            }
        } else {
            showLoginScreen();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    showLoginScreen();
                } else {
                    Toast.makeText(this, "Иҷозат дода нашуд!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    private void showLoginScreen() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#121212"));
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("SHOGA ALL VIP PANEL");
        title.setTextColor(Color.parseColor("#00FF66"));
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        TextView subTitle = new TextView(this);
        subTitle.setText("Free Fire Anti-Ban Panel");
        subTitle.setTextColor(Color.WHITE);
        subTitle.setTextSize(14);
        subTitle.setGravity(Gravity.CENTER);
        subTitle.setPadding(0, 0, 0, 40);
        layout.addView(subTitle);

        final EditText passwordInput = new EditText(this);
        passwordInput.setHint("Паролро ворид кунед (Код)");
        passwordInput.setHintTextColor(Color.GRAY);
        passwordInput.setTextColor(Color.WHITE);
        passwordInput.setBackgroundColor(Color.parseColor("#242424"));
        passwordInput.setPadding(30, 30, 30, 30);
        layout.addView(passwordInput);

        Button loginBtn = new Button(this);
        loginBtn.setText("КУШОДАНИ ПАНЕЛ / LOGIN");
        loginBtn.setBackgroundColor(Color.parseColor("#00FF66"));
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setPadding(0, 30, 0, 30);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 30, 0, 0);
        loginBtn.setLayoutParams(params);

        loginBtn.setOnClickListener(v -> {
            String pass = passwordInput.getText().toString().trim();
            if (pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Майдони парол холи аст!", Toast.LENGTH_SHORT).show();
            } else if (pass.equals("shoga2025")) {
                isAuthorized = true;
                Toast.makeText(MainActivity.this, "Парол дуруст аст! Панел кушода шуд.", Toast.LENGTH_LONG).show();
                showControlPanel();
            } else {
                Toast.makeText(MainActivity.this, "Пароли нодуруст! Код: shoga2025", Toast.LENGTH_LONG).show();
            }
        });
        layout.addView(loginBtn);

        setContentView(layout);
    }

    private void showControlPanel() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#1a1a1a"));
        layout.setPadding(30, 30, 30, 30);

        TextView header = new TextView(this);
        header.setText("SHOGA ALL - FF HACK PANEL");
        header.setTextColor(Color.parseColor("#00FF66"));
        header.setTextSize(20);
        header.setGravity(Gravity.CENTER);
        header.setPadding(0, 0, 0, 20);
        layout.addView(header);

        // Anti-Cheat Status
        TextView antiCheatStatus = new TextView(this);
        antiCheatStatus.setText("🛡️ Anti-Cheat Bypass: ACTIVE (Secure)");
        antiCheatStatus.setTextColor(Color.parseColor("#00FFcc"));
        antiCheatStatus.setTextSize(14);
        antiCheatStatus.setPadding(0, 0, 0, 20);
        layout.addView(antiCheatStatus);

        // ESP Switches
        Switch espBox = new Switch(this);
        espBox.setText("ESP Box (Чехраи душман)");
        espBox.setTextColor(Color.WHITE);
        espBox.setChecked(true);
        layout.addView(espBox);

        Switch espLine = new Switch(this);
        espLine.setText("ESP Line (Хати нишондод)");
        espLine.setTextColor(Color.WHITE);
        espLine.setChecked(true);
        layout.addView(espLine);

        Switch espDistance = new Switch(this);
        espDistance.setText("ESP Distance (Масофа)");
        espDistance.setTextColor(Color.WHITE);
        espDistance.setChecked(true);
        layout.addView(espDistance);

        // Aimbot Switches
        Switch aimbotHead = new Switch(this);
        aimbotHead.setText("Aimbot Head (Низоми сар)");
        aimbotHead.setTextColor(Color.WHITE);
        aimbotHead.setChecked(true);
        layout.addView(aimbotHead);

        Switch aimbotFov = new Switch(this);
        aimbotFov.setText("Aimbot FOV 360 (Доираи нишон)");
        aimbotFov.setTextColor(Color.WHITE);
        aimbotFov.setChecked(true);
        layout.addView(aimbotFov);

        Switch headShotRate = new Switch(this);
        headShotRate.setText("🎯 Auto Headshot 100% (Хечот)");
        headShotRate.setTextColor(Color.parseColor("#FF0055"));
        headShotRate.setChecked(true);
        layout.addView(headShotRate);

        Button launchFFBtn = new Button(this);
        launchFFBtn.setText("🚀 БУРДАН БА FREE FIRE (START GAME)");
        launchFFBtn.setBackgroundColor(Color.parseColor("#00FF66"));
        launchFFBtn.setTextColor(Color.BLACK);
        
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        btnParams.setMargins(0, 40, 0, 0);
        launchFFBtn.setLayoutParams(btnParams);

        launchFFBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Ҳамаи функсияҳо фаъол шуданд. Free Fire кушода мешавад...", Toast.LENGTH_LONG).show();
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.dts.freefireth");
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Toast.makeText(MainActivity.this, "Free Fire дар ин дастгоҳ ёфт нашуд!", Toast.LENGTH_LONG).show();
            }
        });
        layout.addView(launchFFBtn);

        setContentView(layout);
    }
}