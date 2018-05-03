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
        String title=getArguments().getString("title");
        if(title.equals("vnExpress")) {
            Cursor cursor = database.rawQuery("select * from Categories", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                if (cursor.getInt(3) == 1) {
                    String url = cursor.getString(4);
                    urls.add(url);
                }

                cursor.moveToNext();
            }

            cursor.close();
        }else if(title.equals("Sơn Hà"))
        {
            urls.add("http://soha.vn/giai-tri.rss");
            urls.add("http://soha.vn/the-thao.rss");
            urls.add("http://soha.vn/thoi-su.rss");
            urls.add("http://soha.vn/phap-luat.rss");
            urls.add("http://soha.vn/kinh-doanh.rss");
            urls.add("http://soha.vn/quoc-te.rss");
            urls.add("http://soha.vn/song-khoe.rss");
            urls.add("http://soha.vn/quan-su.rss");
            urls.add("http://soha.vn/cu-dan-mang.rss");
            urls.add("http://soha.vn/infographic.rss");
            urls.add("http://soha.vn/kham-pha.rss");
        }else if(title.equals("Dân trí"))
        {
            urls.add("http://dantri.com.vn/suc-khoe.rss");
            urls.add("http://dantri.com.vn/suc-khoe/ung-thu.rss");
            urls.add("http://dantri.com.vn/suc-khoe/lam-dep.rss");
            urls.add("http://dantri.com.vn/suc-khoe/tu-van.rss");
            urls.add("http://dantri.com.vn/suc-khoe/kien-thuc-gioi-tinh.rss");
            urls.add("http://dantri.com.vn/suc-khoe/kí-su-phong-su.rss");
            urls.add("http://dantri.com.vn/suc-khoe/moi-truong.rss");
            urls.add("http://dantri.com.vn/suc-khoe/chinh-tri.rss");
            urls.add("http://dantri.com.vn/suc-khoe/ho-so.rss");
            urls.add("http://dantri.com.vn/suc-khoe/giao-thong.rss");
            urls.add("http://dantri.com.vn/suc-khoe/giai-tri.rss");
            urls.add("http://dantri.com.vn/suc-khoe/thoi-trang.rss");
        }
        else if(title.equals("Thanh niên"))
        {
            urls.add("https://thanhnien.vn/rss/viet-nam.rss");
            urls.add("https://thanhnien.vn/rss/viet-nam/phap-luat.rss");
            urls.add("https://thanhnien.vn/rss/chinh-tri-xa-hoi/phong-su.rss");
            urls.add("https://thanhnien.vn/rss/chinh-tri-xa-hoi/doc-quyen.rss");
            urls.add("https://thanhnien.vn/rss/T%C3%ACnh%20hu%E1%BB%91ng.rss");
            urls.add("https://thanhnien.vn/rss/doi-song/dan-sinh.rss");
            urls.add("https://thanhnien.vn/rss/thoi-su/viec-lam.rss");
            urls.add("https://thanhnien.vn/rss/viec-lam/can-biet.rss");
            urls.add("https://thanhnien.vn/rss/viec-lam/nghe-hot.rss");
            urls.add("https://thanhnien.vn/rss/viec-lam/ky-nang-tim-viec.rss");
            urls.add("https://thanhnien.vn/rss/viec-lam/tuyen-dung.rss");
        }
        else if(title.equals("Kiến thức"))
        {
            urls.add("https://kienthuc.net.vn/rss/xa-hoi-24.rss");
            urls.add("https://kienthuc.net.vn/rss/the-gioi-25.rss");
            urls.add("https://kienthuc.net.vn/rss/cong-dong-tre-27.rss");
            urls.add("https://kienthuc.net.vn/rss/video-214.rss");
            urls.add("https://kienthuc.net.vn/rss/kinh-doanh-9.rss");
            urls.add("https://kienthuc.net.vn/rss/quan-su-26.rss");
            urls.add("https://kienthuc.net.vn/rss/kho-tri-thuc-8.rss");
            urls.add("https://kienthuc.net.vn/rss/kham-pha-13.rss");
            urls.add("https://kienthuc.net.vn/rss/cong-nghe-235.rss");
            urls.add("https://kienthuc.net.vn/rss/lan-banh-217.rss");

        }
        else if(title.equals("vtc"))
        {
            urls.add("http://vtc.vn/xa-hoi.rss");
            urls.add("http://vtc.vn/kinh-te.rss");
            urls.add("http://vtc.vn/truyen-hinh.rss");
            urls.add("http://vtc.vn/phap-luat.rss");
            urls.add("http://vtc.vn/the-gioi.rss");
            urls.add("http://vtc.vn/dia-oc.rss");
            urls.add("http://vtc.vn/giai-tri.rss");
            urls.add("http://vtc.vn/the-thao.rss");
            urls.add("http://vtc.vn/gioi-tre.rss");

        }

//        url.add("https://vnexpress.net/rss/khoa-hoc.rss");

       for(String url:urls)
       {
           readData( url);
           new android.os.Handler().postDelayed(
                   new Runnable() {
                       public void run() {
                           try {
                               Collections.shuffle(content);
                               AllContents.addAll(content);
                               adapter.notifyDataSetChanged();
                           }catch (Exception e ){}
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
