package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class NameNews extends AppCompatActivity {


    Button btnvnexpress,btnsonha,btnvneconomy,btnvietnamnet,btndantri,btnthanhnien;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_news);
        AddStatus();
        addControl();
        addEvent();
    }


    private void addEvent() {
        btnvnexpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NameNews.this,News_Activity.class);
                intent.putExtra("url","vnExpress");
                startActivity(intent);
            }
        });
        btnsonha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NameNews.this,News_Activity.class);
                intent.putExtra("url","Sơn Hà");
                startActivity(intent);
            }
        });
        btndantri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NameNews.this,News_Activity.class);
                intent.putExtra("url","Dân trí");
                startActivity(intent);
            }
        });
        btnthanhnien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NameNews.this,News_Activity.class);
                intent.putExtra("url","Thanh niên");
                startActivity(intent);
            }
        });
        btnvietnamnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NameNews.this,News_Activity.class);
                intent.putExtra("url","Kiến thức");
                startActivity(intent);
            }
        });
        btnvneconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NameNews.this,News_Activity.class);
                intent.putExtra("url","vtc");
                startActivity(intent);
            }
        });

    }

    private void addControl() {
        btnvnexpress=findViewById(R.id.btnvnexpress);
        btnsonha=findViewById(R.id.btnsonha);
        btndantri=findViewById(R.id.btndantri);
        btnthanhnien=findViewById(R.id.btnthanhnien);
        btnvneconomy=findViewById(R.id.btnvneconomy);
        btnvietnamnet=findViewById(R.id.btnvietnamnet);
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
