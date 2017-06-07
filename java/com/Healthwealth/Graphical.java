package com.Healthwealth;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Graphical extends AppCompatActivity {
    private String TAG = Graphical.class.getSimpleName();
    String username;
    public static final String KEY_USERNAME = "username";
    String Firstname;
    public static final String KEY_Firstname = "name";
    TextView tv1_name, tv1_ibo, tv2a_name, tv2a_ibo, tv2b_name,
            tv2b_ibo, tv3a_name, tv3a_ibo, tv3b_name, tv3b_ibo, tv3c_name, tv3c_ibo, tv3d_name, tv3d_ibo;
    String IBO, name1;
    LinearLayout lvl1, lvl2a, lvl2b, lvl3a, lvl3b, lvl3c, lvl3d;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical);
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

                    Intent i = new Intent(Graphical.this, Test.class);

                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }

                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(Graphical.this, Edit.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent i = new Intent(Edit.this, Chart.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);
//
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_sd) {
                    Intent i = new Intent(Graphical.this, SentD.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(Graphical.this, ReceivedD.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(Graphical.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(Graphical.this, Graphical.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);
                    startActivity(i);
                    finish();
                }
                if (menuItem.getItemId() == R.id.item_Grid) {

                    Intent i = new Intent(Graphical.this, Grid.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(Graphical.this, MainActivity.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(Graphical.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }

                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(Graphical.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname,Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(Graphical.this, TotalMembers.class);
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
        tv1_name = (TextView) findViewById(R.id.lvl1name);
        tv1_ibo = (TextView) findViewById(R.id.lvl1ibo);
        tv2a_name = (TextView) findViewById(R.id.lvl2aname);
        tv2a_ibo = (TextView) findViewById(R.id.lvl2aibo);
        tv2b_name = (TextView) findViewById(R.id.lvl2bname);
        tv2b_ibo = (TextView) findViewById(R.id.lvl2bibo);
        tv3a_name = (TextView) findViewById(R.id.lvl3aname);
        tv3a_ibo = (TextView) findViewById(R.id.lvl3aibo);
        tv3b_name = (TextView) findViewById(R.id.lvl3bname);
        tv3b_ibo = (TextView) findViewById(R.id.lvl3bibo);
        tv3c_name = (TextView) findViewById(R.id.lvl3cname);
        tv3c_ibo = (TextView) findViewById(R.id.lvl3cibo);
        tv3d_name = (TextView) findViewById(R.id.lvl3dname);
        tv3d_ibo = (TextView) findViewById(R.id.lvl3dibo);
        tv1_name.setText(Firstname);
        tv1_ibo.setText(username);
        new GetContacts1().execute();
        new GetContacts2().execute();
        new GetContacts3().execute();
        new GetContacts4().execute();
        new GetContacts5().execute();
        new GetContacts6().execute();
        lvl1 = (LinearLayout) findViewById(R.id.level1);
        lvl2a = (LinearLayout) findViewById(R.id.level2a);
        lvl2b = (LinearLayout) findViewById(R.id.level2b);
        lvl3c = (LinearLayout) findViewById(R.id.level3a);
        lvl3b = (LinearLayout) findViewById(R.id.level3b);
        lvl3c = (LinearLayout) findViewById(R.id.level3c);
        lvl3d = (LinearLayout) findViewById(R.id.level3d);

    }
//    public void back(View view){
//        Intent i = new Intent(Graphical.this, MainActivity.class);
//        SharedPreferences sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);
//        String username = sp.getString(KEY_USERNAME, null);
//        i.putExtra(KEY_USERNAME, username);
////        i.putExtra(KEY_Firstname, Firstname);
//        startActivity(i);
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
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/binarygraphical.php?ibo=" + username + "&format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        JSONObject c = contacts.getJSONObject(0);
                        if (c.length() > 0) {
                            IBO = c.getString("IBO");
                            name1 = c.getString("Firstname");
                        } else {
                            IBO = ("");
                            name1 = ("");
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IBO = ("");
                                name1 = ("");
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
                            IBO = ("");
                            name1 = ("");
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                IBO = ("");
                name1 = ("");
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                tv2a_ibo.setText(IBO);
                tv2a_name.setText(name1);

            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetContacts2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/binarygraphical.php?ibo=" + username + "&format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        JSONObject c = contacts.getJSONObject(1);
                        if (c.length() > 0) {
                            IBO = c.getString("IBO");
                            name1 = c.getString("Firstname");
                        } else {
                            IBO = ("");
                            name1 = ("");
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IBO = ("");
                                name1 = ("");
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
                            IBO = ("");
                            name1 = ("");
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                IBO = ("");
                name1 = ("");
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                tv2b_ibo.setText(IBO);
                tv2b_name.setText(name1);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetContacts3 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/binarygraphical.php?ibo=" + username + "&format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        JSONObject c = contacts.getJSONObject(2);
                        if (c.length() > 0) {
                            IBO = c.getString("IBO");
                            name1 = c.getString("Firstname");
                        } else {
                            IBO = ("");
                            name1 = ("");
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IBO = ("");
                                name1 = ("");
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
                            IBO = ("");
                            name1 = ("");
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                IBO = ("");
                name1 = ("");
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                tv3a_ibo.setText(IBO);
                tv3a_name.setText(name1);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetContacts4 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/binarygraphical.php?ibo=" + username + "&format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        JSONObject c = contacts.getJSONObject(3);
                        if (c.length() > 0) {
                            IBO = c.getString("IBO");
                            name1 = c.getString("Firstname");
                        } else {
                            IBO = ("");
                            name1 = ("");
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IBO = ("");
                                name1 = ("");
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
                            IBO = ("");
                            name1 = ("");
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                tv3b_ibo.setText(IBO);
                tv3b_name.setText(name1);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetContacts5 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/binarygraphical.php?ibo=" + username + "&format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        JSONObject c = contacts.getJSONObject(4);
                        if (c.length() > 0) {
                            IBO = c.getString("IBO");
                            name1 = c.getString("Firstname");
                        } else {
                            IBO = ("");
                            name1 = ("");
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IBO = ("");
                                name1 = ("");
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
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                tv3c_ibo.setText(IBO);
                tv3c_name.setText(name1);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class GetContacts6 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/binarygraphical.php?ibo=" + username + "&format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        JSONObject c = contacts.getJSONObject(5);
                        if (c.length() > 0) {
                            IBO = c.getString("IBO");
                            name1 = c.getString("Firstname");
                        } else {
                            IBO = ("");
                            name1 = ("");
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IBO = ("");
                                name1 = ("");
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
                            IBO = ("");
                            name1 = ("");
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't get json from server. Check LogCat for possible errors!",
//                                    Toast.LENGTH_LONG)
//                                    .show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                IBO = ("");
                name1 = ("");
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                tv3d_ibo.setText(IBO);
                tv3d_name.setText(name1);

            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(Graphical.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void level1(View view) {
        if (tv1_name.getText().toString().trim().equals("") && tv1_ibo.getText().toString().trim().equals("")) {
            invisible();
        }
        if (username .equals(tv1_ibo.getText().toString().trim())) {
            username = tv1_ibo.getText().toString().trim();
            Firstname = tv1_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();

//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();
        }
    }

    public void level2a(View view) {
        if (tv2a_name.getText().toString().trim().equals("") && tv2a_ibo.getText().toString().trim().equals("")) {
            invisible();

        }
        if (username .equals(tv1_ibo.getText().toString().trim())) {
            username = tv2a_ibo.getText().toString().trim();
            Firstname = tv2a_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();

        }

//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();

    }

    public void level2b(View view) {
        if (tv2b_name.getText().toString().trim().equals("") && tv2b_ibo.getText().toString().trim().equals("")) {
            invisible();

        }
        if (username .equals(tv1_ibo.getText().toString().trim())) {
            username = tv2b_ibo.getText().toString().trim();
            Firstname = tv2b_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();

//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();
        }
    }

    public void level3a(View view) {
        if (tv3a_name.getText().toString().trim().equals("") && tv3a_ibo.getText().toString().trim().equals("")) {
            invisible();


        }
        if (username .equals( tv1_ibo.getText().toString().trim())) {
            username = tv3a_ibo.getText().toString().trim();
            Firstname = tv3a_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();

//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();

        }
    }

    public void level3b(View view) {
        if (tv3b_name.getText().toString().trim().equals("") && tv3b_ibo.getText().toString().trim().equals("")) {
            invisible();

        }
        if (username .equals( tv1_ibo.getText().toString().trim())) {
            username = tv3b_ibo.getText().toString().trim();
            Firstname = tv3b_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();

//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();
        }
    }

    public void level3c(View view) {
        if (tv3c_name.getText().toString().trim().equals("") && tv3c_ibo.getText().toString().trim().equals("")) {
            invisible();

        }
       if (username .equals( tv1_ibo.getText().toString().trim()) ){
            username = tv3c_ibo.getText().toString().trim();
            Firstname = tv3c_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();

//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();
        }
    }

    public void level3d(View view) {
        if (tv3d_name.getText().toString().trim().equals("") && tv3d_ibo.getText().toString().trim().equals("")) {

         invisible();
        }
       if (username.equals(tv1_ibo.getText().toString().trim())) {
            username = tv3d_ibo.getText().toString().trim();
            Firstname = tv3d_name.getText().toString().trim();
            tv1_name.setText(Firstname);
            tv1_ibo.setText(username);
            new GetContacts1().execute();
            new GetContacts2().execute();
            new GetContacts3().execute();
            new GetContacts4().execute();
            new GetContacts5().execute();
            new GetContacts6().execute();
//        Toast.makeText(this, username+Firstname, Toast.LENGTH_SHORT).show();
        }
    }

    public void invisible(){
        tv1_name.setVisibility(View.INVISIBLE);
        tv1_ibo.setVisibility(View.INVISIBLE);
        tv2a_name.setVisibility(View.INVISIBLE);
        tv2a_ibo.setVisibility(View.INVISIBLE);
        tv2b_name.setVisibility(View.INVISIBLE);
        tv2b_ibo.setVisibility(View.INVISIBLE);
        tv3a_name.setVisibility(View.INVISIBLE);
        tv3a_ibo.setVisibility(View.INVISIBLE);
        tv3b_name.setVisibility(View.INVISIBLE);
        tv3b_ibo.setVisibility(View.INVISIBLE);
        tv3c_name.setVisibility(View.INVISIBLE);
        tv3c_ibo.setVisibility(View.INVISIBLE);
        tv3d_name.setVisibility(View.INVISIBLE);
        tv3d_ibo.setVisibility(View.INVISIBLE);
    }
}
