package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Patricio on 01-12-2016.
 */
public class SupermercadosActivity extends AppCompatActivity implements View.OnClickListener{
    private Button boton1;
    private String region;
    private String nombre_lugar = "Unimarc_Quintero";
    private String code_s = "001";
    private RecyclerView RecycV;
    private RecyclerView.LayoutManager LayMan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercados);
        createMyRecyclerView();

        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(this);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            region = (String) extras.get("REGION");
        }
    }

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
}
