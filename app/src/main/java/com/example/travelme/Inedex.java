package com.example.travelme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

public class Inedex extends AppCompatActivity {
    private Button button;
    private Button register;
    private Button skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_inedex);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View v) {
                Login();



            }
        });

        register = findViewById(R.id.button2);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Register();
            }
        });

        skip = findViewById(R.id.skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Userskip();
            }
        });


}

public void Userskip(){

    Intent intent = new Intent(this, Userskip.class);
    startActivity(intent);
}


 public void Register(){
     Intent intent = new Intent(this, Register.class);
     startActivity(intent);
 }




    public void Login(){

        Intent intent = new Intent(this, Login.class);

        startActivity(intent);

    }
}
