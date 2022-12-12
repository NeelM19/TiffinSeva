package com.example.fooddeliveryapplication.chefFoodPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryapplication.R;

public class ChefPendingOrderFragment extends Fragment{

    @NonNull
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_pendingorders, null);
        getActivity().setTitle("Pending Orders");
        return v;
    }
}
