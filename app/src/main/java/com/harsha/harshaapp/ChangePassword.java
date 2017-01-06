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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.database.DBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jeevani on 12/14/2016.
 */
public class ChangePassword extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String URL1 = "http://harsha-guptas.rhcloud.com/api/checkpoint/changepassword";
    String URL2 = "";
    String result="";

    DBHandler dbHandler = new DBHandler(ChangePassword.this, null, null, 1);
    Bundle bundle;
    User user = new User();

    TextView nav_username,nav_email;

    EditText oldPassword,newPassword,retypePassword;
    Button update;

    String oldPass, newPass, retypePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oldPassword = (EditText) findViewById(R.id.oldPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        retypePassword = (EditText) findViewById(R.id.retypePassword);
        update = (Button) findViewById(R.id.update);

        Intent receive = getIntent();
        bundle = receive.getExtras();
        user = dbHandler.getUserDetail();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oldPass = oldPassword.getText().toString();

                /*if("".equals(oldPassword.getText().toString()) //vUsername.equalsIgnoreCase("") could lead to NPE
                        || "".equals(newPassword.getText().toString()) || "".equals(retypePassword.getText().toString()))
                {
                    Toast.makeText(ChangePassword.this, "All Fields Required.",
                            Toast.LENGTH_LONG).show();
                }
                else if(!(oldPassword.getText().toString()).matches("[a-zA-Z1-9.? ]*")
                        || !(newPassword.getText().toString()).matches("[a-zA-Z1-9.? ]*")
                        || !(retypePassword.getText().toString()).matches("[a-zA-Z1-9.? ]*")){
                    Toast.makeText(ChangePassword.this, "Special character are not allowed",
                            Toast.LENGTH_LONG).show();
                }
                else if(newPassword.getText().toString() == retypePassword.getText().toString()) {
                    Toast.makeText(ChangePassword.this, "Passwords does not match",
                            Toast.LENGTH_LONG).show();
                }
                else */
                if(oldPass.equals(user.getPassword())) {

                    newPass = newPassword.getText().toString();
                    retypePass = retypePassword.getText().toString();

                    URL2 = URL1 + "?userid="+user.getUserId()+"&password="+newPass;
                    new ChangePasswordAsyncTask().execute(URL2);

                }
                else {
                    Toast.makeText(ChangePassword.this, "OLD PASSWORD DID NOT MATCHED", Toast.LENGTH_SHORT).show();

                    oldPassword.setText("");
                    newPassword.setText("");
                    retypePassword.setText("");
                }
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

    /*public String testingRestAPI(String urls) {
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
*/

    class ChangePasswordAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                //Log.d("URL2",URL2);
                URL url=new URL(URL2);
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
            // Toast.makeText(Login.this,"The result is "+s,Toast.LENGTH_LONG).show();
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    boolean code = jsonObject.getBoolean("status");

                    if (code) {

                        user.setPassword(newPass);

                        Bundle sendBundle = new Bundle();

                        sendBundle.putInt("userId", user.getUserId());
                        sendBundle.putInt("roleId", user.getRoleId());
                        sendBundle.putInt("profileId", user.getProfileId());
                        sendBundle.putString("firstName", user.getFirstName());
                        sendBundle.putString("lastName", user.getLastName());
                        sendBundle.putString("email", user.getEmail());
                        sendBundle.putString("phone", user.getPhone());
                        sendBundle.putString("address", user.getAddress());
                        sendBundle.putString("username", user.getUserName());
                        sendBundle.putString("roleName", user.getRoleName());
                        sendBundle.putString("lastActivity", user.getLastActivity());
                        sendBundle.putString("photo", user.getPhoto());

                        dbHandler.updateUser(user);

                        Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();

                        // Create intent for moving to new Activity
                        Intent loginIntent = new Intent(getApplicationContext(), Home.class);
                        // Add Bundle to intent
                        loginIntent.putExtras(sendBundle);
                        // Start the next Activity
                        startActivity(loginIntent);
                        // Finish the current Activity
                        //finish();

                    }
                    else {
                        Toast.makeText(ChangePassword.this, "Password Didn't Changed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
