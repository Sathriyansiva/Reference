package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UpgradeStage extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    String username;
    String name;
    String Entrylevel;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    TextView ed_copy, tv_sponcername, tv_bitcnadd, tv_donationamt, tv_bitcpyadd, TextView, pass, tv_step;
    public static final String KEY_USERNAME = "username";
    String Firstname;
    public static final String KEY_Firstname = "name";
    String sio;
    int count = 1;
    EditText ed_upldlnk;
    Button bt_upld;
    Boolean Patterns;
    String webpattern = " Patterns.WEB_URL.matcher(potentialUrl).matches()";
    private Button btn_prev;
    private Button btn_next;
    private int pageCount = 0;
    public int NUM_ITEMS_PAGE = 10;
    public int TOTAL_LIST_ITEMS = 0;
    private int increment = 0;
    public int val = 0;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_stage);
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
                    Intent i = new Intent(UpgradeStage.this, Test.class);
                    i.putExtra(KEY_Firstname, Firstname);

                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(UpgradeStage.this, Edit.class);
                    i.putExtra(KEY_Firstname, Firstname);

                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent i = new Intent(UpgradeStage.this, Chart.class);
//                    i.putExtra(KEY_Firstname,Firstname);
//
//                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_sd) {
                    Intent i = new Intent(UpgradeStage.this, SentD.class);
                    i.putExtra(KEY_Firstname, Firstname);

                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(UpgradeStage.this, ReceivedD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(UpgradeStage.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(UpgradeStage.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_Grid) {

                    Intent i = new Intent(UpgradeStage.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(UpgradeStage.this, MainActivity.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(UpgradeStage.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(UpgradeStage.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(UpgradeStage.this, TotalMembers.class);
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
        bt_upld = (Button) findViewById(R.id.upload);

        bt_upld.setVisibility(View.GONE);
        ed_upldlnk = (EditText) findViewById(R.id.uploadlink);
        ed_copy = (TextView) findViewById(R.id.ed_copy);
        tv_sponcername = (TextView) findViewById(R.id.sponsor);
        tv_bitcnadd = (TextView) findViewById(R.id.bitcoinadd);
        tv_donationamt = (TextView) findViewById(R.id.donationamt);
        tv_step = (TextView) findViewById(R.id.step);
        pass = (TextView) findViewById(R.id.pass);
        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.upgradelist);
        btn_prev = (Button) findViewById(R.id.prev);
        btn_next = (Button) findViewById(R.id.next);
        btn_prev.setEnabled(false);

        new GetContacts(0).execute();
        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                increment++;
                CheckEnable();
                new GetContacts(increment).execute();
//                new GetContacts1(increment++);
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                increment--;
                CheckEnable();
                new GetContacts(increment).execute();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pass.performClick();
                getData();
            }
        }, 000);

        if (Entrylevel.equals("0")) {
            tv_step.setText("Stage" + Entrylevel + ": 0.01 (Bronze )");
            tv_donationamt.setText("0.01");
        }
        if (Entrylevel.equals("1")) {
            tv_step.setText("Stage" + Entrylevel + ": 0.015 (Silver)");
            tv_donationamt.setText("0.015");

        }
        if (Entrylevel.equals("2")) {
            tv_step.setText("Stage" + Entrylevel + ": 0.05 (Gold)");
            tv_donationamt.setText("0.05");

        }
        if (Entrylevel.equals("3")) {
            tv_step.setText("Stage" + Entrylevel + ": 0.30 (Diamond)");
            tv_donationamt.setText("0.30");
        }
    }

    private void CheckEnable() {
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
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

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        int increment;

        public GetContacts(int increment) {
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
                String jsonStr = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/upgrade_grid.php?format=json&ibo=" + username);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        TOTAL_LIST_ITEMS = contacts.length();
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String image = c.getString("image");
                            String elevel = c.getString("elevel");
                            String sponsor = c.getString("sponsor");
                            String spo_amt = c.getString("spo_amt");
                            String status = c.getString("status");
                            String uploaddate = c.getString("uploaddate");
                            String apprej = c.getString("apprej");


                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("name", name);
                            contact.put("image", image);
                            contact.put("username", username);
                            contact.put("elevel", elevel);
                            contact.put("sponsor", sponsor);
                            contact.put("spo_amt", spo_amt);
                            contact.put("uploaddate", uploaddate);
                            contact.put("apprej", apprej);
                            contact.put("status", status);
                            if (elevel.equals("0")) {
                                contact.put("stage", "Bronze");

                            }
                            if (elevel.equals("1")) {
                                contact.put("stage", "Silver");
                            }
                            if (elevel.equals("2")) {
                                contact.put("stage", "Gold");
                            }
                            if (elevel.equals("3")) {
                                contact.put("stage", "Diamond");
                            }
                            if (status.equals("pending")) {

                            }
                            if (contacts.length() > 0) {
                                for (int i1 = 1; i1 <= contacts.length(); i1++) {
                                    String sio = String.valueOf(count);
                                    contact.put("sio", sio);
                                }
                                count++;
                            }

//                        contact.put("mobile", mobile);
                            // adding contact to contact list
                            contactList.add(contact);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
//            Toast.makeText(UpgradeStage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);

                ArrayList<HashMap<String, String>> sort = new ArrayList<>();
                int start = increment * NUM_ITEMS_PAGE;
                for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
                    if (i < TOTAL_LIST_ITEMS) {
                        sort.add(contactList.get(i));
                    }
                }
                ListAdapter adapter = new SimpleAdapter(
                        UpgradeStage.this, sort,
                        R.layout.upgradelist, new String[]{"sio", "name", "image", "username", "elevel", "sponsor", "spo_amt", "uploaddate", "apprej",
                        "status", "stage"}, new int[]{R.id.sino, R.id.uname, R.id.linkname, R.id.ibo, R.id.level, R.id.spon, R.id.amount
                        , R.id.uplddat, R.id.approvdat, R.id.status, R.id.stage});

                lv.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
//            Toast.makeText(UpgradeStage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void copylink(View view) {
        try {
            String text = ed_copy.getText().toString().trim();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UpgradeStage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void pass(View view) {
    }

    private void getData() {
        try {
//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
            String url = "http://kothuram.com/donatefund/panel/android/upgrade.php?format=json&ibo=" + username + "&level=" + Entrylevel;
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                loading.dismiss();
                    showJSON(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UpgradeStage.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UpgradeStage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showJSON(String response) {
        try {
            String sFirstname = "";
            String sLastname = "";
            String sibo = "";
            String sEmail = "";
            String sPhone = "";
            String sWhatsup = "";
            String sEntrylevel = "";
            String scoin_link = "";
            String scoin_address = "";
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray("posts");
                JSONObject collegeData = result.getJSONObject(0);
                sFirstname = collegeData.getString("sFirstname");
                sLastname = collegeData.getString("sLastname");
                sibo = collegeData.getString("sibo");
                sEmail = collegeData.getString("sEmail");
                sPhone = collegeData.getString("sPhone");
                sWhatsup = collegeData.getString("sWhatsup");
                sEntrylevel = collegeData.getString("sEntrylevel");
                scoin_link = collegeData.getString("scoin_link");
                scoin_address = collegeData.getString("scoin_address");
            } catch (JSONException e) {
                e.printStackTrace();
            }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
            ed_copy.setText(scoin_address);
            tv_sponcername.setText(sFirstname);
            tv_bitcnadd.setText(scoin_address);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UpgradeStage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void upload(View view) {
        try {
            String link = ed_upldlnk.getText().toString().trim();
            String coinaddrs = tv_bitcnadd.getText().toString().trim();
            String sponname = tv_sponcername.getText().toString().trim();
            String donnamnt = tv_donationamt.getText().toString().trim();
            register(link, username, Entrylevel, sponname, donnamnt, coinaddrs);
//        $image=$_POST['image'];
//        $userid=$_POST['userid'];
//        $entrylevel=$_POST['entrylevel'];
//        $sponsor=$_POST['sponsor'];
//        $sponsor_amount=$_POST['sponsor_amount'];
//        $sponsor_coinid=$_POST['sponsor_coinid'];
//        $sponsor_coinaddress=$_POST['sponsor_coinaddress'];
        } catch (Exception e) {
            e.printStackTrace();
//        Toast.makeText(UpgradeStage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void register(String link, String username, String Entrylevel, String sponname, String donnamnt, String coinaddrs) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpgradeStage.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("image", params[0]);
                data.put("userid", params[1]);
                data.put("entrylevel", params[2]);
                data.put("sponsor", params[3]);
                data.put("sponsor_coinaddress", params[4]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/upgradeinsert.php", data);
                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(link, username, Entrylevel, sponname, donnamnt, coinaddrs);
    }
}
