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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mareesoftpc on 3/7/2017.
 */

public class ReceivedD extends AppCompatActivity {
    ListView listView;
    private String TAG = ReceivedD.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> contactList1;
    int count=1;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    public static final String KEY_USERNAME = "username";
    String username;
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
        setContentView(R.layout.received_layout);
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
                    Intent i = new Intent(ReceivedD.this, Test.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }

                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(ReceivedD.this, Edit.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent i = new Intent(ReceivedD.this, Chart.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);
//
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_sd) {
                    Intent i = new Intent(ReceivedD.this, SentD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(ReceivedD.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
//                    Intent i = new Intent(ReceivedD.this, ReceivedD.class);
////                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(ReceivedD.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_Grid) {


                    Intent i = new Intent(ReceivedD.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(ReceivedD.this, MainActivity.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(ReceivedD.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(ReceivedD.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(ReceivedD.this, TotalMembers.class);
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
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        listView=(ListView)findViewById(R.id.reclist1);
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
    }
    private class GetContacts1 extends AsyncTask<Void, Void, Void> {
        int increment;
        public GetContacts1(int increment){
            this.increment = increment;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
//            pDialog = new ProgressDialog(UpgradeStage.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/receivedonation.php?ibo="+username+"&format=json");

                Log.e(TAG, "Response from url: " + jsonStr1);

                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        TOTAL_LIST_ITEMS = contacts.length();
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String userid = c.getString("userid");
                            String entrylevel = c.getString("entrylevel");
                            String Firstname = c.getString("Firstname");
                            String Email = c.getString("Email");
                            String Phone = c.getString("Phone");
                            String sponsor_amount = c.getString("sponsor_amount");
                            String apprej = c.getString("apprej");

                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("userid", userid);
                            contact.put("entrylevel", entrylevel);
                            contact.put("Firstname", Firstname);
                            contact.put("Email", Email);
                            contact.put("Phone", Phone);
                            contact.put("sponsor_amount", sponsor_amount);
                            contact.put("apprej", apprej);


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
//                Toast.makeText(ReceivedD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        ReceivedD.this, sort,
                        R.layout.receivelist, new String[]{"sio", "userid", "entrylevel", "Firstname", "Email", "Phone", "sponsor_amount", "apprej"
                }, new int[]{R.id.rec_si, R.id.rec_rdf, R.id.rec_level, R.id.rec_name, R.id.rec_email, R.id.rec_phone, R.id.rec_amount, R.id.rec_date
                });

                listView.setAdapter(adapter);
            }catch (Exception e){
                e.printStackTrace();
//                Toast.makeText(ReceivedD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}