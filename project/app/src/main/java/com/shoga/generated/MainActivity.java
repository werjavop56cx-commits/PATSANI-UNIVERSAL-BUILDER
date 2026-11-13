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

        // Сохтани интерфейс бо коди барномавӣ (Programmatic Layout)
        android.widget.LinearLayout rootLayout = new android.widget.LinearLayout(this);
        rootLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        rootLayout.setPadding(32, 32, 32, 32);
        rootLayout.setBackgroundColor(0xFF121212);

        // Title
        TextView title = new TextView(this);
        title.setText("PATSAN PRO - Drip Client");
        title.setTextColor(0xFF00E676);
        title.setTextSize(22);
        title.setGravity(android.view.Gravity.CENTER);
        rootLayout.addView(title);

        // Password Input
        inputPassword = new EditText(this);
        inputPassword.setHint("Паролро ворид кунед");
        inputPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        inputPassword.setTextColor(0xFFFFFFFF);
        inputPassword.setHintTextColor(0xFF888888);
        rootLayout.addView(inputPassword);

        // Login Button
        btnLogin = new Button(this);
        btnLogin.setText("Санҷиши парол");
        btnLogin.setBackgroundColor(0xFF00E676);
        btnLogin.setTextColor(0xFF000000);
        rootLayout.addView(btnLogin);

        // Calculator inputs
        inputNumber1 = new EditText(this);
        inputNumber1.setHint("Рақами якум");
        inputNumber1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
        inputNumber1.setTextColor(0xFFFFFFFF);
        inputNumber1.setHintTextColor(0xFF888888);
        rootLayout.addView(inputNumber1);

        inputNumber2 = new EditText(this);
        inputNumber2.setHint("Рақами дуюм");
        inputNumber2.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
        inputNumber2.setTextColor(0xFFFFFFFF);
        inputNumber2.setHintTextColor(0xFF888888);
        rootLayout.addView(inputNumber2);

        // Buttons Layout
        android.widget.LinearLayout btnLayout = new android.widget.LinearLayout(this);
        btnLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL);

        btnAdd = new Button(this);
        btnAdd.setText("+");
        btnLayout.addView(btnAdd);

        btnSubtract = new Button(this);
        btnSubtract.setText("-");
        btnLayout.addView(btnSubtract);

        btnMultiply = new Button(this);
        btnMultiply.setText("*");
        btnLayout.addView(btnMultiply);

        btnDivide = new Button(this);
        btnDivide.setText("/");
        btnLayout.addView(btnDivide);

        rootLayout.addView(btnLayout);

        // Result
        textResult = new TextView(this);
        textResult.setText("Натиҷа: ");
        textResult.setTextColor(0xFFFFFFFF);
        textResult.setTextSize(18);
        rootLayout.addView(textResult);

        setContentView(rootLayout);

        // Logic
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = inputPassword.getText().toString();
                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Лутфан паролро ворид кунед!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Парол қабул шуд: " + pass, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }

    private void calculate(char operator) {
        String s1 = inputNumber1.getText().toString();
        String s2 = inputNumber2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Ҳарду майдон бояд пур карда шаванд!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double n1 = Double.parseDouble(s1);
            double n2 = Double.parseDouble(s2);
            double res = 0;

            switch (operator) {
                case '+':
                    res = n1 + n2;
                    break;
                case '-':
                    res = n1 - n2;
                    break;
                case '*':
                    res = n1 * n2;
                    break;
                case '/':
                    if (n2 == 0) {
                        Toast.makeText(this, "Тақсим кардан ба сифр мумкин нест!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    res = n1 / n2;
                    break;
            }
            textResult.setText("Натиҷа: " + res);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Хатогӣ дар ворид кардани рақамҳо!", Toast.LENGTH_SHORT).show();
        }
    }
}