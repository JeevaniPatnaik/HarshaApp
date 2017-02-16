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
import android.widget.Spinner;
import android.widget.TextView;

import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

/**
 * Created by Jeevani on 2/15/2017.
 */

public class OnFarm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(OnFarm.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    TextView familyHeadname;
    EditText land,noOfPlants,yearOfPlanting,production,income,onFarmDate,impact;
    Spinner state, district, block, village, project;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_farm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyHeadname = (TextView) findViewById(R.id.familyHeadName);
        land = (EditText) findViewById(R.id.land);
        noOfPlants = (EditText) findViewById(R.id.noOfPlants);
        yearOfPlanting = (EditText) findViewById(R.id.yearOfPlanting);
        production = (EditText) findViewById(R.id.production);
        income = (EditText) findViewById(R.id.income);
        onFarmDate = (EditText) findViewById(R.id.onFarmDate);
        impact = (EditText) findViewById(R.id.impact);
        state = (Spinner) findViewById(R.id.state);
        district = (Spinner) findViewById(R.id.district);
        block = (Spinner) findViewById(R.id.block);
        village = (Spinner) findViewById(R.id.village);
        project = (Spinner) findViewById(R.id.project);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
