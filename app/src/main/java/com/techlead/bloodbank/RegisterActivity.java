package com.techlead.bloodbank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.techlead.bloodbank.Utils.Endpoints;
import com.techlead.bloodbank.Utils.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameText, cityText, mobileText, bloodGroupText, passwordText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameText = findViewById(R.id.name);
        cityText = findViewById(R.id.city);
        mobileText = findViewById(R.id.mobile_number);
        bloodGroupText = findViewById(R.id.blood_group);
        passwordText = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, city, mobile, blood_group, password;
                name = nameText.getText().toString().trim();
                city = cityText.getText().toString().trim();
                mobile = mobileText.getText().toString().trim();
                blood_group = bloodGroupText.getText().toString().trim();
                password = passwordText.getText().toString();

                if (isValid(name,city,blood_group,password,mobile)){
                    register(name,city,blood_group,password,mobile);

                }

            }
        });
    }

    private void register(final String name, final String city, final String blood_group, final String password, final String mobile){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Success")){
                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    RegisterActivity.this.finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"Something wrong",Toast.LENGTH_SHORT).show();
                Log.d("Volley",error.getMessage());

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("city",city);
                params.put("blood_group", blood_group);
                params.put("password", password);
                params.put("number", mobile);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private boolean isValid(String name, String city,String blood_group,String password, String mobile){
        List<String> valid_blood_group = new ArrayList<>();
        valid_blood_group.add("A+");
        valid_blood_group.add("B+");
        valid_blood_group.add("O+");
        valid_blood_group.add("AB+");
        valid_blood_group.add("A-");
        valid_blood_group.add("B-");
        valid_blood_group.add("O-");
        valid_blood_group.add("AB-");

        if(name.isEmpty()){
            showMessage("Name is required");
            return false;
        }
        else if(city.isEmpty()){
            showMessage("City name require is Invalid");
            return false;
        }
        else if(!valid_blood_group.contains(blood_group)){
            showMessage("Blood group is invalid choose from" + valid_blood_group);
            return false;
        }
        else if (mobile.length() != 10){
            showMessage("Invalid mobile number, mobile number should be 10 digit");
            return false;
        }
        return true;

    }

    private void showMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}