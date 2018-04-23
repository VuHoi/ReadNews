package com.example.administrator.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import Adapter.MyDatabaseAdapter;
import Adapter.NewsAdapter;
import model.News;

public class HomeFragment extends android.support.v4.app.Fragment{
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;
    ArrayList<News> AllContents,content;
NewsAdapter adapter;
ListView lsvallnews;
    ArrayList<String> urls;
    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        AllContents=new ArrayList();
        content=new ArrayList();
        lsvallnews=view.findViewById(R.id.lsvallnews);
        adapter=new NewsAdapter(getActivity(),R.layout.item_news,AllContents);
        lsvallnews.setAdapter(adapter);
        urls=new ArrayList();
        myDatabase= new MyDatabaseAdapter(getActivity());
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        Cursor cursor = database.rawQuery("select * from Categories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {

            if(cursor.getInt(3)==1) {
                String url = cursor.getString(4);
                urls.add(url);
            }

            cursor.moveToNext();
        }

        cursor.close();
//        url.add("https://vnexpress.net/rss/khoa-hoc.rss");

       for(String url:urls)
       {
           readData( url);
           new android.os.Handler().postDelayed(
                   new Runnable() {
                       public void run() {
                           Collections.shuffle(content);
                          AllContents.addAll(content);
                          adapter.notifyDataSetChanged();
                       }
                   }, 1000);

       }

        final Fragment fragment =new ContentNewFragment();

        lsvallnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager=getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("content");
                Bundle bundle =new Bundle();
                bundle.putString("content",AllContents.get(position).getLink());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.flContent, fragment).commit();

            }
        });
        return view;
    }




    private void readData(final String url) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Object dataTransfer[] = new Object[2];

                dataTransfer[0]=content;
                dataTransfer[1]=url;
                new ReadMultiData().execute(dataTransfer);
            }
        });
    }

}
