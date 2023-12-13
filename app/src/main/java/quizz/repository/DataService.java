package main.java.quizz.repository;

import main.java.quizz.domain.Alternativa;
import main.java.quizz.domain.Pergunta;
import java.util.UUID;
import java.util.List;

public interface DataService {
    void criarPergunta(Pergunta pergunta);
    void editarPergunta(Pergunta pergunta);
    void criarAlternativas(UUID id, Alternativa alternativa);
    void removerPergunta(Pergunta pergunta);

    public Pergunta getPerguntaAtual(UUID id);
    public List<Pergunta> getPergunta();
    
} 
    
