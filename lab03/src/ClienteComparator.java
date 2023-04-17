import java.util.Comparator;

public class ClienteComparator implements Comparator<Cliente> {
    @Override
    public int compare(Cliente a, Cliente b) {
        if (a instanceof ClientePF && b instanceof ClientePF) {
            ClientePF pfA = (ClientePF) a;
            ClientePF pfB = (ClientePF) b;
            return pfA.getCpf().compareTo(pfB.getCpf());
        }
        else if (a instanceof ClientePF && b instanceof ClientePJ) {
            ClientePF pfA = (ClientePF) a;
            ClientePJ pjB = (ClientePJ) b;
            return pfA.getCpf().compareTo(pjB.getCnpj());
        }
        else if (a instanceof ClientePJ && b instanceof ClientePF) {
            ClientePJ pjA = (ClientePJ) a;
            ClientePF pfB = (ClientePF) b;
            return pjA.getCnpj().compareTo(pfB.getCpf());
        }
        else if (a instanceof ClientePJ && b instanceof ClientePJ) {
            ClientePJ pjA = (ClientePJ) a;
            ClientePJ pjB = (ClientePJ) b;
            return pjA.getCnpj().compareTo(pjB.getCnpj());
        }
        return 0;
    } 
}
