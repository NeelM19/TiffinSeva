package com.example.fooddeliveryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Loginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    String number;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginphone);

        num = (EditText)findViewById(R.id.number);
        sendotp = (Button)findViewById(R.id.otp);
        signinemail=(Button)findViewById(R.id.btnEmail);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = number;
                Intent b = new Intent(Loginphone.this,sendotp.class);

                b.putExtra("Phonenumber",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginphone.this,Registration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginphone.this,Login.class));
                finish();
            }
        });

    }
}