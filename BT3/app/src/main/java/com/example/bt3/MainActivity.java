package com.example.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button selectButton, resetButton, singupButton;
    EditText username, password, retype_pw, bdate;
    RadioButton m, fm;
    CheckBox hob1, hob2, hob3;
    DatePickerDialog datePickerDialog;
    Calendar cal = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectButton = findViewById(R.id.button);
        resetButton = findViewById(R.id.button2);
        singupButton = findViewById((R.id.button3));
        username = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPassword4);
        retype_pw = findViewById(R.id.editTextTextPassword5);
        bdate = findViewById(R.id.editTextDate);
        m = findViewById(R.id.radioButton4);
        fm = findViewById(R.id.radioButton5);
        hob1 = findViewById(R.id.checkBox);
        hob2 = findViewById(R.id.checkBox2);
        hob3 = findViewById(R.id.checkBox3);
        selectButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        singupButton.setOnClickListener(this);
        DatePickerDialog.OnDateSetListener dateSetListener  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String date=String.format("%02d/%02d/%d ", day,month,year);
                bdate.setText(date);
            }
        };
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == selectButton.getId())
            datePickerDialog.show();
        if(v.getId() == resetButton.getId())
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        if(v.getId() == singupButton.getId()) {
            if(password.getText().toString().equals(retype_pw.getText().toString())) {
                if(isValidFormat("dd/MM/yyyy", bdate.getText().toString())&& bdate.getText().toString().length()<=10 ){
                    String mask="";
                    for(int i =0;i<password.getText().toString().length();i++){
                        mask+="*";
                    }
                    Intent intentMainInfo = new Intent(MainActivity.this, ResultForm.class);
                    intentMainInfo.putExtra("user", username.getText().toString());
                    intentMainInfo.putExtra("pw", mask);
                    intentMainInfo.putExtra("bdate", bdate.getText().toString());
                    if(m.isChecked())
                        intentMainInfo.putExtra("gender", "Male");
                    if(fm.isChecked())
                        intentMainInfo.putExtra("gender", "Female");
                    String hobbies = "";
                    if(hob1.isChecked())
                        hobbies += "Tennis";
                    if(hob2.isChecked()){
                        if(hobbies.equals(""))
                            hobbies += "Futbal";
                        else
                            hobbies += ", Futbal";
                    }
                    if(hob3.isChecked()){
                        if(hobbies.equals(""))
                            hobbies += "Others";
                        else
                            hobbies += ", Others";
                    }
                    intentMainInfo.putExtra("hobbies", hobbies);
                    startActivity((intentMainInfo));
                }
                else{
                    Toast.makeText(MainActivity.this, "Wrong input format dd/mm/yyyy in birthdate!",
                            Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Confirm password does not match!",
                        Toast.LENGTH_LONG).show();
            }
        }

    }
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
}