package quizz.commands;

import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import quizz.validator.GUITextValidator;
import quizz.validator.NonEmptyValidator;
import quizz.validator.ValidationContext;

public class JogarCommandGUI implements Command {
    private JFrame frame;
    private JLabel enunciado;
    private JLabel numPergunta;
    private JButton botaoA;
    private JButton botaoB;
    private JButton botaoC;
    private JButton botaoD;
    private int pontuacao;

    public JogarCommandGUI(JFrame frame, JLabel enunciado, JLabel numPergunta, JButton botaoA, JButton botaoB, JButton botaoC, JButton botaoD) {
        this.frame = frame;
        this.enunciado = enunciado;
        this.numPergunta = numPergunta;
        this.botaoA = botaoA;
        this.botaoB = botaoB;
        this.botaoC = botaoC;
        this.botaoD = botaoD;
    }

    @Override
    public void execute() {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
        
        List<Pergunta> listaAtualPergunta = perguntaService.listaDePerguntas();
        Collections.shuffle(listaAtualPergunta);
        
        pontuacao = 0;

        for (int numQuestao = 0; numQuestao < listaAtualPergunta.size(); numQuestao++) {
            Pergunta perguntaAtual = listaAtualPergunta.get(numQuestao);

            String strEnunciado = perguntaAtual.getEnunciado();
            this.numPergunta.setText(String.valueOf(numQuestao + 1));
            this.enunciado.setText(strEnunciado);
            
            List<Alternativa> alternativasAtual = perguntaAtual.getAlternativas();
            Collections.shuffle(alternativasAtual);
            
            this.botaoA.setText("A) " + alternativasAtual.get(0).getComando());
            this.botaoB.setText("B) " + alternativasAtual.get(1).getComando());
            this.botaoC.setText("C) " + alternativasAtual.get(2).getComando());
            this.botaoD.setText("D) " + alternativasAtual.get(3).getComando());
            
            int indexRespostaCerta = alternativasAtual.indexOf(alternativasAtual.stream().filter(alternativa -> alternativa.isCorreta()).findFirst().orElse(null));
            
            String comandoRespostaCerta = alternativasAtual.get(indexRespostaCerta).getComando();
            
            adicionarActionListener(perguntaAtual, comandoRespostaCerta);
        }
    }

    private void adicionarActionListener(Pergunta perguntaAtual, String comandoRespostaCerta) {
        this.botaoA.addActionListener(e -> verificarResposta(perguntaAtual, comandoRespostaCerta, botaoA));
        this.botaoB.addActionListener(e -> verificarResposta(perguntaAtual, comandoRespostaCerta, botaoB));
        this.botaoC.addActionListener(e -> verificarResposta(perguntaAtual, comandoRespostaCerta, botaoC));
        this.botaoD.addActionListener(e -> verificarResposta(perguntaAtual, comandoRespostaCerta, botaoD));
    }

    private void verificarResposta(Pergunta perguntaAtual, String comandoRespostaCerta, JButton botao) {        
        if (botao.getText().substring(3).equals(comandoRespostaCerta)) {
            JOptionPane.showMessageDialog(enunciado.getParent(), "Resposta Certa!");
            pontuacao += perguntaAtual.getPontuacao();
        } else {
            JOptionPane.showMessageDialog(enunciado.getParent(), "Resposta Incorreta!");
        }
    }
}
