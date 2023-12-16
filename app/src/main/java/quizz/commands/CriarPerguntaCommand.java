package quizz.commands;

import java.util.ArrayList;
import java.util.List;
import quizz.domain.Alternativa;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.IndiceValidator;
import quizz.validator.NonEmptyValidator;
import quizz.validator.PontuacaoValidator;
import quizz.validator.ValidationContext;

public class CriarPerguntaCommand implements Command {

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("Criar Pergunta:");
        
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String enunciado = strValidationContext.getValidValue("Enunciado: ", "O enunciado não pode ser vazio.", String.class);
        
        List<Alternativa> listaDeAlternativas = new ArrayList<>();
        List<String> comandosDeAlternativas = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            String comando = strValidationContext.getValidValue(String.format("Comando da alternativa %d: ", i+1), "A alternativa não pode ser vazia.", String.class);
            comandosDeAlternativas.add(comando);
        }
        
        for (int i = 0; i < comandosDeAlternativas.size(); i++) {
            System.out.printf("%d. %s\n", i+1, comandosDeAlternativas.get(i));
        }
        
        ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndiceValidator(comandosDeAlternativas.size(),1));
        int alternativaCorreta = intValidationContext.getValidValue("Informe o índice da alternativa correta: ", String.format("Digite um valor entre 1 e %d", comandosDeAlternativas.size()), Integer.class);
        
        for (int indice = 0; indice < comandosDeAlternativas.size(); indice++) {
            if (indice == alternativaCorreta - 1) {
                Alternativa alternativa = new Alternativa(comandosDeAlternativas.get(indice), true);
                listaDeAlternativas.add(alternativa);
            } else {
                Alternativa alternativa = new Alternativa(comandosDeAlternativas.get(indice), false);
                listaDeAlternativas.add(alternativa);
            }
        }
        
        intValidationContext.setValidator(new PontuacaoValidator());
        Integer pontuacao = intValidationContext.getValidValue("Pontuação =  ", "Digite uma pontuação", Integer.class);
        
        perguntaService.criarPergunta(enunciado, listaDeAlternativas, pontuacao);
        System.out.println("Pergunta criada com sucesso!");
        System.out.println("\n=================================");
    }
    
}
