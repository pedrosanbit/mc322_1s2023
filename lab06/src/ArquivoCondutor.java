import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoCondutor implements I_Arquivo {

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/condutores.csv"));
        try {
            writer.write("CPF_CONDUTOR,NOME_CONDUTOR,TELEFONE,ENDERECO,EMAIL,DATA_NASCIMENTO\n");
            for (Object obj : lista) {
                Condutor condutor = (Condutor) obj;
                writer.write(condutor.getCpf() + "," + condutor.getNome() + "," + condutor.getTelefone() + "," + condutor.getEndereco() + "," + condutor.getDataNasc() + "\n");
            }
        }
        finally {
            writer.close();
        }
        return true;
    }

    @Override
    public List<Object> lerArquivo() throws Exception {
        File arquivo = new File("src/arquivos/condutores.csv");
        Scanner scanner = new Scanner(arquivo);
        scanner.useDelimiter(",|\\n");
        scanner.nextLine();
        List<Object> condutores = new ArrayList<>();
        while (scanner.hasNext()) {
            Condutor condutor = new Condutor(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
            condutores.add(condutor);
        }
        scanner.close();
        return condutores;
    }
    
}
