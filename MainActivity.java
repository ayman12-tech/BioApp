package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.RestApi.APIService;
import com.example.test.RestApi.ApiClient;
import com.example.test.RestApi.tokenResponse;
import com.google.gson.Gson;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    Button btnSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignin = (Button) findViewById(R.id.btnSignIn);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnSignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CallLoginService();
            }
        });
    }
    private void CallLoginService()
    {
        try {
            final String username = etUsername.getText().toString();
            final String password = etPassword.getText().toString();

            APIService service = ApiClient.getClient().create(APIService.class);
            Call<ResponseBody> srvLogin = service.getToken(username,password);
            srvLogin.enqueue(new Callback<ResponseBody>()
            {
                @Override
                public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response)
                {
                    if(response.isSuccessful())
                    {
                        try {
                            String ResponseJson = response.body().string();
                            Gson objGson = new Gson();
                            tokenResponse objResp = objGson.fromJson(ResponseJson,tokenResponse.class);
                            Toast.makeText(MainActivity.this,objResp.getAccess_token(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this,"Token got successfully, pursue your process.", Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Credentials is invalid, plz try again" ,Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"System error occurred :" + t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"System error occurred, Please check your internet connection is enabled",Toast.LENGTH_SHORT).show();
        }
    }
}