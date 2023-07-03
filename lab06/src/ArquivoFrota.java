import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoFrota implements I_Arquivo{

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/frotas.csv"));
        try {
            writer.write("ID_FROTA,PLACA_VEICULO1,PLACA_VEICULO2,PLACA_VEICULO3\n");
            for (Object obj : lista) {
                Frota frota = (Frota) obj;
                writer.write(frota.getCode() + "," + frota.getListaVeiculos().get(0).getPlaca()+ "," + frota.getListaVeiculos().get(1).getPlaca() + "," + frota.getListaVeiculos().get(2).getPlaca() + "\n");
            }
        }
        finally {
            writer.close();
        }
        return true;
    }

    @Override
    public List<Object> lerArquivo() throws Exception {
        File arquivo = new File("src/arquivos/frotas.csv");
        Scanner scanner = new Scanner(arquivo);
        scanner.useDelimiter(",|\\n");
        scanner.nextLine();
        List<Object> frotas = new ArrayList<>();
        while (scanner.hasNext()) {
            String id = scanner.next();
            Frota frota = new Frota();
            frota.setCode(id);
            Veiculo veiculo1 = new Veiculo(scanner.next(), "", "", 1970);
            Veiculo veiculo2 = new Veiculo(scanner.next(), "", "", 1970);
            Veiculo veiculo3 = new Veiculo(scanner.next(), "", "", 1970);
            frota.getListaVeiculos().add(veiculo1);
            frota.getListaVeiculos().add(veiculo2);
            frota.getListaVeiculos().add(veiculo3);
            frotas.add(frota);
        }
        scanner.close();
        return frotas;
    }
    
}
