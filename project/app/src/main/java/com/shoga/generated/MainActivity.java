package com.shoga.generated;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private LinearLayout chatContainer;
    private ScrollView scrollView;
    private EditText inputMessage;
    private Button btnSend, btnSettings, btnClear;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "ShogaPrefs";
    private static final String KEY_API = "apiKey";
    private static final String KEY_HISTORY = "chatHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatContainer = findViewById(R.id.chatContainer);
        scrollView = findViewById(R.id.scrollView);
        inputMessage = findViewById(R.id.inputMessage);
        btnSend = findViewById(R.id.btnSend);
        btnSettings = findViewById(R.id.btnSettings);
        btnClear = findViewById(R.id.btnClear);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        loadChatHistory();

        btnSend.setOnClickListener(v -> {
            String text = inputMessage.getText().toString().trim();
            if (!text.isEmpty()) {
                addMessageToUI("Шумо", text);
                inputMessage.setText("");
                saveMessageToHistory("Шумо", text);
                sendToGemini(text);
            }
        });

        btnSettings.setOnClickListener(v -> showSettingsDialog());

        btnClear.setOnClickListener(v -> {
            chatContainer.removeAllViews();
            sharedPreferences.edit().remove(KEY_HISTORY).apply();
            Toast.makeText(this, "Таърих тоза шуд", Toast.LENGTH_SHORT).show();
        });

        if (getApiKey().isEmpty()) {
            showSettingsDialog();
        }
    }

    private String getApiKey() {
        return sharedPreferences.getString(KEY_API, "");
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Танзимоти API Key");

        final EditText input = new EditText(this);
        input.setHint("Калиди Gemini API-ро ворид кунед");
        input.setText(getApiKey());
        builder.setView(input);

        builder.setPositiveButton("Соф кардан / Сабт", (dialog, which) -> {
            String key = input.getText().toString().trim();
            sharedPreferences.edit().putString(KEY_API, key).apply();
            Toast.makeText(this, "Калид сабт шуд", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Бекор кардан", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void addMessageToUI(String sender, String message) {
        View messageView = LayoutInflater.from(this).inflate(R.layout.item_message, chatContainer, false);
        TextView tvSender = messageView.findViewById(R.id.tvSender);
        TextView tvMessage = messageView.findViewById(R.id.tvMessage);

        tvSender.setText(sender);
        tvMessage.setText(message);

        chatContainer.addView(messageView);
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private void saveMessageToHistory(String sender, String message) {
        try {
            String historyJson = sharedPreferences.getString(KEY_HISTORY, "[]");
            JSONArray history = new JSONArray(historyJson);
            JSONObject obj = new JSONObject();
            obj.put("sender", sender);
            obj.put("message", message);
            history.put(obj);
            sharedPreferences.edit().putString(KEY_HISTORY, history.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        try {
            String historyJson = sharedPreferences.getString(KEY_HISTORY, "[]");
            JSONArray history = new JSONArray(historyJson);
            for (int i = 0; i < history.length(); i++) {
                JSONObject obj = history.getJSONObject(i);
                addMessageToUI(obj.getString("sender"), obj.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendToGemini(String prompt) {
        String apiKey = getApiKey();
        if (apiKey.isEmpty()) {
            addMessageToUI("SHOGA AI", "Хато: Калиди API ворид нашудааст. Танзимотро кушоед.");
            return;
        }

        if (!isNetworkAvailable()) {
            addMessageToUI("SHOGA AI", "Хато: Пайвастшавӣ ба интернет вуҷуд надорад.");
            return;
        }

        new Thread(() -> {
            try {
                URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                JSONObject jsonBody = new JSONObject();
                JSONArray contentsArray = new JSONArray();
                JSONObject contentObj = new JSONObject();
                JSONArray partsArray = new JSONArray();
                JSONObject partObj = new JSONObject();
                partObj.put("text", prompt);
                partsArray.put(partObj);
                contentObj.put("parts", partsArray);
                contentsArray.put(contentObj);
                jsonBody.put("contents", contentsArray);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String reply = jsonResponse
                            .getJSONArray("candidates")
                            .getJSONObject(0)
                            .getJSONObject("content")
                            .getJSONArray("parts")
                            .getJSONObject(0)
                            .getString("text");

                    runOnUiThread(() -> {
                        addMessageToUI("SHOGA AI", reply);
                        saveMessageToHistory("SHOGA AI", reply);
                    });
                } else {
                    runOnUiThread(() -> addMessageToUI("SHOGA AI", "Хатои сервер: " + responseCode));
                }
            } catch (Exception e) {
                runOnUiThread(() -> addMessageToUI("SHOGA AI", "Хато: " + e.getMessage()));
            }
        }).start();
    }
}
