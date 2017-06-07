package com.Healthwealth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Group extends AppCompatActivity {
    private String TAG = ProgramPage.class.getSimpleName();
    List<String> Names;
    List<String> Message;
    ListView lv;
    TextView tv_choose;
    List<String> listDataHeader;
    ArrayList<String> imagearray = new ArrayList<String>();
    ArrayList<String> iboarray = new ArrayList<String>();
    SharedPreferences sp;
    String username;
    String Firstname;
    String EmailORfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        sp = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        username = sp.getString(Config.Ibo_SHARED_PREF, null);
        EmailORfb = sp.getString(Config.EMAILORFBID, null);

        Firstname = sp.getString(Config.Name_SHARED_PREF, null);
        lv = (ListView) findViewById(R.id.ltvw);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listDataHeader = new ArrayList<String>();
        tv_choose = (TextView) findViewById(R.id.choose);
        Names = new ArrayList<String>();
        new GetContacts1().execute();
        Message = new ArrayList<String>();
        new GetContacts(0).execute();
        tv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Group.this);
                builder.setTitle("Select The Programe");
//                builder.setIcon(R.drawable.icon);

//                AlertDialog alertDialogObject = builder.create();
//                ListView listView=alertDialogObject.getListView();
//                listView.setDivider(new ColorDrawable(Color.BLUE)); // set color
//                listView.setDividerHeight(2);
//                Resources resources = alertDialogObject.getContext().getResources();
//                int alertTitleId = resources.getIdentifier("alertTitle", "id", "android");
//                TextView alertTitle = (TextView) alertDialogObject.getWindow().getDecorView().findViewById(alertTitleId);
//                alertTitle.setTextColor(Color.MAGENTA); // change title text colo
//                int titleDividerId = resources.getIdentifier("titleDivider", "id", "android");
//                View titleDivider = alertDialogObject.getWindow().getDecorView().findViewById(titleDividerId);
//                titleDivider.setBackgroundColor(Color.YELLOW);

                String[] stockArr = new String[listDataHeader.size()];
                stockArr = listDataHeader.toArray(stockArr);
                for (String s : stockArr)
                    System.out.println(s);
                final String[] finalStockArr = stockArr;
                builder.setItems(stockArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_choose.setText(finalStockArr[which]);
                        new GetContacts(which).execute();
                    }
                });

//                alertDialogObject.show();
                builder.show();
            }
        });
    }

    public void edit(View view) {
        Intent i = new Intent(Group.this, ProgramSignup.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void user(View view) {
        Intent i = new Intent(Group.this, Stats.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void graph(View view) {
//        Intent i = new Intent(Group.this, Group.class);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        startActivity(i);
    }

    public void grid(View view) {
//        Intent i = new Intent(Group.this, Grid.class);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        startActivity(i);
    }

    public void sent(View view) {
        Intent i = new Intent(Group.this, Edit.class);

        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void receive(View view) {
//        Intent i = new Intent(Group.this, ReceivedD.class);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        startActivity(i);
    }

    public void members(View view) {
    Intent i=new Intent(Group.this ,TotalMembers.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        int increment;

        public GetContacts(int increment) {
            this.increment = increment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Names.clear();
                Message.clear();
                iboarray.clear();
                iboarray.clear();
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/getimages.php?format=json&prod_id=" + increment);
                Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj1 = new JSONObject(jsonStr);

                        JSONArray contacts1 = jsonObj1.getJSONArray("posts");
                        for (int j = 0; j < contacts1.length(); j++) {

                            JSONObject c1 = contacts1.getJSONObject(j);
                            String name = c1.getString("name");
                            String message = c1.getString("message");
                            String ibo = c1.getString("ibo");
                            String image = c1.getString("image");

                            imagearray.add(image);
                            iboarray.add(ibo);
                            Names.add(name);
                            Message.add(message);

//                                listDataChild.put(listDataHeader.get(j), Category1);

//                            HashMap<String, String> contact = new HashMap<>();
//                            contact.put("id", prod_name);
//                            contactList1.add(contact);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else

                {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

            } catch (
                    Exception e)

            {
                e.printStackTrace();
                Toast.makeText(Group.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.list_expitem, new String[]{"id"
//                }, new int[]{R.id.lblListItem,
//                });
//                lv.setAdapter(adapter);
//                prepareListData();
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.expadp, new String[]{"id"
//                }, new int[]{R.id.lblListItem,
//                });
//                lv.setAdapter(adapter);
                Grpadpt ListAdapter = new Grpadpt(Group.this,
                        Names,
                        Message,
                        imagearray,
                        iboarray

                );
                lv.setAdapter(ListAdapter);
//                listAdapter1 = new ExpandableListAdapter(ProgramSignup.this, listDataHeader, listDataChild);
//                expListView.setAdapter(listAdapter1);
                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Group.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class GetContacts1 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HttpHandler sh = new HttpHandler();
                String jsonStr1 = sh.makeServiceCall("http://kothuram.com/donatefund/panel/android/cat_list.php?format=json");

                Log.e(TAG, "Response from url: " + jsonStr1);

                if (jsonStr1 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr1);
                        JSONArray contacts = jsonObj.getJSONArray("posts");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String category = c.getString("category");
                            String id = c.getString("id");
                            String points = c.getString("points");

//                            HashMap<String, String> contact = new HashMap<>();
//                            contact.put("id", category);
//                            contactList1.add(contact);

//                            Id.add(id);

                            listDataHeader.add(category);
//                            Points.add(points);

                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Group.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
//                dataAdapter = new ArrayAdapter<String>(Group.this, android.R.layout.simple_spinner_item, listDataHeader);
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(dataAdapter);
//                prepareListData();
//                Expadpt1 ListAdapter = new Expadpt1(ProgramPage.this,
//                        listDataHeader
//                );
//                lv.setAdapter(ListAdapter);
//                ListAdapter adapter = new SimpleAdapter(
//                        ProgramSignup.this, contactList1,
//                        R.layout.gridlist, new String[]{"id"
//                }, new int[]{R.id.grid_ibo,
//                });
//                lv.setAdapter(adapter);
//                listAdapter1 = new ExpandableListAdapter(ProgramSignup.this, listDataHeader, listDataChild);
//                expListView.setAdapter(listAdapter1);
                super.onPostExecute(result);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Group.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void home(View view) {
        Intent i = new Intent(Group.this, ProgramSignup.class);
        startActivity(i);
    }
}
