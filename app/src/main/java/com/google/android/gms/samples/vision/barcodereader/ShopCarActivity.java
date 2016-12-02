package com.google.android.gms.samples.vision.barcodereader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Patricio on 02-12-2016.
 */
public class ShopCarActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);
        createMyRecyclerView();
        MyAsyncTaskBD.getInstance().executeMyAsynctask(this, mRecyclerView,2);
    }
    public void createMyRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
