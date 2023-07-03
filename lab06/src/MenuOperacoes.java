import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public enum MenuOperacoes {
    CADASTRAR(1,0),
    CADASTRAR_CLIENTE_PF(1,1),
    CADASTRAR_CLIENTE_PJ(1,2),
    CADASTRAR_VEICULO_PF(1,3),
    CADASTRAR_VEICULO_FROTA(1,4),
    CADASTRAR_CONDUTOR(1,5),
    CADASTRAR_SEGURO(1,6),
    CADASTRAR_SEGURADORA(1,7),
    VOLTAR_CADASTRAR(1,8),
    LISTAR(2,0),
    LISTAR_CLIENTE_POR_SEGURADORA(2,1),
    LISTAR_SINISTROS_POR_SEGURADORA(2,2),
    LISTAR_SINISTROS_POR_CLIENTE(2,3),
    LISTAR_SINISTROS_POR_SEGURO(2,4),
    LISTAR_SINISTROS_POR_CONDUTOR(2,5),
    LISTAR_VEICULOS_POR_CLIENTE(2,6),
    LISTAR_FROTAS_POR_CLIENTE(2,7),
    LISTAR_VEICULOS_POR_SEGURADORA(2,8),
    LISTAR_CONDUTORES_POR_SEGURO(2,9),
    LISTAR_SEGUROS_POR_SEGURADORA(2,10),
    LISTAR_SEGUROS_POR_CLIENTE(2,11),
    VOLTAR_LISTAR(2,12),
    EXCLUIR(3,0),
    EXCLUIR_CLIENTE(3,1),
    EXCLUIR_VEICULO(3,2),
    EXCLUIR_SINISTRO(3,3),
    EXCLUIR_CONDUTOR(3,4),
    EXCLUIR_SEGURO(3,5),
    VOLTAR_EXCLUIR(3,6),
    GERAR_SINISTRO(4,0),
    TRANSFERIR_SEGURO(5,0),
    CALCULAR_RECEITA_SEGURADORA(6,0),
    SAIR(0,0),
    INICIO(7,0);

    private final int operacaoPrimaria;
    private final int operacaoSecundaria;

    MenuOperacoes(int operacaoPrimaria, int operacaoSecundaria) {
        this.operacaoPrimaria = operacaoPrimaria;
        this.operacaoSecundaria = operacaoSecundaria;
    }

    public int getOperacaoPrimaria() {
        return operacaoPrimaria;
    }

    public int getOperacaoSecundaria() {
        return operacaoSecundaria;
    }

    private static void inicio() {
        System.out.println("1 - Cadastros\n2 - Listas\n3 - Excluir\n4 - Gerar Sinistro\n5 - Transferir Seguro\n6 - Calcular Receita Seguradora\n0 - Sair");
    }

    private static void cadastrar() {
        System.out.println("1.1 - Cadastrar Cliente PF\n1.2 - Cadastrar Cliente PJ\n1.3 - Cadastrar Veiculo PF\n1.4 - Cadastrar Veículo Frota\n1.5 Cadastrar Condutor\n1.6 Cadastrar Seguro\n1.7 - Cadastrar Seguradora\n1.8 - Voltar");
    }

    private static ClientePF cadastrarClientePF(Scanner scanner) {
        Cliente.Validacao nameValidator = new Cliente.Validacao();
        ClientePF.Validacao cpfValidator = new ClientePF.Validacao();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        while (!cpfValidator.validarCodigo(cpf)) {
            System.out.println("CPF inválido. Por favor insira um CPF válido.");
            System.out.print("CPF: ");
            cpf = scanner.nextLine();
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        while (!nameValidator.validarNome(nome)) {
            System.out.println("Nome inválido. Por favor insira um nome válido.");
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();
        }
        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        System.out.print("Nível de escolaridade: ");
        String educacao = scanner.nextLine();
        System.out.print("Classe econômica: ");
        String classeEconomica = scanner.nextLine();
        System.out.print("Data da Licença: ");
        String dataLicenca = scanner.nextLine();
        ClientePF clientePF = new ClientePF(nome, endereco, cpf, dataLicenca, educacao, genero, classeEconomica, dataNascimento);
        return clientePF;
    }

    private static ClientePJ cadastrarClientePJ(Scanner scanner) {
        ClientePJ.Validacao cnpjValidator = new ClientePJ.Validacao();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        while (!cnpjValidator.validarCodigo(cnpj)) {
            System.out.println("CNPJ inválido. Por favor insira um CNPJ válido.");
            System.out.print("CNPJ: ");
            cnpj = scanner.nextLine();
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Data de Fundação: ");
        String dataFundacao = scanner.nextLine();
        System.out.print("Quantidade de Funcionários: ");
        int quantidadeFuncionarios = Integer.parseInt(scanner.nextLine());
        ClientePJ clientePJ = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, quantidadeFuncionarios);
        return clientePJ;
    }

    private static Veiculo cadastrarVeiculo(Scanner scanner) {
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano de Fabricação: ");
        int anoFabricacao;
        try {
            anoFabricacao = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            anoFabricacao = 0;
        }
        Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
        return veiculo;
    }

    private static Condutor cadastrarCondutor(Scanner scanner) {
        ClientePF.Validacao cpfValidator = new ClientePF.Validacao();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        while (!cpfValidator.validarCodigo(cpf)) {
            System.out.println("CNPJ inválido. Por favor insira um CNPJ válido.");
            System.out.print("CNPJ: ");
            cpf = scanner.nextLine();
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();
        return new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
    }

    private static void cadastrarSeguro(List<Seguradora> listaSeguradoras, Scanner scanner) throws Exception {
        System.out.print("Data de Início: ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data de Fim: ");
        String dataFim = scanner.nextLine();
        System.out.print("Valor Mensal: ");
        int valorMensal = Integer.parseInt(scanner.nextLine());
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int index1 = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(index1);
        System.out.print("CPF/CNPJ do Cliente: ");
        String codigoCliente = scanner.nextLine();
        String numbersOnlyCode = codigoCliente.replaceAll("[^0-9]", "");
        int index2 = -1;
        if (numbersOnlyCode.length() == 11) {
			index2 = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF(codigoCliente, "", "", "", "", "", "", ""), new ClienteComparator());
            ClientePF cliente = (ClientePF) seguradora.getListaClientes().get(index2);
            System.out.print("Placa do Veículo: ");
            String placa = scanner.nextLine();
            int index3 = Collections.binarySearch(cliente.getListaVeiculos(), new Veiculo(placa, "", "", 0), (a,b) -> {
                return a.getPlaca().compareTo(b.getPlaca());
            });
            Veiculo veiculo = cliente.getListaVeiculos().get(index3);
            seguradora.gerarSeguro(dataInicio, dataFim, valorMensal, veiculo, cliente);
		}
		else if (numbersOnlyCode.length() == 14) {
			index2 = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ(codigoCliente, "", "", "", "", "", 0), new ClienteComparator());
            ClientePJ cliente = (ClientePJ) seguradora.getListaClientes().get(index2);
            System.out.print("Código da Frota: ");
            String codigo = scanner.nextLine();
            int index3;
            for (index3 = 0; index3 < cliente.getListaFrota().size(); index3++) {
                if (cliente.getListaFrota().get(index3).getCode() == codigo) break;
            }
            Frota frota = cliente.getListaFrota().get(index3);
            seguradora.gerarSeguro(dataInicio, dataFim, valorMensal, frota, cliente);
		}
    }

    private static Seguradora cadastrarSeguradora(Scanner scanner) {
        ClientePJ.Validacao cnpjValidator = new ClientePJ.Validacao();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        while (!cnpjValidator.validarCodigo(cnpj)) {
            System.out.println("CNPJ inválido. Por favor insira um CNPJ válido.");
            System.out.print("CNPJ: ");
            cnpj = scanner.nextLine();
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        Seguradora seguradora = new Seguradora(cnpj, nome, telefone, endereco, email);
        return seguradora;
    }

    private static void listar() {
        System.out.println("2.1 - Listar Cliente (PF/PJ) por Seguradora\n2.2 - Listar Sinistros por Seguradora\n2.3 - Listar Sinistro por Cliente\n2.4 - Listar Sinistro por Seguro\n2.5 - Listar Sinistros por Condutor\n2.6 - Listar Veículo por Cliente\n2.7 - Listar Frota por Cliente\n2.8 - Listar Veiculo por Seguradora\n2.9 - Listar Condutores por Seguro\n2.10 - Listar Seguros por Seguradora\n2.11 - Listar Seguros por Cliente\n2.12 - Voltar");
    }

    private static void listarClienteSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        seguradora.listarClientes();
    }

    private static void listarSinistrosSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        List<Sinistro> sinistros = new ArrayList<Sinistro>();
        for (Seguro seguro : listaSeguradoras.get(indiceSeguradora).getListaSeguros()) {
            for (Sinistro sinistro : seguro.getListaSinistros()) {
                sinistros.add(sinistro);
            }
        }
        for (Sinistro sinistro: sinistros) {
            System.out.println(sinistro);
        }
    }

    private static void listarSinistrosCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do cliente: ");
        String codigo = scanner.nextLine();
        List<Sinistro> sinistros = seguradora.getSinistrosPorCliente(codigo);
        for (Sinistro sinistro: sinistros) {
            System.out.println(sinistro);
        }
    }

    private static void listarSinistrosSeguro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index;
        for (index = 0; index < seguradora.getListaSeguros().size(); index++) {
            if (seguradora.getListaSeguros().get(index).getId() == id) break;
        }
        Seguro seguro = seguradora.getListaSeguros().get(index);
        for (Sinistro sinistro : seguro.getListaSinistros()) {
            System.out.println(sinistro);
        }
    }

    private static void listarSinistrosCondutor(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index;
        for (index = 0; index < seguradora.getListaSeguros().size(); index++) {
            if (seguradora.getListaSeguros().get(index).getId() == id) break;
        }
        Seguro seguro = seguradora.getListaSeguros().get(index);
        System.out.print("CPF do Condutor: ");
        String cpf = scanner.nextLine();
        index = Collections.binarySearch(seguro.getListaCondutores(), new Condutor(cpf, "", "", "", "", ""), (a,b) -> {
            return a.getCpf().compareTo(b.getCpf());
        });
        Condutor condutor = seguro.getListaCondutores().get(index);
        for (Sinistro sinistro : condutor.getListaSinistros()) {
            System.out.println(sinistro);
        }
    }

    private static void listarVeiculosCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do cliente: ");
        String codigo = scanner.nextLine();
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int index = -1;
		if (numbersOnlyCode.length() == 11) {
			index = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF(codigo, "", "", "", "", "", "", ""), new ClienteComparator());
            ClientePF cliente = (ClientePF) seguradora.getListaClientes().get(index);
            for(Veiculo veiculo: cliente.getListaVeiculos()) {
                System.out.println(veiculo);
            }
		}
		else if (numbersOnlyCode.length() == 14) {
			index = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ(codigo, "", "", "", "", "", 0), new ClienteComparator());
            ClientePJ cliente = (ClientePJ) seguradora.getListaClientes().get(index);
            List<Veiculo> veiculos = new ArrayList<Veiculo>();
            for (Frota frota : cliente.getListaFrota()) {
                for (Veiculo veiculo : frota.getListaVeiculos()) veiculos.add(veiculo);
            }
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
            }
		}
    }

    private static void listarFrotasCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CNPJ do cliente: ");
        String codigo = scanner.nextLine();
        int index = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ(codigo, "", "", "", "", "", 0), new ClienteComparator());
        ClientePJ cliente = (ClientePJ) seguradora.getListaClientes().get(index);
        for (Frota frota : cliente.getListaFrota()) {
            System.out.println(frota);
        }
    }

    private static void listarVeiculosSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        for (Cliente cliente : seguradora.getListaClientes()) {
            if (cliente instanceof ClientePF) {
                ClientePF clientePF = (ClientePF) cliente;
                for (Veiculo veiculo : clientePF.getListaVeiculos()) {
                    veiculos.add(veiculo);
                }
            }
            else if (cliente instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ) cliente;
                for (Frota frota : clientePJ.getListaFrota()) {
                    for (Veiculo veiculo : frota.getListaVeiculos()) {
                        veiculos.add(veiculo);
                    }
                }
            }
        }
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo);
        }
    }

    private static void listarCondutoresSeguro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index;
        for (index = 0; index < seguradora.getListaSeguros().size(); index++) {
            if (seguradora.getListaSeguros().get(index).getId() == id) break;
        }
        Seguro seguro = seguradora.getListaSeguros().get(index);
        for (Condutor condutor : seguro.getListaCondutores()) {
            System.out.println(condutor);
        }
    }

    private static void listarSegurosSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        for (Seguro seguro : seguradora.getListaSeguros()) {
            System.out.println(seguro);
        }
    }

    private static void listarSegurosCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do Cliente: ");
        String codigo = scanner.nextLine();
        List<Seguro> seguros = seguradora.getSegurosPorCliente(codigo);
        for (Seguro seguro : seguros) {
            System.out.println(seguro);
        }
    }

    private static void excluir() {
        System.out.println("3.1 - Excluir Cliente\n3.2 - Excluir Veículo\n3.3 - Excluir Sinistro\n3.4 - Excluir Condutor\n3.5 - Excluir Seguro\n3.6 - Voltar");
    }

    private static int[] excluirVeiculo(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do Cliente: ");
        String codigo = scanner.nextLine();
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int indiceCliente = -1;
		if (numbersOnlyCode.length() == 11) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF(codigo, "", "", "", "", "", "", ""), new ClienteComparator());
            int indexes[] = { indiceSeguradora, indiceCliente };
            return indexes;
		}
		else if (numbersOnlyCode.length() == 14) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ(codigo, "", "", "", "", "", 0), new ClienteComparator());
            ClientePJ cliente = (ClientePJ) seguradora.getListaClientes().get(indiceCliente);
            System.out.print("Código da Frota do Cliente: ");
            String code = scanner.nextLine();
            int indiceFrota;
            for (indiceFrota = 0; indiceFrota < cliente.getListaFrota().size(); indiceFrota++) {
                if (cliente.getListaFrota().get(indiceFrota).getCode().equals(code)) break;
            }
            int indexes[] = { indiceSeguradora, indiceCliente, indiceFrota };
            return indexes;
		}
        int indexes[] = {};
        return indexes;
    }

    private static int[] excluirSinistro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int idSeguro = Integer.parseInt(scanner.nextLine());
        int indiceSeguro = 0;
        while (seguradora.getListaSeguros().get(indiceSeguro).getId() != idSeguro) indiceSeguro++;
        Seguro seguro = seguradora.getListaSeguros().get(indiceSeguro);
        System.out.print("ID do Sinistro: ");
        int idSinistro = Integer.parseInt(scanner.nextLine());
        int i = 0;
        while (seguro.getListaSinistros().get(i).getId() != idSinistro) i++;
        int indexes[] = {indiceSeguradora, indiceSeguro, i};
        return indexes;
    }

    private static int[] excluirCondutor(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        });
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int idSeguro = Integer.parseInt(scanner.nextLine());
        int indiceSeguro = 0;
        while (seguradora.getListaSeguros().get(indiceSeguro).getId() != idSeguro) indiceSeguro++;
        int indexes[] = {indiceSeguradora, indiceSeguro };
        return indexes;
    }

    private static int[] gerarSinistro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int id = Integer.parseInt(scanner.nextLine());
        int indiceSeguro;
        for (indiceSeguro = 0; indiceSeguro < seguradora.getListaSeguros().size(); indiceSeguro++) {
            if (seguradora.getListaSeguros().get(indiceSeguro).getId() == id) break;
        }
        int indexes[] = { indiceSeguradora, indiceSeguro };
        return indexes;
    }

    private static int[] transferirSeguro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Seguro: ");
        int id = Integer.parseInt(scanner.nextLine());
        int indiceSeguro;
        for (indiceSeguro = 0; indiceSeguro < seguradora.getListaSeguros().size(); indiceSeguro++) {
            if (seguradora.getListaSeguros().get(indiceSeguro).getId() == id) break;
        }
        System.out.print("CPF/CNPJ do Cliente que receberá a transferência: ");
        String codigo = scanner.nextLine();
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int indiceCliente = -1;
		if (numbersOnlyCode.length() == 11) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF(codigo, "", "", "", "", "", "", ""), new ClienteComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ(codigo, "", "", "", "", "", 0), new ClienteComparator());
		}
        int[] indexes = { indiceSeguradora, indiceSeguro, indiceCliente };
        return indexes;
    }

    private static int calcularReceitaSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("CNPJ da Seguradora: ");
        String cnpj = scanner.nextLine();
        int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
            return a.getCnpj().compareTo(b.getCnpj());
        }); 
        return indiceSeguradora;
    }

    public static void menuOperacoes(List<Seguradora> listaSeguradoras) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int operacaoPrimaria = INICIO.getOperacaoPrimaria();
        int operacaoSecundaria = INICIO.getOperacaoSecundaria();
        while (operacaoPrimaria != -1) {
            if (operacaoPrimaria == INICIO.getOperacaoPrimaria()) {
                inicio();
                operacaoPrimaria = Integer.parseInt(scanner.nextLine());
            }
            else if (operacaoPrimaria == CADASTRAR.getOperacaoPrimaria()) {
                if (operacaoSecundaria == CADASTRAR.getOperacaoSecundaria()) {
                    cadastrar();
                    operacaoSecundaria = Integer.parseInt(scanner.nextLine());
                }
                else if (operacaoSecundaria == CADASTRAR_CLIENTE_PF.getOperacaoSecundaria()) {
                    ClientePF clientePF = cadastrarClientePF(scanner);
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int index = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    listaSeguradoras.get(index).cadastrarCliente(clientePF);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_CLIENTE_PJ.getOperacaoSecundaria()) {
                    ClientePJ clientePJ = cadastrarClientePJ(scanner);
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int index = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    listaSeguradoras.get(index).cadastrarCliente(clientePJ);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_VEICULO_PF.getOperacaoSecundaria()) {
                    Veiculo veiculo = cadastrarVeiculo(scanner);
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int index1 = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    System.out.print("CPF do Cliente: ");
                    String cpf = scanner.nextLine();
                    int index2 = Collections.binarySearch(listaSeguradoras.get(index1).getListaClientes(), new ClientePF(cpf, "", "", "", "", "", "", ""), new ClienteComparator());
                    ((ClientePF) listaSeguradoras.get(index1).getListaClientes().get(index2)).cadastrarVeiculo(veiculo);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_VEICULO_FROTA.getOperacaoSecundaria()) {
                    Veiculo veiculo = cadastrarVeiculo(scanner);
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int index1 = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    System.out.print("CNPJ do Cliente: ");
                    String cnpjCliente = scanner.nextLine();
                    int index2 = Collections.binarySearch(listaSeguradoras.get(index1).getListaClientes(), new ClientePJ(cnpjCliente, "", "", "", "", "", 0), new ClienteComparator());
                    List<Frota> listaFrotas = ((ClientePJ) listaSeguradoras.get(index1).getListaClientes().get(index2)).getListaFrota();
                    System.out.print("Código da Frota do Cliente: ");
                    String codigo = scanner.nextLine();
                    int index3;
                    for (index3 = 0; index3 < listaFrotas.size(); index3++) {
                        if (listaFrotas.get(index3).getCode().equals(codigo)) break;
                    }
                    ((ClientePJ) listaSeguradoras.get(index1).getListaClientes().get(index2)).getListaFrota().get(index3).addVeiculo(veiculo);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_CONDUTOR.getOperacaoSecundaria()) {
                    Condutor condutor = cadastrarCondutor(scanner);
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int index1 = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    List<Seguro> listaSeguros = listaSeguradoras.get(index1).getListaSeguros();
                    System.out.print("ID do Seguro: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    int index2;
                    for (index2 = 0; index2 < listaSeguros.size(); index2++) {
                        if (listaSeguros.get(index2).getId() == id) break;
                    }
                    listaSeguradoras.get(index1).getListaSeguros().get(index2).autorizarCondutor(condutor);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_SEGURO.getOperacaoSecundaria()) {
                    cadastrarSeguro(listaSeguradoras, scanner);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_SEGURADORA.getOperacaoSecundaria()) {
                    listaSeguradoras.add(cadastrarSeguradora(scanner));
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == VOLTAR_CADASTRAR.getOperacaoSecundaria()) {
                    operacaoPrimaria = INICIO.getOperacaoPrimaria();
                    operacaoSecundaria = INICIO.getOperacaoSecundaria();
                }
                else {
                    System.out.println("Operação inválida.");
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
            }
            else if (operacaoPrimaria == LISTAR.getOperacaoPrimaria()) {
                if (operacaoSecundaria == LISTAR.getOperacaoSecundaria()) {
                    listar();
                    operacaoSecundaria = Integer.parseInt(scanner.nextLine());
                }
                else if (operacaoSecundaria == LISTAR_CLIENTE_POR_SEGURADORA.getOperacaoSecundaria()) {
                    listarClienteSeguradora(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_SINISTROS_POR_SEGURADORA.getOperacaoSecundaria()) {
                    listarSinistrosSeguradora(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_SINISTROS_POR_CLIENTE.getOperacaoSecundaria()) {
                    listarSinistrosCliente(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_SINISTROS_POR_SEGURO.getOperacaoSecundaria()) {
                    listarSinistrosSeguro(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_SINISTROS_POR_CONDUTOR.getOperacaoSecundaria()) {
                    listarSinistrosCondutor(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_VEICULOS_POR_CLIENTE.getOperacaoSecundaria()) {
                    listarVeiculosCliente(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_FROTAS_POR_CLIENTE.getOperacaoSecundaria()) {
                    listarFrotasCliente(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_VEICULOS_POR_SEGURADORA.getOperacaoSecundaria()) {
                    listarVeiculosSeguradora(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_CONDUTORES_POR_SEGURO.getOperacaoSecundaria()) {
                    listarCondutoresSeguro(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_SEGUROS_POR_SEGURADORA.getOperacaoSecundaria()) {
                    listarSegurosSeguradora(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_SEGUROS_POR_CLIENTE.getOperacaoSecundaria()) {
                    listarSegurosCliente(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == VOLTAR_LISTAR.getOperacaoSecundaria()) {
                    operacaoPrimaria = INICIO.getOperacaoPrimaria();
                    operacaoSecundaria = INICIO.getOperacaoSecundaria();
                }
                else {
                    System.out.println("Operação inválida.");
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
            }
            else if (operacaoPrimaria == EXCLUIR.getOperacaoPrimaria()) {
                if (operacaoSecundaria == EXCLUIR.getOperacaoSecundaria()) {
                    excluir();
                    operacaoSecundaria = Integer.parseInt(scanner.nextLine());
                }
                else if (operacaoSecundaria == EXCLUIR_CLIENTE.getOperacaoSecundaria()) {
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    System.out.print("CPF/CNPJ do Cliente: ");
                    String codigo = scanner.nextLine();
                    listaSeguradoras.get(indiceSeguradora).removerCliente(codigo);
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == EXCLUIR_VEICULO.getOperacaoSecundaria()) {
                    int indices[] = excluirVeiculo(listaSeguradoras, scanner);
                    Cliente cliente = listaSeguradoras.get(indices[0]).getListaClientes().get(indices[1]);
                    if (cliente instanceof ClientePF) {
                        ClientePF clientePF = (ClientePF) cliente;
                        System.out.print("Placa do Veiculo: ");
                        String placa = scanner.nextLine();
                        clientePF.removerVeiculo(placa);
                    }
                    else if (cliente instanceof ClientePJ) {
                        ClientePJ clientePJ = (ClientePJ) cliente;
                        Frota frota = clientePJ.getListaFrota().get(indices[2]);
                        System.out.print("Placa do Veiculo: ");
                        String placa = scanner.nextLine();
                        frota.removeVeiculo(placa);
                    }
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == EXCLUIR_SINISTRO.getOperacaoSecundaria()) {
                    int indices[] = excluirSinistro(listaSeguradoras, scanner);
                    listaSeguradoras.get(indices[0]).getListaSeguros().get(indices[1]).getListaSinistros().remove(indices[2]);
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == EXCLUIR_CONDUTOR.getOperacaoSecundaria()) {
                    int indices[] = excluirCondutor(listaSeguradoras, scanner);
                    System.out.print("CPF do Condutor: ");
                    String cpf = scanner.nextLine();
                    listaSeguradoras.get(indices[0]).getListaSeguros().get(indices[1]).desautorizarCondutor(cpf);
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == EXCLUIR_SEGURO.getOperacaoSecundaria()) {
                    System.out.print("CNPJ da Seguradora: ");
                    String cnpj = scanner.nextLine();
                    int indiceSeguradora = Collections.binarySearch(listaSeguradoras, new Seguradora(cnpj, "", "", "", ""), (a, b) -> {
                        return a.getCnpj().compareTo(b.getCnpj());
                    });
                    System.out.print("ID do Seguro: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    listaSeguradoras.get(indiceSeguradora).cancelarSeguro(id);
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == VOLTAR_EXCLUIR.getOperacaoSecundaria()) {
                    operacaoPrimaria = INICIO.getOperacaoPrimaria();
                    operacaoSecundaria = INICIO.getOperacaoSecundaria();
                }
                else {
                    System.out.println("Operação inválida.");
                    operacaoPrimaria = EXCLUIR.getOperacaoPrimaria();
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
            }
            else if (operacaoPrimaria == GERAR_SINISTRO.getOperacaoPrimaria()) {
                System.out.print("Data: ");
                String data = scanner.nextLine();
                System.out.print("Endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("CPF do Condutor: ");
                String cpf = scanner.nextLine();
                int indices[] = gerarSinistro(listaSeguradoras, scanner);
                listaSeguradoras.get(indices[0]).getListaSeguros().get(indices[1]).gerarSinistro(data, endereco, cpf);
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
            }
            else if (operacaoPrimaria == TRANSFERIR_SEGURO.getOperacaoPrimaria()) {
                int indices[] = transferirSeguro(listaSeguradoras, scanner);
                Seguro seguro = listaSeguradoras.get(indices[0]).getListaSeguros().get(indices[1]);
                Cliente cliente = listaSeguradoras.get(indices[0]).getListaClientes().get(indices[2]);
                if (seguro instanceof SeguroPF && cliente instanceof ClientePF) {
                    ((SeguroPF) seguro).setCliente(((ClientePF) cliente));
                }
                else if (seguro instanceof SeguroPJ && cliente instanceof ClientePJ) {
                    ((SeguroPJ) seguro).setCliente(((ClientePJ) cliente));
                }
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
            }
            else if (operacaoPrimaria == CALCULAR_RECEITA_SEGURADORA.getOperacaoPrimaria()) {
                listaSeguradoras.get(calcularReceitaSeguradora(listaSeguradoras, scanner)).calcularReceita();
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
            }
            else if (operacaoPrimaria == 0) {
                Seguradora seguradora = listaSeguradoras.get(0);
                List<Object> seguros = new ArrayList<Object>();
                for (Seguro seguro : seguradora.getListaSeguros()) seguros.add(seguro);
                List<Object> sinistros = new ArrayList<Object>();
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    for (Sinistro sinistro : seguro.getListaSinistros()) {
                        sinistros.add(sinistro);
                    }
                }
                seguradora.getArquivoSeguro().gravarArquivo(seguros);
                seguradora.getArquivoSinistro().gravarArquivo(sinistros);
                operacaoPrimaria = -1;
            }
            else {
                System.out.println("Operação inválida.");
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
            }
        }
        scanner.close();
    }
    
}
