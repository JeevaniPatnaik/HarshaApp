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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harsha.harshaapp.bean.Asset;
import com.harsha.harshaapp.bean.Block;
import com.harsha.harshaapp.bean.Disabilities;
import com.harsha.harshaapp.bean.District;
import com.harsha.harshaapp.bean.Education;
import com.harsha.harshaapp.bean.EducationStatus;
import com.harsha.harshaapp.bean.MaritalStatus;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Relationship;
import com.harsha.harshaapp.bean.Religion;
import com.harsha.harshaapp.bean.Scheme;
import com.harsha.harshaapp.bean.SocialCategory;
import com.harsha.harshaapp.bean.State;
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
 * Created by Jeevani on 1/4/2017.
 */
public class ServerDownload extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler dbHandler = new DBHandler(ServerDownload.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    State finalState;
    District finalDistrict;
    Block finalBlock;

    ArrayList<State> stateArray = new ArrayList<State>();
    ArrayList<District> districtArray = new ArrayList<District>();
    ArrayList<Block> blockArray = new ArrayList<Block>();
    ArrayList<Village> villageArray = new ArrayList<>();
    ArrayList<Asset> assetArray = new ArrayList<>();
    ArrayList<Disabilities> disabilitiesArray = new ArrayList<>();
    ArrayList<Occupation> occupationArray = new ArrayList<>();
    ArrayList<Religion> religionArray = new ArrayList<>();
    ArrayList<Relationship> relationshipArray = new ArrayList<>();
    ArrayList<Disabilities> disabilitieArray = new ArrayList<>();
    ArrayList<Education> educationArray = new ArrayList<>();
    ArrayList<EducationStatus> educationStatusArray = new ArrayList<>();
    ArrayList<Scheme> schemeArray = new ArrayList<>();
    ArrayList<MaritalStatus> maritalStatuseArray = new ArrayList<>();
    ArrayList<SocialCategory> socialCategoryArray = new ArrayList<>();

    String URL0 = "https://harsha-guptas.rhcloud.com/api/state/getallstate";
    String URL2 = "https://harsha-guptas.rhcloud.com/api/district/getbystateid";
    String URL3 = "https://harsha-guptas.rhcloud.com/api/block/getbydistrictid";
    String URL4 = "https://harsha-guptas.rhcloud.com/api/village/getbyblockid";
    String URL5 = "http://harsha-guptas.rhcloud.com/api/download/data";
    String URL1 = "";

    ArrayList<String> nameState = new ArrayList<String>();
    ArrayList<String> nameDistrict = new ArrayList<String>();
    ArrayList<String> nameBlock = new ArrayList<String>();


    Spinner stateName,districtName,blockName;
    Button dataDownload;

    int flag = 1;

    //ProgressDialog progressDialog = new ProgressDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_download);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stateName = (Spinner) findViewById(R.id.state);
        districtName = (Spinner) findViewById(R.id.district);
        blockName = (Spinner) findViewById(R.id.block);
        dataDownload = (Button) findViewById(R.id.dataDownload);

        stateArray.add(new State(0, "0", "---- Select State ----"));
        districtArray.add(new District(0, "---- Select District ----", "0", 0));
        blockArray.add(new Block(0, "---- Select Block ----", "0", 0));

        URL1 = URL0;
        GetAllStateAsyncTask obj = new GetAllStateAsyncTask(ServerDownload.this);
        obj.execute(URL1);

        dataDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 5;
                URL1 = URL5;
                GetAllStateAsyncTask obj =  new GetAllStateAsyncTask(ServerDownload.this);
                obj.execute(URL1);

            }
        });

        stateName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    while(i<stateArray.size()) {
                        State st = stateArray.get(i);
                        Log.d("stateName", "item="+item+ " st="+st+" st.getStateName="+st.getStateName()+ " i="+i);
                        if(st.getStateName().equals(item)) {
                            finalState = st;
                            break;
                        }
                        i++;
                    }
                    flag=2;
                    resetSpinner1();
                    URL1 = URL2 + "?stateId="+finalState.getStateId();
                    GetAllStateAsyncTask obj = new GetAllStateAsyncTask(ServerDownload.this);
                    obj.execute(URL1);
                    Toast.makeText(parent.getContext(), "Selected: " + item + " " + (position) , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    while(i<districtArray.size()) {
                        District dt = districtArray.get(i);
                        if(dt.getDistrictName().equals(item)) {
                            finalDistrict = dt;
                            break;
                        }
                        i++;
                    }
                    flag=3;
                    resetSpinner2();
                    URL1 = URL3 + "?districtId="+finalDistrict.getDistrictId();
                    GetAllStateAsyncTask obj = new GetAllStateAsyncTask(ServerDownload.this);
                    obj.execute(URL1);
                    Toast.makeText(parent.getContext(), "Selected: " + item + " " + (position) , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        blockName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    while(i<blockArray.size()) {
                        Block dt = blockArray.get(i);
                        if(dt.getBlockName().equals(item)) {
                            finalBlock = dt;
                            break;
                        }
                        i++;
                    }
                    flag=4;
                    //resetSpinner2();
                    URL1 = URL4 + "?blockId="+finalBlock.getBlockId();
                    GetAllStateAsyncTask obj = new GetAllStateAsyncTask(ServerDownload.this);
                    obj.execute(URL1);
                    Toast.makeText(parent.getContext(), "Selected: " + item + " " + (position) , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*blockName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item + " " + (position) , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/

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

    public void resetSpinner1() {
        String msg2 = "---- Select District ----";
        String msg3 = "---- Select Block ----";
        nameDistrict.clear();
        nameBlock.clear();
        nameDistrict.add(msg2);
        nameBlock.add(msg3);
        ArrayAdapter<String> districtListAdapter = new ArrayAdapter<String>(ServerDownload.this, android.R.layout.simple_spinner_item, nameDistrict);
        districtListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtName.setAdapter(districtListAdapter);
        ArrayAdapter<String> blockListAdapter = new ArrayAdapter<String>(ServerDownload.this, android.R.layout.simple_spinner_item, nameBlock);
        blockListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockName.setAdapter(blockListAdapter);
    }

    public void resetSpinner2() {
        String msg3 = "---- Select Block ----";
        nameBlock.clear();
        nameBlock.add(msg3);
        ArrayAdapter<String> blockListAdapter = new ArrayAdapter<String>(ServerDownload.this, android.R.layout.simple_spinner_item, nameBlock);
        blockListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockName.setAdapter(blockListAdapter);
    }

    public void showState(String s) {
        Log.d("AysncTask", "onPostExecute(" + s + ")");
        // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
        try {
            String msg = "---- Select State ----";
            nameState.add(msg);
            JSONArray jsonArray = new JSONArray(s);
            String stateNames[] = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                State state = new State();
                state.setStateName(jsonObject.getString("stateName"));
                state.setStateId(jsonObject.getInt("stateId"));
                state.setStateCode(jsonObject.getString("stateCode"));
                stateArray.add(state);
                //stateNames[i] = state.getStateName();
                nameState.add(state.getStateName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> stateListAdapter = new ArrayAdapter<String>(ServerDownload.this, android.R.layout.simple_spinner_item, nameState);
        stateListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateName.setAdapter(stateListAdapter);
    }

    public void showDistrict(String s) {
        Log.d("AysncTask", "onPostExecute(" + s + ")");
        // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
        try {
            /*String msg = "---- Select District ----";
            nameDistrict.add(msg);*/
            JSONArray jsonArray = new JSONArray(s);
            String districtNames[] = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                District district = new District();
                district.setDistrictId(jsonObject.getInt("districtId"));
                district.setDistrictName(jsonObject.getString("districtName"));
                district.setDistrictCode(jsonObject.getString("districtCode"));
                district.setStateId(jsonObject.getInt("stateId"));
                districtArray.add(district);
                //stateNames[i] = state.getStateName();
                //stateNames[i] = state.getStateName();
                nameDistrict.add(district.getDistrictName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> districtListAdapter = new ArrayAdapter<String>(ServerDownload.this, android.R.layout.simple_spinner_item, nameDistrict);
        districtListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtName.setAdapter(districtListAdapter);
    }

    public void showBlock(String s) {
        Log.d("AysncTask", "onPostExecute(" + s + ")");
        // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
        try {
            /*String msg = "---- Select Block ----";
            nameBlock.add(msg);*/
            //nameBlock.add(msg);
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Block block = new Block();
                block.setBlockName(jsonObject.getString("blockName"));
                block.setDistrictId(jsonObject.getInt("districtId"));
                block.setBlockCode(jsonObject.getString("blockCode"));
                block.setBlockId(jsonObject.getInt("blockId"));
                blockArray.add(block);
                //stateNames[i] = state.getStateName();
                nameBlock.add(block.getBlockName());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> blockListAdapter = new ArrayAdapter<String>(ServerDownload.this, android.R.layout.simple_spinner_item, nameBlock);
        blockListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockName.setAdapter(blockListAdapter);
    }
    public void showVillage(String s) {
        Log.d("AysncTask", "onPostExecute(" + s + ")");
        // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
        try {
            /*String msg = "---- Select Block ----";
            nameBlock.add(msg);*/
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Village village = new Village();
                village.setVillageName(jsonObject.getString("villageName"));
                village.setVillageId(jsonObject.getInt("villageId"));
                village.setVillageCode(jsonObject.getString("villageCode"));
                village.setBlockId(jsonObject.getInt("blockId"));
                villageArray.add(village);
                //stateNames[i] = state.getStateName();
                //Toast.makeText(this,"nameBlock="+nameBlock.get(i),Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showData(String s) {
        Log.d("AysncTask", "onPostExecute(" + s + ")");
        // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
        try {
            /*String msg = "---- Select Block ----";
            nameBlock.add(msg);*/
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonAssetArray = jsonObject.getJSONArray("AssetBean");

            for (int i = 0; i < jsonAssetArray.length(); i++) {

                JSONObject jsonArrayObject = jsonAssetArray.getJSONObject(i);
                Asset asset = new Asset();
                asset.setAssetName(jsonArrayObject.getString("assetName"));
                asset.setAssetCode(jsonArrayObject.getString("assetCode"));
                asset.setAssetId(jsonArrayObject.getInt("assetId"));
                assetArray.add(asset);
            }

            JSONArray jsonDisabilitiesArray = jsonObject.getJSONArray("DisabilitiesBean");
            for (int i = 0; i < jsonDisabilitiesArray.length(); i++) {

                JSONObject jsonArrayObject = jsonDisabilitiesArray.getJSONObject(i);
                Disabilities disability = new Disabilities();
                disability.setDisabilitiesName(jsonArrayObject.getString("disabilityName"));
                disability.setDisabilitiesCode(jsonArrayObject.getString("disabilityCode"));
                disability.setDisabilitiesId(jsonArrayObject.getInt("disabilityId"));
                disabilitieArray.add(disability);
            }

            JSONArray jsonEducationArray = jsonObject.getJSONArray("EducationBean");
            for (int i = 0; i < jsonEducationArray.length(); i++) {

                JSONObject jsonArrayObject = jsonEducationArray.getJSONObject(i);
                Education education = new Education();
                education.setEducationName(jsonArrayObject.getString("educationName"));
                education.setEducationCode(jsonArrayObject.getString("educationCode"));
                education.setEducationId(jsonArrayObject.getInt("educationId"));
                educationArray.add(education);
            }

            JSONArray jsonEducationStatusArray = jsonObject.getJSONArray("EducationStatusBean");
            for (int i = 0; i < jsonEducationStatusArray.length(); i++) {

                JSONObject jsonArrayObject = jsonEducationStatusArray.getJSONObject(i);
                EducationStatus educationStatus = new EducationStatus();
                educationStatus.setGetEducationStatusName(jsonArrayObject.getString("educationStatusName"));
                educationStatus.setEducationStatusCode(jsonArrayObject.getString("educationStatusCode"));
                educationStatus.setEducationStatusId(jsonArrayObject.getInt("educationStatusId"));
                educationStatusArray.add(educationStatus);
            }

            JSONArray jsonMaritalStatusArray = jsonObject.getJSONArray("MartialStatusBean");
            for (int i = 0; i < jsonMaritalStatusArray.length(); i++) {

                JSONObject jsonArrayObject = jsonMaritalStatusArray.getJSONObject(i);
                MaritalStatus maritalStatus = new MaritalStatus();
                maritalStatus.setMaritalStatusName(jsonArrayObject.getString("maritalStatusName"));
                maritalStatus.setgMaritalStatusCode(jsonArrayObject.getString("maritalStatusCode"));
                maritalStatus.setMaritalStatusId(jsonArrayObject.getInt("maritalStatusId"));
                maritalStatuseArray.add(maritalStatus);
            }

            JSONArray jsonOccupationArray = jsonObject.getJSONArray("OccupationBean");
            for (int i = 0; i < jsonOccupationArray.length(); i++) {

                JSONObject jsonArrayObject = jsonOccupationArray.getJSONObject(i);
                Occupation occupation = new Occupation();
                occupation.setOccupationName(jsonArrayObject.getString("occupationName"));
                occupation.setOccupationCode(jsonArrayObject.getString("occupationCode"));
                occupation.setOccupationId(jsonArrayObject.getInt("occupationId"));
                occupationArray.add(occupation);
            }

            JSONArray jsonRelationshipArray = jsonObject.getJSONArray("RelationshipBean");
            for (int i = 0; i < jsonRelationshipArray.length(); i++) {

                JSONObject jsonArrayObject = jsonRelationshipArray.getJSONObject(i);
                Relationship relationship = new Relationship();
                relationship.setGetRelationshipName(jsonArrayObject.getString("relationshipName"));
                relationship.setRelationshipCode(jsonArrayObject.getString("relationshipCode"));
                relationship.setRelationshipId(jsonArrayObject.getInt("relationshipId"));
                relationshipArray.add(relationship);
            }

            JSONArray jsonReligionArray = jsonObject.getJSONArray("ReligionBean");
            for (int i = 0; i < jsonReligionArray.length(); i++) {

                JSONObject jsonArrayObject = jsonReligionArray.getJSONObject(i);
                Religion religion = new Religion();
                religion.setReligionName(jsonArrayObject.getString("religionName"));
                religion.setReligionCode(jsonArrayObject.getString("religionCode"));
                religion.setReligionId(jsonArrayObject.getInt("religionId"));
                religionArray.add(religion);
            }

            JSONArray jsonSchemeArray = jsonObject.getJSONArray("SchemeBean");
            for (int i = 0; i < jsonSchemeArray.length(); i++) {

                JSONObject jsonArrayObject = jsonSchemeArray.getJSONObject(i);
                Scheme scheme = new Scheme();
                scheme.setSchemeName(jsonArrayObject.getString("schemeName"));
                scheme.setSchemeId(jsonArrayObject.getInt("schemeCode"));
                schemeArray.add(scheme);
            }

            JSONArray jsonSocialCategoryArray = jsonObject.getJSONArray("SocialCategoryBean");
            for (int i = 0; i < jsonSocialCategoryArray.length(); i++) {

                JSONObject jsonArrayObject = jsonSocialCategoryArray.getJSONObject(i);
                SocialCategory socialCategory = new SocialCategory();
                socialCategory.setSocialCategoryName(jsonArrayObject.getString("socialCategoryName"));
                socialCategory.setSocialCategoryCode(jsonArrayObject.getString("socialCategoryCode"));
                socialCategory.setSocialCategoryId(jsonArrayObject.getInt("socialCategoryId"));
                socialCategoryArray.add(socialCategory);
            }

            dbHandler.insertState(finalState);
            dbHandler.insertDistrict(finalDistrict);
            dbHandler.insertBLOCK(finalBlock);
            //dbHandler.insertVillage(villageArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    class GetAllStateAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog progressDialog;

        public GetAllStateAsyncTask(Context mContext) {
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
                showState(s);
            }
            else if(flag==2) {
                showDistrict(s);
            }
            else if(flag==3) {
                showBlock(s);
            }
            else if(flag==4){
                showVillage(s);
            }
            else if(flag==5){
                showData(s);
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
        } else if (id == R.id.change_password) {
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