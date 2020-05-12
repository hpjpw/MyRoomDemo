package com.hpjpw.myroomdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BabyCap.class}, version = 1, exportSchema = false)
public abstract class BabyCapDatabase extends RoomDatabase {
    private static BabyCapDatabase INSTANCE;

    static synchronized BabyCapDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BabyCapDatabase.class, "babycap_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract BabyCapDao getBabyCapDao();
}
