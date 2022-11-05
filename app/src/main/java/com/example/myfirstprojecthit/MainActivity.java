package com.example.myfirstprojecthit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView result, display;
    Button add, sub, mul, equal;
    private String input, answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textViewResult);
        display = findViewById(R.id.textViewDisplay);
        add = findViewById(R.id.buttonPlus);
        sub = findViewById(R.id.buttonSub);
        mul = findViewById(R.id.buttonX);
        equal = findViewById(R.id.buttonEqual);
    }

    public void onButtonClick(View view) {

        //Pairs numbers side by side in the calculation line.
        if (view instanceof Button) {
            Button button = (Button) view;
            String str = button.getText().toString();
            display.append(str);

            //Call to the function that solves the exercise + chaining the calculation sign
            switch (str) {

                case "x":
                    input += "*";
                    break;

                case "/":
                    input += "/";
                    break;

                case "+":
                    input += "+";
                    break;

                case "-":
                    input += "-";
                    break;

                case "=":
                    Solve();
                    //Answer is the result I present. In the solve function the input value has the result.
                    answer = input;
                    result.setText(answer);
                    break;

                default:
                    if(input==null){
                        input="";
                    }
                    input+=str;
            }
        }
    }

    private void Solve() {

        // The main principle - split the line of calc (AKA "input") until the calculation sign.
        // The split function returns a string array.
        // Do the math (treated as a decimal number).
        // Input = to the result.

        if (input.split("\\*").length == 2) {
            String number[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul + "";

            } catch (Exception e) {
                input = "ERROR";

            }
        }

        else if (input.split("/").length == 2) {
            String number[] = input.split("/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = div + "";
            } catch (Exception e) {
                input = "ERROR";

            }
        }

        else if (input.split("\\+").length == 2) {
            String number[] = input.split("\\+");
            try {
                double add = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = add + "";
            } catch (Exception e) {
                input = "ERROR";

            }
        }
        //Here the length could be bigger than 2 - we may have more than one '-'.
        //For example: (-5-9).
        //The split will work like this: {"", 5,9}.

        else if (input.split("\\-").length > 1) {
            System.out.println(input);
            String number[] = input.split("\\-");
            try {
                double sub = 0;
                if(number.length == 2){
                    sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                }
                //If I substract from negitive number (-5-9).
                else if(number.length == 3){
                    number[0] = 0 + "";
                    sub = -Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                }
                else if(number.length > 3){
                    result.setText("ERROR");
                }
                input = sub + "";
            } catch (Exception e) {
                input = "ERROR";

            }
        }

        //If the result is not decimal (13.0, 4.0, atc..) remove the zero after the point
        String n[] = input.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                input = n[0];
            }
        }
        //If someone doing a mess! (3++3, 4*/+-6, atc..)
        if(n[0].contains("*") || n[0].contains("/") || n[0].contains("+")){
            input="ERROR";
        }
    }


    //Clear all - anytime
    public void OnButtonClearAll(View view){
        display.setText("");
        result.setText("");
        input = "";
        answer= "";
    }

    //Clear only one char
    //Will delete characters only if the equal sign is not pressed yet
    public void OnButtonClearChar(View view) {
        if (!(display.getText().toString().isEmpty()) && (!(display.getText().toString().contains("="))) ) {
            String str = display.getText().toString().substring(0, display.length() - 1);
            display.setText(str);
        }
    }
}