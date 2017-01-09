package com.harsha.harshaapp;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.Block;
import com.harsha.harshaapp.bean.District;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Religion;
import com.harsha.harshaapp.bean.SocialCategory;
import com.harsha.harshaapp.bean.State;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.bean.Village;
import com.harsha.harshaapp.database.DBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jeevani on 12/14/2016.
 */
public class CreateBaselineInformation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /*ArrayList<String> nameVillage = new ArrayList<String>();
    ArrayList<String> nameReligion = new ArrayList<String>();
    ArrayList<String> nameSocialCategory = new ArrayList<String>();
    ArrayList<String> nameOccupation = new ArrayList<String>();*/

    DBHandler dbHandler = new DBHandler(CreateBaselineInformation.this, null, null, 1);
    Bundle bundle;
    User user = new User();
    BaselineInfo baselineInfo = new BaselineInfo();
    State state = new State();
    District district = new District();
    Block block = new Block();
    Village villageList = new Village();
    Religion religionList = new Religion();
    SocialCategory socialCategoryList = new SocialCategory();
    Occupation occupationList = new Occupation();

    /*ArrayList<State> listState;
    ArrayList<District> listDistrict;
    ArrayList<Block> listBlock;*/
    ArrayList<Religion> listReligion;
    ArrayList<SocialCategory> listSocialCategory;
    ArrayList<Occupation> listOccupation;
    ArrayList<Village> listVillage;

    TextView nav_username,nav_email;

    EditText familyHeadName,contactNo,income,noOfFamilyMember;
    TextView stateName,districtName,blockName;
    Spinner village,religion,occupation,socialCategory;
    Button next;

    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_baseline_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyHeadName = (EditText) findViewById(R.id.familyHeadName);
        contactNo = (EditText) findViewById(R.id.contactNo);
        income = (EditText) findViewById(R.id.income);
        stateName = (TextView) findViewById(R.id.stateName);
        districtName = (TextView) findViewById(R.id.districtName);
        blockName = (TextView) findViewById(R.id.blockName);
        village = (Spinner) findViewById(R.id.villageName);
        religion = (Spinner) findViewById(R.id.religion);
        occupation = (Spinner) findViewById(R.id.occupation);
        socialCategory = (Spinner) findViewById(R.id.socialCategory);
        noOfFamilyMember = (EditText) findViewById(R.id.noOfFamilyMember);
        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if("".equals(familyHeadName.getText().toString()) //vUsername.equalsIgnoreCase("") could lead to NPE
                        || "".equalsIgnoreCase(income.getText().toString())
                        || "".equalsIgnoreCase(village.getSelectedItem().toString())
                        || "".equalsIgnoreCase(religion.getSelectedItem().toString())
                        || "".equalsIgnoreCase(occupation.getSelectedItem().toString())
                        || "".equalsIgnoreCase(socialCategory.getSelectedItem().toString())
                        || "".equalsIgnoreCase(noOfFamilyMember.getText().toString())
                        )
                {
                    Toast.makeText(CreateBaselineInformation.this, "All Fields Required.",
                            Toast.LENGTH_LONG).show();
                }
                else if(!(familyHeadName.getText().toString()).matches("[a-zA-Z? ]*")){

                    Toast.makeText(CreateBaselineInformation.this, familyHeadName.getText().toString()+" must be Character",
                            Toast.LENGTH_LONG).show();
                }
                else if(!(income.getText().toString()).matches("[1-9.? ]*")){

                    Toast.makeText(CreateBaselineInformation.this, income.getText().toString() +"must be numeric",
                            Toast.LENGTH_LONG).show();
                }
                else if(!(noOfFamilyMember.getText().toString()).matches("[1-9.? ]*")){
                    Toast.makeText(CreateBaselineInformation.this, Integer.parseInt(noOfFamilyMember.getText().toString()) +"must be numeric",
                            Toast.LENGTH_LONG).show();
                }*/
                AddBaselineAsyncTask obj = new AddBaselineAsyncTask();
                obj.execute();
                Intent intent = new Intent(CreateBaselineInformation.this,AddMemberInformation.class);
                //intent.putExtra(bundle);
                startActivity(intent);
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

    public void spinnerList() {
        //Textview
        /*listState = dbHandler.getAllState();
        listDistrict = dbHandler.getAllDistrict();
        listBlock = dbHandler.getAllBlock();*/

        //Spinner
        listVillage = dbHandler.getAllVillage();
        listReligion = dbHandler.getAllReligion();
        listOccupation = dbHandler.getAllOccupation();
        listSocialCategory = dbHandler.getAllSocialCategory();

        ArrayAdapter<Village> villageListAdapter = new ArrayAdapter<Village>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, listVillage);
        villageListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village.setAdapter(villageListAdapter);

        ArrayAdapter<Religion> religionListAdapter = new ArrayAdapter<Religion>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, listReligion);
        religionListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religion.setAdapter(religionListAdapter);

        ArrayAdapter<Occupation> occupationListAdapter = new ArrayAdapter<Occupation>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, listOccupation);
        occupationListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(occupationListAdapter);

        ArrayAdapter<SocialCategory> socialCategoryListAdapter = new ArrayAdapter<SocialCategory>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, listSocialCategory);
        socialCategoryListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialCategory.setAdapter(socialCategoryListAdapter);
    }

    class AddBaselineAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                spinnerList();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    baselineInfo.setBaselineId(jsonObject.getInt("baselineId"));

                    if(baselineInfo.getBaselineId()>0) {

                        baselineInfo.setFamilyHeadId(jsonObject.getInt("familyHeadId"));
                        baselineInfo.setStateId(jsonObject.getInt("stateId"));
                        baselineInfo.setDistrictId(jsonObject.getInt("districtId"));
                        baselineInfo.setBlockId(jsonObject.getInt("blockId"));
                        baselineInfo.setVillageId(jsonObject.getInt("villageId"));
                        baselineInfo.setSurveyUserId(jsonObject.getInt("surveyUserId"));
                        baselineInfo.setSocialCategoryId(jsonObject.getInt("socialCategoryId"));
                        baselineInfo.setReligionId(jsonObject.getInt("religionId"));
                        baselineInfo.setOccupationId(jsonObject.getInt("occupationId"));
                        baselineInfo.setContactNo(jsonObject.getInt("contactNo"));
                        baselineInfo.setFamilyMemberNumber(jsonObject.getInt("familyMemberNumber"));
                        baselineInfo.setIncome(jsonObject.getString("income"));

                        stateName.setText(state.getStateName());
                        districtName.setText(district.getDistrictName());
                        blockName.setText(block.getBlockName());

                        dbHandler.insertBaselineInformation(baselineInfo);

                        Bundle userBundle = new Bundle();

                        userBundle.putInt("familyHeadId", baselineInfo.getFamilyHeadId());
                        userBundle.putInt("stateId", baselineInfo.getStateId());
                        userBundle.putInt("districtId", baselineInfo.getDistrictId());
                        userBundle.putInt("blockId", baselineInfo.getBlockId());
                        userBundle.putInt("villageId", baselineInfo.getVillageId());
                        userBundle.putInt("surveyUserId", baselineInfo.getSurveyUserId());
                        userBundle.putInt("socialCategoryId", baselineInfo.getSocialCategoryId());
                        userBundle.putInt("religionId", baselineInfo.getReligionId());
                        userBundle.putInt("occupationId", baselineInfo.getOccupationId());
                        userBundle.putInt("contactNo", baselineInfo.getContactNo());
                        userBundle.putInt("familyMemberNumber", baselineInfo.getFamilyMemberNumber());
                        userBundle.putString("income", baselineInfo.getIncome());

                    } else {
                        familyHeadName.setText("");
                        stateName.setText(state.getStateName());
                        districtName.setText(district.getDistrictName());
                        blockName.setText(block.getBlockName());
                        contactNo.setText("");
                        income.setText("");
                        noOfFamilyMember.setText("");
                        stateName.setText("");
                        districtName.setText("");
                        blockName.setText("");
                        Toast.makeText(CreateBaselineInformation.this, "Baseline Information is not added successfully", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
