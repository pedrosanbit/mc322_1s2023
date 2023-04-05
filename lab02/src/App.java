public class App {
    public static void main(String[] args) throws Exception {
        // Seguradora
        Seguradora seguradora = new Seguradora(
			"Porto Seguro",
			"4004-76786",
			"contato@portoseguro.com",
			"Av. Barão de Piracicaba, 618 - Campos Elíseos - São Paulo"
		);

        System.out.println(seguradora.toString());

        System.out.println();

		System.out.println(seguradora.getNome()); // Porto Seguro
		System.out.println(seguradora.getTelefone()); // 4004-76786
		System.out.println(seguradora.getEmail()); // contato@portoseguro.com
		System.out.println(seguradora.getEndereco()); // Av. Barão de Piracicaba, 618 - Campos Elíseos - São Paulo

		System.out.println();
		
		seguradora.setNome("Mapfre");
		seguradora.setTelefone("4004-0101");
		seguradora.setEmail("invest@mapfre.com.br");
		seguradora.setEndereco("R. Nuporanga, 454 - Chácara da Barra - Campinas-SP");

		System.out.println(seguradora.getNome()); // Mapfre
		System.out.println(seguradora.getTelefone()); // 4004-0101
		System.out.println(seguradora.getEmail()); // invest@mapfre.com.br
		System.out.println(seguradora.getEndereco()); // R. Nuporanga, 454 - Chácara da Barra - Campinas-SP
		
		System.out.println();

		// Cliente
		Cliente cliente = new Cliente(
			"Antônio Ferreira Pereira",
			"164.401.932-95",
			"12/12/1950",
			72,
			"Rua Frederico Moura, 557 - Vila Nova - Arapiraca-AL"
		);

        System.out.println(cliente.toString());

        System.out.println();
		
		System.out.println(cliente.getNome()); // Antônio Ferreira Pereira
		System.out.println(cliente.getCpf()); // 164.401.932-95
		System.out.println(cliente.getDataNascimento()); // 12/12/1950
		System.out.println(cliente.getIdade()); // 72
		System.out.println(cliente.getEndereco()); // Rua Frederico Moura, 557 - Vila Nova - Arapiraca-AL
		System.out.println(cliente.validarCPF() ? "CPF válido" : "CPF inválido"); // CPF válido

        System.out.println();

        cliente.setNome("Giovanna Fernandes Costa");
        cliente.setCpf("164.401.932-00");
        cliente.setDataNascimento("24/10/1990");
        cliente.setIdade(32);
        cliente.setEndereco("Rua Soares Meireles, 1067 - Rio de Janeiro, RJ");

        System.out.println(cliente.getNome()); // Giovanna Fernandes Costa
		System.out.println(cliente.getCpf()); // 164.401.932-00
		System.out.println(cliente.getDataNascimento()); // 24/10/1990
		System.out.println(cliente.getIdade()); // 32
		System.out.println(cliente.getEndereco()); // Rua Soares Meireles, 1067 - Rio de Janeiro, RJ
		System.out.println(cliente.validarCPF() ? "CPF válido" : "CPF inválido"); // CPF inválido

        System.out.println();

        // Sinistro
        Sinistro sinistro1 = new Sinistro("11/07/2022", "Rua José Morais, 1740 - Caucaia, CE");
        Sinistro sinistro2 = new Sinistro("15/10/2017","Rua Maria Karas, 915 - Araucária, PR");

        System.out.println(sinistro1.toString());

        System.out.println();

        System.out.println(sinistro1.getId()); // 1
        System.out.println(sinistro1.getData()); // 11/07/2022
        System.out.println(sinistro1.getEndereco()); // Rua José Morais, 1740 - Caucaia, CE

        System.out.println();

        System.out.println(sinistro2.getId()); // 2
        System.out.println(sinistro2.getData()); // 15/10/2017
        System.out.println(sinistro2.getEndereco()); // Rua Maria Karas, 915 - Araucária, PR

        sinistro1.setData("10/01/2021");
        sinistro1.setEndereco("Rua Matheus Gomes do Val Júnior, 281 - Franca, SP");

        System.out.println();

        System.out.println(sinistro1.getId()); // 1
        System.out.println(sinistro1.getData()); // 10/01/2021
        System.out.println(sinistro1.getEndereco()); // Rua Matheus Gomes do Val Júnior, 281 - Franca, SP

        System.out.println();

        // Veiculo
        Veiculo veiculo = new Veiculo("DMH-3855", "Walk", "Buggy Walk Sport 1.6 8V 58cv");

        System.out.println(veiculo.toString());

        System.out.println();

        System.out.println(veiculo.getPlaca()); // DMH-3855
        System.out.println(veiculo.getMarca()); // Walk
        System.out.println(veiculo.getModelo()); // Buggy Walk Sport 1.6 8V 58cv

        System.out.println();

        veiculo.setPlaca("CHZ-2927");
        veiculo.setMarca("GREAT WALL");
        veiculo.setModelo("HOVER CUV 2.4 16V 5p Mec.");

        System.out.println(veiculo.getPlaca()); // CHZ-2927
        System.out.println(veiculo.getMarca()); // GREAT WALL
        System.out.println(veiculo.getModelo()); // HOVER CUV 2.4 16V 5p Mec.
    }
}
