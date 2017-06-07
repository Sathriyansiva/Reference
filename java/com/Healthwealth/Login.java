package com.Healthwealth;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements

        GoogleApiClient.OnConnectionFailedListener {
    EditText ed_user, ed_pass;
    public static final String USER_NAME = "USERNAME";
    String username;
    String password;
    int ret;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SessionManagement session;
    String email;
    Animation animBlink;
    String Firstname;
    String yes = "success";
    public static final String LOGIN_URL = "http://kothuram.com/donatefund/panel/android/contact.php";
//    public static final String LOGIN_URL = "https://dulcet-nucleus-168208.appspot.com/?format=json&name=login";
    public static final String LOGIN_URLEMAIL = " http://kothuram.com/donatefund/panel/android/forgot.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_Password = "password";
    public static final String KEY_Email = "email";
    public static final String MY_PREFS_NAME1 = "MyPrefsFile";
    public static final String KEY_Firstname2 = "name";
    public static final String EmORfb = "emfb";
    ImageView iv1, iv_urnm, iv_pass;

    Button submit_btn, bt_signup, bt_go;
    LinearLayout ll_login, ll_gologin;
    private CallbackManager callbackManager;
    private TextView textView, tv_alredy, tv_forgt;
    String personName;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);

        }


        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException e) {
        }
    };
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;

    private SignInButton btnSignIn;
    LoginButton loginButton;
    private boolean loggedIn = false;
    private Boolean exit = false;
    String EmailORfb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.chatTheme);
        printHashKey();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);

            }
        };
        setContentView(R.layout.activity_login);
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        textView = (TextView) findViewById(R.id.textView);
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        bt_go = (Button) findViewById(R.id.go);
        bt_signup = (Button) findViewById(R.id.signup);
        tv_alredy = (TextView) findViewById(R.id.alredy);
        tv_forgt = (TextView) findViewById(R.id.forgt);
        ll_login = (LinearLayout) findViewById(R.id.lv_lgin);
        ll_gologin = (LinearLayout) findViewById(R.id.lv_golgin);
        iv_urnm = (ImageView) findViewById(R.id.iv_usrgr);
        iv_pass = (ImageView) findViewById(R.id.iv_gmgr);
        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_gologin.setVisibility(View.GONE);
                ll_login.setVisibility(View.VISIBLE);
            }
        });
        ed_pass.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    submit_btn.performClick();
                    return true;
                }
                return false;
            }
        });
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.package.ACTION_LOGOUT");
        iv1 = (ImageView) findViewById(R.id.imageView);
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.d("onReceive", "Logout in progress");
//                //At this point you should start the login activity and finish this one
//                finish();
//            }
//        }, intentFilter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv1.performClick();
//                getData();
            }
        }, 000);
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        ed_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
    }

    public void iv1(View v) {
//        getData();
    }

    public void login(View v) {

        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        try {
            EmailORfb = ed_user.getText().toString().trim();
            password = ed_pass.getText().toString().trim();
            if (EmailORfb.length() == 0||!EmailORfb.matches(emailPattern)) {
//                Toast.makeText(this, "Please Enter Your Username", Toast.LENGTH_SHORT).show();
                ed_user.setError("Please Enter Your EmailId");
                progressDialog.dismiss();

            } else if (password.length() == 0) {
//                Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                ed_pass.setError("Please Enter Your Password");
                progressDialog.dismiss();
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("success")) {

                                    getData1( EmailORfb);
                                    Intent intent = new Intent(Login.this, ProgramSignup.class);
                                    SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                    editor.putString(Config.EMAILORFBID, EmailORfb);
                                    editor.putString(Config.Name_SHARED_PREF, Firstname);
                                    editor.commit();
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                    ed_user.setText("");
                                    ed_pass.setText("");
                                } else {
                                    progressDialog.dismiss();
                                    ed_pass.setError("Password Not Matched");

//                                    Toast.makeText(Login.this, "Username Password Not Matched", Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(KEY_USERNAME, EmailORfb);
                        map.put(KEY_Password, password);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
            Toast.makeText(this, "Check Network Connection", Toast.LENGTH_SHORT).show();
        }
    }
    public void forgot(View view) {
//        String HALLOWEEN_ORANGE = "#FF7F27";
            final Dialog dialog = new Dialog(Login.this);
        dialog.setTitle("Forgot Password / Username");
            dialog.setContentView(R.layout.forgot);
//        dialog.setTitleColor(HALLOWEEN_ORANGE);
//        dialog.setDividerColor(HALLOWEEN_ORANGE);
        TextView tologin = (TextView) dialog.findViewById(R.id.tologin);
        Button submit = (Button) dialog.findViewById(R.id.submit);
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URLEMAIL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                EditText ed_email = (EditText) dialog.findViewById(R.id.email);
                                email = ed_email.getText().toString().trim();
                                if (email.length() == 0 && !email.matches(emailPattern)) {
                                    ed_email.setError("Invalid email address");
                                } else if (response.trim().equals("success")) {
                                    Toast.makeText(Login.this, "You Have Received Your Username&Password to Your Mailid", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Login.this, "Your Email-Id not Matched", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Login.this, "Check Network Connection", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(KEY_Email, email);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);
            }
        });
        dialog.show();
    }
    private void displayMessage(Profile profile) {
//        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();

        if (profile != null) {
            textView.setText(profile.getName());
            EmailORfb = profile.getName().trim();
            EmailORfb = EmailORfb.replace(" ", "");
//            Toast.makeText(this, EmailORfb, Toast.LENGTH_SHORT).show();
            getData(EmailORfb);
            register(EmailORfb);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }
    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
    public void session() {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if (loggedIn) {
            Intent intent = new Intent(Login.this, ProgramSignup.class);
            startActivity(intent);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    public void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.Healthwealth", PackageManager.GET_SIGNATURES);
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
             personName = acct.getDisplayName();
            personName = personName.replace(" ", "");
            String personPhotoUrl = acct.getPhotoUrl().toString();
            EmailORfb = acct.getEmail();
            EmailORfb = EmailORfb.replace(" ", "");
//            Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
            getData(EmailORfb);
            register(EmailORfb);

        }
    }
    @Override
    public void onStart() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        mGoogleApiClient.connect();
//        super.onStart();
        super.onStart();
        session();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.

            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void create(View view) {
        ll_gologin.setVisibility(View.VISIBLE);
        ll_login.setVisibility(View.GONE);
    }

    public void google(View view) {
        signIn();
    }
    public void faceingtn(View view) {
        loginButton.performClick();
//        getData();
    }
    public void signup(View view) {
        Intent i = (new Intent(Login.this, Signup.class));
        startActivity(i);
    }
    //    @Override
//    public void onBackPressed() {
//        if (exit) {
//            finish(); // finish activity
//        } else {
//            Toast.makeText(this, "Press Back again to Exit.",
//                    Toast.LENGTH_SHORT).show();
//            exit = true;
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exit = false;
//                }
//            }, 3 * 1000);
//
//        }
//
//    }
    private void getData1( String EmailORfb) {

//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "\n" +
                "http://kothuram.com/donatefund/panel/android/getibo.php?format=json&Email="+EmailORfb;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                loading.dismiss();
                showJSON1(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON1(String response) {
        username = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            username = collegeData.getString("IBO");
            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.Ibo_SHARED_PREF, username);
//            editor.putString(Config.Name_SHARED_PREF, Firstname);
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
    }
    private void getData( String EmailORfb) {
//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/fb_ibo.php?format=json&Username="+EmailORfb;
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
                        Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response) {
        username = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            username = collegeData.getString("IBO");
            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.Ibo_SHARED_PREF, username);
//            editor.putString(Config.Name_SHARED_PREF, Firstname);
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
    }
    private void register(String usrnm) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
//                SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
//                editor.putString(Config.Ibo_SHARED_PREF, username);
//                editor.putString(Config.Name_SHARED_PREF, Firstname);
//                editor.commit();
                Intent intent = new Intent(Login.this, ProgramSignup.class);
                SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Config.EMAILORFBID,EmailORfb);
                editor.putString(Config.KEY_Emailuname,personName);
                editor.commit();
//                intent.putExtra(EmORfb, EmailORfb);
                startActivity(intent);
//            progressDialog.dismiss();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("Username", params[0]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/fb_register.php", data);
                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(usrnm);
    }
}

