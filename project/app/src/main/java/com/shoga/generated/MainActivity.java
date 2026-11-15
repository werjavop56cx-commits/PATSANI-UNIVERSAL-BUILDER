package com.shoga.generated;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputCode;
    private TextView outputCode;
    private Button btnOptimize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCode = findViewById(R.id.inputCode);
        outputCode = findViewById(R.id.outputCode);
        btnOptimize = findViewById(R.id.btnOptimize);

        btnOptimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in));
                String code = inputCode.getText().toString().trim();
                if (code.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Лутфан рамзро ворид кунед!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Коркарди воқеӣ ва тоза кардани камбудиҳои рамз (Optimization logic)
                String optimized = optimizeCode(code);
                outputCode.setText(optimized);
                Toast.makeText(MainActivity.this, "Рамз бо муваффақият мутобиқ карда шуд!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String optimizeCode(String rawCode) {
        // Тоза кардани фосилаҳои зиёдатӣ ва шарҳҳои нолозим
        String cleaned = rawCode.replaceAll("\\s+", " ");
        cleaned = cleaned.replaceAll("/\\*[\\s\\S]*?\\*/", "");
        cleaned = cleaned.replaceAll("//.*$", "");
        
        StringBuilder sb = new StringBuilder();
        sb.append("// --- SHOGA OPTIMIZED CODE ---\n");
        sb.append(cleaned.trim());
        sb.append("\n// --- STATUS: SUCCESS ---");
        return sb.toString();
    }
}
