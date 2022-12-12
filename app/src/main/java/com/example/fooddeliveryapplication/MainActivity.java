package com.example.fooddeliveryapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    FirebaseAuth Fauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView7);

        imageView.animate().alpha(0f).setDuration(0);
//        textView.animate().alpha(0f).setDuration(0);

        imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
//                textView.animate().alpha(1f).setDuration(800);

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //after database

//                Fauth = FirebaseAuth.getInstance();
//                if(Fauth.getCurrentUser()!=null){
//                    if(Fauth.getCurrentUser().isEmailVerified()){
//                        Fauth=FirebaseAuth.getInstance();
//
//                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid()+"/Role");
//                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                String role = snapshot.getValue(String.class);
//                                if(role.equals("Chef")){
//                                    startActivity(new Intent(MainActivity.this,ChefFoodPanel_BottomNavigation.class));
//                                    finish();
//
//                                }
//                                if(role.equals("Customer")){
//                                    startActivity(new Intent(MainActivity.this,CustomerFoodPanel_BottomNavigation.class));
//                                    finish();
//                                }
//                                if(role.equals("DeliveryPerson")){
//                                    startActivity(new Intent(MainActivity.this,DeliveryFoodPanel_BottomNavigation.class));
//                                    finish();
//                                }
//                            }
//
//
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
//
//                            }
//                        });
//                    }else{
//                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                        builder.setMessage("Check Whether You Have Verified Your Detail , Otherwise Please Verify");
//                        builder.setCancelable(false);
//                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                Intent intent = new Intent(MainActivity.this,MainMenu.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        });
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//                        Fauth.signOut();
//                    }
//                }else {
//
//                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//            }
//        },3000);

//         before registration

                Intent intent = new Intent(MainActivity.this,MainMenu.class);
                startActivity(intent);
                finish();

            }
        },3000);

    }
}

