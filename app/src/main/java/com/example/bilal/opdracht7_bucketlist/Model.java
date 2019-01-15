package com.example.bilal.opdracht7_bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class Model extends ViewModel {

    private ItemRep mRepository;
    private LiveData<List<Item>> mItems;

    public Model(Context context){
        mRepository = new ItemRep(context);
        mItems = mRepository.getAllBucketItems();
    }

    public LiveData<List<Item>> getItems() {
        return mItems;
    }

    public void insert(Item item){
        mRepository.insert(item);
    }

    public void update(Item item){
        mRepository.update(item);
    }

    public void delete(Item item){
        mRepository.delete(item);
    }
}
