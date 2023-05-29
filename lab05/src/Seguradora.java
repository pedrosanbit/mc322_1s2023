import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seguradora {
	// Atributos
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private List<Cliente> listaClientes;
	private List<Seguro> listaSeguros;

	// Construtor
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
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
	
	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + nome + ",\ntelefone: " + telefone + ",\nemail: " + email + ",\nendereco: " + endereco + ",\nclientes: " + listaClientes + "\nseguros: " + listaSeguros + "\n}";
	}

	public void listarClientes() {
		for (Cliente cliente : listaClientes) {
			System.out.println(cliente);
		}
	}

	public boolean gerarSeguro(String dataInicio, String dataFim, int valorMensal, Veiculo veiculo, ClientePF cliente) {
		for (Seguro seguro : listaSeguros) {
			if (seguro instanceof SeguroPF) {
				SeguroPF seguroPf = (SeguroPF) seguro;
				if (seguroPf.getCliente().equals(cliente) && seguroPf.getVeiculo().equals(veiculo)) return false;
			}
		}
		Seguro seguro = new SeguroPF(dataInicio, dataFim, this, valorMensal, veiculo, cliente);
		listaSeguros.add(seguro);
		return true;
	}

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

	public boolean cancelarSeguro(int id) {
		for (int i = 0; i < listaSeguros.size(); i++) {
			if (listaSeguros.get(i).getId() == id) {
				listaSeguros.remove(i);
				return true;
			}
		} 
		return false;
	}

	public boolean cadastrarCliente(String nome, String telefone, String endereco, String email, String cpf, String genero, String educacao, String dataNascimento) {
		if (Collections.binarySearch(listaClientes, new ClientePF("","", "", "", cpf, "", "", ""), new ClienteComparator()) >= 0) return false;
		Cliente cliente = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento);
		listaClientes.add(cliente);
		listaClientes.sort(new ClienteComparator());
		return true;
	}

	public boolean cadastrarCliente(String nome, String telefone, String endereco, String email, String cnpj, String dataFundacao, int qtdeFuncionarios) {
		if (Collections.binarySearch(listaClientes, new ClientePJ("", "", "", "", cnpj, "", 0), new ClienteComparator()) >= 0) return false;
		Cliente cliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, qtdeFuncionarios);
		listaClientes.add(cliente);
		listaClientes.sort(new ClienteComparator());
		return true;
	}

	public boolean removerCliente(String codigo) {
		try {
			String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
			int index = -1;
			if (numbersOnlyCode.length() == 11) {
				index = Collections.binarySearch(listaClientes, new ClientePF("", "", "", "", numbersOnlyCode, "", "", ""), new ClienteComparator());
			}
			else if (numbersOnlyCode.length() == 14) {
				index = Collections.binarySearch(listaClientes, new ClientePJ("", "", "", "", numbersOnlyCode, "", 0), new ClienteComparator());
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

	public List<Seguro> getSegurosPorCliente(String codigo) {
		try {
			String numbersOnlyCode = codigo.replaceAll("[^0-9]", "");
			int index = -1;
			if (numbersOnlyCode.length() == 11) {
				index = Collections.binarySearch(listaClientes, new ClientePF("", "", "", "", numbersOnlyCode, "", "", ""), new ClienteComparator());
			}
			else if (numbersOnlyCode.length() == 14) {
				index = Collections.binarySearch(listaClientes, new ClientePJ("", "", "", "", numbersOnlyCode, "", 0), new ClienteComparator());
			}
			if (index < 0 || index >= listaClientes.size()) return new ArrayList<Seguro>();

			Cliente cliente = listaClientes.get(index);
			List<Seguro> segurosCliente = new ArrayList<Seguro>();
			for (Seguro seguro : listaSeguros) {
				if (cliente instanceof ClientePF && seguro instanceof SeguroPF) {
					SeguroPF seguroPF = (SeguroPF) seguro;
					if (seguroPF.getCliente().equals(cliente)) segurosCliente.add(seguroPF);
				}
				else if (cliente instanceof ClientePJ && seguro instanceof SeguroPJ) {
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

	public List<Sinistro> getSinistrosPorCliente(String codigo) {
		List<Seguro> segurosCliente = getSegurosPorCliente(codigo);
		List<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();
		for (Seguro seguro : segurosCliente) {
			for (Sinistro sinistro : seguro.getListaSinistros()) sinistrosCliente.add(sinistro);
		}
		return sinistrosCliente;
	}

	public void calcularReceita() {
		int receita = 0;
		for (Seguro seguro : listaSeguros) {
			receita += seguro.getValorMensal();
		}
		System.out.println(receita);
	}
}
