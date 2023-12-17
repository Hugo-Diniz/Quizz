package quizz;

import java.util.Scanner;
import quizz.commands.CommandExecutor;
import quizz.commands.CriarPerguntaCommand;
import quizz.commands.EditarPerguntaCommand;
import quizz.commands.HistoricoDePontuacaoCommand;
import quizz.commands.JogarCommand;
import quizz.commands.ListarPerguntasCommand;
import quizz.commands.RemoverPerguntaCommand;
import quizz.repository.FileDataService;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;

public class App {
    
    public static void main(String[] args) {
        PerguntaService service = new PerguntaService(PerguntaRepository.getInstance());
        PerguntaRepository dataService = PerguntaRepository.getInstance();
        dataService.setRepository(new FileDataService());
        CommandExecutor executor = new CommandExecutor();
        
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 7) {
            System.out.println("\n=================================");
            System.out.println("MENU QUIZZ");
            System.out.println("[1] - Jogar");
            System.out.println("[2] - Histórico de Pontuação");
            System.out.println("[3] - Criar pergunta");
            System.out.println("[4] - Editar pergunta");
            System.out.println("[5] - Listar perguntas");
            System.out.println("[6] - Excluir pergunta");
            System.out.println("[7] - Sair");

            System.out.print("Digite a opção -> ");
            opcao = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (opcao) {
                case 1 -> executor.executeCommand(new JogarCommand());
                case 2 -> executor.executeCommand(new HistoricoDePontuacaoCommand());
                case 3 -> executor.executeCommand(new CriarPerguntaCommand());
                case 4 -> executor.executeCommand(new EditarPerguntaCommand());
                case 5 -> executor.executeCommand(new ListarPerguntasCommand());
                case 6 -> executor.executeCommand(new RemoverPerguntaCommand());
                case 7 -> System.out.println("Tchau!");
                default -> System.out.println("Opção inválida");
            }
        }
        sc.close();
    }
}
