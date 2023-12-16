package quizz.commands;

import java.util.List;

import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.IndiceValidator;
import quizz.validator.ValidationContext;

public class RemoverPerguntaCommand implements Command {

    @Override
    public void execute() {
       PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

       System.out.println("\n=================================");
       System.out.println("REMOVER PERGUNTA:");
       
       List<Pergunta> listaPerguntas = perguntaService.listaDePerguntas();
       
       if (!listaPerguntas.isEmpty()) {
            for (Pergunta p: listaPerguntas) {
               int index = listaPerguntas.indexOf(p);
               System.out.printf("%d - %s\n", index + 1, perguntaService.listaDePerguntas().get(index).getEnunciado());
            }
            
            ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndiceValidator(listaPerguntas.size(),1));
            int indice = intValidationContext.getValidValue("Selecione a pergunta: ", "Digite um valor válido!", Integer.class);
            
            Pergunta perguntaSelecionada = listaPerguntas.get(indice - 1);
            perguntaService.removerPergunta(perguntaSelecionada);
            System.out.println("Pergunta removida com sucesso!");
            System.out.println("\n=================================");
       } else {
           System.out.println("Nenhuma pergunta para remover!");
       }

    }
    
}
