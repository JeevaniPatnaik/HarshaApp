package com.harsha.harshaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harsha.harshaapp.bean.BaselineHeadInfo2;
import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.MemberInfo;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

import java.util.ArrayList;

/**
 * Created by Jeevani on 2/15/2017.
 */

public class Farm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(Farm.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    Button onFarm,offFarm,nonFarm;

    int position = 0;

    ArrayList<BaselineHeadInfo2> baselineHeadInfo = new ArrayList<BaselineHeadInfo2>();

    BaselineHeadInfo2 baseHead;

    BaselineInfo baselineInfo = new BaselineInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onFarm = (Button) findViewById(R.id.onFarm);
        offFarm = (Button) findViewById(R.id.offFarm);
        nonFarm = (Button) findViewById(R.id.nonFarm);

        Intent receive = getIntent();
        bundle = receive.getExtras();
        position = receive.getIntExtra("baselineHeadInfoPosition", 0);
        user = dbHandler.getUserDetail();

        baselineHeadInfo = dbHandler.getAllBaselineHeadInformation2();
        baseHead = baselineHeadInfo.get(position);
        MemberInfo memberInfo = new MemberInfo();

        bundle.putInt("memberId", memberInfo.getMemberId());
        bundle.putInt("uniqueId", memberInfo.getUniqueId());
        bundle.putString("memberName", memberInfo.getMemberName());
        bundle.putString("dob", memberInfo.getDob());
        bundle.putString("gender", memberInfo.getGender());
        bundle.putInt("socialCategoryId", memberInfo.getSocialCategoryId());
        bundle.putString("aadhaarCardId", memberInfo.getAadhaarCardId());
        bundle.putString("voterId", memberInfo.getVoterId());
        bundle.putString("familyHead", memberInfo.getFamilyHead());
        bundle.putString("personalSalary", memberInfo.getPersonalSalary());
        bundle.putInt("occupationId", memberInfo.getOccupationId());
        bundle.putInt("disabilityId", memberInfo.getDisabilityId());
        bundle.putInt("relationshipId", memberInfo.getRelationshipId());
        bundle.putInt("educationId", memberInfo.getEducationId());
        bundle.putInt("educationStatusId", memberInfo.getEducationStatusId());
        bundle.putInt("maritalStatusId", memberInfo.getMaritalStatusId());
        bundle.putInt("religionId", memberInfo.getReligionId());
        bundle.putInt("schemeId", memberInfo.getSchemeId());
        Log.d("userBundle:","memberId"+ memberInfo.getMemberId()
                + "\nuniqueId=" + memberInfo.getUniqueId()
                + "\nmemberName=" + memberInfo.getMemberName()
                + "\ndob=" + memberInfo.getDob()
                + "\ngender=" + memberInfo.getGender()
                + "\nsocialCategoryId=" + memberInfo.getSocialCategoryId()
                + "\naadhaarCardId=" + memberInfo.getAadhaarCardId()
                + "\nvoterId=" + memberInfo.getVoterId()
                + "\nfamilyHead=" + memberInfo.getFamilyHead()
                + "\npersonalSalary=" + memberInfo.getPersonalSalary()
                + "\noccupationId=" + memberInfo.getOccupationId()
                + "\ndisabilityId=" + memberInfo.getDisabilityId()
                + "relationshipId=" + memberInfo.getRelationshipId()
                + "educationId=" + memberInfo.getEducationId()
                + "educationStatusId=" + memberInfo.getEducationStatusId()
                + "maritalStatusId=" + memberInfo.getMaritalStatusId()
                +"religionId" + memberInfo.getReligionId()
                + "\nschemeId="+ memberInfo.getSchemeId());

        onFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OnFarm.class);
                intent.putExtra("onFarm",bundle);
                startActivity(intent);
            }
        });

        offFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OffFarm.class);
                intent.putExtra("offFarm",bundle);
                startActivity(intent);
            }
        });

        nonFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NonFarm.class);
                intent.putExtra("nonFarm",bundle);
                startActivity(intent);
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
