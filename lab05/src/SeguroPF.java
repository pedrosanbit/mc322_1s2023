import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    public double calcularValor() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now());
        double fatorIdade = idade < 30 ? CalcSeguro.FATOR_18_30.getValor() : idade > 60 ? CalcSeguro.FATOR_60_90.getValor() : CalcSeguro.FATOR_30_60.getValor();
        double quantidadeVeiculos = cliente.getListaVeiculos().size();
        double quantidadeSinistrosCliente = this.getListaSinistros().size();
        double quantidadeSinistrosCondutor = 0;
        for (Condutor condutor : this.getListaCondutores()) {
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
        }

        return valorBase * fatorIdade * (1 + 1/(quantidadeVeiculos+2)) * (2 + quantidadeSinistrosCliente/10) * (5 + quantidadeSinistrosCondutor/10);
    }
}
