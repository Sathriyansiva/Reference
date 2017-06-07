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
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity {

    public static final String KEY_USERNAME = "username";
    String Firstname;
    public static final String KEY_Firstname = "name";
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    String username;
    public static final String MY_PREFS_NAMET = "MyPrefsFile";
    public static final String KEY_Firstname1 = "name";
    SharedPreferences sp;
    private String TAG = MainActivity.class.getSimpleName();
    List<Integer> arrayOfInts;

    ArrayList<String> weight1 = new ArrayList<String>();
    ArrayList<String> bmi1 = new ArrayList<String>();
    BarChart barChart;
    ArrayList<BarEntry> entries;
    BarData data;
    BarDataSet dataset;
    ArrayList<String> labels;
    BarChart barChart1;
    ArrayList<BarEntry> entries1;
    BarData data1;
    BarDataSet dataset1;
    ArrayList<String> labels1;
    String weight;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        tv = (TextView) findViewById(R.id.tv);
        SharedPreferences sp1 = this.getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        username = sp1.getString(Config.Ibo_SHARED_PREF,null);
        Firstname = sp1.getString(Config.Name_SHARED_PREF,null);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        barChart = (BarChart) findViewById(R.id.chart);
        barChart1 = (BarChart) findViewById(R.id.chart1);

//    entries = new ArrayList<>();
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));
//         dataset = new BarDataSet(entries, "# of Calls");
//        labels = new ArrayList<String>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//         data = new BarData(labels, dataset);
//        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
//        barChart.setData(data);
//        barChart.animateY(5000);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_dash) {
                    Intent intent = getIntent();
                    String username = intent.getStringExtra(Login.KEY_USERNAME);
                    Intent i = new Intent(Chart.this, Test.class);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(Chart.this, Edit.class);
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
                    Intent i = new Intent(Chart.this, SentD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(Chart.this, ReceivedD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(Chart.this, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("GO", false);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(Chart.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_Grid) {
                    Intent i = new Intent(Chart.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(Chart.this, MainActivity.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(Chart.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(Chart.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(Chart.this, TotalMembers.class);
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
        new GetContacts().execute();

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
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/graph.php?format=json&ibo="+username);
                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            weight = c.getString("weight");
                            String bmi=c.getString("bmi");
                            weight1.add(weight);
                            bmi1.add(bmi);
//                        Firstname = c.getString("Firstname");

                        }
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
                Toast.makeText(Chart.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                super.onPostExecute(result);
                //                tv.setText(weight1.get(0));
//                for(int i=0;i<weight2.length();i++) {
//                   a[i] = Integer.valueOf(weight2[i]);
//
//                }
               int a = Integer.valueOf(weight1.get(5));
                int b = Integer.valueOf(weight1.get(4));
                int c = Integer.valueOf(weight1.get(3));
                int d = Integer.valueOf(weight1.get(2));
                int e = Integer.valueOf(weight1.get(1));
                int f = Integer.valueOf(weight1.get(0));

                entries = new ArrayList<>();
                entries.add(new BarEntry(a ,0));
                entries.add(new BarEntry(b, 1));
                entries.add(new BarEntry(c, 2));
                entries.add(new BarEntry(d, 3));
                entries.add(new BarEntry(e, 4));
                entries.add(new BarEntry(f, 5));
                labels = new ArrayList<String>();
                labels.add("1");
                labels.add("2");
                labels.add("3");
                labels.add("4");
                labels.add("5");
                labels.add("6");
                dataset = new BarDataSet(entries, "# of Calls");
                data = new BarData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                barChart.setData(data);
                barChart.animateY(5000);
                Float a1 = Float.valueOf(bmi1.get(5));
                Float b1 = Float.valueOf(bmi1.get(4));
                Float c1 = Float.valueOf(bmi1.get(3));
                Float d1 = Float.valueOf(bmi1.get(2));
                Float e1 = Float.valueOf(bmi1.get(1));
                Float f1 = Float.valueOf(bmi1.get(0));

                entries1 = new ArrayList<>();
                entries1.add(new BarEntry(a1 ,0));
                entries1.add(new BarEntry(b1, 1));
                entries1.add(new BarEntry(c1, 2));
                entries1.add(new BarEntry(d1, 3));
                entries1.add(new BarEntry(e1, 4));
                entries1.add(new BarEntry(f1, 5));
                labels1 = new ArrayList<String>();
                labels1.add("Sun");
                labels1.add("Mon");
                labels1.add("Tue");
                labels1.add("Wed");
                labels1.add("The");
                labels1.add("Fri");
                dataset1 = new BarDataSet(entries1, "# of Calls");
                data1 = new BarData(labels1, dataset1);
                dataset1.setColors(ColorTemplate.COLORFUL_COLORS);

//                dataset1.setColor(Color.rgb(0, 155, 0));

                barChart1.setData(data1);
                barChart1.animateY(5000);
//
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Chart.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
