package quizz.repository;

import java.util.List;
import java.util.UUID;

import quizz.domain.Alternativa;
import quizz.domain.Pergunta;

public class PerguntaRepository {
    private DataService dataService;
    private static PerguntaRepository instance;

    private PerguntaRepository(DataService dataService) {
        this.dataService = dataService;
    }

    public static PerguntaRepository getInstance() {
        if (instance == null) {
            instance = new PerguntaRepository(new InMemoryDataService());
        }

        return instance;
    }

    public void setRepository(DataService dataService) {
        this.dataService = dataService;
    }

    public void criarNovaPergunta(Pergunta p) {
        dataService.criarPergunta(p);
    }

    // REVISAR O METODO DE CRIAR ALTERNATIVA!
    public void criarNovaAlternativa(UUID id, Alternativa alternativa) {
        dataService.criarAlternativas(id, alternativa);
    }

    public void editarPergunta(Pergunta p) {
        dataService.editarPergunta(p);
    }

    public void removerPergunta(Pergunta p) {
        dataService.removerPergunta(p);
    }

    public Pergunta getPergunta(UUID id) {
        return dataService.getPergunta(id);
    }

    public List<Pergunta> listaDePerguntas() {
        return dataService.listaPerguntas();
    }
}
