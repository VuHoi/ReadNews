package com.example.administrator.readnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.readnews.R;

public class Notification extends AppCompatActivity {
    WebView wvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String url =getIntent().getStringExtra("url");
        wvContent =findViewById(R.id.wvcontent);
        wvContent.loadUrl(url);
        wvContent.setWebViewClient(new WebViewClient());
    }
}
