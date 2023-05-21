import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Seguro {
    // Atributos
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private List<Sinistro> listaSinistros;
    private List<Condutor> listaCondutores;
    private int valorMensal;

    private static AtomicInteger ids = new AtomicInteger();

    public Seguro(String dataInicio, String dataFim, Seguradora seguradora, int valorMensal) {
        this.id = ids.incrementAndGet();
        try {
			this.dataInicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		catch (DateTimeParseException e){
			this.dataInicio = LocalDate.MIN;
		}
        try {
			this.dataFim = LocalDate.parse(dataFim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		catch (DateTimeParseException e){
			this.dataFim = LocalDate.MAX;
		}
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
        this.valorMensal = valorMensal;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public int getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public void autorizarCondutor() {
        // TODO
    }
    
    public void desautorizarCondutor() {
        // TODO
    }

    public void gerarSinistro() {
        // TODO
    }

    public abstract void calcularValor();
}
