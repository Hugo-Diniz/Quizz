package quizz.repository;

import java.io.*;
import quizz.domain.Pergunta;
import java.util.List;

public class FileDataService extends InMemoryDataService {
    
    public FileDataService() {
        File data = new File("perguntas.bin");
        if (data.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("perguntas.bin"))) {
                listaDePerguntas = (List<Pergunta>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void criarPergunta(Pergunta pergunta) {
        super.criarPergunta(pergunta);
        write();
    }

    @Override
    public void editarPergunta(Pergunta pergunta) {
        super.editarPergunta(pergunta);
        write();
    }

    @Override
    public void removerPergunta(Pergunta pergunta) {
        super.removerPergunta(pergunta);
        write();
    }

    private void write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("perguntas.bin"))) {
            oos.writeObject(listaPerguntas());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
