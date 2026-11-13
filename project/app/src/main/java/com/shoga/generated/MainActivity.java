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

    private EditText passInput;
    private Switch headshotSwitch, fpsSwitch, lagSwitch, bypassSwitch;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main Layout (Gaming Style - Dark Theme & Neon Accents)
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.parseColor("#0A0A0C"));
        rootLayout.setPadding(30, 30, 30, 30);

        // Title
        TextView title = new TextView(this);
        title.setText("PATSAN PRO PANEL");
        title.setTextColor(Color.parseColor("#00FF66"));
        title.setTextSize(24);
        title.setTypeface(null, android.graphics.Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 20, 0, 40);
        rootLayout.addView(title);

        // ScrollView for features
        ScrollView scrollView = new ScrollView(this);
        LinearLayout scrollContent = new LinearLayout(this);
        scrollContent.setOrientation(LinearLayout.VERTICAL);

        // Password Section
        TextView passLabel = new TextView(this);
        passLabel.setText("ENTER ACTIVATION KEY:");
        passLabel.setTextColor(Color.parseColor("#FFFFFF"));
        scrollContent.addView(passLabel);

        passInput = new EditText(this);
        passInput.setHint("Enter Password");
        passInput.setHintTextColor(Color.parseColor("#555555"));
        passInput.setTextColor(Color.parseColor("#FFFFFF"));
        passInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        scrollContent.addView(passInput);

        Button loginBtn = createGamingButton("AUTHORIZE");
        loginBtn.setOnClickListener(v -> {
            String pass = passInput.getText().toString().trim();
            if (pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Key field is empty!", Toast.LENGTH_SHORT).show();
            } else if (pass.equals("PATSAN2025")) {
                Toast.makeText(MainActivity.this, "Access Granted! Welcome Pro.", Toast.LENGTH_LONG).show();
                statusText.setText("Status: ACTIVE");
                statusText.setTextColor(Color.parseColor("#00FF66"));
            } else {
                Toast.makeText(MainActivity.this, "Invalid Key! Access Denied.", Toast.LENGTH_SHORT).show();
            }
        });
        scrollContent.addView(loginBtn);

        // Separator
        View sep = new View(this);
        sep.setMinimumHeight(5);
        sep.setBackgroundColor(Color.parseColor("#222222"));
        LinearLayout.LayoutParams sepParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 20);
        sepParams.setMargins(0, 20, 0, 20);
        sep.setLayoutParams(sepParams);
        scrollContent.addView(sep);

        // Status Display
        statusText = new TextView(this);
        statusText.setText("Status: LOCKED");
        statusText.setTextColor(Color.parseColor("#FF3333"));
        statusText.setTextSize(16);
        statusText.setGravity(Gravity.CENTER);
        scrollContent.addView(statusText);

        // Switches for Pro Features
        headshotSwitch = createGamingSwitch("AIMBOT HEADSHOT 100%");
        fpsSwitch = createGamingSwitch("UNLOCK 90 FPS");
        lagSwitch = createGamingSwitch("ANTI-LAG FIX");
        bypassSwitch = createGamingSwitch("BYPASS ANTI-CHEAT");

        scrollContent.addView(headshotSwitch);
        scrollContent.addView(fpsSwitch);
        scrollContent.addView(lagSwitch);
        scrollContent.addView(bypassSwitch);

        // Action Button
        Button applyBtn = createGamingButton("APPLY CONFIG");
        applyBtn.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder("Applied:\n");
            if (headshotSwitch.isChecked()) sb.append("- Headshot Aimbot\n");
            if (fpsSwitch.isChecked()) sb.append("- 90 FPS Unlocked\n");
            if (lagSwitch.isChecked()) sb.append("- Anti-Lag Enabled\n");
            if (bypassSwitch.isChecked()) sb.append("- Bypass Active\n");
            
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("PATSAN PANEL CONFIG");
            builder.setMessage(sb.toString());
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
        scrollContent.addView(applyBtn);

        scrollView.addView(scrollContent);
        rootLayout.addView(scrollView);

        setContentView(rootLayout);
    }

    private Button createGamingButton(String text) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setTextColor(Color.parseColor("#FFFFFF"));
        btn.setBackgroundColor(Color.parseColor("#1E1E24"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(0, 15, 0, 15);
        btn.setLayoutParams(params);
        return btn;
    }

    private Switch createGamingSwitch(String text) {
        Switch sw = new Switch(this);
        sw.setText(text);
        sw.setTextColor(Color.parseColor("#CCCCCC"));
        sw.setPadding(20, 20, 20, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        sw.setLayoutParams(params);
        return sw;
    }
}