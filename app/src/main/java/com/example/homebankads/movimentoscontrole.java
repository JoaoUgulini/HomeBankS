package com.example.homebankads;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> Lista_movimentos(int id) {
        List<String> listaDeMovimentos = new ArrayList<String>();
        String CONSULTA_USUARIO ="SELECT tipo_opera,valor,destino,dataopera  FROM movimento where idusr="+id+" ORDER BY dataopera";
        //idusr INTEGER

        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(CONSULTA_USUARIO,null);
            if (c.moveToFirst()) {
                do {
                    listaDeMovimentos.add(c.getString(0)+" - "+c.getDouble(1)+" - "+c.getString(2)+" - "+ c.getString(3));
                } while (c.moveToNext());
            }
            c.close();
            bd.close();
            return listaDeMovimentos;
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            return null;
        }

    }
    }


