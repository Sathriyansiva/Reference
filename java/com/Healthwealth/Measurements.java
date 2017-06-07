package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Measurements extends AppCompatActivity {
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
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);

        Firstname = sp.getString(Config.Name_SHARED_PREF, null);
        barChart = (BarChart) findViewById(R.id.chart);
        barChart1 = (BarChart) findViewById(R.id.chart1);
        entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
         dataset = new BarDataSet(entries, "# of Calls");
        labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
         data = new BarData(labels, dataset);
         dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setData(data);
        barChart.animateY(5000);
        entries1 = new ArrayList<>();
        entries1.add(new BarEntry(4f, 0));
        entries1.add(new BarEntry(8f, 1));
        entries1.add(new BarEntry(6f, 2));
        entries1.add(new BarEntry(12f, 3));
        entries1.add(new BarEntry(18f, 4));
        entries1.add(new BarEntry(9f, 5));
        dataset1 = new BarDataSet(entries, "# of Calls");
        labels1 = new ArrayList<String>();
        labels1.add("January");
        labels1.add("February");
        labels1.add("March");
        labels1.add("April");
        labels1.add("May");
        labels1.add("June");
        data1 =new BarData(labels1, dataset1);
         dataset1.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart1.setData(data1);
        barChart1.animateY(5000);
    }
    public void edit(View view) {
        Intent i = new Intent(Measurements.this, ProgramSignup.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void user(View view) {
        Intent i = new Intent(Measurements.this, Stats.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void graph(View view) {
        Intent i = new Intent(Measurements.this, Group.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void grid(View view) {
//        Intent i = new Intent(Measurements.this, Grid.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
//        Intent i = new Intent(Measurements.this, SentD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void receive(View view) {
//        Intent i = new Intent(Measurements.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }
    public void AddMeasrmnt(View view) {
        Intent i = new Intent(Measurements.this, AddMesrmnt.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(Measurements.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }
    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void home(View view) {
        Intent i=new Intent(Measurements.this,ProgramSignup.class);
        startActivity(i);
    }
}
