package com.example.bilal.opdracht7_bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ItemRep {

    private BucketDatabase mAppDatabase;
    private BucketListDAO mBucketListDao;
    private LiveData<List<Item>> mItems;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public ItemRep(Context context){
        mAppDatabase = BucketDatabase.getInstance(context);
        mBucketListDao = mAppDatabase.bucketListDao();
        mItems = mBucketListDao.getAllBucketItems();
    }

    public LiveData<List<Item>> getAllBucketItems() {
        return mItems;
    }

    public void insert(final Item item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.insertItems(item);
            }
        });
    }

    public void update(final Item item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.updateItems(item);
            }
        });
    }


    public void delete(final Item item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.deleteItems(item);
            }
        });
    }
}
