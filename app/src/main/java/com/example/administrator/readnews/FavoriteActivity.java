package com.example.administrator.readnews;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;


public class FavoriteActivity extends AppCompatActivity {


    Toolbar toolbar1;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        toolbar1=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(getResources().getColor(android.R.color.white));
        setTitle("Mục báo ưu thích");
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Fragment fragment=new HomeFragment();
        Bundle  args = new Bundle();
        args.putString("title","favorite");
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent1, fragment).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
