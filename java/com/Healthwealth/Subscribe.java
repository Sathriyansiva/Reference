package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Subscribe extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ListView lv;
    SharedPreferences sp;
    public static final String KEY_USERNAME = "username";
    String Firstname;
    String username;

    public static final String KEY_Firstname = "name";
    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<HashMap<String, String>> contactList1;
    ArrayList<String> prod_name1 = new ArrayList<String>();
    ArrayList<String> prod_desc1 = new ArrayList<String>();
    ArrayList<String> prod_price1 = new ArrayList<String>();
    ArrayList<String> prod_code1 = new ArrayList<String>();

    ArrayList<String> satus1 = new ArrayList<String>();


    private Handler mHandler;
    Runnable updater;
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        Firstname = sp.getString(Config.Name_SHARED_PREF, null);

        Intent i = getIntent();
        Firstname = i.getStringExtra(Grid.KEY_Firstname);
        Firstname = i.getStringExtra(Test.KEY_Firstname);
        Firstname = i.getStringExtra(Edit.KEY_Firstname);
        Firstname = i.getStringExtra(ReceivedD.KEY_Firstname);
        Firstname = i.getStringExtra(SentD.KEY_Firstname);
        Firstname = i.getStringExtra(UpgradeStage.KEY_Firstname);
        Firstname = i.getStringExtra(Comment.KEY_Firstname);
        Firstname = i.getStringExtra(ToDoList.KEY_Firstname);

        Firstname = i.getStringExtra(MainActivity.KEY_Firstname1);
        Firstname = i.getStringExtra(Graphical.KEY_Firstname);
        Firstname = i.getStringExtra(Login.KEY_Firstname2);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                mDrawerLayout.closeDrawers();
//                if (menuItem.getItemId() == R.id.nav_item_dash) {
//
//                    Intent i = new Intent(Subscribe.this, Test.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                }
//                if (menuItem.getItemId() == R.id.nav_item_edit) {
//                    Intent i = new Intent(Subscribe.this, Edit.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
////                if (menuItem.getItemId() == R.id.nav_item_profile) {
////                    Intent i = new Intent(Test.this, Chart.class);
////                    i.putExtra(KEY_USERNAME, username);
////                    i.putExtra(KEY_Firstname, Firstname);
////
////                    startActivity(i);
////                }
//                if (menuItem.getItemId() == R.id.nav_item_sd) {
//                    Intent i = new Intent(Subscribe.this, SentD.class);
//
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_rd) {
//                    Intent i = new Intent(Subscribe.this, ReceivedD.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_logout) {
//                    Intent i = new Intent(Subscribe.this, Login.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    i.putExtra("GO", false);
//                    startActivity(i);
//                    finish();
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.item_graphical) {
//                    Intent i = new Intent(Subscribe.this, Graphical.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.item_Grid) {
//                    Intent i = new Intent(Subscribe.this, Grid.class);
//
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_home) {
//                    Intent i = new Intent(Subscribe.this, MainActivity.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_subs) {
//                    Intent i = new Intent(Subscribe.this, Subscribe.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_chart) {
//                    Intent i = new Intent(Subscribe.this, Chart.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                if (menuItem.getItemId() == R.id.nav_item_chat) {
//                    Intent i = new Intent(Subscribe.this, TotalMembers.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                return false;
//            }
//
//        });
//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
//                R.string.app_name);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();
        lv = (ListView) findViewById(R.id.sublist);
        contactList1 = new ArrayList<>();
        new GetContacts1().execute();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 000);

    }

    public void edit(View view) {
        Intent i = new Intent(Subscribe.this, Edit.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void Task(View view) {
        Intent i = new Intent(Subscribe.this, ProgramSignup.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void user(View view) {
        Intent i = new Intent(Subscribe.this, Stats.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void graph(View view) {
        Intent i = new Intent(Subscribe.this, Group.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void grid(View view) {
//        Intent i = new Intent(ProgramPage.this, Grid.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
//        Intent i = new Intent(ProgramPage.this, SentD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void receive(View view) {
//        Intent i = new Intent(ProgramPage.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }


    //    void updateTime(final String timeString) {
//        timer=(TextView) findViewById(R.id.timerText);
//        final Handler timerHandler = new Handler();
//
//        updater = new Runnable() {
//            @Override
//            public void run() {
//                timer.setText(timeString);
//                timerHandler.postDelayed(updater,1000);
//            }
//        };
//        timerHandler.post(updater);
//    }
    private class GetContacts1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/firebase/subscription.php?format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);

                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String prod_name = c.getString("prod_name");
                            String prod_desc = c.getString("prod_desc");
                            String prod_price = c.getString("prod_price");
                            String prod_code = c.getString("prod_code");
                            prod_name1.add(prod_name);
                            prod_desc1.add(prod_desc);
                            prod_price1.add(prod_price);
                            prod_code1.add(prod_code);

                            String jsonStr = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/checkstatus.php?format=json&ibo=" + username + "&prod_id=" + prod_code);
                            Log.e(TAG, "Response from url: " + jsonStr);
                            JSONObject jsonObj1 = new JSONObject(jsonStr);
                            JSONArray contacts1 = jsonObj1.getJSONArray("posts");
                            JSONObject c1 = contacts1.getJSONObject(0);
                            String status = c1.getString("status");
                            satus1.add(status);


                            String jsonStr2 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/todolist.php?format=json&id=" + prod_code);
                            Log.e(TAG, "Response from url: " + jsonStr2);
                            JSONObject todo = new JSONObject(jsonStr2);
                            JSONArray contacts2 = todo.getJSONArray("posts");

                            HashMap<String, String> contact = new HashMap<>();
                            // adding each child node to HashMap key => value
                            contact.put("prod_name", prod_name);
                            contact.put("prod_desc", prod_desc);
                            contact.put("prod_price", prod_price);

//                            URL newurl = new URL(url);
//                            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());

                            contactList1.add(contact);
                        }

//
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Subscribe.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                Subsadapter ListAdapter = new Subsadapter(Subscribe.this,
                        prod_name1,
                        prod_desc1,
                        prod_price1,
                        prod_code1,
                        satus1,
                        username,
                        Firstname

                );
//                List<ClipData.Item> newItems = databaseHandler.getItems();
//                ListAdapter.clear();
//                ListAdapter.addAll(newItems);
//                ListAdapter.notifyDataSetChanged();
//                ListAdapter.close();
                lv.setAdapter(ListAdapter);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Subscribe.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
//    public void home(View view) {
//        Intent i=new Intent(Subscribe.this,ProgramSignup.class);
//        startActivity(i);
//    }
}
