package Scambo;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {

	private static ArrayList<String> nomes = new ArrayList<>();
	private static ArrayList<String> categorias = new ArrayList<>();
	private static ArrayList<String> pesos = new ArrayList<>();
	private static ArrayList<String> tamanhos = new ArrayList<>();
	private static ArrayList<Integer> quantidades = new ArrayList<>();

	public static void main(String[] args) {

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
					// case 4:
					// realizarTroca();
					// break;
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
			String nome = JOptionPane.showInputDialog(null, "Digite o nome do produto: ", "Adicionar Produto",
					JOptionPane.PLAIN_MESSAGE);
			if (nome == null || nome.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nome do produto não pode estar vazio.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String[] opcaoCategorias = { "Eletrônicos", "Moda e Acessórios", "Casa e Decoração", "Esportes",
					"Brinquedos e Jogos", "Livros e Papelaria", "Música e Instrumentos", "Automóveis e Peças",
					"Ferramentas", "Acessórios para Animais", "Outros" };
			String categoria = (String) JOptionPane.showInputDialog(null, "Escolha a categoria do produto:",
					"Adicionar Produto", JOptionPane.PLAIN_MESSAGE, null, opcaoCategorias, opcaoCategorias[0]);
			if (categoria == null) {
				JOptionPane.showMessageDialog(null, "Nenhuma categoria selecionada.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (categoria.equals("Outros")) {
				String especificacao = JOptionPane.showInputDialog(null, "Especifique a categoria:",
						"Adicionar Produto", JOptionPane.PLAIN_MESSAGE);
				if (especificacao != null && !especificacao.trim().isEmpty()) {
					categoria = especificacao;
					JOptionPane.showMessageDialog(null, "Nenhuma categoria selecionada.", "ERRO",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			String[] opcaoPesos = { "Leve", "Médio", "Pesado" };
			String peso = (String) JOptionPane.showInputDialog(null,
					"Escolha o peso do produto:\n\n" + "Leve   → até 12kg\n" + "Médio  → de 13kg a 25kg\n"
							+ "Pesado → acima de 25kg\n",
					"Adicionar Produto", JOptionPane.PLAIN_MESSAGE, null, opcaoPesos, opcaoPesos[1]);
			if (peso == null) {
				JOptionPane.showMessageDialog(null, "Nenhum peso selecionado.", "ERRO", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String[] opcaoTamanhos = { "Pequeno", "Médio", "Grande" };
			String tamanho = (String) JOptionPane.showInputDialog(null, "Escolha o tamanho do produto:\n\n",
					"Adicionar Produto", JOptionPane.PLAIN_MESSAGE, null, opcaoTamanhos, opcaoTamanhos[1]);
			if (tamanho == null) {
				JOptionPane.showMessageDialog(null, "Nenhum tamanho selecionado.", "ERRO", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String quantidadeInput = JOptionPane.showInputDialog(null, "Digite a quantidade de produtos: ",
					"Adicionar Produtos", JOptionPane.PLAIN_MESSAGE);
			if (quantidadeInput == null || quantidadeInput.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Quantidade não pode estar vazia.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int quantidade;
			try {
				quantidade = Integer.parseInt(quantidadeInput);
				if (quantidade <= 0) {
					JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que zero.", "ERRO",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Quantidade inválida. Digite apenas números.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			nomes.add(nome);
			categorias.add(categoria);
			pesos.add(peso);
			tamanhos.add(tamanho);
			quantidades.add(quantidade);

			JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void listarProdutosDetalhado() {

		if (nomes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.", "Lista de Produtos",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		StringBuilder lista = new StringBuilder();

		lista.append("=========== LISTA DE PRODUTOS ===========\n\n");

		for (int i = 0; i < nomes.size(); i++) {

			lista.append("Produto ").append(i + 1).append("\n");
			lista.append("Nome: ").append(nomes.get(i)).append("\n");
			lista.append("Categoria: ").append(categorias.get(i)).append("\n");
			lista.append("Peso: ").append(pesos.get(i)).append("\n");
			lista.append("Tamanho: ").append(tamanhos.get(i)).append("\n");
			lista.append("Quantidade: ").append(quantidades.get(i)).append("\n");

			lista.append("--------------------------");

		}

		lista.append("\n\nTotal de itens cadastrados: ").append(nomes.size());

		JTextArea areaTexto = new JTextArea(lista.toString());
		areaTexto.setEditable(false);

		JScrollPane scroll = new JScrollPane(areaTexto);
		scroll.setPreferredSize(new java.awt.Dimension(700, 400));

		JOptionPane.showMessageDialog(null, scroll, "Lista de Produtos", JOptionPane.PLAIN_MESSAGE);
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