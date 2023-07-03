import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ArquivoSinistro implements I_Arquivo {

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        new File("src/arquivos/sinistros.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/sinistros.csv"));
        try {
            writer.write("ID,DATA,ENDERECO,CONDUTOR,SEGURO\n");
            for (Object obj : lista) {
                Sinistro sinistro = (Sinistro) obj;
                writer.write(sinistro.getId() + "," + sinistro.getData() + "," + sinistro.getEndereco() + "," + sinistro.getCondutor().getCpf() + "," + sinistro.getSeguro().getId() + "\n");
            }
        }
        finally {
            writer.close();
        }
        return true;
    }

    @Override
    public List<Object> lerArquivo() {
        return new ArrayList<>();
    }
    
}
