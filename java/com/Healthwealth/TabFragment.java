package com.Healthwealth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {
    ImageView iv;
    Button bt_btcn, bt_upgrd,bt_upld;
    TextView tv_user, tv_id, tv_stage, tv_link, tv_joined, tv_dsent, tv_dreceive, tv_lastlogin;
    private final int SELECT_PHOTO = 1;
    Context context;
    private ProgressDialog loading;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.tab_layout, null);
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        iv = (ImageView) view.findViewById(R.id.photo);
        bt_btcn = (Button) view.findViewById(R.id.bitcoin);
        bt_upgrd = (Button) view.findViewById(R.id.upgradestage);
        tv_user = (TextView) view.findViewById(R.id.username);
        tv_id = (TextView) view.findViewById(R.id.uid);
        tv_stage = (TextView) view.findViewById(R.id.stag1);
        tv_link = (TextView) view.findViewById(R.id.link);
        tv_joined = (TextView) view.findViewById(R.id.joined1);
        tv_dsent = (TextView) view.findViewById(R.id.dsent);
        tv_dreceive = (TextView) view.findViewById(R.id.dreceive);
        tv_lastlogin = (TextView) view.findViewById(R.id.lastlogin);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setType("image/*");
//                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        bt_btcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(getActivity().getApplicationContext(), Bitcoin.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_upgrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(getActivity().getApplicationContext(), UpgradeStage.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void getData() {

        loading = ProgressDialog.show(getActivity().getApplicationContext(), "Please wait...", "Fetching...", false, false);

        String url = "http://www.Healthwealth.com/Healthwealth/panel/android/dashboard.php?format=json&username=" + tv_id.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String Firstname = "";
        String Entrylevel = "";
        String fund_received = "";
        String fund_sent = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("posts");
            JSONObject collegeData = result.getJSONObject(0);
            Firstname = collegeData.getString("Firstname");
            Entrylevel = collegeData.getString("Entrylevel");
            fund_received = collegeData.getString("fund_received");
            fund_sent = collegeData.getString("fund_sent");

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tv1.setText("Username:\t"+Firstname+"\nEntrylevel:\t" +Entrylevel+ "\nfund_received:\t"+ fund_received);
        tv_user.setText("Username:\t" + Firstname);
        tv_stage.setText(Entrylevel);
        tv_dreceive.setText(fund_received);
        tv_dsent.setText(fund_sent);
    }


//    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//
//        switch (requestCode) {
//            case SELECT_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    try {
//                        final Uri imageUri = imageReturnedIntent.getData();
//                        final InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
//                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                        iv.setImageBitmap(selectedImage);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//        }
//    }
//@Override
//public void onViewCreated(View view, Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);
//}
//    @Override
//    public void sendData(String data) {
//        if(data != null)
//            tv_user.setText(data);
//    }
}
