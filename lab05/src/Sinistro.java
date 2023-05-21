import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;

public class Sinistro {
	private final int id;
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	// Valor estático que é incrementado toda vez que uma nova instância da classe
	// é criada para que cada instância possua um id único.
	private static AtomicInteger ids = new AtomicInteger();

	public Sinistro(String data, String endereco, Condutor condutor, Seguro seguro) {
		this.id = ids.incrementAndGet();
		try {
			this.data = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		catch (DateTimeParseException e){
			this.data = LocalDate.MIN;
		}
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}
	
	// ID não pode ser setado através de métodos pois é gerado.
	public int getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	@Override
	public String toString() {
		return "{\nid: " + id + ",\ndata: " + data + ",\nendereco: " + endereco + ",\ncondutor: " + condutor + ",\nseguro: " + seguro + "\n}";
	}
	
}
