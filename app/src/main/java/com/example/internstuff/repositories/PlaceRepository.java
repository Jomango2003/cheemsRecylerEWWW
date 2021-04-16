package com.example.internstuff.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.internstuff.models.Place;

import java.util.ArrayList;
import java.util.List;

/*
Singleton pattern
 */
public class PlaceRepository {

    private static PlaceRepository instance;
    private ArrayList<Place> dataset = new ArrayList<>();

    public static PlaceRepository getInstance(){
        if(instance == null){
            instance = new PlaceRepository();
        }
        return instance;
    }
    //pretend to get data from online source
    public MutableLiveData<List<Place>> getPlaces(){
        setPlaces();

        MutableLiveData<List<Place>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setPlaces(){
        dataset.add(new Place("cheems", "https://m.media-amazon.com/images/I/51nwxb8IVmL._AC_SX466_.jpg"));
        dataset.add(new Place("cheems2", "https://m.media-amazon.com/images/I/51nwxb8IVmL._AC_SX466_.jpg"));
        dataset.add(new Place("cheems3", "https://m.media-amazon.com/images/I/51nwxb8IVmL._AC_SX466_.jpg"));
        dataset.add(new Place("cheems4", "https://m.media-amazon.com/images/I/51nwxb8IVmL._AC_SX466_.jpg"));

    }
}
