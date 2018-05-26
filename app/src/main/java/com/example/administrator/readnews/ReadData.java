package com.example.administrator.readnews;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Adapter.NewsAdapter;
import model.News;

public class ReadData extends AsyncTask<Object, Integer,String> {

    NewsAdapter newsAdapter;
    ArrayList<News>listNews;


    @Override
    protected String doInBackground(Object... objects) {


        newsAdapter = (NewsAdapter) objects[1];
        listNews= (ArrayList<News>) objects[2];
        return getXmlFromUrl((String) objects[0]);
    }

    @Override
    protected void onPostExecute(String s) {


        XMLDOMParser parser =new XMLDOMParser();
        Document document =parser.getDocument(s);

        NodeList nodeList=document.getElementsByTagName("item");
        NodeList nodeListDescription = document.getElementsByTagName("description");






        listNews.clear();
        for(int i=0;i<nodeList.getLength();i++)
        {
            News news=new News();
            String cData=nodeListDescription.item(i+1).getTextContent();

            Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
            Matcher matcher=p.matcher(cData);
            if(matcher.find())
            {
                news.setImage(matcher.group(1));
                if(news.getImage().indexOf("//")==-1){
                    Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher  matcher1=p1.matcher(cData);
                    if(matcher1.find()) {
                        news.setImage(matcher1.group(1));
                    }
                }

            }
            Element element = (Element) nodeList.item(i);

            news.setTitle(parser.getValue(element,"title"));
            news.setLink(parser.getValue(element,"link"));
try{            news.setDescription( (cData.split("</a></br>"))[1]);}catch (Exception e){}

            listNews.add(news);
           if(newsAdapter!=null) newsAdapter.notifyDataSetChanged();
        }


        super.onPostExecute(s);

    }



    private String getXmlFromUrl(String urlString) {
        String xml = null;    try {

            // defaultHttpClient lấy toàn bộ dữ liệu trong http đổ vào 1 chuỗi String
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlString);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity, HTTP.UTF_8);
            // set UTF-8 cho ra chữ unikey
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();    }
        // return XML
        return xml;
    }
}
