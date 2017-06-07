package com.Healthwealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static com.Healthwealth.Login.MY_PREFS_NAME1;

public class Comment extends AppCompatActivity {
    public static final String KEY_USERNAME = "username";
    String Firstname;
    public static final String KEY_Firstname = "name";
    String username;
    public static final String MY_PREFS_NAMET = "MyPrefsFile";
    public static final String KEY_Firstname1 = "name";
    public static final String KEY_ProId = "proid";
    TextView tv_topic, tv_taskno, tv_taskname, tv_discription;
    SharedPreferences sp;
    private String TAG = MainActivity.class.getSimpleName();
    private int noOfBtns;
    private int noofdys;
    public int TOTAL_LIST_ITEMS;
    private int increment = 0;
    private TextView[] btns;
    private ImageView[] imgvw;
    ListView lv;
    String pro_id;
    String day;
    String taskno;
    Button bt_like;
    Button bt_comment;
    Button bt_image,refresh;
    Button bt_post;
    ListView listView;
    ImageView imageView;
    LinearLayout ll;
    EditText textview_comments;
    int imgResource;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ArrayList<String> messages = new ArrayList<String>();
    ArrayList<String> imagearray = new ArrayList<String>();
    ArrayList<String> namearray = new ArrayList<String>();
    ArrayList<String> iboarray = new ArrayList<String>();

    private String UPLOAD_URL = "http://kothuram.com/donatefund/uploadnew.php";
    Bitmap bm = null;
    private String KEY_IMAGE = "image";
    private Uri filePath;
    private Uri mImageCaptureUri;
    private String path;
    ArrayList<HashMap<String, String>> contactList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        SharedPreferences sp1 = this.getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        username = sp1.getString(Config.Ibo_SHARED_PREF,null);
        Intent i = getIntent();
        pro_id = i.getStringExtra(ToDoadapter.KEY_ProId);
        day = i.getStringExtra(ToDoadapter.KEY_Day);
        taskno = i.getStringExtra(ToDoadapter.KEY_Taskno);
        Firstname = i.getStringExtra(ToDoadapter.KEY_Firstname);
//        tv_topic = (TextView) findViewById(R.id.topic);
//        tv_taskname = (TextView) findViewById(R.id.taskname);
//        tv_taskno = (TextView) findViewById(R.id.taskno);
//        tv_discription = (TextView) findViewById(R.id.discription);
//        bt_like = (Button) findViewById(R.id.like);
//        bt_comment = (Button) findViewById(R.id.comment);
        refresh = (Button) findViewById(R.id.ref);
        bt_image = (Button) findViewById(R.id.selectimage);
        bt_post = (Button) findViewById(R.id.postdata);
        textview_comments = (EditText) findViewById(R.id.comments);
        imageView = (ImageView) findViewById(R.id.ivImage);
        ll = (LinearLayout) findViewById(R.id.cmnt);
        listView = (ListView) findViewById(R.id.list);
        contactList1 = new ArrayList<>();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetContacts().execute();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.performClick();
            }
        }, 000);
//        bt_like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgResource = R.drawable.like;
//                bt_like.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
//            }
//        });
//        bt_comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        bt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
//        bt_post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences sp = this.getSharedPreferences(MY_PREFS_NAME1,Context.MODE_PRIVATE);
//                String username = sp.getString(KEY_USERNAME, null);
//                String data = textview_comments.getText().toString().trim();
//                if (data.equals("")) {
//
//                    btns = new TextView[7];
//                    for (int i = 0; i < 1; i++) {
//                        btns[i] = new TextView(getApplicationContext());
//                        btns[i].setBackground(getResources().getDrawable(android.R.drawable.btn_default));
//                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//                        ll.addView(btns[i], lp);
//
//                    }
////                    textview_comments.setError("Please Type Comment");
//                } else {
//                    btns = new TextView[7];
//
//                    for (int i = 0; i < 1; i++) {
//                        btns[i] = new TextView(getApplicationContext());
//                        btns[i].setBackgroundColor(getResources().getColor(android.R.color.background_light));
//                        btns[i].setTextColor(getResources().getColor(android.R.color.black));
//                        btns[i].setText("Saranraj says : " + data);
//                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//                        ll.addView(btns[i], lp);
//                        textview_comments.setText("");
//                        register(name, email, phone, whatsup, address);
//                    }
//                }
//            }
//        });

    }

    public void post(View view) {
        SharedPreferences sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);
        final String username = sp.getString(KEY_USERNAME, null);
        String data = textview_comments.getText().toString().trim();
        SharedPreferences sp1 = this.getSharedPreferences(MY_PREFS_NAMET, Context.MODE_PRIVATE);
        String name = sp1.getString(KEY_Firstname1, null);
        uploadMultipart();
//        register(username, pro_id, taskno, day, data);
//            new GetContacts().execute();
        textview_comments.setText("");
        if (data.equals("")) {
//            btns = new TextView[7];
//            for (int i = 0; i < 1; i++) {
//                btns[i] = new TextView(getApplicationContext());
//                btns[i].setBackground(getResources().getDrawable(android.R.drawable.btn_default));
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//                ll.addView(btns[i], lp);
//
//            }
//                    textview_comments.setError("Please Type Comment");

        } else {
//            btns = new TextView[7];
//            for (int i = 0; i < 1; i++) {
//                btns[i] = new TextView(getApplicationContext());
//                btns[i].setBackgroundColor(getResources().getColor(android.R.color.background_light));
//                btns[i].setTextColor(getResources().getColor(android.R.color.black));
//                btns[i].setText(Firstname + " Says : " + data);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//                ll.addView(btns[i], lp);
//                textview_comments.setText("");
//            }

        }
    }
    public void uploadMultipart() {
        String data = textview_comments.getText().toString().trim();
//        path = getPath(filePath);
//        String con=filePath.toString().trim();
        if( imageView.getDrawable() == null){
            register(username, pro_id, taskno, day, data);
                        new GetContacts().execute();
        }
        else {
        Cursor cursor = getContentResolver().query(filePath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
         path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        try {

            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image")
                    .addParameter("name", Firstname)
                    .addParameter("ibo", username)
                    .addParameter("taskno", taskno)
                    .addParameter("day", day)
                    .addParameter("prod_id", pro_id)
                    .addParameter("message", data)

                    //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload
            Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show();
            imageView.setVisibility(View.GONE);
            new GetContacts().execute();
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
        }
    }

    public String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    public void back(View view) {
        Intent i = new Intent(Comment.this, Subscribe.class);
        SharedPreferences sp = this.getSharedPreferences(MY_PREFS_NAME1, Context.MODE_PRIVATE);
        String username = sp.getString(KEY_USERNAME, null);
        i.putExtra(KEY_USERNAME, username);
        i.putExtra(KEY_Firstname, Firstname);
        i.putExtra(KEY_ProId, pro_id);
        startActivity(i);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                imagearray.clear();
                messages.clear();
                namearray.clear();
                iboarray.clear();
                HttpHandler sh = new HttpHandler();
                String jsonStr2 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/getimages.php?format=json&day=" + day + "&taskno=" + taskno + "&prod_id=" + pro_id);
                Log.e(TAG, "Response from url: " + jsonStr2);
                if (jsonStr2 != null) {
                    try {
                        JSONObject todo = new JSONObject(jsonStr2);
                        JSONArray contacts2 = todo.getJSONArray("posts");
                        for (int i = 0; i < contacts2.length(); i++) {
                            JSONObject c2 = contacts2.getJSONObject(i);
                            String message = c2.getString("message");
                            String name = c2.getString("name");
                            String image = c2.getString("image");
                            String ibo = c2.getString("ibo");
                            imagearray.add(image);
                            messages.add(message);
                            namearray.add(name);
                            iboarray.add(ibo);
                            HashMap<String, String> contact = new HashMap<>();
                            contact.put("message", message);
                            contact.put("name", name);
                            contactList1.add(contact);
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
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
//                btns = new TextView[messages.size()];
//                for (int i = 0; i < messages.size(); i++) {
//                    btns[i] = new TextView(getApplicationContext());
//                    btns[i].setBackgroundColor(getResources().getColor(android.R.color.background_light));
//                    btns[i].setTextColor(getResources().getColor(android.R.color.black));
//                    btns[i].setText(namearray.get(i)+" Says : " + messages.get(i));
//                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//                    ll.addView(btns[i], lp);
//            }
//                ListAdapter adapter = new SimpleAdapter(
//                        Comment.this, contactList1,
//                        R.layout.commentadapter, new String[]{"name", "message"
//                }, new int[]{R.id.cmname, R.id.message
//                });
//                listView.setAdapter(adapter);
                CommentAdapter ListAdapter = new CommentAdapter(Comment.this,
                        username,
                        imagearray,
                        messages,
                        namearray,
                        pro_id,
                        Firstname,
                        iboarray

                );

                listView.setAdapter(ListAdapter);
            } catch (
                    Exception e)

            {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getData1() {
//        String url = "http://kothuram.com/donatefund/panel/android/tasklist.php?format=json&id="+pro_id+"&day="+day+"&taskno="+taskno;
        String url = "http://kothuram.com/donatefund/panel/android/tasklist.php?format=json&id=" + pro_id + "&day=" + day + "&taskno=" + taskno;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {

                showJSON1(response1);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Comment.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON1(String response1) {
        String prod_name = "";
        String taskno = "";
        String taskname = "";
        String topic = "";
        try {
            JSONObject jsonObject = new JSONObject(response1);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            prod_name = collegeData.getString("prod_name");
            taskno = collegeData.getString("taskno");
            taskname = collegeData.getString("taskname");
            topic = collegeData.getString("topic");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
        tv_topic.setText(prod_name);
        tv_taskno.setText("Task " + taskno);
        tv_taskname.setText(taskname);
        tv_discription.setText(topic);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Comment.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Comment.this);

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

    private void register(String username, String pro_id, String taskno, String day, String data) {
        class RegisterUser extends AsyncTask<String, Void, String> {

            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data1 = new HashMap<String, String>();
                data1.put("ibo", params[0]);
                data1.put("prod_id", params[1]);
                data1.put("taskno", params[2]);
                data1.put("day", params[3]);
                data1.put("message", params[4]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/insertcomment.php", data1);

                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(username, pro_id, taskno, day, data);
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
            filePath=getImageUri(getApplicationContext(),bm);

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

            imageView.setImageBitmap(bm);
            this.imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).rightMargin = 10;
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

        imageView.setImageBitmap(bm);
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).rightMargin = 10;
    }
//    public void async_post(){
//
//        String url = "http://search.twitter.com/search.json";
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("q", "androidquery");
//
//
//        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {
//
//            @Override
//            public void callback(String url, JSONObject json, AjaxStatus status) {
//
//                showResult(json);
//
//            }
//        });
//    }
}
