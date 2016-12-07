package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.samples.vision.barcodereader.conexion.Server;
import com.google.android.gms.samples.vision.barcodereader.modelo.Supermercado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patricio on 01-12-2016.
 */
public class SupermercadosActivity extends AppCompatActivity implements ItemClickListener{
    private Button boton1;
    private String region;
    private String nombre_lugar;// = "Unimarc_Quintero";
    private String code_s;// = "001";
    private List<Supermercado> reposes;
    private RecyclerView RecycV;
    private RecyclerView.LayoutManager LayMan;
    private RecyclerView.Adapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercados);
        createMyRecyclerView();

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            region = (String) extras.get("REGION");
        }

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new Server().connectToServer("http://telemarket.telprojects.xyz/?s", 15000);
                System.out.println(resultado);
                return resultado;
            }

            @Override
            protected void onPostExecute(String resultado) {

                if (resultado != null) {
                    System.out.println(resultado);

                    //Why god... why

                    reposes = getFeedS(resultado);
                    System.out.println(reposes);
                    mAdapter = new DataAdapterSuper(reposes);
                    RecycV.setAdapter(mAdapter);
                    ((DataAdapterSuper) mAdapter).setOnClickListener(SupermercadosActivity.this); // Bind the listener
                }
                else{
                    System.out.println("else");
                }
            }
        };

        task.execute();


    }


/*
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button5:
                Intent i = new Intent(SupermercadosActivity.this,ConsultarActivity.class);
                i.putExtra("REGION",region);
                i.putExtra("CODE_S",code_s);
                i.putExtra("NOMBRE_LUGAR",nombre_lugar);
                i.putExtra("VISTA",1);
                startActivity(i);
                break;
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void createMyRecyclerView() {
        RecycV = (RecyclerView) findViewById(R.id.supers);
        RecycV.setHasFixedSize(true);

        LayMan = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecycV.setLayoutManager(LayMan);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(SupermercadosActivity.this,ShopCarActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v, int position) {
        final Supermercado repo = reposes.get(position);
        Intent i = new Intent(SupermercadosActivity.this,ConsultarActivity.class);
        i.putExtra("REGION",repo.getRegion());
        i.putExtra("CODE_S",repo.getN());
        i.putExtra("NOMBRE_LUGAR",repo.getNombre());
        i.putExtra("VISTA",1);
        startActivity(i);
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
                if (objeto.getString("Region").equals(region)){

                    superm.setN(objeto.getString("N"));
                    superm.setNombre(objeto.getString("name")+"_"+objeto.getString("Lugar"));
                    superm.setRegion(objeto.getString("Region"));
                    superm.setLugar(objeto.getString("Lugar"));

                    listaSupermercados.add(superm);
                }
            }
            return listaSupermercados;
        }catch (JSONException e) {
            e.printStackTrace();
            return listaSupermercados;
        }

    }
}
