package com.harsha.harshaapp;

/**
 * Created by Jeevani on 12/4/2016.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

public class Address extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(Address.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t6 = (TextView) findViewById(R.id.t6);
        t7 = (TextView) findViewById(R.id.t7);
        t8 = (TextView) findViewById(R.id.t8);
        t9 = (TextView) findViewById(R.id.t9);
        t10 = (TextView) findViewById(R.id.t10);
        t11 = (TextView) findViewById(R.id.t11);
        t12 = (TextView) findViewById(R.id.t12);

        Typeface face1= Typeface.createFromAsset(getAssets(), "fonts/AlexBrush-Regular.ttf");
        t1.setTypeface(face1);
        t6.setTypeface(face1);
        t11.setTypeface(face1);
        t12.setTypeface(face1);
        Typeface face2= Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        t2.setTypeface(face2);
        t3.setTypeface(face2);
        t4.setTypeface(face2);
        t5.setTypeface(face2);
        t7.setTypeface(face2);
        t8.setTypeface(face2);
        t9.setTypeface(face2);
        t10.setTypeface(face2);

        Intent receive = getIntent();
        bundle = receive.getExtras();
        user = dbHandler.getUserDetail();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        nav_username = (TextView) headerView.findViewById(R.id.nav_username);
        nav_username.setText(user.getUserName());
        nav_email = (TextView) headerView.findViewById(R.id.nav_email);
        nav_email.setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.b_info) {
            Intent intent = new Intent(getApplicationContext(), BaselineInformation.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.create_info) {
            Intent intent = new Intent(getApplicationContext(), CreateBaselineInformation.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.impact_area) {
            Intent intent = new Intent(getApplicationContext(), ImpactArea.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.uploads) {
            Intent intent = new Intent(getApplicationContext(), ProjectDetails.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.profile) {
            Intent intent = new Intent(getApplicationContext(), MyProfile.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }else if (id == R.id.change_password) {
            Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.serverDownload) {
            Intent intent = new Intent(getApplicationContext(), ServerDownload.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.serverUpload) {
            Intent intent = new Intent(getApplicationContext(), ServerUpload.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.address) {
            Intent intent = new Intent(getApplicationContext(), Address.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.logout) {
            dbHandler.deleteUser(user);
            Intent intent = new Intent(getApplicationContext(), Login.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
