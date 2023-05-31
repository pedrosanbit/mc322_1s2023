import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public enum MenuOperacoes {
    CADASTRAR(1,0),
    CADASTRAR_CLIENTE_PF(1,1),
    CADASTRAR_CLIENTE_PJ(1,2),
    CADASTRAR_VEICULO_PF(1,3),
    CADASTRAR_VEICULO_FROTA(1,4),
    CADASTRAR_SEGURADORA(1,5),
    VOLTAR_CADASTRAR(1,6),
    LISTAR(2,0),
    LISTAR_CLIENTE_POR_SEGURADORA(2,1),
    LISTAR_SINISTROS_POR_SEGURADORA(2,2),
    LISTAR_SINISTROS_POR_CLIENTE(2,3),
    LISTAR_VEICULOS_POR_CLIENTE(2,4),
    LISTAR_VEICULOS_POR_SEGURADORA(2,5),
    VOLTAR_LISTAR(2,6),
    EXCLUIR(3,0),
    EXCLUIR_CLIENTE(3,1),
    EXCLUIR_VEICULO(3,2),
    EXCLUIR_SINISTRO(3,3),
    VOLTAR_EXCLUIR(3,4),
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
        System.out.println("1.1 - Cadastrar Cliente PF\n1.2 - Cadastrar Cliente PJ\n1.3 - Cadastrar Veiculo\n1.4 - Cadastrar Seguradora\n1.5 - Voltar");
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
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Data de Fundação: ");
        String dataFundacao = scanner.nextLine();
        System.out.print("Quantidade de Funcionários: ");
        int quantidadeFuncionarios = Integer.parseInt(scanner.nextLine());
        ClientePJ clientePJ = new ClientePJ(nome, endereco, cnpj, dataFundacao, quantidadeFuncionarios);
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

    private static Seguradora cadastrarSeguradora(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        return seguradora;
    }

    private static void listar() {
        System.out.println("2.1 - Listar Cliente (PF/PJ) por Seguradora\n2.2 - Listar Sinistros por Seguradora\n2.3 - Listar Sinistro por Cliente\n2.4 - Listar Veiculo por Cliente\n2.5 - Listar Veiculo por Seguradora\n2.6 - Voltar");
    }

    private static void listarClienteSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("Tipo de Cliente (PF/PJ): ");
        String tipoCliente = scanner.nextLine();
        System.out.println(seguradora.listarClientes(tipoCliente));
    }

    private static void listarSinistrosSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        listaSeguradoras.get(indiceSeguradora).listarSinistros();
    }

    private static void listarSinistrosCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do cliente: ");
        String codigo = scanner.nextLine();
        for (Sinistro sinistro: seguradora.getListaSinistros()) {
            if (sinistro.getCliente().getCodigo().equals(codigo)) System.out.println(sinistro);
        }
    }

    private static void listarVeiculosCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do cliente: ");
        String codigo = scanner.nextLine();
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int index = -1;
		if (numbersOnlyCode.length() == 11) {
			index = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF("", "", codigo, "", "", "", "", ""), new ClienteComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			index = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ("", "", codigo, "", 0), new ClienteComparator());
		}
        Cliente cliente = seguradora.getListaClientes().get(index);
        for(Veiculo veiculo: cliente.getListaVeiculos()) {
            System.out.println(veiculo);
        }
    }

    private static void listarVeiculosSeguradora(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        for(Cliente cliente: seguradora.getListaClientes()) {
            for(Veiculo veiculo: cliente.getListaVeiculos()) {
                System.out.println(veiculo);
            }
        }
    }

    private static void excluir() {
        System.out.println("3.1 - Excluir Cliente\n3.2 - Excluir Veículo\n3.3 - Excluir Sinistro\n3.4 - Voltar");
    }

    private static int excluirCliente(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        return indiceSeguradora;
    }

    private static int[] excluirVeiculo(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do Cliente: ");
        String codigo = scanner.nextLine();
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int indiceCliente = -1;
		if (numbersOnlyCode.length() == 11) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF("", "", codigo, "", "", "", "", ""), new ClienteComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ("", "", codigo, "", 0), new ClienteComparator());
		}
        System.out.print("Placa do Veiculo: ");
        String placa = scanner.nextLine();
        int indiceVeiculo = Collections.binarySearch(seguradora.getListaClientes().get(indiceCliente).getListaVeiculos(), new Veiculo(placa, "", "", 0), (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});
        int indexes[] = { indiceSeguradora, indiceCliente, indiceVeiculo };
        return indexes;
    }

    private static int[] excluirSinistro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("ID do Sinistro: ");
        int idSinistro = Integer.parseInt(scanner.nextLine());
        int i = 0;
        while (seguradora.getListaSinistros().get(i).getId() != idSinistro) i++;
        int indexes[] = {indiceSeguradora, i};
        return indexes;
    }

    private static int[] gerarSinistro(List<Seguradora> listaSeguradoras, Scanner scanner, String codigo) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int indiceCliente = -1;
		if (numbersOnlyCode.length() == 11) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF("", "", codigo, "", "", "", "", ""), new ClienteComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			indiceCliente = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ("", "", codigo, "", 0), new ClienteComparator());
		}
        System.out.print("Placa do Veiculo: ");
        String placa = scanner.nextLine();
        int indiceVeiculo = Collections.binarySearch(seguradora.getListaClientes().get(indiceCliente).getListaVeiculos(), new Veiculo(placa, "", "", 0), (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});
        int indexes[] = { indiceSeguradora, indiceCliente, indiceVeiculo };
        return indexes;
    }

    private static int[] transferirSeguro(List<Seguradora> listaSeguradoras, Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        int indiceSeguradora = Integer.parseInt(scanner.nextLine());
        Seguradora seguradora = listaSeguradoras.get(indiceSeguradora);
        System.out.print("CPF/CNPJ do Cliente do qual o Seguro será transferido: ");
        String codigo = scanner.nextLine();
        String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int indiceCliente1 = -1;
		if (numbersOnlyCode.length() == 11) {
			indiceCliente1 = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF("", "", codigo, "", "", "", "", ""), new ClienteComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			indiceCliente1 = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ("", "", codigo, "", 0), new ClienteComparator());
		}
        System.out.print("CPF/CNPJ do Cliente que receberá a transferência: ");
        codigo = scanner.nextLine();
        numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
	    int indiceCliente2 = -1;
		if (numbersOnlyCode.length() == 11) {
			indiceCliente2 = Collections.binarySearch(seguradora.getListaClientes(), new ClientePF("", "", codigo, "", "", "", "", ""), new ClienteComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			indiceCliente2 = Collections.binarySearch(seguradora.getListaClientes(), new ClientePJ("", "", codigo, "", 0), new ClienteComparator());
		}
        int[] indexes = { indiceSeguradora, indiceCliente1, indiceCliente2 };
        return indexes;
    }

    private static int calcularReceitaSeguradora(Scanner scanner) {
        System.out.print("Índice da Seguradora no sistema: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static void menuOperacoes(List<Seguradora> listaSeguradoras) {
        Scanner scanner = new Scanner(System.in);
        int operacaoPrimaria = INICIO.getOperacaoPrimaria();
        int operacaoSecundaria = INICIO.getOperacaoSecundaria();
        while (operacaoPrimaria != 0) {
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
                    System.out.print("Índice da Seguradora no sistema: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    listaSeguradoras.get(index).cadastrarCliente(clientePF);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_CLIENTE_PJ.getOperacaoSecundaria()) {
                    ClientePJ clientePJ = cadastrarClientePJ(scanner);
                    System.out.print("Índice da Seguradora no sistema: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    listaSeguradoras.get(index).cadastrarCliente(clientePJ);
                    operacaoPrimaria = CADASTRAR.getOperacaoPrimaria();
                    operacaoSecundaria = CADASTRAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == CADASTRAR_VEICULO.getOperacaoSecundaria()) {
                    Veiculo veiculo = cadastrarVeiculo(scanner);
                    System.out.print("Índice da Seguradora no sistema: ");
                    int index1 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Índice do Cliente na Seguradora: ");
                    int index2 = Integer.parseInt(scanner.nextLine());
                    listaSeguradoras.get(index1).getListaClientes().get(index2).adicionarVeiculo(veiculo);
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
                else if (operacaoSecundaria == LISTAR_VEICULOS_POR_CLIENTE.getOperacaoSecundaria()) {
                    listarVeiculosCliente(listaSeguradoras, scanner);
                    operacaoPrimaria = LISTAR.getOperacaoPrimaria();
                    operacaoSecundaria = LISTAR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == LISTAR_VEICULOS_POR_SEGURADORA.getOperacaoSecundaria()) {
                    listarVeiculosSeguradora(listaSeguradoras, scanner);
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
                    System.out.print("CPF/CNPJ do Cliente: ");
                    String codigo = scanner.nextLine();
                    listaSeguradoras.get(excluirCliente(listaSeguradoras, scanner)).removerCliente(codigo);
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == EXCLUIR_VEICULO.getOperacaoSecundaria()) {
                    int indices[] = excluirVeiculo(listaSeguradoras, scanner);
                    listaSeguradoras.get(indices[0]).getListaClientes().get(indices[1]).getListaVeiculos().remove(indices[2]);
                    operacaoSecundaria = EXCLUIR.getOperacaoSecundaria();
                }
                else if (operacaoSecundaria == EXCLUIR_SINISTRO.getOperacaoSecundaria()) {
                    int indices[] = excluirSinistro(listaSeguradoras, scanner);
                    listaSeguradoras.get(indices[0]).getListaSinistros().remove(indices[1]);
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
                System.out.print("CPF/CNPJ do cliente: ");
                String codigo = scanner.nextLine();
                int indices[] = gerarSinistro(listaSeguradoras, scanner, codigo);
                Veiculo veiculo = listaSeguradoras.get(indices[0]).getListaClientes().get(indices[1]).getListaVeiculos().get(indices[2]);
                listaSeguradoras.get(indices[0]).gerarSinistro(data, endereco, veiculo, codigo);
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
            }
            else if (operacaoPrimaria == TRANSFERIR_SEGURO.getOperacaoPrimaria()) {
                int indices[] = transferirSeguro(listaSeguradoras, scanner);
                listaSeguradoras.get(indices[0]).getListaClientes().get(indices[2]).getListaVeiculos().addAll(listaSeguradoras.get(indices[0]).getListaClientes().get(indices[1]).getListaVeiculos());
                listaSeguradoras.get(indices[0]).getListaClientes().get(indices[1]).getListaVeiculos().clear();
                listaSeguradoras.get(indices[0]).calcularPrecoSeguroCliente();
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
            }
            else if (operacaoPrimaria == CALCULAR_RECEITA_SEGURADORA.getOperacaoPrimaria()) {
                listaSeguradoras.get(calcularReceitaSeguradora(scanner)).calcularReceita();
                operacaoPrimaria = INICIO.getOperacaoPrimaria();
                operacaoSecundaria = INICIO.getOperacaoSecundaria();
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
