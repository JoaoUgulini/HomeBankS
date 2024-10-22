package com.example.homebankads;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class telaprincipal extends AppCompatActivity {
    Button btsair,btmovimentos,btextrato;
    TextView txtcorrentista,txtsaldo;
    int idusr;


    @Override
    protected void onResume() {
        super.onResume();
        usuariocontrole usrc = new usuariocontrole(telaprincipal.this);
        txtsaldo.setText("" + usrc.retornaDadosUsuario(txtcorrentista.getText().toString()).getSaldo());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal);
        txtcorrentista=(TextView) findViewById(R.id.txtcorrentista);
        txtsaldo=(TextView) findViewById(R.id.txtsaldo);
        usuariocontrole usrc = new usuariocontrole(telaprincipal.this);
        try {
            Bundle extras = getIntent().getExtras();
            String nome = extras.getString("login");
            idusr = extras.getInt("id");
            txtcorrentista.setText(nome);
            txtsaldo.setText(""+usrc.retornaDadosUsuario(nome).getSaldo());
        }catch(Exception ex){}
        btextrato = (Button)findViewById(R.id.btextrato);
        btextrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaextrato.class);
                i.putExtra("id",idusr);
                startActivity(i);
            }
        });

        this.setTitle("Home Bank ADS - principal");
        btmovimentos=(Button) findViewById(R.id.btmovimentos);
        btmovimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telamovimentos.class);
                i.putExtra("idusuario","");
                i.putExtra("id",idusr);
                startActivity(i);
            }
        });


        btsair=(Button) findViewById(R.id.btencerra);
        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}