package com.example.homebankads;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btlogar;
    TextView linkregistro;
    EditText edtlogin,edtsenha;
    private conectadb banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home Bank ADS");
        banco = new conectadb(MainActivity.this);
        btlogar=(Button) findViewById(R.id.buttonlogar);
        edtlogin=(EditText)findViewById(R.id.edtemail);
        edtsenha=(EditText)findViewById(R.id.edtsenha);
        btlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuariocontrole usrcontrol = new usuariocontrole(MainActivity.this);
                if(usrcontrol.checkusuario(edtlogin.getText().toString(),
                        edtsenha.getText().toString())){
                usuario usr = usrcontrol.retornaDadosUsuario(edtlogin.getText().toString());
                Intent i = new Intent(getApplicationContext(), telaprincipal.class);
                i.putExtra("login",edtlogin.getText().toString() );
                i.putExtra("senha", edtsenha.getText().toString());
                i.putExtra("id",usr.getId());
                startActivity(i);
                }
            }
        });
        linkregistro =(TextView) findViewById(R.id.linkCriaConta);
        linkregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaregistro.class);
                startActivity(i);
            }
        });


    }
}