package quizz.repository;

import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import java.util.UUID;
import java.util.List;

public interface DataService {
    void criarPergunta(Pergunta pergunta);
    void editarPergunta(Pergunta pergunta);
    void criarAlternativas(UUID id, Alternativa alternativa);
    void removerPergunta(Pergunta pergunta);

    public Pergunta getPergunta(UUID id);
    public List<Pergunta> listaPerguntas();
    
} 
    
