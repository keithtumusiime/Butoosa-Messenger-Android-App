package com.butoosa.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.butoosa.myapp.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button mLogin;
    EditText sid,password;
    String url = "https://butoosa.online/androidApp/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        mLogin = findViewById(R.id.login);
        sid = findViewById(R.id.sid);
        password = findViewById(R.id.password);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(),Registeration.class);
                startActivity(register);
            }
        });

       /* mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, Dashboard.class);
                //startActivity(intent);


            }
        });*/
    }

    public void Login(View view) {
        final String SID = sid.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        mLogin.setText("Loading...");
        if (SID.isEmpty() && Password.isEmpty()) {
            Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
            mLogin.setText("Login");
        }
        else if (SID.isEmpty()) {
            sid.setError("Student ID is required");
            mLogin.setText("Login");
        } else if (Password.isEmpty()) {
            password.setError("Password is required");
            mLogin.setText("Login");
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equalsIgnoreCase("success")){
                        Toast.makeText(MainActivity.this, "Logged in successfully "+SID, Toast.LENGTH_LONG).show();
                        finish();

                        //starting new activity
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        intent.putExtra("sid", SID);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        mLogin.setText("Login");

                    }
                    /*try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("login");

                        if (success.equals("1")) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String Sid = object.getString("sid").trim();
                                String email = object.getString("email").trim();
                                Toast.makeText(MainActivity.this, "sid " + Sid + " Email " + email, Toast.LENGTH_LONG).show();


                            }

                        }else {
                            Toast.makeText(MainActivity.this, "Incorrect studentID or Password ", Toast.LENGTH_LONG).show();
                            mLogin.setText("Login");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mLogin.setText("Login");
                    }*/

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(MainActivity.this, "Volley Error =>" + error.toString(), Toast.LENGTH_LONG).show();
                    mLogin.setText("Login");
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", SID);
                    params.put("password", Password);

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
        /*@Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }*/
}
