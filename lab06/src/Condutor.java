import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Condutor {
    // Atributos
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNasc;
    private List<Sinistro> listaSinistros;

    // Construtor
    public Condutor(String cpf, String nome, String telefone, String endereco, String email, String dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        try {
			this.dataNasc = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		catch (DateTimeParseException e){
			this.dataNasc = LocalDate.MIN;
		}
        this.listaSinistros = new ArrayList<Sinistro>();
    }

    // Getters and Setters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }  
    
    // Adiciona um Sinistro associado ao Condutor
    public void adicionarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
    }

    //toString override
    @Override
    public String toString() {
        return "{\ncpf: " + cpf + ",\nnome: " + nome + ",\ntelefone: " + telefone + ",\nemail: " + email + ",\ndataNasc: "
                + dataNasc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n}";
    }
    
}
