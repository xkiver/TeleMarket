package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;

import java.text.Normalizer;

/**
 * Created by Patricio on 01-12-2016.
 */
public class RespuestaActivity extends AppCompatActivity {
    private String url;
    private Producto producto;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private String best;
    private String best2;
    private String code_s;
    private String region;
    private int vista;
    private String nombre_lugar;
    private int name;
    private boolean first = true;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);
        createMyRecyclerView();

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            best = (String) extras.get("ID");
            code_s = (String) extras.get("CODE_S");
            region = (String) extras.get("REGION");
            vista = (int) extras.get("VISTA");
            if (vista==1){
                nombre_lugar = (String) extras.get("NOMBRE_LUGAR");
            }
            else {
                nombre_lugar = "Ningun_lugar";
            }
            name = (int) extras.get("NAME");
            if(name == 0){
                BarcodeCaptureActivity.fa.finish();
                MyAsyncTaskInternet.getInstance().executeMyAsynctask(this, mRecyclerView,1,region,code_s,best, vista, nombre_lugar,name);
            }
            else{
                String limpio;
                String valor = best;
                valor = valor.toUpperCase();
                // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
                limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
                // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
                limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
                // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
                limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
                limpio = limpio.trim();
                best2 = limpio.replaceAll("\\s", "_");
                best2=remove1(best2);
                MyAsyncTaskInternet.getInstance().executeMyAsynctask(this, mRecyclerView,1,region,code_s,best2, vista, nombre_lugar,name);
            }

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
            if(name == 0){
                MyAsyncTaskInternet.getInstance().executeMyAsynctask(this, mRecyclerView,1,region,code_s,best, vista, nombre_lugar,name);
            }
            else{
                best2 = best.replaceAll(" ", "-");
                MyAsyncTaskInternet.getInstance().executeMyAsynctask(this, mRecyclerView,1,region,code_s,best2, vista, nombre_lugar,name);
            }
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
    public static String remove1(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }
}