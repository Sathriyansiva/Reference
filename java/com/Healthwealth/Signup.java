package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    EditText ed_urnm,ed_em, ed_pass, ed_repass, ed_spoid;
    ImageView iv_urnm, iv_pass, iv_repass, iv_em, iv_spoid;
    String usrnm, usrem, usrpass, usrrepass, usrrefid;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.chatTheme);
        setContentView(R.layout.activity_signup);
        ed_urnm = (EditText) findViewById(R.id.ed_user);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        ed_repass = (EditText) findViewById(R.id.ed_confpass);
        ed_em = (EditText) findViewById(R.id.ed_email);
        ed_spoid = (EditText) findViewById(R.id.ed_refid);
        iv_urnm = (ImageView) findViewById(R.id.iv_usrgr);
        iv_pass = (ImageView) findViewById(R.id.iv_lkgr);
        iv_repass = (ImageView) findViewById(R.id.iv_lkgr1);
        iv_em = (ImageView) findViewById(R.id.iv_gmgr);
        iv_spoid = (ImageView) findViewById(R.id.iv_refgr);
        ed_urnm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_urnm.setImageResource(R.drawable.avatargr);
                } else {
                    iv_urnm.setImageResource(R.drawable.avatarbl);
                }
            }
        });
        ed_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_pass.setImageResource(R.drawable.lock);
                } else {
                    iv_pass.setImageResource(R.drawable.lockbl);
                }

            }
        });
        ed_repass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_repass.setImageResource(R.drawable.lock);
                } else {
                    iv_repass.setImageResource(R.drawable.lockbl);
                }
            }
        });
        ed_em.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_em.setImageResource(R.drawable.emgr);
                } else {
                    iv_em.setImageResource(R.drawable.embl);
                }

            }
        });
        ed_spoid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_spoid.setImageResource(R.drawable.avatargr);
                } else {
                    iv_spoid.setImageResource(R.drawable.avatarbl);
                }
            }
        });
    }

    public void alredyaccnt(View view) {
        startActivity(new Intent(Signup.this, Login.class));
    }

    public void reg(View view) {
        usrnm = ed_urnm.getText().toString().trim();
        usrem = ed_em.getText().toString().trim();
        usrpass = ed_pass.getText().toString().trim();
        usrrepass = ed_repass.getText().toString().trim();
        usrrefid = ed_spoid.getText().toString().trim();
        if ( usrem.equals("") || usrpass.equals("") || !usrrepass.matches(usrpass) || usrrefid.equals("")) {
            if (usrnm.equals("")) {
                ed_urnm.setError("Please Enter username");
            }
             if (usrem.equals("")) {
                ed_em.setError("Please Enter email");
            } else if (!usrem.matches(emailPattern)) {
                ed_em.setError("Please Check your email");
            } else if (usrpass.equals("")) {
                ed_pass.setError("Please Enter password");
            } else if (usrrefid.equals("")) {
                ed_spoid.setError("Please Enter referenceid");
            } else if (!usrpass.equals(usrrepass)) {
                ed_repass.setError("Password mismatched");
            }
        } else {
            register(usrnm,usrem, usrpass, usrrefid);
            ed_em.setText(" ");
            ed_pass.setText(" ");
            ed_repass.setText(" ");
            ed_spoid.setText(" ");
        }
    }


    private void register(String usrnm,String usrem, String usrpass, String usrrefid) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Signup.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("Username", params[0]);
                data.put("Email", params[1]);
                data.put("Password", params[2]);
                data.put("Sponsor", params[3]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/register.php", data);
                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(usrnm,usrem, usrpass, usrrefid);
    }
    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void home(View view) {
        Intent i=new Intent(Signup.this,ProgramSignup.class);
        startActivity(i);
    }
}
