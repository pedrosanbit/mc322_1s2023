import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros;
	private List<Cliente> listaClientes;
	
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		listaSinistros = new ArrayList<Sinistro>();
		listaClientes = new ArrayList<Cliente>();
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

	@Override
	public String toString() {
		return "Nome: " + nome + "\nTelefone: " + telefone + "\nE-mail: " + email + "\nEndereço: " + endereco;
	}

	public boolean cadastrarCliente(Cliente cliente) {
		try {
			listaClientes.add(cliente);
			Collections.sort(listaClientes, (a, b) -> {
				return a.getNome().compareTo(b.getNome());
			});
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean removerCliente(String cliente) {
		try {
			listaClientes.remove(Collections.binarySearch(listaClientes, new Cliente(cliente, "", "01/07/1970", "", "", ""), (a, b) -> {
				return a.getNome().compareTo(b.getNome());
			}));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public List<Cliente> listarClientes(String tipoCliente) {
		List<Cliente> lista = new ArrayList<Cliente>();
		for (Cliente cliente: listaClientes) {
			if (tipoCliente.equals("PF") && cliente instanceof ClientePF) lista.add(cliente);
			else if (tipoCliente.equals("PJ") && cliente instanceof ClientePJ) lista.add(cliente);
		}
		return lista;
	}

	// public boolean gerarSinistro() {

	// }

	public List<Sinistro> listarSinistros() {
		return listaSinistros;
	}
}
