package com.example.quizti.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizti.R;
import com.example.quizti.model.CriaBanco;

public class LoginMainActivity extends AppCompatActivity {
    private EditText entradaEmail, entradaSenha;
    private Button botaoEntrar, botaoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

            inicializarComponentes();

    botaoEntrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseController query = new DatabaseController(getBaseContext());
            String email = entradaEmail.getText().toString();
            String senha = entradaSenha.getText().toString();
            Cursor cursor;

            if(email.length() <= 0){
                Toast.makeText(getApplicationContext(), "Preencha o campo nome!", Toast.LENGTH_SHORT).show();
                entradaEmail.requestFocus();
            } else if(senha.length() <= 0){
                Toast.makeText(getApplicationContext(), "Preencha o campo senha!", Toast.LENGTH_SHORT).show();
                entradaSenha.requestFocus();
            }else {

                cursor = query.validaLogin(email, senha);
                //cursor = query.carregaDados();

                if(cursor.getCount() <= 0){
                    Toast.makeText(getApplicationContext(), "usuário ou senha invalidos!", Toast.LENGTH_SHORT).show();
                }else {
                    cursor.moveToFirst();
                    String paramNome = cursor.getString(cursor.getColumnIndex(CriaBanco.NOME_USUARIO));
                    byte[] paramFoto = cursor.getBlob(cursor.getColumnIndex(CriaBanco.FOTO));

                    Bundle parametros = new Bundle();
                    parametros.putString("nome_usuario", paramNome);
                    parametros.putByteArray("foto", paramFoto);

                    Intent intentMain = new Intent(getApplicationContext(), InicialActivity.class);
                    intentMain.putExtras(parametros);
                    startActivity(intentMain);
                }
            }

        }
    });

    botaoCadastro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intentMain = new Intent(LoginMainActivity.this, CadastroActivity.class);
            startActivity(intentMain);
        }
    });

    }

    private void inicializarComponentes() {
        entradaEmail = findViewById(R.id.editEmailID);
        entradaSenha = findViewById(R.id.editSenhaID);
        botaoEntrar = findViewById(R.id.btEntrarID);
        botaoCadastro = findViewById(R.id.btnCadastrar);
    }
}