package com.example.travelme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelme.ui.ContactUsActivity;
import com.example.travelme.ui.MapActivity;
import com.example.travelme.ui.RateActivity;
import com.example.travelme.ui.GalleryActivity;
import com.example.travelme.ui.AdvertisementActivity;
import com.example.travelme.ui.CategoryActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Usermenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    ImageView back, HeadPhoto, lodge, map, traveling, restaurant, hospital;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView HeadName, HeadEmail;



    FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private GoogleSignInClient mGoogleSignInClient;

    //
    GoogleSignInOptions gso = new GoogleSignInOptions.
            Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
            build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermenu);


        fAuth = FirebaseAuth.getInstance();







        //menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        back = findViewById(R.id.back);
        //item list
        lodge = findViewById(R.id.image_lodge);
        map = findViewById(R.id.image_map);
        traveling = findViewById(R.id.image_Traveling);
        restaurant = findViewById(R.id.image_Restaurants);
        hospital = findViewById(R.id.image_Hospital);


        //header name email and photo
        View headview = navigationView.getHeaderView(0);
        HeadName = headview.findViewById(R.id.Uname);
        HeadEmail = headview.findViewById(R.id.user_email);
        HeadPhoto = headview.findViewById(R.id.user_photo);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            HeadName.setText((signInAccount.getDisplayName()));
            HeadEmail.setText(signInAccount.getEmail());
            Uri photo = signInAccount.getPhotoUrl();
            Picasso.with(this).load(photo).fit().placeholder(R.mipmap.ic_launcher_round).into(HeadPhoto);

        }


        navigationDrawer();
        menuitem();



    }

    private void menuitem() {
        lodge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Usermenu.this, CategoryActivity.class);
                startActivity(intent);
                finish();

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Usermenu.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });
        traveling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Usermenu.this, AdvertisementActivity.class);
                startActivity(intent);
                finish();
            }
        });
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Usermenu.this, GalleryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Usermenu.this, ContactUsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    // Navigation Drawer Functions
    private void navigationDrawer() {

        //navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });



    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    //navigation change
    @Override
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

            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);

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
           GoogleSignInClient googleSignInClient=GoogleSignIn.getClient(this,gso);
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


