package com.Healthwealth;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TotalMembers extends AppCompatActivity {
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;
    ListView listView;
    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<String> namearray = new ArrayList<String>();
    ArrayList<String> iboarray = new ArrayList<String>();
    public static final String KEY_Firstname = "name";
    public static final String KEY_Ibo = "ibo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_members);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);
        listView = (ListView) findViewById(R.id.ltvw);
        new GetContacts().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TotalMembers.this, Chat.class);
                i.putExtra(KEY_Firstname, namearray.get(position));
                i.putExtra(KEY_Ibo, iboarray.get(position));
                startActivity(i);
            }
        });
    }

    public void edit(View view) {
        Intent i = new Intent(TotalMembers.this, ProgramSignup.class);
        Bundle bundle = ActivityOptions.makeCustomAnimation(TotalMembers.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }

    public void user(View view) {
        Intent i = new Intent(TotalMembers.this, Stats.class);

        Bundle bundle = ActivityOptions.makeCustomAnimation(TotalMembers.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }

    public void graph(View view) {
        Intent i = new Intent(TotalMembers.this, Group.class);

        Bundle bundle = ActivityOptions.makeCustomAnimation(TotalMembers.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }

    public void grid(View view) {
//        Intent i = new Intent(ProgramDetails.this, Grid.class);
//
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
//        Intent i = new Intent(ProgramDetails.this, SentD.class);
//
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void receive(View view) {
//        Intent i = new Intent(ProgramDetails.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void home(View view) {
        Intent i = new Intent(TotalMembers.this, ProgramSignup.class);
        startActivity(i);
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
                String jsonStr2 = sh.makeServiceCall("\n" +
                        "http://kothuram.com/donatefund/panel/android/chat_members.php?format=json&ibo=" + username);
                Log.e(TAG, "Response from url: " + jsonStr2);
                if (jsonStr2 != null) {
                    try {
                        JSONObject todo = new JSONObject(jsonStr2);
                        JSONArray contacts2 = todo.getJSONArray("posts");
                        for (int i = 0; i < contacts2.length(); i++) {
                            JSONObject c2 = contacts2.getJSONObject(i);
                            String Username = c2.getString("Username");
                            String ibo = c2.getString("ibo");
                            iboarray.add(ibo);
                            namearray.add(Username);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(getApplicationContext(),"Json parsing error: " + e.getMessage(),Toast.LENGTH_LONG).show();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<String> namearray1 = new ArrayList<String>();
                        ArrayList<String> iboarray1 = new ArrayList<String>();
                        namearray1.clear();
                        iboarray1.clear();
                        for (int i = 0; i < namearray.size(); i++) {
                            namearray1.add(namearray.get(i));
                            iboarray1.add(iboarray.get(i));
                        }
                        Totalmembadpt ListAdapter = new Totalmembadpt(TotalMembers.this,
                                namearray1
                        );
                        listView.invalidateViews();
                        listView.setAdapter(ListAdapter);
//                        ListAdapter.notifyDataSetChanged();
                    }
                });
            } catch (
                    Exception e) {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
