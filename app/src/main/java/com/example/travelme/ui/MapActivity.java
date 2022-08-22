package com.example.travelme.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelme.Login;
import com.example.travelme.R;
import com.example.travelme.Usermenu;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class MapActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView back, HeadPhoto;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView HeadName, HeadEmail;
    FirebaseAuth fAuth;
    private WebView mywebview;

    //google client
    GoogleSignInOptions gso = new GoogleSignInOptions.
            Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
            build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_map);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //menu hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        back = findViewById(R.id.back);
        View headview = navigationView.getHeaderView(0);
        HeadName = headview.findViewById(R.id.Uname);
        HeadEmail = headview.findViewById(R.id.user_email);

        fAuth = FirebaseAuth.getInstance();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            HeadName.setText((signInAccount.getDisplayName()));
            HeadEmail.setText(signInAccount.getEmail());
            HeadPhoto = headview.findViewById(R.id.user_photo);
            Uri photo = signInAccount.getPhotoUrl();
            Picasso.with(this).load(photo).fit().placeholder(R.mipmap.ic_launcher_round).into(HeadPhoto);
        }


        mywebview = findViewById(R.id.webview);
        WebSettings webSettings = mywebview.getSettings();
        webSettings.getJavaScriptEnabled();

        mywebview.loadUrl("https://www.google.lk/maps/search/sri+lankan+lodge/@7.3073029,80.6556869,9.25z");
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.setWebChromeClient(new WebChromeClient());
        mywebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        mywebview.getSettings().setJavaScriptEnabled(true);








        navigationDrawer();
    }
    // Navigation Drawer Functions
    private void navigationDrawer() {

        //navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_map);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }

    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId() == R.id.nav_home) {

            Intent intent = new Intent(this, Usermenu.class);
            startActivity(intent);

        }

        if (menuItem.getItemId() == R.id.nav_map) {

            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);


        }
        if (menuItem.getItemId() == R.id.nav_lodge) {



            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);

        }
        if (menuItem.getItemId() == R.id.nav_Restaurants) {



        }
        if (menuItem.getItemId() == R.id.nav_Traveling) {

            Intent intent = new Intent(this, AdvertisementActivity.class);
            startActivity(intent);

        }

        if (menuItem.getItemId() == R.id.nav_Hospitals) {

            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);

        }

        if (menuItem.getItemId() == R.id.nav_rateus) {

            Intent intent = new Intent(this, RateActivity.class);
            startActivity(intent);

        }

        if (menuItem.getItemId() == R.id.nav_logoout) {
            /** FirebaseAuth.getInstance().signOut();
             Intent intent = new Intent(getApplicationContext(), Login.class);
             startActivity(intent);
             Toast.makeText(this, "User has been sign out", Toast.LENGTH_SHORT).show();**/
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
            googleSignInClient.signOut();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            Toast.makeText(this, "User has been sign out", Toast.LENGTH_SHORT).show();
            finish();

        }
        return true;
    }

}
