import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClientePF extends Cliente {
	//Atributos
    private final String cpf;
	private LocalDate dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;
    private LocalDate dataNascimento;

    //Construtor
    public ClientePF(String nome, String endereco, String cpf, String dataLicenca, String educacao, String genero,
			String classeEconomica, String dataNascimento) {
		super(nome, endereco);
		this.cpf = cpf;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		
		try {
			this.dataLicenca = LocalDate.parse(dataLicenca, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		catch (DateTimeParseException e) {
			this.dataLicenca = LocalDate.now();
		}

		try {
			this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		catch (DateTimeParseException e){
			this.dataNascimento = LocalDate.MIN;
		}
	}

	//Getters e Setters
	public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public LocalDate getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
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

	//toString override
	@Override
	public String toString() {
		return "{\nnome: " + this.getNome() + ",\nendereco: " + this.getEndereco() + ",\ncpf: " + cpf + ",\ndataLicenca: " + dataLicenca.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",\neducacao: " + educacao + ",\ngenero: "
				+ genero + ",\nclasseEconomica: " + classeEconomica + ",\ndataNascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n}";
	}

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
	private static boolean validarDigitosVerificadores(String cpf) {
		int somatorio = 0;
		int primeiroDigitoEsperado = Character.getNumericValue(cpf.charAt(9));
		int segundoDigitoEsperado = Character.getNumericValue(cpf.charAt(10));

		for (int i = 0; i < 9; i++) {
			somatorio += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		somatorio *= 10;

		int primeiroDigito = somatorio % 11 == 10 ? 0 : somatorio % 11;
		if (primeiroDigito != primeiroDigitoEsperado) return false;

		somatorio = 0;
		for (int i = 0; i < 10; i++) {
			somatorio += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		somatorio *= 10;

		int segundoDigito = somatorio % 11 == 10 ? 0 : somatorio % 11;
		if (segundoDigito != segundoDigitoEsperado) return false;

		return true;
	}
	
	//Valida o CPF da Pessoa Física
	public static boolean validarCPF(String cpf) {
		String numbersOnlyCPF = cpf.replaceAll("[^0-9]", "");
		if (numbersOnlyCPF.length() != 11) return false;
		if (temDigitosIguais(numbersOnlyCPF)) return false;
		if (!validarDigitosVerificadores(numbersOnlyCPF)) return false;
		return true;
	}
}
