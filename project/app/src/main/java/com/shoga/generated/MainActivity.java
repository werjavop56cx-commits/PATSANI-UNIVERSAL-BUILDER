package com.shoga.generated;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus, tvComputerCardCount, tvPlayerCards;
    private Button btnTake, btnThrow, btnRestart;
    
    private List<String> deck;
    private List<String> playerCards;
    private List<String> computerCards;
    private String tableCard = "";
    private boolean isPlayerTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvComputerCardCount = findViewById(R.id.tvComputerCardCount);
        tvPlayerCards = findViewById(R.id.tvPlayerCards);
        btnTake = findViewById(R.id.btnTake);
        btnThrow = findViewById(R.id.btnThrow);
        btnRestart = findViewById(R.id.btnRestart);

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTakeCard();
            }
        });

        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerMove();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        startNewGame();
    }

    private void startNewGame() {
        deck = new ArrayList<>();
        String[] suits = {"♠", "♣", "♥", "♦"};
        String[] ranks = {"6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String s : suits) {
            for (String r : ranks) {
                deck.add(r + s);
            }
        }

        Collections.shuffle(deck);

        playerCards = new ArrayList<>();
        computerCards = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            if (!deck.isEmpty()) playerCards.add(deck.remove(0));
            if (!deck.isEmpty()) computerCards.add(deck.remove(0));
        }

        tableCard = "";
        isPlayerTurn = true;
        updateUI();
        Toast.this(this, "Бозии нав оғоз шуд!", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        tvComputerCardCount.setText("Компютер: " + computerCards.size() + " карт");
        StringBuilder sb = new StringBuilder("Картаҳои шумо: ");
        for (String c : playerCards) {
            sb.append(c).append(" ");
        }
        tvPlayerCards.setText(sb.toString());

        if (!tableCard.isEmpty()) {
            tvStatus.setText("Миз: " + tableCard + (isPlayerTurn ? " (Навбати шумо)" : " (Навбати компютер)"));
        } else {
            tvStatus.setText("Миз холӣ аст. Ҳаракат кунед.");
        }
    }

    private void playerMove() {
        if (playerCards.isEmpty()) return;
        String card = playerCards.remove(0);
        tableCard = card;
        isPlayerTurn = false;
        updateUI();
        checkEndGame();

        // Компютер ҷавоб медиҳад
        tvStatus.postDelayed(new Runnable() {
            @Override
            public void run() {
                computerResponse();
            }
        }, 1000);
    }

    private void playerTakeCard() {
        if (!tableCard.isEmpty()) {
            playerCards.add(tableCard);
            tableCard = "";
            Toast.this(this, "Шумо картаро гирифтед!", Toast.LENGTH_SHORT).show();
            updateUI();
        }
    }

    private void computerResponse() {
        if (!computerCards.isEmpty()) {
            String compCard = computerCards.remove(0);
            tableCard = compCard;
            isPlayerTurn = true;
            Toast.this(this, "Компютер карта партофт: " + compCard, Toast.LENGTH_SHORT).show();
            updateUI();
            checkEndGame();
        }
    }

    private void checkEndGame() {
        if (deck.isEmpty() && playerCards.isEmpty()) {
            tvStatus.setText("Шумо бурд кардед!");
        } else if (deck.isEmpty() && computerCards.isEmpty()) {
            tvStatus.setText("Компютер бурд кард!");
        }
    }
}
