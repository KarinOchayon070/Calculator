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
                case ")":
                    input = "Not clickable!";
                    display.setText(input);
                    break;

                case "(":
                    input = "Not clickable!";
                    display.setText(input);
                    break;

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

        if (input.split("\\*").length > 1) {
            String number[] = input.split("\\*");
            try {
                double mul=0;
                for(int i=0; i<number.length-1; i++){
                    mul = Double.parseDouble(number[i]) * Double.parseDouble(number[i+1]);
                    number[i+1] = mul+"";
                }
                input = mul + "";

            } catch (Exception e) {
                input = "ERROR";

            }
        }

        else if (input.split("/").length > 1) {
            String number[] = input.split("/");
            try {
                double div = 0;
                for(int i=0; i<number.length-1; i++){
                    div = Double.parseDouble(number[i]) / Double.parseDouble(number[i+1]);
                    number[i+1] = div+"";
                }
                input = div + "";
            } catch (Exception e) {
                input = "ERROR";

            }
        }

        else if (input.split("\\+").length >1) {
            String number[] = input.split("\\+");
            try {
                double add = 0;
                for(int i=0; i<number.length-1; i++){
                    add = Double.parseDouble(number[i]) + Double.parseDouble(number[i+1]);
                    number[i+1] = add+"";
                }
                input = add + "";
            } catch (Exception e) {
                input = "ERROR";

            }
        }
        //For example: (-5-9).
        //The split will work like this: {"", "5","9"} OR {"null", "5", "9"}.

        else if (input.split("\\-").length > 1) {
            String number[] = input.split("\\-");
            try {
                //If I substract from negitive number (-5-9).
                if(number[0].equals("null") || input.startsWith("-")){
                    double sub =0;
                    number[0] = 0 + "";
                    //Special consideration if it is only two numbers (-5-9)
                    sub = -Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                    number[2] = sub+"";
                    //If more than 2 numbers: for example, -2-9-8
                    for(int i=2; i<number.length-1; i++){
                        sub = Double.parseDouble(number[i]) - Double.parseDouble(number[i+1]);
                        number[i+1] =sub+"";
                    }
                    input = sub + "";
                }
                else{
                    //Subtraction of several numbers where the first one is **not** a negative number
                    double sub=0;
                    for(int i=0; i<number.length-1; i++){
                        sub = Double.parseDouble(number[i]) - Double.parseDouble(number[i+1]);
                        number[i+1] = sub+"";
                    }
                    input = sub + "";
                }
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
        if(n[0].contains("*") || n[0].contains("/") || n[0].contains("+") || n[0].contains("--")){
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
            input = str;
            //Because of the "toString" in line 168, argument like: "6*5" become to "6x5" and we have to chane it in order the "case x" will work
            if(input.contains("x")){
                input = input.replace("x","*");
            }
        }
    }
}