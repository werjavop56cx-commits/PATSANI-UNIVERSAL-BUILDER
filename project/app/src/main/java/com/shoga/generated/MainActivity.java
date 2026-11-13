package com.shoga.generated;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText inputNum1, inputNum2;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        TextView title = new TextView(this);
        title.setText("Shoga Calculator");
        title.setTextSize(24);
        title.setGravity(android.view.Gravity.CENTER);
        layout.addView(title);

        inputNum1 = new EditText(this);
        inputNum1.setHint("Рақами якумро ворид кунед");
        inputNum1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(inputNum1);

        inputNum2 = new EditText(this);
        inputNum2.setHint("Рақами дуюмро ворид кунед");
        inputNum2.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(inputNum2);

        android.widget.LinearLayout btnLayout = new android.widget.LinearLayout(this);
        btnLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL);
        btnLayout.setGravity(android.view.Gravity.CENTER);

        Button btnAdd = new Button(this);
        btnAdd.setText("+");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });
        btnLayout.addView(btnAdd);

        Button btnSub = new Button(this);
        btnSub.setText("-");
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });
        btnLayout.addView(btnSub);

        Button btnMul = new Button(this);
        btnMul.setText("*");
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });
        btnLayout.addView(btnMul);

        Button btnDiv = new Button(this);
        btnDiv.setText("/");
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });
        btnLayout.addView(btnDiv);

        layout.addView(btnLayout);

        textResult = new TextView(this);
        textResult.setText("Натиҷа: ");
        textResult.setTextSize(20);
        textResult.setPadding(0, 32, 0, 0);
        layout.addView(textResult);

        setContentView(layout);
    }

    private void calculate(String operator) {
        String s1 = inputNum1.getText().toString().trim();
        String s2 = inputNum2.getText().toString().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Лутфан ҳарду майдонро пур кунед!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Тақсимкунӣ ба сифр мумкин нест!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            textResult.setText("Натиҷа: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Лутфан рақами дуруст ворид кунед!", Toast.LENGTH_SHORT).show();
        }
    }
}