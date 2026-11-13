package com.shoga.generated;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
        mainLayout.setBackgroundColor(Color.parseColor("#121212"));

        TextView title = new TextView(this);
        title.setText("TJ PATSAN");
        title.setTextSize(30);
        title.setTextColor(Color.YELLOW);
        title.setGravity(Gravity.CENTER);

        EditText user = new EditText(this);
        user.setHint("Логин");
        user.setTextColor(Color.WHITE);
        user.setHintTextColor(Color.GRAY);

        EditText pass = new EditText(this);
        pass.setHint("Парол");
        pass.setTextColor(Color.WHITE);
        pass.setHintTextColor(Color.GRAY);
        pass.setInputType(129);

        Button btn = new Button(this);
        btn.setText("Даромадан");
        btn.setBackgroundColor(Color.YELLOW);
        btn.setOnClickListener(v -> showProfileScreen());

        mainLayout.addView(title);
        mainLayout.addView(user);
        mainLayout.addView(pass);
        mainLayout.addView(btn);
        setContentView(mainLayout);
    }

    private void showProfileScreen() {
        mainLayout.removeAllViews();
        
        TextView profile = new TextView(this);
        profile.setText("Хуш омадед, Patsan!\n\nМеню:\n1. Статус\n2. Суратҳо\n3. Танзимот");
        profile.setTextSize(20);
        profile.setTextColor(Color.WHITE);
        
        Button back = new Button(this);
        back.setText("Баромадан");
        back.setOnClickListener(v -> showLoginScreen());

        mainLayout.addView(profile);
        mainLayout.addView(back);
    }
}