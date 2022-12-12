package com.example.fooddeliveryapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fooddeliveryapplication.customerFoodPanel.CustomerCartFragment;
import com.example.fooddeliveryapplication.customerFoodPanel.CustomerHomeFragment;
import com.example.fooddeliveryapplication.customerFoodPanel.CustomerOrdersFragment;
import com.example.fooddeliveryapplication.customerFoodPanel.CustomerProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class CustomerFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private java.lang.String String;
    boolean name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        String = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        if(name!=null){
//            if (name.equalsIgnoreCase("Homepage")){
//                loadfragment(new CustomerHomeFragment())
//            }else if (name.equalsIgnoreCase("Trackpage")){
//                loadfragment(new CustomerTrackFragment())
//           }else if (name.equalsIgnoreCase("DeliveryOrderpage")){
//                loadfragment(new CustomerTrackFragment())
//          }else if (name.equalsIgnoreCase("ThankYoupage")){
//                loadfragment(new CustomerHomeFragment())
//          }
//        }else {
//        loadfragment(new CustomerHomeFragment)
//    }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.cust_Home:
                fragment=new CustomerHomeFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.cart:
                fragment=new CustomerCartFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.cust_profile:
                fragment=new CustomerProfileFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.cust_order:
                fragment=new CustomerOrdersFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.cart:
                fragment=new CustomerCartFragment();
                break;
        }
        return loadcheffragment(fragment);
        }

    private boolean loadcheffragment(Fragment fragment) {

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }


}