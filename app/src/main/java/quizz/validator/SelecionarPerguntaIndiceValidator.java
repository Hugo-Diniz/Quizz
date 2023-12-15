package quizz.validator;

public class SelecionarPerguntaIndiceValidator implements Validator<Integer> {
    
    private int valorMax;
    private int valorMin;

    public SelecionarPerguntaIndiceValidator(int max, int min) {
        this.valorMax = max;
        this.valorMin = min;
    }


    @Override
    public boolean validate(Integer data) {
        return data >= this.valorMin && data <= this.valorMax;
    }
    
}
