package com.Healthwealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mareesoftpc on 5/4/2017.
 */

public class Expadpt1 extends BaseAdapter {
    public static final String KEY_PROGNM1 = "proname";

    Context context;
    List<String> listDataHeader;
    Integer[] imageId;
    public Expadpt1(
            Context context2,
            List<String> listDataHeader,
            Integer[] imageId

    ) {

        this.context = context2;
        this.listDataHeader = listDataHeader;
        this.imageId = imageId;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return listDataHeader.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(final int position, View child, ViewGroup parent) {

        final Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.list_expgroup, null);

            holder = new Holder();
            holder.textview_topic = (TextView) child.findViewById(R.id.lblListHeader);
            holder.iv_log = (ImageView) child.findViewById(R.id.iv_li);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textview_topic.setText(listDataHeader.get(position));
        holder.iv_log.setImageResource(imageId[position]);
//        holder.rb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = (new Intent(context, ProgramDetails.class));
//                i.putExtra(KEY_PROGNM1, listDataHeader.get(position));
//                context.startActivity(i);
//            }
//        });
        return child;
    }
    public class Holder {
        TextView textview_topic;
ImageView iv_log;

    }
}