import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    public String toString() {
        return "{\nid: " + this.getId() + "\ndataInicio: " + this.getDataInicio() + "\ndataFim: " + this.getDataFim() + "\nvalorMensal: " + this.getValorMensal() + "\nfrota: " + frota + ",\ncliente: " + cliente + "\n}";
    }

    // Obtém o valor associado ao Seguro, fazendo parte da receita da Seguradora
    @Override
    public double calcularValor() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        double quantidadeFuncionarios = cliente.getQtdeFuncionarios();
        long anosPosFundacao = ChronoUnit.YEARS.between(cliente.getDataFundacao(), LocalDate.now());
        double quantidadeSinistrosCliente = this.getListaSinistros().size();
        double quantidadeVeiculos = 0;
        for (Frota frota : cliente.getListaFrota()) {
            quantidadeVeiculos = frota.getListaVeiculos().size();
        }
        double quantidadeSinistrosCondutor = 0;
        for (Condutor condutor : this.getListaCondutores()) {
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
        }

        return valorBase * (10 + (quantidadeFuncionarios/10)) * (1 + 1/(quantidadeVeiculos+2)) * (1 + 1/(anosPosFundacao+2)) * (2 + quantidadeSinistrosCliente/10) * (5 + quantidadeSinistrosCondutor/10);
    }
    
}
