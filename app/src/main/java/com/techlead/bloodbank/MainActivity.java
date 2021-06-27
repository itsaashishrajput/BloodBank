package com.techlead.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.techlead.bloodbank.Adapter.RequestAdapter;
import com.techlead.bloodbank.DataModel.RequestData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView requestRecyclerView;
    private RequestAdapter requestAdapter;
    private List<RequestData> requestDataList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.search_button){
                    // open new activity
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));

                }
                return false;
            }
        });
        requestDataList = new ArrayList<>();
        requestDataList.add(new RequestData("Hi my name is antony gonzlvis",R.drawable.logo));
        requestDataList.add(new RequestData("Hi my name is Tony",R.drawable.logo));
        requestDataList.add(new RequestData("Hi my name is Saurabh Jain",R.drawable.logo));
        requestDataList.add(new RequestData("Hi my name is Mukesh",R.drawable.logo));
        requestDataList.add(new RequestData("Hi my name is Pranjal Dhaiya",R.drawable.logo));
        requestDataList.add(new RequestData("Hi my name is Anjali Arora",R.drawable.logo));
        setRequestRecyclerView(requestDataList);

    }

    private void setRequestRecyclerView(List<RequestData> requestDataList) {
        requestRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        requestRecyclerView.setLayoutManager(layoutManager);
        requestAdapter = new RequestAdapter(this,requestDataList);
        requestRecyclerView.setAdapter(requestAdapter);
    }

}