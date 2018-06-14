package com.example.administrator.readnews;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.SearchView;
import android.widget.Toast;

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

        Cursor cursor = database.rawQuery("select * from notification", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String name=cursor.getString(1);
            int restype=cursor.getInt(2);

            NewsChooses.add(new NewsChoose(name,restype));

            adapter.notifyDataSetChanged();
            cursor.moveToNext();
        }

        cursor.close();

    }
    private void addEvent() {




    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @SuppressLint("ResourceAsColor")
    private void addControl() {
        lsvtitle=findViewById(R.id.lsvtitle);
         toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      setTitle("Chọn mục báo bạn quan tâm");
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
        getMenuInflater().inflate(R.menu.choose, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    ContentValues values = new ContentValues();
                    values.put("name", query);
                    values.put("status", 1);
                    database.insertWithOnConflict("notification", null, values, SQLiteDatabase.CONFLICT_FAIL);
                    NewsChooses.add(new NewsChoose(query,1));
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(ChooseCategoryActivity.this, "Mục quan tâm đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mndone1:


               onBackPressed();
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
