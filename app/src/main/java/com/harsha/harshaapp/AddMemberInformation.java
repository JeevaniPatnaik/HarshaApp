package com.harsha.harshaapp;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.harsha.harshaapp.bean.MemberInfo;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

/**
 * Created by Jeevani on 12/7/2016.
 */

public class AddMemberInformation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String URL1 = "http://10.1.1.117:8085/harsha/member/add";
    String URL2 = "";
    String result="";

    DBHandler dbHandler = new DBHandler(AddMemberInformation.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    TextView familyInformation, personalInformation;
    EditText name,dob,aadhaarCard,voterId,familyHeadName,personalSalary;
    RadioGroup gender;
    RadioButton male,female,other;
    Spinner quota, occupation, disabilities, relationship, education, educationStatus, migrationReason, religion, centralScheme, maritalStatus;
    Button add;
    boolean flag = false;
    MemberInfo memberInfo = new MemberInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        familyInformation = (TextView) findViewById(R.id.familyInformation);
        personalInformation = (TextView) findViewById(R.id.personalInformation);
        name = (EditText) findViewById(R.id.name);
        dob = (EditText) findViewById(R.id.date);
        aadhaarCard = (EditText) findViewById(R.id.aadhaarCard);
        voterId = (EditText) findViewById(R.id.voterId);
        familyHeadName = (EditText) findViewById(R.id.familyHeadName);
        personalSalary = (EditText) findViewById(R.id.personalSalary);
        gender = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        other = (RadioButton) findViewById(R.id.other);
        quota = (Spinner) findViewById(R.id.quota);
        occupation = (Spinner) findViewById(R.id.occupation);
        disabilities = (Spinner) findViewById(R.id.disabilities);
        relationship = (Spinner) findViewById(R.id.relationship);
        education = (Spinner) findViewById(R.id.education);
        educationStatus = (Spinner) findViewById(R.id.educationStatus);
        migrationReason = (Spinner) findViewById(R.id.migrationReason);
        maritalStatus = (Spinner) findViewById(R.id.maritalStatus);
        religion = (Spinner) findViewById(R.id.religion);
        centralScheme = (Spinner) findViewById(R.id.centralScheme);
        add = (Button) findViewById(R.id.add);

        ArrayAdapter<CharSequence> quotaListAdapter = ArrayAdapter
                .createFromResource(this, R.array.quota_list,
                        android.R.layout.simple_spinner_item);
        quotaListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quota.setAdapter(quotaListAdapter);

        /*quota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                memberInfo.setQuota((String) parent.getItemAtPosition(position));
                flag = true;
            }
        });*/

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* URL2 = URL1 + "?memberName=" + name.getText().toString()+"&dob=" + dob.getText().toString()
                        +"&gender=" + gender.getId()+"&aadhaarCardId=" + aadhaarCard.getText().toString()
                        +"&voterId=" + voterId.getText().toString()+"&familyHead=" + familyHeadName.getText().toString()
                        +"&personalSalary=" + personalSalary.getText().toString();
                new AddMemberAsyncTask().execute(URL2);*/

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
        //navigationView.getMenu().getItem(0).setChecked(true);
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

   /* class AddMemberAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url=new URL(params[0]);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
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
            Toast.makeText(AddMemberInformation.this,"The result is "+s,Toast.LENGTH_LONG).show();
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    memberInfo.setMemberName(name.getText().toString());
                    memberInfo.setDob(dob.getText().toString());
                    memberInfo.setGender(gender.toString());
                    memberInfo.setQuota(quota.toString());
                    memberInfo.setAadhaarCardId(aadhaarCard.getId());
                    memberInfo.setVoterId(voterId.getId());
                    memberInfo.setFamilyHead(familyHeadName.getText().toString());
                    memberInfo.setPersonalSalary(personalSalary.getText().toString());

                    dbHandler.insertMemberInformation(memberInfo);

                    Intent loginIntent = new Intent(getApplicationContext(), Home.class);
                    // Add Bundle to intent
                    loginIntent.putExtras(bundle);
                    // Start the next Activity
                    startActivity(loginIntent);
                    // Finish the current Activity
                    finish();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

}
