package com.google.android.gms.samples.vision.barcodereader;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.samples.vision.barcodereader.conexion.Server;
import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Patricio on 01-12-2016.
 */

public class MyAsyncTaskInternet {
    private RecyclerView.Adapter mAdapter;
    private static MyAsyncTaskInternet instance;

    public static MyAsyncTaskInternet getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskInternet();
        }
        return instance;
    }

    public void executeMyAsynctask(final RespuestaActivity activity, final RecyclerView mRecyclerView, final int number,
                                   final String code_s, final String code_p) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){
            }

            @Override
            protected String doInBackground(Void... params) {
                //String resultado = new Server().connectToServer("http://telemarket.telprojects.xyz/?s"+code_s+"c"+code_p, 15000);
                String resultado = new Server().connectToServer("http://www.mocky.io/v2/5841ae5d100000320cbb4ccf", 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {

                if(result != null){
                    System.out.println(result);

                    //Why god... why
                    mAdapter = new DataAdapter(activity, getFeeds(result),number);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
    }
    public List<Producto> getFeeds(String result) {
        List<Producto> listaProductos = new ArrayList<>();

        try {
            JSONArray lista = new JSONArray(result);
            int size = lista.length();
            for (int i = 0; i < size; i++) {
                Producto prod = new Producto();
                JSONObject objeto = lista.getJSONObject(i);

                prod.setId(objeto.getInt("Code"));
                prod.setNombre(objeto.getString("name"));
                if (objeto.getString("description") == "null" || objeto.getString("description").isEmpty()) {
                    prod.setDescription("Sin descripción disponible");
                } else {
                    prod.setDescription(objeto.getString("description"));
                }

                prod.setValor(objeto.getString("value"));
                listaProductos.add(prod);
            }

            Collections.sort(listaProductos, new Comparator<Producto>(){

                @Override
                public int compare(Producto o1, Producto o2) {
                    return new Integer(o1.getValor()).compareTo(new Integer(o2.getValor()));
                }

            });
            return listaProductos;
        }catch (JSONException e) {
            e.printStackTrace();
            return listaProductos;
        }
    }
}
