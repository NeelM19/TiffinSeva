package com.example.fooddeliveryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Chefloginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    FirebaseAuth Fauth;
    String number;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefloginphone);

        num = (EditText)findViewById(R.id.number);
        sendotp = (Button)findViewById(R.id.otp);
        signinemail=(Button)findViewById(R.id.Email);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = number;
                Intent b = new Intent(Chefloginphone.this,Chefsendotp.class);

                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chefloginphone.this,ChefRegistration.class));
                finish();
            }
        });
//        signinemail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Chefloginphone.this,Cheflogin.class));
//                finish();
//            }
//        });

    }
}