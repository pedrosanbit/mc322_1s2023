public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(String dataInicio, String dataFim, Seguradora seguradora, int valorMensal, Veiculo veiculo,
            ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, valorMensal);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{\nveiculo: " + veiculo + ",\ncliente: " + cliente + "\n}";
    }

    @Override
    public void calcularValor() {
        // TODO
    }
}
