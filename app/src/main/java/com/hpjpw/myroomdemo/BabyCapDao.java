package com.hpjpw.myroomdemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BabyCapDao {
    @Insert
    void insertBabyCap(BabyCap... babyCaps);

    @Update
    void updateBabyCap(BabyCap... babyCaps);

    @Delete
    void deleteBabyCap(BabyCap... babyCaps);

    @Query("DELETE FROM BABYCAP")
    void deleteAllBabyCap();

    @Query("SELECT * FROM BABYCAP ORDER BY ID DESC")
    List<BabyCap> getBabyCaps();

    @Query("SELECT * FROM BABYCAP ORDER BY ID DESC")
    LiveData<List<BabyCap>> getBabyCapsLiveData();
}
