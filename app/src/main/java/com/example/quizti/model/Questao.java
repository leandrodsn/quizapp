package com.example.quizti.model;

public class Questao {

    private String texto;
    private String respostaCorreta;
    private String opcao1;
    private String opcao2;
    private String opcao3;
    private String opcao4;

    public Questao(String texto, String respostaCorreta, String opcao1, String opcao2, String opcao3, String opcao4){
        this.texto = texto;
        this.respostaCorreta = respostaCorreta;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.opcao4 = opcao4;
    }

    public String getTexto() { return texto; }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public String getOpcao1() {
        return opcao1;
    }
    public String getOpcao2() {
        return opcao2;
    }
    public String getOpcao3() {
        return opcao3;
    }
    public String getOpcao4() {
        return opcao4;
    }
}
