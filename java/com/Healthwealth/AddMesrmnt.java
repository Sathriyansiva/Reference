package com.Healthwealth;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class AddMesrmnt extends AppCompatActivity {
    private TextView dateView;
    DatePickerDialog datePickerDialog;
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb, dob, height, weight;
    EditText ed_weight, ed_height;
    CheckBox ck_male, ck_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mesrmnt);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);

        Firstname = sp.getString(Config.Name_SHARED_PREF, null);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        dateView = (TextView) findViewById(R.id.dateview);
        ed_weight = (EditText) findViewById(R.id.et_weight);
        ed_height = (EditText) findViewById(R.id.et_height);
        ck_male = (CheckBox) findViewById(R.id.ck_male);
        ck_female = (CheckBox) findViewById(R.id.ck_female);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                int mYear = c.get(java.util.Calendar.YEAR); // current year
                int mMonth = c.get(java.util.Calendar.MONTH); // current month
                int mDay = c.get(java.util.Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(AddMesrmnt.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateView.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ck_male.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ck_female.setChecked(false);
                ck_male.setChecked(true);
            }
        });
        ck_female.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ck_female.setChecked(true);
                ck_male.setChecked(false);
            }
        });
    }
    public void edit(View view) {
        Intent i = new Intent(AddMesrmnt.this, ProgramSignup.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void user(View view) {
        Intent i = new Intent(AddMesrmnt.this, Stats.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void graph(View view) {
        Intent i = new Intent(AddMesrmnt.this, Group.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void grid(View view) {
//        Intent i = new Intent(AddMesrmnt.this, Grid.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
//        Intent i = new Intent(AddMesrmnt.this, SentD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void receive(View view) {
//        Intent i = new Intent(AddMesrmnt.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void save(View view) {
//        Intent i = new Intent(AddMesrmnt.this, Measurements.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(AddMesrmnt.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);

        height = ed_height.getText().toString().trim();
        weight = ed_weight.getText().toString().trim();
        dob = dateView.getText().toString().trim();

        if ( height.equals("") || weight.equals("")|| dob.equals("")) {
            if (dob.equals("")) {
                dateView.setError("Please Enter DOB");
            }
            else if (weight.equals("")) {
                ed_weight.setError("Please Enter Weight");
            } else if (height.equals("")) {
                ed_height.setError("Please Enter Height");
            }
        } else {
            register(username, height, weight, dob);
            ed_height.setText(" ");
            ed_weight.setText(" ");
//            dateView.setText(" ");
        }

    }

    private void register(String ibo, String height, String weight, String dob) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddMesrmnt.this, "Please Wait", null, true, true);
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
                data.put("ibo", params[0]);
                data.put("height", params[1]);
                data.put("weight", params[2]);
                data.put("dob", params[3]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/add_measurement.php", data);
                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(ibo, height, weight, dob);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void home(View view) {
        Intent i = new Intent(AddMesrmnt.this, ProgramSignup.class);
        startActivity(i);
    }
}
