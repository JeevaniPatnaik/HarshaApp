package com.harsha.harshaapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.harsha.harshaapp.database.DBHandler;

/**
 * Created by Jeevani on 12/4/2016.
 */
public class ForgotPassword extends AppCompatActivity {

    String URL1 = "http://10.1.1.117:8085/harsha/checkpoint/forgotpassword";
    String URL2 = "";
    String result="";
    String KEY, CODE;

    DBHandler dbHandler = new DBHandler(ForgotPassword.this, null, null, 1);

    EditText email;
    Button send;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        send = (Button) findViewById(R.id.send);
        email = (EditText) findViewById(R.id.email);
        login = (TextView) findViewById(R.id.login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        send.setTypeface(custom_font1);
        login.setTypeface(custom_font);
        email.setTypeface(custom_font);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*URL2 = URL1 + "?email=" + email.getText().toString();
                new ForgotPasswordAsyncTask().execute(URL2);*/
                Intent intent = new Intent(ForgotPassword.this, ManagerHome.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });

    }

    /*class ForgotPasswordAsyncTask extends AsyncTask<String, String, String> {

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
            Toast.makeText(ForgotPassword.this,"The result is "+s,Toast.LENGTH_LONG).show();
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    KEY = jsonObject.getString("KEY");
                    CODE = jsonObject.getString("CODE");

                    if (CODE.equals("true")) {

                        Toast.makeText(ForgotPassword.this, "YOUR PASSWORD HAS BEEN RECOVERED, PLEASE CHECK YOUR MAIL", Toast.LENGTH_SHORT).show();

                        // Create intent for moving to new Activity
                        Intent loginIntent = new Intent(getApplicationContext(), Login.class);

                        // Start the next Activity
                        startActivity(loginIntent);

                        // Finish the current Activity
                        //finish();

                    }
                    else {

                        Toast.makeText(ForgotPassword.this, "INVALID EMAIL ID", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

}
