package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mareesoftpc on 3/29/2017.
 */

public class Subsadapter extends BaseAdapter {

    public static final String PoId = null;
    public static final String PoName = null;
    public static final String KEY_Firstname = "name";
    public static final String KEY_USERNAME = null;
    public static final String KEY_PROID = null;
    public static final String KEY_STATUS = null;
    String stat;
    String th;
    Context context;
    ArrayList<String> prod_name1;
    ArrayList<String> prod_desc1;
    ArrayList<String> prod_price1;
    ArrayList<String> prod_code1;
    ArrayList<String> satus1;
    String username;
    String Firstname;

//    String prod_code[];

    public Subsadapter(
            Context context2,
            ArrayList<String> prod_name1,
            ArrayList<String> prod_desc1,
            ArrayList<String> prod_price1,
            ArrayList<String> prod_code1,
            ArrayList<String> satus1,
            String username,
            String Firstname

//            String prod_code[]


    ) {

        this.context = context2;
        this.prod_name1 = prod_name1;
        this.prod_desc1 = prod_desc1;
        this.prod_price1 = prod_price1;
        this.prod_code1 = prod_code1;
        this.satus1 = satus1;
        this.username = username;
        this.Firstname = Firstname;


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return prod_code1.size();
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

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.subscribelist, null);

            holder = new Holder();

            holder.textview_prod_desc = (TextView) child.findViewById(R.id.prod_desc);
            holder.textview_prod_name = (TextView) child.findViewById(R.id.prod_name);
            holder.textview_prod_price = (TextView) child.findViewById(R.id.prod_price);
            holder.po = (TextView) child.findViewById(R.id.t);
            holder.tv_check = (TextView) child.findViewById(R.id.check);
            holder.lin_bk = (LinearLayout) child.findViewById(R.id.back);
            holder.lin_go = (LinearLayout) child.findViewById(R.id.click);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textview_prod_desc.setText(prod_name1.get(position));
        holder.textview_prod_name.setText(prod_desc1.get(position));
        holder.textview_prod_price.setText(prod_price1.get(position));
        holder.po.setText(prod_code1.get(position));
        th = holder.po.getText().toString().trim();
        stat = satus1.get(position);
//        holder.po.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handler.post(timedTask);
//            }
//        });
        if (satus1.get(position).equals("Approved")) {
            holder.tv_check.setBackgroundResource(R.drawable.subscribed);
            if (prod_code1.get(position).equals("1")) {
                holder.lin_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = "http://kothuram.com/donatefund/index.php/readsubscribe/1";
                        Intent intent = new Intent(context, ToDoList.class);
//                        Bundle extras = new Bundle();
//                        extras.putString(PoId, "1");
//                        extras.putString(PoName, prod_name1.get(position));
//                        intent.putExtras(extras);
//                        intent.putExtra(PoName, prod_name1.get(position));
                        intent.putExtra(KEY_Firstname, Firstname);
                        intent.putExtra(PoId, "1");
                        context.startActivity(intent);
                    }
                });
            }
            if (prod_code1.get(position).equals("2")) {
                holder.lin_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = "http://kothuram.com/donatefund/index.php/readsubscribe/2";
                        Intent intent = new Intent(context, ToDoList.class);
//                        Bundle extras = new Bundle();
//                        extras.putString(PoId, "2");
//                        extras.putString(PoName, prod_name1.get(position));
//                        intent.putExtras(extras);
//                        intent.putExtra(PoName, prod_name1.get(position));

                        intent.putExtra(PoId, "2");
                        context.startActivity(intent);
                    }
                });
            }
            if (prod_code1.get(position).equals("3")) {
                holder.lin_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = "http://kothuram.com/donatefund/index.php/readsubscribe/3";
                        Intent intent = new Intent(context, ToDoList.class);
//                        Bundle extras = new Bundle();
//                        extras.putString(PoId, "3");
//                        extras.putString(PoName, prod_name1.get(position));
//                        intent.putExtras(extras);
//                        intent.putExtra(PoName, prod_name1.get(position));
                        intent.putExtra(PoId, "3");
                        context.startActivity(intent);
                    }
                });
            }
        }
        if (satus1.get(position).equals("Pending")) {
            holder.tv_check.setBackgroundResource(R.drawable.approval);

        }
        if (satus1.get(position).equals("null")) {

            holder.tv_check.setBackgroundResource(R.drawable.subscribe);
            holder.lin_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register(username, prod_code1.get(position), "Pending");
                }
            });
        }
        if (satus1.get(position).equals("Rejected")) {
            holder.tv_check.setBackgroundResource(R.drawable.subscribe);
            holder.lin_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register(username, prod_code1.get(position), "Pending");
                    notifyDataSetChanged();
                }
            });
        }
        if (prod_code1.get(position).equals("1")) {
            holder.lin_bk.setBackgroundResource(R.drawable.c);

        }
        if (prod_code1.get(position).equals("2")) {
            holder.lin_bk.setBackgroundResource(R.drawable.h);

        }
        if (prod_code1.get(position).equals("3")) {
            holder.lin_bk.setBackgroundResource(R.drawable.php);
        }

        return child;
    }

    public class Holder {
        TextView textview_prod_desc;
        TextView textview_prod_name;
        TextView textview_prod_price;
        LinearLayout lin_bk;
        LinearLayout lin_go;
        TextView po;
        TextView tv_check;

    }

    public interface OnClickInAdapter {
        public void onClickInAdapter(String uri);
    }

    private void register(String username, String th, String stat) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("ibo", params[0]);
                data.put("prod_id", params[1]);
                data.put("status", params[2]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/approval.php", data);
                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(username, th, stat);
    }
//    Handler handler = new Handler();
//    Runnable timedTask = new Runnable(){
//
//        @Override
//        public void run() {
//            handler.postDelayed(timedTask, 1000);
//        }};
}