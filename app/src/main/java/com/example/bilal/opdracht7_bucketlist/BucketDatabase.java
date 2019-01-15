package com.example.bilal.opdracht7_bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;



@Database(entities = {Item.class}, version = 1)
public abstract class BucketDatabase extends RoomDatabase {

    public abstract BucketListDAO bucketListDao();

    private final static String NAME_DATABASE = "bucket_item_db";

    //Static instance
    private static BucketDatabase sInstance;

    public static BucketDatabase getInstance(Context context) {

        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context, BucketDatabase.class,   NAME_DATABASE).allowMainThreadQueries().build();
        }
        return sInstance;
    }
}