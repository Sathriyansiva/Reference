package com.Healthwealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Mareesoftpc on 5/4/2017.
 */

public class Ppadapt extends BaseAdapter {
    public static final String KEY_PROGNM1 = "proname";
    private String UPLOAD_URL = "http://kothuram.com/donatefund/uploadnew.php";
    Bitmap bm = null;
    private String KEY_IMAGE = "image";
    private Uri filePath;
    private Uri mImageCaptureUri;
    private String path;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ImageView imageView;
    EditText et_cmnts;
    Context context;


    public interface CallbackInterface {


    }

    List<String> Prods;
    List<String> Times;
    List<String> Intervals;
    String username;
    String EmailORfb;
    String id;

    public Ppadapt(
            Context context2,
            List<String> Prods,
            List<String> Times,
            List<String> Intervals,
            String id,
            String username,
            String EmailORfb

    ) {

        this.context = context2;
        this.Prods = Prods;
        this.Times = Times;
        this.Intervals = Intervals;
        this.username = username;
        this.EmailORfb = EmailORfb;
        this.id = id;


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Prods.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(final int position, View child, ViewGroup parent) {

        final Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.programpageadp, null);

            holder = new Holder();
            holder.textview_topic = (TextView) child.findViewById(R.id.prodname);
            holder.textView_time = (TextView) child.findViewById(R.id.tv_time);
            holder.textView_dur = (TextView) child.findViewById(R.id.tv_dur);
            holder.chk_pop = (CheckBox) child.findViewById(R.id.ckpop);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textview_topic.setText(Prods.get(position));
        holder.textView_time.setText(Times.get(position));
//        id=Id.get(position);
        holder.textView_dur.setText("Duration " + "\n" + Intervals.get(position) + " days");
        holder.chk_pop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.pop);
                    dialog.setTitle("Post Comment");
                    et_cmnts = (EditText) dialog.findViewById(R.id.comments);
                    Button bt_post = (Button) dialog.findViewById(R.id.postdata);
                    Button bt_seltimg = (Button) dialog.findViewById(R.id.selectimage);
                    imageView = (ImageView) dialog.findViewById(R.id.ivImage);
                    bt_seltimg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectImage();
                        }
                    });
                    bt_post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String data = et_cmnts.getText().toString().trim();
                            uploadMultipart();
                            et_cmnts.setText("");
//                            Intent i=new Intent(context,Comment.class);
//                            context.startActivity(i);
                        }
                    });
                    dialog.show();
                }
            }
        });
        return child;
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(context);

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
        ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
        ((Activity) context).startActivityForResult(intent, REQUEST_CAMERA);
    }

    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == SELECT_FILE)
//                onSelectFromGalleryResult(data);
//            else if (requestCode == REQUEST_CAMERA)
//                onCaptureImageResult(data);
//        }
//    }
    public void onCaptureImageResult(Intent data) {
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
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @SuppressWarnings("deprecation")
    public void onSelectFromGalleryResult(Intent data) {
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

    public void uploadMultipart() {
        String data = et_cmnts.getText().toString().trim();
        String taskno = " ";
        String day = " ";
        String pro_id = " ";

//        path = getPath(filePath);
//        String con=filePath.toString().trim();
        if (imageView.getDrawable() == null) {
            register(username, data, EmailORfb, id);
//            new GetContacts().execute();
        } else {
            Cursor cursor = context.getContentResolver().query(filePath, null, null, null, null);
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = context.getContentResolver().query(
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
            cursor.moveToFirst();
            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            try {

                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(path, "image")
                        .addParameter("name", EmailORfb)
                        .addParameter("ibo", username)
                        .addParameter("taskno", taskno)
                        .addParameter("day", day)
                        .addParameter("prod_id", id)
                        .addParameter("message", data)

                        //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
                Toast.makeText(context, "Posted", Toast.LENGTH_SHORT).show();
                imageView.setImageDrawable(null);
//                new GetContacts().execute();
            } catch (Exception exc) {
                Toast.makeText(context, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void register(String username, String data, String EmailORfb, String id) {
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
                data1.put("message", params[1]);
                data1.put("username", params[2]);
                data1.put("prod_id", params[3]);

                String result = ruc.sendPostRequest("http://kothuram.com/donatefund/panel/android/insertcomment.php", data1);

                return result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(username, data, EmailORfb, id);
    }

    public class Holder {
        TextView textview_topic, textView_time, textView_dur;
        CheckBox chk_pop;

    }
}
