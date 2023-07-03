import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AppMain {
    public static void main(String[] args) {
        Seguradora seguradora = new Seguradora("14.607.268/0001-09", "Caroline e Lucas Seguros Ltda", "(19) 3646-1282", "Rua Berto Piccolo, 765 - Campinas, SP", "contato@carolineelucassegurosltda.com.br");
        try {
            List<Object> veiculos = seguradora.getArquivoVeiculo().lerArquivo();
            List<Object> clientesPF = seguradora.getArquivoClientePF().lerArquivo();
            for (Object obj : clientesPF) {
                ClientePF clientePF = (ClientePF) obj;
                Veiculo veiculo = (Veiculo) veiculos.stream().filter(v -> ((Veiculo) v).getPlaca().equals(clientePF.getListaVeiculos().get(0).getPlaca())).findFirst().orElse(null);
                clientePF.getListaVeiculos().remove(0);
                clientePF.getListaVeiculos().add(veiculo);
            }
            List<Object> frotas = seguradora.getArquivoFrota().lerArquivo();
            for (Object obj : frotas) {
                Frota frota = (Frota) obj;
                Veiculo veiculo1 = (Veiculo) veiculos.stream().filter(v -> ((Veiculo) v).getPlaca().equals(frota.getListaVeiculos().get(0).getPlaca())).findFirst().orElse(null);
                Veiculo veiculo2 = (Veiculo) veiculos.stream().filter(v -> ((Veiculo) v).getPlaca().equals(frota.getListaVeiculos().get(1).getPlaca())).findFirst().orElse(null);
                Veiculo veiculo3 = (Veiculo) veiculos.stream().filter(v -> ((Veiculo) v).getPlaca().equals(frota.getListaVeiculos().get(2).getPlaca())).findFirst().orElse(null);
                frota.getListaVeiculos().remove(0);
                frota.getListaVeiculos().remove(0);
                frota.getListaVeiculos().remove(0);
                frota.addVeiculo(veiculo1);
                frota.addVeiculo(veiculo2);
                frota.addVeiculo(veiculo3);
            }
            List<Object> clientesPJ = seguradora.getArquivoClientePJ().lerArquivo();
            for (Object obj : clientesPJ) {
                ClientePJ clientePJ = (ClientePJ) obj;
                Frota frota = (Frota) frotas.stream().filter(f -> ((Frota) f).getCode().equals(clientePJ.getListaFrota().get(0).getCode())).findFirst().orElse(null);
                clientePJ.getListaFrota().remove(0);
                clientePJ.getListaFrota().add(frota);
            }
            for (Object obj: clientesPF) seguradora.getListaClientes().add((ClientePF) obj);
            for (Object obj: clientesPJ) seguradora.getListaClientes().add((ClientePJ) obj);
            seguradora.getListaClientes().sort(new ClienteComparator());

            seguradora.calcularReceita();
            List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
            listaSeguradoras.add(seguradora);
        
            MenuOperacoes.menuOperacoes(listaSeguradoras);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado.");
        }
        catch (IOException ex) {
            System.out.println("Erro de Entrada e Saída.");
        }
        catch (NoSuchElementException ex) {
            System.out.println("Não foi possível seguir com a leitura.");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("Acesso de índice indevido.");
        }
        catch (Exception ex) {
            System.out.println("Ocorreu um erro. Tente novamente.");
        }
    }
}
