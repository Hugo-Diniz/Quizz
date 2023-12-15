package quizz.commands;

import java.util.List;

import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;

public class ListarPerguntasCommand implements Command {

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("=======================");
        System.out.println("LISTA DE PERGUNTAS:");
        
        List<Pergunta> listaPerguntas = perguntaService.listaDePerguntas();
        if (!listaPerguntas.isEmpty()) {
            for (Pergunta p: listaPerguntas) {
                int indice = listaPerguntas.indexOf(p);
                System.out.printf("%d - %s;%n", indice + 1, perguntaService.listaDePerguntas().get(indice).getEnunciado());
            }
        } else {
            System.out.println("Nenhuma pergunta para listar!");
        }

        System.out.println("=======================");
    }
    
}
