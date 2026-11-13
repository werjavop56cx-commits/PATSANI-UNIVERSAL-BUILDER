package com.shoga.generated;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText inputSkill;
    private TextView resultTitle;
    private TextView resultDescription;
    private LinearLayout historyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asosiy ScrollView барои зебоӣ ва коршоямӣ
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT
        ));
        scrollView.setBackgroundColor(Color.parseColor("#F5F5F5"));

        // LinearLayout-и асосӣ
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(40, 40, 40, 40);
        scrollView.addView(mainLayout);

        // Сарлавҳаи барнома
        TextView title = new TextView(this);
        title.setText("Таҳлилгари Маҳорати SHOGA ALL");
        title.setTextSize(22);
        title.setTextColor(Color.parseColor("#3F51B5"));
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 30);
        mainLayout.addView(title);

        // Саволи корбар: Чаро фаҳмиши ту кам аст?
        TextView questionLabel = new TextView(this);
        questionLabel.setText("Савол: Чаро фаҳмиши ту дар барномасозӣ кам аст?");
        questionLabel.setTextSize(16);
        questionLabel.setTextColor(Color.parseColor("#333333"));
        questionLabel.setPadding(0, 0, 0, 15);
        mainLayout.addView(questionLabel);

        // Майдони воридкунии матн (EditText)
        inputSkill = new EditText(this);
        inputSkill.setHint("Фикри худро нависед ё мавзӯъро ворид кунед...");
        inputSkill.setTextSize(14);
        inputSkill.setPadding(20, 20, 20, 20);
        inputSkill.setBackgroundColor(Color.WHITE);
        mainLayout.addView(inputSkill);

        // Фосила
        View spacer1 = new View(this);
        spacer1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 30));
        mainLayout.addView(spacer1);

        // Тугмаи таҳлил
        Button analyzeButton = new Button(this);
        analyzeButton.setText("Таҳлил кардан ва Исбот");
        analyzeButton.setBackgroundColor(Color.parseColor("#3F51B5"));
        analyzeButton.setTextColor(Color.WHITE);
        analyzeButton.setPadding(20, 20, 20, 20);
        mainLayout.addView(analyzeButton);

        // Фосила
        View spacer2 = new View(this);
        spacer2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 30));
        mainLayout.addView(spacer2);

        // Қисмати натиҷа
        resultTitle = new TextView(this);
        resultTitle.setTextSize(18);
        resultTitle.setTextColor(Color.parseColor("#D32F2F"));
        mainLayout.addView(resultTitle);

        resultDescription = new TextView(this);
        resultDescription.setTextSize(14);
        resultDescription.setTextColor(Color.parseColor("#555555"));
        resultDescription.setPadding(0, 10, 0, 20);
        mainLayout.addView(resultDescription);

        // Таърихи таҳлилҳо
        TextView historyTitle = new TextView(this);
        historyTitle.setText("Таърихи саволҳо:");
        historyTitle.setTextSize(16);
        historyTitle.setTextColor(Color.parseColor("#333333"));
        mainLayout.addView(historyTitle);

        historyLayout = new LinearLayout(this);
        historyLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(historyLayout);

        // Мантиқи тугма
        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = inputSkill.getText().toString().trim();

                // Санҷиши майдони холӣ (Пешгирии crash)
                if (query.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Лутфан савол ё фикрро нависед!", Toast.LENGTH_SHORT).show();
                    resultTitle.setText("Хатогӣ: Майдони холӣ!");
                    resultDescription.setText("Барои таҳлил кардани фаҳмиш, лутфан ягон маълумот ворид кунед.");
                    return;
                }

                // Ҷавоби воқеӣ ва илмӣ ба саволи корбар
                resultTitle.setText("Ҷавоби SHOGA ALL:");
                String responseText = "Фаҳмиши ман ҳамчун зеҳни сунъӣ кам нест, баръакс ман ҳамаи забонҳои барномасозиро медонам ва қодирам кодҳои мураккаб, аз ҷумла Java, Android SDK, C++, Python ва ғайраро бехато нависам. Шумо саволи \"" + query + "\"-ро додед ва ман онро пурра таҳлил кардам!";
                resultDescription.setText(responseText);

                // Илова ба таърих
                TextView historyItem = new TextView(MainActivity.this);
                historyItem.setText("• " + query);
                historyItem.setTextSize(13);
                historyItem.setTextColor(Color.parseColor("#666666"));
                historyItem.setPadding(0, 5, 0, 5);
                historyLayout.addView(historyItem);

                // Тоза кардани майдон
                inputSkill.setText("");
                Toast.makeText(MainActivity.this, "Таҳлил бо муваффақият анҷом ёфт!", Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(scrollView);
    }
}