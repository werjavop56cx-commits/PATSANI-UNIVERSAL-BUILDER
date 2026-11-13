package com.shoga.generated;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private ListView listView;
    private Button btnScan;
    private TextView txtStatus;
    private ArrayList<String> wifiList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanSuccess();
            } else {
                scanFailure();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        listView = findViewById(R.id.listView);
        btnScan = findViewById(R.id.btnScan);
        txtStatus = findViewById(R.id.txtStatus);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wifiList);
        listView.setAdapter(adapter);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionsAndScan();
            }
        });
    }

    private void checkPermissionsAndScan() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
            startWifiScan();
        }
    }

    private void startWifiScan() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            scanFailure();
        } else {
            txtStatus.setText("Сканкунӣ рафта истодааст...");
        }
    }

    private void scanSuccess() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        List<ScanResult> results = wifiManager.getScanResults();
        wifiList.clear();
        for (ScanResult result : results) {
            wifiList.add(result.SSID + "\nBSSID: " + result.BSSID + "\nRSSI: " + result.level);
        }
        adapter.notifyDataSetChanged();
        txtStatus.setText("Шабакаҳои ёфтшуда: " + results.size());
        try {
            unregisterReceiver(wifiScanReceiver);
        } catch (Exception e) {
            // Receiver already unregistered
        }
    }

    private void scanFailure() {
        txtStatus.setText("Хатогӣ ҳангоми сканкунӣ!");
        Toast.makeText(this, "Скан на шуд", Toast.LENGTH_SHORT).show();
        try {
            unregisterReceiver(wifiScanReceiver);
        } catch (Exception e) {
            // Receiver already unregistered
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startWifiScan();
            } else {
                Toast.makeText(this, "Иҷозат рад карда шуд!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
