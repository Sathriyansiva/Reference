package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mareesoftpc on 3/7/2017.
 */

public class SentD extends AppCompatActivity {
    ListView listView;
    private String TAG = SentD.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> contactList1;
    int count=1;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    public static final String KEY_USERNAME = "username";
    String username;
    TextView tv_sino, tv_senddon, tv_level, tv_amount, tv_date;
    String Firstname;
    public static final String KEY_Firstname = "name";
    private Button btn_prev;
    private Button btn_next;
    private int pageCount;
    public int NUM_ITEMS_PAGE = 10;
    public int TOTAL_LIST_ITEMS = 0;
    private int increment = 0;
    public int val=0;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_layout);
        SharedPreferences sp1 = this.getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        username = sp1.getString(Config.Ibo_SHARED_PREF,null);
        Firstname = sp1.getString(Config.Name_SHARED_PREF,null);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_item_dash) {

                    Intent i = new Intent(SentD.this, Test.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }

                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(SentD.this, Edit.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent i = new Intent(SentD.this, Chart.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);
//
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_sd) {
//                    Intent i = new Intent(SentD.this, SentD.class);
////                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(SentD.this, ReceivedD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(SentD.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);


                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(SentD.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_Grid) {
                    Intent i = new Intent(SentD.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(SentD.this, MainActivity.class);
                    i.putExtra(KEY_USERNAME, username);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(SentD.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(SentD.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(SentD.this, TotalMembers.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                return false;
            }

        });
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        tv_sino = (TextView) findViewById(R.id.sino1);
        tv_senddon = (TextView) findViewById(R.id.sndo);
        tv_level = (TextView) findViewById(R.id.lvl);
        tv_amount = (TextView) findViewById(R.id.amnt);
        tv_date = (TextView) findViewById(R.id.dt);
        listView=(ListView)findViewById(R.id.sendlist1);
        contactList1 = new ArrayList<>();
        btn_prev = (Button) findViewById(R.id.prev);
        btn_next = (Button) findViewById(R.id.next);
        btn_prev.setEnabled(false);
        new GetContacts1(0).execute();

        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                increment++;
                CheckEnable();
                new GetContacts1(increment).execute();
//                new GetContacts1(increment++);
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment--;
                CheckEnable();
                new GetContacts1(increment).execute();
            }
        });

    }
    private void CheckEnable() {
        try {
            val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
            val = val == 0 ? 0 : 1;
            pageCount = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;
            if (increment + 1 == pageCount) {
                btn_next.setEnabled(false);
            } else if (increment == 0) {
                btn_prev.setEnabled(false);
            } else {
                btn_prev.setEnabled(true);
                btn_next.setEnabled(true);
            }
        }catch (Exception e){
            e.printStackTrace();
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private class GetContacts1 extends AsyncTask<Void, Void, Void> {
        int increment;
        public GetContacts1(int increment){
            this.increment = increment;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/senddonation.php?ibo="+username+"&format=json");

                Log.e(TAG, "Response from url: " + jsonStr1);

                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        TOTAL_LIST_ITEMS = contacts.length();
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String sentdonto = c.getString("sentdonto");
                            String lvl = c.getString("lvl");
                            String amnt = c.getString("amnt");
                            String dt = c.getString("dt");

                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("sentdonto", sentdonto);
                            contact.put("lvl", lvl);
                            contact.put("amnt", amnt);
                            contact.put("dt", dt);

                            if (contacts.length() > 0) {
                                for (int i1 = 1; i1 <= contacts.length(); i1++) {
                                    String sio = String.valueOf(count);
                                    contact.put("sio", sio);
                                }
                                count++;
                            }

//                        contact.put("mobile", mobile);
                            // adding contact to contact list
                            contactList1.add(contact);
                        }
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


            }catch (Exception e){
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {


                super.onPostExecute(result);
                // Dismiss the progress dialog
//            if (pDialog.isShowing())
//                pDialog.dismiss();
                ArrayList<HashMap<String, String>> sort = new ArrayList<>();
                int start = increment * NUM_ITEMS_PAGE;
                for ( int i = start; i < (start)+NUM_ITEMS_PAGE; i++) {
                    if(i<TOTAL_LIST_ITEMS)
                    {
                        sort.add(contactList1.get(i));
                    }
                }
                ListAdapter adapter = new SimpleAdapter(
                        SentD.this, sort,
                        R.layout.sendlist, new String[]{"sio", "sentdonto", "lvl", "amnt", "dt"
                }, new int[]{R.id.sino1, R.id.sndonto, R.id.level, R.id.amount, R.id.date
                });

                listView.setAdapter(adapter);
            }catch (Exception e){
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

}