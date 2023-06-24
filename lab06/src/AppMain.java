import java.util.ArrayList;
import java.util.List;

public class AppMain {
    public static void main(String[] args) {
        Veiculo veiculo1 = new Veiculo("DYW5575", "AM Gen", "Hummer Open-Top 6.5 4x4 Diesel TB", 1998);
        Veiculo veiculo2 = new Veiculo("FOX5305", "Ferrari", "456 GTA", 1997);

        ClientePF clientePF = new ClientePF("Analu Sueli Bernardes", "555555555", "Rua Praia da Penha, 284 - Ponta Grosa, PR", "omail@email.com", "324.469.042-46", "Feminino", "Ensino médio completo", "22/01/1998");
        ClientePJ clientePJ = new ClientePJ("Stefany e Larissa Corretores Associados Ltda", "9999999999", "Rua Particular, 731 - Jundiaí, SP", "email@email.com", "79.732.528/0001-05", "21/02/2018", 22);
        clientePF.cadastrarVeiculo(veiculo1);
        clientePJ.cadastrarFrota(veiculo2);

        Seguradora seguradora = new Seguradora("14.607.268/0001-09", "Caroline e Lucas Seguros Ltda", "(19) 3646-1282", "Rua Berto Piccolo, 765 - Campinas, SP", "contato@carolineelucassegurosltda.com.br");
        seguradora.gerarSeguro("11/11/1991", "02/02/2032", 100, veiculo1, clientePF);
        seguradora.gerarSeguro("11/11/1991", "02/02/2032", 200, clientePJ.getListaFrota().get(0), clientePJ);

        seguradora.cadastrarCliente(clientePF);
        seguradora.cadastrarCliente(clientePJ);

        Condutor condutor1 = new Condutor("560.200.050-03", "Condutor Um", "888888888", "umemail@email.com", "02/02/1982");
        Condutor condutor2 = new Condutor("322.159.570-00", "Condutor Dois", "777777777", "doisemail@email.com", "01/01/1971");

        seguradora.getListaSeguros().get(0).autorizarCondutor(condutor1);
        seguradora.getListaSeguros().get(1).autorizarCondutor(condutor2);

        seguradora.getListaSeguros().get(0).gerarSinistro("02/01/2004", "Rua Pongaí, 528 - Ferraz de Vasconcelos - SP", condutor1.getCpf());
        seguradora.getListaSeguros().get(0).gerarSinistro("08/12/17", "Rua João Maciel Baião, 793 - São Paulo, SP", condutor2.getCpf());

        seguradora.calcularReceita();
        System.out.println();

        List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        listaSeguradoras.add(seguradora);
        
        MenuOperacoes.menuOperacoes(listaSeguradoras);
    }
}
