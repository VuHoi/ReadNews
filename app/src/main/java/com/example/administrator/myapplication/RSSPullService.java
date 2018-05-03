package com.example.administrator.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import model.News;

public class RSSPullService extends Service {


//    public RSSPullService(String name) {
//        super(name);
//
//    }

//    public RSSPullService() {
//        super(" ");
//
//
//    }

//    @Override
//    protected void onHandleIntent(Intent workIntent) {
//        // Gets data from the incoming Intent
//       String  url = workIntent.getDataString();
//
//        // Do work here, based on the contents of dataString
//
//                        createNotification(url);
//
//
//
//
//    }

ArrayList <String> urls;
    News news;
    public void createNotification(String url,String BigTitle,String content,int index) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(), "notify_001");
        Intent ii = new Intent(getApplicationContext(), Notification.class);
        ii.putExtra("url",url);

        ii.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, ii, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();

        bigText.setBigContentTitle(BigTitle);
        bigText.setSummaryText(" ");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle(" ");
        mBuilder.setContentText(content);
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);
        mBuilder.setStyle(bigText);

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
        urls=new ArrayList();
        urls.add("https://vnexpress.net/rss/tin-moi-nhat.rss");
        urls.add("http://soha.vn/giai-tri.rss");
        urls.add("http://dantri.com.vn/trangchu.rss");
        urls.add("https://vtc.vn/feed.rss");
        urls.add("https://thanhnien.vn/rss/home.rss");
        urls.add("https://kienthuc.net.vn/rss/home.rss");
        news=new News();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        final String  url = intent.getDataString();

for (int i=0;i<100;i++)
{
    Random random=new Random();
    final int index=random.nextInt(5);
    readDataDanTri(urls.get(index));


    final int finalI = i;
    new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

createNotification(news.getLink(),news.getTitle(),news.getDescription(), finalI);


                    }
                }, 3000);
}
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d("destroy", "jajajaj");
        super.onDestroy();
    }
}
