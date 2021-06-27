package com.techlead.bloodbank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.techlead.bloodbank.Utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

import static com.techlead.bloodbank.Utils.Endpoints.login_url;

public class LoginActivity extends AppCompatActivity {
    private EditText numberText,passwordText;
    private TextView signUpText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        numberText = findViewById(R.id.login_mobile);
        passwordText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signUpText = findViewById(R.id.sign_up_text_view);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberText.setError(null);
                passwordText.setError(null);
                String number, password;
                number = numberText.getText().toString();
                password = passwordText.getText().toString();
                if(isValid(number,password)){
                    login(number,password);

                }
            }
        });
    }
    // for PHP scripts to get data or push
    private void login(final String number, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Success")){
                    Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    LoginActivity.this.finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,"Something Went wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("number",number);
                params.put("password",password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private boolean isValid(String mobile, String password) {
        if(mobile.isEmpty()){
            showMessage("Mobile Number is Empty");
            numberText.setError("Mobile Number is Empty");
            return false;
        }
        else if(password.isEmpty()){
            showMessage("Password is Empty");
            passwordText.setError("Password is Empty");
            return false;
        }
        return true;
    }
    private void showMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}