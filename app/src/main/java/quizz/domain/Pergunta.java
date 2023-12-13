package main.java.quizz.domain;

import java.util.UUID;
import java.util.Objects;

public class Pergunta {
    private String enunciado;
    private List<Alternativa> alternativas;
    private int pontuacao;
    private UUID id;

    
    public Pergunta(String enunciado, List<Alternativa> alternativas, int pontuacao) {
        this.id = UUID.randomUUID();
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.pontuacao = pontuacao;
    }
    
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public int getPontuacao() {
        return pontuacao;
    }
    
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pergunta pergunta)) {
            return false;
        }

        return Objects.equals(getId(), pergunta.getId());
    }

    public UUID getId() {
        return id;
    }

}
