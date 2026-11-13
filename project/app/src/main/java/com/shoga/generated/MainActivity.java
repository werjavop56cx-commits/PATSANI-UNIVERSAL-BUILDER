package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText inputNum1, inputNum2;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(32, 32, 32, 32);
        rootLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        rootLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("SHOGA CALCULATOR PRO");
        title.setTextSize(22);
        title.setTextColor(Color.parseColor("#3F51B5"));
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 32);
        rootLayout.addView(title);

        inputNum1 = new EditText(this);
        inputNum1.setHint("Рақами якумро ворид кунед");
        inputNum1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL | android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
        inputNum1.setPadding(16, 16, 16, 16);
        rootLayout.addView(inputNum1);

        inputNum2 = new EditText(this);
        inputNum2.setHint("Рақами дуюмро ворид кунед");
        inputNum2.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL | android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
        inputNum2.setPadding(16, 16, 16, 16);
        rootLayout.addView(inputNum2);

        LinearLayout btnLayout = new LinearLayout(this);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        btnLayout.setGravity(Gravity.CENTER);
        btnLayout.setPadding(0, 24, 0, 24);

        Button btnAdd = createButton("+");
        Button btnSub = createButton("-");
        Button btnMul = createButton("*");
        Button btnDiv = createButton("/");

        btnLayout.addView(btnAdd);
        btnLayout.addView(btnSub);
        btnLayout.addView(btnMul);
        btnLayout.addView(btnDiv);

        rootLayout.addView(btnLayout);

        textResult = new TextView(this);
        textResult.setText("Натиҷа: 0");
        textResult.setTextSize(20);
        textResult.setTextColor(Color.parseColor("#333333"));
        textResult.setGravity(Gravity.CENTER);
        textResult.setPadding(0, 32, 0, 0);
        rootLayout.addView(textResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });

        setContentView(rootLayout);
    }

    private Button createButton(String text) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setTextSize(18);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundColor(Color.parseColor("#3F51B5"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);
        btn.setLayoutParams(params);
        return btn;
    }

    private void calculate(String op) {
        String s1 = inputNum1.getText().toString().trim();
        String s2 = inputNum2.getText().toString().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Лутфан ҳарду майдонро пур кунед!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double n1 = Double.parseDouble(s1);
            double n2 = Double.parseDouble(s2);
            double res = 0;

            switch (op) {
                :
                res = n1 + n2;
                break;
                case "-":
                res = n1 - n2;
                break;
                case "*":
                res = n1 * n2;
                break;
                case "/":
                if (n2 == 0) {
                    Toast.makeText(this, "Хатогӣ: Тақсимкунӣ ба сифр мумкин нест!", Toast.LENGTH_LONG).show();
                    return;
                }
                res = n1 / n2;
                break;
            }

            textResult.setText("Натиҷа: " + res);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Хатогӣ: Рақами нодуруст ворид шудааст!", Toast.LENGTH_LONG).show();
        }
    }
}