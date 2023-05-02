import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seguradora {
	//Atributos
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros;
	private List<Cliente> listaClientes;
	
	//Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		listaSinistros = new ArrayList<Sinistro>();
		listaClientes = new ArrayList<Cliente>();
	}

	//Getters e Setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + nome + ",\ntelefone: " + telefone + ",\nemail: " + email + ",\nendereco: " + endereco + "\n}";
	}

	//Cadastra um cliente e o adiciona à lista de Clientes da Seguradora
	public boolean cadastrarCliente(Cliente cliente) {
		int index = Collections.binarySearch(listaClientes, cliente, new ClienteComparator());
		if (index >= 0 && index <= listaClientes.size()) return false;

		listaClientes.add(cliente);
		Collections.sort(listaClientes, new ClienteComparator());

		return true;
	}

	//Remove um cliente da lista de clientes pelo seu CPF ou CNPJ
	public boolean removerCliente(String cliente) {
		try {
			String numbersOnlyCode = cliente.replaceAll("[^0-9]", "");
			int index = -1;
			if (numbersOnlyCode.length() == 11) {
				index = Collections.binarySearch(listaClientes, new ClientePF("", "", cliente, "", "", "", "", ""), new ClienteComparator());
			}
			else if (numbersOnlyCode.length() == 14) {
				index = Collections.binarySearch(listaClientes, new ClientePJ("", "", cliente, "", 0), new ClienteComparator());
			}
			if (index < 0 || index >= listaClientes.size()) return false;

			for (int i = 0; i < listaSinistros.size(); i++) {
				if (listaSinistros.get(i).getCliente().equals(listaClientes.get(index))) {
					listaSinistros.remove(i); 
				}
			}
			listaClientes.remove(index);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	//Retorna uma lista de todos os cliente do tipo passado registrados na lista de clientes da Seguradora
	public List<Cliente> listarClientes(String tipoCliente) {
		List<Cliente> lista = new ArrayList<Cliente>();
		for (Cliente cliente: listaClientes) {
			if (tipoCliente.equals("PF") && cliente instanceof ClientePF) lista.add(cliente);
			else if (tipoCliente.equals("PJ") && cliente instanceof ClientePJ) lista.add(cliente);
		}
		return lista;
	}

	//Gera um sinistro com os parâmetros passados e o adiciona à lista de Sinistros da Seguradora
	public boolean gerarSinistro(String data, String endereco, Veiculo veiculo, String cliente) {
		if (Collections.binarySearch(listaClientes, new Cliente(cliente, ""), new ClienteComparator()) < 0) return false;
		
		Cliente clienteSinistro = listaClientes.get(Collections.binarySearch(listaClientes, new Cliente(cliente, ""), new ClienteComparator()));

		Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, clienteSinistro);
		listaSinistros.add(sinistro);
		Collections.sort(listaSinistros, new SinistroComparator());
		
		return true;
	}

	//Exibe a lista de sinistros da Seguradora na tela
	public void listarSinistros() {
		for (Sinistro sinistro: listaSinistros) {
			System.out.println(sinistro);
		}
	}

	//Exibe um sinistro associado a um cliente
	public boolean visualizarSinistro(String cliente) {
		String numbersOnlyCode = cliente.replaceAll("[^0-9]", "");
		int index = -1;
		if (numbersOnlyCode.length() == 11) {
			index = Collections.binarySearch(listaSinistros, new Sinistro("", "", null, null, new ClientePF("", "", cliente, "", "", "", "", "")), new SinistroComparator());
		}
		else if (numbersOnlyCode.length() == 14) {
			index = Collections.binarySearch(listaSinistros, new Sinistro("", "", null, null, new ClientePJ("", "", cliente, "", 0)), new SinistroComparator());
		}
		if (index < 0 || index >= listaSinistros.size()) return false;

		System.out.println(listaSinistros.get(index));
		return true;
	}

	private int getQuantidadeSinistros(Cliente cliente) {
		int quantidadeDeSinistros = 0;
		for(Sinistro sinistro : listaSinistros) {
			if (sinistro.getCliente().equals(cliente)) quantidadeDeSinistros++;
		}
		return quantidadeDeSinistros;
	}

	public void calcularPrecoSeguroCliente() {
		for(Cliente cliente : listaClientes) {
			cliente.setValorSeguro(cliente.calculaScore() * (1 + this.getQuantidadeSinistros(cliente)));
		}
	}
}
