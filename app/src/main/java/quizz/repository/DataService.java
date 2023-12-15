package quizz.repository;

import quizz.domain.Pergunta;
import java.util.UUID;
import java.util.List;

public interface DataService {
    void criarPergunta(Pergunta pergunta);
    void editarPergunta(Pergunta pergunta);
    void removerPergunta(Pergunta pergunta);
    List<Pergunta> listaPerguntas();
    List<Pergunta> buscarPergunta(String termo);

    Pergunta getPergunta(UUID id);
} 
    
