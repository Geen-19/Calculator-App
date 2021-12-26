package com.example.calculator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;
    // variables to hold the operands and type of calculations
    private Double operand1 = null;
    private String pendingOperation = "=";
    private Double operand2 = null;

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERANT1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//"setContentView" is the function to display the layout Activity_main in the app.

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonAdd);
        Button buttonRoot = (Button) findViewById(R.id.buttonRoot);

        View.OnClickListener Clear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand2 = null;
                operand1 = null;
                newNumber.setText("");
                result.setText("");
                displayOperation.setText("");
            }
        };
        buttonClear.setOnClickListener(Clear);

        View.OnClickListener Special = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try{
                    Double doubleValue = Double.valueOf(value);
                    PerformSpecialFun(doubleValue , op);
                    result.setText(operand2.toString());
                    newNumber.setText("");
                }catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        buttonRoot.setOnClickListener(Special);



        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());

            }
        };
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

        View.OnClickListener OperationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try{
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue , op);
                }catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        buttonDivide.setOnClickListener(OperationListener);
        buttonMultiply.setOnClickListener(OperationListener);
        buttonPlus.setOnClickListener(OperationListener);
        buttonMinus.setOnClickListener(OperationListener);
        buttonEquals.setOnClickListener(OperationListener);

        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);

        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if(value.length() == 0){
                    newNumber.setText("-");
                }
                else{
                    try{
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        newNumber.setText(doubleValue.toString());
                    }catch(NumberFormatException e ){
                        newNumber.setText("");
                    }
                }
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION,pendingOperation);
        if(operand1 != null){
            outState.putDouble(STATE_OPERANT1 , operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        displayOperation.setText(pendingOperation);
    }
    private void PerformSpecialFun(Double value , String op ){
       operand2 = Math.sqrt(value);


    }

    private void performOperation(Double  value , String operation){
        if(operand1==null){
            operand1 = value;
        }else{

            switch (pendingOperation){
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if(value==0){
                        operand1 = 0.0;
                    }else{
                        operand1 /=value;
                    }
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "X":
                    operand1 *=value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");

    }

}







