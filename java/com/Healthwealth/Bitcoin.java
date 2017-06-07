package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Bitcoin extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    String username;
    String bitcoinaddress1, website;
    TextView tv_wltweb;
    EditText tv_bitcoin;
    String[] walletwebsite = {"Blockchain"};
    int walletwebsitevalue;
    String Firstname;
    public static final String KEY_Firstname = "name";
    public static final String KEY_USERNAME = "username";
    //    public static final String LOGIN_URL = "http://www.sapfund.com/sapfund/panel/android/bitcoin.php?username=" + username;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);
        SharedPreferences sp1 = this.getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        username = sp1.getString(Config.Ibo_SHARED_PREF,null);
        Firstname = sp1.getString(Config.Name_SHARED_PREF,null);


        tv_wltweb = (TextView) findViewById(R.id.wlletwebsit);
        tv_bitcoin = (EditText) findViewById(R.id.bitcoinaddress);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_dash) {
                    Intent i = new Intent(Bitcoin.this, Test.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    Intent i = new Intent(Bitcoin.this, Edit.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
//                if (menuItem.getItemId() == R.id.nav_item_profile) {
//                    Intent i = new Intent(Bitcoin.this, Chart.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname,Firstname);
//
//                    startActivity(i);
//                }
                if (menuItem.getItemId() == R.id.nav_item_sd) {
                    Intent i = new Intent(Bitcoin.this, SentD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_rd) {
                    Intent i = new Intent(Bitcoin.this, ReceivedD.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent i = new Intent(Bitcoin.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_graphical) {
                    Intent i = new Intent(Bitcoin.this, Graphical.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.item_Grid) {

                    Intent i = new Intent(Bitcoin.this, Grid.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);

                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    Intent i = new Intent(Bitcoin.this, MainActivity.class);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_subs) {
                    Intent i = new Intent(Bitcoin.this, Subscribe.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }
                if (menuItem.getItemId() == R.id.nav_item_chart) {
                    Intent i = new Intent(Bitcoin.this, Chart.class);
                    String username = sp.getString(KEY_USERNAME, null);
                    i.putExtra(KEY_USERNAME, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(Bitcoin.this, TotalMembers.class);
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

    }

    public void website(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select The Wallet Website");
        builder.setItems(walletwebsite, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_wltweb.setText(walletwebsite[which]);
                walletwebsitevalue = which + 1;
            }
        });
        builder.show();
    }

    public void submit(View view) {
        try {
            bitcoinaddress1 = tv_bitcoin.getText().toString().trim();
            website = tv_wltweb.getText().toString().trim();
            if (bitcoinaddress1.length() != 34) {
//            Toast.makeText(this, "Invalid Bitcoin Address", Toast.LENGTH_SHORT).show();
                tv_wltweb.setError("Invalid Bitcoin Address");
            }
            register(website, bitcoinaddress1);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void register(String website, String bitcoinaddress1) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Bitcoin.this, "Please Wait", null, true, true);
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
                data.put("coin_link", params[0]);
                data.put("coin_address", params[1]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/bitcoin.php?username=" + username, data);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(website, bitcoinaddress1);
    }
}
