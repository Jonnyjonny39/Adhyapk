package com.jonnyjonny.s_assistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.DateFormat;
import java.util.Date;

import com.jonnyjonny.s_assistant.Model.Data;

public class AddBatchNoteActivity extends AppCompatActivity {
    private EditText mTitle,mDescription,noOfStudents,batchLocation;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch_note);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Note").child(uid);
        mDialog=new ProgressDialog(this);

        addNote();
    }
    private void addNote(){
        noOfStudents=findViewById(R.id.noOfStudents);
        batchLocation=findViewById(R.id.batchLocation);

        mTitle=findViewById(R.id.title);
        mDescription=findViewById(R.id.description);
        String noStudents=noOfStudents.getText().toString().trim();
        String location=batchLocation.getText().toString().trim();

        String title=mTitle.getText().toString().trim();
        String description=mDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title)){
            mTitle.setError("Required Field..");
            return;
        }
        String id=mDatabase.push().getKey();
        String date= DateFormat.getDateInstance().format(new Date());

        Data data=new Data(title,description,date,id,noStudents,location);
        mDatabase.child(id).setValue(data);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.done23:
                mDialog.setMessage("Adding Batch Note");
                mDialog.show();
                addNote();

                startActivity(new Intent(AddBatchNoteActivity.this,BatchNoteActivity.class));
                mDialog.dismiss();

                FancyToast.makeText(AddBatchNoteActivity.this,"Batch Note Added!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
