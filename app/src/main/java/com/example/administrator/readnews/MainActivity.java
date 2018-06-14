package com.example.administrator.readnews;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.readnews.R;

public class MainActivity extends AppCompatActivity {


    Button btnloaibao,btnuuthich;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnloaibao=findViewById(R.id.btnloaibao);
        btnuuthich=findViewById(R.id.btnuuthich);




        addControl();



    }





    private void   addControl(){

      ImageView imageView=findViewById(R.id.logoMain);
        Animation animalpha= AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        imageView.startAnimation(animalpha);
        setTitle("Đọc báo");
        btnloaibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NameNews.class);
                startActivity(intent);

            }
        });
       
        btnuuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        Intent  mServiceIntent = new Intent(MainActivity.this, RSSPullService.class);
//        mServiceIntent.setData(Uri.parse("https://vnexpress.net/tin-tuc/thoi-su/de-xuat-mo-hinh-bi-thu-kiem-chu-tich-dac-khu-kinh-te-3740710.html"));
        startService(mServiceIntent);

    }




}
