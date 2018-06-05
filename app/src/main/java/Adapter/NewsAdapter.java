package Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.readnews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.News;

public class NewsAdapter extends ArrayAdapter<News> {
    Activity context;
    int resource;
    List<News> objects;

    public NewsAdapter(Activity context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView title=row.findViewById(R.id.txtnewtitle);
       TextView description =row.findViewById((R.id.txtdescription));
        ImageView imglogo=row.findViewById(R.id.imgnews);


        final News news = this.objects.get(position);

        title.setText(news.getTitle().toString());
        description.setText(news.getDescription());
        Picasso.get().load(news.getImage()).placeholder(R.drawable.logo).resize(95,95).into(imglogo);


        return row;
    }
}
