package com.Healthwealth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Mareesoftpc on 4/21/2017.
 */

public class CommentAdapter extends BaseAdapter {
    public static final String KEY_USERNAME = null;
    public static final String KEY_ProId = "proid";
    public static final String KEY_Day = null;
    public static final String KEY_Taskno = "taskno";
    public static final String KEY_Firstname = "name";

    Context context;
    String username;
    ArrayList<String> imagearray;
    ArrayList<String> messages;
    ArrayList<String> namearray;
    String pro_id;
    String Firstname;
    ArrayList<String> iboarray;
    public CommentAdapter(
            Context context2,
            String username,
            ArrayList<String> imagearray,
            ArrayList<String> messages,
            ArrayList<String> namearray,
            String pro_id,
            String Firstname,
            ArrayList<String> iboarray
//            String prod_code[]


    ) {

        this.context = context2;

        this.username = username;
        this.imagearray = imagearray;
        this.messages = messages;
        this.namearray = namearray;
        this.pro_id = pro_id;
        this.Firstname = Firstname;
        this.iboarray = iboarray;

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
            child = layoutInflater.inflate(R.layout.commentadapter, null);

            holder = new Holder();

            holder.tv_name = (TextView) child.findViewById(R.id.cmname);
            holder.tv_message = (TextView) child.findViewById(R.id.message);
            holder.iv_post = (ImageView) child.findViewById(R.id.cmntiv);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }

        holder.tv_name.setText(namearray.get(position));
        holder.tv_message.setText(messages.get(position));


            String imagename = imagearray.get(position);
        String ibo = iboarray.get(position);
            if (imagename.equals("")) {
                holder.iv_post.setVisibility(View.GONE);
            } else {
                holder.iv_post.setVisibility(View.VISIBLE);
                String imageUri = "http://kothuram.com/donatefund/uploads/"+ibo +"/"+imagename;
                Picasso.with(context).load(imageUri).resize(200,200).into(holder.iv_post);
            }

//        new ImageFromserver(holder.iv_post)
//                .execute("http://kothuram.com/donatefund/uploads/"+username +"/20170421_095642.jpg");
        return child;
    }

    private class ImageFromserver extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public ImageFromserver(ImageView imageView) {
            this.imageView = imageView;
//            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    public class Holder {
        TextView tv_name;
        TextView tv_message;
        ImageView iv_post;


    }
}