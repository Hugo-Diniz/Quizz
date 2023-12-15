package quizz.commands;

import java.util.List;

import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;

// COMANDO SUSCETÍVEL EM SER DELETADO!
public class ListarPerguntasCommand implements Command {

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("=======================");
        System.out.println("LISTA DE PERGUNTAS:");
        
        List<Pergunta> listaPerguntas = perguntaService.listaDePerguntas();
        for (Pergunta p: listaPerguntas) {
            int indice = listaPerguntas.indexOf(p);
            System.out.printf("%d - %s;%n", indice + 1, perguntaService.listaDePerguntas().get(indice).getEnunciado());
        }

        System.out.println("=======================");
    }
    
}
