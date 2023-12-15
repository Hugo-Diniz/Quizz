package quizz.commands;

import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.NonEmptyValidator;
import quizz.validator.PontuacaoValidator;
import quizz.validator.ValidationContext;

public class CriarPerguntaCommand implements Command {

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("=======================");
        System.out.println("Criar Pergunta:");
        
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String enunciado = strValidationContext.getValidValue("Enunciado: ", "O enunciado não pode ser vazio.", String.class);
        
        ValidationContext<Integer> intValidationContext = new ValidationContext<>(new PontuacaoValidator());
        Integer pontuacao = intValidationContext.getValidValue("Pontuação =  ", "Digite uma pontuação", Integer.class);
        
        perguntaService.criarPergunta(enunciado, pontuacao);
        System.out.println("Pergunta criada com sucesso!");
        System.out.println("=======================");
    }
    
}
