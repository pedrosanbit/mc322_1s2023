import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoClientePJ implements I_Arquivo {

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/clientesPJ.csv"));
        try {
            writer.write("CNPJ_CLIENTE,NOME_CLIENTE,TELEFONE,ENDERECO,EMAIL_CLIENTE,DATA_FUND,CODE_FROTA\n");
            for (Object obj : lista) {
                ClientePJ cliente = (ClientePJ) obj;
                writer.write(cliente.getCnpj() + "," + cliente.getNome() + "," + cliente.getTelefone() + "," + cliente.getEndereco() + "," + cliente.getEmail() + "," + cliente.getDataFundacao() + "," + cliente.getListaFrota().get(0).getCode() + "\n");
            }
        }
        finally {
            writer.close();
        }
        return true;
    }

    @Override
    public List<Object> lerArquivo() throws Exception {
        File arquivo = new File("src/arquivos/clientesPJ.csv");
        Scanner scanner = new Scanner(arquivo);
        scanner.useDelimiter(",|\\n");
        scanner.nextLine();
        List<Object> clientes = new ArrayList<>();
        while (scanner.hasNext()) {
            ClientePJ cliente = new ClientePJ(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), 0);
            String code = scanner.next();
            Frota frota = new Frota();
            frota.setCode(code);
            cliente.getListaFrota().add(frota);
            clientes.add(cliente);
        }
        scanner.close();
        return clientes;
    }
    
}
