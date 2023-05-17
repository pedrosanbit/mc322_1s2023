import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	// Atributos
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private List<Sinistro> listaSinitros;
	private List<Seguro> listaSeguros;

	// Construtor
	public Cliente(String nome, String telefone, String endereco, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaSinitros = new ArrayList<Sinistro>();
		this.listaSeguros = new ArrayList<Seguro>();
	}

	// Getters and Setters
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

	public List<Sinistro> getListaSinitros() {
		return listaSinitros;
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	// Obtém o CPF/CNPJ correspondente do cliente
	public String getCodigo() {
		if (this instanceof ClientePF) {
			ClientePF clientePF = (ClientePF) this;
			return clientePF.getCpf();
		}
		else if (this instanceof ClientePJ) {
			ClientePJ clientePJ = (ClientePJ) this;
			return clientePJ.getCnpj();
		}
		return "";
	}

	// Método de calculo do score do cliente para ser sobrescrito nas classes-filha
	public double calculaScore() {
		return 0;
	}
	
	// Classe estática para validação do nome do Cliente
	public static class Validacao {
		public boolean validarNome(String nome) {
			return nome.trim().matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
		}
	}
}
