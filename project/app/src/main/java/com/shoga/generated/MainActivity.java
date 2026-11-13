package com.shoga.generated;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView tvDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0.0;
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(0xFF121212);
        rootLayout.setPadding(32, 32, 32, 32);

        tvDisplay = new TextView(this);
        tvDisplay.setText("0");
        tvDisplay.setTextColor(0xFFFFFFFF);
        tvDisplay.setTextSize(48);
        tvDisplay.setGravity(Gravity.END | Gravity.BOTTOM);
        tvDisplay.setPadding(32, 64, 32, 64);
        LinearLayout.LayoutParams displayParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                2.0f
        );
        rootLayout.addView(tvDisplay, displayParams);

        String[][] buttons = {
            {"C", "()", "%", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"+/-", "0", ".", "="}
        };

        for (String[] row : buttons) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1.0f
            );
            rowParams.setMargins(0, 8, 0, 8);

            for (String text : row) {
                Button btn = new Button(this);
                btn.setText(text);
                btn.setTextSize(24);
                btn.setTextColor(0xFFFFFFFF);
                btn.setBackgroundColor(0xFF2D2D2D);
                
                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                );
                btnParams.setMargins(8, 0, 8, 0);
                rowLayout.addView(btn, btnParams);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(text);
                    }
                });
            }
            rootLayout.addView(rowLayout, rowParams);
        }

        setContentView(rootLayout);
    }

    private void onButtonClick(String symbol) {
        switch (symbol) {
            case "C":
                currentInput = "";
                operator = "";
                firstValue = 0.0;
                isNewOp = true;
                tvDisplay.setText("0");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!currentInput.isEmpty()) {
                    firstValue = Double.parseDouble(currentInput);
                    operator = symbol;
                    isNewOp = true;
                }
                break;
            case "=":
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondValue = Double.parseDouble(currentInput);
                    double result = 0.0;
                    switch (operator) {
                        case "+":
                            result = firstValue + secondValue;
                            break;
                        case "-":
                            result = firstValue - secondValue;
                            break;
                        case "*":
                            result = firstValue * secondValue;
                            break;
                        case "/":
                            if (secondValue == 0) {
                                Toast.makeText(this, "Тақсим ба сифр мумкин нест", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            result = firstValue / secondValue;
                            break;
                    }
                    if (result == (long) result) {
                        tvDisplay.setText(String.valueOf((long) result));
                    } else {
                        tvDisplay.setText(String.valueOf(result));
                    }
                    currentInput = String.valueOf(result);
                    operator = "";
                    isNewOp = true;
                }
                break;
            case ".":
                if (isNewOp) {
                    currentInput = "0.";
                    isNewOp = false;
                } else if (!currentInput.contains(".")) {
                    currentInput += ".";
                }
                tvDisplay.setText(currentInput);
                break;
            case "+/-":
                if (!currentInput.isEmpty() && !currentInput.equals("0")) {
                    if (currentInput.startsWith("-")) {
                        currentInput = currentInput.substring(1);
                    } else {
                        currentInput = "-" + currentInput;
                    }
                    tvDisplay.setText(currentInput);
                }
                break;
            case "% Льво":
            case "%":
                if (!currentInput.isEmpty()) {
                    double val = Double.parseDouble(currentInput) / 100.0;
                    currentInput = String.valueOf(val);
                    tvDisplay.setText(currentInput);
                }
                break;
            default:
                if (isNewOp) {
                    currentInput = symbol;
                    isNewOp = false;
                } else {
                    currentInput += symbol;
                }
                tvDisplay.setText(currentInput);
                break;
        }
    }
}
