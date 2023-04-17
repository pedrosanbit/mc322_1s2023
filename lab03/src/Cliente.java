import java.util.ArrayList;
import java.util.List;

public class Cliente {
	//Atributos
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;

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
	
	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + nome + ",\nendereco: " + endereco + "\n}";
	}
	
	//Adiciona um veículo à lista de veículos
	public boolean adicionarVeiculo(Veiculo veiculo) {
		try {
			listaVeiculos.add(veiculo);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
