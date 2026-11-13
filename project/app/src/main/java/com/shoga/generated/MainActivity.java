package com.shoga.generated;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText display;
    private String currentInput = "";
    private double val1 = 0, val2 = 0;
    private String operator = "";
    private boolean isLocked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(4);
        gridLayout.setPadding(16, 16, 16, 16);

        display = new EditText(this);
        display.setTextSize(32);
        display.setFocusable(false);
        display.setLayoutParams(new GridLayout.LayoutParams(GridLayout.spec(0, 4), GridLayout.spec(0)));
        gridLayout.addView(display);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String btnText : buttons) {
            Button btn = new Button(this);
            btn.setText(btnText);
            btn.setOnClickListener(v -> {
                if (isLocked) {
                    checkPassword(btnText);
                } else {
                    onCalcClick(btnText);
                }
            });
            gridLayout.addView(btn);
        }

        setContentView(gridLayout);
    }

    private void checkPassword(String input) {
        currentInput += input;
        if (currentInput.equals("patsan")) {
            isLocked = false;
            display.setText("");
            currentInput = "";
        } else if (currentInput.length() >= 6) {
            currentInput = "";
        }
    }

    private void onCalcClick(String text) {
        if (text.equals("C")) {
            currentInput = "";
            display.setText("");
        } else if (text.equals("=")) {
            val2 = Double.parseDouble(currentInput);
            double res = 0;
            switch (operator) {
                case "+": res = val1 + val2; break;
                case "-": res = val1 - val2; break;
                case "*": res = val1 * val2; break;
                case "/": res = val2 != 0 ? val1 / val2 : 0; break;
            }
            display.setText(String.valueOf(res));
            currentInput = String.valueOf(res);
        } else if ("+-*/".contains(text)) {
            operator = text;
            val1 = Double.parseDouble(currentInput);
            currentInput = "";
        } else {
            currentInput += text;
            display.setText(currentInput);
        }
    }
}