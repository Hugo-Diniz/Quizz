package main.java.quizz.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import main.java.quizz.domain.Alternativa;
import main.java.quizz.domain.Pergunta;

public class PerguntaRepository implements DataService {
    protected List<Pergunta> listaDePerguntas = new ArrayList<>();

    @Override
    public void criarAlternativas(UUID id, Alternativa alternativa) {
        getPerguntaAtual(id).getAlternativas().add(alternativa);
        
    }

    @Override
    public void criarPergunta(Pergunta pergunta) {
        listaDePerguntas.add(pergunta);
        
    }

    @Override
    public void editarPergunta(Pergunta pergunta) {
        int indiceDaTarefa = getPergunta().indexOf(pergunta);
        getPergunta().set(indiceDaTarefa, pergunta);
        
    }

    @Override
    public List<Pergunta> getPergunta() {
        return listaDePerguntas;
    }

    @Override
    public Pergunta getPerguntaAtual(UUID id) {
        Optional Pergunta = getPergunta().stream().filter(p -> p.getId().equals(id)).findFirst();
        return Pergunta.isPresent() ? Pergunta.get() : null;
    }

    @Override
    public void removerPergunta(Pergunta pergunta) {
        getPergunta().remove(pergunta);
        
    }

}