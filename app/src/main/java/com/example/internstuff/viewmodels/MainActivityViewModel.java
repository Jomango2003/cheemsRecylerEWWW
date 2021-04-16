package com.example.internstuff.viewmodels;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.internstuff.models.Place;
import com.example.internstuff.repositories.PlaceRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {//holds the business logic for whatever that means lol

    private MutableLiveData<List<Place>> mPlaces; //subclass of live data, can change; live data cant be changed, it can only be observed
    private PlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mPlaces != null){
            return;
        }
        mRepo = PlaceRepository.getInstance();
        mPlaces = mRepo.getPlaces(); //retrieves mutable live data list from repository
    }

    public void addNewValue(final Place place){
        mIsUpdating.setValue(true);
        //this is not being ran...
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Place> currentPlaces = mPlaces.getValue();
                currentPlaces.add(place);
                System.out.println(currentPlaces);
                Log.d("MainActivityViewModel", "i got ran but like bruh");
                mPlaces.postValue(currentPlaces);
                mIsUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
    public LiveData<List<Place>> getNicePlaces(){
        return mPlaces;
    }

}
