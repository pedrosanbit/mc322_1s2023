public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(String dataInicio, String dataFim, Seguradora seguradora, int valorMensal, Frota frota,
            ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, valorMensal);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    public void calcularValor() {
        // TODO
    }
    
}
