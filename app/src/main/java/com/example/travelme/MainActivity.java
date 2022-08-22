package com.example.travelme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity {

    //veriables
    Animation travleanim, getawayanim ;
    ImageView image;
    ImageView logo;


    //auto redirect to the nex page
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);




        //Timer
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent( MainActivity.this, Inedex.class);
                startActivity(intent);
            finish();

            }
        },2000);




        //the animation

        travleanim = AnimationUtils.loadAnimation(this, R.anim.tavel_me_anim);
        getawayanim = AnimationUtils.loadAnimation(this, R.anim.get_away_anim);

        //hook
        logo =findViewById(R.id.logo);
        image =findViewById(R.id.get_away);

        logo.setAnimation(travleanim);
        image.setAnimation(getawayanim);


    }



}
