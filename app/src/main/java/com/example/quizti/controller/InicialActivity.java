package com.example.quizti.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizti.R;

public class InicialActivity extends AppCompatActivity {

    private Button botaoQuiz;
    private Button botaoRanking;
    private Button botaoCompartilhar;
    private ImageView fotoPerfil;
    private TextView nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        fotoPerfil = (ImageView)findViewById(R.id.imgVwFotoPerfil);
        nomeUsuario = (TextView)findViewById(R.id.txtVwNomeUsuario);
        Intent intentDados = getIntent();

        Bundle dados = intentDados.getExtras();

        String nome = dados.getString("nome_usuario");
        byte[] fotoArray = dados.getByteArray("foto");

        if(fotoArray != null){
            Bitmap btm = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
            fotoPerfil.setImageBitmap(btm);
        }
        nomeUsuario.setText(nome);

        if(dados != null){
            Toast.makeText(InicialActivity.this, "Nome: "+ nome, Toast.LENGTH_LONG).show();
        }

        botaoQuiz = (Button)findViewById(R.id.btnQuiz);
        botaoRanking = (Button)findViewById(R.id.btnRaking);
        botaoCompartilhar = (Button)findViewById(R.id.btnCompartilha);

        botaoQuiz.setText("Começar");
        botaoRanking.setText("Ranking");
        botaoCompartilhar.setText("Compartilhe");

        botaoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentQuiz = new Intent(InicialActivity.this, MainActivity.class);
                startActivity(intentQuiz);
            }
        });

        botaoRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRanking = new Intent(InicialActivity.this, UsuariosActicity.class);
                startActivity(intentRanking);
            }
        });

        botaoCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Olá, Estou usando o aplicativo QuizTI!\n"
                                                            +"Baixe este aplicativo através do Play Store e divirta-se!!");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Compartilhe com seus amigos"));
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Perfil");
    }


}
