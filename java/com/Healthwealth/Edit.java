package com.Healthwealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Mareesoftpc on 3/7/2017.
 */

public class Edit extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    public static final String KEY_USERNAME = "username";
    String username;
    String name, email, phone, whatsup, address;
    EditText tv_name, tv_email, tv_phone, tv_whatsup, tv_address;
    TextView ed_country, ref, usernm, userid, userstage;
    String Firstname;
    ImageView iv;
    private final int SELECT_PHOTO = 1;
    public static final String KEY_Firstname = "name";
    SharedPreferences sp;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    Bitmap bm = null;
    private String KEY_IMAGE = "image";
    private Uri filePath;
    private Uri mImageCaptureUri;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp1 = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp1.getString(Config.Ibo_SHARED_PREF, null);
        Firstname = sp1.getString(Config.Name_SHARED_PREF, null);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.edit_layout);
        ref = (TextView) findViewById(R.id.textView2);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                mDrawerLayout.closeDrawers();
//
//                if (menuItem.getItemId() == R.id.nav_item_dash) {
//
//                    Intent i = new Intent(Edit.this, Test.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_edit) {
////                    Intent i = new Intent(Edit.this, Edit.class);
//////                    i.putExtra(KEY_USERNAME, username);
////                    startActivity(i);
//                }
////                if (menuItem.getItemId() == R.id.nav_item_profile) {
////                    Intent i = new Intent(Edit.this, Chart.class);
////                    i.putExtra(KEY_USERNAME, username);
////                    i.putExtra(KEY_Firstname,Firstname);
////
////                    startActivity(i);
////                }
//                if (menuItem.getItemId() == R.id.nav_item_sd) {
//                    Intent i = new Intent(Edit.this, SentD.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_rd) {
//                    Intent i = new Intent(Edit.this, ReceivedD.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_logout) {
//                    Intent i = new Intent(Edit.this, Login.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//
//                }
//                if (menuItem.getItemId() == R.id.item_graphical) {
//                    Intent i = new Intent(Edit.this, Graphical.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.item_Grid) {
//
//                    Intent i = new Intent(Edit.this, Grid.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_home) {
//                    Intent i = new Intent(Edit.this, MainActivity.class);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_subs) {
//                    Intent i = new Intent(Edit.this, Subscribe.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//
//                }
//                if (menuItem.getItemId() == R.id.nav_item_chart) {
//                    Intent i = new Intent(Edit.this, Chart.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                if (menuItem.getItemId() == R.id.nav_item_chat) {
//                    Intent i = new Intent(Edit.this, TotalMembers.class);
//                    String username = sp.getString(KEY_USERNAME, null);
//                    i.putExtra(KEY_USERNAME, username);
//                    i.putExtra(KEY_Firstname, Firstname);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                }
//                return false;
//            }
//
//        });
//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();
        tv_name = (EditText) findViewById(R.id.name);
        tv_email = (EditText) findViewById(R.id.email);
        tv_phone = (EditText) findViewById(R.id.phone);
        tv_whatsup = (EditText) findViewById(R.id.whatsup);
        tv_address = (EditText) findViewById(R.id.address);
        ed_country = (TextView) findViewById(R.id.country);
        usernm = (TextView) findViewById(R.id.username);
        userid = (TextView) findViewById(R.id.id);
        userstage = (TextView) findViewById(R.id.stage);
       iv = (ImageView) findViewById(R.id.photo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ref.performClick();
                getData1();
            }
        }, 000);
    }

    public void refresh(View view) {

    }

    private void getData1() {

//        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = "http://kothuram.com/donatefund/panel/android/editprofile.php?format=json&ibo=" + username;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
//                loading.dismiss();
                showJSON1(response1);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON1(String response1) {
        String Firstname = "";
        String Email = "";
        String Phone = "";
        String Whatsup = "";
        String country = "";
        String Address1 = "";

        try {
            JSONObject jsonObject = new JSONObject(response1);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            Firstname = collegeData.getString("Firstname");
            Email = collegeData.getString("Email");
            Phone = collegeData.getString("Phone");
            Whatsup = collegeData.getString("Whatsup");
            country = collegeData.getString("country");

            Address1 = collegeData.getString("Address1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
        tv_name.setText(Firstname);
        tv_email.setText(Email);
        tv_phone.setText(Phone);
        tv_whatsup.setText(Whatsup);
        ed_country.setText(country);
        tv_address.setText(Address1);
        usernm.setText(Firstname);
        userid.setText(username);
//        userstage.setText();
    }

    public void update(View view) {
        name = tv_name.getText().toString().trim();
        email = tv_email.getText().toString().trim();
        phone = tv_phone.getText().toString().trim();
        whatsup = tv_whatsup.getText().toString().trim();
        address = tv_address.getText().toString().trim();
        register(name, email, phone, whatsup, address);
    }

    private void register(String name, String email, String phone, String whatsup, String address) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Edit.this, "Please Wait", null, true, true);
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
                data.put("Firstname", params[0]);
                data.put("Email", params[1]);
                data.put("Phone", params[2]);
                data.put("Whatsup", params[3]);
                data.put("Address1", params[4]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/updateprofile.php?ibo=" + username, data);

                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(name, email, phone, whatsup, address);
    }

    public void photo(View view) {
        selectImage();
        iv.setImageDrawable(null);
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Edit.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Edit.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        try {
            bm = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            filePath = getImageUri(getApplicationContext(), bm);

            File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
//          filePath=Uri.fromFile(new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg"));
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            iv.setImageBitmap(bm);
            this.iv.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
            ((ViewGroup.MarginLayoutParams) iv.getLayoutParams()).rightMargin = 10;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {
            try {
                filePath = data.getData();
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        iv.setImageBitmap(bm);
        this.iv.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        ((ViewGroup.MarginLayoutParams) iv.getLayoutParams()).rightMargin = 10;
    }



    public void edit(View view) {
        Intent i = new Intent(Edit.this, Edit.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void Task(View view) {
        Intent i = new Intent(Edit.this, ProgramSignup.class);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void user(View view) {
        Intent i = new Intent(Edit.this, Stats.class);
//        i.putExtra(KEY_USERNAME, username);
//        i.putExtra(KEY_Firstname1, Firstname);
//        Bundle bundle = ActivityOptions.makeCustomAnimation(ProgramPage.this, R.anim.push_left_in, R.anim.push_left_out).toBundle();
//        startActivity(i, bundle);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void graph(View view) {
//        Intent i = new Intent(Edit.this, Group.class);
//
//        startActivity(i);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void grid(View view) {
//        Intent i = new Intent(ProgramPage.this, Grid.class);
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
//    public void home(View view) {
//        Intent i=new Intent(Edit.this,ProgramSignup.class);
//        startActivity(i);
//    }
}