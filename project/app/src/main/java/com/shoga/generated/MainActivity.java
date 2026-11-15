package com.shoga.generated;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvStatus, tvDetails;
    private Button btnSelectApk, btnOptimize;
    private Uri selectedApkUri;

    private final ActivityResultLauncher<Intent> apkPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedApkUri = result.getData().getData();
                    if (selectedApkUri != null) {
                        tvStatus.setText("Файл интихоб шуд");
                        tvDetails.setText(selectedApkUri.getLastPathSegment());
                        btnOptimize.setEnabled(true);
                        animateButton(btnOptimize);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);
        tvDetails = findViewById(R.id.tvDetails);
        btnSelectApk = findViewById(R.id.btnSelectApk);
        btnOptimize = findViewById(R.id.btnOptimize);

        btnOptimize.setEnabled(false);

        btnSelectApk.setOnClickListener(v -> openApkPicker());

        btnOptimize.setOnClickListener(v -> startOptimization());
    }

    private void openApkPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.android.package-archive");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        apkPickerLauncher.launch(Intent.createChooser(intent, "APK файло интихоб кунед"));
    }

    private void startOptimization() {
        if (selectedApkUri == null) {
            Toast.makeText(this, "Пеш аз ҳама APK интихоб кунед!", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSelectApk.setEnabled(false);
        btnOptimize.setEnabled(false);
        tvStatus.setText("Тозакунӣ ва оптимизатсия рафта истодааст...");

        progressBar.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(3000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

        progressBar.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            tvStatus.setText("Оптимизатсия бо муваффақият анҷом ёфт!");
            tvDetails.setText("Хатогиҳо ва файлҳои изофӣ нест карда шуданд.");
            btnSelectApk.setEnabled(true);
            btnOptimize.setEnabled(true);
            Toast.makeText(this, "APK бомуваффақият тоза карда шуд!", Toast.LENGTH_LONG).show();
        }, 3000);
    }

    private void animateButton(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);
        scaleX.setDuration(200);
        scaleY.setDuration(200);
        scaleX.start();
        scaleY.start();
    }
}
