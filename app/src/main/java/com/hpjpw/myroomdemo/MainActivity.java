package com.hpjpw.myroomdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    BabyCapViewModel babyCapViewModel;
    BabyCapRecyclerViewAdapter babyCapRecyclerViewAdapter;
    RecyclerView recyclerView;
    Button buttonInsert, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("宝宝相册");
        babyCapViewModel = ViewModelProviders.of(this).get(BabyCapViewModel.class);
        babyCapRecyclerViewAdapter = new BabyCapRecyclerViewAdapter(babyCapViewModel, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(babyCapRecyclerViewAdapter);

        babyCapViewModel.getAllBabyCapLive().observe(this, new Observer<List<BabyCap>>() {
            @Override
            public void onChanged(List<BabyCap> babyCaps) {
                babyCapRecyclerViewAdapter.setBabyCapList(babyCaps);
                babyCapRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        final EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchEditText.getText().toString();
                setBabyCapListVisibility(keyword);
                babyCapRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonClear = findViewById(R.id.buttonClear);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BabyCap i1 = new BabyCap(R.drawable.i1, "此系列服装有点cute，像不像小车夫。",
                        "毡帽系列", true);
                BabyCap i2 = new BabyCap(R.drawable.i2, "宝宝变成了小蜗牛，爬啊爬啊爬啊。",
                        "蜗牛系列", true);
                BabyCap i3 = new BabyCap(R.drawable.i3, "小蜜蜂，嗡嗡嗡，飞到西，飞到东。",
                        "小蜜蜂系列", true);
                babyCapViewModel.insertBabyCaps(i1,i2,i3);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                babyCapViewModel.deleteAllBabyCaps();
            }
        });
    }

    private void setBabyCapListVisibility(String keyword) {
        LiveData<List<BabyCap>> listLiveData = babyCapViewModel.getAllBabyCapLive();
        List<BabyCap> babyCapList = babyCapViewModel.getAllBabyCapLive().getValue();
        assert babyCapList != null;
        for (BabyCap babyCap : babyCapList) {
            if (babyCap.getNews_title().contains(keyword))
                babyCap.setVisibility(true);
            else
                babyCap.setVisibility(false);
        }
    }
}
