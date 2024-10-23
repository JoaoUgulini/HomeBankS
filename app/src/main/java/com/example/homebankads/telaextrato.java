package com.example.homebankads;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class telaextrato extends AppCompatActivity {
    Button btextratoret;
    Spinner spmeses;
    ListView listaextrato;
    int idusr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaextrato);

        listaextrato=(ListView)findViewById(R.id.listaextrato);
        Bundle extras = getIntent().getExtras();
        idusr = extras.getInt("id");
        movimentoscontrole movi = new movimentoscontrole(telaextrato.this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,movi.Lista_movimentos(idusr));
        listaextrato.setAdapter(adapter);
        btextratoret=(Button) findViewById(R.id.btextratret);
        btextratoret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                spmeses = (Spinner)findViewById(R.id.spmes);
            }
        });
    }
}