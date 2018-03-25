package project.sayan.hms.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

import project.sayan.hms.Model.HealthNewsModel;
import project.sayan.hms.R;

/**
 * Created by Sayan on 3/25/2018.
 */

public class HealthNewsAdapter extends BaseAdapter {

    HealthNewsModel model;
    Context context;

    HealthNewsAdapter(Context context, HealthNewsModel model)
    {
        this.context=context;
        this.model=model;
    }
    @Override
    public int getCount() {
        return model.articles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.healthnews_listrow,null);
        }

        ConstraintLayout constraintLayout= convertView.findViewById(R.id.listrow_cnstrainLayout);
        ImageView img= convertView.findViewById(R.id.listrow_imageview);
        TextView tvTitle= convertView.findViewById(R.id.listrow_tvtitle);
        TextView tvDesc= convertView.findViewById(R.id.listrow_tvdescription);
        TextView tvDate= convertView.findViewById(R.id.listrow_tvdate);
        TextView tvAuthor= convertView.findViewById(R.id.listrow_tvauthor);

        return convertView;
    }
}
