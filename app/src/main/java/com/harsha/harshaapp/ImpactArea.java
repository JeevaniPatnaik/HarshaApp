package com.harsha.harshaapp;

/**
 * Created by Jeevani on 12/4/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.harsha.harshaapp.bean.BaselineHeadInfo2;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

import java.util.ArrayList;

public class ImpactArea extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(ImpactArea.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    int flag=0;
    String result="";

    private ListView list;
    private ArrayAdapter arrayAdapter;
    private String[] familyHeadArr = { "Family Head-1", "Family Head-2", "Family Head-3", "Family Head-4", "Family Head-5",
            "Family Head-6", "Family Head-7", "Family Head-8", "Family Head-9", "Family Head-10" };

    ArrayList<String> baselineList = new ArrayList<String>();

    ArrayList<BaselineHeadInfo2> baselineHeadInfo = new ArrayList<BaselineHeadInfo2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseline_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.lists);

        flag = 1;
        DisplayAllImpactAsyncTask obj1 = new DisplayAllImpactAsyncTask(ImpactArea.this);
        obj1.execute();
/*
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, familyHeadArr);
        list.setAdapter(arrayAdapter);
*/
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {

                //String item = ((TextView)view).getText().toString();

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ImpactArea.this );

                // set title
                alertDialogBuilder.setTitle("Want To See Impact Information");

                // set dialog message
                alertDialogBuilder
                        .setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                BaselineHeadInfo2 baseHead = baselineHeadInfo.get(position);
                                Intent intent = new Intent(getApplicationContext(),Farm.class);
                                //Bundle bundle = new Bundle();
                                intent.putExtra("baselineHeadInfoPosition", position);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

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

    public void displayList() {

        baselineHeadInfo = dbHandler.getAllBaselineHeadInformation2();

        for(int i=0; i<baselineHeadInfo.size(); i++) {
            BaselineHeadInfo2 baseHead = baselineHeadInfo.get(i);
            String text = "-0"+ "-" + i + "-" + baseHead.voterId + "-" + baseHead.memberName + "-" + baseHead.familyHead;
            baselineList.add(text);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, baselineList);
        list.setAdapter(arrayAdapter);
    }

    public void readData() {

    }

    class DisplayAllImpactAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public DisplayAllImpactAsyncTask(Context mContext) {
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

             /*   Bundle userBundle = new Bundle();

                BaselineInfo baselineInfo = new BaselineInfo();
                MemberInfo memberInfo = new MemberInfo();

                userBundle.putInt("baselineId", baselineInfo.getBaselineId());
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

                userBundle.putInt("memberId", memberInfo.getMemberId());
                userBundle.putInt("uniqueId", memberInfo.getUniqueId());
                userBundle.putString("memberName", memberInfo.getMemberName());
                userBundle.putString("dob", memberInfo.getDob());
                userBundle.putString("gender", memberInfo.getGender());
                userBundle.putInt("socialCategoryId", memberInfo.getSocialCategoryId());
                userBundle.putString("aadhaarCardId", memberInfo.getAadhaarCardId());
                userBundle.putString("voterId", memberInfo.getVoterId());
                userBundle.putString("familyHead", memberInfo.getFamilyHead());
                userBundle.putString("personalSalary", memberInfo.getPersonalSalary());
                userBundle.putInt("occupationId", memberInfo.getOccupationId());
                userBundle.putInt("disabilityId", memberInfo.getDisabilityId());
                userBundle.putInt("relationshipId", memberInfo.getRelationshipId());
                userBundle.putInt("educationId", memberInfo.getEducationId());
                userBundle.putInt("educationStatusId", memberInfo.getEducationStatusId());
                userBundle.putInt("maritalStatusId", memberInfo.getMaritalStatusId());
                userBundle.putInt("religionId", memberInfo.getReligionId());
                userBundle.putInt("schemeId", memberInfo.getSchemeId());


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
                        + "\nincome=" + baselineInfo.getIncome()
                        + "\nbaselineid=" + baselineInfo.getBaselineId());*/
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