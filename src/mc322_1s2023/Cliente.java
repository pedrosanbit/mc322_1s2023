package mc322_1s2023;

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
	
	public static boolean temDigitosIguais(String cpf) {
		char digito = cpf.charAt(0);
		for(int i = 1; i < cpf.length(); i++) {
			char digitoAtual = cpf.charAt(i);
			if(digito != digitoAtual) return false;
			digito = digitoAtual;
		}
		return true;
	}
	
	public static boolean validarDigitosVerificadores(String cpf) {
		int produtorio = 0;
		int primeiroDigitoEsperado = Character.getNumericValue(cpf.charAt(9));
		int segundoDigitoEsperado = Character.getNumericValue(cpf.charAt(10));

		for (int i = 0; i < 9; i++) {
			produtorio += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		produtorio *= 10;

		int primeiroDigito = produtorio % 11;
		if (primeiroDigito != primeiroDigitoEsperado) return false;

		produtorio = 0;
		for (int i = 0; i < 10; i++) {
			produtorio += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		produtorio *= 10;

		int segundoDigito = produtorio % 11;
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
