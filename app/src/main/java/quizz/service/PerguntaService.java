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

    public void criarPergunta(String enunciado, int pontuacao) {
        repository.criarNovaPergunta(new Pergunta(enunciado, pontuacao));
    }

    public void criarAlternativa(UUID id, String comando, boolean correta) {
        repository.criarNovaAlternativa(id, new Alternativa(comando, correta));
    }

    public void editarPergunta(Pergunta p) {
        repository.editarPergunta(p);
    }

    public void removerPergunta(Pergunta p) {
        repository.removerPergunta(p);
    }

    public Pergunta getPergunta(UUID id) {
        return repository.getPergunta(id);
    }

    public List<Pergunta> listaDePerguntas() {
        return repository.listaDePerguntas();
    }
}
