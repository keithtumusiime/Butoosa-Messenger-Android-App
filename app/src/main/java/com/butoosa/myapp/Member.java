package com.butoosa.myapp;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        // hide the default actionbar
        getSupportActionBar().hide();

        // Recieve data

        String name  = getIntent().getExtras().getString("name");
        String course = getIntent().getExtras().getString("course");
        String sid = getIntent().getExtras().getString("sid") ;
        String email = getIntent().getExtras().getString("email");
        String contact = getIntent().getExtras().getString("contact") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_name_name);
        TextView tv_course = findViewById(R.id.aa_course);
        TextView tv_sid = findViewById(R.id.aa_sid) ;
        TextView tv_email = findViewById(R.id.aa_email);
        TextView tv_contact  = findViewById(R.id.aa_tel) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_course.setText(course);
        tv_email.setText(email);
        tv_sid.setText(sid);
        tv_contact.setText(contact);

        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);
    }
}
