package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
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

import de.hdodenhof.circleimageview.CircleImageView;

import static com.Healthwealth.Login.MY_PREFS_NAME1;

public class Test extends AppCompatActivity {
    TextView tv, tv1, stage, sent, received, tv_joined, vi, tv_name, tv_id, tv_sponsor, tv_email, tv_whatsup, tv_mobile, tv_link;
    String myJSON;
    String Entrylevel;
    public static final String KEY_USERNAME = "username";
    String Firstname;
    public static final String KEY_Firstname = "name";
    public static final String KEY_LEVEL = "level";
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    JSONArray peoples = null;
    String username;
    private ProgressDialog loading;
    CircleImageView iv;
    private final int SELECT_PHOTO = 1;
    Animation animBlink;
    public static final String MY_PREFS_NAMET = "MyPrefsFile";
    public static final String KEY_Firstname1 = "name";
    SharedPreferences sp;
    public static final String  Url="url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);

        Intent i = getIntent();
        username = i.getStringExtra(Graphical.KEY_USERNAME);
        username = i.getStringExtra(Grid.KEY_USERNAME);
        username = i.getStringExtra(Test.KEY_USERNAME);
        username = i.getStringExtra(Edit.KEY_USERNAME);
        username = i.getStringExtra(Chart.KEY_USERNAME);
        username = i.getStringExtra(ReceivedD.KEY_USERNAME);
        username = i.getStringExtra(SentD.KEY_USERNAME);
        username = i.getStringExtra(UpgradeStage.KEY_USERNAME);
        username = i.getStringExtra(Bitcoin.KEY_USERNAME);
        username = i.getStringExtra(Main2Activity.KEY_USERNAME1);
        Firstname = i.getStringExtra(Main2Activity.KEY_Firstname);
        Firstname = i.getStringExtra(MainActivity.KEY_Firstname1);
        username = i.getStringExtra(MainActivity.KEY_USERNAME);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAMET, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_Firstname1, Firstname);
        editor.commit();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
//        Intent intent = getIntent();
        username = i.getStringExtra(Login.KEY_USERNAME);
        tv = (TextView) findViewById(R.id.id);
//        tv_name = (TextView) findViewById(R.id.navusername);
//        tv_id = (TextView) findViewById(R.id.navuserid);

        stage = (TextView) findViewById(R.id.stage);
        tv1 = (TextView) findViewById(R.id.username);
        sent = (TextView) findViewById(R.id.sent);
        received = (TextView) findViewById(R.id.received);
        tv_joined = (TextView) findViewById(R.id.joined);
        vi = (TextView) findViewById(R.id.clik);
        tv_link = (TextView) findViewById(R.id.link);
        iv = (CircleImageView) findViewById(R.id.photo);

        tv.setText(username);
//        tv_id.setText(username);
//        tv_name.setText(Firstname);
//        BarChart barChart = (BarChart) findViewById(R.id.chart);
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));
//
//        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
//
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//        BarData data = new BarData(labels, dataset);
        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
//        barChart.setData(data);
//        barChart.animateY(5000);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
//                if (menuItem.getItemId() == R.id.nav_item_dash) {
//                    Intent intent = getIntent();
//                    String username = intent.getStringExtra(Login.KEY_USERNAME);
//                    Intent i = new Intent(Test.this, Test.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(Test.this, Edit.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent i = new Intent(Test.this, Chart.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_sd) {
                    Intent i = new Intent(Test.this, SentD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(Test.this, ReceivedD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(Test.this, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("GO", false);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(Test.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_Grid) {
                    Intent i = new Intent(Test.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(Test.this, MainActivity.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(Test.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(Test.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(Test.this, TotalMembers.class);
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
        tv_sponsor = (TextView) findViewById(R.id.sponsorname);
        tv_email = (TextView) findViewById(R.id.email);
        tv_whatsup = (TextView) findViewById(R.id.whatsup);
        tv_mobile = (TextView) findViewById(R.id.mobile);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vi.performClick();
                getData();
                getData1();
            }
        }, 000);
        tv_link.setText("kothuram.com/donatefund/index.php/login/register/" + username);
//        tv_id.setText(username);
//        tv_name.setText(Firstname);
    }

    public void link(View view) {
//        Uri uri = Uri.parse("http://kothuram.com/sapfund/index.php/login/register/" + username);
        String uri="http://kothuram.com/donatefund/index.php/login/register/" + username;
        Intent intent = new Intent(Test.this,Register.class);
        intent.putExtra(Url,uri);
        startActivity(intent);
    }

    public void vi(View view) {
    }

    private void getData() {

//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/dashboard.php?format=json&username=" + username;
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
                        Toast.makeText(Test.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        Firstname = "";
        Entrylevel = "";
        String fund_received = "";
        String fund_sent = "";
        String Created_Date = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            Firstname = collegeData.getString("Firstname");
            Entrylevel = collegeData.getString("Entrylevel");
            fund_received = collegeData.getString("fund_received");
            fund_sent = collegeData.getString("fund_sent");
            Created_Date = collegeData.getString("Created_Date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
        tv1.setText(Firstname);
        stage.setText(Entrylevel);
        received.setText(fund_received);
        sent.setText(fund_sent);
        tv_joined.setText(Created_Date);
    }

    public void bitcoin(View view) {
        Intent intent = new Intent(Test.this, Post.class);
        intent.putExtra(KEY_USERNAME, username);
        startActivity(intent);

    }

    public void upgrade(View view) {
        Intent intent = new Intent(Test.this, UpgradeStage.class);
        intent.putExtra(KEY_USERNAME, username);
        intent.putExtra(KEY_LEVEL, Entrylevel);
        intent.putExtra(KEY_Firstname, Firstname);
        startActivity(intent);

    }

    private void getData1() {

//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/upgrade.php?format=json&ibo=" + username + "&level=" + Entrylevel;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
//                loading.dismiss();
                showJSON1(response1);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Test.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON1(String response1) {
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
            JSONObject jsonObject = new JSONObject(response1);
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
        tv_sponsor.setText(sFirstname);
        tv_email.setText(sEmail);
        tv_whatsup.setText(sWhatsup);
        tv_mobile.setText(sPhone);
    }


}
