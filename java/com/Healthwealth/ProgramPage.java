package com.Healthwealth;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.List;

public class ProgramPage extends AppCompatActivity implements Ppadapt.CallbackInterface {
    Spinner spinner;
    private String TAG = ProgramPage.class.getSimpleName();
    List<String> listDataHeader;
    List<String> Id;
    List<String> Times;
    List<String> Intervals;
    List<String> Points;
    ArrayAdapter<String> dataAdapter;
    List<String> Prods;
    ListView lv;
    TextView tv_choose, tv_points, tv_dt;
    private String[] amountType = {"ADMINISTRATOR", "IT TEAM", "MANAGEMENT"};
    private int amountTypeValue;
    private Ppadapt ListAdapter;
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;
    String posid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_page);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);

        Firstname = sp.getString(Config.Name_SHARED_PREF, null);
        Id = new ArrayList<String>();
        Points = new ArrayList<String>();
        Times = new ArrayList<String>();
        Intervals = new ArrayList<String>();
        new GetContacts1().execute();
        new GetContacts(1).execute();
        getData(1);

        listDataHeader = new ArrayList<String>();
        Prods = new ArrayList<String>();
        lv = (ListView) findViewById(R.id.ltvw);
        spinner = (Spinner) findViewById(R.id.spin);
        tv_choose = (TextView) findViewById(R.id.choose);
        tv_points = (TextView) findViewById(R.id.pts);
        tv_dt = (TextView) findViewById(R.id.dt);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                new GetContacts(position + 1).execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        tv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramPage.this);
                builder.setTitle("Select The Programe");
//                builder.setIcon(R.drawable.icon);

//                AlertDialog alertDialogObject = builder.create();
//                ListView listView=alertDialogObject.getListView();
//                listView.setDivider(new ColorDrawable(Color.BLUE)); // set color
//                listView.setDividerHeight(2);
//                Resources resources = alertDialogObject.getContext().getResources();
//                int alertTitleId = resources.getIdentifier("alertTitle", "id", "android");
//                TextView alertTitle = (TextView) alertDialogObject.getWindow().getDecorView().findViewById(alertTitleId);
//                alertTitle.setTextColor(Color.MAGENTA); // change title text colo
//                int titleDividerId = resources.getIdentifier("titleDivider", "id", "android");
//                View titleDivider = alertDialogObject.getWindow().getDecorView().findViewById(titleDividerId);
//                titleDivider.setBackgroundColor(Color.YELLOW);

                String[] stockArr = new String[listDataHeader.size()];
                stockArr = listDataHeader.toArray(stockArr);
                for (String s : stockArr)
                    System.out.println(s);
                final String[] finalStockArr = stockArr;
                builder.setItems(stockArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_choose.setText(finalStockArr[which]);
                        amountTypeValue = which + 1;
                        new GetContacts(which + 1).execute();
                        getData(which + 1);
                        posid = Integer.toString(which).trim();
                    }
                });

//                alertDialogObject.show();
                builder.show();
            }
        });
    }

    public void edit(View view) {
        Intent i = new Intent(ProgramPage.this, ProgramSignup.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public void user(View view) {
        Intent i = new Intent(ProgramPage.this, Stats.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void graph(View view) {
        Intent i = new Intent(ProgramPage.this, Group.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void grid(View view) {
//        Intent i = new Intent(ProgramPage.this, Grid.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
        Intent i = new Intent(ProgramPage.this, Edit.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void receive(View view) {
//        Intent i = new Intent(ProgramPage.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private class GetContacts1 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/cat_list.php?format=json");

                Log.e(TAG, "Response from url: " + jsonStr1);
                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String category = c.getString("category");
                            String id = c.getString("id");
                            String points = c.getString("points");
//                            HashMap<String, String> contact = new HashMap<>();
//                            contact.put("id", category);
//                            contactList1.add(contact);

                            Id.add(id);
                            listDataHeader.add(category);
                            Points.add(points);

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
                Toast.makeText(ProgramPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                dataAdapter = new ArrayAdapter<String>(ProgramPage.this, android.R.layout.simple_spinner_item, listDataHeader);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
//                prepareListData();
//                Expadpt1 ListAdapter = new Expadpt1(ProgramPage.this,
//                        listDataHeader
//                );
//                lv.setAdapter(ListAdapter);
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.gridlist, new String[]{"id"
//                }, new int[]{R.id.grid_ibo,
//                });
//                lv.setAdapter(adapter);
//                listAdapter1 = new ExpandableListAdapter(ProgramSignup.this, listDataHeader, listDataChild);
//                expListView.setAdapter(listAdapter1);
                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ProgramPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
                Prods.clear();
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/cat_list.php?format=json&id=" + increment);
                Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj1 = new JSONObject(jsonStr);

                        JSONArray contacts1 = jsonObj1.getJSONArray("posts");
                        for (int j = 0; j < contacts1.length(); j++) {

                            JSONObject c1 = contacts1.getJSONObject(j);
                            String prod_name = c1.getString("prod_name");
                            String fromdate = c1.getString("fromdate");
                            String todate = c1.getString("todate");
                            String interval = c1.getString("interval");
                            String time = c1.getString("time");
                            Prods.add(prod_name);
                            Times.add(time);
                            Intervals.add(interval);
//                                listDataChild.put(listDataHeader.get(j), Category1);

//                            HashMap<String, String> contact = new HashMap<>();
//                            contact.put("id", prod_name);
//                            contactList1.add(contact);
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
                } else

                {
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

            } catch (
                    Exception e)

            {
                e.printStackTrace();
                Toast.makeText(ProgramPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.list_expitem, new String[]{"id"
//                }, new int[]{R.id.lblListItem,
//                });
//                lv.setAdapter(adapter);
//                prepareListData();
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.expadp, new String[]{"id"
//                }, new int[]{R.id.lblListItem,
//                });
//                lv.setAdapter(adapter);
                ListAdapter = new Ppadapt(ProgramPage.this,
                        Prods,
                        Times,
                        Intervals,
                        posid,
                        username,
                        EmailORfb
                );
                lv.setAdapter(ListAdapter);
//                listAdapter1 = new ExpandableListAdapter(ProgramSignup.this, listDataHeader, listDataChild);
//                expListView.setAdapter(listAdapter1);
                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ProgramPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getData(int id) {

//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/cat_points.php?format=json&id=" + id;
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
                        Toast.makeText(ProgramPage.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        String points = "";
        String stdt = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            points = collegeData.getString("points");
            stdt = collegeData.getString("date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_points.setText(points);
        tv_dt.setText(stdt);
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)
                ListAdapter.onSelectFromGalleryResult(data);
            else if (requestCode == 0)
                ListAdapter.onCaptureImageResult(data);
        }
    }

    public void home(View view) {
        Intent i = new Intent(ProgramPage.this, ProgramSignup.class);
        startActivity(i);
    }
}
