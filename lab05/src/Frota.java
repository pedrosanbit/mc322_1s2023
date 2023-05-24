import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Frota {
    // Atributos
    private String code;
    private List<Veiculo> listaVeiculos;

    private static AtomicInteger ids = new AtomicInteger();

    // Construtor
    public Frota() {
        this.code = "" + ids.incrementAndGet();
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    // Getters
    public String getCode() {
        return code;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    @Override
    public String toString() {
        return "{\ncode: " + code + ",\nlistaVeiculos: " + listaVeiculos + "\n}";
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if (Collections.binarySearch(listaVeiculos, veiculo, (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		}) >= 0) return false;

        listaVeiculos.add(veiculo);
        Collections.sort(listaVeiculos, (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});
        return true;
    }

    public boolean removeVeiculo(String placa) {
        int index = Collections.binarySearch(listaVeiculos, new Veiculo(placa, "", "", 0), (a, b) -> {
			return a.getPlaca().compareTo(b.getPlaca());
		});
		if (index < 0 || index >= listaVeiculos.size()) return false;

		listaVeiculos.remove(index);
        return true;
    }
    
}
