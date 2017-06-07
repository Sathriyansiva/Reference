package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoadapter extends BaseAdapter {
    public static final String url = null;
    public static final String KEY_USERNAME = null;
    public static final String KEY_ProId = "proid";
    public static final String KEY_Day = null;
    public static final String KEY_Taskno = "taskno";
    public static final String KEY_Firstname = "name";

    public static final String KEY_PROID = null;
    public static final String KEY_STATUS = null;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private TextView[] btns;
    String stat;
    String th;
    Context context;
    String username;
    ArrayList<Integer> days1;
    ArrayList<String> topics1;
    ArrayList<String> tasknames1;
    ArrayList<String> tasknos1;
    ArrayList<String> prod_names;
    String pro_id;
    String Firstname;

    int imgResource;
    //    String prod_code[];
//    Activity activity;
//
//    public ToDoadapter(Activity activity) {
//        this.activity = activity;
//    }

    public ToDoadapter(
            Context context2,
            String username,
            ArrayList<Integer> days1,
            ArrayList<String> topics1,
            ArrayList<String> tasknames1,
            ArrayList<String> tasknos1,
            ArrayList<String> prod_names,
            String pro_id,
            String Firstname
//            String prod_code[]


    ) {

        this.context = context2;

        this.username = username;

        this.days1 = days1;
        this.topics1 = topics1;
        this.tasknames1 = tasknames1;
        this.tasknos1 = tasknos1;
        this.tasknos1 = tasknos1;
        this.prod_names = prod_names;
        this.pro_id = pro_id;
        this.Firstname = Firstname;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return days1.size();
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
            child = layoutInflater.inflate(R.layout.todoadapter, null);

            holder = new Holder();
            holder.textview_topic = (TextView) child.findViewById(R.id.topic);
            holder.textview_taskname = (TextView) child.findViewById(R.id.taskname);
            holder.textview_taskno = (TextView) child.findViewById(R.id.taskno);
            holder.textview_discrip = (TextView) child.findViewById(R.id.discription);
            holder.bt_like = (Button) child.findViewById(R.id.like);
            holder.bt_comment = (Button) child.findViewById(R.id.comment);
            holder.textview_comments = (EditText) child.findViewById(R.id.comments);
//            holder.lin_bk = (LinearLayout) child.findViewById(R.id.back);
//            holder.lin_go = (LinearLayout) child.findViewById(R.id.click);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textview_topic.setText(prod_names.get(position));
        holder.textview_taskname.setText(tasknames1.get(position));
        holder.textview_taskno.setText("Task" + tasknos1.get(position));
        holder.textview_discrip.setText(topics1.get(position));
        final Integer days = days1.get(position);
        final String day1=String.valueOf(days);
        holder.bt_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgResource = R.drawable.like;
                holder.bt_like.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
            }
        });
        holder.bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Comment.class);
                i.putExtra(KEY_ProId, pro_id);
                i.putExtra(KEY_Day, day1);
                i.putExtra(KEY_Taskno,tasknos1.get(position));
                i.putExtra(KEY_Firstname, Firstname);
                context.startActivity(i);
            }
        });


//        th = holder.po.getText().toString().trim();
//        stat = satus1.get(position);
        return child;
    }

    public class Holder {
        TextView textview_topic;
        TextView textview_taskname;
        TextView textview_taskno;
        EditText textview_comments;

        LinearLayout lin_bk;
        LinearLayout lin_go;
        TextView textview_discrip;
        TextView tv_check;
      Button bt_comment,bt_like;

    }

    public interface OnClickInAdapter {
        public void onClickInAdapter(String uri);
    }
}
