package mc322_1s2023;

import java.util.concurrent.atomic.AtomicInteger;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	// Valor estático que é incrementado toda vez que uma nova instância da classe
	// é criada para que cada instância possua um id único.
	static private AtomicInteger ids = new AtomicInteger();
	
	public Sinistro(String data, String endereco) {
		this.id = ids.incrementAndGet();
		this.data = data;
		this.endereco = endereco;
	}

	// ID não pode ser setado através de métodos pois é gerado.
	public int getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
