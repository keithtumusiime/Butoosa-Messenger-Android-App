package com.butoosa.myapp;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.butoosa.myapp.model.Anime;
import com.butoosa.myapp.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView Name, S_ID;
    ImageView image;
    private final String url = "https://butoosa.online/androidApp/profile.php?sid=";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    RequestOptions option;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //making header dynamic

        //request option from glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Working on it. or contact developer", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                dialog = new Dialog(Dashboard.this);
                dialog.setContentView(R.layout.memb_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        //leadership styles:

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //getting items in the header
        View header = navigationView.getHeaderView(0);
        Name = header.findViewById(R.id.name);
        S_ID = header.findViewById(R.id.student_id);
        image = header.findViewById(R.id.imageView);



        String s_id = getIntent().getExtras().getString("sid");
        S_ID.setText("StudentID: "+s_id);


        //bundle to the next fragment
        Bundle bundle = new Bundle();
        bundle.putString("studentID",s_id);



        //setting default fragment
        Fragment fragment = new Home();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");

        fragmentTransaction.commit();
        
        //method to get user details
        jsonReq();

        //Profile pro = new Profile();
        //S_ID.setText(pro.getStudentID());
        //Name.setText(pro.getName());



    }

    private void jsonReq() {

        final Profile anime = new Profile();

        request = new JsonArrayRequest(url +getIntent().getExtras().getString("sid"), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        object = response.getJSONObject(i);
                        anime.setName(object.getString("name"));
                        anime.setImage_url(object.getString("photo"));
                        anime.setStudentID(object.getString("studentID"));

                        Toast.makeText(getApplicationContext(),"Image: " +anime.getImage_url(),Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        //load image from the internet and set it.
        //Glide.with(this).load(img).apply(option).into(image);
        //Name.setText(anime.getName());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(),Settings.class);
            startActivity(i);
            //return true;
        }else if (id == R.id.action_logout) {
            finish();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new Constitution()).commit();
            Home fragment = new Home();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_constitution) {
            Constitution fragment = new Constitution();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Constitution");
            fragmentTransaction.commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Constitution()).commit();
        } else if (id == R.id.nav_loans) {
            Loans fragment = new Loans();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Loans");
            fragmentTransaction.commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Loans()).commit();
        } else if (id == R.id.nav_events) {
            Events fragment = new Events();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Events");
            fragmentTransaction.commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Events()).commit();
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String body = "Check out BUTOOSA App here: https://keith.butoosa.online/contact";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "BUTOOSA APP");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(sharingIntent, "Share BUTOOSA with"));
            return true;

        } else if (id == R.id.nav_help) {
            Help fragment = new Help();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Help");
            fragmentTransaction.commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Help()).commit();
        } else if (id == R.id.nav_finance) {
            Finance fragment = new Finance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Finance");
            fragmentTransaction.commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Finance()).commit();
        } else if (id == R.id.nav_members) {
            Members fragment = new Members();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Members");
            fragmentTransaction.commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Members()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
