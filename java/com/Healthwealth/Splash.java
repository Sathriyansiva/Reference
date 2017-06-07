package com.Healthwealth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    Animation animBlink;
    TextView tv;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        } else {
            setContentView(R.layout.activity_splash);
            tv = (TextView) findViewById(R.id.tv);
            animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.blink);
            tv.setVisibility(View.VISIBLE);
//            tv.startAnimation(animBlink);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    prefManager.setFirstTimeLaunch(false);
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
    }
}
