import java.util.Comparator;

public class ClienteComparator implements Comparator<Cliente> {
    @Override
    public int compare(Cliente a, Cliente b) {
        return a.getCodigo().compareTo(b.getCodigo());
    } 
}
