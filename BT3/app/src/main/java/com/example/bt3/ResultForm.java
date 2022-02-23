package com.example.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultForm extends AppCompatActivity implements View.OnClickListener{
    Button exitButton;
    TextView username,password,birthdate,gender,hobbies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_form);

        exitButton = findViewById(R.id.button4);
        exitButton.setOnClickListener(this);
        username=findViewById(R.id.textView11);
        password=findViewById(R.id.textView12);
        birthdate= findViewById(R.id.textView13);
        gender=findViewById(R.id.textView14);
        hobbies =findViewById(R.id.textView15);
        username.setText("Username: "+getIntent().getStringExtra("user"));
        password.setText("Password: "+getIntent().getStringExtra("pw"));
        birthdate.setText("Birthdate: "+getIntent().getStringExtra("bdate"));
        gender.setText("Gender: "+getIntent().getStringExtra("gender"));
        hobbies.setText("Hobbies: "+getIntent().getStringExtra("hobbies"));
    }

    @Override
    public void onClick(View v){
        if(v.getId() == exitButton.getId())
            this.finishAffinity();
    }
}