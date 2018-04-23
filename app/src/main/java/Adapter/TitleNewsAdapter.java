package Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        MyDatabaseAdapter myDatabase;
        final SQLiteDatabase database;
        myDatabase= new MyDatabaseAdapter(context);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        TextView title=row.findViewById(R.id.txttitle);
        final CheckBox chkstar=row.findViewById(R.id.chkstar);
        RelativeLayout rltcontainer=row.findViewById(R.id.rltcontainer);
        ImageView imglogo=row.findViewById(R.id.imglogo);


        final NewsChoose newsChoose = this.objects.get(position);

        title.setText(newsChoose.get_title().toString());

        byte[] decodedString = Base64.decode(newsChoose.get_image(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        imglogo.setImageBitmap(decodedByte);


        chkstar.setChecked(newsChoose.get_star()==1);
        chkstar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContentValues values=new ContentValues();
                values.put("CheckChoose",isChecked?1:2);
                database.updateWithOnConflict("Categories", values, "Name=?",new String[]{newsChoose.get_title()}, SQLiteDatabase.CONFLICT_FAIL);
            }
        });
        rltcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsChoose.set_star(chkstar.isChecked()?2:1);
                chkstar.setChecked(!chkstar.isChecked());
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(1000);
                v.startAnimation(animation1);

                ContentValues values=new ContentValues();
                values.put("CheckChoose",chkstar.isChecked()?1:2);
                database.updateWithOnConflict("Categories", values, "Name=?",new String[]{newsChoose.get_title()}, SQLiteDatabase.CONFLICT_FAIL);
            }
        });

        return row;
    }

}
