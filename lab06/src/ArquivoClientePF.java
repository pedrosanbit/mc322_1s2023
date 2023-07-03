import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoClientePF implements I_Arquivo {

    @Override
    public boolean gravarArquivo(List<Object> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/arquivos/clientesPF.csv"));
        try {
            writer.write("CPF_CLIENTE,NOME_CLIENTE,TELEFONE_CLIENTE,ENDERECO_CLIENTE,EMAIL_CLIENTE,SEXO,ENSINO,DATA_NASCIMENTO,PLACA_VEICULO_CLIENTE1\n");
            for (Object obj : lista) {
                ClientePF cliente = (ClientePF) obj;
                writer.write(cliente.getCpf() + "," + cliente.getNome() + "," + cliente.getTelefone() + "," + cliente.getEndereco() + "," + cliente.getEmail() + "," + cliente.getGenero() + "," + cliente.getEducacao() + "," + cliente.getDataNascimento() + "," + cliente.getListaVeiculos().get(0).getPlaca() + "\n");
                writer.write("\n");
            }
        }
        finally {
            writer.close();
        }
        return true;
    }

    @Override
    public List<Object> lerArquivo() throws Exception {
        File arquivo = new File("src/arquivos/clientesPF.csv");
        Scanner scanner = new Scanner(arquivo);
        scanner.useDelimiter(",|\\n");
        scanner.nextLine();
        List<Object> clientes = new ArrayList<>();
        while (scanner.hasNext()) {
            ClientePF cliente = new ClientePF(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
            Veiculo veiculo = new Veiculo(scanner.next(), "", "", 1970);
            cliente.getListaVeiculos().add(veiculo);
            clientes.add(cliente);
        }
        scanner.close();
        return clientes;
    }

    
}
