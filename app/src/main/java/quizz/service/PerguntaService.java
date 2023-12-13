package main.java.quizz.service;

import main.java.quizz.domain.Alternativa;
import main.java.quizz.repository.PerguntaRepository;

public class PerguntaService {
    private PerguntaRepository perguntaRepository;
    private Pergunta pergunta;

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public void criarPergunta(String enunciado, List<Alternativa> alternativa, int pontuacao) {
        
    }
}
