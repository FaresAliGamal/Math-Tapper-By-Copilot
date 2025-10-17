package com.mathtapper.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button enableServiceButton;
    private Button selectRegionsButton;
    private Button startSolvingButton;
    private TextView statusText;
    private TextView regionsInfo;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("MathTapperPrefs", MODE_PRIVATE);

        enableServiceButton = findViewById(R.id.enableServiceButton);
        selectRegionsButton = findViewById(R.id.selectRegionsButton);
        startSolvingButton = findViewById(R.id.startSolvingButton);
        statusText = findViewById(R.id.statusText);
        regionsInfo = findViewById(R.id.regionsInfo);

        enableServiceButton.setOnClickListener(v -> openAccessibilitySettings());
        selectRegionsButton.setOnClickListener(v -> openRegionSelector());
        startSolvingButton.setOnClickListener(v -> toggleSolving());

        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void openAccessibilitySettings() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
        Toast.makeText(this, "Enable Math Tapper Accessibility Service", Toast.LENGTH_LONG).show();
    }

    private void openRegionSelector() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            Toast.makeText(this, "Grant overlay permission to select regions", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, OverlayService.class);
        intent.setAction("SELECT_REGIONS");
        startService(intent);
    }

    private void toggleSolving() {
        boolean isActive = prefs.getBoolean("solving_active", false);
        prefs.edit().putBoolean("solving_active", !isActive).apply();
        
        if (MathTapperAccessibilityService.getInstance() != null) {
            MathTapperAccessibilityService.getInstance().setSolvingActive(!isActive);
        }
        
        updateUI();
    }

    private void updateUI() {
        boolean serviceEnabled = isAccessibilityServiceEnabled();
        boolean regionsSet = prefs.getBoolean("regions_set", false);
        boolean solvingActive = prefs.getBoolean("solving_active", false);

        statusText.setText(serviceEnabled ? R.string.service_enabled : R.string.service_disabled);
        selectRegionsButton.setEnabled(serviceEnabled);
        startSolvingButton.setEnabled(serviceEnabled && regionsSet);
        startSolvingButton.setText(solvingActive ? R.string.stop_solving : R.string.start_solving);

        if (regionsSet) {
            StringBuilder info = new StringBuilder();
            info.append("Question: ").append(getRegionString("question")).append("\n");
            info.append("Choice 1: ").append(getRegionString("choice1")).append("\n");
            info.append("Choice 2: ").append(getRegionString("choice2")).append("\n");
            info.append("Choice 3: ").append(getRegionString("choice3")).append("\n");
            info.append("Choice 4: ").append(getRegionString("choice4"));
            regionsInfo.setText(info.toString());
        } else {
            regionsInfo.setText("No regions selected");
        }
    }

    private String getRegionString(String key) {
        int x = prefs.getInt(key + "_x", 0);
        int y = prefs.getInt(key + "_y", 0);
        int w = prefs.getInt(key + "_w", 0);
        int h = prefs.getInt(key + "_h", 0);
        return String.format("(%d, %d, %dx%d)", x, y, w, h);
    }

    private boolean isAccessibilityServiceEnabled() {
        AccessibilityManager am = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        if (am == null) return false;

        String packageName = getPackageName();
        String serviceName = packageName + "/" + MathTapperAccessibilityService.class.getName();

        return am.getEnabledAccessibilityServiceList(-1).stream()
                .anyMatch(info -> serviceName.equals(info.getId()));
    }
}
