package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by Patricio on 01-12-2016.
 */
public class RespuestaActivity extends Activity {
    private String url;
    private Producto producto;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);
        createMyRecyclerView();

        Intent i = getIntent();

        Bundle extras = i.getExtras();
        BarcodeCaptureActivity.fa.finish();
        if (extras != null) {
            Barcode best = (Barcode) extras.get("ID");
            String code_s = (String) extras.get("code_s");
            MyAsyncTask.getInstance().executeMyAsynctask(this, mRecyclerView,1,code_s,best.displayValue);
            /*url = "http://telemarket.telprojects.xyz/?"+best.displayValue;
            AsyncTask<Void, Void, String> show = new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute(){

                }

                @Override
                protected String doInBackground(Void... params) {
                    String resultado = new Server().connectToServer(url, 15000);
                    return resultado;
                }

                @Override
                protected void onPostExecute(String result) {
                    if(result != null){
                        System.out.println(result);
                        producto = getLista(result);

                        //mAdapter = new UIAdapter(reposes);
                        //mRecyclerView.setAdapter(mAdapter);
                        //((UIAdapter) mAdapter).setOnClickListener(Main2Activity.this); // Bind the listener
                    }
                }
            };
            show.execute();*/
        }
    }
    public void createMyRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /*private Producto getLista(String result){
        Producto producto = new Producto();
        try {
            JSONObject objeto = new JSONObject(result);
            producto.setId(objeto.getInt("id"));
            producto.setNombre(objeto.getString("name"));
            producto.setDescription(objeto.getString("description"));
            producto.setValor(objeto.getString("value"));
            return producto;
        }catch (JSONException e) {
            e.printStackTrace();
            return producto;
        }
    }*/

}