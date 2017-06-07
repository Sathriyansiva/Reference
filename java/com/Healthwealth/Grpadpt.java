package com.Healthwealth;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mareesoftpc on 5/11/2017.
 */

public class Grpadpt extends BaseAdapter {
    public static final String KEY_PROGNM1 = "proname";

    Context context;
    List<String> Names;
    List<String> Message;
    ArrayList<String> iboarray;
    ArrayList<String> imagearray;

    public Grpadpt(
            Context context2,
            List<String> Names,
            List<String> Message,
            ArrayList<String> imagearray,
            ArrayList<String> iboarray


    ) {

        this.context = context2;
        this.Names = Names;
        this.Message = Message;
        this.imagearray = imagearray;
        this.iboarray = iboarray;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Message.size();
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
            child = layoutInflater.inflate(R.layout.groupadpt, null);

            holder = new Holder();
            holder.textview_name = (TextView) child.findViewById(R.id.tv_name);
            holder.textView_messg = (TextView) child.findViewById(R.id.tv_message);
            holder.iv_post = (ImageView) child.findViewById(R.id.cmntiv);


//            holder.iv_log = (ImageView) child.findViewById(R.id.iv_li);
            child.setTag(holder);

        } else {
            holder = (Holder) child.getTag();
        }

        holder.textview_name.setText(Names.get(position));
        holder.textView_messg.setText(Message.get(position));

        String imagename = imagearray.get(position);
        String ibo = iboarray.get(position);
        if (imagename.equals("")) {
            holder.iv_post.setVisibility(View.GONE);
        } else {
            holder.iv_post.setVisibility(View.VISIBLE);
            final String imageUri = "http://kothuram.com/donatefund/uploads/"+ibo +"/"+imagename;
            Picasso.with(context).load(imageUri).resize(100,100).into(holder.iv_post);
            holder.iv_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.imagepopup);
//                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    ImageView iv_popimgvw = (ImageView) dialog.findViewById(R.id.iv_popimg);
                    TextView tv_clode = (TextView) dialog.findViewById(R.id.tv_close);
                    Picasso.with(context).load(imageUri).resize(400, 400).into(iv_popimgvw);
                    tv_clode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }

//        holder.iv_log.setImageResource(imageId[position]);
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
        TextView textview_name,textView_messg;
        ImageView iv_post;
    }
}