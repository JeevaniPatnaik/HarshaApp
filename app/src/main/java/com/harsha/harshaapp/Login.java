package com.harsha.harshaapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 * Created by Jeevani on 12/4/2016.
 */
public class Login extends AppCompatActivity {

    //String URL1 = "http://10.1.6.140:8085/HarshaTrust/api/checkpoint/login";
    String URL1 = "http://harsha-guptas.rhcloud.com/api/checkpoint/login";
    String URL2 = "";
    String result="";

    DBHandler dbHandler = new DBHandler(Login.this, null, null, 1);
    User user = new User();

    EditText username, password;//adminUser/adminHarsha/admin/admin123
    Button login;
    TextView forgotpassword;
    String pass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        login.setTypeface(custom_font1);
        forgotpassword.setTypeface(custom_font);
        username.setTypeface(custom_font);
        password.setTypeface(custom_font);


        if (dbHandler.isUserLoggedIn()) {

            user = dbHandler.getUserDetail();

            Bundle userBundle = new Bundle();

            userBundle.putInt("userId", user.getUserId());
            userBundle.putInt("roleId", user.getRoleId());
            userBundle.putInt("profileId", user.getProfileId());
            userBundle.putString("firstName", user.getFirstName());
            userBundle.putString("lastName", user.getLastName());
            userBundle.putString("email", user.getEmail());
            //userBundle.putString("password", pass);
            userBundle.putString("phone", user.getPhone());
            userBundle.putString("address", user.getAddress());
            userBundle.putString("username", user.getUserName());
            userBundle.putString("roleName", user.getRoleName());
            userBundle.putString("lastActivity", user.getLastActivity());
            userBundle.putString("photo", user.getPhoto());

            Log.d("roleName", user.getRoleName());


            if (user.getRoleName().equals("SUPERVISOR") && user.getUserId() > 0) {
                Intent intent = new Intent(Login.this, Home.class);
                intent.putExtra("user", userBundle);
                startActivity(intent);
                finish();
            } else if (user.getRoleName().equals("MANAGER") && user.getUserId() > 0) {
                Intent intent = new Intent(Login.this, ManagerHome.class);
                intent.putExtra("user", userBundle);
                startActivity(intent);
                finish();
            }
        }



        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(Login.this,Home.class);
                //startActivity(intent);
                if("".equals(username.getText().toString()) //vUsername.equalsIgnoreCase("") could lead to NPE
                        || "".equals(password.getText().toString()))
                {
                    Toast.makeText(Login.this, "All Fields Required.",
                            Toast.LENGTH_LONG).show();
                }
                else if(!(username.getText().toString()).matches("[a-zA-Z1-9.? ]*")){
                    Toast.makeText(Login.this, "Special character are not allowed",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    pass = password.getText().toString();
                    URL2 = URL1 + "?username=" + username.getText().toString()+"&password=" + password.getText().toString();
                    new LoginAsyncTask().execute(URL2);
                }

            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
    class LoginAsyncTask extends AsyncTask<String, String, String> {

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

                    user.setUserId(jsonObject.getInt("userId"));

                    if(user.getUserId()>0) {

                        user.setRoleId(jsonObject.getInt("roleId"));
                        user.setProfileId(jsonObject.getInt("profileId"));
                        user.setFirstName(jsonObject.getString("firstName"));
                        user.setLastName(jsonObject.getString("lastName"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setPassword(pass);
                        user.setPhone(jsonObject.getString("phone"));
                        user.setAddress(jsonObject.getString("address"));
                        user.setUserName(jsonObject.getString("username"));
                        user.setRoleName(jsonObject.getString("roleName"));
                        user.setLastActivity(jsonObject.getString("lastActivity"));
                        user.setPhoto(jsonObject.getString("photo"));
                        Log.d("roleName", user.getRoleName());

                        if (dbHandler.isUserLoggedIn()) {

                            user = dbHandler.getUserDetail();


                            Bundle userBundle = new Bundle();

                            userBundle.putInt("userId", user.getUserId());
                            userBundle.putInt("roleId", user.getRoleId());
                            userBundle.putInt("profileId", user.getProfileId());
                            userBundle.putString("firstName", user.getFirstName());
                            userBundle.putString("lastName", user.getLastName());
                            userBundle.putString("email", user.getEmail());
                            //userBundle.putString("password", pass);
                            userBundle.putString("phone", user.getPhone());
                            userBundle.putString("address", user.getAddress());
                            userBundle.putString("username", user.getUserName());
                            userBundle.putString("roleName", user.getRoleName());
                            userBundle.putString("lastActivity", user.getLastActivity());
                            userBundle.putString("photo", user.getPhoto());

                            Log.d("roleName", user.getRoleName());


                            if (user.getRoleName().equals("SUPERVISOR") && user.getUserId() > 0) {
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.putExtra("user", userBundle);
                                startActivity(intent);
                                finish();
                            } else if (user.getRoleName().equals("MANAGER") && user.getUserId() > 0) {
                                Intent intent = new Intent(Login.this, ManagerHome.class);
                                intent.putExtra("user", userBundle);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Log.d("roleName1", user.getRoleName());

                            Bundle userBundle = new Bundle();

                            userBundle.putInt("userId", user.getUserId());
                            userBundle.putInt("roleId", user.getRoleId());
                            userBundle.putInt("profileId", user.getProfileId());
                            userBundle.putString("firstName", user.getFirstName());
                            userBundle.putString("lastName", user.getLastName());
                            userBundle.putString("email", user.getEmail());
                            //userBundle.putString("password", pass);
                            userBundle.putString("phone", user.getPhone());
                            userBundle.putString("address", user.getAddress());
                            userBundle.putString("username", user.getUserName());
                            userBundle.putString("roleName", user.getRoleName());
                            userBundle.putString("lastActivity", user.getLastActivity());
                            userBundle.putString("photo", user.getPhoto());

                            Log.d("roleName", user.getRoleName());

                            if (user.getRoleName().equals("SUPERVISOR") && user.getUserId() > 0) {
                                dbHandler.insertUser(user);
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.putExtra("user", userBundle);
                                startActivity(intent);
                                finish();
                            } else if (user.getRoleName().equals("MANAGER") && user.getUserId() > 0) {
                                dbHandler.insertUser(user);
                                Intent intent = new Intent(Login.this, ManagerHome.class);
                                startActivity(intent);
                                finish();
                            } else {
                                username.setText("");
                                password.setText("");
                                Toast.makeText(Login.this, "Invalid UserName or Password", Toast.LENGTH_LONG).show();
                            }
/*
                        Intent loginIntent = new Intent(getApplicationContext(), Home.class);
                        // Add Bundle to intent
                        loginIntent.putExtras(userBundle);
                        // Start the next Activity
                        startActivity(loginIntent);
                        // Finish the current Activity
                        finish();
*/
                        }
                    }
                    else {
                        username.setText("");
                        password.setText("");
                        Toast.makeText(Login.this, "Invalid UserName or Password", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
