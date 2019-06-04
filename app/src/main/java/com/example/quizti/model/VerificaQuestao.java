package com.example.quizti.model;

public class VerificaQuestao {

    public boolean isRespostaCorreta(Questao questao, String resposta) {
        return questao.getRespostaCorreta() == resposta;
    }
}
