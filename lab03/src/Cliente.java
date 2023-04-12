import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;

	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

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
