package com.jonnyjonny.s_assistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.jonnyjonny.s_assistant.main.AppBase;
import com.jonnyjonny.s_assistant.main.schedule.Scheduler;

public class PreDashActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_dash);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.schedule:
                        Intent scheduleIntent=new Intent(PreDashActivity.this, Scheduler.class);
                        startActivity(scheduleIntent);
                        break;
                    case R.id.batches:
                        Intent batchIntent=new Intent(PreDashActivity.this,BatchNoteActivity.class);
                        startActivity(batchIntent);
                        break;
                    case R.id.dashBoard:
                        Intent dashIntent=new Intent(PreDashActivity.this, AppBase.class);
                        startActivity(dashIntent);
                        break;
                }
                return true;
            }
        });
    }
}
