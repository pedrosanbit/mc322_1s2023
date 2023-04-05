import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente {
	private String nome;
	private String endereco;
	private Date dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private List<Veiculo> listaVeiculos;

	public Cliente(String nome, String endereco, String dataLicenca, String educacao, String genero,
			String classeEconomica) throws ParseException {
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicenca = DateFormat.getInstance().parse(dataLicenca);
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
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

	public Date getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
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
