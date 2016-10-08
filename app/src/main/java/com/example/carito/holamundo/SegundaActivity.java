package com.example.carito.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    private TextView palabratext ;
    private Spinner comboCiudad ;
    private ListView listaCiudades;

    private final String[] ciudades = {"Cali" , "Bogota" , "Medellin" , "Barranquilla" , "Cartagena"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        palabratext = (TextView)findViewById(R.id.segundatextView);

        Bundle b = getIntent().getExtras();
        if (b != null){
            String pal = b.getString("palabra");
            palabratext.setText(pal);
        }

        cargarCiudades();
        cargarListaCiudades();
    }

    @Override
    public void finish() {
        Intent dato = new Intent();
        dato.putExtra("largo" , "La longitus de la cadena es : " + palabratext.getText().toString().length());
        setResult(RESULT_OK , dato);
        super.finish();
    }

    private void cargarCiudades(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , ciudades);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        comboCiudad = (Spinner) findViewById(R.id.comboCiudades);
        comboCiudad.setAdapter(arrayAdapter);


        comboCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                palabratext.setText("selecciono de combo>>> " + ciudades[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void cargarListaCiudades( ){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , ciudades);
        listaCiudades = (ListView) findViewById(R.id.listaCiudades);
        listaCiudades.setAdapter(arrayAdapter);

        listaCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                palabratext.setText("selecciono de lista >>> " + ciudades[position]);
            }
        });
    }


}
