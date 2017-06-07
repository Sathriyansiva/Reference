package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mareesoftpc on 5/3/2017.
 */

public class Expadapt extends BaseAdapter {
    public static final String KEY_PROGNM1 = "proname";
    public static final String KEY_PROID = "proid";
    Context context;
    List<String> Category1;
    List<String> Id;
    int position2;

    public Expadapt(
            Context context2,
            List<String> listDataHeader,
            List<String> Id,
            int position2
    ) {

        this.context = context2;
        this.Category1 = listDataHeader;
        this.Id = Id;
        this.position2 = position2;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Category1.size();
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
            child = layoutInflater.inflate(R.layout.list_expitem, null);

            holder = new Holder();
            holder.textview_topic = (TextView) child.findViewById(R.id.lblListItem);
            holder.rb = (RadioButton) child.findViewById(R.id.rbt);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textview_topic.setText(Category1.get(position));
        holder.rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = (new Intent(context, ProgramDetails.class));
//                listDataHeader.get(position);
                i.putExtra(KEY_PROGNM1, Integer.toString(position2+1));
                i.putExtra(KEY_PROID, Integer.toString(position+1));
                context.startActivity(i);
                holder.rb.setChecked(false);
            }
        });

        return child;
    }

    public class Holder {
        TextView textview_topic;
        RadioButton rb;

    }


}