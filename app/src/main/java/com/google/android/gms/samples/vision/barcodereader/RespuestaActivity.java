package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by Patricio on 01-12-2016.
 */
public class RespuestaActivity extends AppCompatActivity {
    private String url;
    private Producto producto;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Barcode best;
    private String code_s;
    private boolean first = true;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);
        createMyRecyclerView();

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        BarcodeCaptureActivity.fa.finish();
        if (extras != null) {
            best = (Barcode) extras.get("ID");
            code_s = (String) extras.get("code_s");
            MyAsyncTaskInternet.getInstance().executeMyAsynctask(this, mRecyclerView,1,code_s,best.displayValue);
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
    public void onPause() {
        super.onPause();
        first = false;
    }

    public void onResume(){
        super.onResume();
        if (!first){
            MyAsyncTaskInternet.getInstance().executeMyAsynctask(this, mRecyclerView,1,code_s,best.displayValue);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(RespuestaActivity.this,ShopCarActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}