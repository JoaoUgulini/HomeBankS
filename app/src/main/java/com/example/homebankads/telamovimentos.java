package com.example.homebankads;




import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class telamovimentos extends AppCompatActivity {
    Button btsaque,btdeposito,btpagamento,bttransferencia,btretornar;
    TextView saldo, nome;
    final Context context = this;
    int idusr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telamovimentos);
        Bundle extras = getIntent().getExtras();
        idusr = extras.getInt("id");
        btsaque=(Button) findViewById(R.id.btsaque);
        btsaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutinflater = LayoutInflater.from(context);
                View promptUserView = layoutinflater.inflate(R.layout.layout_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptUserView);
                final EditText Valordigitado = (EditText) promptUserView.findViewById(R.id.edit_saque_deposito);
                alertDialogBuilder.setTitle("Qual valor você deseja sacar? ");
                alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        realizaMovimento("Saque", Valordigitado);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        btdeposito=(Button) findViewById(R.id.btdeposito);
        btdeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutinflater = LayoutInflater.from(context);
                View promptUserView = layoutinflater.inflate(R.layout.layout_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptUserView);
                final EditText Valordigitado = (EditText) promptUserView.findViewById(R.id.edit_saque_deposito);
                alertDialogBuilder.setTitle("Qual valor você deseja depositar? ");
                alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        movimentoscontrole movic =new movimentoscontrole(telamovimentos.this);
                        movimentos movi = new movimentos();
                        movi.setValor(Double.parseDouble(Valordigitado.getText().toString()));
                        movi.setTipo_opera("Depósito");
                        movi.setIdusr(idusr);
                        Calendar c = Calendar.getInstance();
                        movi.setDataopera((c.getTime()));
                        movic.insere_movimento(movi);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        btpagamento=(Button) findViewById(R.id.btpagamento);
        btpagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutinflater = LayoutInflater.from(context);
                View promptUserView = layoutinflater.inflate(R.layout.layout_pgto, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptUserView);
                final EditText Valordigitado = (EditText) promptUserView.findViewById(R.id.edit_Valor);
                final EditText Objeto = (EditText) promptUserView.findViewById(R.id.edit_destino);
                alertDialogBuilder.setTitle("Efetuar pagamento ");
                alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        realizaMovimento("Pagamento", Valordigitado, Objeto);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        bttransferencia=(Button) findViewById(R.id.bttransfer);
        bttransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutinflater = LayoutInflater.from(context);
                View promptUserView = layoutinflater.inflate(R.layout.layout_transf, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptUserView);
                final EditText Valordigitado = (EditText) promptUserView.findViewById(R.id.edit_Valor_transf);
                final EditText Objeto = (EditText) promptUserView.findViewById(R.id.edit_destino_transf);
                alertDialogBuilder.setTitle("Efetuar Transferencia ");
                alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        realizaMovimento("Transferência",  Valordigitado, Objeto);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        btretornar=(Button) findViewById(R.id.btmovretorna);
        btretornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void realizaMovimento(String op, EditText ValorDigitado, EditText Objeto){
        movimentoscontrole movic =new movimentoscontrole(telamovimentos.this);
        movimentos movi = new movimentos();
        movi.setValor(Double.parseDouble(ValorDigitado.getText().toString()) * (-1));
        movi.setTipo_opera(op);
        movi.setIdusr(idusr);
        movi.setDestino(Objeto.getText().toString());
        Calendar c = Calendar.getInstance();
        movi.setDataopera((c.getTime()));
        movic.insere_movimento(movi);
    }

    public void realizaMovimento(String op, EditText ValorDigitado){
        movimentoscontrole movic =new movimentoscontrole(telamovimentos.this);
        movimentos movi = new movimentos();
        movi.setValor(Double.parseDouble(ValorDigitado.getText().toString()) * (-1));
        movi.setTipo_opera(op);
        movi.setIdusr(idusr);
        Calendar c = Calendar.getInstance();
        movi.setDataopera((c.getTime()));
        movic.insere_movimento(movi);
    }
}