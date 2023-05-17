import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {
	//Atributos
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	private double valorSeguro;

	//Construtor
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

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

	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + nome + ",\nendereco: " + endereco + "\n}";
	}
	
	//Adiciona um veículo à lista de veículos
	public boolean adicionarVeiculo(Veiculo veiculo) {
		if (Collections.binarySearch(listaVeiculos, veiculo, (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		}) >= 0) return false;

		listaVeiculos.add(veiculo);
		Collections.sort(listaVeiculos, (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});

		return true;
	}

	public double calculaScore() {
		return 0;
	}

	public static class Validacao {
		public boolean validarNome(String nome) {
			return nome.trim().matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
		}
	}
	
}
