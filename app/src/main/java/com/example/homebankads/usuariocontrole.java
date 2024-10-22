package com.example.homebankads;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

public class usuariocontrole {

    private SQLiteDatabase db;
    private conectadb banco;

    public usuariocontrole(Context contexto) {

        this.banco = new conectadb(contexto);
    }

    public void insere_usuario(usuario usr){
        String INSERE_USUARIO = "INSERT INTO usuario (login,senha,saldo) values ('"
                +usr.getLogin()+"','"+usr.getSenha()+"',"+usr.getSaldo()+")";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(INSERE_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }


    }

    public void apaga_usuario(usuario usr){
        String APAGA_USUARIO = "DELETE FROM usuario WHERE id ="+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(APAGA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public void atualiza_usuario(usuario usr){
        String ATUALIZA_USUARIO = "UPDATE TABLE usuario SET login = "+usr.getLogin()+", senha ="+usr.getSenha()+
                ",saldo="+usr.getSaldo()+" WHERE id = "+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(ATUALIZA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }

    public usuario retornaDadosUsuario(String login){
        usuario usr =new usuario();
        String CONSULTA_USUARIO ="SELECT * FROM usuario where login ='"+login+"'";
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(CONSULTA_USUARIO,null);
            ArrayList<String> linhas = new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    usr.setId(Integer.parseInt(c.getString(0)));
                    usr.setLogin(c.getString(1));
                    usr.setSenha(c.getString(2));
                    usr.setSaldo(c.getDouble(3));
                } while (c.moveToNext());
            }
            c.close();
            bd.close();
            return usr;
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            return null;
        }

    }

    public List<usuario> Consulta_todos_usuarios() {
        List<usuario> listaDeUsuarios = new ArrayList<usuario>();
        String CONSULTA_USUARIO ="SELECT id,login,senha,saldo FROM usuario ORDER BY login";
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(CONSULTA_USUARIO,null);
            if (c.moveToFirst()) {
                do {
                    usuario usr = new usuario();
                    usr.setId(Integer.parseInt(c.getString(0)));
                    usr.setLogin(c.getString(1));
                    usr.setSenha(c.getString(2));
                    usr.setSaldo(c.getDouble(3));
                    listaDeUsuarios.add(usr);
                } while (c.moveToNext());
            }
            c.close();
            bd.close();
            return listaDeUsuarios;
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            return null;
        }

    }

    public boolean testaUsuario(String nome) {
        String PESQUISA_POR_NOME="SELECT id FROM usuario WHERE login ='"+nome+"'";
        boolean ret=false;
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret =true;
            }
            banco.close();
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }
        return ret;
    }


    public boolean checkusuario(String nome, String senha) {
        String PESQUISA_POR_NOME_SENHA="SELECT id  FROM usuario WHERE login ='"+nome+"' AND senha='"+senha+"'";
       boolean ret=false;
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME_SENHA,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret =true;
            }
            banco.close();
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }
       return ret;
    }
}
