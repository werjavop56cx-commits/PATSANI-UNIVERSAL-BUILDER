package com.shoga.generated;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput, tvResult;
    private String input = "";
    private String operator = "";
    private double firstValue = Double.NaN;
    private double secondValue = 0;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#,##########");

        tvInput = findViewById(R.id.tvInput);
        tvResult = findViewById(R.id.tvResult);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        };

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            input += button.getText().toString();
            tvInput.setText(input);
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            input = "";
            tvInput.setText("");
            tvResult.setText("0");
            firstValue = Double.NaN;
            operator = "";
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            if (input.length() > 0) {
                input = input.substring(0, input.length() - 1);
                tvInput.setText(input);
            }
        });
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.btnAdd).setOnClickListener(v -> compute("+"));
        findViewById(R.id.btnSub).setOnClickListener(v -> compute("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> compute("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> compute("/"));

        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            executeCompute();
            operator = "";
        });
    }

    private void compute(String op) {
        if (!Double.isNaN(firstValue)) {
            executeCompute();
            operator = op;
            if (!Double.isNaN(firstValue)) {
                tvResult.setText(decimalFormat.format(firstValue) + " " + operator);
                input = "";
            }
        } else {
            if (!input.isEmpty()) {
                firstValue = Double.parseDouble(input);
                operator = op;
                tvResult.setText(decimalFormat.format(firstValue) + " " + operator);
                input = "";
                tvInput.setText("");
            }
        }
    }

    private void executeCompute() {
        if (!Double.isNaN(firstValue)) {
            if (!input.isEmpty()) {
                secondValue = Double.parseDouble(input);
                input = "";
                switch (operator) {
                    case "+":
                        firstValue = firstValue + secondValue;
                        break;
                    case "-":
                        firstValue = firstValue - secondValue;
                        break;
                    case "*":
                        firstValue = firstValue * secondValue;
                        break;
                    case "/":
                        if (secondValue == 0) {
                            firstValue = 0;
                        } else {
                            firstValue = firstValue / secondValue;
                        }
                        break;
                }
                tvResult.setText(decimalFormat.format(firstValue));
                tvInput.setText("");
            }
        } else {
            if (!input.isEmpty()) {
                firstValue = Double.parseDouble(input);
            }
        }
    }
}
