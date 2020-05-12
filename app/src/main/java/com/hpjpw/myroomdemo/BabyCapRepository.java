package com.hpjpw.myroomdemo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BabyCapRepository {
    private LiveData<List<BabyCap>> allBabyCapLive;
    private BabyCapDao babyCapDao;

    public BabyCapRepository(Context context) {
        BabyCapDatabase babyCapDatabase = BabyCapDatabase.getDatabase(context.getApplicationContext());
        babyCapDao = babyCapDatabase.getBabyCapDao();
        allBabyCapLive = babyCapDao.getBabyCapsLiveData();
    }

    public LiveData<List<BabyCap>> getAllBabyCapLive() {
        return allBabyCapLive;
    }
    void insertBabyCaps(BabyCap... BabyCaps) {
        new InsertAsyncTask(babyCapDao).execute(BabyCaps);
    }

    void updateBabyCaps(BabyCap... BabyCaps) {
        new UpdateAsyncTask(babyCapDao).execute(BabyCaps);
    }

    void deleteBabyCaps(BabyCap... BabyCaps) {
        new DeleteAsyncTask(babyCapDao).execute(BabyCaps);
    }

    void deleteAllBabyCaps(BabyCap... BabyCaps) {
        new DeleteAllAsyncTask(babyCapDao).execute();
    }
    static class InsertAsyncTask extends AsyncTask<BabyCap, Void, Void> {
        private BabyCapDao BabyCapDao;

        InsertAsyncTask(BabyCapDao BabyCapDao) {
            this.BabyCapDao = BabyCapDao;
        }

        @Override
        protected Void doInBackground(BabyCap... BabyCaps) {
            BabyCapDao.insertBabyCap(BabyCaps);
            return null;
        }

    }

    static class UpdateAsyncTask extends AsyncTask<BabyCap, Void, Void> {
        private BabyCapDao BabyCapDao;

        UpdateAsyncTask(BabyCapDao BabyCapDao) {
            this.BabyCapDao = BabyCapDao;
        }

        @Override
        protected Void doInBackground(BabyCap... BabyCaps) {
            BabyCapDao.updateBabyCap(BabyCaps);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<BabyCap, Void, Void> {
        private BabyCapDao BabyCapDao;

        DeleteAsyncTask(BabyCapDao BabyCapDao) {
            this.BabyCapDao = BabyCapDao;
        }

        @Override
        protected Void doInBackground(BabyCap... BabyCaps) {
            BabyCapDao.deleteBabyCap(BabyCaps);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private BabyCapDao BabyCapDao;

        DeleteAllAsyncTask(BabyCapDao BabyCapDao) {
            this.BabyCapDao = BabyCapDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BabyCapDao.deleteAllBabyCap();
            return null;
        }

    }
}
