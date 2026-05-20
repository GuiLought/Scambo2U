package Scambo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	private static ArrayList<String> nomes = new ArrayList<>();
	private static ArrayList<String> categorias = new ArrayList<>();
	private static ArrayList<Double> pesos = new ArrayList<>();
	private static ArrayList<Double> tamanhos = new ArrayList<>();
	private static ArrayList<Integer> quantidades = new ArrayList<>();

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		String input; // Essa variável irá armazenar a entrada do usuário.
		int opcao = -1;

		while (opcao != 0) {

			try {

				// Exibe o menu de opções dentro de uma caixa de diálogo.
				input = JOptionPane.showInputDialog(null,
						"---Scambo2U---\n\n" + "1- Adicionar Produto\n" + "2- Listar meus Produtos\n"
								+ "3- Buscar Produto por Filtros\n" + "4- Realizar troca\n" + "5- Remover Produto\n"
								+ "0- Sair do programa\n\n" + "Escolha sua opção",
						"Scambo2U", JOptionPane.PLAIN_MESSAGE);

				// Se o usuário clicar em "cancelar" ou fechar a janela o 'input' fica nulo.
				if (input == null) {

					opcao = 0;

				} else {

					opcao = Integer.parseInt(input);

					switch (opcao) {

					case 1:
						adicionarProduto();
						break;

					case 2:
						listarProdutosDetalhado();
						break;

					case 3:
						buscarProduto();
						break;

					case 4:
						realizarTroca();
						break;

					case 5:
						removerProdutoPorNome();
						break;

					case 0:
						JOptionPane.showMessageDialog(null, "Saindo do Sistema. Até mais...");
						break;

					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida! Por favor, tente novamente.", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Por favor, digite um número", "ERRO",
						JOptionPane.ERROR_MESSAGE);

				opcao = -1;
			}
		}
	}

	// Método para adicionar um produto no sistema.
	private static void adicionarProduto() {

		try {

			// Solicita o nome do produto
			String nome = JOptionPane.showInputDialog(null, "Digite o nome do produto: ", "Adicionar Produto",
					JOptionPane.PLAIN_MESSAGE);

			if (nome == null || nome.trim().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Nome do produto não pode estar vazio.", "ERRO",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			// Solicita a categoria do produto
			String categoria = JOptionPane.showInputDialog(null, "Escolha a categoria do produto: ",
					"Adicionar Produto", JOptionPane.PLAIN_MESSAGE);

			switch (opcaoCategoria) {

			case 1:
				categoria = "Eletrônicos";
				break;

			case 2:
				categoria = "Moda e Acessórios";
				break;

			case 3:
				categoria = "Casa e Decoração";
				break;

			case 4:
				categoria = "Esportes";
				break;

			case 5:
				categoria = "Brinquedos e Jogos";
				break;

			case 6:
				categoria = "Livros e Papelaria";
				break;

			case 7:
				categoria = "Música e Instrumentos";
				break;

			case 8:
				categoria = "Automóveis e Peças";
				break;

			case "9":
				categoria = "Ferramentas";
				break;

			case "10":
				categoria = "Acessórios para Animais";
				break;

			case "11":
				categoria = "Outros";
				System.out.println("Categoria 'Outros' selecionada. Por favor, especifique a categoria do produto.");
				break;

			default:
				JOptionPane.showMessageDialog(null, "Categoria inválida. Por favor, escolha uma categoria válida.",
						"ERRO", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Solicita o peso do produto.
			String pesoInput = JOptionPane.showInputDialog(null, "Digite o peso do produto: ", "Adicionar Produto",
					JOptionPane.PLAIN_MESSAGE);

			double peso = Double.parseDouble(pesoInput);

			if (peso <= 0) {

				JOptionPane.showMessageDialog(null, "Peso do produto deve ser um valor positivo.", "ERRO",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			if (pesoInput == null || pesoInput.trim().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Peso do produto não pode estar vazio.", "ERRO",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			// Solicita o tamanho do produto.
			String tamanhoInput = JOptionPane.showInputDialog(null, "Digite o tamanho do produto: ",
					"Adicionar Produto", JOptionPane.PLAIN_MESSAGE);

			double tamanho = Double.parseDouble(tamanhoInput);

			if (tamanho <= 0) {

				JOptionPane.showMessageDialog(null, "Tamanho do produto deve ser um valor positivo.", "ERRO",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			if (tamanhoInput == null || tamanhoInput.trim().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Tamanho do produto não pode estar vazio.", "ERRO",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			// Solicita a quantidade do produto.
			String quantidadeInput = JOptionPane.showInputDialog(null, "Digite a quantidade de produtos: ",
					"Adicionar Produtos", JOptionPane.PLAIN_MESSAGE);

			int quantidade = Integer.parseInt(quantidadeInput);

			// Adiciona os dados nas listas
			nomes.add(nome);
			categorias.add(categoria);
			pesos.add(peso);
			tamanhos.add(tamanho);
			quantidades.add(quantidade);

			JOptionPane.showMessageDialog(null, "Produto: " + nome + " adicionado com sucesso!!!", "SUCESSO",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(null,
					"Erro: Preço ou quantidade inválidos. Por favor, digite somente números.", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void listarProdutosDetalhado() {

		if (nomes.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.", "Lista de Produtos",
					JOptionPane.INFORMATION_MESSAGE);

			return;
		}

		StringBuilder lista = new StringBuilder("---Produtos em Estoque---\n\n");
		double valorTotalEstoque = 0.0;

		// Itera sobre as listas para construir a string de exibição.
		for (int i = 0; i < nomes.size(); i++) {

			lista.append("Nome: ").append(nomes.get(i)).append(" | Preço: R$ ")
					.append(String.format("%.2f", peso.get(i))).append("| Estoque: ").append(quantidades.get(i))
					.append("\n");

			// Acumula o valor total do estoque.
			valorTotalEstoque += peso.get(i) * quantidades.get(i);
		}

		// Adiciona o total ao final da mensagem
		lista.append("\nTotal itens cadastrados: ").append(nomes.size());
		lista.append("\nValor total do estoque: ").append(String.format("%.2f", valorTotalEstoque));

		JOptionPane.showMessageDialog(null, lista.toString(), "Lista de Produtos", JOptionPane.PLAIN_MESSAGE);
	}

	private static void buscarProduto() {

		if (nomes.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Nenhum produto para buscar.", "Lista de Produtos",
					JOptionPane.INFORMATION_MESSAGE);

			return;
		}

		String nomeProcurado = JOptionPane.showInputDialog(null, "Digite o nome do produto para buscar.",
				"Buscar Produto", JOptionPane.PLAIN_MESSAGE);

		if (nomeProcurado == null || nomeProcurado.trim().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Nome para procurar não pode estar vazio.", "ERRO",
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		int indiceEncontrado = -1;

		for (int i = 0; i < nomes.size(); i++) {

			if (nomes.get(i).equalsIgnoreCase(nomeProcurado)) {

				indiceEncontrado = i;
				break;
			}
		}

		if (indiceEncontrado != -1) {

			String mensagem = "Produto encontrado: \n\n" + "Nome: " + nomes.get(indiceEncontrado) + "\nPreço: R$ "
					+ String.format("%.2f", peso.get(indiceEncontrado)) + "\nEstoque: "
					+ quantidades.get(indiceEncontrado);

			JOptionPane.showMessageDialog(null, mensagem, "Resultado da Busca", JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null, "Produto ' " + nomeProcurado + " ' não encontrado. ", "Resultado Busca",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private static void removerProdutoPorNome() {

		if (nomes.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Nenhum produto para remover.", "Remover Produto",
					JOptionPane.INFORMATION_MESSAGE);

			return;
		}

		String nomeParaRemover = JOptionPane.showInputDialog(null, "Digite o nome do produto para remover: ",
				"Remover Produto", JOptionPane.PLAIN_MESSAGE);

		if (nomeParaRemover == null || nomeParaRemover.trim().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Nome para remoção não pode ser vazio.", "ERRO",
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		int indiceParaRemover = -1;

		for (int i = 0; i < nomes.size(); i++) {

			if (nomes.get(i).equalsIgnoreCase(nomeParaRemover)) {

				indiceParaRemover = i;
				break;
			}
		}

		if (indiceParaRemover != -1) {

			nomes.remove(indiceParaRemover);
			pesos.remove(indiceParaRemover);
			quantidades.remove(indiceParaRemover);

			JOptionPane.showMessageDialog(null, "Produto ' " + nomeParaRemover + " ' removido com sucesso.", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null, "Produto ' " + nomeParaRemover + " ' não encontrado.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}