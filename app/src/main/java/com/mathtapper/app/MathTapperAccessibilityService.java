package com.mathtapper.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MathTapperAccessibilityService extends AccessibilityService {

    private static MathTapperAccessibilityService instance;
    private boolean solvingActive = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private OCRProcessor ocrProcessor;
    private MathSolver mathSolver;
    private SharedPreferences prefs;
    private boolean isProcessing = false;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ocrProcessor = new OCRProcessor(this);
        mathSolver = new MathSolver();
        prefs = getSharedPreferences("MathTapperPrefs", MODE_PRIVATE);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!solvingActive || isProcessing) {
            return;
        }

        if (!prefs.getBoolean("regions_set", false)) {
            return;
        }

        // Process with a small delay to ensure content is fully loaded
        handler.postDelayed(this::processMathProblem, 500);
    }

    @Override
    public void onInterrupt() {
        // Service interrupted
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    public static MathTapperAccessibilityService getInstance() {
        return instance;
    }

    public void setSolvingActive(boolean active) {
        this.solvingActive = active;
        if (active) {
            Toast.makeText(this, "Math solving activated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Math solving deactivated", Toast.LENGTH_SHORT).show();
        }
    }

    private void processMathProblem() {
        if (isProcessing) return;
        isProcessing = true;

        try {
            // Take screenshot
            takeScreenshot(Display.DEFAULT_DISPLAY, getMainExecutor(), new TakeScreenshotCallback() {
                @Override
                public void onSuccess(ScreenshotResult screenshot) {
                    Bitmap bitmap = Bitmap.wrapHardwareBuffer(
                            screenshot.getHardwareBuffer(),
                            screenshot.getColorSpace()
                    );

                    if (bitmap != null) {
                        processScreenshot(bitmap);
                        bitmap.recycle();
                    } else {
                        isProcessing = false;
                    }
                }

                @Override
                public void onFailure(int errorCode) {
                    isProcessing = false;
                    Toast.makeText(MathTapperAccessibilityService.this, 
                            "Screenshot failed: " + errorCode, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            isProcessing = false;
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void processScreenshot(Bitmap fullBitmap) {
        try {
            // Extract question region
            Rect questionRect = getRegion("question");
            Bitmap questionBitmap = Bitmap.createBitmap(
                    fullBitmap,
                    questionRect.left,
                    questionRect.top,
                    questionRect.width(),
                    questionRect.height()
            );

            // OCR the question
            ocrProcessor.recognizeText(questionBitmap, questionText -> {
                if (questionText == null || questionText.isEmpty()) {
                    isProcessing = false;
                    return;
                }

                // Solve the math problem
                String result = mathSolver.solve(questionText);
                if (result == null) {
                    isProcessing = false;
                    return;
                }

                // Find the correct answer in choices
                findAndClickCorrectAnswer(fullBitmap, result);
            });

            questionBitmap.recycle();
        } catch (Exception e) {
            isProcessing = false;
            Toast.makeText(this, "Processing error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void findAndClickCorrectAnswer(Bitmap fullBitmap, String correctAnswer) {
        String[] choiceKeys = {"choice1", "choice2", "choice3", "choice4"};
        
        for (int i = 0; i < choiceKeys.length; i++) {
            final int index = i;
            Rect choiceRect = getRegion(choiceKeys[i]);
            
            try {
                Bitmap choiceBitmap = Bitmap.createBitmap(
                        fullBitmap,
                        choiceRect.left,
                        choiceRect.top,
                        choiceRect.width(),
                        choiceRect.height()
                );

                ocrProcessor.recognizeText(choiceBitmap, choiceText -> {
                    if (choiceText != null && mathSolver.compareAnswers(choiceText, correctAnswer)) {
                        // Found the correct answer, click it
                        performClick(choiceRect);
                        isProcessing = false;
                    } else if (index == choiceKeys.length - 1) {
                        // Last choice checked, no match found
                        isProcessing = false;
                    }
                });

                choiceBitmap.recycle();
            } catch (Exception e) {
                if (index == choiceKeys.length - 1) {
                    isProcessing = false;
                }
            }
        }
    }

    private void performClick(Rect rect) {
        int x = rect.centerX();
        int y = rect.centerY();

        Path clickPath = new Path();
        clickPath.moveTo(x, y);

        GestureDescription.StrokeDescription strokeDescription =
                new GestureDescription.StrokeDescription(clickPath, 0, 100);
        
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(strokeDescription);

        dispatchGesture(gestureBuilder.build(), new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Toast.makeText(MathTapperAccessibilityService.this, 
                        "Answer clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Toast.makeText(MathTapperAccessibilityService.this, 
                        "Click cancelled", Toast.LENGTH_SHORT).show();
            }
        }, null);
    }

    private Rect getRegion(String key) {
        int x = prefs.getInt(key + "_x", 0);
        int y = prefs.getInt(key + "_y", 0);
        int w = prefs.getInt(key + "_w", 100);
        int h = prefs.getInt(key + "_h", 100);
        return new Rect(x, y, x + w, y + h);
    }
}
