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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.harsha.harshaapp.bean.BaselineHeadInfo2;
import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.Block;
import com.harsha.harshaapp.bean.District;
import com.harsha.harshaapp.bean.Project;
import com.harsha.harshaapp.bean.State;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.bean.Village;
import com.harsha.harshaapp.database.DBHandler;

import java.util.ArrayList;

/**
 * Created by Jeevani on 2/15/2017.
 */

public class OffFarm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(OffFarm.this, null, null, 1);
    Bundle bundle;
    User user = new User();
    State stateBean = new State();
    District districtBean = new District();
    Block blockBean = new Block();
    Village villageBean = new Village();
    Project projectBean = new Project();

    String result = "";
    int flag = 1;
    int position = 0;

    BaselineInfo baselineInfo = new BaselineInfo();
    ArrayList<BaselineHeadInfo2> baselineHeadInfo = new ArrayList<BaselineHeadInfo2>();
    ArrayList<String> projectArray = new ArrayList<String>();
    ArrayList<Project> listProject;

    BaselineHeadInfo2 baseHead;

    TextView nav_username,nav_email;

    TextView familyHeadname, offFarmState, offFarmDistrict, offFarmBlock, offFarmVillage;
    EditText unitBeginning,unitPresent,fieldConsumption,bodyWeight,mortality,unitSold,income,offFarmDate,impact;
    Spinner offFarmProject;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_farm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyHeadname = (TextView) findViewById(R.id.familyHeadName);
        unitBeginning = (EditText) findViewById(R.id.unitBeginning);
        unitPresent = (EditText) findViewById(R.id.unitPresent);
        fieldConsumption = (EditText) findViewById(R.id.fieldConsumption);
        bodyWeight = (EditText) findViewById(R.id.bodyWeight);
        mortality = (EditText) findViewById(R.id.mortality);
        unitSold = (EditText) findViewById(R.id.unitSold);
        income = (EditText) findViewById(R.id.income);
        impact = (EditText) findViewById(R.id.impact);
        offFarmDate = (EditText) findViewById(R.id.offFarmDate);
        offFarmState = (TextView) findViewById(R.id.state);
        offFarmDistrict = (TextView) findViewById(R.id.district);
        offFarmBlock = (TextView) findViewById(R.id.block);
        offFarmVillage = (TextView) findViewById(R.id.village);
        offFarmProject = (Spinner) findViewById(R.id.project);
        save = (Button) findViewById(R.id.save);

        AddOffFarmAsyncTask obj = new AddOffFarmAsyncTask(this);
        obj.execute();

        offFarmProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listProject.size(); i++) {
                        Project pr = listProject.get(i);
                        if(item.equalsIgnoreCase(pr.getProjectName() + "-" + pr.getProjectId())) {
                            projectBean = pr;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=2;
                AddOffFarmAsyncTask obj1 = new AddOffFarmAsyncTask(OffFarm.this);
                obj1.execute();
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

    public void readData() {

        /*baselineInfo.setStateId(state.getStateId());
        baselineInfo.setDistrictId(district.getDistrictId());
        baselineInfo.setBlockId(block.getBlockId());
        baselineInfo.setSurveyUserId(user.getUserId());
        Log.d("income=","Income="+income.getText().toString()+"\ngetIncome="+baselineInfo.getIncome());
        Log.d("user=",user + "\nuserId="+user.getUserId()+"\nUsername="+user.getUserName());*/
        baselineInfo.setStateId(stateBean.getStateId());
        baselineInfo.setDistrictId(districtBean.getDistrictId());
        baselineInfo.setBlockId(blockBean.getBlockId());
        baselineInfo.setVillageId(villageBean.getVillageId());
        baselineInfo.setSurveyUserId(user.getUserId());

    }

    public void spinnerList() {

   /*     baselineHeadInfo = dbHandler.getAllBaselineHeadInformation2();
        baseHead = baselineHeadInfo.get(position);

        familyHeadname.setText(familyHeadname.getText().toString() + ": " + baseHead.memberName);
        onFarmState.setText(onFarmState.getText().toString() + ": " + baseHead.stateName);
        onFarmDistrict.setText(onFarmDistrict.getText().toString() + ": " + baseHead.districtName);
        onFarmBlock.setText(onFarmBlock.getText().toString() + ": " + baseHead.blockName);
        onFarmVillage.setText(onFarmVillage.getText().toString() + ": " + baseHead.villageName);*/
        stateBean = dbHandler.getLastState();
        districtBean = dbHandler.getLastDistrict();
        blockBean = dbHandler.getLastBlock();
        villageBean = dbHandler.getLastVillage();

        offFarmState.setText(stateBean.getStateName() + "-" + stateBean.getStateCode());
        offFarmDistrict.setText(districtBean.getDistrictName()+ "-"+districtBean.getDistrictCode());
        offFarmBlock.setText(blockBean.getBlockName()+"-"+blockBean.getBlockCode());
        offFarmVillage.setText(villageBean.getVillageName()+"-"+villageBean.getVillageCode());

        listProject = dbHandler.getAllProject();
        String pr = "---- Select Project ----";
        projectArray.add(pr);
        for(int i=0; i<listProject.size(); i++) {
            Project proj = listProject.get(i);
            String name = proj.getProjectName() + "-" + proj.getProjectId();
            projectArray.add(name);
        }

        ArrayAdapter<String> projectListAdapter = new ArrayAdapter<String>(OffFarm.this, android.R.layout.simple_spinner_item, projectArray);
        projectListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        offFarmProject.setAdapter(projectListAdapter);

    }

    class AddOffFarmAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public AddOffFarmAsyncTask(Context mContext) {
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
                    spinnerList();
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
