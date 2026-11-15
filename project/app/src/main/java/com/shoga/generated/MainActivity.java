package com.shoga.generated;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int timeLeft = 15;
    private boolean isPlaying = false;
    
    private TextView tvScore;
    private TextView tvTimer;
    private Button btnTap;
    private Button btnStart;
    private View rootLayout;
    
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable timerRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        btnTap = findViewById(R.id.btnTap);
        btnStart = findViewById(R.id.btnStart);
        rootLayout = findViewById(R.id.rootLayout);

        btnTap.setEnabled(false);
        btnTap.setAlpha(0.5f);

        btnStart.setOnClickListener(v -> startGame());
        
        btnTap.setOnClickListener(v -> {
            if (isPlaying) {
                score++;
                tvScore.setText("Хол: " + score);
                animateTapButton();
                animateScoreText();
            }
        });
    }

    private void startGame() {
        score = 0;
        timeLeft = 15;
        isPlaying = true;
        tvScore.setText("Хол: 0");
        tvTimer.setText("Вақт: 15с");
        
        btnStart.setEnabled(false);
        btnStart.setAlpha(0.5f);
        btnTap.setEnabled(true);
        btnTap.setAlpha(1.0f);

        timerRunnable = new Runnable() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    tvTimer.setText("Вақт: " + timeLeft + "с");
                    handler.postDelayed(this, 1000);
                } else {
                    endGame();
                }
            }
        };
        handler.postDelayed(timerRunnable, 1000);
    }

    private void endGame() {
        isPlaying = false;
        btnTap.setEnabled(false);
        btnTap.setAlpha(0.5f);
        btnStart.setEnabled(true);
        btnStart.setAlpha(1.0f);
        tvTimer.setText("Бозӣ тамом! Холи шумо: " + score);
    }

    private void animateTapButton() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(btnTap, "scaleX", 1f, 1.15f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btnTap, "scaleY", 1f, 1.15f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(150);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.start();
    }

    private void animateScoreText() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvScore, "scaleX", 1f, 1.3f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvScore, "scaleY", 1f, 1.3f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(100);
        set.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerRunnable != null) {
            handler.removeCallbacks(timerRunnable);
        }
    }
}
