package com.example.homebankads;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class telaregistro extends AppCompatActivity {
    Button btretorna, btregistra;
    EditText edtlogin, edtsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaregistro);
        btretorna = (Button) findViewById(R.id.btregretornar);
        btregistra = (Button) findViewById(R.id.btregregistrar);
        edtlogin=(EditText) findViewById(R.id.edtreglogin);
        edtsenha=(EditText) findViewById(R.id.edtregsenha);

        btregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario usrtemp = new usuario(edtlogin.getText().toString(),edtsenha.getText().toString(),10);
           usuariocontrole usrcontrol = new usuariocontrole(telaregistro.this);
           if (!usrcontrol.testaUsuario(usrtemp.getLogin())){
               usrcontrol.insere_usuario(usrtemp);
           }
            }
        });
        btretorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}