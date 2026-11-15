package com.shoga.generated;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout menuLayout;
    private LinearLayout gameLayout;
    private TextView tvStatus;
    private GridLayout gridPlayer;
    private GridLayout gridAI;
    private GridLayout gridTable;
    private Button btnAttack;
    private Button btnPass;

    private List<String> deck = new ArrayList<>();
    private List<String> playerCards = new ArrayList<>();
    private List<String> aiCards = new ArrayList<>();
    private List<String> tableCards = new ArrayList<>();
    private boolean isPlayerTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuLayout = findViewById(R.id.menuLayout);
        gameLayout = findViewById(R.id.gameLayout);
        tvStatus = findViewById(R.id.tvStatus);
        gridPlayer = findViewById(R.id.gridPlayer);
        gridAI = findViewById(R.id.gridAI);
        gridTable = findViewById(R.id.gridTable);
        btnAttack = findViewById(R.id.btnAttack);
        btnPass = findViewById(R.id.btnPass);

        Button btnStartGame = findViewById(R.id.btnStartGame);
        Button btnExit = findViewById(R.id.btnExit);

        btnStartGame.setOnClickListener(v -> {
            animateTransition(menuLayout, gameLayout);
            startNewGame();
        });

        btnExit.setOnClickListener(v -> finish());

        btnAttack.setOnClickListener(v -> {
            Toast.Text(this, "Интихоби корт барои ҳамла", Toast.LENGTH_SHORT).show();
        });

        btnPass.setOnClickListener(v -> {
            aiTurn();
        });
    }

    private void animateTransition(View from, View to) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(from, "alpha", 1f, 0f);
        fadeOut.setDuration(300);
        fadeOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                from.setVisibility(View.GONE);
                to.setVisibility(View.VISIBLE);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(to, "alpha", 0f, 1f);
                fadeIn.setDuration(300);
                fadeIn.start();
            }
        });
        fadeOut.start();
    }

    private void startNewGame() {
        deck.clear();
        String[] suits = {"♠", "♣", "♥", "♦"};
        String[] ranks = {"6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String s : suits) {
            for (String r : ranks) {
                deck.add(r + s);
            }
        }
        Collections.shuffle(deck);

        playerCards.clear();
        aiCards.clear();
        tableCards.clear();

        for (int i = 0; i < 6; i++) {
            playerCards.add(deck.remove(0));
            aiCards.add(deck.remove(0));
        }

        updateUI();
        tvStatus.setText("Бозӣ оғоз шуд. Навбати Шумо!");
    }

    private void updateUI() {
        gridPlayer.removeAllViews();
        for (String card : playerCards) {
            Button btn = createCardButton(card);
            btn.setOnClickListener(v -> {
                if (isPlayerTurn) {
                    playerCards.remove(card);
                    tableCards.add(card);
                    updateUI();
                    isPlayerTurn = false;
                    tvStatus.setText("Навбати SHOGA AI...");
                    gridPlayer.postDelayed(this::aiTurn, 1000);
                }
            });
            gridPlayer.addView(btn);
        }

        gridAI.removeAllViews();
        for (String card : aiCards) {
            Button btn = createCardButton("🂠");
            gridAI.addView(btn);
        }

        gridTable.removeAllViews();
        for (String card : tableCards) {
            Button btn = createCardButton(card);
            gridTable.addView(btn);
        }
    }

    private Button createCardButton(String text) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setTextColor(text.contains("♥") || text.contains("♦") ? Color.RED : Color.BLACK);
        btn.setBackgroundResource(R.drawable.card_background);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 140;
        params.height = 200;
        params.setMargins(8, 8, 8, 8);
        btn.setLayoutParams(params);
        return btn;
    }

    private void aiTurn() {
        if (!aiCards.isEmpty()) {
            String aiCard = aiCards.remove(0);
            tableCards.add(aiCard);
            updateUI();
            tvStatus.setText("SHOGA AI ҳамла кард. Навбати Шумо!");
        }
        isPlayerTurn = true;
    }
}
