import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ArquivoSeguro implements I_Arquivo {

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        new File("src/arquivos/seguros.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/seguros.csv"));
        try {
            writer.write("ID,DATA_INICIO,DATA_FIM,SEGURADORA,LISTA_SINISTROS,LISTA_CONDUTORES,VALOR_MENSAL\n");
            for (Object obj : lista) {
                Seguro seguro = (Seguro) obj;
                writer.write(seguro.getId() + "," + seguro.getDataInicio() + "," + seguro.getDataFim() + "," + seguro.getSeguradora().getNome() + ",[");
                for (int i = 0; i < seguro.getListaSinistros().size(); i++) {
                    Sinistro sinistro = seguro.getListaSinistros().get(i);
                    writer.write("" + sinistro.getId());
                    if (i != seguro.getListaSinistros().size() - 1) writer.write(",");
                }
                writer.write("],[");
                for (int i = 0; i < seguro.getListaCondutores().size(); i++) {
                    Condutor condutor = seguro.getListaCondutores().get(i);
                    writer.write(condutor.getCpf());
                    if (i != seguro.getListaCondutores().size() - 1) writer.write(",");
                }
                writer.write("]," + seguro.getValorMensal() + "\n");
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
