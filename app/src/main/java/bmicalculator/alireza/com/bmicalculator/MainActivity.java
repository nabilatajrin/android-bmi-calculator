package bmicalculator.alireza.com.bmicalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText heightInch;
    private EditText heightCentimeter;
    private EditText weightPound;
    private EditText weightKg;
    private RadioGroup radioGender;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private Button calculate;
    private TextView bmiresult;
    private TextView textresult;
    private TextView descriptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }
    private void initialization(){
        heightInch  = (EditText) findViewById(R.id.editTextHeightInch);
        heightCentimeter  = (EditText) findViewById(R.id.editTextHeightCentimeter);
        weightPound  = (EditText) findViewById(R.id.editTextWeightPound);
        weightKg  = (EditText) findViewById(R.id.editTextWeightKg);
        radioGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
        radioMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
        calculate = (Button) findViewById(R.id.buttonBMI);
        bmiresult = (TextView) findViewById(R.id.textViewResultBMI);
        textresult = (TextView) findViewById(R.id.textViewResult);
        descriptions = (TextView) findViewById(R.id.textViewDefination);

        descriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphView();
            }
        });

        heightInch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && heightInch.getText().length()>0) {
                    double height = Double.parseDouble(heightInch.getText().toString());
                    heightCentimeter.setText(height*2.54 + "");
                    heightInch.setNextFocusDownId(R.id.editTextWeightPound);
                }
            }
        });

        heightCentimeter.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && heightCentimeter.getText().length()>0) {
                    double height = Double.parseDouble(heightCentimeter.getText().toString());
                    heightInch.setText(height*0.393701 + "");
                    heightCentimeter.setNextFocusDownId(R.id.editTextWeightKg);
                }
            }
        });

        weightPound.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && weightPound.getText().length()>0){
                    double weight = Double.parseDouble(weightPound.getText().toString());
                    weightKg.setText(weight*0.45359237 + "");
                    weightPound.setNextFocusDownId(R.id.radioButtonMale);
                }

            }
        });

        weightKg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && weightKg.getText().length()>0){
                    double weight = Double.parseDouble(weightKg.getText().toString());
                    weightPound.setText(weight*2.20462 + "");
                }
            }
        });

        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonMale) {
                    Toast.makeText(MainActivity.this, "You are Male", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radioButtonFemale) {
                    Toast.makeText(MainActivity.this, "You are Female", Toast.LENGTH_SHORT).show();
                }
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Data", "I am in ");
                if(checkfeilds()) calculateBMI();
            }
        });
    }

    private boolean checkfeilds() {
        String inch = heightInch.getText().toString();
        String centimeter = heightCentimeter.getText().toString();
        if(inch.isEmpty() && centimeter.isEmpty()){
            Toast.makeText(MainActivity.this, "Height field is empty", Toast.LENGTH_LONG).show();
        }else if (inch.isEmpty() && !centimeter.isEmpty()){
            Double height = Double.parseDouble(centimeter);
            heightInch.setText(height*0.393701 + "");
        }else if (!inch.isEmpty() && centimeter.isEmpty()){
            Double height = Double.parseDouble(inch);
            heightCentimeter.setText(height*2.54 + "");
        }

        String pound = weightPound.getText().toString();
        String kg = weightKg.getText().toString();
        if(pound.isEmpty() && kg.isEmpty()){
            Toast.makeText(MainActivity.this, "Weight field is empty", Toast.LENGTH_LONG).show();
        }else if (pound.isEmpty() && !kg.isEmpty()){
            Double weight = Double.parseDouble(kg);
            weightPound.setText(weight*2.20462 + "");
        }else if (!pound.isEmpty() && kg.isEmpty()){
            Double weight = Double.parseDouble(pound);
            weightKg.setText(weight*0.45359237 + "");
        }

        String height = heightInch.getText().toString();
        String weight = weightPound.getText().toString();
        if(!height.isEmpty() && !weight.isEmpty()) return true;
        return false;
    }

    private void calculateBMI(){
        /*
        Double height = Double.parseDouble(heightInch.getText().toString());
        Double weight = Double.parseDouble(weightPound.getText().toString());
        Double kg = weight*0.45359237;
        Double meter = height * .025;
        */
        Double height = Double.parseDouble(heightCentimeter.getText().toString())/100;
        Double weight = Double.parseDouble(weightKg.getText().toString());
        Double result = weight  / (height * height );
        bmiresult.setText("Your BMI is : " + result);
        if (result<16) {textresult.setTextColor(Color.RED); textresult.setText("Severe Thinness");}
        else if(result>16 && result<=17) {textresult.setTextColor(Color.MAGENTA); textresult.setText("Moderate Thinness");}
        else if(result>17 && result<=18.5) {textresult.setTextColor(Color.BLUE); textresult.setText("Mild Thinness");}
        else if(result>18.5 && result<=25) {textresult.setTextColor(Color.BLACK); textresult.setText("Normal");}
        else if(result>25 && result<=30) {textresult.setTextColor(Color.MAGENTA); textresult.setText("Overweight");}
        else if(result>30 && result<=35) {textresult.setTextColor(Color.RED); textresult.setText("Obese Class I");}
        else if(result>35 && result<=40) {textresult.setTextColor(Color.RED); textresult.setText("Obese Class II");}
        else if(result>40) {textresult.setTextColor(Color.RED); textresult.setText("Obese Class III");}

    }

    public void graphView(){
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }



}
