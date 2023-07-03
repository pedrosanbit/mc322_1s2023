import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoVeiculo implements I_Arquivo {

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/veiculos.csv"));
        try {
            writer.write("PLACA,MARCA,MODELO,ANO_FAB\n");
            for (Object obj : lista) {
                Veiculo veiculo = (Veiculo) obj;
                writer.write(veiculo.getPlaca() + "," + veiculo.getMarca() + "," + veiculo.getModelo() + "," + veiculo.getAnoFabricacao() + "\n");
            }
        }
        finally {
            writer.close();
        }
        return true;
    }

    @Override
    public List<Object> lerArquivo() throws Exception {
        File arquivo = new File("src/arquivos/veiculos.csv");
        Scanner scanner = new Scanner(arquivo);
        scanner.useDelimiter(",|\\n");
        scanner.nextLine();
        List<Object> veiculos = new ArrayList<>();
        while (scanner.hasNext()) {
            Veiculo veiculo = new Veiculo(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
            veiculos.add(veiculo);
        }
        scanner.close();
        return veiculos;
    }
    
}
