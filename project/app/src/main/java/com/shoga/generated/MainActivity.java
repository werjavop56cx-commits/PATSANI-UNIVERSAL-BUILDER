package com.shoga.generated;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus, tvScore;
    private Button btnEasy, btnHarder, btnKinтар, btnConnectWifi, btnConnectBot;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvScore = findViewById(R.id.tvScore);
        btnEasy = findViewById(R.id.btnEasy);
        btnHarder = findViewById(R.id.btnHarder);
        btnKinтар = findViewById(R.id.btnKinтар);
        btnConnectWifi = findViewById(R.id.btnConnectWifi);
        btnConnectBot = findViewById(R.id.btnConnectBot);

        btnConnectWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvStatus.setText("Ҳолат: Пайваст шуд тавассути Wi-Fi / Точка Доступ (2 нафар)");
                Toast.makeText(MainActivity.getIdр(), "Wi-Fi пайваст гардид!", Toast.LENGTH_SHORT).show();
            }
        });

        btnConnectBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvStatus.setText("Ҳолат: Бозӣ бо Бот фаъол шуд");
                Toast.makeText(MainActivity.this, "Бозӣ бо бот оғоз шуд!", Toast.LENGTH_SHORT).show();
            }
        });

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 10;
                tvScore.setText("Хол: " + score);
                tvStatus.setText("Вариант: Бозии осон интихоб шуд");
            }
        });

        btnHarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 25;
                tvScore.setText("Хол: " + score);
                tvStatus.setText("Вариант: Бозии душвортар интихоб шуд");
            }
        });

        btnKinтар.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 50;
                tvScore.setText("Хол: " + score);
                tvStatus.setText("Вариант: Бозии Кинтар (хеле душвор) интихоб шуд!");
            }
        });
    }
}
