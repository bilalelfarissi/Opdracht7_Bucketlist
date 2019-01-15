package com.example.bilal.opdracht7_bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Deze interface handelt de queries af voor een bucket item
 */
@Dao
public interface BucketListDAO {

    @Query("SELECT * FROM bucket_item")
    public LiveData<List<Item>> getAllBucketItems();

    @Insert
    void insertItems(Item item);

    @Delete
    void deleteItems(Item item);

    @Update
    void updateItems(Item item);
}
