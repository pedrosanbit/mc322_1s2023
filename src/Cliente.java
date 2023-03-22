public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento + "\nIdade: " + idade
				+ "\nEndereço: " + endereco;
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
