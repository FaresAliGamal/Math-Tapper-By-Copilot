package com.mathtapper.app;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private View overlayView;
    private SharedPreferences prefs;
    private int currentRegionIndex = 0;
    private String[] regionKeys = {"question", "choice1", "choice2", "choice3", "choice4"};
    private String[] regionNames = {"Question Area", "Choice 1", "Choice 2", "Choice 3", "Choice 4"};
    private ResizableView currentRegionView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && "SELECT_REGIONS".equals(intent.getAction())) {
            showRegionSelector();
        }
        return START_STICKY;
    }

    private void showRegionSelector() {
        prefs = getSharedPreferences("MathTapperPrefs", MODE_PRIVATE);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Create overlay layout
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(Color.argb(100, 0, 0, 0));

        // Create control panel
        LinearLayout controlPanel = new LinearLayout(this);
        controlPanel.setOrientation(LinearLayout.VERTICAL);
        controlPanel.setBackgroundColor(Color.WHITE);
        controlPanel.setPadding(20, 20, 20, 20);

        TextView instructionText = new TextView(this);
        instructionText.setText(regionNames[currentRegionIndex]);
        instructionText.setTextSize(18);
        instructionText.setTextColor(Color.BLACK);

        Button nextButton = new Button(this);
        nextButton.setText("Next Region");
        nextButton.setOnClickListener(v -> nextRegion());

        Button saveButton = new Button(this);
        saveButton.setText("Save & Exit");
        saveButton.setOnClickListener(v -> saveAndExit());

        controlPanel.addView(instructionText);
        controlPanel.addView(nextButton);
        controlPanel.addView(saveButton);

        // Create resizable region view
        currentRegionView = new ResizableView(this);
        
        frameLayout.addView(currentRegionView);
        frameLayout.addView(controlPanel);

        // Setup window parameters
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.LEFT;

        overlayView = frameLayout;
        windowManager.addView(overlayView, params);

        // Load saved region if exists
        loadRegion(regionKeys[currentRegionIndex]);
    }

    private void nextRegion() {
        // Save current region
        saveRegion(regionKeys[currentRegionIndex]);

        currentRegionIndex++;
        if (currentRegionIndex >= regionKeys.length) {
            saveAndExit();
        } else {
            // Update instruction text
            TextView instructionText = overlayView.findViewById(0);
            if (instructionText instanceof TextView) {
                ((TextView) instructionText).setText(regionNames[currentRegionIndex]);
            }
            
            // Load next region
            loadRegion(regionKeys[currentRegionIndex]);
            
            Toast.makeText(this, "Adjust " + regionNames[currentRegionIndex], Toast.LENGTH_SHORT).show();
        }
    }

    private void saveAndExit() {
        // Save final region
        saveRegion(regionKeys[currentRegionIndex]);
        
        // Mark regions as set
        prefs.edit().putBoolean("regions_set", true).apply();
        
        // Remove overlay
        if (overlayView != null && windowManager != null) {
            windowManager.removeView(overlayView);
        }
        
        Toast.makeText(this, "Regions saved successfully!", Toast.LENGTH_SHORT).show();
        stopSelf();
    }

    private void saveRegion(String key) {
        int x = currentRegionView.getRegionX();
        int y = currentRegionView.getRegionY();
        int w = currentRegionView.getRegionWidth();
        int h = currentRegionView.getRegionHeight();

        prefs.edit()
                .putInt(key + "_x", x)
                .putInt(key + "_y", y)
                .putInt(key + "_w", w)
                .putInt(key + "_h", h)
                .apply();
    }

    private void loadRegion(String key) {
        int x = prefs.getInt(key + "_x", 100);
        int y = prefs.getInt(key + "_y", 100);
        int w = prefs.getInt(key + "_w", 300);
        int h = prefs.getInt(key + "_h", 100);

        currentRegionView.setRegion(x, y, w, h);
    }

    // Inner class for resizable region view
    private class ResizableView extends View {
        private int regionX = 100;
        private int regionY = 100;
        private int regionWidth = 300;
        private int regionHeight = 100;
        private boolean isDragging = false;
        private float lastTouchX, lastTouchY;

        public ResizableView(Service context) {
            super(context);
            setBackgroundColor(Color.argb(128, 0, 255, 0));
        }

        public void setRegion(int x, int y, int w, int h) {
            this.regionX = x;
            this.regionY = y;
            this.regionWidth = w;
            this.regionHeight = h;
            updatePosition();
        }

        public int getRegionX() { return regionX; }
        public int getRegionY() { return regionY; }
        public int getRegionWidth() { return regionWidth; }
        public int getRegionHeight() { return regionHeight; }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDragging = true;
                    lastTouchX = event.getRawX();
                    lastTouchY = event.getRawY();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    if (isDragging) {
                        float deltaX = event.getRawX() - lastTouchX;
                        float deltaY = event.getRawY() - lastTouchY;

                        regionX += (int) deltaX;
                        regionY += (int) deltaY;

                        lastTouchX = event.getRawX();
                        lastTouchY = event.getRawY();

                        updatePosition();
                    }
                    return true;

                case MotionEvent.ACTION_UP:
                    isDragging = false;
                    return true;
            }
            return super.onTouchEvent(event);
        }

        private void updatePosition() {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    regionWidth,
                    regionHeight,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
            params.gravity = Gravity.TOP | Gravity.LEFT;
            params.x = regionX;
            params.y = regionY;

            if (getParent() != null) {
                windowManager.updateViewLayout(this, params);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null && windowManager != null) {
            windowManager.removeView(overlayView);
        }
    }
}
