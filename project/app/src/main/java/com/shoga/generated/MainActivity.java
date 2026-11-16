package com.shoga.generated;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore, tvStatus;
    private Button btnAction, btnReset;
    private int score = 0;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tvScore);
        tvStatus = findViewById(R.id.tvStatus);
        btnAction = findViewById(R.id.btnAction);
        btnReset = findViewById(R.id.btnReset);
        random = new Random();

        updateUI();

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
                animateButton(v);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                tvStatus.setText("Бозӣ аз нав оғоз шуд!");
                updateUI();
                animateButton(v);
            }
        });
    }

    private void playGame() {
        int luck = random.nextInt(2);
        if (luck == 1) {
            score += 10;
            tvStatus.setText("Мувафқият! +10 хол");
        } else {
            score = Math.max(0, score - 5);
            tvStatus.setText("Мағлуб шудӣ! -5 хол");
        }
        updateUI();
    }

    private void updateUI() {
        tvScore.setText("Холҳо: " + score);
    }

    private void animateButton(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.1f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.1f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(200);
        set.start();
    }
}
