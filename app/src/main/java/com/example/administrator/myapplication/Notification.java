package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Notification extends AppCompatActivity {
    WebView wvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String url =getIntent().getStringExtra("url");
        wvContent =findViewById(R.id.wvcontent);
        wvContent.loadUrl(url);
        wvContent.setWebViewClient(new WebViewClient());
    }
}
