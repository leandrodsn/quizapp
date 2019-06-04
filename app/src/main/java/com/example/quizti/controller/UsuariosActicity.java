package com.example.quizti.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quizti.R;
import com.example.quizti.model.CriaBanco;
import com.example.quizti.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosActicity extends AppCompatActivity {
    private UsuariosAdapter adaptador;
    private ListView controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        final List<Usuario> lista;
        DatabaseController dao = new DatabaseController(getBaseContext());
        Cursor cursor = dao.carregaDados();
        lista = cursor2List(cursor);
        adaptador = new UsuariosAdapter(getApplication(),R.layout.item_usuario, lista);
        controle = (ListView)findViewById(R.id.listUsuarios);
        controle.setAdapter(adaptador);

        controle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intentDetalhes = new Intent(UsuariosActicity.this, UsuarioDetalhes.class);
                intentDetalhes.putExtra("nome", lista.get(position).getNome());
                intentDetalhes.putExtra("email", lista.get(position).getEmail());
                intentDetalhes.putExtra("foto", lista.get(position).getFoto());

                startActivity(intentDetalhes);
            }
        });

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Ranking");
    }

    protected List<Usuario> cursor2List(Cursor cursor){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        cursor.moveToFirst();
        do{
            Usuario usuario = new Usuario();

            usuario.setNome(cursor.getString(cursor.getColumnIndex(CriaBanco.NOME_USUARIO)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex(CriaBanco.EMAIL)));
            usuario.setFoto(cursor.getBlob(cursor.getColumnIndex(CriaBanco.FOTO)));

            usuarios.add(usuario);
        }while(cursor.moveToNext());
        return usuarios;
    }


}
