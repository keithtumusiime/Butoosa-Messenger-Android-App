package com.butoosa.myapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class Registeration extends AppCompatActivity {
    EditText Name, Email,Reg, Tel,Institution,Faculty,Dept,Course,Year,Hostel,Nationality,District,County,Subcounty,Parish,Village;
    TextInputLayout layout_name,layout_email,layout_reg,layout_tel,layout_institution,
            layout_faculty,layout_dept,layout_year,layout_hostel,layout_nationality,layout_district,
            layout_county,layout_subcounty,layout_parish,layout_village;
    Button regist;
    String url = "https://butoosa.online/androidApp/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Reg = findViewById(R.id.reg);
        Tel = findViewById(R.id.tel);
        Institution = findViewById(R.id.institution);

        Faculty = findViewById(R.id.faculty);
        Dept = findViewById(R.id.dept);
        Course = findViewById(R.id.course);
        Year = findViewById(R.id.year);
        Hostel = findViewById(R.id.hostel);
        Nationality = findViewById(R.id.nationaliy);
        District = findViewById(R.id.district);
        County = findViewById(R.id.county);
        Subcounty = findViewById(R.id.subcounty);
        Parish = findViewById(R.id.parish);
        Village = findViewById(R.id.village);
        regist = findViewById(R.id.register);
    }

    public void Register(View view){
        regist.setText("Processing....");
        final String name = Name.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String reg = Reg.getText().toString().trim();
        final String contact = Tel.getText().toString().trim();
        final String institution = Name.getText().toString().trim();
        final String faculty = Faculty.getText().toString().trim();
        final String dept = Dept.getText().toString().trim();
        final String course = Course.getText().toString().trim();
        final String year = Year.getText().toString().trim();
        final String hostel = Hostel.getText().toString().trim();
        final String nationality = Nationality.getText().toString().trim();
        final String district = District.getText().toString().trim();
        final String county = County.getText().toString().trim();
        final String subcounty = Subcounty.getText().toString().trim();
        final String parish = Parish.getText().toString().trim();
        final String village = Village.getText().toString().trim();

        //checking if there is empty field
        if (name.isEmpty() || email.isEmpty()||reg.isEmpty()|| contact.isEmpty()
                || institution.isEmpty() ||faculty.isEmpty() || dept.isEmpty() || course.isEmpty()
                || year.isEmpty() || hostel.isEmpty() ||nationality.isEmpty() || district.isEmpty() || county.isEmpty()
                || subcounty.isEmpty() || parish.isEmpty() || village.isEmpty()){
            Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_LONG).show();
            regist.setText("Register");

        }else{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    regist.setText("Register");
                    Name.setText("");
                    Email.setText("");
                    Reg.setText("");
                    Tel.setText("");
                    Institution.setText("");
                    Faculty.setText("");
                    Dept.setText("");
                    Course.setText("");
                    Year.setText("");
                    Hostel.setText("");
                    Nationality.setText("");
                    District.setText("");
                    County.setText("");
                    Subcounty.setText("");
                    Parish.setText("");
                    Village.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Volley Error => "+error.toString(),Toast.LENGTH_LONG).show();
                    regist.setText("Register");
                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("reg", reg);
                    params.put("tel", contact);
                    params.put("institution", institution);
                    params.put("faculty", faculty);
                    params.put("dept", dept);
                    params.put("course", course);
                    params.put("year", year);
                    params.put("hostel", hostel);
                    params.put("nationality", nationality);
                    params.put("district", district);
                    params.put("county", county);
                    params.put("subcounty", subcounty);
                    params.put("parish", parish);
                    params.put("village", village);

                    return params;
                }
            };
            requestQueue.add(request);
        }
    }
    public void BackToLogin(View view){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
