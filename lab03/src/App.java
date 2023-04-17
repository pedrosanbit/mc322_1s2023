import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============== CADASTRO DE CLIENTES ==============");
        System.out.println();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        while (!ClientePF.validarCPF(cpf)) {
            System.out.println("CPF inválido. Por favor insira um CPF válido.");
            System.out.print("CPF: ");
            cpf = scanner.nextLine();
        }
        System.out.print("Nome: ");
        String nomePF = scanner.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoPF = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        System.out.print("Nível de escolaridade: ");
        String educacao = scanner.nextLine();
        System.out.print("Classe econômica: ");
        String classeEconomica = scanner.nextLine();
        System.out.print("Data da Licença: ");
        String dataLicenca = scanner.nextLine();

        System.out.println();

        ClientePF clientePF = new ClientePF(nomePF, enderecoPF, cpf, dataLicenca, educacao, genero, classeEconomica, dataNascimento);

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        while (!ClientePJ.validarCNPJ(cnpj)) {
            System.out.println("CNPJ inválido. Por favor insira um CNPJ válido.");
            System.out.print("CNPJ: ");
            cnpj = scanner.nextLine();
        }
        System.out.print("Nome: ");
        String nomePJ = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoPJ = scanner.nextLine();
        System.out.print("Data de Fundação: ");
        String dataFundacao = scanner.nextLine();

        ClientePJ clientePJ = new ClientePJ(nomePJ, enderecoPJ, cnpj, dataFundacao);

        System.out.println();
        System.out.println("=============== ADIÇÃO DE VEÍCULOS ==============");
        System.out.println();

        System.out.println("#Veículo do primeiro cliente");
        System.out.print("Placa: ");
        String placa1 = scanner.nextLine();
        System.out.print("Marca: ");
        String marca1 = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo1 = scanner.nextLine();
        System.out.print("Ano de Fabricação: ");
        int anoFabricacao1;
        try {
            anoFabricacao1 = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            anoFabricacao1 = 0;
        }
        Veiculo veiculo1 = new Veiculo(placa1, marca1, modelo1, anoFabricacao1);
        clientePF.adicionarVeiculo(veiculo1);
        System.out.println();

        System.out.println("#Veículo do segundo cliente");
        System.out.print("Placa: ");
        String placa2 = scanner.nextLine();
        System.out.print("Marca: ");
        String marca2 = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo2 = scanner.nextLine();
        System.out.print("Ano de Fabricação: ");
        int anoFabricacao2;
        try {
            anoFabricacao2 = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            anoFabricacao2 = 0;
        }
        Veiculo veiculo2 = new Veiculo(placa2, marca2, modelo2, anoFabricacao2);
        clientePJ.adicionarVeiculo(veiculo2);
        System.out.println();

        System.out.println("=============== SEGURADORA ==============");
        System.out.println();

        System.out.print("Nome: ");
        String nomeSeguradora = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoSeguradora = scanner.nextLine();
        Seguradora seguradora = new Seguradora(nomeSeguradora, telefone, email, enderecoSeguradora);
        System.out.println();

        System.out.println("#Cadastrando clientes na seguradora...");
        System.out.println(seguradora.cadastrarCliente(clientePF) ? "Cadastrado com sucesso." : "Falha ao cadastrar cliente.");
        System.out.println(seguradora.cadastrarCliente(clientePJ) ? "Cadastrado com sucesso." : "Falha ao cadastrar cliente.");
        System.out.println();

        System.out.println("#Gere um sinistro para a pessoa física cadastrada:");
        System.out.print("Data: ");
        String data = scanner.nextLine();
        System.out.print("Endereco: ");
        String enderecoSinistro = scanner.nextLine();
        seguradora.gerarSinistro(data, enderecoSinistro, clientePF.getListaVeiculos().get(0), clientePF.getNome());

        System.out.println();
        System.out.println("=============== EXIBIÇÃO DOS DADOS ==============");
        System.out.println();

        System.out.println("#Cliente pessoa física");
        System.out.println(clientePF);
        System.out.println();

        System.out.println("#Cliente pessoa jurídica");
        System.out.println(clientePJ);
        System.out.println();

        System.out.println("#Veículo pessoa física");
        System.out.println(veiculo1);
        System.out.println();

        System.out.println("#Veículo pessoa jurídica");
        System.out.println(veiculo2);
        System.out.println();

        System.out.println("#Seguradora");
        System.out.println(seguradora);
        System.out.println();

        System.out.println("#Sinistro");
        System.out.println(seguradora.getListaSinistros().get(0));
        System.out.println();

        System.out.println("#Listando clientes do tipo pessoa física");
        for (Cliente cliente: seguradora.listarClientes("PF")) System.out.println(cliente);
        System.out.println();

        System.out.println("#Listando clientes do tipo pessoa jurídica");
        for (Cliente cliente: seguradora.listarClientes("PJ")) System.out.println(cliente);
        System.out.println();

        System.out.println("#Visualizando sinistro associado à pessoa física cadastrada...");
        if(!seguradora.visualizarSinistro(clientePF.getCpf())) System.out.println("Sinistro associado ao cliente não pôde ser encontrado.");
        System.out.println();

        System.out.println("#Listando os sinistros gerados na seguradora");
        seguradora.listarSinistros();
        System.out.println();

        System.out.println("#Removendo o cliente do tipo pessoa jurídica cadastrado...");
        System.out.println(seguradora.removerCliente(clientePJ.getCnpj()) ? "Cliente removido com sucesso." : "Falha ao remover cliente.");
        System.out.println();

        System.out.println("#Listando clientes do tipo pessoa jurídica após remoção");
        for (Cliente cliente: seguradora.listarClientes("PJ")) System.out.println(cliente);
        System.out.println();

        System.out.println("=============== FIM =============");

        scanner.close();
    }
}
