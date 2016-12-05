/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class ConsultarActivity extends AppCompatActivity implements View.OnClickListener {

    // use a compound button so either checkbox or switch widgets work.
    //private CompoundButton autoFocus;
    //cambio menor
    private CompoundButton useFlash;
    private TextView statusMessage;
    private String region;
    private String code_s;
    private String nombre_lugar;
    private int vista;


    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = (TextView)findViewById(R.id.status_message);

        //autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);

        findViewById(R.id.read_barcode).setOnClickListener(this);


        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            region = (String) extras.get("REGION");
            vista = (int) extras.get("VISTA");
            if (vista==1){
                code_s = (String) extras.get("CODE_S");
                nombre_lugar = (String) extras.get("NOMBRE_LUGAR");
            }
            else {
                code_s = "000";
            }

        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            //intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());
            intent.putExtra("REGION",region);
            intent.putExtra("CODE_S",code_s);
            intent.putExtra("VISTA",vista);
            if(vista==1){
                intent.putExtra("NOMBRE_LUGAR",nombre_lugar);
            }

            //startActivityForResult(intent, RC_BARCODE_CAPTURE);
            startActivity(intent);
        }

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     //* @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     //* @param resultCode  The integer result code returned by the child activity
     *
     *                    through its setResult().
     //* @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        name.setText("");
        barcodeValue.setText("");
        description.setText("");
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);

                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                    //luego de obtener barcode
                    //url = "http://10.112.8.203:8008/?"+barcode.displayValue;
                    //url = "http://www.mocky.io/v2/5812ac760f0000391e0bac92";
                    url = "http://telemarket.telprojects.xyz/?"+barcode.displayValue;
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
                                name.setText("Nombre: "+producto.getNombre());
                                description.setText("Descripción: "+producto.getDescription());
                                barcodeValue.setText("Valor: "+producto.getValor());

                                //mAdapter = new UIAdapter(reposes);
                                //mRecyclerView.setAdapter(mAdapter);
                                //((UIAdapter) mAdapter).setOnClickListener(Main2Activity.this); // Bind the listener
                            }
                        }
                    };
                    show.execute();

                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
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
            Intent i = new Intent(ConsultarActivity.this,ShopCarActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
