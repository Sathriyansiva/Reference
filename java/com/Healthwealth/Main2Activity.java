package com.Healthwealth;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.Healthwealth.MainActivity.KEY_Firstname2;
import static com.Healthwealth.MainActivity.KEY_USERNAME;
import static com.Healthwealth.MainActivity.MY_PREFS_NAMEm;

public class Main2Activity extends Fragment {
    LinearLayout lv_edit, lv_user, lv_sendtn, lv_rcvdtn, lv_graph, lv_grid;
    String username;
    public static final String KEY_USERNAME1 = "username";
    TextView tv;
    String Firstname;
    public static final String KEY_Firstname = "name";
    SharedPreferences sp1;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, null);
        sp1 = getActivity().getSharedPreferences(MY_PREFS_NAMEm, Context.MODE_PRIVATE);
        username =  sp1.getString(KEY_USERNAME, null);
        Firstname =  sp1.getString(KEY_Firstname2, null);
        lv_edit = (LinearLayout) view.findViewById(R.id.edit);
        lv_user = (LinearLayout) view.findViewById(R.id.user);
        lv_graph = (LinearLayout) view.findViewById(R.id.graph);
        lv_grid = (LinearLayout) view.findViewById(R.id.grid);
        lv_sendtn = (LinearLayout) view.findViewById(R.id.snddtn);
        lv_rcvdtn = (LinearLayout) view.findViewById(R.id.rcvdtn);
//        tv = (TextView) view.findViewById(R.id.c);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), Firstname, Toast.LENGTH_SHORT).show();
//            }
//        });
        lv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), Edit.class);
//                    String Firstname  = sp1.getString(KEY_Firstname1, null);
                    i.putExtra(KEY_USERNAME1, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(),R.anim.push_left_in, R.anim.push_left_out).toBundle();
                  startActivity(i, bundle);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
            }
        });
        lv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), Test.class);
//                    String Firstname  = sp1.getString(KEY_Firstname1, null);
                    i.putExtra(KEY_USERNAME1, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(),R.anim.push_left_in, R.anim.push_left_out).toBundle();
                    startActivity(i, bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), Graphical.class);

//                    String Firstname  = sp1.getString(KEY_Firstname1, null);
                    i.putExtra(KEY_USERNAME1, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(),R.anim.push_left_in, R.anim.push_left_out).toBundle();
                    startActivity(i, bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), Grid.class);
//                    String Firstname  = sp1.getString(KEY_Firstname1, null);
                    i.putExtra(KEY_USERNAME1, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(),R.anim.push_left_in, R.anim.push_left_out).toBundle();
                    startActivity(i, bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv_sendtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), SentD.class);
//                    String Firstname  = sp1.getString(KEY_Firstname1, null);
                    i.putExtra(KEY_USERNAME1, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(),R.anim.push_left_in, R.anim.push_left_out).toBundle();
                    startActivity(i, bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv_rcvdtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), ReceivedD.class);
//                    String Firstname  = sp1.getString(KEY_Firstname1, null);
                    i.putExtra(KEY_USERNAME1, username);
                    i.putExtra(KEY_Firstname, Firstname);
                    Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(),R.anim.push_left_in, R.anim.push_left_out).toBundle();
                    startActivity(i, bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        FacebookSdk.sdkInitialize(getApplicationContext());
        Button invite = (Button)view.findViewById(R.id.button);

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String appLinkUrl, previewImageUrl;

                appLinkUrl = "https://play.google.com/store/apps/details?id=com.imangi.templerun2";
                previewImageUrl = "http://2.bp.blogspot.com/-99shOruuadw/VQsG2T233sI/AAAAAAAAEi0/noFTxUBh_rg/s1600/appscripts.png";

                AppInviteContent content = new AppInviteContent.Builder()
                        .setApplinkUrl(appLinkUrl)
                        .setPreviewImageUrl(previewImageUrl)
                        .build();
                AppInviteDialog.show(getActivity(), content);
            }
        });
        return view;
    }
}
