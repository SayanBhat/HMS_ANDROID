package project.sayan.hms.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import project.sayan.hms.Model.UserModel;
import project.sayan.hms.R;
import project.sayan.hms.mInterface.CallBackInterFace;
import project.sayan.hms.mServices.LoginService;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity" ;
    private EditText etName,etEmail,etPassword;
    private Button btSignIn;
    private TextView tvSignUp,tvSignIn;
    private Context mContext;
    SharedPreferences sPref;
    private ProgressBar progressBar;

    public  boolean FLAG_SIGNIN=true;

 //   private final String REQ_REGISTRATION = "user_save";
   // private final String REQ_AUTHENTICATE = "user_auth";
    private final String LOGINCRED="LoginCredentials";

   // private final String postUserUrl="http://10.0.2.2:2002/api/user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sPref=getSharedPreferences(LOGINCRED,MODE_PRIVATE);


        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btSignIn=findViewById(R.id.btnSignIn);
        tvSignUp=findViewById(R.id.tvSignUp);
        tvSignIn=findViewById(R.id.tvSignIn);
        progressBar = findViewById(R.id.loginProgress);

        mContext=getApplicationContext();



        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btSignIn.setText("Sign Up");
                btSignIn.setBackgroundColor(Color.parseColor("#e31b25"));
                tvSignUp.setVisibility(View.GONE);
                etName.setVisibility(View.VISIBLE);
                tvSignIn.setVisibility(View.VISIBLE);

                FLAG_SIGNIN=false;
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btSignIn.setText("Sign In");
                btSignIn.setBackgroundColor(Color.parseColor("#263238"));
                etName.setVisibility(View.GONE);
                tvSignUp.setVisibility(View.VISIBLE);
                tvSignIn.setVisibility(View.GONE);
                FLAG_SIGNIN=true;
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FLAG_SIGNIN){

                    if(!TextUtils.isEmpty(etEmail.getText()) && !TextUtils.isEmpty(etPassword.getText())){

                        String email=etEmail.getText().toString().trim();
                        String password=etPassword.getText().toString().trim();
                        if(isEmailValid(email)){
                            //TODO Request UserLogin api
                            LoginService loginService= new LoginService(LoginActivity.this,email,password);
                            loginService.authenticateUser(new CallBackInterFace() {
                                @Override
                                public void onSuccss(Object object) {
                                    Log.w("Login Acti",object.toString());
                                    UserModel user=(UserModel)object;
                                    setSharePreference(user);
                                    Toast.makeText(LoginActivity.this,user.getMessage(),Toast.LENGTH_SHORT).show();

                                    finish();
                                }
                            });
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Invalid Email ",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
                    }
                }
                if(!FLAG_SIGNIN){

                    if(!TextUtils.isEmpty(etEmail.getText()) && !TextUtils.isEmpty(etPassword.getText())
                            && !TextUtils.isEmpty(etName.getText())){

                        String name =etName.getText().toString().trim();
                        String email=etEmail.getText().toString().trim();
                        String password=etPassword.getText().toString().trim();
                        if(isEmailValid(email)){
                           //TODO Request  user register api


                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Invalid Email ",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean isEmailValid(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



    private void setSharePreference(UserModel user) {
        SharedPreferences.Editor ed=sPref.edit();
        ed.putBoolean("IS_LOGIN",true);
        ed.putString(user.EMAIL,user.getEmail());
        ed.putString(user.NAME,user.getName());
        ed.apply();
        finish();
    }
}
