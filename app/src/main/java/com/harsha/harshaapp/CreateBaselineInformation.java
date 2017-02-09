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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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


    ArrayList<String> nameVillage = new ArrayList<String>();
    ArrayList<String> nameReligion = new ArrayList<String>();
    ArrayList<String> nameSocialCategory = new ArrayList<String>();
    ArrayList<String> nameOccupation = new ArrayList<String>();

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

    EditText contactNo,income,noOfFamilyMember;
    TextView stateName,districtName,blockName;
    Spinner village,religion,occupation,socialCategory;
    Button next;

    String result = "";
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_baseline_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactNo = (EditText) findViewById(R.id.contactNo);
        income = (EditText) findViewById(R.id.create_baseline_income);
        stateName = (TextView) findViewById(R.id.stateName);
        districtName = (TextView) findViewById(R.id.districtName);
        blockName = (TextView) findViewById(R.id.blockName);
        village = (Spinner) findViewById(R.id.villageName);
        religion = (Spinner) findViewById(R.id.religion);
        occupation = (Spinner) findViewById(R.id.occupation);
        socialCategory = (Spinner) findViewById(R.id.socialCategory);
        noOfFamilyMember = (EditText) findViewById(R.id.noOfFamilyMember);
        next = (Button) findViewById(R.id.next);

        Intent receive = getIntent();
        bundle = receive.getExtras();
        user = dbHandler.getUserDetail();

        AddBaselineAsyncTask obj = new AddBaselineAsyncTask(this);
        obj.execute();

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listVillage.size(); i++) {
                        Village vll = listVillage.get(i);
                        if(item.equalsIgnoreCase(vll.getVillageName() + "-" + vll.getVillageCode())) {
                            villageList = vll;
                            break;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        religion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listReligion.size(); i++) {
                        Religion rl = listReligion.get(i);
                        if(item.equalsIgnoreCase(rl.getReligionName() + "-" + rl.getReligionCode())) {
                            religionList = rl;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listOccupation.size(); i++) {
                        Occupation oc = listOccupation.get(i);
                        if(item.equalsIgnoreCase(oc.getOccupationName() + "-" + oc.getOccupationCode())) {
                            occupationList = oc;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        socialCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listSocialCategory.size(); i++) {
                        SocialCategory sc = listSocialCategory.get(i);
                        if(item.equalsIgnoreCase(sc.getSocialCategoryName() + "-" + sc.getSocialCategoryCode())) {
                            socialCategoryList = sc;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                flag=2;
                AddBaselineAsyncTask obj1 = new AddBaselineAsyncTask(CreateBaselineInformation.this);
                obj1.execute();
                //Intent intent = new Intent(CreateBaselineInformation.this,AddMemberInformation.class);
                //intent.putExtra(bundle);
                //startActivity(intent);
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

    public void readData() {

        baselineInfo.setStateId(state.getStateId());
        baselineInfo.setDistrictId(district.getDistrictId());
        baselineInfo.setBlockId(block.getBlockId());
        baselineInfo.setVillageId(villageList.getVillageId());
        baselineInfo.setReligionId(religionList.getReligionId());
        baselineInfo.setOccupationId(occupationList.getOccupationId());
        baselineInfo.setSocialCategoryId(socialCategoryList.getSocialCategoryId());
        baselineInfo.setFamilyMemberNumber(Integer.parseInt(noOfFamilyMember.getText().toString()));
        baselineInfo.setContactNo(contactNo.getText().toString());
        baselineInfo.setIncome(income.getText().toString());
        baselineInfo.setSurveyUserId(user.getUserId());
        Log.d("income=","Income="+income.getText().toString()+"\ngetIncome="+baselineInfo.getIncome());
        Log.d("user=",user + "\nuserId="+user.getUserId()+"\nUsername="+user.getUserName());

        //dbHandler.insertBaselineInformation(baselineInfo);
    }

    public void spinnerList() {
        //Spinner
        listVillage = dbHandler.getAllVillage();
        listReligion = dbHandler.getAllReligion();
        listOccupation = dbHandler.getAllOccupation();
        listSocialCategory = dbHandler.getAllSocialCategory();

        //TextView
        state = dbHandler.getLastState();
        district = dbHandler.getLastDistrict();
        block = dbHandler.getLastBlock();

        stateName.setText(state.getStateName() + "-" + state.getStateCode());
        districtName.setText(district.getDistrictName()+ "-"+district.getDistrictCode());
        blockName.setText(block.getBlockName()+"-"+block.getBlockCode());

        String vl = "---- Select Village ----";
        String oc = "---- Select Occupation ----";
        String rl = "---- Select Religion ----";
        String sc = "---- Select Social Category ----";

        nameVillage.add(vl);
        nameOccupation.add(oc);
        nameReligion.add(rl);
        nameSocialCategory.add(sc);

        for(int i=0; i<listVillage.size(); i++) {
            Village vill = listVillage.get(i);
            String name = vill.getVillageName() + "-" + vill.getVillageCode();
            nameVillage.add(name);
        }

        for(int i=0; i<listReligion.size(); i++) {
            Religion rell = listReligion.get(i);
            String name = rell.getReligionName() + "-" + rell.getReligionCode();
            nameReligion.add(name);
        }

        for(int i=0; i<listOccupation.size(); i++) {
            Occupation occp = listOccupation.get(i);
            String name = occp.getOccupationName() + "-" + occp.getOccupationCode();
            nameOccupation.add(name);
        }

        for(int i=0; i<listSocialCategory.size(); i++) {
            SocialCategory socl = listSocialCategory.get(i);
            String name = socl.getSocialCategoryName() + "-" + socl.getSocialCategoryCode();
            nameSocialCategory.add(name);
        }

        ArrayAdapter<String> villageListAdapter = new ArrayAdapter<String>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, nameVillage);
        villageListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village.setAdapter(villageListAdapter);

        ArrayAdapter<String> religionListAdapter = new ArrayAdapter<String>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, nameReligion);
        religionListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religion.setAdapter(religionListAdapter);

        ArrayAdapter<String> occupationListAdapter = new ArrayAdapter<String>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, nameOccupation);
        occupationListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(occupationListAdapter);

        ArrayAdapter<String> socialCategoryListAdapter = new ArrayAdapter<String>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, nameSocialCategory);
        socialCategoryListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialCategory.setAdapter(socialCategoryListAdapter);
    }

    class AddBaselineAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public AddBaselineAsyncTask(Context mContext) {
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

                Bundle userBundle = new Bundle();

                userBundle.putInt("stateId", baselineInfo.getStateId());
                userBundle.putInt("districtId", baselineInfo.getDistrictId());
                userBundle.putInt("blockId", baselineInfo.getBlockId());
                userBundle.putInt("villageId", baselineInfo.getVillageId());
                userBundle.putInt("surveyUserId", baselineInfo.getSurveyUserId());
                userBundle.putInt("socialCategoryId", baselineInfo.getSocialCategoryId());
                userBundle.putInt("religionId", baselineInfo.getReligionId());
                userBundle.putInt("occupationId", baselineInfo.getOccupationId());
                userBundle.putString("contactNo", baselineInfo.getContactNo());
                userBundle.putInt("familyMemberNumber", baselineInfo.getFamilyMemberNumber());
                userBundle.putString("income", baselineInfo.getIncome());

                Log.d("userBundle:", "stateId=" + baselineInfo.getStateId()
                        + "\ndistrictId=" + baselineInfo.getDistrictId()
                        + "\nblockId=" + baselineInfo.getBlockId()
                        + "\nvillageId=" + baselineInfo.getVillageId()
                        + "\nsurveyUserId=" + baselineInfo.getSurveyUserId()
                        + "\nsocialCategoryId=" + baselineInfo.getSocialCategoryId()
                        + "\nreligionId=" + baselineInfo.getReligionId()
                        + "\noccupationId=" + baselineInfo.getOccupationId()
                        + "\ncontactNo=" + baselineInfo.getContactNo()
                        + "\nfamilyMemberNumber=" + baselineInfo.getFamilyMemberNumber()
                        + "\nincome=" + baselineInfo.getIncome());

                Toast.makeText(CreateBaselineInformation.this, "Baseline Information is added successfully in Bundle", Toast.LENGTH_LONG).show();

                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        baselineInfo.setBaselineId(object.getInt("baselineId"));

                        if(baselineInfo.getBaselineId()>0) {

                        baselineInfo.setStateId(object.getInt("stateId"));
                        baselineInfo.setDistrictId(object.getInt("districtId"));
                        baselineInfo.setBlockId(object.getInt("blockId"));
                        baselineInfo.setVillageId(object.getInt("villageId"));
                        baselineInfo.setReligionId(object.getInt("religionId"));
                        baselineInfo.setOccupationId(object.getInt("occupationId"));
                        baselineInfo.setSocialCategoryId(object.getInt("socialCategoryId"));
                        baselineInfo.setFamilyMemberNumber(object.getInt("noOfFamilyMember"));
                        baselineInfo.setContactNo(object.getString("contactNo"));
                        baselineInfo.setIncome(object.getString("income"));
                        baselineInfo.setSurveyUserId(object.getInt("userId"));

                        Log.d("Baseline Info:", "Baseline=" + dbHandler.getAllBaselineInformation());

                        dbHandler.insertBaselineInformation(baselineInfo);

                        Intent intent1 = new Intent(CreateBaselineInformation.this, AddMemberInformation.class);
                        intent1.putExtras(userBundle);
                        startActivity(intent1);
                        }
                        else {
                            Toast.makeText(CreateBaselineInformation.this, "Baseline Information can't be added", Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                /*try {

                    JSONArray array = new JSONArray(s);

                    if (array.length() > 0) {

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object = array.getJSONObject(i);

                            baselineInfo.setStateId(object.getInt("stateId"));
                            baselineInfo.setDistrictId(object.getInt("districtId"));
                            baselineInfo.setBlockId(object.getInt("blockId"));
                            baselineInfo.setVillageId(object.getInt("villageId"));
                            baselineInfo.setReligionId(object.getInt("religionId"));
                            baselineInfo.setOccupationId(object.getInt("occupationId"));
                            baselineInfo.setSocialCategoryId(object.getInt("socialCategoryId"));
                            baselineInfo.setFamilyMemberNumber(object.getInt("noOfFamilyMember"));
                            baselineInfo.setContactNo(object.getString("contactNo"));
                            baselineInfo.setIncome(object.getString("income"));
                            baselineInfo.setSurveyUserId(object.getInt("userId"));

                            dbHandler.insertBaselineInformation(baselineInfo);

                            Bundle userBundle = new Bundle();

                            userBundle.putInt("stateId", baselineInfo.getStateId());
                            userBundle.putInt("districtId", baselineInfo.getDistrictId());
                            userBundle.putInt("blockId", baselineInfo.getBlockId());
                            userBundle.putInt("villageId", baselineInfo.getVillageId());
                            userBundle.putInt("surveyUserId", baselineInfo.getSurveyUserId());
                            userBundle.putInt("socialCategoryId", baselineInfo.getSocialCategoryId());
                            userBundle.putInt("religionId", baselineInfo.getReligionId());
                            userBundle.putInt("occupationId", baselineInfo.getOccupationId());
                            userBundle.putString("contactNo", baselineInfo.getContactNo());
                            userBundle.putInt("familyMemberNumber", baselineInfo.getFamilyMemberNumber());
                            userBundle.putString("income", baselineInfo.getIncome());

                            Log.d("userBundle:", "stateId=" + baselineInfo.getStateId()
                                    + "\ndistrictId=" + baselineInfo.getDistrictId()
                                    + "\nblockId=" + baselineInfo.getBlockId()
                                    + "\nvillageId=" + baselineInfo.getVillageId()
                                    + "\nsurveyUserId=" + baselineInfo.getSurveyUserId()
                                    + "\nsocialCategoryId=" + baselineInfo.getSocialCategoryId()
                                    + "\nreligionId=" + baselineInfo.getReligionId()
                                    + "\noccupationId=" + baselineInfo.getOccupationId()
                                    + "\ncontactNo=" + baselineInfo.getContactNo()
                                    + "\nfamilyMemberNumber=" + baselineInfo.getFamilyMemberNumber()
                                    + "\nincome=" + baselineInfo.getIncome());

                            Toast.makeText(CreateBaselineInformation.this, "Baseline Information is added successfully in Bundle", Toast.LENGTH_LONG).show();

                            Intent intent1 = new Intent(CreateBaselineInformation.this, AddMemberInformation.class);
                            intent1.putExtras(userBundle);
                            startActivity(intent1);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
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
