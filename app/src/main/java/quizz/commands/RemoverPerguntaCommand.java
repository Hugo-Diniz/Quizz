package quizz.commands;

import java.util.List;

import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.PontuacaoValidator;
import quizz.validator.SelecionarPerguntaIndiceValidator;
import quizz.validator.ValidationContext;

public class RemoverPerguntaCommand implements Command {

    @Override
    public void execute() {
       PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

       System.out.println("=======================");
       System.out.println("REMOVER PERGUNTA:");
       
       List<Pergunta> listaPerguntas = perguntaService.listaDePerguntas();
       
       if (!listaPerguntas.isEmpty()) {
           for (Pergunta p: listaPerguntas) {
               int index = listaPerguntas.indexOf(p);
               System.out.printf("%d - %s;%n", index + 1, perguntaService.listaDePerguntas().get(index).getEnunciado());
            }
            ValidationContext<Integer> intValidationContext = new ValidationContext<>(new SelecionarPerguntaIndiceValidator(1,listaPerguntas.size()));
            Integer indice = intValidationContext.getValidValue("Selecione a pergunta: ", "Digite um valor válido!", Integer.class);
            
            Pergunta perguntaSelecionada = listaPerguntas.get(indice - 1);
            perguntaService.removerPergunta(perguntaSelecionada);
            System.out.printf("A pergunta %d foi removida com sucesso!%n", indice);
            System.out.println("=======================");
       }

    }
    
}
