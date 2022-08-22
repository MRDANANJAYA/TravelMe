package com.example.travelme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    //define veriables
    private ImageView imgClick, allsignin;
    private ProgressBar progressBar;
    private EditText lemail;
    private EditText lusername;
    private EditText lpassword;
    private ImageButton register;


    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_register);

        //hooks
        imgClick = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        lpassword = findViewById(R.id.pass);
        lemail = findViewById(R.id.email);
        register = findViewById(R.id.button4);
        lusername = findViewById(R.id.usernametext);
        allsignin = findViewById(R.id.signin);

        allsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);

                startActivity(intent);
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        /**if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext() , Register.class));
            finish();
        }*/




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lemail.getText().toString();
                String password = lpassword.getText().toString();
                String username = lusername.getText().toString();
                if(TextUtils.isEmpty(email)){
                    lemail.setError("Email Cannot Be Empty");
                    return;
                }
                if(TextUtils.isEmpty((username))){
                    lusername.setError("Username Cannot Be Empty");
                    return;
                }
                if(TextUtils.isEmpty((password))){
                    lpassword.setError("Password Cannot Be Empty");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registation Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Usermenu.class));
                            } else {
                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });




            }
        });


         imgClick.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Inedex();
             }
         });



    }

    public void Inedex(){

        Intent intent = new Intent(this, Inedex.class);

        startActivity(intent);
    }
}
