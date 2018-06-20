package com.example.administrator.readnews;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.readnews.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapter.MyDatabaseAdapter;
import model.News;
import model.NewsChoose;

public class RSSPullService extends Service {
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;

ArrayList <String> urls;
    News news;

    public RSSPullService(Context applicationContext) {
        super();

    }

    public RSSPullService() {
    }
    public void createNotification(String url,String BigTitle,String content,int index) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(), "notify_001");
        Intent ii = new Intent(getApplicationContext(), Notification.class);
        ii.putExtra("url",url);
        Intent backIntent = new Intent(this, NameNews.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,  new Intent[] {backIntent, ii}, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();

        bigText.setBigContentTitle(BigTitle.length()<100?BigTitle:BigTitle.substring(0,100));
        bigText.setSummaryText(" ");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle(" ");
        mBuilder.setContentText(content);
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);
        mBuilder.setStyle(bigText);
        mBuilder.setGroup("com.read.administrator.readnews");
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notify_001",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }


        mNotificationManager.notify(index, mBuilder.build());

    }
    private void readDataDanTri(final String url) {


                Object dataTransfer[] = new Object[3];
                dataTransfer[0]=news;
                dataTransfer[1]=url;

                new ReadOneData().execute(dataTransfer);

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        myDatabase= new MyDatabaseAdapter(RSSPullService.this);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        urls=new ArrayList();
        Cursor cursor = database.rawQuery("select * from notification", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            String name=cursor.getString(1);
            int restype=cursor.getInt(2);
            if(restype==1) {


                if (name.equals("Khoa học")) {
                    urls.add("http://vietbao.vn/live/Khoa-hoc/rss.xml");
                    urls.add("https://www.tienphong.vn/rss/ho-chi-minh-288.rss");
                    urls.add("https://vnexpress.net/rss/khoa-hoc.rss");
                } else if (name.equals("Giải trí")) {
                    urls.add("https://vnexpress.net/rss/giai-tri.rss");
                    urls.add("http://dantri.com.vn/suc-khoe/giai-tri.rss");
                    urls.add("https://kienthuc.net.vn/rss/quan-su-26.rss");
                    urls.add("http://vtc.vn/giai-tri.rss");
                }
                else if (name.equals("Giáo dục")) {
                    urls.add("https://vnexpress.net/rss/giao-duc.rss");
                    urls.add("https://kienthuc.net.vn/rss/video-214.rss");
                    urls.add("http://vietbao.vn/live/Giao-duc/rss.xml");
                }
                else if (name.equals("Thời sự")) {
                    urls.add("https://vnexpress.net/rss/thoi-su.rss");
                    urls.add("http://soha.vn/thoi-su.rss");
                    urls.add("https://thanhnien.vn/rss/viet-nam.rss");
                    urls.add("https://kienthuc.net.vn/rss/kinh-doanh-9.rss");
                }
                else if (name.equals("Pháp luật")) {
                    urls.add("https://vnexpress.net/rss/phap-luat.rss");
                    urls.add("http://soha.vn/phap-luat.rss");
                    urls.add("https://thanhnien.vn/rss/viet-nam/phap-luat.rss");
                    urls.add("http://vtc.vn/phap-luat.rss");
                }
                else if (name.equals("Sức khỏe")) {
                    urls.add("http://vietbao.vn/live/Suc-khoe/rss.xml");
                    urls.add("https://vnexpress.net/rss/suc-khoe.rss");
                    urls.add("http://dantri.com.vn/suc-khoe.rss");
                    urls.add("");
                }
                else if (name.equals("Gia đình")) {
                    urls.add("https://vnexpress.net/rss/gia-dinh.rss");

                }
                else if (name.equals("Kinh doanh")) {
                    urls.add("http://soha.vn/kinh-doanh.rss");
                    urls.add("https://kienthuc.net.vn/rss/cong-dong-tre-27.rss");
                    urls.add("");
                    urls.add("");
                }
                else if (name.equals("Quân sự")) {
                    urls.add("http://soha.vn/quan-su.rss");
                }
                else if (name.equals("Cư dân mạng")) {
                    urls.add("http://soha.vn/cu-dan-mang.rss");

                }
                else if (name.equals("Khám phá")) {
                    urls.add("http://soha.vn/kham-pha.rss");
                    urls.add("https://kienthuc.net.vn/rss/kham-pha-13.rss");
                    urls.add("http://vietbao.vn/live/Kham-pha-Viet-Nam/rss.xml");
                    urls.add("https://www.tienphong.vn/rss/hoc-duong-ky-tuc-xa-194.rss");
                }
                else if (name.equals("Làm đẹp")) {
                    urls.add("http://dantri.com.vn/suc-khoe/lam-dep.rss");
                    urls.add("https://thanhnien.vn/rss/thoi-su/viec-lam.rss");
                    urls.add("https://www.tienphong.vn/rss/gioi-tre-nhip-song-27.rss");
                    urls.add("https://www.tienphong.vn/rss/thoi-trang-266.rss");
                }
                else if (name.equals("Kiến thức giới tính")) {
                    urls.add("http://dantri.com.vn/suc-khoe/kien-thuc-gioi-tinh.rss");
                    urls.add("http://dantri.com.vn/suc-khoe/tu-van.rss");
                    urls.add("http://vtc.vn/xa-hoi.rss");
                    urls.add("http://vtc.vn/gioi-tre.rss");
                }
                else if (name.equals("Xã hội")) {
                    urls.add("http://dantri.com.vn/suc-khoe/xa-hoi.rss");
                    urls.add("http://vtc.vn/xa-hoi.rss");
                    urls.add("http://dantri.com.vn/suc-khoe/xa-hoi.rss");
                    urls.add("http://vtc.vn/xa-hoi.rss");
                }
                else if (name.equals("Môi trường")) {
                    urls.add("http://dantri.com.vn/suc-khoe/moi-truong.rss");
                    urls.add("https://thanhnien.vn/rss/thoi-su/quoc-phong.rss");
                    urls.add("https://thanhnien.vn/rss/viec-lam/can-biet.rss");
                    urls.add("https://kienthuc.net.vn/rss/lan-banh-217.rss");
                }
                else if (name.equals("Giao thông")) {
                    urls.add("http://dantri.com.vn/suc-khoe/giao-thong.rss");
                    urls.add("https://thanhnien.vn/rss/phap-luat/trong-an.rss");

                }
                else if (name.equals("Thời trang")) {
                    urls.add("https://www.tienphong.vn/rss/thoi-trang-266.rss");
                    urls.add("http://dantri.com.vn/suc-khoe/thoi-trang.rss");
                    urls.add("https://www.tienphong.vn/rss/thoi-trang-266.rss");
                    urls.add("http://dantri.com.vn/suc-khoe/thoi-trang.rss");
                }
                else if (name.equals("Tuyển dụng")) {
                    urls.add("https://thanhnien.vn/rss/viec-lam/tuyen-dung.rss");

                }




            }

            cursor.moveToNext();
        }

        cursor.close();






        news=new News();
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        final String  url = intent.getDataString();

for (int i=1;i<100;i++) {
    final int index = i%urls.size();
    try {
        final int finalI = i;
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        readDataDanTri(urls.get(index));

                     if(!news.getTitle().isEmpty()) {

                         createNotification(news.getLink(), news.getTitle(), news.getDescription().substring(0,news.getDescription().length()>120?120:news.getDescription().length()-1), finalI);
                     }

                    }
                }, 1000*10*50 * finalI);
    }catch(Exception e) {}
}
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
