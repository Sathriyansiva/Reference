package com.Healthwealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Chat extends AppCompatActivity {
    TextView tv_name;
    String name;
    Bitmap bm = null;
    private String KEY_IMAGE = "image";
    private Uri filePath;
    private Uri mImageCaptureUri;
    private String path;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ImageView imageView;
    EditText ed_msg;
    LinearLayout ll_llout;
    String ibo;
    SharedPreferences sp;
    String username;
    String EmailORfb;
    Button bt_postdata;
    private String UPLOAD_URL = "http://kothuram.com/donatefund/chatupload.php";
    ArrayList<String> messages = new ArrayList<String>();
    ArrayList<String> imagearray = new ArrayList<String>();
    ArrayList<String> Senderibo = new ArrayList<String>();
    ListView listView;
    private String TAG = Chat.class.getSimpleName();
    Chatadpt ListAdapter;
    Timer timer;
    String message,image,senderibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.chatTheme);
        setContentView(R.layout.activity_chat);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);
        imageView = (ImageView) findViewById(R.id.ivImage);
        tv_name = (TextView) findViewById(R.id.name);
        ed_msg = (EditText) findViewById(R.id.et_message);
        bt_postdata = (Button) findViewById(R.id.postdata);
        listView = (ListView) findViewById(R.id.ltvw);
        new GetContacts().execute();
        Intent i = getIntent();
        name = i.getStringExtra(TotalMembers.KEY_Firstname);
        ibo = i.getStringExtra(TotalMembers.KEY_Ibo);
        tv_name.setText(name);
        if (Integer.valueOf(ed_msg.getText().toString().trim().length()).equals(19)) {
            final ActionBar.LayoutParams lparams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100); // Width , height
            ed_msg.setLayoutParams(lparams);
        }
//        Referesh();

//        listView.post(new Runnable(){
//            public void run() {
//                listView.setSelection(listView.getCount() - 1);
//            }});
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                listView.smoothScrollBy(0, 0);
//                return false;
//            }
//        });

//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(10000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                new GetContacts().execute();
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//
//        t.start();
    }

    private void Referesh() {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        new GetContacts().execute();
//                        ListAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, 0, 10000);
    }

    public void selectimage(View view) {
        selectImage();
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Chat.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Chat.this);

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

    public void post(View view) {
        uploadMultipart();
        ed_msg.setText(" ");
        new GetContacts().execute();
//        register(username,data, ibo );
    }

    public void uploadMultipart() {
        String data = ed_msg.getText().toString().trim();
//        path = getPath(filePath);
//        String con=filePath.toString().trim();
        String taskno = " ";
        String day = " ";
        String pro_id = " ";

        if (imageView.getDrawable() == null) {
            register(username, data, ibo);

        } else {
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
//                        .addFileToUpload(path, "image")
//                        .addParameter("ibo", username)
//                        .addParameter("receiveribo", ibo)
//                        .addParameter("message", data)

                        .addFileToUpload(path, "image")
                        .addParameter("name", EmailORfb)
                        .addParameter("ibo", username)
                        .addParameter("receiveribo", ibo)
                        .addParameter("day", day)
                        .addParameter("prod_id", pro_id)
                        .addParameter("message", data)
                        //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//                Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show();

                imageView.setImageDrawable(null);
                this.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).rightMargin = 10;
                new GetContacts().execute();
            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void register(String username, String data, String ibo) {
        class RegisterUser extends AsyncTask<String, Void, String> {

            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ed_msg.setText(" ");
//                Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data1 = new HashMap<String, String>();
                data1.put("senderibo", params[0]);
                data1.put("message", params[1]);
                data1.put("receiveribo", params[2]);
                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/chatinsert.php", data1);

                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(username, data, ibo);
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
                Senderibo.clear();
                HttpHandler sh = new HttpHandler();
                String jsonStr2 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/getchatimages.php?format=json&ibo=" + username + "&receiveribo=" + ibo);
                Log.e(TAG, "Response from url: " + jsonStr2);
                if (jsonStr2 != null) {
                    try {
                        JSONObject todo = new JSONObject(jsonStr2);
                        JSONArray contacts2 = todo.getJSONArray("posts");
                        for (int i = 0; i < contacts2.length(); i++) {
                            JSONObject c2 = contacts2.getJSONObject(i);
                             message = c2.getString("message");
                             image = c2.getString("image");
                             senderibo = c2.getString("senderibo");
                            imagearray.add(image);
                            messages.add(message);
                            Senderibo.add(senderibo);
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListAdapter = new Chatadpt(Chat.this,
                                imagearray, messages, Senderibo, username
                        );
                        listView.invalidateViews();
                        listView.setAdapter(ListAdapter);
//                        ListAdapter.notifyDataSetChanged();
                    }
                });
            } catch (
                    Exception e)

            {
                e.printStackTrace();
//                Toast.makeText(SentD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
