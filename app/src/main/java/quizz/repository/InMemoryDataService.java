package quizz.repository;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import quizz.domain.Pergunta;

public class InMemoryDataService implements DataService {
    protected List<Pergunta> listaDePerguntas = new ArrayList<>();
    protected List<Integer> historico = new ArrayList<>();

    @Override
    public void criarPergunta(Pergunta pergunta) {
        listaDePerguntas.add(pergunta);
    }

    @Override
    public void editarPergunta(Pergunta pergunta) {
        int indiceDaTarefa = listaPerguntas().indexOf(pergunta);
        listaPerguntas().set(indiceDaTarefa, pergunta);
    }

    @Override
    public void removerPergunta(Pergunta pergunta) {
        listaPerguntas().remove(pergunta);
    }
    
    @Override
    public List<Pergunta> listaPerguntas() {
        return listaDePerguntas;
    }

    @Override
    public List<Pergunta> buscarPergunta(String termo) {
        return listaPerguntas().stream().filter(pergunta -> 
                pergunta.getEnunciado().toLowerCase().contains(termo.toLowerCase())).toList();
    }
    
    @Override
    public Pergunta getPergunta(UUID id) {
        Optional<Pergunta> pergunta = listaPerguntas().stream().filter(p -> 
                p.getId().equals(id)).findFirst();
        return pergunta.isPresent() ? pergunta.get() : null;
    }

    @Override
    public void adcionarPontuacao(int ponto) {
        historico.add(ponto);
    }

    @Override
    public List<Integer> getHistorico() {
       return historico;
    }

}