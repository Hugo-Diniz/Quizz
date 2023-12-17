package quizz.commands;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import quizz.domain.Pergunta;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;

public class RemoverPerguntaCommandGUI implements Command {
    private final JTable tablePergunta;
    private final PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
    private final JFrame frame;

    public RemoverPerguntaCommandGUI(JTable table, JFrame frame) {
        this.tablePergunta = table;
        this.frame = frame;
    }

    @Override
    public void execute() {
        int indicePerguntaSelecionada = tablePergunta.getSelectedRow();
        
        if (indicePerguntaSelecionada == -1) {
            JOptionPane.showMessageDialog(tablePergunta.getParent(), "Selecione uma pergunta para remover!");
        } else {
            Pergunta perguntaSelecionada = perguntaService.listaDePerguntas().get(indicePerguntaSelecionada);
            perguntaService.removerPergunta(perguntaSelecionada);
            
            JOptionPane.showMessageDialog(tablePergunta.getParent(), "Pergunta removida com sucesso!");
            
            frame.setVisible(false);
        }
    }
}
