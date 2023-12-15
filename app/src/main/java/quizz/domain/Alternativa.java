package quizz.domain;

public class Alternativa {
    private String comando;
    private boolean correta;
    
    public Alternativa(String comando, boolean correta) {
        this.comando = comando;
        this.correta = correta;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }
}
