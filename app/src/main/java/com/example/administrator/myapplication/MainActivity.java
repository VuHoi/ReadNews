package com.example.administrator.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import Adapter.MyDatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        AddStatus();
    }

    private void   addControl(){

      ImageView imageView=findViewById(R.id.logoMain);
        Animation animalpha= AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        imageView.startAnimation(animalpha);
        setTitle("Đọc báo");
        MyDatabaseAdapter myDatabase;
         SQLiteDatabase database;
        myDatabase= new MyDatabaseAdapter(MainActivity.this);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        final Cursor cursor = database.rawQuery("select * from StatusChoose", null);
        cursor.moveToFirst();
//        getSupportActionBar().hide();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(cursor.getInt(1)==1) {
                            Intent intent = new Intent(MainActivity.this, ChooseCategoryActivity.class);
                            startActivity(intent);
                        }
                        else  {
                            Intent intent = new Intent(MainActivity.this, News_Activity.class);
                            startActivity(intent);
                        }
                    }
                }, 3000);
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
