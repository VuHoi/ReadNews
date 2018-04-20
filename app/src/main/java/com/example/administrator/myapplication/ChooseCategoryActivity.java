package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.TitleNewsAdapter;
import model.NewsChoose;

public class ChooseCategoryActivity extends AppCompatActivity {
    ListView lsvtitle;
    TitleNewsAdapter adapter;
    ArrayList<NewsChoose> NewsChooses;
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        addControl();
        addEvent();
        AddStatus();

    }

    private void addEvent() {
        NewsChooses.add(new NewsChoose("Báo thanh niên",true,R.drawable.logo));
        NewsChooses.add(new NewsChoose("Báo thanh niên",true,R.drawable.logo));
        NewsChooses.add(new NewsChoose("Báo thanh niên",true,R.drawable.logo));
        NewsChooses.add(new NewsChoose("Báo thanh niên",true,R.drawable.logo));
        NewsChooses.add(new NewsChoose("Báo thanh niên",true,R.drawable.logo));
        adapter.notifyDataSetChanged();

        lsvtitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ChooseCategoryActivity.this, i+" ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void addControl() {
        lsvtitle=findViewById(R.id.lsvtitle);
         toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      setTitle("Chọn tên báo bạn quan tâm");
//      toolbar.setTitleTextColor(R.color.white);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NewsChooses=new ArrayList<>();
        adapter=new TitleNewsAdapter(ChooseCategoryActivity.this,R.layout.item_choose_news,NewsChooses);
        lsvtitle.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_choose, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mndone:
                Intent intent =new Intent(ChooseCategoryActivity.this,News_Activity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
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
