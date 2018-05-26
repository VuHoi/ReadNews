package com.example.administrator.readnews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.administrator.readnews.R;

import java.util.ArrayList;

import Adapter.MyDatabaseAdapter;
import Adapter.TitleNewsAdapter;
import model.NewsChoose;

public class ChooseCategoryActivity extends AppCompatActivity {
    ListView lsvtitle;
    TitleNewsAdapter adapter;
    ArrayList<NewsChoose> NewsChooses;
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        addControl();
        addEvent();
        test();
        AddStatus();

    }

    private void test() {

        myDatabase= new MyDatabaseAdapter(ChooseCategoryActivity.this);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();

        Cursor cursor = database.rawQuery("select * from Categories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String name=cursor.getString(1);
            int restype=cursor.getInt(3);
            byte[] image = cursor.getBlob(2);
            NewsChooses.add(new NewsChoose(name,image,restype));

            adapter.notifyDataSetChanged();
            cursor.moveToNext();
        }

        cursor.close();

    }
    private void addEvent() {




    }

    @SuppressLint("ResourceAsColor")
    private void addControl() {
        lsvtitle=findViewById(R.id.lsvtitle);
         toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      setTitle("Chọn tên báo bạn quan tâm");
      toolbar.setTitleTextColor(R.color.white);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

//                ContentValues values=new ContentValues();
//                values.put("checkChoice",2);
//                database.updateWithOnConflict("StatusChoose",values,"Id=1",null,SQLiteDatabase.CONFLICT_FAIL);
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
