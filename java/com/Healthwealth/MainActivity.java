package com.Healthwealth;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
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

import static com.Healthwealth.Login.MY_PREFS_NAME1;

public class MainActivity extends AppCompatActivity {
    //    DataFromActivityToFragment dataFromActivityToFragment;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    public static final String KEY_USERNAME = "username";
    String username;
    public static final String MY_PREFS_NAMEm = "MyPrefsFile";
    Animation animBlink;
    TextView tv_jm;
    String Firstname;
    //    public static final String KEY_Firstname = "name";
    public static final String KEY_Firstname1 = "name";
    public static final String KEY_Firstname2 = "name";
    TextView iv;
    SharedPreferences sp;
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);
        username = sp.getString(KEY_USERNAME, null);
        FacebookSdk.sdkInitialize(getApplicationContext());
        printHashKey();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                iv.performClick();
//                iv = (TextView) findViewById(R.id.iv);
//                iv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mFragmentTransaction = mFragmentManager.beginTransaction();
//                        mFragmentTransaction.replace(R.id.containerView, new Main2Activity()).commit();
//                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAMEm, Context.MODE_PRIVATE).edit();
//                        editor.putString(KEY_USERNAME, username);
//                        editor.putString(KEY_Firstname1, Firstname);
//                        editor.commit();
//                    }
//                });
//                iv.performClick();
                getData();
            }
        }, 000);
        Intent i = getIntent();

        username = i.getStringExtra(Login.KEY_USERNAME);
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
        username = i.getStringExtra(MainActivity.KEY_USERNAME);
        Firstname = i.getStringExtra(Grid.KEY_Firstname);
        Firstname = i.getStringExtra(Test.KEY_Firstname);
        Firstname = i.getStringExtra(Edit.KEY_Firstname);
        Firstname = i.getStringExtra(ReceivedD.KEY_Firstname);
        Firstname = i.getStringExtra(SentD.KEY_Firstname);
        Firstname = i.getStringExtra(UpgradeStage.KEY_Firstname);

        Firstname = i.getStringExtra(MainActivity.KEY_Firstname1);
        username = i.getStringExtra(Graphical.KEY_USERNAME);
        Firstname = i.getStringExtra(Login.KEY_Firstname2);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAMEm, Context.MODE_PRIVATE).edit();
//        editor.putString(KEY_USERNAME, username);
//        editor.putString(KEY_Firstname1, Firstname);
//        editor.commit();
//        Toast.makeText(this,username, Toast.LENGTH_SHORT).show();
        tv_jm = (TextView) findViewById(R.id.jm);
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        tv_jm.setVisibility(View.VISIBLE);
        tv_jm.startAnimation(animBlink);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_dash) {
                    Intent i = new Intent(MainActivity.this, Test.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(MainActivity.this, Edit.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);

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
                    Intent i = new Intent(MainActivity.this, SentD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(MainActivity.this, ReceivedD.class);
                    i.putExtra(KEY_USERNAME, username);

                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {
                                    // ...
                                    Intent i=new Intent(getApplicationContext(),Login.class);
                                    startActivity(i);
                                }
                            });
                    LoginManager.getInstance().logOut();
                    Intent i = new Intent(MainActivity.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(MainActivity.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


                }
                if (menuItem.getItemId() == R.id.item_Grid) {
                    Intent i = new Intent(MainActivity.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(MainActivity.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(MainActivity.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(MainActivity.this, TotalMembers.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname1, Firstname);
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
                        Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        Firstname = "";
        String fund_received = "";
        String fund_sent = "";
        String Created_Date = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            Firstname = collegeData.getString("Firstname");

            fund_received = collegeData.getString("fund_received");
            fund_sent = collegeData.getString("fund_sent");
            Created_Date = collegeData.getString("Created_Date");
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
        Intent i = new Intent(MainActivity.this, Edit.class);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname1, Firstname);
        Bundle bundle = ActivityOptions.makeCustomAnimation(MainActivity.this,R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }
    public void user(View view) {
        Intent i = new Intent(MainActivity.this, Test.class);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname1, Firstname);
        Bundle bundle = ActivityOptions.makeCustomAnimation(MainActivity.this,R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }
    public void graph(View view) {
        Intent i = new Intent(MainActivity.this, Graphical.class);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname1, Firstname);
        Bundle bundle = ActivityOptions.makeCustomAnimation(MainActivity.this,R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }
    public void grid(View view) {
        Intent i = new Intent(MainActivity.this, Grid.class);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname1, Firstname);
        Bundle bundle = ActivityOptions.makeCustomAnimation(MainActivity.this,R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }
    public void sent(View view) {
        Intent i = new Intent(MainActivity.this, SentD.class);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname1, Firstname);
        Bundle bundle = ActivityOptions.makeCustomAnimation(MainActivity.this,R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }
    public void receive(View view) {
        Intent i = new Intent(MainActivity.this, ReceivedD.class);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname1, Firstname);
        Bundle bundle = ActivityOptions.makeCustomAnimation(MainActivity.this,R.anim.push_left_in, R.anim.push_left_out).toBundle();
        startActivity(i, bundle);
    }
    public void facebook(View view) {
        String appLinkUrl, previewImageUrl;

        appLinkUrl = "https://play.google.com/store/apps/details?id=com.imangi.templerun2";
        previewImageUrl = "http://2.bp.blogspot.com/-99shOruuadw/VQsG2T233sI/AAAAAAAAEi0/noFTxUBh_rg/s1600/appscripts.png";

        AppInviteContent content = new AppInviteContent.Builder()
                .setApplinkUrl(appLinkUrl)
                .setPreviewImageUrl(previewImageUrl)
                .build();
        AppInviteDialog.show(MainActivity.this, content);
    }
    public void printHashKey(){

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

        } catch(NoSuchAlgorithmException e) {

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
}