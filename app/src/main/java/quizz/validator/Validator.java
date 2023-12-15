package quizz.validator;

public interface Validator<T> {
    boolean validate(T data);
}
