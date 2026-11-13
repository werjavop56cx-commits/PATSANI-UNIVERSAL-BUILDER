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

    private LinearLayout loginLayout, calcLayout;
    private EditText passwordInput;
    private Button loginButton;
    
    private TextView calcDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asosiy konteyner
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.setBackgroundColor(Color.parseColor("#121212"));
        rootLayout.setPadding(32, 32, 32, 32);

        // --- LOGIN LAYOUT --- 
        loginLayout = new LinearLayout(this);
        loginLayout.setOrientation(LinearLayout.VERTICAL);
        loginLayout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("TJ PATSAN");
        title.setTextColor(Color.parseColor("#00E676"));
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 32);
        loginLayout.addView(title);

        passwordInput = new EditText(this);
        passwordInput.setHint("Паролро ворид кунед (patsan)");
        passwordInput.setHintTextColor(Color.parseColor("#757575"));
        passwordInput.setTextColor(Color.WHITE);
        passwordInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordInput.setGravity(Gravity.CENTER);
        passwordInput.setBackgroundColor(Color.parseColor("#1E1E1E"));
        passwordInput.setPadding(16, 24, 16, 24);
        loginLayout.addView(passwordInput);

        loginButton = new Button(this);
        loginButton.setText("Дромадан");
        loginButton.setBackgroundColor(Color.parseColor("#00E676"));
        loginButton.setTextColor(Color.BLACK);
        loginButton.setPadding(16, 16, 16, 16);
        
        LinearLayout. എന്നാൽparams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 32, 0, 0);
        loginButton.setLayoutParams(params);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = passwordInput.getText().toString();
                if (pass.equals("patsan")) {
                    loginLayout.setVisibility(View.GONE);
                    calcLayout.setVisibility(View.VISIBLE);
                    Toast. хуш омадед = Toast.makeText(MainActivity.this, "Хуш омадед, бародар!", Toast.LENGTH_SHORT);
                    хуш омадед.show();
                } else {
                    Toast.makeText(MainActivity.this, "Парол хато аст! Парол: patsan", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginLayout.addView(loginButton);
        rootLayout.addView(loginLayout);

        // --- CALCULATOR LAYOUT ---
        calcLayout = new LinearLayout(this);
        calcLayout.setOrientation(LinearLayout.VERTICAL);
        calcLayout.setGravity(Gravity.CENTER);
        calcLayout.setVisibility(View.GONE);

        calcDisplay = new TextView(this);
        calcDisplay.setText("0");
        calcDisplay.setTextSize(36);
        calcDisplay.setTextColor(Color.WHITE);
        calcDisplay.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        calcDisplay.setBackgroundColor(Color.parseColor("#1E1E1E"));
        calcDisplay.setPadding(24, 24, 24, 24);
        
        LinearLayout.LayoutParams dispParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            200
        );
        dispParams.setMargins(0, 0, 0, 32);
        calcDisplay.setLayoutParams(dispParams);
        calcLayout.addView(calcDisplay);

        // Tugmalar qatorlari
        String[][] buttons = {
            {"7", "8", "9", "÷"},
            {"4", "5", "6", "×"},
            {"1", "2", "3", "-"},
            {"C", "0", "=", "+"}
        };

        for (String[] row : buttons) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setGravity(Gravity.CENTER);

            for (String text : row) {
                Button btn = new Button(this);
                btn.setText(text);
                btn.setTextSize(22);
                btn.setTextColor(Color.WHITE);
                btn.setBackgroundColor(Color.parseColor("#2D2D2D"));
                
                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(150, 150);
                btnParams.setMargins(8, 8, 8, 8);
                btn.setLayoutParams(btnParams);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCalcButtonClick(text);
                    }
                });

                rowLayout.addView(btn);
            }
            calcLayout.addView(rowLayout);
        }

        rootLayout.addView(calcLayout);
        setContentView(rootLayout);
    }

    private void onCalcButtonClick(String val) {
        if (val.matches("[0-9]")) {
            if (isNewOp) {
                currentInput = val;
                isNewOp = false;
            } else {
                currentInput += val;
            }
            calcDisplay.setText(currentInput);
        } else if (val.equals("C")) {
            currentInput = "0";
            firstValue = 0;
            operator = "";
            isNewOp = true;
            calcDisplay.setText("0");
        } else if (val.equals("=")) {
            if (!operator.isEmpty() && !currentInput.isEmpty()) {
                double secondValue = Double.parseDouble(currentInput);
                double result = 0;
                switch (operator) {
                    case "+": result = firstValue + secondValue; break;
                    case "-": result = firstValue - secondValue; break;
                    case "×": result = firstValue * secondValue; break;
                    case "÷":
                        if (secondValue == 0) {
                            Toast.makeText(this, "Тақсим ба сифр мумкин нест!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        result = firstValue / secondValue;
                        break;
                }
                if (result == (long) result) {
                    calcDisplay.setText(String.valueOf((long) result));
                } else {
                    calcDisplay.setText(String.valueOf(result));
                }
                currentInput = String.valueOf(result);
                operator = "";
                isNewOp = true;
            }
        } else {
            if (!currentInput.isEmpty()) {
                firstValue = Double.parseDouble(currentInput);
                operator = val;
                isNewOp = true;
            }
        }
    }
}