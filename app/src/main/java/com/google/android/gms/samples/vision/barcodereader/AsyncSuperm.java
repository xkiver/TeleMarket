package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.samples.vision.barcodereader.conexion.Server;
import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;
import com.google.android.gms.samples.vision.barcodereader.modelo.Supermercado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Luchiin on 06-12-2016.
 */

public class AsyncSuperm {
    private List<Supermercado> reposes;

    public AsyncSuperm() {
        System.out.println("ACA");
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new Server().connectToServer("http://telemarket.telprojects.xyz/?s", 15000);

                return resultado;
            }

            @Override
            protected void onPostExecute(String resultado) {

                if (resultado != null) {
                    System.out.println(resultado);

                    //Why god... why

                    reposes = getFeedS(resultado);
                    System.out.println(reposes);
                }
                else{
                    System.out.println("else");
                }
            }
        };

        task.execute();
    }

    public List<Supermercado> getFeedS(String result){
        List<Supermercado> listaSupermercados = new ArrayList<>();
        String place;
        try {
            JSONArray lista = new JSONArray(result);
            int size = lista.length();
            for (int i = 0; i < size; i++) {
                Supermercado superm = new Supermercado();
                JSONObject objeto = lista.getJSONObject(i);

                superm.setN(objeto.getString("N"));
                superm.setNombre(objeto.getString("name"));
                superm.setRegion(objeto.getString("Region"));
                superm.setLugar(objeto.getString("Lugar"));

                listaSupermercados.add(superm);
            }
            return listaSupermercados;
        }catch (JSONException e) {
            e.printStackTrace();
            return listaSupermercados;
        }

    }
    public List<Supermercado> getSup(){
        return reposes;

    }
}
