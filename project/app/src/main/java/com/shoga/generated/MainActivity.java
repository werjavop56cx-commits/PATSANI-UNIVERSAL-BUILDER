package com.shoga.generated;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout settings
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(50, 50, 50, 50);
        layout.setBackgroundColor(Color.parseColor("#121212"));

        TextView title = new TextView(this);
        title.setText("TJ PATSAN PROFILE");
        title.setTextColor(Color.CYAN);
        title.setTextSize(24);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        EditText passwordInput = new EditText(this);
        passwordInput.setHint("Паролро ворид кунед");
        passwordInput.setTextColor(Color.WHITE);
        passwordInput.setHintTextColor(Color.GRAY);
        passwordInput.setInputType(129); // Password type
        layout.addView(passwordInput);

        Button loginButton = new Button(this);
        loginButton.setText("Даромадан");
        loginButton.setBackgroundColor(Color.parseColor("#00796B"));
        loginButton.setTextColor(Color.WHITE);
        layout.addView(loginButton);

        loginButton.setOnClickListener(v -> {
            String input = passwordInput.getText().toString();
            if (input.equals("patsan")) {
                Toast.makeText(this, "Хуш омадед, Патсан!", Toast.LENGTH_LONG).show();
                title.setText("ПРОФИЛИ ТУ: TJ PATSAN\nСтатус: Фаъол");
                title.setTextColor(Color.GREEN);
            } else {
                Toast.makeText(this, "Парол нодуруст аст!", Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(layout);
    }
}

/* 
Gradle Dependencies Required:
dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.0'
}
*/