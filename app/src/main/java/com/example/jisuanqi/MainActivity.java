package com.example.jisuanqi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

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
    Button btn_clear;
    Button btn_gen;
    Button btn_percent;
    Button btn_divide;
    Button btn_multiply;
    Button btn_minus;
    Button btn_plus;
    Button btn_point;
    Button btn_equal;
    Button btn_del;
    EditText edit_input;
    boolean clear_flag;//清空显示屏内内容的标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = findViewById(R.id.btn_0);//实例化
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_del=findViewById(R.id.btn_del);
        btn_clear = findViewById(R.id.btn_clear);
        btn_gen = findViewById(R.id.btn_gen);
        btn_percent = findViewById(R.id.btn_percent);
        btn_divide = findViewById(R.id.btn_divide);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        btn_point = findViewById(R.id.btn_point);
        btn_equal = findViewById(R.id.btn_equal);
        edit_input = findViewById(R.id.edit_input);
        btn_0.setOnClickListener(this);//设置点击事件
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_gen.setOnClickListener(this);
        btn_percent.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

    }

    public void onClick(View v) {
        String s = edit_input.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (clear_flag) {
                    clear_flag = false;
                    s = "";
                    edit_input.setText("");

                }
                edit_input.setText(s + ((Button) v).getText());//获取Button中text的内容
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:

                if (clear_flag) {
                    clear_flag = false;
                    s = "";
                    edit_input.setText("");
                }
                edit_input.setText(s + " " + ((Button) v).getText() + " ");
                //区别数字按钮，增加了空字符串
                break;
            case R.id.btn_del:
                if (clear_flag) {
                    clear_flag =false ;
                    s ="" ;
                    edit_input.setText("");
                }else if (s!=null&&!s.equals("")){
                    edit_input.setText(s.substring(0,s.length()-1));
                }
                break;
            case R.id.btn_gen:
                double num1=Double.parseDouble(s);
                if(num1<0) {
                    Toast.makeText(MainActivity.this, "负数没有平方根", Toast.LENGTH_SHORT).show();
                }
                else {
                    edit_input.setText(Math.sqrt(num1) + "");
                }
                break;
            case R.id.btn_percent:
                double num2=Double.parseDouble(s);
                double s2=num2/100;
                edit_input.setText(s2+"");
                break;









            case R.id.btn_clear:
                clear_flag = false;
                s = "";
                edit_input.setText("");//直接设置显示屏内容为空
            case R.id.btn_equal:
                getResult();
                break;



        }
    }


        public void getResult () {
            String e = edit_input.getText().toString();//e来读取显示屏中的内容
            if (e == null || e.equals("")) {
                return;//没有运算符，所以不用计算
            }
            if (!e.contains(" ")) {
                return;//没有运算符的特征的字符串，则直接返回值
            }
            if (clear_flag) {
                clear_flag = false;
                return;
            }
            clear_flag = true;
            double result = 0;
            String s1 = e.substring(0, e.indexOf(" "));//运算符前面的字符串
            String op = e.substring(e.indexOf(" ") + 1, e.indexOf(" ") + 2);//获取运算符的内容
            String s2 = e.substring(e.indexOf(" ") + 3);//运算符后面的字符串


            if (!s1.equals(" ") && !s2.equals(" ")) {
                double d1 = Double.parseDouble(s1);
                double d2 = Double.parseDouble(s2);
                if (op.equals("+")) {
                    result = d1 + d2;
                } else if (op.equals("-")) {
                    result = d1 - d2;
                } else if (op.equals("*")) {

                    double f = d1 * d2;
                    BigDecimal bg = new BigDecimal(f);
                    result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                } else if (op.equals("÷")) {
                    if (d2 == 0) {
                        result = 0.0;
                        Toast.makeText(MainActivity.this, "除数不能为0", Toast.LENGTH_SHORT).show();
                    } else {
                        result = d1 / d2;
                    }

                } else if (op.equals("%")) {
                    result = d1 - (d1 / d2) * d2;
                }

                if (s1.contains(".")&&s2.contains(".")) {
                    double r =  result;
                    edit_input.setText(r+"");
                }else {
                    edit_input.setText(result+"");

                }
            }else if (!s1.equals("")&&s2.equals("")){
                edit_input.setText(e);
            }else if (s1.equals("")&&!s2.equals("")){
                double d2 = Double.parseDouble(s2) ;
                if (op.equals("+")){
                    result = 0 + d2 ;

                }else  if (op.equals("-")){
                    result = 0 - d2 ;

                }else  if (op.equals("*")){
                    result = 0 ;

                }else  if (op.equals("/")){
                    result = 0 ;
                }
                if (s2.contains(".")) {
                    double r =  result;
                    edit_input.setText(r+"");
                }else {
                    edit_input.setText(result+"");
                }
            }else {
                edit_input.setText("");

            }
        }
}





















