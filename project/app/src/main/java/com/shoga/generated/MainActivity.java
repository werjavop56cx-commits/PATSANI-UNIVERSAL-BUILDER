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

    private EditText edtUsername, edtPassword;
    private Button btnSave;
    private Switch switchAim, switchEsp, switchSpeed;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main Layout
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.parseColor("#121212"));
        rootLayout.setPadding(32, 32, 32, 32);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Title
        TextView tvTitle = new TextView(this);
        tvTitle.setText("SHOGA FREE FIRE PANEL");
        tvTitle.setTextColor(Color.parseColor("#00E676"));
        tvTitle.setTextSize(22);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setPadding(0, 0, 0, 32);
        contentLayout.addView(tvTitle);

        // Username Input
        edtUsername = new EditText(this);
        edtUsername.setHint("Номи нави корбар (Username)");
        edtUsername.setHintTextColor(Color.parseColor("#757575"));
        edtUsername.setTextColor(Color.WHITE);
        edtUsername.setBackgroundColor(Color.parseColor("#1E1E1E"));
        edtUsername.setPadding(24, 24, 24, 24);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        edtUsername.setLayoutParams(params);
        contentLayout.addView(edtUsername);

        // Password Input
        edtPassword = new EditText(this);
        edtPassword.setHint("Пароли нав (Password)");
        edtPassword.setHintTextColor(Color.parseColor("#757575"));
        edtPassword.setTextColor(Color.WHITE);
        edtPassword.setBackgroundColor(Color.parseColor("#1E1E1E"));
        edtPassword.setPadding(24, 24, 24, 24);
        edtPassword.setLayoutParams(params);
        contentLayout.addView(edtPassword);

        // Save Button
        btnSave = new Button(this);
        btnSave.setText("ИВВАЗ КУНИИ МАЪЛУМОТ");
        btnSave.setTextColor(Color.WHITE);
        btnSave.setBackgroundColor(Color.parseColor("#6200EE"));
        btnSave.setPadding(24, 24, 24, 24);
        btnSave.setLayoutParams(params);
        contentLayout.addView(btnSave);

        // Cheats Section Title
        TextView tvCheats = new TextView(this);
        tvCheats.setText("Танзимоти Бозӣ (Features)");
        tvCheats.setTextColor(Color.parseColor("#FF9800"));
        tvCheats.setTextSize(18);
        tvCheats.setPadding(0, 32, 0, 16);
        contentLayout.addView(tvCheats);

        // Switches
        switchAim = new Switch(this);
        switchAim.setText("AimLock 360");
        switchAim.setTextColor(Color.WHITE);
        switchAim.setPadding(0, 16, 0, 16);
        contentLayout.addView(switchAim);

        switchEsp = new Switch(this);
        switchEsp.setText("ESP Box / Line");
        switchEsp.setTextColor(Color.WHITE);
        switchEsp.setPadding(0, 16, 0, 16);
        contentLayout.addView(switchEsp);

        switchSpeed = new Switch(this);
        switchSpeed.setText("Speed Hack x2");
        switchSpeed.setTextColor(Color.WHITE);
        switchSpeed.setPadding(0, 16, 0, 32);
        contentLayout.addView(switchSpeed);

        // Status Label
        tvStatus = new TextView(this);
        tvStatus.setText("Ҳолат: Тайёр ба кор");
        tvStatus.setTextColor(Color.parseColor("#00E676"));
        tvStatus.setGravity(Gravity.CENTER);
        contentLayout.addView(tvStatus);

        // Logic
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String user = edtUsername.getText().toString().trim();
                    String pass = edtPassword.getText().toString().trim();

                    if (user.isEmpty() || pass.isEmpty()) {
                        Toast.makeText(MainActivity.class.cast(MainActivity.this), "Лутфан ҳамаи майдонҳоро пур кунед!", Toast.LENGTH_SHORT).show();
                    } else {
                        tvStatus.setText("Успешно! Ном ва парол иваз шуданд.");
                        Toast.makeText(MainActivity.this, "Маълумот бо муваффақият сабт шуд!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Хатогӣ рух дод: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        scrollView.addView(contentLayout);
        rootLayout.addView(scrollView);
        setContentView(rootLayout);
    }
}
