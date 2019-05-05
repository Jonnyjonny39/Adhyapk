package com.jonnyjonny.s_assistant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfilePicActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ImageView postImageView;
  //  private Button btnCreatePost;
    private Bitmap bitmap;
    private EditText edtName,edtAddress,edtPhone,edtEmail;

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        edtName=findViewById(R.id.edtName);
        edtEmail=findViewById(R.id.edtEmail1);
        edtAddress=findViewById(R.id.edtAddress);
        edtPhone=findViewById(R.id.edtPhone);
        saveBtn=findViewById(R.id.btnSave);
        mAuth=FirebaseAuth.getInstance();
        postImageView=findViewById(R.id.postImageView);
       // btnCreatePost=findViewById(R.id.btnCreatePost);
        postImageView.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                selectImage();

            }
        });

     //   btnCreatePost.setOnClickListener(new View.OnClickListener() {
     //       @Override
      //      public void onClick(View v) {

         //   }
      //  });
    }
    private void selectImage(){
        if (Build.VERSION.SDK_INT<23){
            Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1000);
        }else if (Build.VERSION.SDK_INT>23)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1000);
            }else {
            Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1000);
            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1000&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000&&resultCode==RESULT_OK&&data!=null){
            Uri chosenImageData=data.getData();

            try{
                bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),chosenImageData);
                postImageView.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
