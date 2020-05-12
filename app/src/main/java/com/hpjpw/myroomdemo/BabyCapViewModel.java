package com.hpjpw.myroomdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BabyCapViewModel extends AndroidViewModel {
    private BabyCapRepository babyCapRepository;

    public BabyCapViewModel(@NonNull Application application) {
        super(application);
        babyCapRepository =new BabyCapRepository(application);
    }

    public LiveData<List<BabyCap>> getAllBabyCapLive() {
        return babyCapRepository.getAllBabyCapLive();
    }

    void insertBabyCaps(BabyCap... BabyCaps) {
        babyCapRepository.insertBabyCaps(BabyCaps);
    }

    void updateBabyCaps(BabyCap... BabyCaps) {
        babyCapRepository.updateBabyCaps(BabyCaps);
    }

    void deleteBabyCaps(BabyCap... BabyCaps) {
        babyCapRepository.deleteBabyCaps(BabyCaps);
    }

    void deleteAllBabyCaps() {
        babyCapRepository.deleteAllBabyCaps();
    }
}
