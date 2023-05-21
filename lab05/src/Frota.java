import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Frota {
    // Atributos
    private String code;
    private List<Veiculo> listaVeiculos;

    // Construtor
    public Frota() {
        this.code = UUID.randomUUID().toString();
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    // Getters
    public String getCode() {
        return code;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public boolean addVeiculo() {
        //TODO
        return true;
    }

    public boolean removeVeiculo() {
        //TODO
        return true;
    }
    
}
