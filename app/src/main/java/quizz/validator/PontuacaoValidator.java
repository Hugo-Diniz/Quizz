package quizz.validator;

public class PontuacaoValidator implements Validator<Integer> {

    @Override
    public boolean validate(Integer data) {
        return (data > 0);
    }
    
}
