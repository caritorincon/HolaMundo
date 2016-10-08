package com.example.carito.holamundo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.carito.holamundo.R.id.fab;

public class MainActivity extends AppCompatActivity {

    private EditText palabra;
    private TextView longitud;
    private FloatingActionButton fab;

    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView helloMenu = (TextView) findViewById(R.id.hellomenu);
        registerForContextMenu(helloMenu);

        palabra = (EditText) findViewById(R.id.palabraEdit);
        longitud = (TextView) findViewById(R.id.longitudText);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //1. crear la instancia del administrador de fragmentos
                FragmentManager manager = getFragmentManager();
                //2. Crear una nueva transaction
                FragmentTransaction transaction =  manager.beginTransaction();
                //3.Crear el fragment y añadirlo a la tx
                FirstFragment firstfragment = new FirstFragment();
                transaction.add(R.id.layoutprinciopal ,firstfragment);
                //4. termino la tx
                transaction.commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if (data.hasExtra("largo")){
                longitud.setText(data.getStringExtra("largo"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void longitudOnClick(View v){
        Intent intent = new Intent(this , SegundaActivity.class);
        intent.putExtra("palabra" , palabra.getText().toString());
        startActivityForResult(intent , REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
           Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.opc1) {
            Snackbar.make(fab, "hicieron click en opc 1", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
