package com.example.myfirstprojecthit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

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

        if (view instanceof Button) {
            Button button = (Button) view;
            String str = button.getText().toString();
            display.append(str);


            switch (str) {
                case "x":
                    Solve();
                    input += "*";
                    break;

                case "/":
                    Solve();
                    input += "/";
                    break;

                case "+":
                    Solve();
                    input += "+";
                    break;

                case "-":
                    Solve();
                    input += "-";
                    break;

                case "=":
                    Solve();
                    answer = input;
                    result.setText(answer);
                    break;

                default:
                    if(input==null){
                        input="";
                    }
//                    if(str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*")){
//                        Solve();
//                    }
                    input+=str;
            }
        }
    }

    private void Solve() {

            if(input.split("\\*").length==2) {
                String number[] = input.split("\\*");
                try {
                    double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);

                    input = mul+"";

                } catch (Exception e) {

                }
            }

            else if (input.split("/").length==2) {
                String number[] = input.split("/");
                try {
                    double div = Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                    input=div+"";
                }
                catch (Exception e) {

                }
            }

            else if (input.split("\\+").length==2) {
                String number[] = input.split("\\+");
                try {
                    double add = Double.parseDouble(number[0])+Double.parseDouble(number[1]);
                    input=add+"";
                }
                catch (Exception e) {

                }
            }

            else if (input.split("\\-").length==2) {
                String number[] = input.split("\\-");
                if(number[0]=="" && number.length==2){
                    number[0]=0+"'";
                }
                try {
                    double sub = Double.parseDouble(number[0])-Double.parseDouble(number[1]);
                    input=sub+"";
                }
                catch (Exception e) {

                }
            }
            String n[]=input.split("\\.");
                if(n.length>1){
                    if(n[1].equals("0")){
                            input=n[0];
                        }
                    }
    }



    public void OnButtonClearAll(View view){
        display.setText("");
        result.setText("");
        input = "";
        answer= "";
    }

    public void OnButtonClearChar(View view) {
        if (!(display.getText().toString().isEmpty())) {
            String str = display.getText().toString().substring(0, display.length() - 1);
            display.setText(str);
            input = "";
            answer= "";
        }
    }
}