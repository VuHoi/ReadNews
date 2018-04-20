package Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

import model.NewsChoose;

public class TitleNewsAdapter extends ArrayAdapter<NewsChoose> {
    Activity context;
    int resource;
    List<NewsChoose> objects;

    public TitleNewsAdapter(Activity context, int resource, List<NewsChoose> objects) {
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
        final CheckBox chkstar=row.findViewById(R.id.chkstar);
        RelativeLayout rltcontainer=row.findViewById(R.id.rltcontainer);
        ImageView imglogo=row.findViewById(R.id.imglogo);


        final NewsChoose newsChoose = this.objects.get(position);

        title.setText(newsChoose.get_title().toString());
        imglogo.setImageResource(newsChoose.get_image());
        chkstar.setChecked(newsChoose.is_star());
        rltcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsChoose.set_star(!newsChoose.is_star());
                chkstar.setChecked(newsChoose.is_star());
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(1000);
                v.startAnimation(animation1);
            }
        });

        return row;
    }

}
