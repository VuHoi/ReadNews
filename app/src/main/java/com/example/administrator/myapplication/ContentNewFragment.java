package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContentNewFragment extends android.support.v4.app.Fragment {

    public ContentNewFragment(){}

    WebView wvContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_news, container, false);
        Bundle bundle=getArguments();
       String Url= bundle.getString("content");
       wvContent =view.findViewById(R.id.wvcontent);
       wvContent.loadUrl(Url);
       wvContent.setWebViewClient(new WebViewClient());
        return view;
    }
}
