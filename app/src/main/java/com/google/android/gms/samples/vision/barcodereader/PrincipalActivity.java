package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Patricio on 01-12-2016.
 */
public class PrincipalActivity extends Activity implements View.OnClickListener{
    private Button boton1;
    private Button boton2;
    private Button boton3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        boton1 = (Button) findViewById(R.id.button2);
        boton1.setOnClickListener(this);
        boton2 = (Button) findViewById(R.id.button3);
        boton2.setOnClickListener(this);
        boton3 = (Button) findViewById(R.id.button4);
        boton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                Intent i = new Intent(PrincipalActivity.this,SupermercadosActivity.class);
                startActivity(i);
                break;
            case R.id.button3:
                Intent i2 = new Intent(PrincipalActivity.this,ConsultarActivity.class);
                startActivity(i2);
                break;
            case R.id.button4:
                //Intent i3 = new Intent(PrincipalActivity.this,ConsultarActivity.class);
                //i.putExtra("DATO",dato);
                //startActivity(i3);
                Context context = getApplicationContext();
                CharSequence text = "Aún no implementado!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
        }
    }
}
