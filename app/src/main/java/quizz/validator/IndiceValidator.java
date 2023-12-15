package quizz.validator;

public class IndiceValidator implements Validator<Integer> {
    
    private int valorMax;
    private int valorMin;

    public IndiceValidator(int max, int min) {
        this.valorMax = max;
        this.valorMin = min;
    }


    @Override
    public boolean validate(Integer data) {
        return data >= this.valorMin && data <= this.valorMax;
    }
    
}
