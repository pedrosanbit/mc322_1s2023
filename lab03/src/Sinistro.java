import java.util.concurrent.atomic.AtomicInteger;

public class Sinistro {
	private final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	// Valor estático que é incrementado toda vez que uma nova instância da classe
	// é criada para que cada instância possua um id único.
	private static AtomicInteger ids = new AtomicInteger();

	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		this.id = ids.incrementAndGet();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
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

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "{\nid: " + id + ",\ndata: " + data + ",\nendereco: " + endereco + ",\nseguradora: " + seguradora.toString() + ",\nveiculo: " + veiculo.toString() + ",\ncliente: " + cliente.toString() + "\n}";
	}
	
}
