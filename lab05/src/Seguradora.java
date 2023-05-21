import java.util.ArrayList;
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
		//TODO
	}

	public boolean gerarSeguro() {
		//TODO
		return true;
	}

	public boolean cancelarSeguro() {
		//TODO
		return true;
	}

	public boolean cadastrarCliente() {
		//TODO
		return true;
	}

	public boolean removerCliente() {
		//TODO
		return true;
	}

	public List<Seguro> getSegurosPorCliente() {
		//TODO
		return new ArrayList<Seguro>();
	}

	public List<Sinistro> getSinistrosPorCliente() {
		//TODO
		return new ArrayList<Sinistro>();
	}

	public void calcularReceita() {
		//TODO
	}
}
