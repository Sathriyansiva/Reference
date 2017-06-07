package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Stats extends AppCompatActivity {
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);

        Firstname = sp.getString(Config.Name_SHARED_PREF, null);
    }
    public void edit(View view) {
        Intent i = new Intent(Stats.this, ProgramSignup.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Stats.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void user(View view) {
//        Intent i = new Intent(Stats.this, Stats.class);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        startActivity(i);
    }
    public void graph(View view) {
        Intent i = new Intent(Stats.this, Group.class);

        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public void grid(View view) {
//        Intent i = new Intent(Stats.this, Grid.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Stats.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
        Intent i = new Intent(Stats.this, Edit.class);

        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void receive(View view) {
//        Intent i = new Intent(Stats.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Stats.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }
    public void Measrmnt(View view) {
        Intent i = new Intent(Stats.this, Measurements.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Stats.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void home(View view) {
        Intent i=new Intent(Stats.this,ProgramSignup.class);
        startActivity(i);
    }
}
