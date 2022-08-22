package com.example.travelme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {

    private ImageView imgClick, google, forgotPass;
    private Button loginbt;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private EditText memail;
    private EditText mpassword;
    private GoogleSignInClient mGoogleSignInClient;

    private final static int RC_SIGN_IN = 123;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = fAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), Usermenu.class);
            startActivity(intent);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        google = findViewById(R.id.g_sign_in);
        imgClick = findViewById(R.id.back);
        loginbt = findViewById(R.id.btnlogin);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);

        memail = findViewById(R.id.emaillog);
        mpassword = findViewById(R.id.passlog);
        forgotPass = findViewById(R.id.forgetpass);
        FirebaseAuth.getInstance().setLanguageCode("en");


        // login button
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = memail.getText().toString();
                String password = mpassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    memail.setError("Email Cannot Be Empty");
                    return;
                }

                if (TextUtils.isEmpty((password))) {
                    mpassword.setError("Password Cannot Be Empty");
                    return;
                }

                //check user vaild
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Usermenu.class));
                        } else {
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });


                //Intent intent = new Intent(Login.this, Usermenu.class);
                //startActivity(intent);

            }
        });

        //reset Password
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText forgotPass = new EditText(Login.this);
                final AlertDialog.Builder PasswordResetDialog = new AlertDialog.Builder(v.getContext());
                PasswordResetDialog.setTitle("Did you forget password..?");
                PasswordResetDialog.setMessage("Enter Tour Email To Received Reset Link");
                PasswordResetDialog.setView(forgotPass);
                forgotPass.setHint("Email");
                forgotPass.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);


                // LinearLayout linearLayout =new LinearLayout(Login.this);


                PasswordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String email = forgotPass.getText().toString().trim();
                        fAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Reset Link Can Not be Send !" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                PasswordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                PasswordResetDialog.create().show();

            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Inedex.class);
                startActivity(intent);
            }
        });

        createRequest();


    }

    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    private void signIn() {
        progressBar.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

                // Google Sign In failed, update UI appropriately

                Toast.makeText(this,"Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        progressBar.setVisibility(View.VISIBLE);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = fAuth.getCurrentUser();
                            Intent intent = new Intent(Login.this.getApplicationContext(), Usermenu.class);
                            Login.this.startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.


                            Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }

                });

    }


}
