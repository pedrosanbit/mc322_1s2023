import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, String dataLicenca, String educacao, String genero,
            String classeEconomica, String cnpj, String dataFundacao) throws ParseException {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        this.cnpj = cnpj;
        this.dataFundacao = DateFormat.getInstance().parse(dataFundacao);
    }

    public String getCnpj() {
        return cnpj;
    }
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

}
