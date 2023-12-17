package quizz.commands;

import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.IndiceValidator;
import quizz.validator.ValidationContext;
import java.util.Collections;

public class JogarCommand implements Command {
   
    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("QUIZZ - JOGO");
        
        int pontuacao = 0;
        int pontuacao_total = 0;
        
        if (!perguntaService.listaDePerguntas().isEmpty()) {
            Collections.shuffle(perguntaService.listaDePerguntas());
            for (Pergunta pergunta : perguntaService.listaDePerguntas()) {
                System.out.println(pergunta.getEnunciado());
                int i = 0;
                Collections.shuffle(pergunta.getAlternativas());
                for (Alternativa alternativa : pergunta.getAlternativas()) {
                    System.out.printf("%d. %s\n", i+1, alternativa.getComando());
                    i++;
                }

                ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndiceValidator(pergunta.getAlternativas().size(), 1));
                int resposta = intValidationContext.getValidValue("Digite o índice da alternativa (entre 1 e 4): ", "Digite um valor válido!", Integer.class);
                
                if (pergunta.getAlternativas().get(resposta - 1).isCorreta()) {
                    pontuacao += pergunta.getPontuacao(); // pontuação do jogador
                    System.out.println("Certa resposta!");
                } else {
                    System.out.println("Resposta incorreta!");
                }
                pontuacao_total += pergunta.getPontuacao(); // pontuação do jogo
            }
            System.out.printf("\nJogo concluído!\nRESULTADO FINAL: %d/%d", pontuacao, pontuacao_total);
            
        } else {
            System.out.println("Não há perguntas para jogar. Crie algumas perguntas primeiro.");
        }
    }
}
