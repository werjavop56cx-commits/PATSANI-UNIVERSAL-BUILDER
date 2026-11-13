package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoginScreen();
    }

    private void showLoginScreen() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView title = new TextView(this);
        title.setText("TJ PATSAN - Воридшавӣ");
        title.setTextSize(20);

        EditText user = new EditText(this);
        user.setHint("Номи корбар");

        EditText pass = new EditText(this);
        pass.setHint("Парол");

        Button btn = new Button(this);
        btn.setText("Ворид шудан");
        btn.setOnClickListener(v -> {
            if(user.getText().toString().equals("patsan") && pass.getText().toString().equals("123")) {
                showMainScreen();
            } else {
                Toast.makeText(this, "Маълумот нодуруст аст!", Toast.LENGTH_SHORT).show();
            }
        });

        layout.addView(title);
        layout.addView(user);
        layout.addView(pass);
        layout.addView(btn);
        setContentView(layout);
    }

    private void showMainScreen() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);
        layout.setBackgroundColor(Color.LTGRAY);

        TextView welcome = new TextView(this);
        welcome.setText("Хуш омадед ба TJ PATSAN!");
        welcome.setTextSize(24);

        Button menu1 = new Button(this);
        menu1.setText("Менюи Асосӣ");

        Button logout = new Button(this);
        logout.setText("Баромад");
        logout.setOnClickListener(v -> showLoginScreen());

        layout.addView(welcome);
        layout.addView(menu1);
        layout.addView(logout);
        setContentView(layout);
    }
}