package com.example.internstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.internstuff.adapters.RecyclerViewAdapter;
import com.example.internstuff.models.Place;
import com.example.internstuff.viewmodels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<Place> mPlaces = new ArrayList<Place>();
    private FloatingActionButton mFab;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate: started.");

        mFab = findViewById(R.id.fab);
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recyclerv_view);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class); //constructor is deprecated, just use static method directly ig...

        mMainActivityViewModel.init();

        mMainActivityViewModel.getNicePlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showProgressBar();
                }
                else{
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mMainActivityViewModel.getNicePlaces().getValue().size() - 1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivityViewModel.addNewValue(
                        new Place("um",
                                "https://m.media-amazon.com/images/I/51nwxb8IVmL._AC_SX466_.jpg")//make sure to replace thses stuff with images from api
                );
            }
        });

        initImageBitmaps();

    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mPlaces.add(new Place("cheems", "https://i1.sndcdn.com/avatars-0LB1rBFzJlicpyws-Pkl5mA-t500x500.jpg"));
       // mImageUrls.add("https://i1.sndcdn.com/avatars-0LB1rBFzJlicpyws-Pkl5mA-t500x500.jpg");
        //mNames.add("cheems");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mAdapter = new RecyclerViewAdapter((ArrayList<Place>)mMainActivityViewModel.getNicePlaces().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        /*
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mPlaces, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         */
///asdfafddfafasdfadsfasdafsdadsf

    }
    private void showProgressBar(){mProgressBar.setVisibility(View.VISIBLE);}
    private void hideProgressBar(){mProgressBar.setVisibility(View.GONE);}
}