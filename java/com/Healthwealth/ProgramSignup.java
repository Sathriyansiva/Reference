package com.Healthwealth;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.Healthwealth.Login.MY_PREFS_NAME1;

public class ProgramSignup extends AppCompatActivity {
    private String TAG = ProgramSignup.class.getSimpleName();
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    AlertDialog.Builder alertDialogBuilder;
    public static final String KEY_USERNAME = "username";
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;
    String emailpersonname = null;
    //    public static final String KEY_Firstname = "name";
    public static final String KEY_Firstname1 = "name";
    public static final String KEY_Firstname2 = "name";


    GoogleApiClient mGoogleApiClient;
    ExpandableListAdapter listAdapter1;
    List<String> listDataHeader;
    List<String> Id;
    HashMap<String, List<String>> listDataChild;
    List<String> Category1;
    List<String> Category2;
    List<String> Category3;
    List<String> Category4;
    ListView lv;
    ListView lidi;
    ArrayList<HashMap<String, String>> contactList1;
    private int lastExpandedPosition = -1;
    private String KEY_position;
    Dialog dialog;
    Integer[] imageId = {R.drawable.weitdet, R.drawable.hel};
    int Position;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_signup);

        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);
        emailpersonname = sp.getString(Config.KEY_Emailuname, " ");
//        getData();
        listDataHeader = new ArrayList<String>();
        Id = new ArrayList<String>();

        listDataChild = new HashMap<String, List<String>>();
        Category1 = new ArrayList<String>();
        Category2 = new ArrayList<String>();
        Category3 = new ArrayList<String>();
        Category4 = new ArrayList<String>();
//        Intent i = getIntent();
//        EmailORfb = i.getStringExtra(EmORfb);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        lv = (ListView) findViewById(R.id.sublist1);
        contactList1 = new ArrayList<>();
        new GetContacts1().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                new GetContacts(position + 1).execute();
//                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramSignup.this);
//                builder.setTitle(listDataHeader.get(position));
//                String[] stockArr = new String[listDataHeader.size()];
//                stockArr = listDataHeader.toArray(stockArr);
//                for(String s : stockArr)
//                    System.out.println(s);
//                final String[] finalStockArr = stockArr;
//                builder.setItems(stockArr, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        tv_choose.setText(finalStockArr[which]);
////                        new GetContacts(which+1).execute();
//                    }
//                });
//                builder.show();
                Position = position;
                dialog = new Dialog(ProgramSignup.this);
                dialog.setContentView(R.layout.dialistexp);
                dialog.setTitle(listDataHeader.get(position));
                lidi = (ListView) dialog.findViewById(R.id.list);
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.list_expitem, new String[]{"id"
//                }, new int[]{R.id.lblListItem,
//                });
//                lidi.setAdapter(adapter);
                new GetContacts(position + 1).execute();


                dialog.show();
            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        printHashKey();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                getData();
            }

        }, 000);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAMEm, Context.MODE_PRIVATE).edit();
//        editor.putString(KEY_USERNAME, username);
//        editor.putString(KEY_Firstname1, Firstname);
//        editor.commit();
//        Toast.makeText(this,username, Toast.LENGTH_SHORT).show();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
//                if (menuItem.getItemId() == R.id.nav_item_dash) {
//                    Intent i = new Intent(ProgramSignup.this, Test.class);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(ProgramSignup.this, Edit.class);
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
//                if (menuItem.getItemId() == R.id.nav_item_sd) {
////                    Intent i = new Intent(ProgramSignup.this, SentD.class);
////
////                    startActivity(i);
////                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_rd) {
////                    Intent i = new Intent(ProgramSignup.this, ReceivedD.class);
////
////                    startActivity(i);
////                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    try {
//                            if (emailpersonname.equals(" ")) {
                        LoginManager.getInstance().logOut();
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                        editor.putString(Config.Ibo_SHARED_PREF, "");
                        editor.commit();
                        Intent i = new Intent(ProgramSignup.this, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                            }
                        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                new ResultCallback<Status>() {
                                    @Override
                                    public void onResult(Status status) {
                                        Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                                    }
                                });
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(ProgramSignup.this, Graphical.class);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


                }
//                if (menuItem.getItemId() == R.id.item_Grid) {
////                    Intent i = new Intent(ProgramSignup.this, Grid.class);
////
////                    startActivity(i);
////                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_home) {
//                    Intent i = new Intent(ProgramSignup.this, MainActivity.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_subs) {
//                    Intent i = new Intent(ProgramSignup.this, Subscribe.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(ProgramSignup.this, Group.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_stats) {
                    Intent i = new Intent(ProgramSignup.this, Stats.class);
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
        expListView = (ExpandableListView) findViewById(R.id.sublist);

//        prepareListData();

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                if (lastExpandedPosition != -1
//                        && groupPosition != lastExpandedPosition) {
//                    expListView.collapseGroup(lastExpandedPosition);
//                }
//                lastExpandedPosition = groupPosition;
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });
    }
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            expListView.setIndicatorBounds(450, 500);
//        } else {
//            expListView.setIndicatorBoundsRelative(450, 500);
//        }
//    }
    private void prepareListData() {

        listDataChild = new HashMap<String, List<String>>();
        new GetContacts(1).execute();
        new GetContacts0(2).execute();
        new GetContacts2(3).execute();
        new GetContacts3(4).execute();
        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

//        for(int i=0;i<=listDataHeader.size();i++){
//            new GetContacts(i+1).execute();
//            listDataChild.put(listDataHeader.get(i), Category1);
//            listAdapter1 = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//            expListView.setAdapter(listAdapter1);
//        }
        listDataChild.put(listDataHeader.get(0), Category1);
        listDataChild.put(listDataHeader.get(1), Category2);
        listDataChild.put(listDataHeader.get(2), Category3);
        listDataChild.put(listDataHeader.get(3), Category4);

        listAdapter1 = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter1);

    }

//    public void iv(View view) {
//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.containerView, new Main2Activity()).commit();
//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAMEm, Context.MODE_PRIVATE).edit();
//        editor.putString(KEY_USERNAME, username);
//        editor.putString(KEY_Firstname1, Firstname);
//        editor.commit();
//    }

    private void getData() {
//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/dashboard.php?format=json&ibo=" + username;
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
                        Toast.makeText(ProgramSignup.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void showJSON(String response) {
        EmailORfb = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            EmailORfb = collegeData.getString("Username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
    }

    public void shared() {
        SharedPreferences sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);
        username = sp.getString(KEY_USERNAME, null);
        Firstname = sp.getString(KEY_Firstname2, null);
    }
    public void edit(View view) {
        Intent i = new Intent(ProgramSignup.this, Edit.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public void task(View view) {
        Intent i = new Intent(ProgramSignup.this, ProgramSignup.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public void user(View view) {
        Intent i = new Intent(ProgramSignup.this, Stats.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramSignup.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void graph(View view) {
        Intent i = new Intent(ProgramSignup.this, Group.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramSignup.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void grid(View view) {
//        Intent i = new Intent(ProgramSignup.this, Grid.class);
//
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramSignup.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }
    public void sent(View view) {
//        Intent i = new Intent(ProgramSignup.this, SentD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramSignup.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }
    public void receive(View view) {
//        Intent i = new Intent(ProgramSignup.this, ReceivedD.class);
//
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramSignup.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }
    public void facebook(View view) {
        String appLinkUrl, previewImageUrl;
        appLinkUrl = "https://play.google.com/store/apps/details?id=com.imangi.templerun2";
        previewImageUrl = "http://2.bp.blogspot.com/-99shOruuadw/VQsG2T233sI/AAAAAAAAEi0/noFTxUBh_rg/s1600/appscripts.png";
        AppInviteContent content = new AppInviteContent.Builder()
                .setApplinkUrl(appLinkUrl)
                .setPreviewImageUrl(previewImageUrl)
                .build();
        AppInviteDialog.show(ProgramSignup.this, content);
    }
    public void printHashKey() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.mareesoftpc.fbinvites",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void next(View view) {
        Intent i = new Intent(ProgramSignup.this, ProgramPage.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    private class GetContacts1 extends AsyncTask<Void, Void, Void> {

        //        int increment;
//        public GetContacts1(int increment){
//            this.increment = increment;
//        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/cat_list.php?format=json");
//                String jsonStr1 = sh.makeServiceCall("https://dulcet-nucleus-168208.appspot.com/?format=json");
                Log.e(TAG, "Response from url: " + jsonStr1);

                if (jsonStr1 != null) {
                    try {
                        listDataHeader.clear();
                        Id.clear();
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String category = c.getString("category");
                            String id = c.getString("id");
                            HashMap<String, String> contact = new HashMap<>();
                            contact.put("id", category);
                            contactList1.add(contact);
                            Id.add(id);
                            listDataHeader.add(category);

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

            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                prepareListData();
//                Expadpt1 ListAdapter = new Expadpt1(ProgramSignup.this,
//                        listDataHeader,
//                        imageId
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
                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Category1.clear();
//                contactList1.clear();
                HttpHandler sh = new HttpHandler();
//                String jsonStr = sh.makeServiceCall("https://dulcet-nucleus-168208.appspot.com?format=json&id=" + increment);
                String jsonStr = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/cat_list.php?format=json&id=" + increment);
                Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj1 = new JSONObject(jsonStr);

                        JSONArray contacts1 = jsonObj1.getJSONArray("posts");
                        for (int j = 0; j < contacts1.length(); j++) {

                            JSONObject c1 = contacts1.getJSONObject(j);
                            String prod_name = c1.getString("prod_name");

                            Category1.add(prod_name);
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
//                                Toast.makeText(getApplicationContext(),
//                                        "Json parsing error: " + e.getMessage(),
//                                        Toast.LENGTH_LONG)
//                                        .show();
                            }
                        });

                    }
                } else

                {
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

            } catch (
                    Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.expadp, new String[]{"id"
//                }, new int[]{R.id.lblListItem,
//                });
//                lv.setAdapter(adapter);
//                Expadapt ListAdapter = new Expadapt(ProgramSignup.this,
//                        Category1
//                );
//                lv.setAdapter(ListAdapter);
//                listAdapter1 = new ExpandableListAdapter(ProgramSignup.this, listDataHeader, listDataChild);
//                expListView.setAdapter(listAdapter1);
                Expadapt ListAdapter = new Expadapt(ProgramSignup.this,
                        Category1,
                        Id,
                        Position
                );
                lidi.setAdapter(ListAdapter);
                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetContacts0 extends AsyncTask<Void, Void, Void> {
        int increment;

        public GetContacts0(int increment) {
            this.increment = increment;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Category2.clear();
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
                            Category2.add(prod_name);
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

            } catch (
                    Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            try {

                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    private class GetContacts2 extends AsyncTask<Void, Void, Void> {
        int increment;

        public GetContacts2(int increment) {
            this.increment = increment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Category3.clear();
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
                            Category3.add(prod_name);
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

            } catch (
                    Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {

                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class GetContacts3 extends AsyncTask<Void, Void, Void> {
        int increment;

        public GetContacts3(int increment) {
            this.increment = increment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Category4.clear();
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
                            Category4.add(prod_name);
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

            } catch (
                    Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {

                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void logout(View view) {
        try {
            sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            username = sp.getString(Config.Ibo_SHARED_PREF, null);
            EmailORfb = sp.getString(Config.EMAILORFBID, null);
            emailpersonname = sp.getString(Config.KEY_Emailuname, " ");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProgramSignup.this);
            alertDialog.setTitle(EmailORfb);
            alertDialog.setMessage("Are you sure you want to Logout?");
            alertDialog.setIcon(R.drawable.close);
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
//                            if (emailpersonname.equals(" ")) {
                                LoginManager.getInstance().logOut();
                                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                                editor.putString(Config.Ibo_SHARED_PREF, "");
                                editor.commit();
                                Intent i = new Intent(ProgramSignup.this, Login.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                            }
                                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                        new ResultCallback<Status>() {
                                            @Override
                                            public void onResult(Status status) {
                                                Intent i = new Intent(getApplicationContext(), Login.class);
                                                startActivity(i);
                                            }
                                        });
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(ProgramSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
