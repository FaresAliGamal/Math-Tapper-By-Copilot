package com.mathtapper.app;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

public class OCRProcessor {

    private TextRecognizer recognizer;
    
    public interface OCRCallback {
        void onTextRecognized(String text);
    }

    public OCRProcessor(Context context) {
        recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    }

    public void recognizeText(Bitmap bitmap, OCRCallback callback) {
        if (bitmap == null) {
            callback.onTextRecognized(null);
            return;
        }

        InputImage image = InputImage.fromBitmap(bitmap, 0);

        recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    String extractedText = extractText(visionText);
                    callback.onTextRecognized(extractedText);
                })
                .addOnFailureListener(e -> {
                    callback.onTextRecognized(null);
                });
    }

    private String extractText(Text visionText) {
        StringBuilder result = new StringBuilder();
        
        for (Text.TextBlock block : visionText.getTextBlocks()) {
            for (Text.Line line : block.getLines()) {
                result.append(line.getText()).append(" ");
            }
        }
        
        return result.toString().trim();
    }

    public void cleanup() {
        if (recognizer != null) {
            recognizer.close();
        }
    }
}
