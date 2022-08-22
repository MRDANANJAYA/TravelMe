package com.example.travelme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Userskip extends AppCompatActivity {

    ImageView loginbt,pickme, yogo, kang, uber;
    private WebView mywebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userskip);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);




        loginbt  = findViewById(R.id.back);
        pickme  = findViewById(R.id.pickme);
        yogo  = findViewById(R.id.yogo);
        kang  = findViewById(R.id.Kangaroo);
        uber  = findViewById(R.id.Uber);


       loginbt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Inedex();
           }
       });




        pickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserintent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.pickme.passenger&hl=en"));
                startActivity(browserintent);

            }
        });

        yogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserintent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=lk.link.yogo_passenger&hl=en"));
                startActivity(browserintent);

            }
        });

        kang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserintent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=kangaroo.lk.clientapp&hl=en"));
                startActivity(browserintent);


            }
        });

        uber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserintent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=kangaroo.lk.clientapp&hl=en"));
                startActivity(browserintent);

            }
        });





    }











    public void Inedex(){
        Intent intent = new Intent(this, Inedex.class);
        startActivity(intent);

    }


}
