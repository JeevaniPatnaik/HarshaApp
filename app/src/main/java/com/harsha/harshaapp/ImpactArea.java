package com.harsha.harshaapp;

/**
 * Created by Jeevani on 12/4/2016.
 */
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

public class ImpactArea extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(ImpactArea.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    EditText familyHeadName,beneficiaryName,husbandWife,land,herr,income;
    Spinner state,district,village,project,plantsNo,plantingYear;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impact_area);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyHeadName = (EditText) findViewById(R.id.familyHeadName);
        beneficiaryName = (EditText) findViewById(R.id.beneficiaryName);
        husbandWife = (EditText) findViewById(R.id.husbandWife);
        land = (EditText) findViewById(R.id.land);
        herr = (EditText) findViewById(R.id.herr);
        income = (EditText) findViewById(R.id.income);
        plantsNo = (Spinner) findViewById(R.id.plantsNo);
        plantingYear = (Spinner) findViewById(R.id.plantingYear);
        state = (Spinner) findViewById(R.id.state);
        district = (Spinner) findViewById(R.id.district);
        village = (Spinner) findViewById(R.id.village);
        project = (Spinner) findViewById(R.id.project);
        save = (Button) findViewById(R.id.save);

        ArrayAdapter<CharSequence> stateListAdapter = ArrayAdapter
                .createFromResource(this, R.array.state_list,
                        android.R.layout.simple_spinner_item);
        stateListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(stateListAdapter);

        ArrayAdapter<CharSequence> districtListAdapter = ArrayAdapter
                .createFromResource(this, R.array.district_list,
                        android.R.layout.simple_spinner_item);
        districtListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(districtListAdapter);

        ArrayAdapter<CharSequence> villageListAdapter = ArrayAdapter
                .createFromResource(this, R.array.village_list,
                        android.R.layout.simple_spinner_item);
        villageListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village.setAdapter(villageListAdapter);

        ArrayAdapter<CharSequence> projectListAdapter = ArrayAdapter
                .createFromResource(this, R.array.project_list,
                        android.R.layout.simple_spinner_item);
        projectListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project.setAdapter(projectListAdapter);

        ArrayAdapter<CharSequence> plantsNoListAdapter = ArrayAdapter
                .createFromResource(this, R.array.plantsNo_list,
                        android.R.layout.simple_spinner_item);
        plantsNoListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plantsNo.setAdapter(plantsNoListAdapter);

        ArrayAdapter<CharSequence> plantingYearListAdapter = ArrayAdapter
                .createFromResource(this, R.array.plantingYear_list,
                        android.R.layout.simple_spinner_item);
        plantingYearListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plantingYear.setAdapter(plantingYearListAdapter);

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
