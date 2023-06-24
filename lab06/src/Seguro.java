import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
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

    // Adiciona um Condutor à lista de condutores do Seguro
    public boolean autorizarCondutor(Condutor condutor) {
        if (Collections.binarySearch(listaCondutores, condutor, (a, b) -> {
			return a.getCpf().compareTo(b.getCpf());
		}) >= 0) return false;

        listaCondutores.add(condutor);
        Collections.sort(listaCondutores, (a, b) -> {
            return a.getCpf().compareTo(b.getCpf());
        });
        return true;
    }
    
    // Remove um Condutor à lista de condutores do Seguro
    public boolean desautorizarCondutor(String cpf) {
        int index = Collections.binarySearch(listaCondutores, new Condutor(cpf, "", "", "", ""), (a, b) -> {
            return a.getCpf().compareTo(b.getCpf());
        });
        if (index < 0 || index >= listaCondutores.size()) return false;

        listaCondutores.get(index).getListaSinistros().clear();
        for (int i = 0; i < listaSinistros.size(); i++) {
            if (listaSinistros.get(i).getCondutor().equals(listaCondutores.get(index))) listaSinistros.remove(i);
        }
        listaCondutores.remove(index);
        return true;
    }

    // Gera um novo Sinistro associado ao Seguro
    public boolean gerarSinistro(String data, String endereco, String cpf) {
        int index = Collections.binarySearch(listaCondutores, new Condutor(cpf, "", "", "", ""), (a, b) -> {
            return a.getCpf().compareTo(b.getCpf());
        });
        if (index < 0 || index >= listaCondutores.size()) return false;

        Sinistro sinistro = new Sinistro(data, endereco, listaCondutores.get(index), this);
        listaSinistros.add(sinistro);
        listaCondutores.get(index).adicionarSinistro(sinistro);
        return true;
    }

    public abstract double calcularValor();
}
