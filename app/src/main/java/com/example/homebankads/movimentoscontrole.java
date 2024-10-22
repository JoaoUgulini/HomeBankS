package com.example.homebankads;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class movimentoscontrole {

    private SQLiteDatabase db;
    private conectadb banco;

    public movimentoscontrole(Context contexto) {
        this.banco = new conectadb(contexto);
    }
    public void insere_movimento(movimentos movi){

        String INSERE_MOVIMENTO = "INSERT INTO movimento (idusr,tipo_opera,valor,destino,dataopera)" +
                " VALUES ("+
                "'"+movi.getIdusr()+"','"+movi.getTipo_opera()+"','"+
                movi.getValor()+"','"+movi.getDestino()+"','"+movi.getDataopera()+"')";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(INSERE_MOVIMENTO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }


    }

    public void apaga_movimento(movimentos movi){
        String APAGA_MOVIMENTO = "DELETE FROM movimento WHERE idmov ="+movi.getIdmov();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(APAGA_MOVIMENTO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public void atualiza_movimento(movimentos movi){
        String ATUALIZA_MOVIMENTO = "UPDATE movimento SET " +
                "idusr = '"+movi.getIdusr()+"'," +
                "tipo_opera = '"+movi.getTipo_opera()+"',"+
                "valor = '"+movi.getValor()+"',"+
                "destino = '"+movi.getDestino()+"',"+
                "dataopera = '"+movi.getDataopera()+"'"+
                "WHERE idmov = '"+movi.getIdmov()+"'";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(ATUALIZA_MOVIMENTO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }

   /* public void retorna_movimentos(String mes, String ano) {
        movimentos mov = new movimentos();
        String rm = "SELECT * FROM movimentos WHERE dataoperacao BETWEEN '"+ano+"'/'"+mes+"'/01 AND '"+ano+"'/'"+mes+"'/31";
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            ArrayList<String> linhas = new ArrayList<>();
            ArrayAdapter meuAdapter = new ArrayAdapter(
                    movimentoscontrole.this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.listview,
                    linhas);
            Cursor c=bd.rawQuery(rm,null);
            if (c.moveToFirst()) {
                do {
                    mov.setIdmov(Integer.parseInt(c.getString(0)));
                    mov.setIdusr(Integer.parseInt(c.getString(1)));
                    mov.setTipo_opera(c.getString(2));
                    mov.setValor(Double.parseDouble(c.getString(3)));
                    mov.setDestino(c.getString(4));
                    mov.setDataopera(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(c.getString(4)));
                } while (c.moveToNext());
            }
            c.close();
            bd.close();
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());

       */
    }


