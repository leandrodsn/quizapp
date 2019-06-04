package com.example.quizti.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.quizti.model.CriaBanco;

public class DatabaseController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public DatabaseController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereUsuario(String nome, String email, String senha, String confirmaSenha, byte[] foto) {
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_USUARIO, nome);
        valores.put(CriaBanco.EMAIL, email);
        valores.put(CriaBanco.SENHA, senha);
        valores.put(CriaBanco.FOTO, foto);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();


        if(resultado == -1 ){
            return "Erro ao inserir Registro";
        }else {
            return "Registro inserido com sucesso";
        }
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME_USUARIO, banco.EMAIL, banco.FOTO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor validaLogin(String email, String senha){
        Cursor cursor;
        String[] campos =  {banco.NOME_USUARIO, banco.FOTO};
        String where = banco.EMAIL+" = '"+email+"' AND "+banco.SENHA+" = '"+senha+"';";
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, where, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }



}
