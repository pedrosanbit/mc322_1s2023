import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Seguradora {
	// Atributos
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private List<Cliente> listaClientes;
	private List<Seguro> listaSeguros;
	private ArquivoClientePF arquivoClientePF;
	private ArquivoClientePJ arquivoClientePJ;
	private ArquivoCondutor arquivoCondutor;
	private ArquivoFrota arquivoFrota;
	private ArquivoVeiculo arquivoVeiculo;
	private ArquivoSeguro arquivoSeguro;
	private ArquivoSinistro arquivoSinistro;

	// Construtor
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
		this.arquivoClientePF = new ArquivoClientePF();
		this.arquivoClientePJ = new ArquivoClientePJ();
		this.arquivoCondutor = new ArquivoCondutor();
		this.arquivoFrota = new ArquivoFrota();
		this.arquivoVeiculo = new ArquivoVeiculo();
		this.arquivoSeguro = new ArquivoSeguro();
		this.arquivoSinistro = new ArquivoSinistro();
	}

	// Getters and Setters
	public String getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public ArquivoClientePF getArquivoClientePF() {
		return arquivoClientePF;
	}

	public ArquivoClientePJ getArquivoClientePJ() {
		return arquivoClientePJ;
	}

	public ArquivoCondutor getArquivoCondutor() {
		return arquivoCondutor;
	}

	public ArquivoFrota getArquivoFrota() {
		return arquivoFrota;
	}

	public ArquivoVeiculo getArquivoVeiculo() {
		return arquivoVeiculo;
	}

	public ArquivoSeguro getArquivoSeguro() {
		return arquivoSeguro;
	}

	public ArquivoSinistro getArquivoSinistro() {
		return arquivoSinistro;
	}

	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + nome + ",\ntelefone: " + telefone + ",\nemail: " + email + ",\nendereco: " + endereco + ",\nclientes: " + listaClientes + "\nseguros: " + listaSeguros + "\n}";
	}

	// Exibe uma lista com os Clientes associados à Seguradora
	public void listarClientes() {
		for (Cliente cliente : listaClientes) {
			System.out.println(cliente);
		}
	}

	// Gera um novo Seguro de Pessoa Física associado à Seguradora
	public boolean gerarSeguro(String dataInicio, String dataFim, int valorMensal, Veiculo veiculo, ClientePF cliente) throws Exception {
		for (Seguro seguro : listaSeguros) {
			if (seguro instanceof SeguroPF) {
				SeguroPF seguroPf = (SeguroPF) seguro;
				if (seguroPf.getCliente().equals(cliente) && seguroPf.getVeiculo().equals(veiculo)) return false;
			}
		}
		Seguro seguro = new SeguroPF(dataInicio, dataFim, this, valorMensal, veiculo, cliente);
		List<Object> condutores = this.getArquivoCondutor().lerArquivo();
		condutores.sort((a,b) -> {
			return ((Condutor) a).getCpf().compareTo(((Condutor) b).getCpf());
		});
		for (Object obj: condutores) seguro.getListaCondutores().add((Condutor) obj);
		listaSeguros.add(seguro);
		return true;
	}

	// Gera um novo Seguro de Pessoa Jurídica associado à Seguradora
	public boolean gerarSeguro(String dataInicio, String dataFim, int valorMensal, Frota frota, ClientePJ cliente) {
		for (Seguro seguro : listaSeguros) {
			if (seguro instanceof SeguroPJ) {
				SeguroPJ seguroPj = (SeguroPJ) seguro;
				if (seguroPj.getCliente().equals(cliente) && seguroPj.getFrota().equals(frota)) return false;
			}
		}
		Seguro seguro = new SeguroPJ(dataInicio, dataFim, this, valorMensal, frota, cliente);
		listaSeguros.add(seguro);
		return true;
	}

	// Cancela um Seguro associado à Seguradora pelo seu ID
	public boolean cancelarSeguro(int id) {
		for (int i = 0; i < listaSeguros.size(); i++) {
			if (listaSeguros.get(i).getId() == id) {
				listaSeguros.remove(i);
				return true;
			}
		} 
		return false;
	}

	// Cadastra um novo Cliente à Seguradora
	public boolean cadastrarCliente(Cliente cliente) {
		if (Collections.binarySearch(listaClientes, cliente, new ClienteComparator()) >= 0) return false;
		listaClientes.add(cliente);
		listaClientes.sort(new ClienteComparator());
		return true;
	}

	// Remove um Cliente da Seguradora pelo seu CPF/CNPJ
	public boolean removerCliente(String codigo) {
		try {
			String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
			int index = -1;
			if (numbersOnlyCode.length() == 11) {
				index = Collections.binarySearch(listaClientes, new ClientePF("", "", "", "", codigo, "", "", ""), new ClienteComparator());
			}
			else if (numbersOnlyCode.length() == 14) {
				index = Collections.binarySearch(listaClientes, new ClientePJ("", "", "", "", codigo, "", 0), new ClienteComparator());
			}
			if (index < 0 || index >= listaClientes.size()) return false;

			Cliente cliente = listaClientes.get(index);
			for (int i = 0; i < listaSeguros.size(); i++) {
				Seguro seguro = listaSeguros.get(i);
				if (cliente instanceof ClientePF && seguro instanceof SeguroPF) {
					SeguroPF seguroPF = (SeguroPF) seguro;
					if (seguroPF.getCliente().equals(cliente)) {
						listaSeguros.remove(i);
						continue;
					}
				}
				else if (cliente instanceof ClientePJ && seguro instanceof SeguroPJ) {
					SeguroPJ seguroPJ = (SeguroPJ) seguro;
					if (seguroPJ.getCliente().equals(cliente)) {
						listaSeguros.remove(i);
						continue;
					}
				}
			}
			listaClientes.remove(index);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	// Obtém uma lista de Seguros associados a um Cliente da Seguradora pelo seu CPF/CNPJ
	public List<Seguro> getSegurosPorCliente(String codigo) {
		try {
			String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
			int index = -1;
			if (numbersOnlyCode.length() == 11) {
				index = Collections.binarySearch(listaClientes, new ClientePF("", "", "", "", codigo, "", "", ""), new ClienteComparator());
			}
			else if (numbersOnlyCode.length() == 14) {
				index = Collections.binarySearch(listaClientes, new ClientePJ("", "", "", "", codigo, "", 0), new ClienteComparator());
			}
			if (index < 0 || index >= listaClientes.size()) return new ArrayList<Seguro>();

			Cliente cliente = listaClientes.get(index);
			List<Seguro> segurosCliente = new ArrayList<Seguro>();
			for (Seguro seguro : listaSeguros) {
				if (seguro instanceof SeguroPF) {
					SeguroPF seguroPF = (SeguroPF) seguro;
					if (seguroPF.getCliente().equals(cliente)) segurosCliente.add(seguroPF);
				}
				else if (seguro instanceof SeguroPJ) {
					SeguroPJ seguroPJ = (SeguroPJ) seguro;
					if (seguroPJ.getCliente().equals(cliente)) segurosCliente.add(seguroPJ);
				}
			}
			return segurosCliente;
		}
		catch (Exception e) {
			return new ArrayList<Seguro>();
		}
	}

	// Obtém uma lista de Sinistros associados a um Cliente da Seguradora pelo seu CPF/CNPJ
	public List<Sinistro> getSinistrosPorCliente(String codigo) {
		List<Seguro> segurosCliente = getSegurosPorCliente(codigo);
		System.out.println(segurosCliente);
		List<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();
		for (Seguro seguro : segurosCliente) {
			for (Sinistro sinistro : seguro.getListaSinistros()) sinistrosCliente.add(sinistro);
		}
		return sinistrosCliente;
	}

	// Exibe a receita total da Seguradora
	public void calcularReceita() {
		double receita = 0;
		Locale locale = new Locale("pt", "BR");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		
		for (Seguro seguro : listaSeguros) {
			receita += seguro instanceof SeguroPF ? ((SeguroPF) seguro).calcularValor() : seguro instanceof SeguroPJ ? ((SeguroPJ) seguro).calcularValor() : 0;
		}
		System.out.println(currencyFormatter.format(receita));
	}
}
