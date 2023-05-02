public enum MenuOperacoes {
    CADASTRAR(1,0),
    CADASTRAR_CLIENTE(1,1),
    CADASTRAR_VEICULO(1,2),
    CADASTRAR_SEGURADORA(1,3),
    VOLTAR_CADASTRAR(1,4),
    LISTAR(2,0),
    LISTAR_CLIENTE_POR_SEGURADORA(2,1),
    LISTAR_SINISTROS_POR_SEGURADORA(2,2),
    LISTAR_SINISTROS_POR_CLIENTE(2,3),
    LISTAR_VEICULOS_POR_CLIENTE(2,4),
    LISTAR_VEICULOS_POR_SEGURADORA(2,5),
    VOLTAR_LISTAR(2,6),
    EXCLUIR(3,0),
    EXCLUIR_CLIENTE(3,1),
    EXCLUIR_VEICULO(3,2),
    EXCLUIR_SINISTRO(3,3),
    VOLTAR_EXCLUIR(3,4),
    GERAR_SINISTRO(4,0),
    TRANSFERIR_SEGURO(5,0),
    CALCULAR_RECEITA_SEGURADORA(6,0),
    SAIR(0,0);

    public final int operacaoPrimaria;
    public final int operacaoSecundaria;

    MenuOperacoes(int operacaoPrimaria, int operacaoSecundaria) {
        this.operacaoPrimaria = operacaoPrimaria;
        this.operacaoSecundaria = operacaoSecundaria;
    }

    public int getOperacaoPrimaria() {
        return operacaoPrimaria;
    }

    public int getOperacaoSecundaria() {
        return operacaoSecundaria;
    }
    
}
