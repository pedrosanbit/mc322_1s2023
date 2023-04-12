import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ClientePF extends Cliente {
    private final String cpf;
	private Date dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;
    private Date dataNascimento;
    
    public ClientePF(String nome, String endereco, String cpf, String dataLicenca, String educacao, String genero,
			String classeEconomica, String dataNascimento) throws ParseException {
		super(nome, endereco);
		this.cpf = cpf;
		this.dataLicenca = DateFormat.getInstance().parse(dataLicenca);
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.dataNascimento = DateFormat.getInstance().parse(dataNascimento);
	}

	public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

	private static boolean temDigitosIguais(String cpf) {
		char digito = cpf.charAt(0);
		for (int i = 1; i < cpf.length(); i++) {
			char digitoAtual = cpf.charAt(i);
			if(digito != digitoAtual) return false;
			digito = digitoAtual;
		}
		return true;
	}
	
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
	
	public boolean validarCPF() {
		String numbersOnlyCPF = this.cpf.replaceAll("[^0-9]", "");
		if (numbersOnlyCPF.length() != 11) return false;
		if (temDigitosIguais(numbersOnlyCPF)) return false;
		if (!validarDigitosVerificadores(numbersOnlyCPF)) return false;
		return true;
	}
}
