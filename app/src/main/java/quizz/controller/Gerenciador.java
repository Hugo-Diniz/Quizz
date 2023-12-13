package main.java.quizz.controller;

import main.java.quizz.domain.Pergunta;

public class Gerenciador {
    /*Criar o metodo de jogar;
    Criar o metodo de criar uma nova Pergunta;
    Criar o metodo de adcionar alternativas;
    Criar o metodo de editar pergunta
    Criar o metodo de remover pergunta
    */
    private Pergunta perguntaAtual;

    public Gerenciador(Pergunta perguntaAtual) {
        this.perguntaAtual = perguntaAtual;
    }

    public Pergunta getPerguntaAtual() {
        return perguntaAtual;
    }

    public void setPerguntaAtual(Pergunta perguntaAtual) {
        this.perguntaAtual = perguntaAtual;
    }
    
    public void criarPergunta() {

    }

    public void adcionarAlternativas(Pergunta pergunta) {

    }

    public void editarPergunta(Pergunta pergunta) {

    }

    public void removerPergunta(Pergunta pergunta) {
        
    }

}
