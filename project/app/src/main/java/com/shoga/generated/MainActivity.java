package com.shoga.generated;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText inputNumber1, inputNumber2, inputPassword;
    private TextView textResult;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.widget.LinearLayout rootLayout = new android.widget.LinearLayout(this);
        rootLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        rootLayout.setPadding(32, 32, 32, 32);
        rootLayout.setBackgroundColor(0xFF121212);

        TextView title = new TextView(this);
        title.setText("PATSAN PRO - Drip Client");
        title.setTextSize(22);
        title.setTextColor(0xFF03DAC5);
        title.setGravity(android.view.Gravity.CENTER);
        title.setPadding(0, 0, 0, 32);
        rootLayout.addView(title);

        inputPassword = new EditText(this);
        inputPassword.setHint("Паролро ворид кунед");
        inputPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        inputPassword.setTextColor(0xFFFFFFFF);
        inputPassword.setHintTextColor(0xFF888888);
        rootLayout.addView(inputPassword);

        btnLogin = new Button(this);
        btnLogin.setText("Санҷиши парол");
        btnLogin.setBackgroundColor(0xFF6200EE);
        btnLogin.setTextColor(0xFFFFFFFF);
        rootLayout.addView(btnLogin);

        View spacer = new View(this);
        spacer.setMinimumHeight(40);
        rootLayout.addView(spacer);

        inputNumber1 = new EditText(this);
        inputNumber1.setHint("Рақами 1");
        inputNumber1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        inputNumber1.setTextColor(0xFFFFFFFF);
        inputNumber1.setHintTextColor(0xFF888888);
        rootLayout.addView(inputNumber1);

        inputNumber2 = new EditText(this);
        inputNumber2.setHint("Рақами 2");
        inputNumber2.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        inputNumber2.setTextColor(0xFFFFFFFF);
        inputNumber2.setHintTextColor(0xFF888888);
        rootLayout.addView(inputNumber2);

        android.widget.LinearLayout btnLayout = new android.widget.LinearLayout(this);
        btnLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL);
        btnLayout.setGravity(android.view.Gravity.CENTER);

        btnAdd = new Button(this);
        btnAdd.setText("+");
        btnAdd.setTextColor(0xFFFFFFFF);
        btnAdd.setBackgroundColor(0xFF3700B3);
        btnLayout.addView(btnAdd);

        btnSubtract = new Button(this);
        btnSubtract.setText("-");
        btnSubtract.setTextColor(0xFFFFFFFF);
        btnSubtract.setBackgroundColor(0xFF3700B3);
        btnLayout.addView(btnSubtract);

        btnMultiply = new Button(this);
        btnMultiply.setText("*");
        btnMultiply.setTextColor(0xFFFFFFFF);
        btnMultiply.setBackgroundColor(0xFF3700B3);
        btnLayout.addView(btnMultiply);

        btnDivide = new Button(this);
        btnDivide.setText("/");
        btnDivide.setTextColor(0xFFFFFFFF);
        btnDivide.setBackgroundColor(0xFF3700B3);
        btnLayout.addView(btnDivide);

        rootLayout.addView(btnLayout);

        textResult = new TextView(this);
        textResult.setText("Натиҷа: ");
        textResult.setTextSize(18);
        textResult.setTextColor(0xFF03DAC5);
        textResult.setPadding(0, 32, 0, 0);
        rootLayout.addView(textResult);

        setContentView(rootLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = inputPassword.getText().toString();
                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Лутфан паролро нависед!", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("patsan2024")) {
                    Toast.makeText(MainActivity.this, "Парол дуруст аст! Хуш омадед!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Пароли хато!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });
    }

    private void calculate(String op) {
        String s1 = inputNumber1.getText().toString();
        String s2 = inputNumber2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Лутфан ҳарду рақамро пур кунед!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double res = 0;

            switch (op) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Хато: Тақсимкунӣ ба сифр мумкин нест!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    res = num1 / num2;
                    break;
            }
            textResult.setText("Натиҷа: " + res);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Формати рақам хато аст!", Toast.LENGTH_SHORT).show();
        }
    }
}