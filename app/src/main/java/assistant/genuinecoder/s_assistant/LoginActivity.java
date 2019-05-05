package com.jonnyjonny.s_assistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import com.jonnyjonny.s_assistant.main.AppBase;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private Button btnSignup;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        btnSignup=findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog=new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Signing Up!");
                mDialog.show();

                String email2=edtEmail.getText().toString();
                String password2=edtPassword.getText().toString();
                if (email2.equals("")||password2.equals("")){

                    Toast.makeText(LoginActivity.this, "Both Fields Are Required!", Toast.LENGTH_LONG).show();



                }else if ( email2!=null&&password2!=null){
                signUp();
                mDialog.dismiss();


            }}
        });
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog=new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Logging In...");
                mDialog.show();

                String email=edtEmail.getText().toString();
                String password=edtPassword.getText().toString();
                if (email.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this, "Both Fields Are Required!", Toast.LENGTH_LONG).show();

                }else {

                signIn();

            }}
        });
        mAuth=FirebaseAuth.getInstance();


    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser!=null){
            //Transtsition to next Activity
            startActivity(new Intent(LoginActivity.this,AppBase.class));
        }
    }
    private void signUp(){
        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FancyToast.makeText(LoginActivity.this,"Signing Up Was Successfull!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                            Intent intent=new Intent(LoginActivity.this,PreDashActivity.class);
                            startActivity(intent);
                            mDialog.dismiss();


                        }else {
                            FancyToast.makeText(LoginActivity.this,"Signing Up Failed!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            mDialog.dismiss();

                        }

                    }
                }
        );
    }
    private void signIn(){
        mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FancyToast.makeText(LoginActivity.this,"Signing In Was Successfull!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    if (edtEmail!=null&&edtPassword!=null){
                        Intent intent=new Intent(LoginActivity.this,PreDashActivity.class);
                        startActivity(intent);
                    }



                    overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
                    mDialog.dismiss();


                }else {
                    FancyToast.makeText(LoginActivity.this,"Signing In Failed!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    mDialog.dismiss();


                }
            }
        });
    }
    }

