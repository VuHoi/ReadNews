package Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

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

        TextView title=row.findViewById(R.id.txttitle);
//       TextView description =row.findViewById((R.id.txtdescription))
        ImageView imglogo=row.findViewById(R.id.imglogo);


        final News news = this.objects.get(position);

//        title.setText(news.get_title().toString());
//        imglogo.setImageResource(newsChoose.get_image());
//        chkstar.setChecked(newsChoose.is_star());
//        rltcontainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newsChoose.set_star(!newsChoose.is_star());
//                chkstar.setChecked(newsChoose.is_star());
//                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
//                animation1.setDuration(1000);
//                v.startAnimation(animation1);
//            }
//        });

        return row;
    }
}
