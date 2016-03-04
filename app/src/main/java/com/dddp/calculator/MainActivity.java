package com.dddp.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_dot;
    Button btn_cls;
    Button btn_del;
    Button btn_plus;
    Button btn_minus;
    Button btn_mutiply;
    Button btn_divide;
    Button btn_equal;
    EditText editTextShow;
    boolean clearFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*1# Initialize widgets*/
        btn_0 = (Button) findViewById(R.id.button_0);
        btn_1 = (Button) findViewById(R.id.button_1);
        btn_2 = (Button) findViewById(R.id.button_2);
        btn_3 = (Button) findViewById(R.id.button_3);
        btn_4 = (Button) findViewById(R.id.button_4);
        btn_5 = (Button) findViewById(R.id.button_5);
        btn_6 = (Button) findViewById(R.id.button_6);
        btn_7 = (Button) findViewById(R.id.button_7);
        btn_8 = (Button) findViewById(R.id.button_8);
        btn_9 = (Button) findViewById(R.id.button_9);
        btn_dot = (Button) findViewById(R.id.button_dot);
        btn_cls = (Button) findViewById(R.id.button_c);
        btn_del = (Button) findViewById(R.id.button_del);
        btn_plus    = (Button) findViewById(R.id.button_plus);
        btn_minus   = (Button) findViewById(R.id.button_minus);
        btn_mutiply = (Button) findViewById(R.id.button_multipy);
        btn_divide  = (Button) findViewById(R.id.button_divide);
        btn_equal   = (Button) findViewById(R.id.button_equal);
        editTextShow = (EditText) findViewById(R.id.editText);
        /*2# setOnClickListener*/
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_cls.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mutiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String str = editTextShow.getText().toString();
        /*Start a new calculation if true*/
        if (clearFlag == true) {
            str = "";
            editTextShow.setText(str);
            clearFlag = false;
        }
        switch (v.getId()){
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_dot:
                editTextShow.setText(str + ((Button)v).getText());
                break;
            case R.id.button_plus:
            case R.id.button_minus:
            case R.id.button_multipy:
            case R.id.button_divide:
                editTextShow.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.button_c:
                editTextShow.setText("");
                break;
            case R.id.button_del:
                if(str != null && !str.equals("")){
                    editTextShow.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.button_equal:
                getResult();
                break;
            default:
                break;
        }
    }

    private void getResult(){
        double result = 0.0d;

        String exp = editTextShow.getText().toString();
        if ( exp == null || exp.equals("") ) {  // No input
            return;

        } else if ( ! exp.contains(" ")) {  //No opertaor
            return;

        } else if ( exp.contains(" ")) {
            String left = exp.substring(0, exp.indexOf(" "));
            String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
            String right = exp.substring(exp.indexOf(" ") + 3);
            if (! left.equals("") && ! right.equals("")) {
                double dblLeft = Double.parseDouble(left);
                double dblRight = Double.parseDouble(right);
                if (op.equals("+")){
                    result = dblLeft + dblRight;
                } else if (op.equals("-")) {
                    result = dblLeft - dblRight;
                } else if (op.equals("*")) {
                    result = dblLeft * dblRight;
                } else if (op.equals("/")) {
                    if ( right.equals("0") ){
                        clearFlag = true;
                        editTextShow.setText("" );
                        return;
                    }
                    result = dblLeft / dblRight;
                }
                clearFlag = true;
                String convertResult = (result + "");
                String afterPoint = convertResult.substring(convertResult.indexOf(".") + 1);
                long afterPoingNum = Long.parseLong(afterPoint);
                if ( 0 == afterPoingNum && ! left.contains(".") && ! right.contains(".")){
                    editTextShow.setText(convertResult.substring(0, convertResult.indexOf('.')));
                    return;
                }

            }
        }
        clearFlag = true;
        editTextShow.setText(result + "" );
        return;
    }

}
