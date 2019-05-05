package com.jonnyjonny.s_assistant.main.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import com.jonnyjonny.s_assistant.R;
import com.jonnyjonny.s_assistant.main.AppBase;

public class StudentRegistration extends AppCompatActivity {
    private ImageView profileImage;
    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;


    Activity activity = this;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registartion);





        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions);
        spinner.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.buttonSAVE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = (EditText) findViewById(R.id.edit_name);
        EditText roll = (EditText) findViewById(R.id.roll);
        EditText register = (EditText) findViewById(R.id.register);


        EditText contact = (EditText) findViewById(R.id.contact);
        String classSelected = spinner.getSelectedItem().toString();

        if (name.getText().length() < 2 || roll.getText().length() == 0 || register.getText().length() < 2 ||
                contact.getText().length() < 2 || classSelected.length() < 2) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        String qu = "INSERT INTO STUDENT VALUES('" + name.getText().toString() + "'," +
                "'" + classSelected + "'," +
                "'" + register.getText().toString().toUpperCase() + "'," +
                "'" + contact.getText().toString() + "'," +
                "" + Integer.parseInt(roll.getText().toString()) + ");";
        Log.d("Student Reg", qu);
        AppBase.handler.execAction(qu);
        qu = "SELECT * FROM STUDENT WHERE regno = '" + register.getText().toString() + "';";
        Log.d("Student Reg", qu);
        if (AppBase.handler.execQuery(qu) != null) {
            Toast.makeText(getBaseContext(), "Student Added", Toast.LENGTH_LONG).show();
            this.finish();
        }








    }
    public void getPhoto(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK&&data!=null){
            Uri selectedImage=data.getData();
            try{
            Bitmap bitmap=MediaStore.Images.Media.getBitmap(StudentRegistration.this.getContentResolver(),selectedImage);
              //  profileImage=findViewById(R.id.profileImageView);

                profileImage.setImageBitmap(bitmap);
        }catch (IOException e){
                e.printStackTrace();
            }


        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getPhoto();


            }

            }
    }
}


