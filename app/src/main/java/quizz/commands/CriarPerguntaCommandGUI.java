package quizz.commands;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import quizz.domain.Alternativa;
import quizz.domain.Pergunta;
import quizz.gui.MenuWindow;
import quizz.repository.PerguntaRepository;
import quizz.service.PerguntaService;
import quizz.validator.GUITextValidator;
import quizz.validator.NonEmptyValidator;

public class CriarPerguntaCommandGUI implements Command {
    private final JTextField txtEnunciado;
    private final JTextField txtAlternativa1;
    private final JTextField txtAlternativa2;
    private final JTextField txtAlternativa3;
    private final JTextField txtAlternativa4;
    private final JSlider sliderPontuacao;
    private final JComboBox alternaticaCorreta;
    private final JFrame frame;

    public CriarPerguntaCommandGUI(JTextField enunciado, JTextField a1, JTextField a2, JTextField a3, JTextField a4, JSlider pontuacao, JComboBox correta, JFrame frame) {
        this.txtEnunciado = enunciado;
        this.txtAlternativa1 = a1;
        this.txtAlternativa2 = a2;
        this.txtAlternativa3 = a3;
        this.txtAlternativa4 = a4;
        this.sliderPontuacao = pontuacao;
        this.alternaticaCorreta = correta;
        this.frame = frame;
    }
            
    private final PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

    @Override
    public void execute() {
        String enunciado = this.txtEnunciado.getText();
        String alternativa1 = this.txtAlternativa1.getText();
        String alternativa2 = this.txtAlternativa2.getText();
        String alternativa3 = this.txtAlternativa3.getText();
        String alternativa4 = this.txtAlternativa4.getText();
        int valorPontuacao = this.sliderPontuacao.getValue();
        int valorSelecionado = this.alternaticaCorreta.getSelectedIndex();
        List<Alternativa> listaDeAlternativas = new ArrayList<>();
        List<String> ComandoDeAlternativas = new ArrayList<>();
        
        GUITextValidator enunciadoValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator alternativa1Validator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator alternativa2Validator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator alternativa3Validator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator alternativa4Validator = new GUITextValidator(new NonEmptyValidator());
        
        boolean enunciadoValido = enunciadoValidator.validate(txtEnunciado);
        boolean alternativa1Valido = alternativa1Validator.validate(txtAlternativa1);
        boolean alternativa2Valido = alternativa2Validator.validate(txtAlternativa2);
        boolean alternativa3Valido = alternativa3Validator.validate(txtAlternativa3);
        boolean alternativa4Valido = alternativa4Validator.validate(txtAlternativa4);
        
        if (enunciadoValido && alternativa1Valido && alternativa2Valido && alternativa3Valido && alternativa4Valido) {
            ComandoDeAlternativas.add(alternativa1);            
            ComandoDeAlternativas.add(alternativa2);            
            ComandoDeAlternativas.add(alternativa3);
            ComandoDeAlternativas.add(alternativa4);        
            for (int i=1; i <= 4; i++) {
                if (i == (valorSelecionado + 1)) {
                    Alternativa alternativa = new Alternativa(ComandoDeAlternativas.get(i-1), true);
                    listaDeAlternativas.add(alternativa);
                }else {
                    Alternativa alternativa = new Alternativa(ComandoDeAlternativas.get(i-1), false);
                    listaDeAlternativas.add(alternativa);

                }
            }
            perguntaService.criarPergunta(enunciado, listaDeAlternativas, valorPontuacao);
            JOptionPane.showMessageDialog(txtEnunciado.getParent(), "Pergunta Criada com sucesso!");
            frame.setVisible(false);
            MenuWindow menu = new MenuWindow();
            menu.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(txtEnunciado.getParent(), "Por favor preencha as informações corretamente.");
        }
    }
}
