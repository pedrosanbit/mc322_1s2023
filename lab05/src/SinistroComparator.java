import java.util.Comparator;

public class SinistroComparator implements Comparator<Sinistro> {

    @Override
    public int compare(Sinistro sinistroA, Sinistro sinistroB) {
        Cliente a = sinistroA.getCliente();
        Cliente b = sinistroB.getCliente();
        return new ClienteComparator().compare(a, b);
    }
    
}
