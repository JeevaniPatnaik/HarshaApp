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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harsha.harshaapp.bean.BaselineHeadInfo2;
import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.Disabilities;
import com.harsha.harshaapp.bean.Education;
import com.harsha.harshaapp.bean.EducationStatus;
import com.harsha.harshaapp.bean.MaritalStatus;
import com.harsha.harshaapp.bean.MemberInfo;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Relationship;
import com.harsha.harshaapp.bean.Religion;
import com.harsha.harshaapp.bean.Scheme;
import com.harsha.harshaapp.bean.SocialCategory;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

import java.util.ArrayList;

/**
 * Created by Jeevani on 12/7/2016.
 */
public class AddFamilyMemberInformation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BaselineHeadInfo2 baseHead = new BaselineHeadInfo2();

    DBHandler dbHandler = new DBHandler(AddFamilyMemberInformation.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    SocialCategory socialCategoryBean = new SocialCategory();
    Occupation occupationBean = new Occupation();
    Disabilities disabilitiesBean = new Disabilities();
    Relationship relationshipBean = new Relationship();
    Education educationBean = new Education();
    EducationStatus educationStatusBean = new EducationStatus();
    Religion religionBean = new Religion();
    Scheme schemeBean = new Scheme();
    MaritalStatus maritalStatusBean = new MaritalStatus();

    TextView nav_username,nav_email;

    TextView familyInformation, personalInformation;
    EditText name,dob,aadhaarCard,voterId,personalSalary;
    RadioGroup gender;
    RadioButton male,female,other;
    Spinner socialCategory, occupation, disabilities, relationship, education, educationStatus, maritalStatus, religion, centralScheme;
    Button add;

    MemberInfo memberInfo = new MemberInfo();

    ArrayList<String> nameSocialCategory = new ArrayList<String>();
    ArrayList<String> nameOccupation = new ArrayList<String>();
    ArrayList<String> nameDisability = new ArrayList<String>();
    ArrayList<String> nameRelationship = new ArrayList<String>();
    ArrayList<String> nameEducation = new ArrayList<String>();
    ArrayList<String> nameEducationStatus = new ArrayList<String>();
    ArrayList<String> nameReligion = new ArrayList<String>();
    ArrayList<String> nameCentralScheme = new ArrayList<String>();
    ArrayList<String> nameMaritalStatus = new ArrayList<String>();


    ArrayList<SocialCategory> listSocialCategory;
    ArrayList<Occupation> listOccupation;
    ArrayList<Disabilities> listDisability;
    ArrayList<Relationship> listRelationship;
    ArrayList<Education> listEducation;
    ArrayList<EducationStatus> listEducationStatus;
    ArrayList<Religion> listReligion;
    ArrayList<Scheme> listCentalScheme;
    ArrayList<MaritalStatus> listMaritalStatus;

    String result = "";
    int flag = 1;

    BaselineInfo baselineInfo = new BaselineInfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member_add_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyInformation = (TextView) findViewById(R.id.familyInformation);
        personalInformation = (TextView) findViewById(R.id.personalInformation);
        name = (EditText) findViewById(R.id.memberName);
        dob = (EditText) findViewById(R.id.memberDob);
        aadhaarCard = (EditText) findViewById(R.id.memberAadhaarCard);
        voterId = (EditText) findViewById(R.id.memberVoterId);
        personalSalary = (EditText) findViewById(R.id.memberPersonalSalary);
        gender = (RadioGroup) findViewById(R.id.memberGender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        other = (RadioButton) findViewById(R.id.other);
        socialCategory = (Spinner) findViewById(R.id.memberSocialCategory);
        occupation = (Spinner) findViewById(R.id.memberOccupation);
        disabilities = (Spinner) findViewById(R.id.memberDisabilities);
        relationship = (Spinner) findViewById(R.id.memberRelationship);
        education = (Spinner) findViewById(R.id.memberEducation);
        educationStatus = (Spinner) findViewById(R.id.memberEducationStatus);
        maritalStatus = (Spinner) findViewById(R.id.memberMaritalStatus);
        religion = (Spinner) findViewById(R.id.memberReligion);
        centralScheme = (Spinner) findViewById(R.id.memberCentralScheme);
        add = (Button) findViewById(R.id.memberAdd);

        Intent receive = getIntent();

        baseHead.baselineId = receive.getIntExtra("baselineId", 0);
        baseHead.memberName = receive.getStringExtra("memberName");
        baseHead.stateId = receive.getIntExtra("stateId", 0);
        baseHead.districtId = receive.getIntExtra("districtId", 0);
        baseHead.blockId = receive.getIntExtra("blockId", 0);
        baseHead.villageId = receive.getIntExtra("villageId", 0);
        baseHead.stateName = receive.getStringExtra("stateName");
        baseHead.districtName = receive.getStringExtra("districtName");
        baseHead.blockName = receive.getStringExtra("blockName");
        baseHead.villageName = receive.getStringExtra("villageName");

        bundle = receive.getExtras();
        user = dbHandler.getUserDetail();

        AddFamilyMemberAsyncTask obj = new AddFamilyMemberAsyncTask(this);
        obj.execute();

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
                            socialCategoryBean = sc;
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
                            occupationBean = oc;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        disabilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listDisability.size(); i++) {
                        Disabilities disb = listDisability.get(i);
                        if(item.equalsIgnoreCase(disb.getDisabilitiesName() + "-" + disb.getDisbilitiesCode())) {
                            disabilitiesBean = disb;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        relationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listRelationship.size(); i++) {
                        Relationship rls = listRelationship.get(i);
                        if(item.equalsIgnoreCase(rls.getRelationshipName() + "-" + rls.getRelationshipCode())) {
                            relationshipBean = rls;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listEducation.size(); i++) {
                        Education edu = listEducation.get(i);
                        if(item.equalsIgnoreCase(edu.getEducationName() + "-" + edu.getEducationCode())) {
                            educationBean = edu;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        educationStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listEducationStatus.size(); i++) {
                        EducationStatus educ = listEducationStatus.get(i);
                        if(item.equalsIgnoreCase(educ.getEducationStatusName() + "-" + educ.getEducationStatusCode())) {
                            educationStatusBean = educ;
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
                            religionBean = rl;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        centralScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listCentalScheme.size(); i++) {
                        Scheme sch = listCentalScheme.get(i);
                        if(item.equalsIgnoreCase(sch.getSchemeName() + "-" + sch.getSchemeId())) {
                            schemeBean = sch;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    for(int i=0; i<listMaritalStatus.size(); i++) {
                        MaritalStatus mar = listMaritalStatus.get(i);
                        if(item.equalsIgnoreCase(mar.getMaritalStatusName() + "-" + mar.getMaritalStatusCode())) {
                            maritalStatusBean = mar;
                            break;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag=2;

                AddFamilyMemberAsyncTask obj1 = new AddFamilyMemberAsyncTask(AddFamilyMemberInformation.this);
                obj1.execute();

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

    public String returnGender(int id){
        RadioButton radioButton = (RadioButton) findViewById(id);
        return radioButton.getText().toString();
    }

    public void readData() {

        memberInfo.setMemberName(name.getText().toString());
        memberInfo.setDob(dob.getText().toString());
        memberInfo.setGender(returnGender(gender.getCheckedRadioButtonId()));
        memberInfo.setSocialCategoryId(socialCategoryBean.getSocialCategoryId());
        memberInfo.setAadhaarCardId(aadhaarCard.getText().toString());
        memberInfo.setVoterId(voterId.getText().toString());
        memberInfo.setPersonalSalary(personalSalary.getText().toString());
        memberInfo.setOccupationId(occupationBean.getOccupationId());
        memberInfo.setDisabilityId(disabilitiesBean.getDisabilitiesId());
        memberInfo.setRelationshipId(relationshipBean.getRelationshipId());
        memberInfo.setEducationId(educationBean.getEducationId());
        memberInfo.setEducationStatusId(educationStatusBean.getEducationStatusId());
        memberInfo.setMaritalStatusId(maritalStatusBean.getMaritalStatusId());
        memberInfo.setReligionId(religionBean.getReligionId());
        memberInfo.setSchemeId(schemeBean.getSchemeId());

        Log.d("user=",user + "\nuserId="+user.getUserId()+"\nUsername="+user.getUserName());

        dbHandler.insertMemberInformation(memberInfo);

        Log.d("userBundle:", "memberId=" + memberInfo.getMemberId()
                + "\nmemberName=" + memberInfo.getMemberName()
                + "\nDOB=" + memberInfo.getDob()
                + "\ngender=" + memberInfo.getGender()
                + "\nsocialCategoryId=" + memberInfo.getSocialCategoryId()
                + "\nAashaarCardId=" + memberInfo.getAadhaarCardId()
                + "\nVoterId=" + memberInfo.getVoterId()
                + "\npersonalSalary=" + memberInfo.getPersonalSalary()
                + "\noccupationId=" + memberInfo.getOccupationId()
                + "\ndissabilitiesId=" + memberInfo.getDisabilityId()
                + "\nrelationshipId=" + memberInfo.getRelationshipId()
                + "\neducationId=" + memberInfo.getEducationId()
                + "\neducationStatusId=" + memberInfo.getEducationStatusId()
                + "\nmaritalStatusId=" + memberInfo.getMaritalStatusId()
                + "\nreligionId=" + memberInfo.getReligionId()
                + "\nSchemeId=" + memberInfo.getSchemeId());
    }

    public void spinnerList() {
        //Spinner
        listOccupation = dbHandler.getAllOccupation();
        listDisability = dbHandler.getAllDisability();
        listRelationship = dbHandler.getAllRelationship();
        listEducation = dbHandler.getAllEducation();
        listEducationStatus = dbHandler.getAllEducationStatus();
        listReligion = dbHandler.getAllReligion();
        listCentalScheme = dbHandler.getAllScheme();
        listMaritalStatus = dbHandler.getAllMaritalStatus();
        listSocialCategory = dbHandler.getAllSocialCategory();

        String sc = "---- Select Social Category ----";
        String oc = "---- Select Occupation ----";
        String ds = "---- Select Disability ----";
        String re = "---- Select Relationship ----";
        String ed = "---- Select Education ----";
        String es = "---- Select Education Status ----";
        String rl = "---- Select Religion ----";
        String cs = "---- Select Central Scheme ----";
        String ms = "---- Select Marital Status ----";

        nameSocialCategory.add(sc);
        nameOccupation.add(oc);
        nameDisability.add(ds);
        nameRelationship.add(re);
        nameEducation.add(ed);
        nameEducationStatus.add(es);
        nameReligion.add(rl);
        nameCentralScheme.add(cs);
        nameMaritalStatus.add(ms);

        for(int i=0; i<listSocialCategory.size(); i++) {
            SocialCategory socl = listSocialCategory.get(i);
            String name = socl.getSocialCategoryName() + "-" + socl.getSocialCategoryCode();
            nameSocialCategory.add(name);
        }

        for(int i=0; i<listOccupation.size(); i++) {
            Occupation occp = listOccupation.get(i);
            String name = occp.getOccupationName() + "-" + occp.getOccupationCode();
            nameOccupation.add(name);
        }

        for(int i=0; i<listDisability.size(); i++) {
            Disabilities dis = listDisability.get(i);
            String name = dis.getDisabilitiesName() + "-" + dis.getDisbilitiesCode();
            nameDisability.add(name);
        }

        for(int i=0; i<listRelationship.size(); i++) {
            Relationship rels = listRelationship.get(i);
            String name = rels.getRelationshipName() + "-" + rels.getRelationshipCode();
            nameRelationship.add(name);
        }

        for(int i=0; i<listEducation.size(); i++) {
            Education educ = listEducation.get(i);
            String name = educ.getEducationName() + "-" + educ.getEducationCode();
            nameEducation.add(name);
        }

        for(int i=0; i<listEducationStatus.size(); i++) {
            EducationStatus edst = listEducationStatus.get(i);
            String name = edst.getEducationStatusName() + "-" + edst.getEducationStatusCode();
            nameEducationStatus.add(name);
        }

        for(int i=0; i<listReligion.size(); i++) {
            Religion rell = listReligion.get(i);
            String name = rell.getReligionName() + "-" + rell.getReligionCode();
            nameReligion.add(name);
        }

        for(int i=0; i<listCentalScheme.size(); i++) {
            Scheme csc = listCentalScheme.get(i);
            String name = csc.getSchemeName() + "-" + csc.getSchemeId();
            nameCentralScheme.add(name);
        }

        for(int i=0; i<listMaritalStatus.size(); i++) {
            MaritalStatus mst = listMaritalStatus.get(i);
            String name = mst.getMaritalStatusName() + "-" + mst.getMaritalStatusCode();
            nameMaritalStatus.add(name);
        }

        ArrayAdapter<String> socialCategoryListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameSocialCategory);
        socialCategoryListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialCategory.setAdapter(socialCategoryListAdapter);

        ArrayAdapter<String> occupationListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameOccupation);
        occupationListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(occupationListAdapter);

        ArrayAdapter<String> disabilitiesListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameDisability);
        disabilitiesListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disabilities.setAdapter(disabilitiesListAdapter);

        ArrayAdapter<String> relationshipListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameRelationship);
        relationshipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationship.setAdapter(relationshipListAdapter);

        ArrayAdapter<String> educationListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameEducation);
        educationListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        education.setAdapter(educationListAdapter);

        ArrayAdapter<String> educationStatusListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameEducationStatus);
        educationStatusListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationStatus.setAdapter(educationStatusListAdapter);

        ArrayAdapter<String> religionListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameReligion);
        religionListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religion.setAdapter(religionListAdapter);

        ArrayAdapter<String> centralSchemeListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameCentralScheme);
        centralSchemeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        centralScheme.setAdapter(centralSchemeListAdapter);

        ArrayAdapter<String> maritalStatusListAdapter = new ArrayAdapter<String>(AddFamilyMemberInformation.this, android.R.layout.simple_spinner_item, nameMaritalStatus);
        maritalStatusListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatus.setAdapter(maritalStatusListAdapter);
    }

    class AddFamilyMemberAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public AddFamilyMemberAsyncTask(Context mContext) {
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

                dbHandler.insertMemberInformation(memberInfo);
                int memberId = dbHandler.getLastMemberId();
                dbHandler.updateMemberUniqueId(memberId, baseHead.baselineId);

                /*int memberId = dbHandler.getLastMemberId();
                baselineInfo.setFamilyHeadId(memberId);
                dbHandler.insertBaselineInformation(baselineInfo);
                dbHandler.getAllBaselineInformation();
                int baselineId = dbHandler.getLastBaselineId();
                dbHandler.updateMemberUniqueId(memberId,baselineId);*//*
                Log.d("MemberData:", "memberId=" + memberInfo.getMemberId()
                        + "\nmemberName=" + memberInfo.getMemberName()
                        + "\nDOB=" + memberInfo.getDob()
                        + "\ngender=" + memberInfo.getGender()
                        + "\nsocialCategoryId=" + memberInfo.getSocialCategoryId()
                        + "\nAashaarCardId=" + memberInfo.getAadhaarCardId()
                        + "\nVoterId=" + memberInfo.getVoterId()
                        + "\npersonalSalary=" + memberInfo.getPersonalSalary()
                        + "\noccupationId=" + memberInfo.getOccupationId()
                        + "\ndissabilitiesId=" + memberInfo.getDisabilityId()
                        + "\nrelationshipId=" + memberInfo.getRelationshipId()
                        + "\neducationId=" + memberInfo.getEducationId()
                        + "\neducationStatusId=" + memberInfo.getEducationStatusId()
                        + "\nmaritalStatusId=" + memberInfo.getMaritalStatusId()
                        + "\nreligionId=" + memberInfo.getReligionId()
                        + "\nSchemeId=" + memberInfo.getSchemeId());*/

                Toast.makeText(AddFamilyMemberInformation.this,"Successfully Inserted in Member Table",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddFamilyMemberInformation.this,BaselineInformation.class);
                //intent.putExtra(bundle);
                startActivity(intent);

            }
            else if (flag==1) {
                progressDialog.dismiss();
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
