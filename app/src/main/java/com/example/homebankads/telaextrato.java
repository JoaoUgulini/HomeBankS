package com.example.homebankads;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedList;


public class telaextrato extends AppCompatActivity {
    Button btextratoret,btextmostra;
    Spinner spmeses, spanos;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaextrato);
        btextratoret=(Button) findViewById(R.id.btextratret);
        listview = (ListView) findViewById(R.id.listview);
        btextratoret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btextmostra = (Button) findViewById(R.id.btextmostra);
        btextmostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostraMovimentos();
            }
        });
    }

    public void mostraMovimentos(){
        String mes = this.spmeses.getSelectedItem().toString();
        String ano = this.spanos.getSelectedItem().toString();
        String sql = "SELECT * FROM movimentos WHERE dataopera BETWEEN '"+ano+"'/'"+mes+"'/01  AND '"+ano+"'/'"+mes+"'/31";
        LinkedList<String> linhas = new LinkedList<String>();

    }
}