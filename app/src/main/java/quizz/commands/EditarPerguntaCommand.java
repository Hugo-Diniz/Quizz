package quizz.commands;

import java.util.List;
import java.util.Scanner;
import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.IndiceValidator;
import quizz.validator.NonEmptyValidator;
import quizz.validator.ValidationContext;

public class EditarPerguntaCommand implements Command {

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("Editar Pergunta:");
        
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String termo = strValidationContext.getValidValue("Digite parte do enunciado para buscar: ", "O enunciado não pode ser vazio.", String.class);
        
        List<Pergunta> listaDePerguntas = perguntaService.buscarPergunta(termo);
        
        if (!listaDePerguntas.isEmpty()) {
            int i = 0;
            for (Pergunta pergunta : listaDePerguntas) {
                System.out.printf("%d. %s\n", i+1, pergunta.getEnunciado());
                i++;
            }
            ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndiceValidator(1, listaDePerguntas.size()));
            int indice = intValidationContext.getValidValue("Digite o índice da pergunta para editar: ", "Digite um valor válido!", Integer.class);
            
            Pergunta perguntaSelecionada = listaDePerguntas.get(indice - 1);
            
            System.out.print("Digite um novo enunciado (ou deixe vazio para não alterar): ");
            String novoEnunciado = new Scanner(System.in).nextLine();
            
            System.out.println("\nAs alternativas da pergunta são: ");
            int indiceAlternativasDaPergunta = 0;
            for (Alternativa alternativa : perguntaSelecionada.getAlternativas()) {
                System.out.printf("%d. %s\n", indiceAlternativasDaPergunta+1,alternativa.getComando());
                indiceAlternativasDaPergunta++;
            }
            System.out.print("[1] Editar as alternativas\n[2] Editar a alternativa correta\nDigite a opção: ");
            String opcao = new Scanner(System.in).nextLine();
            
            switch(opcao) {
                case "1" -> {
                    for (int j = 0; j < perguntaSelecionada.getAlternativas().size(); j++) {
                        System.out.printf("Edite a alternativa '%s' (ou deixe vazio para não alterar): ", perguntaSelecionada.getAlternativas().get(j).getComando());
                        String novoComando = new Scanner(System.in).nextLine();
                        if (!"".equals(novoComando)) {
                            perguntaSelecionada.getAlternativas().get(j).setComando(novoComando);
                        }
                    }
                    break;
                }
                case "2" -> {
                    for (int k = 0; k < perguntaSelecionada.getAlternativas().size(); k++) {
                        if (perguntaSelecionada.getAlternativas().get(k).isCorreta()) {
                            System.out.printf("A atual alternativa correta é:\n%s\n",perguntaSelecionada.getAlternativas().get(k).getComando());
                            
                            intValidationContext.setValidator(new IndiceValidator(perguntaSelecionada.getAlternativas().size(),1));
                            int indiceAlternativa = intValidationContext.getValidValue("Digite o índice da nova alternativa correta (entre 1 e 4): ", "Digite um valor válido!", Integer.class);
                            
                            perguntaSelecionada.getAlternativas().get(k).setCorreta(false);
                            perguntaSelecionada.getAlternativas().get(indiceAlternativa - 1).setCorreta(true);
                            break;
                        }
                    }
                    break;
                }
                default -> {
                    System.out.println("Valor inválido!");
                    break;
                }
            }
            
            System.out.print("Digite uma nova pontuação para a pergunta (ou deixe vazio para não editar): ");
            String novaPontuacao = new Scanner(System.in).nextLine();
            
            if (!"".equals(novoEnunciado)) {
                perguntaSelecionada.setEnunciado(novoEnunciado);
            }
            
            if (!"".equals(novaPontuacao)) {
                perguntaSelecionada.setPontuacao(Integer.parseInt(novaPontuacao));
            }
            
            perguntaService.editarPergunta(perguntaSelecionada.getId(), perguntaSelecionada.getEnunciado(), perguntaSelecionada.getAlternativas(), perguntaSelecionada.getPontuacao());
            System.out.println("Pergunta editada com sucesso!");
        } else {
            System.out.println("Nenhuma pergunta com este termo!");
        }
        
    }
    
}
