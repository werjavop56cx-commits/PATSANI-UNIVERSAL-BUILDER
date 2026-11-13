package com.patsani.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    TextView display;
    String current = "0";
    double first = 0;
    String operator = "";
    boolean newInput = true;
    boolean error = false;

    int bg = Color.rgb(16, 20, 35);
    int panel = Color.rgb(27, 33, 52);
    int blue = Color.rgb(50, 120, 255);
    int white = Color.WHITE;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        buildUI();
    }

    void buildUI() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18, 25, 18, 18);
        root.setBackgroundColor(bg);

        TextView title = new TextView(this);
        title.setText("PATSAN");
        title.setTextSize(27);
        title.setTextColor(blue);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, 65));

        TextView subtitle = new TextView(this);
        subtitle.setText("КАЛЬКУЛЯТОР");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(12);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle);

        display = new TextView(this);
        display.setText("0");
        display.setTextSize(43);
        display.setTextColor(white);
        display.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        display.setPadding(12, 15, 12, 15);
        display.setSingleLine(true);
        root.addView(display, new LinearLayout.LayoutParams(-1, 0, 1.5f));

        String[][] keys = {
            {"C", "DEL", "%", "÷"},
            {"7", "8", "9", "×"},
            {"4", "5", "6", "−"},
            {"1", "2", "3", "+"},
            {"±", "0", ".", "="}
        };

        for (String[] row : keys) {
            LinearLayout line = new LinearLayout(this);
            line.setOrientation(LinearLayout.HORIZONTAL);

            for (String key : row) {
                Button b = new Button(this);
                b.setText(key);
                b.setTextSize(21);
                b.setAllCaps(false);
                b.setTextColor(white);

                int color = panel;
                if (key.equals("=")) color = blue;
                else if ("÷×−+%".contains(key) || key.equals("C") || key.equals("DEL"))
                    color = Color.rgb(45, 65, 105);

                b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
                b.setOnClickListener(v -> press(key));

                LinearLayout.LayoutParams p =
                    new LinearLayout.LayoutParams(0, 0, 1);
                p.height = -1;
                p.weight = 1;
                p.setMargins(5, 5, 5, 5);
                line.addView(b, p);
            }

            root.addView(line, new LinearLayout.LayoutParams(-1, 0, 1));
        }

        setContentView(root);
    }

    void press(String key) {
        if (error && !key.equals("C")) return;

        if (key.matches("[0-9]")) {
            if (newInput || current.equals("0")) current = key;
            else if (current.length() < 14) current += key;
            newInput = false;
        }
        else if (key.equals(".")) {
            if (newInput) {
                current = "0.";
                newInput = false;
            } else if (!current.contains(".")) current += ".";
        }
        else if (key.equals("C")) {
            current = "0";
            first = 0;
            operator = "";
            newInput = true;
            error = false;
        }
        else if (key.equals("DEL")) {
            if (!newInput && current.length() > 1)
                current = current.substring(0, current.length() - 1);
            else current = "0";
        }
        else if (key.equals("±")) {
            if (!current.equals("0"))
                current = current.startsWith("-") ?
                    current.substring(1) : "-" + current;
        }
        else if (key.equals("%")) {
            current = format(Double.parseDouble(current) / 100);
        }
        else if ("÷×−+".contains(key) && key.length() == 1) {
            if (!operator.isEmpty() && !newInput) calculate();
            first = Double.parseDouble(current);
            operator = key;
            newInput = true;
        }
        else if (key.equals("=")) {
            calculate();
            operator = "";
            newInput = true;
        }

        display.setText(current);
    }

    void calculate() {
        if (operator.isEmpty()) return;

        double second = Double.parseDouble(current);
        double result;

        switch (operator) {
            case "+": result = first + second; break;
            case "−": result = first - second; break;
            case "×": result = first * second; break;
            case "÷":
                if (second == 0) {
                    current = "Хато: тақсим ба 0";
                    error = true;
                    display.setText(current);
                    return;
                }
                result = first / second;
                break;
            default: return;
        }

        current = format(result);
        first = result;
        newInput = true;
    }

    String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) return "Хато";
        if (value == 0) return "0";
        DecimalFormat df = new DecimalFormat("0.##########");
        return df.format(value);
    }
}
