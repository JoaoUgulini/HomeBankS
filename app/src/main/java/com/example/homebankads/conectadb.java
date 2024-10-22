package com.example.homebankads;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class conectadb extends SQLiteOpenHelper {

    String tab_usr = "CREATE TABLE IF NOT EXISTS usuario (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "login TEXT," +
            "senha TEXT, " +
            "saldo NUMERIC)";
    String tab_mov ="CREATE TABLE IF NOT EXISTS movimento (" +
            "idmov INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            "idusr INTEGER ," +
            "tipo_opera TEXT ," +
            "valor NUMERIC ," +
            "destino TEXT  ," +
            "dataopera DATE )";
    String cria_t_deposito =" CREATE TRIGGER IF NOT EXISTS deposito\n" +
            "         AFTER INSERT\n" +
            "            ON movimento\n" +
            "      FOR EACH ROW\n" +
            "BEGIN\n" +
            "    UPDATE usuario\n" +
            "       SET saldo = saldo + (NEW.valor)\n" +
            "     WHERE id = new.idusr;\n" +
            "END;";
     /*
     String cria_t_saque =" CREATE TRIGGER IF NOT EXISTS saque \n" +
            "AFTER INSERT ON movimento \n" +
            "FOR EACH ROW WHEN New.tipo_opera=\"saque\"  \n" +
            "BEGIN \n" +
            "\n" +
            "UPDATE usuario\n" +
            "       SET saldo = saldo - NEW.valor\n" +
            "     WHERE id = new.idusr;\n" +
            "\n" +
            "END;";

      */



    conectadb(Context contexto) {

        super(contexto, "homebankdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(tab_usr);
        sqLiteDatabase.execSQL(tab_mov);
        sqLiteDatabase.execSQL(cria_t_deposito);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String RECRIA = "DROP TABLE IF EXISTS usuario";
        String RECRIA2 = "DROP TABLE IF EXISTS movimento";
        sqLiteDatabase.execSQL(RECRIA);
        sqLiteDatabase.execSQL(RECRIA2);
        onCreate(sqLiteDatabase);
    }
}


