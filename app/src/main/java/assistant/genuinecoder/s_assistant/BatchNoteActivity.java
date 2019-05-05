package com.jonnyjonny.s_assistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.jonnyjonny.s_assistant.Model.Data;

public class BatchNoteActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_note);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Note").child(uid);
        mDatabase.keepSynced(true);
        mRecyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        floatingActionButton=findViewById(R.id.floatBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BatchNoteActivity.this,AddBatchNoteActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,MyViewHolder>recyclerAdapter=new FirebaseRecyclerAdapter<Data, MyViewHolder>(
                Data.class,
                R.layout.item_data,
                BatchNoteActivity.MyViewHolder.class,
                mDatabase
        ) {
       //     @NonNull
         //   @Override
         //   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           //     return null;
          //  }

            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, Data model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setNoOfStudents(model.getNoOfStudents());
                viewHolder.setLocation(model.getLocation());



            }











        };
        mRecyclerView.setAdapter(recyclerAdapter);

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View myView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myView=itemView;
        }
        public void setTitle(String title){
            TextView mTitle=myView.findViewById(R.id.title_xml);
            mTitle.setText(title);

        }
        public void setDescription(String description){

            TextView mDescription=myView.findViewById(R.id.description_xml);
            mDescription.setText(description);


        }
        public void setNoOfStudents(String noOfStudents){
            TextView nStudents=myView.findViewById(R.id.noStudentsxml);
            nStudents.setText(noOfStudents);
        }
        public void setLocation(String location){
            TextView batchLocation=myView.findViewById(R.id.locationXml);
            batchLocation.setText(location);
        }
        public void setDate(String date){
            TextView mDate=myView.findViewById(R.id.date_xml);
            mDate.setText(date);


        }
    }
}
