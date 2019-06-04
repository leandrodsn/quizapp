package com.example.quizti.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizti.R;
import com.example.quizti.controller.UsuariosActicity;

public class UsuarioDetalhes extends AppCompatActivity {
    private ImageView foto;
    private TextView nome, email;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalhes);

        Intent intent = getIntent();
        String paramNome = (String) intent.getSerializableExtra("nome");
        String paramEmail = (String) intent.getSerializableExtra("email");
        byte[] paramFoto = intent.getByteArrayExtra("foto");


        nome = (TextView)findViewById(R.id.txtVwNomeDetalhe);
        email = (TextView)findViewById(R.id.txtVwEmailDetalhe);
        foto = (ImageView)findViewById(R.id.imgvFotoDetalhe);

        if(paramFoto != null){
            Bitmap btm = BitmapFactory.decodeByteArray(paramFoto, 0, paramFoto.length);
            foto.setImageBitmap(btm);
        }

        nome.setText(paramNome);
        email.setText(paramEmail);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detalhes");

    }



}
