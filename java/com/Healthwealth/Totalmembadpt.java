package com.Healthwealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mareesoftpc on 5/12/2017.
 */

public class Totalmembadpt extends BaseAdapter {
    Context context;
    ArrayList<String> namearray;


    public Totalmembadpt(
            Context context2,
            ArrayList<String> namearray
    ) {

        this.context = context2;
        this.namearray = namearray;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return namearray.size();
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
            child = layoutInflater.inflate(R.layout.toatalmembadapt, null);

            holder =new  Holder();

            holder.tv_name = (TextView) child.findViewById(R.id.cmname);
            holder.tv_message = (TextView) child.findViewById(R.id.message);
            holder.iv_post = (ImageView) child.findViewById(R.id.cmntiv);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }

        holder.tv_name.setText(namearray.get(position));

        return child;
    }
    public class Holder {
        TextView tv_name;
        TextView tv_message;
        ImageView iv_post;


    }
}