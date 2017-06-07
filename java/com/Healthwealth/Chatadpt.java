package com.Healthwealth;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Mareesoftpc on 5/13/2017.
 */

public class Chatadpt extends BaseAdapter {
    public static final String KEY_USERNAME = null;
    public static final String KEY_Firstname = "name";
//    PhotoViewAttacher mAttacher;
    Context context;

    ArrayList<String> imagearray;
    ArrayList<String> messages;
    ArrayList<String> Senderibo;
    String useribo;

    public Chatadpt(
            Context context2,
            ArrayList<String> imagearray,
            ArrayList<String> messages,
            ArrayList<String> Senderibo,
            String useribo
    ) {

        this.context = context2;


        this.imagearray = imagearray;
        this.messages = messages;
        this.Senderibo = Senderibo;
        this.useribo = useribo;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return imagearray.size();
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
            child = layoutInflater.inflate(R.layout.chatadpt, null);
            holder = new Holder();
            holder.tv_message = (TextView) child.findViewById(R.id.message);
            holder.iv_post = (ImageView) child.findViewById(R.id.cmntiv);
            holder.ll_recevlayout = (LinearLayout) child.findViewById(R.id.recevlayout);
            holder.ll_sendlayout = (LinearLayout) child.findViewById(R.id.sendlayout);
            holder.tv_sendmessage = (TextView) child.findViewById(R.id.sendmessage);
            holder.iv_sendpost = (ImageView) child.findViewById(R.id.sendcmntiv);
            holder.ll_recmsg = (LinearLayout) child.findViewById(R.id.recevlayout_msg);
            holder.ll_sendmsg = (LinearLayout) child.findViewById(R.id.sendlayout_msg);
            holder.ll_recvimg= (LinearLayout) child.findViewById(R.id.linlay);
            holder.ll_senimg = (LinearLayout) child.findViewById(R.id.sendlinlay);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
//        holder.timer = new Timer();
//        holder.timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                ((Activity) context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
        String ibo = Senderibo.get(position);
        if (useribo.equals(ibo)) {
            holder.ll_sendlayout.setVisibility(View.VISIBLE);
            holder.ll_recevlayout.setVisibility(View.GONE);
            holder.tv_sendmessage.setText(messages.get(position));
            String imagename = imagearray.get(position);

            if (messages.get(position).equals("")) {
                holder.ll_sendmsg.setVisibility(View.GONE);

            }
            if (imagename.equals("")) {
                holder.ll_senimg.setVisibility(View.GONE);
            } else {
                holder.iv_sendpost.setVisibility(View.VISIBLE);
                final String imageUri = "http://kothuram.com/donatefund/uploads/" + ibo + "/" + imagename;
                Picasso.with(context).load(imageUri).resize(200, 200).into(holder.iv_sendpost);
//                final Bitmap image=((BitmapDrawable)holder.iv_sendpost.getDrawable()).getBitmap();
                holder.iv_sendpost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.imagepopup);
//                                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        ImageView iv_popimgvw = (ImageView) dialog.findViewById(R.id.iv_popimg);
                        TextView tv_clode = (TextView) dialog.findViewById(R.id.tv_close);
//                        iv_popimgvw.setImageBitmap(image);
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
        } else {
            holder.ll_sendlayout.setVisibility(View.GONE);
            holder.ll_recevlayout.setVisibility(View.VISIBLE);
            holder.tv_message.setText(messages.get(position));
            String imagename = imagearray.get(position);
            final String imageUri;
            if (messages.get(position).equals("")) {
                holder.ll_recmsg.setVisibility(View.GONE);
            }
            if (imagename.equals("")) {
                holder.ll_recvimg.setVisibility(View.GONE);
            } else {
                holder.iv_post.setVisibility(View.VISIBLE);
                imageUri = "http://kothuram.com/donatefund/uploads/" + ibo + "/" + imagename;
                Picasso.with(context).load(imageUri).resize(200, 200).into(holder.iv_post);
//                final Bitmap image=((BitmapDrawable)holder.iv_post.getDrawable()).getBitmap();
                holder.iv_post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.imagepopup);
//                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        ImageView iv_popimgvw = (ImageView) dialog.findViewById(R.id.iv_popimg);
                        TextView tv_clode = (TextView) dialog.findViewById(R.id.tv_close);
//                        iv_popimgvw.setImageBitmap(image);
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
        }
//                    }
//                });
//
//            }
//        }, 0, 10000);
//        new ImageFromserver(holder.iv_post)
//                .execute("http://kothuram.com/donatefund/uploads/"+username +"/20170421_095642.jpg");
        return child;
    }

    public class Holder {
        TextView tv_message, tv_sendmessage;
        ImageView iv_post, iv_sendpost;
        LinearLayout ll_recevlayout, ll_sendlayout,ll_recmsg,ll_sendmsg,ll_senimg,ll_recvimg;
        Timer timer;
    }
}
