package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private TextView newNumber;
    private TextView displayOperation;
    // variables to hold the operands and type of calculations
    private Double operand1 = null;
    private String pendingOperation = "";
    private Double operand2 = null;

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.resultTextView);
        newNumber = (TextView) findViewById(R.id.ongoingCalculusTextView);
        displayOperation = (TextView) findViewById(R.id.operation);
        Button button0 = findViewById(R.id.zeroBtn);
        Button button1 = findViewById(R.id.oneBtn);
        Button button2 = findViewById(R.id.twoBtn);
        Button button3 = findViewById(R.id.threeBtn);
        Button button4 = findViewById(R.id.fourBtn);
        Button button5 = findViewById(R.id.fiveBtn);
        Button button6 = findViewById(R.id.sixBtn);
        Button button7 = findViewById(R.id.sevenBtn);
        Button button8 = findViewById(R.id.eightBtn);
        Button button9 = findViewById(R.id.nineBtn);
        Button buttonDot = findViewById(R.id.dotBtn);
        Button buttonClear = findViewById(R.id.clearBtn);

        Button buttonEquals = findViewById(R.id.equalBtn);
        Button buttonDivide = findViewById(R.id.divideBtn);
        Button buttonMultiply = findViewById(R.id.multiplyBtn);
        Button buttonMinus = findViewById(R.id.minusBtn);
        Button buttonPlus = findViewById(R.id.addBtn);
        Button buttonRoot = findViewById(R.id.rootBtn);
        Button buttonNeg = findViewById(R.id.negBtn);

        buttonClear.setOnClickListener(Clear);

        buttonRoot.setOnClickListener(Special);

        button0.setOnClickListener(Listener);
        button1.setOnClickListener(Listener);
        button2.setOnClickListener(Listener);
        button3.setOnClickListener(Listener);
        button4.setOnClickListener(Listener);
        button5.setOnClickListener(Listener);
        button6.setOnClickListener(Listener);
        button7.setOnClickListener(Listener);
        button8.setOnClickListener(Listener);
        button9.setOnClickListener(Listener);
        buttonDot.setOnClickListener(Listener);

        buttonDivide.setOnClickListener(OperationListener);
        buttonMultiply.setOnClickListener(OperationListener);
        buttonPlus.setOnClickListener(OperationListener);
        buttonMinus.setOnClickListener(OperationListener);
        buttonEquals.setOnClickListener(OperationListener);

        buttonNeg.setOnClickListener(NegBtn);
    }

    View.OnClickListener Special = v -> {
        Button b = (Button) v;
        String op = b.getText().toString();
        String value = newNumber.getText().toString();
        try {
            Double doubleValue = Double.valueOf(value);
            PerformSpecialFun(doubleValue);
            result.setText(String.valueOf(operand2));
            newNumber.setText("");
        } catch (NumberFormatException e) {
            newNumber.setText("");
        }
        pendingOperation = op;
        displayOperation.setText(pendingOperation);
    };

    View.OnClickListener Listener = v -> {
        Button b = (Button) v;
        newNumber.append(b.getText().toString());

    };

    View.OnClickListener OperationListener = v -> {
        Button b = (Button) v;
        String op = b.getText().toString();
        String value = newNumber.getText().toString();
        try {
            Double doubleValue = Double.valueOf(value);
            performOperation(doubleValue);
        } catch (NumberFormatException e) {
            newNumber.setText("");
        }
        pendingOperation = op;
        displayOperation.setText(pendingOperation);
    };

    View.OnClickListener Clear = v -> {
        operand2 = null;
        operand1 = null;
        newNumber.setText("");
        result.setText("");
        displayOperation.setText("");
    };

    View.OnClickListener NegBtn = v -> {
        String value = newNumber.getText().toString();
        if (value.length() == 0) {
            newNumber.setText("-");
        } else {
            try {
                double doubleValue = Double.parseDouble(value);
                doubleValue *= -1;
                newNumber.setText(String.valueOf(doubleValue));
            } catch (NumberFormatException e) {
                newNumber.setText("");
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        displayOperation.setText(pendingOperation);
    }

    private void PerformSpecialFun(Double value) {
        operand2 = Math.sqrt(value);
    }

    private void performOperation(Double value) {
        if (operand1 == null) {
            operand1 = value;
        } else {

            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "X":
                    operand1 *= value;
                    break;
            }
        }
        result.setText(String.valueOf(operand1));
        newNumber.setText("");
    }
}