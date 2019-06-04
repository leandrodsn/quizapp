package com.example.quizti.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_DATABASE = "db_Quizti";
    public static final String TABELA = "usuarios";

    public static final String ID = "_id";
    public static final String NOME_USUARIO = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String FOTO = "foto";
    public static final String SCORE = "score";
    public static final int VERSAO = 1;


    public CriaBanco(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+" ("
        + ID + " integer primary key autoincrement,"
        + NOME_USUARIO + " text not null,"
        + EMAIL + " text,"
        + SENHA + " text,"
        + FOTO +  " blob,"
        + SCORE + " integer default 0);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
