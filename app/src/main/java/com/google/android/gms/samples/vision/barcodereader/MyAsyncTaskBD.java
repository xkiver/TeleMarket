package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patricio on 02-12-2016.
 */

public class MyAsyncTaskBD {

    private RecyclerView.Adapter mAdapter;

    private static MyAsyncTaskBD instance;

    public static MyAsyncTaskBD getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskBD();
        }
        return instance;
    }

    public void executeMyAsynctask(final ShopCarActivity activity, final RecyclerView mRecyclerView, final int number) {
        AsyncTask<Void, Void, List<Producto>> task = new AsyncTask<Void, Void, List<Producto>>() {

            @Override
            protected void onPreExecute(){
            }

            @Override
            protected List<Producto> doInBackground(Void... params) {
                List<Producto> resultado = getFeeds(activity);
                return resultado;
            }

            @Override
            protected void onPostExecute(List<Producto> result) {

                if(result != null){
                    System.out.println(result);

                    //Why god... why
                    mAdapter = new DataAdapter(activity, result,number);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
    }
    public List<Producto> getFeeds(Context activity) {
        List<Producto> feeds = new ArrayList<>();
        DataBaseClass myDB = new DataBaseClass(activity);

        Cursor res = myDB.getAllData();
        if(res.getCount() == 0) {
        }
        else {
            while (res.moveToNext()) {
                Producto feed = new Producto();
                feed.setNombre(res.getString(1));
                feed.setDescription(res.getString(2));
                feed.setValor(res.getString(3));
                feed.setId(res.getInt(4));
                feed.setSupermercado(res.getString(5));
                feed.setLugar(res.getString(6));

                feeds.add(feed);
            }
        }
        return feeds;
    }
}
