package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.samples.vision.barcodereader.conexion.Server;
import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Patricio on 01-12-2016.
 */
public class RespuestaActivity extends Activity {
    private TextView texto1;
    private TextView texto2;
    private TextView texto3;
    private String url;
    private Producto producto;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);

        texto1 = (TextView) findViewById(R.id.textView);
        texto2 = (TextView) findViewById(R.id.textView2);
        texto3 = (TextView) findViewById(R.id.textView3);


        System.out.println("Por acá!");

        Intent i = getIntent();

        Bundle extras = i.getExtras();
        BarcodeCaptureActivity.fa.finish();
        if (extras != null) {
            Barcode best = (Barcode) extras.get("ID");
            url = "http://telemarket.telprojects.xyz/?"+best.displayValue;
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
                        texto1.setText("Nombre: "+producto.getNombre());
                        texto2.setText("Descripción: "+producto.getDescription());
                        texto3.setText("Valor: "+producto.getValor());

                        //mAdapter = new UIAdapter(reposes);
                        //mRecyclerView.setAdapter(mAdapter);
                        //((UIAdapter) mAdapter).setOnClickListener(Main2Activity.this); // Bind the listener
                    }
                }
            };
            show.execute();
        }
    }

    private Producto getLista(String result){
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
    }


}
