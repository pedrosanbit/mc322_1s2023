import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClientePJ extends Cliente {
    //Atributos
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    //Construtor
    public ClientePJ(String nome, String endereco, String cnpj, String dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.qtdeFuncionarios = qtdeFuncionarios;
        try {
            this.dataFundacao = LocalDate.parse(dataFundacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        catch (DateTimeParseException e) {
            this.dataFundacao = LocalDate.now();
        }
    }

    //Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    //toString override
    @Override
    public String toString() {
        return "{\nnome: "+ this.getNome() + ",\nendereco: " + this.getEndereco() + ",\ncnpj: " + cnpj + ",\ndataFundacao: " + dataFundacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n}";
    }

    //Valida se os digitos verificadores de um CNPJ estão corretos
    private static boolean validarDigitosVerificadores(String cnpj) {
        int somatorio = 0;
        int primeiroDigitoEsperado = Character.getNumericValue(cnpj.charAt(12));
        int segundoDigitoEsperado = Character.getNumericValue(cnpj.charAt(13));
        int[] fatores1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] fatores2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        for (int i = 0; i < 12; i++) somatorio += Character.getNumericValue(cnpj.charAt(i)) * fatores1[i];
        int primeiroDigito = somatorio % 11 < 2 ? 0 : 11 - (somatorio % 11);
        if(primeiroDigito != primeiroDigitoEsperado) return false;
        
        somatorio = 0;
        for (int i = 0; i < 13; i++) somatorio += Character.getNumericValue(cnpj.charAt(i)) * fatores2[i];
        int segundoDigito = somatorio % 11 < 2 ? 0 : 11 - (somatorio % 11);
        if(segundoDigito != segundoDigitoEsperado) return false;

        return true;
    }

    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValor() * (1 + qtdeFuncionarios/100) * this.getListaVeiculos().size();
    }

    public static class Validacao {
        private static boolean validarDigitosVerificadores(String codigo) {
            int somatorio = 0;
            int primeiroDigitoEsperado = Character.getNumericValue(codigo.charAt(12));
            int segundoDigitoEsperado = Character.getNumericValue(codigo.charAt(13));
            int[] fatores1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
            int[] fatores2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    
            for (int i = 0; i < 12; i++) somatorio += Character.getNumericValue(codigo.charAt(i)) * fatores1[i];
            int primeiroDigito = somatorio % 11 < 2 ? 0 : 11 - (somatorio % 11);
            if(primeiroDigito != primeiroDigitoEsperado) return false;
            
            somatorio = 0;
            for (int i = 0; i < 13; i++) somatorio += Character.getNumericValue(codigo.charAt(i)) * fatores2[i];
            int segundoDigito = somatorio % 11 < 2 ? 0 : 11 - (somatorio % 11);
            if(segundoDigito != segundoDigitoEsperado) return false;
    
            return true;
        }
    
        //Valida o CNPJ da Pessoa Jurídica
        public boolean validarCodigo(String codigo) {
            String numbersOnlyCNPJ = codigo.replaceAll("[^0-9]", "");
            if (numbersOnlyCNPJ.length() != 14) return false;
            if (!validarDigitosVerificadores(numbersOnlyCNPJ)) return false;
            return true;
        }
    }

}
