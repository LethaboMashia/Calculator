package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final CalculatorLogic logic = new CalculatorLogic();

    private TextView tvAnswer;
    private EditText etNumber1;
    private EditText etNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        View root = findViewById(R.id.main);
        // Keep the layout's own padding and add the system bar insets on top of it
        final int padLeft = root.getPaddingLeft();
        final int padTop = root.getPaddingTop();
        final int padRight = root.getPaddingRight();
        final int padBottom = root.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(padLeft + systemBars.left, padTop + systemBars.top,
                    padRight + systemBars.right, padBottom + systemBars.bottom);
            return insets;
        });

        tvAnswer = findViewById(R.id.tvAnswer);
        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnClear = findViewById(R.id.btnClear);

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSubtract.setOnClickListener(v -> calculate('-'));
        btnMultiply.setOnClickListener(v -> calculate('*'));
        btnDivide.setOnClickListener(v -> calculate('/'));
        btnClear.setOnClickListener(v -> clear());
    }

    private void calculate(char operator) {
        String first = etNumber1.getText().toString().trim();
        String second = etNumber2.getText().toString().trim();

        if (first.isEmpty() || second.isEmpty()) {
            tvAnswer.setText(R.string.error_empty);
            return;
        }

        double a;
        double b;
        try {
            a = Double.parseDouble(first);
            b = Double.parseDouble(second);
        } catch (NumberFormatException e) {
            tvAnswer.setText(R.string.error_invalid);
            return;
        }

        try {
            double result;
            switch (operator) {
                case '+':
                    result = logic.add(a, b);
                    break;
                case '-':
                    result = logic.subtract(a, b);
                    break;
                case '*':
                    result = logic.multiply(a, b);
                    break;
                default:
                    result = logic.divide(a, b);
                    break;
            }
            tvAnswer.setText(logic.format(result));
        } catch (ArithmeticException e) {
            tvAnswer.setText(R.string.error_divide_zero);
        }
    }

    private void clear() {
        etNumber1.setText("");
        etNumber2.setText("");
        tvAnswer.setText(R.string.answer_default);
        etNumber1.requestFocus();
    }
}
