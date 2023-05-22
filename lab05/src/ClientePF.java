import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

public class ClientePF extends Cliente {
	// Atributos
	private final String cpf;
	private String genero;
	private String educacao;
	private LocalDate dataNascimento;
	private List<Veiculo> listaVeiculos;

	// Construtor
	public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String genero,
			String educacao, String dataNascimento) {
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.genero = genero;
		this.educacao = educacao;
		try {
			this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		catch (DateTimeParseException e){
			this.dataNascimento = LocalDate.MIN;
		}
	}

	// Getters and Setter
	public String getCpf() {
		return cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}	
	
	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + this.getNome() + ",\nendereco: " + this.getEndereco() + ",\ncpf: " + cpf + ",\neducacao: " + educacao + ",\ngenero: "
				+ genero + ",\ndataNascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n}";
	}

	public boolean cadastrarVeiculo(Veiculo veiculo) {
		if (Collections.binarySearch(listaVeiculos, veiculo, (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		}) >= 0) return false;

		listaVeiculos.add(veiculo);
		Collections.sort(listaVeiculos, (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});
		return true;
	}

	public boolean removerVeiculo(String placa) {
		int index = Collections.binarySearch(listaVeiculos, new Veiculo(placa, "", "", 0), (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});
		if (index < 0 || index >= listaVeiculos.size()) return false;

		listaVeiculos.remove(index);
		return true;
	}

	public static class Validacao {
		//Valida se um CPF possui todos os digitos iguais
		private static boolean temDigitosIguais(String cpf) {
			char digito = cpf.charAt(0);
			for (int i = 1; i < cpf.length(); i++) {
				char digitoAtual = cpf.charAt(i);
				if(digito != digitoAtual) return false;
				digito = digitoAtual;
			}
			return true;
		}

		//Valida se os digitos verificadores estão corretos em um CPF
		private static boolean validarDigitosVerificadores(String codigo) {
			int somatorio = 0;
			int primeiroDigitoEsperado = Character.getNumericValue(codigo.charAt(9));
			int segundoDigitoEsperado = Character.getNumericValue(codigo.charAt(10));
	
			for (int i = 0; i < 9; i++) {
				somatorio += Character.getNumericValue(codigo.charAt(i)) * (10 - i);
			}
			somatorio *= 10;
	
			int primeiroDigito = somatorio % 11 == 10 ? 0 : somatorio % 11;
			if (primeiroDigito != primeiroDigitoEsperado) return false;
	
			somatorio = 0;
			for (int i = 0; i < 10; i++) {
				somatorio += Character.getNumericValue(codigo.charAt(i)) * (11 - i);
			}
			somatorio *= 10;
	
			int segundoDigito = somatorio % 11 == 10 ? 0 : somatorio % 11;
			if (segundoDigito != segundoDigitoEsperado) return false;
	
			return true;
		}

		//Valida o CPF da Pessoa Física
		public boolean validarCodigo(String codigo) {
			String numbersOnlyCPF = codigo.replaceAll("[^0-9]", "");
			if (numbersOnlyCPF.length() != 11) return false;
			if (temDigitosIguais(numbersOnlyCPF)) return false;
			if (!validarDigitosVerificadores(numbersOnlyCPF)) return false;
			return true;
		}
	}
}
