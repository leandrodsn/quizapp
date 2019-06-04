package com.example.quizti.controller;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizti.R;
import com.example.quizti.model.Questao;
import com.example.quizti.model.QuestaoRepositorio;
import com.example.quizti.model.VerificaQuestao;

public class MainActivity extends AppCompatActivity {

        public static final String INDICE_QUESTAO = "INDICE_QUESTAO";
        private QuestaoRepositorio repositorio = new QuestaoRepositorio();
        private int indice_questao = 0;
        private TextView textViewPergunta;
        private Button botaoResposta1, botaoResposta2, botaoResposta3, botaoResposta4;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            inicializarComponentes();

            View.OnClickListener listerner = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String resposta = ((Button) v).getText().toString();

                    Questao questao = repositorio.getListaQuestoes().get(indice_questao);
                    VerificaQuestao verificador = new VerificaQuestao();

                    String mensagem;

                    if (verificador.isRespostaCorreta(questao, resposta)){
                        mensagem = "Parabéns, resposta correta!";
                    } else {
                        mensagem = "Resposta Incorreta!";
                    }

                    Toast.makeText( MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                    incrementaIndiceQuestao();
                }
            };




            botaoResposta1.setOnClickListener(listerner);
            botaoResposta2.setOnClickListener(listerner);
            botaoResposta3.setOnClickListener(listerner);
            botaoResposta4.setOnClickListener(listerner);

            if (savedInstanceState != null){
                indice_questao = savedInstanceState.getInt(INDICE_QUESTAO);
            }
            exibirQuestao(indice_questao);

            ActionBar actionbar = getSupportActionBar();
            actionbar.setTitle("Quiz");

        }

        private void incrementaIndiceQuestao() {
            indice_questao++;

            if(indice_questao >= repositorio.getListaQuestoes().size()){
                indice_questao = 0;
            }

            exibirQuestao(indice_questao);
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putInt(INDICE_QUESTAO, indice_questao);

        }

        public void exibirQuestao(final int indice_questao){

                Questao questao = repositorio.getListaQuestoes().get(indice_questao);

                textViewPergunta.setText(questao.getTexto());
                botaoResposta1.setText(questao.getOpcao1());
                botaoResposta2.setText(questao.getOpcao2());
                botaoResposta3.setText(questao.getOpcao3());
                botaoResposta4.setText(questao.getOpcao4());
        }

        private void inicializarComponentes(){

            botaoResposta1 = findViewById(R.id.opcao1_button);
            botaoResposta2 = findViewById(R.id.opcao2_button);
            botaoResposta3 = findViewById(R.id.opcao3_button);
            botaoResposta4 = findViewById(R.id.opcao4_button);
            textViewPergunta = findViewById(R.id.texto_pergunta_textview);
        }
}

