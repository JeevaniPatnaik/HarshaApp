package com.harsha.harshaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jeevani on 12/28/2016.
 */
public class ManagerChangePassword extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText oldPassword,newPassword,retypePassword;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oldPassword = (EditText) findViewById(R.id.oldPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        retypePassword = (EditText) findViewById(R.id.retypePassword);
        update = (Button) findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
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
            Intent intent = new Intent(getApplicationContext(), ManagerHome.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.b_info) {
            Intent intent = new Intent(getApplicationContext(), ManagerBaselineInformation.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }  else if (id == R.id.impact_area) {
            Intent intent = new Intent(getApplicationContext(),  ManagerImpactArea.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.profile) {
            Intent intent = new Intent(getApplicationContext(),  ManagerProfile.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }else if (id == R.id.change_password) {
            Intent intent = new Intent(getApplicationContext(),  ManagerChangePassword.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.serverUpload) {
            Intent intent = new Intent(getApplicationContext(),  ManagerServerUpload.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.address) {
            Intent intent = new Intent(getApplicationContext(),  ManagerAddress.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
