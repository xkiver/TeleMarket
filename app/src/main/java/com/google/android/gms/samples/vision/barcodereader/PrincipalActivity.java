package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Patricio on 01-12-2016.
 */
public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Spinner spinner;
    private String region;
    //comentario de Luchiin

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        boton1 = (Button) findViewById(R.id.button2);
        boton1.setOnClickListener(this);
        boton2 = (Button) findViewById(R.id.button3);
        boton2.setOnClickListener(this);
        boton3 = (Button) findViewById(R.id.button4);
        boton3.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.regions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                Intent i = new Intent(PrincipalActivity.this,SupermercadosActivity.class);
                i.putExtra("REGION",region);
                startActivity(i);
                break;
            case R.id.button3:
                Intent i2 = new Intent(PrincipalActivity.this,ConsultarActivity.class);
                i2.putExtra("REGION",region);
                startActivity(i2);
                break;
            case R.id.button4:
                //Intent i3 = new Intent(PrincipalActivity.this,ConsultarActivity.class);
                //i.putExtra("DATO",dato);
                //startActivity(i3);
                Context context = getApplicationContext();
                CharSequence text = "Aún no implementado!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
        }
    }

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
            Intent i = new Intent(PrincipalActivity.this, ShopCarActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if (pos <10){
            String cero = "0";
            region = cero+ String.valueOf(pos);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
