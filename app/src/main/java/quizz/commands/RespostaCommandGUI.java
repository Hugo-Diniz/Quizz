package quizz.commands;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import quizz.gui.JogoQuizzWindow;

public class RespostaCommandGUI implements Command {
    private JButton botaoResposta;
    private Pergunta perguntaRespondida;
    private JogoQuizzWindow frame;

    public RespostaCommandGUI(JButton botaoResposta, Pergunta perguntaRespondida, JogoQuizzWindow frame) {
        this.botaoResposta = botaoResposta;
        this.perguntaRespondida = perguntaRespondida;
        this.frame = frame;
    }
    
    @Override
    public void execute() {
        boolean respostaCorreta = false;
        
        for (Alternativa alternativa : perguntaRespondida.getAlternativas()) {
            if (alternativa.isCorreta()) {
                if (botaoResposta.getText().substring(3).equals(alternativa.getComando())) {
                    respostaCorreta = true;
                    break;
                }
            }
        }
        
        if (respostaCorreta) {
            JOptionPane.showMessageDialog(botaoResposta.getParent(), "Resposta correta!");
            frame.pontuacao += perguntaRespondida.getPontuacao();
        } else {
            JOptionPane.showMessageDialog(botaoResposta.getParent(), "Resposta errada!");
        }
        frame.setField();
    }
}
