package mc322_1s2023;

public class Main {

	public static void main(String[] args) {
		Seguradora seguradora = new Seguradora(
			"Porto Seguro",
			"4004-76786",
			"contato@portoseguro.com",
			"Av. Barão de Piracicaba, 618 - Campos Elíseos - São Paulo"
		);

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
		
		Cliente cliente = new Cliente(
			"Antônio Ferreira Pereira",
			"164.401.932-95",
			"12/12/1950",
			72,
			"Rua Frederico Moura, 557 - Vila Nova - Arapiraca-AL"
		);
		
		System.out.println(cliente.getNome()); // Antônio Ferreira Pereira
		System.out.println(cliente.getCpf()); // 164.401.932-95
		System.out.println(cliente.getDataNascimento()); // 12/12/1950
		System.out.println(cliente.getIdade()); // 72
		System.out.println(cliente.getEndereco()); // Rua Frederico Moura, 557 - Vila Nova - Arapiraca-AL

		System.out.println(cliente.validarCPF() ? "CPF válido" : "CPF inválido");
	}

}
