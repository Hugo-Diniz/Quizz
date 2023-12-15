package quizz.service;

import java.util.List;
import java.util.UUID;

import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;

public class PerguntaService {
    private final PerguntaRepository repository;

    public PerguntaService(PerguntaRepository repository) {
        this.repository = repository;
    }

    public void criarPergunta(String enunciado, List<Alternativa> alternativas, int pontuacao) {
        repository.criarNovaPergunta(new Pergunta(enunciado, alternativas, pontuacao));
    }

    public void editarPergunta(UUID id, String enunciado, List<Alternativa> alternativas, int pontuacao) {
        repository.editarPergunta(new Pergunta(getPergunta(id).getId(), enunciado, alternativas, pontuacao));
    }

    public void removerPergunta(Pergunta p) {
        repository.removerPergunta(p);
    }

    public List<Pergunta> listaDePerguntas() {
        return repository.listaDePerguntas();
    }
    
    public List<Pergunta> buscarPergunta(String termo) {
        return repository.buscarPergunta(termo);
    }
    
    public Pergunta getPergunta(UUID id) {
        return repository.getPergunta(id);
    }

}
