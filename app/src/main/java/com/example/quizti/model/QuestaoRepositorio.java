package com.example.quizti.model;

import java.util.ArrayList;
import java.util.List;

public class QuestaoRepositorio {

    public List<Questao> getListaQuestoes() {
        return new ArrayList<Questao>() {{
            add(new Questao("As heranças, que são princípios de orientação a objetos, permitem o compartilhamento de atributos e métodos pelas classes" +
                    " e são usadas com o intuito de se reaproveitar código ou comportamento generalizado ou especializar operações ou atributos?",
                    "Verdadeiro", "Falso", "Falso", "Verdadeiro", "Falso"));
            add(new Questao("O que significa HTML?",
                    "HyperText Master Language", "HyperText Markup Language","HyperTest Markup Language",
                    "HyperText Master Language", "HyperTest Master Language"));
            add(new Questao("Qual a tag de marcação de parágrafos?",
                    "<p>", "<br>", "<h1>", "<body>", "<p>"));
            add(new Questao("Dentre as tags abaixo, qual será representada em maior tamanho em uma página HTML?",
                    "<h1>", "<h1>", "<h4>", "<h2>", "<h3>"));
            add(new Questao("Qual a função da tag <br>?",
                    "quebrar linha", "centralizar conteúdo", "quebrar linha",
                    "criar efeito", "definir tamanho do elemento"));
        }};

    }

}
