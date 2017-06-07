package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Mareesoftpc on 3/10/2017.
 */

public class SessionManagement {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_Password = "password";

//    public SessionManager(Context context) {
//        this._context = context;
//        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
//        editor = pref.edit();
//
//    }
        /**
         * Create login session
         * */
        public void createLoginSession(String username, String password){
            editor.putBoolean(IS_LOGIN, true);
            editor.putString(KEY_USERNAME, username);
            editor.putString(KEY_Password, password);
         editor.commit();
        }

        /**
         * Check login method wil check user login status
         * If false it will redirect user to login page
         * Else won't do anything
         * */
        public void checkLogin(){
            if(!this.isLoggedIn()){
                Intent i = new Intent(_context, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(i);
            }
        }
        /**
         * Get stored session data
         * */
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();
            user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));

            user.put(KEY_Password, pref.getString(KEY_Password, null));
            return user;
        }

        /**
         * Clear session details
         * */
        public void logoutUser(){
            // Clearing all data from Shared Preferences
            editor.clear();
            editor.commit();

            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

        /**
         * Quick check for login
         * **/
        public boolean isLoggedIn(){
            return pref.getBoolean(IS_LOGIN, false);
        }
    }
