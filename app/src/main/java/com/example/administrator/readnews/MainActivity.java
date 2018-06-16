package com.example.administrator.readnews;

import android.app.ActivityManager;
import android.content.Context;
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
    Intent mServiceIntent;
    private RSSPullService mSensorService;
    Context ctx;
    public Context getCtx() {
        return ctx;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        ctx = this;
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

        mSensorService = new RSSPullService(getCtx());
        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());

        if (!isMyServiceRunning(mSensorService.getClass())) {
            startService(mServiceIntent);
        }

    }



    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {

                return true;
            }
        }

        return false;
    }


//    @Override
//    protected void onDestroy() {
//        stopService(mServiceIntent);
//
//        super.onDestroy();
//
//    }

}
