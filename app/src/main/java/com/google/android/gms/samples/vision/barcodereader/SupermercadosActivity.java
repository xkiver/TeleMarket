package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Patricio on 01-12-2016.
 */
public class SupermercadosActivity extends Activity implements View.OnClickListener{
    private Button boton1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercados);

        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button5:
                Intent i = new Intent(SupermercadosActivity.this,ConsultarActivity.class);
                startActivity(i);
                break;
        }
    }
}
