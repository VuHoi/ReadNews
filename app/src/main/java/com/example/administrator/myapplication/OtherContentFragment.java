package com.example.administrator.myapplication;

import android.app.Activity;
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

import Adapter.NewsAdapter;
import model.News;

public class OtherContentFragment extends android.support.v4.app.Fragment {
    NewsAdapter adapter;
    ArrayList<News> news;
    ListView lsvnews;
    String title;
    public OtherContentFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_content, container, false);
        Bundle bundle=getArguments();
        String Url= bundle.getString("url");
        title=bundle.getString("title");

        news=new ArrayList<>();

        lsvnews=view.findViewById(R.id.lsvnews);
        adapter=new NewsAdapter((Activity) getContext(),R.layout.item_news,news);
        lsvnews.setAdapter(adapter);
        final Fragment fragment =new ContentNewFragment();

        lsvnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager=getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("content");
                Bundle bundle =new Bundle();
                bundle.putString("content",news.get(position).getLink());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.flContent, fragment).commit();

            }
        });

        if(title.equals("vnExpress")) {
            readData(Url);
        }
        else if(title.equals("Sơn Hà")){
            readDataDanTri(Url);
        }
        else if(title.equals("Dân trí")){
            readDataDanTri(Url);
        }
        else if(title.equals("Kiến thức")){
            readDataDanTri(Url);
        }
        else if(title.equals("vtc")){
            readDataDanTri(Url);
        }
        else if(title.equals("Thanh niên")){
            readDataDanTri(Url);
        }

        return view;
    }

    private void readData(final String url) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Object dataTransfer[] = new Object[3];
                dataTransfer[0]=url;
                dataTransfer[1]=adapter;
                dataTransfer[2]=news;
                new ReadData().execute(dataTransfer);
            }
        });
    }
    private void readDataDanTri(final String url) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Object dataTransfer[] = new Object[3];
                dataTransfer[0]=url;
                dataTransfer[1]=adapter;
                dataTransfer[2]=news;
                new ReadDataDanTri().execute(dataTransfer);
            }
        });
    }
}
