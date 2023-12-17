package quizz.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Alternativa implements Serializable {
    private final UUID idAlternativa; // Necessário para Serializar
    
    private String comando;
    private boolean correta;
    
    public Alternativa(String comando, boolean correta) {
        this.idAlternativa = UUID.randomUUID();
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

    public UUID getIdAlternativa() {
        return idAlternativa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAlternativa());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Alternativa alternativa)) {
            return false;
        }

        return Objects.equals(getIdAlternativa(), alternativa.getIdAlternativa());
    }
}
