package quizz.repository;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import quizz.domain.Alternativa;
import quizz.domain.Pergunta;

public class InMemoryDataService implements DataService {
    protected List<Pergunta> listaDePerguntas = new ArrayList<>();

    @Override
    public void criarAlternativas(UUID id, Alternativa alternativa) {
        getPergunta(id).getAlternativas().add(alternativa);
        
    }

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
    public List<Pergunta> listaPerguntas() {
        return listaDePerguntas;
    }

    @Override
    public Pergunta getPergunta(UUID id) {
        Optional<Pergunta> pergunta = listaPerguntas().stream().filter(p -> p.getId().equals(id)).findFirst();
        return pergunta.isPresent() ? pergunta.get() : null;
    }

    @Override
    public void removerPergunta(Pergunta pergunta) {
        listaPerguntas().remove(pergunta);
        
    }

}