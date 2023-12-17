package quizz.commands;

import java.util.List;

import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;

public class HistoricoDePontuacaoCommand implements Command {

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("=============== [HISTÓRICO DE PONTUAÇÃO] ===============");
        List<Integer> historicoPontuacao = perguntaService.getHistorico();
        if (!historicoPontuacao.isEmpty()) {
            for (Integer pontos: historicoPontuacao) {
                System.out.printf("%dº - %d;%n", historicoPontuacao.indexOf(pontos) + 1, pontos);
            }
        }else {
            System.out.println("Você ainda não realizou nenhuma jogada.");
        }
        System.out.println("===========================================================");
    }
    
}
