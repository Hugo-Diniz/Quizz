package quizz;

import java.util.Scanner;
import quizz.commands.CommandExecutor;
import quizz.commands.CriarPerguntaCommand;
import quizz.commands.EditarPerguntaCommand;
import quizz.commands.ListarPerguntasCommand;
import quizz.commands.RemoverPerguntaCommand;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;

public class App {
    
    public static void main(String[] args) {
        PerguntaService service = new PerguntaService(PerguntaRepository.getInstance());
        CommandExecutor executor = new CommandExecutor();
        
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 5) {
            System.out.println("\n=================================");
            System.out.println("MENU QUIZZ");
            System.out.println("[1] - Criar pergunta");
            System.out.println("[2] - Editar pergunta");
            System.out.println("[3] - Listar perguntas");
            System.out.println("[4] - Excluir pergunta");
            System.out.println("[5] - Sair");

            System.out.print("Digite a opção -> ");
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch (opcao) {
                case 1 -> executor.executeCommand(new CriarPerguntaCommand());
                case 2 -> executor.executeCommand(new EditarPerguntaCommand());
                case 3 -> executor.executeCommand(new ListarPerguntasCommand());
                case 4 -> executor.executeCommand(new RemoverPerguntaCommand());
                case 5 -> System.out.println("Tchau!");
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
