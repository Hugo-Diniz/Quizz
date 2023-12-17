package quizz.validator;

import javax.swing.text.JTextComponent;

public interface GUIValidator {
    Validator<String> getStringValidator();
    boolean validate(JTextComponent field);
}
