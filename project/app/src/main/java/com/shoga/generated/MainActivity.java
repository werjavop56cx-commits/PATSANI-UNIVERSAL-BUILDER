package com.shoga.generated;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQ_CODE = 101;
    private static final int PORT = 8888;
    private static final String MULTICAST_IP = "239.255.255.255";

    private boolean isConnected = false;
    private boolean isTransmitting = false;
    private boolean isListening = false;

    private AudioRecord audioRecord;
    private AudioTrack audioTrack;
    private DatagramSocket sendSocket;
    private MulticastSocket receiveSocket;

    private Thread sendThread;
    private Thread receiveThread;

    private Button btnConnect;
    private Button btnTalk;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btnConnect);
        btnTalk = findViewById(R.id.btnTalk);
        tvStatus = findViewById(R.id.tvStatus);

        checkPermissions();

        btnConnect.setOnClickListener(v -> {
            if (!isConnected) {
                startNetworking();
            } else {
                stopNetworking();
            }
        });

        btnTalk.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case android.view.MotionEvent.ACTION_DOWN:
                    if (isConnected) {
                        startTransmitting();
                    }
                    return true;
                case android.view.MotionEvent.ACTION_UP:
                case android.view.MotionEvent.ACTION_CANCEL:
                    if (isConnected) {
                        stopTransmitting();
                    }
                    return true;
            }
            return false;
        });
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_MULTICAST_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_MULTICAST_STATE
            }, PERMISSION_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Иҷозат дода шуд", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Иҷозат рад карда шуд!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startNetworking() {
        try {
            sendSocket = new DatagramSocket();
            receiveSocket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            receiveSocket.joinGroup(group);

            isConnected = true;
            btnConnect.setText("Қатъ кардан");
            btnConnect.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            tvStatus.setText("Ҳолат: Пайваст шуд. Тугмаро пахш карда гап занед.");
            btnTalk.setEnabled(true);

            startListening();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Хатогӣ дар пайвастшавӣ", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopNetworking() {
        isConnected = false;
        stopTransmitting();
        stopListening();

        if (sendSocket != null) {
            sendSocket.close();
            sendSocket = null;
        }
        if (receiveSocket != null) {
            try {
                receiveSocket.leaveGroup(InetAddress.getByName(MULTICAST_IP));
            } catch (Exception ignored) {}
            receiveSocket.close();
            receiveSocket = null;
        }

        btnConnect.setText("Пайваст шудан ба Wi-Fi");
        btnConnect.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        tvStatus.setText("Ҳолат: Қатъ шудааст");
        btnTalk.setEnabled(false);
    }

    private void startTransmitting() {
        if (isTransmitting) return;
        isTransmitting = true;
        btnTalk.setText("ГЕП ЗА ДОРЕД...");
        btnTalk.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

        sendThread = new Thread(() -> {
            int bufferSize = AudioRecord.getMinBufferSize(8000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
            byte[] buffer = new byte[bufferSize];
            audioRecord.startRecording();

            try {
                InetAddress address = InetAddress.getByName(MULTICAST_IP);
                while (isTransmitting && isConnected) {
                    int bytesRead = audioRecord.read(buffer, 0, bufferSize);
                    if (bytesRead > 0) {
                        DatagramPacket packet = new DatagramPacket(buffer, bytesRead, address, PORT);
                        if (sendSocket != null) {
                            sendSocket.send(packet);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (audioRecord != null) {
                    try {
                        audioRecord.stop();
                        audioRecord.release();
                    } catch (Exception ignored) {}
                    audioRecord = null;
                }
            }
        });
        sendThread.start();
    }

    private void stopTransmitting() {
        isTransmitting = false;
        btnTalk.setText("БАРОИ ГАП ЗАНДАН ПАХШ КУНЕД");
        btnTalk.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
    }

    private void startListening() {
        if (isListening) return;
        isListening = true;

        receiveThread = new Thread(() -> {
            int bufferSize = AudioTrack.getMinBufferSize(8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
            audioTrack = new AudioTrack(
                    android.media.AudioManager.STREAM_MUSIC,
                    8000,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize,
                    AudioTrack.MODE_STREAM
            );
            audioTrack.play();

            byte[] buffer = new byte[bufferSize];
            while (isListening && isConnected) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    if (receiveSocket != null) {
                        receiveSocket.receive(packet);
                        audioTrack.write(packet.getData(), 0, packet.getLength());
                    }
                } catch (IOException e) {
                    break;
                }
            }

            if (audioTrack != null) {
                try {
                    audioTrack.stop();
                    audioTrack.release();
                } catch (Exception ignored) {}
                audioTrack = null;
            }
        });
        receiveThread.start();
    }

    private void stopListening() {
        isListening = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopNetworking();
    }
}
