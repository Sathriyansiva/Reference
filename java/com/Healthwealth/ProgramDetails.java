package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.Healthwealth.ExpandableListAdapter.KEY_PROGNM1;
import static com.Healthwealth.ExpandableListAdapter.KEY_PROID;

public class ProgramDetails extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
    ViewPagerEx.OnPageChangeListener{

        //    DrawerLayout mDrawerLayout;
//    NavigationView mNavigationView;
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;
    public static final String KEY_USERNAME = "username";

    //    public static final String KEY_Firstname = "name";
    public static final String KEY_Firstname1 = "name";
    public static final String KEY_Firstname2 = "name";


    GoogleApiClient mGoogleApiClient;
    LinearLayout ly;
    String progname;
    String proid;
    TextView tv_descrtn, tv_dur, tv_benfts, tv_tesmnil, tv_pics, tv_startfin, tv_pts;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);

        Firstname = sp.getString(Config.Name_SHARED_PREF, null);
        tv_descrtn = (TextView) findViewById(R.id.tv_decp);
        tv_dur = (TextView) findViewById(R.id.tv_dur);
        tv_benfts = (TextView) findViewById(R.id.tv_benft);
        tv_tesmnil = (TextView) findViewById(R.id.tv_temnl);
        tv_pics = (TextView) findViewById(R.id.pics);
        tv_startfin = (TextView) findViewById(R.id.tv_stdt);
        tv_pts = (TextView) findViewById(R.id.tv_pnts);


        FacebookSdk.sdkInitialize(getApplicationContext());
        ly = (LinearLayout) findViewById(R.id.linlyt);
        Intent i = getIntent();
        progname = i.getStringExtra(KEY_PROGNM1);
        proid=i.getStringExtra(KEY_PROID);
        getData();




        mViewPager = (ViewPager) findViewById(R.id.pager);
        CustomPagerAdapter  mCustomPagerAdapter = new CustomPagerAdapter(this);
        mViewPager.setAdapter(mCustomPagerAdapter);
        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps.put("Health", "http://kothuram.com/donatefund/sliderimage/slider1.png");
        Hash_file_maps.put("Weight Detection", "http://kothuram.com/donatefund/sliderimage/slider2.png");
        Hash_file_maps.put("Fitness", "http://kothuram.com/donatefund/sliderimage/slider3.jpg");
        Hash_file_maps.put("Food", "http://kothuram.com/donatefund/sliderimage/slider4.jpg");
        for(String name : Hash_file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(ProgramDetails.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
//        Toast.makeText(this, progname, Toast.LENGTH_SHORT).show();
//        if (progname.equals("0")){
//            ly.setBackgroundResource(R.drawable.weightlogo);
//        }
//        if (progname.equals("1")){
//            ly.setBackgroundResource(R.drawable.h);
//        }
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                mDrawerLayout.closeDrawers();
//                if (menuItem.getItemId() == R.id.nav_item_dash) {
//                    Intent i = new Intent(ProgramDetails.this, Test.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                if (menuItem.getItemId() == R.id.nav_item_edit) {
//                    Intent i = new Intent(ProgramDetails.this, Edit.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
////                if (menuItem.getItemId() == R.id.nav_item_profile) {
////                    Intent i = new Intent(Test.this, Chart.class);
////                    i.putExtra(KEY_USERNAME, username);
////                    i.putExtra(KEY_Firstname, Firstname);
////
////                    startActivity(i);
////                }
//                if (menuItem.getItemId() == R.id.nav_item_sd) {
//                    Intent i = new Intent(ProgramDetails.this, SentD.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_rd) {
//                    Intent i = new Intent(ProgramDetails.this, ReceivedD.class);
//                    i.putExtra(KEY_USERNAME, username);
//
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_logout) {
//                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                            new ResultCallback<Status>() {
//                                @Override
//                                public void onResult(Status status) {
//                                    Intent i=new Intent(getApplicationContext(),Login.class);
//                                    startActivity(i);
//                                }
//                            });
//                    LoginManager.getInstance().logOut();
//                    SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
//
//                    editor.putString(Config.Ibo_SHARED_PREF, "");
//                    editor.commit();
//                    Intent i = new Intent(ProgramDetails.this, Login.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.item_graphical) {
//                    Intent i = new Intent(ProgramDetails.this, Graphical.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//
//                }
//                if (menuItem.getItemId() == R.id.item_Grid) {
//                    Intent i = new Intent(ProgramDetails.this, Grid.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
//                    Intent i = new Intent(ProgramDetails.this, Subscribe.class);
//
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                if (menuItem.getItemId() == R.id.nav_item_chart) {
//                    Intent i = new Intent(ProgramDetails.this, Chart.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                if (menuItem.getItemId() == R.id.nav_item_chat) {
//                    Intent i = new Intent(ProgramDetails.this, TotalMembers.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname1, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                return false;
//            }
//
//        });
//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
//                R.string.app_name);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        mDrawerToggle.syncState();

    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
    public void edit(View view) {
        Intent i = new Intent(ProgramDetails.this, ProgramSignup.class);

//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void user(View view) {
        Intent i = new Intent(ProgramDetails.this, Stats.class);

//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void graph(View view) {
        Intent i = new Intent(ProgramDetails.this, Group.class);

//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        startActivity(i);
    }

    public void grid(View view) {
//        Intent i = new Intent(ProgramDetails.this, Grid.class);
//
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void sent(View view) {
//        Intent i = new Intent(ProgramDetails.this, SentD.class);
//
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
    }

    public void receive(View view) {
//        Intent i = new Intent(ProgramDetails.this, ReceivedD.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramDetails.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
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
        AppInviteDialog.show(ProgramDetails.this, content);
    }

    private void getData() {

//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/prog_details.php?format=json&id=" + progname+"&prod_id="+proid;
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
                        Toast.makeText(ProgramDetails.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void showJSON(String response) {
        String prod_desc = " ";
        Integer duration = null;
        String benifits = " ";
        String fromdate = " ";
        String todate = " ";
        String testimonial = " ";
        String points = " ";
        String prod_image = " ";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            prod_desc = collegeData.getString("prod_desc");
            duration = collegeData.getInt("duration");
            benifits = collegeData.getString("benifits");
            fromdate = collegeData.getString("fromdate");
            todate = collegeData.getString("todate");
            testimonial = collegeData.getString("testimonial");
            points = collegeData.getString("points");
            prod_image = collegeData.getString("prod_image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
        tv_descrtn.setText("Description:\t"+prod_desc);
        tv_dur.setText("Duration:\t"+Integer.toString(duration));
        tv_benfts.setText("Benifits:\t"+benifits);
        tv_tesmnil.setText("Testimonial:\t"+testimonial);
//        tv_pics.setText();
        tv_startfin.setText("StartDate:\t"+fromdate + "\nFinishDate:\t" + todate);
        tv_pts.setText("TotalPoints:\t"+points);
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

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void home(View view) {
        Intent i=new Intent(ProgramDetails.this,ProgramSignup.class);
        startActivity(i);
    }
}

