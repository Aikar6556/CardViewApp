package com.cifpceuta.cardviewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> elementos = new ArrayList<>();

    Toolbar toolbar;

    Item_adapter adaptador = new Item_adapter(elementos);

    SearchView barraBusqueda;

    FloatingActionButton fabAñadir;







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        barraBusqueda = findViewById(R.id.bBusqueda);

        fabAñadir = findViewById(R.id.fabAñadir);

        fabAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(MainActivity.this);

                // le asociamos el layout correspondiente:
                dialog.setContentView(R.layout.activity_dialog);


                EditText etTextoItem = dialog.findViewById(R.id.etTextoItem);
                Button btnDiaLogAñadir = dialog.findViewById(R.id.btnDialogAñadir);

                btnDiaLogAñadir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String item = etTextoItem.getText().toString();

                        elementos.add(item);

                        adaptador.notifyItemInserted(elementos.size()-1);


                        recyclerView.scrollToPosition(elementos.size()-1);

                        dialog.dismiss();

                    }
                });



                dialog.show();



            }
        });

        barraBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrado(newText);
                return false;
            }
        });



        return true;


    }


    private void filtrado (String texto){

        ArrayList<String> filteredList_items = new ArrayList<>();
        for(String item : elementos){
            if(item.toLowerCase().contains(texto.toLowerCase())){
                filteredList_items.add(item);
            }
        }
        adaptador.setFilterList(filteredList_items);


    }

    boolean flag = false;



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.opcion1){

            elementos.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            Toast.makeText(MainActivity.this,"Opcion1",Toast.LENGTH_LONG).show();
            adaptador.notifyDataSetChanged();

            flag = true;


            //Ascendete
            
        } else if (id == R.id.opcion2) {

            elementos.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });

            Toast.makeText(MainActivity.this,"Opcion2",Toast.LENGTH_LONG).show();
            adaptador.notifyDataSetChanged();

            flag = false;





            //Descendete
            
        } else if (id == R.id.opcion3) {

            if (!flag){

                elementos.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });

                adaptador.notifyDataSetChanged();

                flag = true;


            }else if (flag){

                elementos.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.compareTo(o1);
                    }
                });

                adaptador.notifyDataSetChanged();

                flag = false;


            }



            Toast.makeText(MainActivity.this,"Opcion3",Toast.LENGTH_LONG).show();
            //Interactivo
        } else if (id == R.id.opcion4) {


            if (recyclerView.getLayoutManager().getClass().equals(LinearLayoutManager.class)) {
                Toast.makeText(MainActivity.this, "Opcion4", Toast.LENGTH_LONG).show();

                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }else {

                recyclerView.setLayoutManager(new LinearLayoutManager(this));

            }



        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0;i<20;i++){

            int random = (int) (Math.random()*21);
            elementos.add(String.valueOf(random));


        }


        recyclerView = (RecyclerView)(findViewById(R.id.recicledView_main));
        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }



}