package com.harsha.harshaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.harsha.harshaapp.bean.BaselineHeadInfo2;
import com.harsha.harshaapp.bean.Disabilities;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Relationship;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

import java.util.ArrayList;

/**
 * Created by Jeevani on 1/6/2017.
 */
public class FamilyInformation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(FamilyInformation.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    Occupation occupationBean = new Occupation();
    Disabilities disabilitiesBean = new Disabilities();
    Relationship relationshipBean = new Relationship();

    ArrayList<BaselineHeadInfo2> baselineHeadInfo = new ArrayList<BaselineHeadInfo2>();

    BaselineHeadInfo2 baseHead;

    TextView nav_username,nav_email;

    int flag=1;
    int position = 0;
    String result = "";

    TextView familyInformation, personalInformation,name,dob1,aadhaarCard,voterId,familyHeadName,personalSalary, gender, quota, occupation, disabilities, relationship, education, educationStatus, migrationReason, maritalStatus, religion, centralScheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyInformation = (TextView) findViewById(R.id.familyInformation);
        personalInformation = (TextView) findViewById(R.id.personalInformation);
        name = (TextView) findViewById(R.id.name);
        dob1 = (TextView) findViewById(R.id.dob);
        aadhaarCard = (TextView) findViewById(R.id.aadhaarCard);
        voterId = (TextView) findViewById(R.id.voterId);
        familyHeadName = (TextView) findViewById(R.id.familyHeadName);
        personalSalary = (TextView) findViewById(R.id.personalSalary);
        gender = (TextView) findViewById(R.id.gender);
        quota = (TextView) findViewById(R.id.quota);
        occupation = (TextView) findViewById(R.id.occupation);
        disabilities = (TextView) findViewById(R.id.disabilities);
        relationship = (TextView) findViewById(R.id.relationship);
        education = (TextView) findViewById(R.id.education);
        educationStatus = (TextView) findViewById(R.id.educationStatus);
        //migrationReason = (TextView) findViewById(R.id.migrationReason);
        maritalStatus = (TextView) findViewById(R.id.maritalStatus);
        religion = (TextView) findViewById(R.id.religion);
        centralScheme = (TextView) findViewById(R.id.centralScheme);

        Intent receive = getIntent();
        bundle = receive.getExtras();

        position = receive.getIntExtra("baselineHeadInfoPosition", 0);
        user = dbHandler.getUserDetail();

        flag = 1;
        DisplayBaselineAsyncTask obj1 = new DisplayBaselineAsyncTask(FamilyInformation.this);
        obj1.execute();

       /* occupation.setText(occupationBean.getOccupationName());
        disabilities.setText(disabilitiesBean.getDisabilitiesName());*/

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

    public void displayList() {

        baselineHeadInfo = dbHandler.getAllBaselineHeadInformation2();
        baseHead = baselineHeadInfo.get(position);

        name.setText(name.getText().toString() + ": " + baseHead.memberName);
        dob1.setText(dob1.getText().toString() + ": " + baseHead.dob);
        aadhaarCard.setText(aadhaarCard.getText().toString() + ": " + baseHead.aadhaarCardId);
        voterId.setText(voterId.getText().toString() + ": " + baseHead.voterId);
        familyHeadName.setText(familyHeadName.getText().toString() + ": " + baseHead.familyHead);
        personalSalary.setText(personalSalary.getText().toString() + ": " + baseHead.personalSalary);
        gender.setText(gender.getText().toString() + ": " + baseHead.gender);
        quota.setText(quota.getText().toString() + ": " + baseHead.socialCategoryName);
        occupation.setText(occupation.getText().toString() + ": " + baseHead.occupationName);
        disabilities.setText(disabilities.getText().toString()+ ": " + baseHead.disabilitiesName);
        relationship.setText(relationship.getText().toString() + ": " + baseHead.relationshipName);
        education.setText(education.getText().toString() + ": " + baseHead.educationName);
        educationStatus.setText(educationStatus.getText().toString() + ": " + baseHead.educationStatusName);
        maritalStatus.setText(maritalStatus.getText().toString() + ": " + baseHead.maritalStatusName);
        religion.setText(religion.getText().toString() + ": " + baseHead.religionName);
        centralScheme.setText(centralScheme.getText().toString() + ": " + baseHead.schemeName);

    }

    public void readData() {

    }

    class DisplayBaselineAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public DisplayBaselineAsyncTask(Context mContext) {
            this.mContext = mContext;
            progressDialog = new ProgressDialog(mContext);
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle(R.string.app_name);
            progressDialog.setMessage("Loading, Please Wait...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                if (flag==1) {
                    displayList();
                }
                else if (flag==2) {
                    readData();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            if(flag==2) {

            }
            progressDialog.dismiss();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.family_information_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_info) {
            Intent intent = new Intent(getApplicationContext(), AddFamilyMemberInformation.class);
            //intent.putExtras(bundle);
            intent.putExtra("baselineId", baseHead.baselineId);
            intent.putExtra("familyHeadName", baseHead.memberName);
            intent.putExtra("stateId", baseHead.stateId);
            intent.putExtra("stateName", baseHead.stateName);
            intent.putExtra("districtId", baseHead.districtId);
            intent.putExtra("districtName", baseHead.districtName);
            intent.putExtra("blockId", baseHead.blockId);
            intent.putExtra("blockName", baseHead.blockName);
            intent.putExtra("villageId", baseHead.villageId);
            intent.putExtra("villageName", baseHead.villageName);
            startActivity(intent);
        } else if (id == R.id.update_info) {
            Intent intent = new Intent(getApplicationContext(), UpdateMemberInformation.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.update_baseline_info) {
            Intent intent = new Intent(getApplicationContext(), UpdateBaselineInformation.class);
            //intent.putExtras(bundle);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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