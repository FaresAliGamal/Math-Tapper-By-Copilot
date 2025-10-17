package com.mathtapper.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathSolver {

    public String solve(String question) {
        if (question == null || question.isEmpty()) {
            return null;
        }

        // Normalize the question text
        String normalized = normalizeQuestion(question);
        
        // Extract the math expression
        String expression = extractExpression(normalized);
        if (expression == null) {
            return null;
        }

        // Evaluate the expression
        return evaluateExpression(expression);
    }

    private String normalizeQuestion(String text) {
        // Remove extra whitespace
        text = text.replaceAll("\\s+", " ").trim();
        
        // Replace common OCR mistakes and variations
        text = text.replace("×", "*");
        text = text.replace("x", "*");
        text = text.replace("X", "*");
        text = text.replace("÷", "/");
        text = text.replace("−", "-");
        text = text.replace("–", "-");
        
        return text;
    }

    private String extractExpression(String text) {
        // Pattern to match mathematical expressions like "2 + 3", "10 - 5", "4 * 6", "8 / 2"
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");
        Matcher matcher = pattern.matcher(text);
        
        if (matcher.find()) {
            return matcher.group(0);
        }
        
        return null;
    }

    private String evaluateExpression(String expression) {
        try {
            // Extract operands and operator
            Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");
            Matcher matcher = pattern.matcher(expression);
            
            if (!matcher.find()) {
                return null;
            }

            int operand1 = Integer.parseInt(matcher.group(1));
            String operator = matcher.group(2);
            int operand2 = Integer.parseInt(matcher.group(3));

            int result;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        return null;
                    }
                    result = operand1 / operand2;
                    break;
                default:
                    return null;
            }

            return String.valueOf(result);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean compareAnswers(String choice, String correctAnswer) {
        if (choice == null || correctAnswer == null) {
            return false;
        }

        // Extract numbers from both strings
        String choiceNum = extractNumber(choice);
        String correctNum = extractNumber(correctAnswer);

        return choiceNum != null && choiceNum.equals(correctNum);
    }

    private String extractNumber(String text) {
        // Extract the first number found in the text
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(text);
        
        if (matcher.find()) {
            return matcher.group(0);
        }
        
        return null;
    }
}
