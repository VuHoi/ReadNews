package com.example.administrator.readnews;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.readnews.R;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                // Thông báo sẽ tự động bị hủy khi người dùng click vào Panel



        addControl();
        AddStatus();


    }





    private void   addControl(){

      ImageView imageView=findViewById(R.id.logoMain);
        Animation animalpha= AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        imageView.startAnimation(animalpha);
        setTitle("Đọc báo");

       
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {


                        Intent intent = new Intent(MainActivity.this, NameNews.class);
                        startActivity(intent);

                    }
                }, 3000);

        Intent  mServiceIntent = new Intent(MainActivity.this, RSSPullService.class);
//        mServiceIntent.setData(Uri.parse("https://vnexpress.net/tin-tuc/thoi-su/de-xuat-mo-hinh-bi-thu-kiem-chu-tich-dac-khu-kinh-te-3740710.html"));
        startService(mServiceIntent);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private  void AddStatus()
    { Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
//       getSupportActionBar().hide();
    }

}
