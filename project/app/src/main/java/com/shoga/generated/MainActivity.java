package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoginScreen();
    }

    private void showLoginScreen() {
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.setPadding(50, 50, 50, 50);

        EditText user = new EditText(this);
        user.setHint("Логин: PATSAN");
        
        EditText pass = new EditText(this);
        pass.setHint("Парол: PATSAN");
        pass.setInputType(129); // Password type

        Button loginBtn = new Button(this);
        loginBtn.setText("Даромадан");
        loginBtn.setOnClickListener(v -> {
            if (user.getText().toString().equals("PATSAN") && pass.getText().toString().equals("PATSAN")) {
                showMenu();
            } else {
                Toast.makeText(this, "Хато! Логин ё парол нодуруст.", Toast.LENGTH_SHORT).show();
            }
        });

        mainLayout.addView(user);
        mainLayout.addView(pass);
        mainLayout.addView(loginBtn);
        setContentView(mainLayout);
    }

    private void showMenu() {
        mainLayout.removeAllViews();
        TextView title = new TextView(this);
        title.setText("МЕНЮИ TJ PATSAN");
        title.setTextSize(20);
        
        Button profileBtn = new Button(this);
        profileBtn.setText("Профил");
        profileBtn.setOnClickListener(v -> showProfile());

        Button exitBtn = new Button(this);
        exitBtn.setText("Баромадан");
        exitBtn.setOnClickListener(v -> showLoginScreen());

        mainLayout.addView(title);
        mainLayout.addView(profileBtn);
        mainLayout.addView(exitBtn);
    }

    private void showProfile() {
        mainLayout.removeAllViews();
        TextView profileInfo = new TextView(this);
        profileInfo.setText("Ном: PATSAN\nСтатус: Дар ҳаракат!\nСабк: Тоҷикӣ");
        
        Button backBtn = new Button(this);
        backBtn.setText("Бозгашт");
        backBtn.setOnClickListener(v -> showMenu());

        mainLayout.addView(profileInfo);
        mainLayout.addView(backBtn);
    }
}