package com.Healthwealth;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.Healthwealth.Login.MY_PREFS_NAME1;

public class ToDoList extends AppCompatActivity {
    public static final String KEY_USERNAME = "username";
    String Firstname;
    public static final String KEY_Firstname = "name";

    String username;
    public static final String MY_PREFS_NAMET = "MyPrefsFile";
    public static final String KEY_Firstname1 = "name";
    SharedPreferences sp;
    private String TAG = MainActivity.class.getSimpleName();
    private int noOfBtns;
    private int noofdys;
    public int TOTAL_LIST_ITEMS;
    private int increment = 0;
    private Button[] btns;
    ListView lv;
    String pro_id;

    ArrayList<Integer> days = new ArrayList<Integer>();
    ArrayList<String> topics = new ArrayList<String>();
    ArrayList<String> tasknames = new ArrayList<String>();
    ArrayList<String> tasknos = new ArrayList<String>();
    ArrayList<String> prod_names1 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);
        username = sp.getString(KEY_USERNAME, null);
        Intent i = getIntent();
        pro_id = i.getStringExtra(Subsadapter.PoId);
//        pro_id = i.getStringExtra(Comment.KEY_ProId);

        Firstname = i.getStringExtra(Subsadapter.KEY_Firstname);

        lv = (ListView) findViewById(R.id.todolist);
        new GetContacts1(1).execute();
        new GetContacts().execute();
    }

    public void back(View view) {
        onBackPressed();
    }

    public void Btnfooter() {
//        int val = 100 % 10;
//        val = val == 0 ? 0 : 1;
//        noOfBtns = 100 / 10 + val;
        LinearLayout ll = (LinearLayout) findViewById(R.id.btnLay);
        btns = new Button[noofdys];

        for (int i = 0; i < noofdys; i++) {
            btns[i] = new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText("day" + (i + 1));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBtnBackGroud(j);
                    new GetContacts1(j + 1).execute();
                }
            });
        }

    }

    public void CheckBtnBackGroud(int index) {
        for (int i = 0; i < noofdys; i++) {
            if (i == index) {
//                btns[index].setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_red));
                btns[index].setBackgroundColor(getResources().getColor(android.R.color.tab_indicator_text));

                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            } else {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }

    }

    private class GetContacts1 extends AsyncTask<Void, Void, Void> {

        int increment;

        private GetContacts1(int increment) {
            this.increment = increment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                days.clear();
                topics.clear();
                tasknames.clear();
                tasknos.clear();
                prod_names1.clear();
                HttpHandler sh = new HttpHandler();
                String jsonStr2 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/todolist.php?format=json&id=" + pro_id + "&day=" + increment);
                Log.e(TAG, "Response from url: " + jsonStr2);
                if (jsonStr2 != null) {
                    try {
                        JSONObject todo = new JSONObject(jsonStr2);
                        JSONArray contacts2 = todo.getJSONArray("posts");
                        TOTAL_LIST_ITEMS = contacts2.length();
                        for (int i = 0; i < contacts2.length(); i++) {
                            JSONObject c2 = contacts2.getJSONObject(i);
                            String Days = c2.getString("Days");
                            String topic = c2.getString("topic");
                            String taskname = c2.getString("taskname");
                            String taskno = c2.getString("taskno");
                            String prod_name = c2.getString("prod_name");
                            int day1 = Integer.parseInt(Days);
                            days.add(day1);
                            topics.add(topic);
                            tasknames.add(taskname);
                            tasknos.add(taskno);
                            prod_names1.add(prod_name);
//                            HashMap<String, String> contact = new HashMap<>();
                            // adding each child node to HashMap key => value
//                            contact.put("prod_name", prod_name);
//                            contact.put("prod_desc", prod_desc);
//                            contact.put("prod_price", prod_price);

//                            URL newurl = new URL(url);
//                            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());

//                            contactList1.add(contact);
                        }
//
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(getApplicationContext(),
//                                        "Json parsing error: " + e.getMessage(),
//                                        Toast.LENGTH_LONG)
//                                        .show();

                            }
                        });
                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {

                ArrayList<Integer> days1 = new ArrayList<Integer>();
                ArrayList<String> topics1 = new ArrayList<String>();
                ArrayList<String> tasknames1 = new ArrayList<String>();
                ArrayList<String> tasknos1 = new ArrayList<String>();
                ArrayList<String> prod_names = new ArrayList<String>();

                int i = --increment;
//                for ( int i = increment;  i++) {
                if (i < TOTAL_LIST_ITEMS) {
                    days1.add(days.get(i));
                    topics1.add(topics.get(i));
                    tasknames1.add(tasknames.get(i));
                    tasknos1.add(tasknos.get(i));
                    prod_names.add(prod_names1.get(i));
                }

//                }

                ToDoadapter ListAdapter = new ToDoadapter(ToDoList.this,
                        username,
                        days,
                        topics,
                        tasknames,
                        tasknos,
                        prod_names1,
                        pro_id,
                        Firstname
                );

                lv.setAdapter(ListAdapter);

            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();
                String jsonStr2 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/firebase/daycount.php?format=json&prod_id=" + pro_id);
                Log.e(TAG, "Response from url: " + jsonStr2);
                if (jsonStr2 != null) {
                    try {
                        JSONObject todo = new JSONObject(jsonStr2);
                        JSONArray contacts2 = todo.getJSONArray("posts");
                        JSONObject c2 = contacts2.getJSONObject(0);
                        String Count = c2.getString("Count");
                        noofdys = Integer.parseInt(Count);
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(getApplicationContext(),
//                                        "Json parsing error: " + e.getMessage(),
//                                        Toast.LENGTH_LONG)
//                                        .show();
                            }
                        });
                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                Btnfooter();
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
