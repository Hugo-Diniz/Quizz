package quizz.commands;

import java.util.List;
import java.util.Scanner;
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

        System.out.println("=======================");
        System.out.println("Editar Pergunta:");
        
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String termo = strValidationContext.getValidValue("Digite parte do enunciado para buscar: ", "O enunciado não pode ser vazio.", String.class);
        
        List<Pergunta> listaDePerguntas = perguntaService.buscarPergunta(termo);
        
        if (!listaDePerguntas.isEmpty()) {
            for (Pergunta pergunta : listaDePerguntas) {
                int i = 0;
                System.out.printf("%d. %s\n", i+1, pergunta.getEnunciado());
                i++;
            }
            ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndiceValidator(1, listaDePerguntas.size()));
            int indice = intValidationContext.getValidValue("Digite o índice da pergunta para editar: ", "Digite um valor válido!", Integer.class);
            
            Pergunta perguntaSelecionada = listaDePerguntas.get(indice - 1);
            
            System.out.print("Digite um novo enunciado (ou deixe vazio para não alterar): ");
            String novoEnunciado = new Scanner(System.in).nextLine();
            // perguntaSelecionada.getAlternativas().size()-1 é o correto, aparentemente
            for (int i = 0; i < (perguntaSelecionada.getAlternativas().size()-1); i++) {
                System.out.println(perguntaSelecionada.getAlternativas().size());
                System.out.println(perguntaSelecionada.getAlternativas().get(i).getComando());
            }
            System.out.println("[1] Editar as alternativas\n[2] Editar a alternativa correta");
            String opcao = new Scanner(System.in).nextLine();
            
            switch(opcao) {
                case "1" -> {
                    for (int i = 0; i < perguntaSelecionada.getAlternativas().size(); i++) {
                        System.out.printf("Edite a alternativa '%s' (ou deixe vazio para não alterar): ", perguntaSelecionada.getAlternativas().get(i).getComando());
                        String novoComando = new Scanner(System.in).nextLine();
                        if (novoComando != "") {
                            perguntaSelecionada.getAlternativas().get(i).setComando(novoComando);
                        }
                    }
                    break;
                }
                case "2" -> {
                    for (int i = 0; i < perguntaSelecionada.getAlternativas().size(); i++) {
                        if (perguntaSelecionada.getAlternativas().get(i).isCorreta()) {
                            System.out.printf("A atual alternativa correta é:\n %s\n",perguntaSelecionada.getAlternativas().get(i));
                            
                            intValidationContext.setValidator(new IndiceValidator(perguntaSelecionada.getAlternativas().size(),1));
                            int indiceAlternativa = intValidationContext.getValidValue("Selecione a pergunta: ", "Digite um valor válido!", Integer.class);
                            
                            if (indiceAlternativa != Integer.valueOf("")) {
                                perguntaSelecionada.getAlternativas().get(i).setCorreta(false);
                                perguntaSelecionada.getAlternativas().get(indiceAlternativa).setCorreta(true);
                            }
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
            int novaPontuacao = new Scanner(System.in).nextInt();
            
            if (novoEnunciado != "") {
                perguntaSelecionada.setEnunciado(novoEnunciado);
            }
            
            if (novaPontuacao != Integer.valueOf("")) {
                perguntaSelecionada.setPontuacao(novaPontuacao);
            }
            
            perguntaService.editarPergunta(perguntaSelecionada.getId(), perguntaSelecionada.getEnunciado(), perguntaSelecionada.getAlternativas(), perguntaSelecionada.getPontuacao());
            System.out.println("Pergunta editada com sucesso!");
        } else {
            System.out.println("Nenhuma pergunta com este termo!");
        }
        
    }
    
}
