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

import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Religion;
import com.harsha.harshaapp.bean.SocialCategory;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.bean.Village;
import com.harsha.harshaapp.database.DBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Jeevani on 12/14/2016.
 */
//jeevani
public class CreateBaselineInformation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String URL1 = "https://harsha-guptas.rhcloud.com/api/household/add";
    String URL2 = "";
    //String result="";

    int flag = 1;

    Village finalVillage;
    Religion finalReligion;
    SocialCategory finalSocialCategory;
    Occupation finalOccupation;

    ArrayList<Village> villageArray = new ArrayList<Village>();
    ArrayList<Religion> religionArray = new ArrayList<Religion>();
    ArrayList<SocialCategory> socialCategoryArray = new ArrayList<SocialCategory>();
    ArrayList<Occupation> occupationArray = new ArrayList<Occupation>();

    String villageUrl = "https://harsha-guptas.rhcloud.com/api/village/getallstate";
    String religionUrl = "https://harsha-guptas.rhcloud.com/api/religion/getallreligion";
    String socialCategoryUrl = "https://harsha-guptas.rhcloud.com/api/socialcategory/getallsocialcategory";
    String occupationUrl = "https://harsha-guptas.rhcloud.com/api/occupation/getalloccupation";

    ArrayList<String> nameVillage = new ArrayList<String>();
    ArrayList<String> nameReligion = new ArrayList<String>();
    ArrayList<String> nameSocialCategory = new ArrayList<String>();
    ArrayList<String> nameOccupation = new ArrayList<String>();

    DBHandler dbHandler = new DBHandler(CreateBaselineInformation.this, null, null, 1);
    Bundle bundle;
    User user = new User();
    BaselineInfo baselineInfo = new BaselineInfo();

    TextView nav_username,nav_email;

    EditText familyHeadName,contactNo,income,noOfFamilyMember;
    TextView stateName,districtName,blockName;
    Spinner village,religion,occupation,socialCategory;
    Button confirm;

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
        confirm = (Button) findViewById(R.id.confirm);

        villageArray.add(new Village(0,  "---- Select Village ----","0",0));
        religionArray.add(new Religion(0, "---- Select Religion ----", "0"));
        socialCategoryArray.add(new SocialCategory(0, "---- Select Social Category ----", "0"));
        occupationArray.add(new Occupation(0,"---- Select Occupation ----","0"));

        CreateBaselineInformationAsyncTask obj1 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
        obj1.execute(villageUrl);
        CreateBaselineInformationAsyncTask obj2 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
        obj2.execute(religionUrl);
        CreateBaselineInformationAsyncTask obj3 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
        obj3.execute(socialCategoryUrl);
        CreateBaselineInformationAsyncTask obj4 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
        obj4.execute(occupationUrl);

        confirm.setOnClickListener(new View.OnClickListener() {
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

                CreateBaselineInformationAsyncTask obj1 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
                obj1.execute(villageUrl);
                CreateBaselineInformationAsyncTask obj2 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
                obj2.execute(religionUrl);
                CreateBaselineInformationAsyncTask obj3 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
                obj3.execute(socialCategoryUrl);
                CreateBaselineInformationAsyncTask obj4 = new CreateBaselineInformationAsyncTask(CreateBaselineInformation.this);
                obj4.execute(occupationUrl);

               /*URL2 = URL1 + "?familyHeadName=" + familyHeadName.getText().toString() + "&stateName" + stateName.getText().toString()
                        + "&districtName" + districtName.getText().toString() + "&blockName" + blockName.getText().toString()
                        + "&contactNo" + contactNo.getText().toString() + "&income" + income.getText().toString()
                        + "&noOfFamilyMember" + noOfFamilyMember.getText().toString();
                new AddBaselineAsyncTask().execute(URL2);*/
            }
        });

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    //long pos = parent.getItemIdAtPosition(position);
                    // Showing selected spinner item
                    int i=0;
                    while(i<villageArray.size()) {
                        Village vl = villageArray.get(i);
                        Log.d("villageName", "item="+item+ " vl="+vl+" vl.getVillageName="+vl.getVillageName()+ " i="+i);
                        if(vl.getVillageName().equals(item)) {
                            finalVillage = vl;
                            break;
                        }
                        i++;
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
                    //long pos = parent.getItemIdAtPosition(position);
                    // Showing selected spinner item
                    int i=0;
                    while(i<religionArray.size()) {
                        Religion rl = religionArray.get(i);
                        Log.d("religionName", "item="+item+ " vl="+rl+" vl.getReligionName="+rl.getReligionName()+ " i="+i);
                        if(rl.getReligionName().equals(item)) {
                            finalReligion = rl;
                            break;
                        }
                        i++;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    //long pos = parent.getItemIdAtPosition(position);
                    // Showing selected spinner item
                    int i=0;
                    while(i<villageArray.size()) {
                        Village vl = villageArray.get(i);
                        Log.d("villageName", "item="+item+ " vl="+vl+" vl.getVillageName="+vl.getVillageName()+ " i="+i);
                        if(vl.getVillageName().equals(item)) {
                            finalVillage = vl;
                            break;
                        }
                        i++;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    return;
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    //long pos = parent.getItemIdAtPosition(position);
                    // Showing selected spinner item
                    int i=0;
                    while(i<villageArray.size()) {
                        Village vl = villageArray.get(i);
                        Log.d("villageName", "item="+item+ " vl="+vl+" vl.getVillageName="+vl.getVillageName()+ " i="+i);
                        if(vl.getVillageName().equals(item)) {
                            finalVillage = vl;
                            break;
                        }
                        i++;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    public void showVillage(String s) {
        Log.d("AysncTask", "onPostExecute(" + s + ")");
        // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
        try {
            String msg = "---- Select State ----";
            nameVillage.add(msg);
            JSONArray jsonArray = new JSONArray(s);
            String villageNames[] = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Village villageBean = new Village();
                villageBean.setVillageName(jsonObject.getString("villageName"));
                villageBean.setVillageId(jsonObject.getInt("villageId"));
                villageBean.setVillageCode(jsonObject.getString("villageCode"));
                villageArray.add(villageBean);
                //stateNames[i] = state.getStateName();
                nameVillage.add(villageBean.getVillageName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> stateListAdapter = new ArrayAdapter<String>(CreateBaselineInformation.this, android.R.layout.simple_spinner_item, nameVillage);
        stateListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village.setAdapter(stateListAdapter);
    }

    public String testingRestAPI(String urls) {
        StringBuilder result = new StringBuilder();
        HttpsURLConnection httpsURLConnection = null;
        Log.d("Line-61 - urls:",urls);
        try {
            URL url = new URL(urls);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setConnectTimeout(30 * 1000);
            httpsURLConnection.setReadTimeout(30 * 1000);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);

            httpsURLConnection.connect();
            DataOutputStream out = new DataOutputStream(httpsURLConnection.getOutputStream());
            //out.writeBytes(parameter);
            out.flush();
            out.close();

            Log.d("Line-72 - https:",httpsURLConnection.toString());

            Log.d("Line-721 - status code:","" + httpsURLConnection.getResponseCode());

            if (httpsURLConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                Log.d("Line-78 - reader:",reader.toString());
                String line;
                while((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //httpsURLConnection.disconnect();
        }

        Log.d("Line-94 - result:",result.toString());
        return result.toString();
    }

    class CreateBaselineInformationAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public CreateBaselineInformationAsyncTask(Context mContext) {
            this.mContext = mContext;
            progressDialog = new ProgressDialog(mContext);
        }

        @Override
        protected String doInBackground(String... params) {

            Log.d("Line-117 - AysncTask:", "doInBackground");
            return testingRestAPI(URL1);

        }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle(R.string.app_name);
            progressDialog.setMessage("Loading, Please Wait...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {

            if(flag==1) {
                showVillage(s);
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

   /* class AddBaselineAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                //Log.d("URL2",URL2);
                URL url=new URL(URL2);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value=bufferedReader.readLine();
                System.out.println("Result is: "+value);
                result=value;
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
            // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    baselineInfo.setBaselineId(jsonObject.getInt("baselineId"));

                    if(baselineInfo.getBaselineId()>0) {

                        baselineInfo.setFamilyHeadId(jsonObject.getInt("familyHeadId"));
                        baselineInfo.setStateId(jsonObject.getInt("stateId"));
                        baselineInfo.setDistrictId(jsonObject.getInt("districtId"));
                        baselineInfo.setVillageId(jsonObject.getInt("villageId"));
                        baselineInfo.setSurveyUserId(jsonObject.getInt("surveyUserId"));
                        baselineInfo.setSocialCategoryId(jsonObject.getInt("socialCategoryId"));
                        baselineInfo.setReligionId(jsonObject.getInt("religionId"));
                        baselineInfo.setOccupationId(jsonObject.getInt("occupationId"));
                        baselineInfo.setContactNo(jsonObject.getInt("contactNo"));
                        baselineInfo.setFamilyMemberNumber(jsonObject.getString("familyMemberNumber"));
                        baselineInfo.setIncome(jsonObject.getString("income"));

                        Log.d("roleName", user.getRoleName());
                    } else {
                        familyHeadName.setText("");
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
*/
}
