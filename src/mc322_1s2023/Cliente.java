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
		// TODO
		return false;
	}
	
	public boolean validarCPF() {
		String numbersOnlyCPF = this.cpf.replaceAll("[^0-9]", "");
		if (numbersOnlyCPF.length() != 11) return false;
		if (temDigitosIguais(numbersOnlyCPF)) return false;
	}
	
}
