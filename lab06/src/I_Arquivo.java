import java.util.List;

public interface I_Arquivo {
    public boolean gravarArquivo(List<Object> lista) throws Exception;
    public List<Object> lerArquivo() throws Exception;
}
