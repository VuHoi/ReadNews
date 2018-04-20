package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Const_App;

public class HomeFragment extends android.support.v4.app.Fragment{
    public HomeFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        TextView txttest=view.findViewById(R.id.txttest);
        txttest.setText(getArguments().getInt("someInt", Const_App.All)+" ");
        Log.d("hoi", Const_App.All+"");
        return view;
    }
}
