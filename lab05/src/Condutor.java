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
    private String email;
    private LocalDate dataNasc;
    private List<Sinistro> listaSinistros;

    // Construtor
    public Condutor(String cpf, String nome, String telefone, String email, String dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        try {
			this.dataNasc = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
    
    public void adicionarSinistro() {
        //TODO
    }
}
