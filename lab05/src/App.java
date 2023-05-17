import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Veiculo veiculo1 = new Veiculo("DYW5575", "AM Gen", "Hummer Open-Top 6.5 4x4 Diesel TB", 1998);
        Veiculo veiculo2 = new Veiculo("FOX5305", "Ferrari", "456 GTA", 1997);

        ClientePF clientePF = new ClientePF("Analu Sueli Bernardes", "Rua Praia da Penha, 284 - Ponta Grosa, PR", "324.469.042-46", "03/11/2005", "Ensio médio completo", "Feminino", "C", "22/01/1998");
        ClientePJ clientePJ = new ClientePJ("Stefany e Larissa Corretores Associados Ltda", "Rua Particular, 731 - Jundiaí, SP", "79.732.528/0001-05", "21/02/2018", 22);

        Seguradora seguradora = new Seguradora("Caroline e Lucas Seguros Ltda", "(19) 3646-1282", "contato@carolineelucassegurosltda.com.br", "Rua Berto Piccolo, 765 - Campinas, SP");

        clientePF.adicionarVeiculo(veiculo1);
        clientePJ.adicionarVeiculo(veiculo2);

        seguradora.cadastrarCliente(clientePF);
        seguradora.cadastrarCliente(clientePJ);

        seguradora.gerarSinistro("02/01/2004", "Rua Pongaí, 528 - Ferraz de Vasconcelos - SP", veiculo1, clientePF.getCpf());
        seguradora.gerarSinistro("08/12/17", "Rua João Maciel Baião, 793 - São Paulo, SP", veiculo2, clientePJ.getCnpj());

        System.out.println(seguradora.listarClientes("PF"));
        System.out.println();
        System.out.println(seguradora.listarClientes("PJ"));
        System.out.println();
        seguradora.visualizarSinistro(clientePF.getCpf());
        System.out.println();
        seguradora.visualizarSinistro(clientePJ.getCnpj());
        System.out.println();
        seguradora.listarSinistros();
        System.out.println();
        seguradora.calcularPrecoSeguroCliente();
        seguradora.calcularReceita();
        System.out.println();

        List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        listaSeguradoras.add(seguradora);

        MenuOperacoes.menuOperacoes(listaSeguradoras);
    }
}
