package ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Produto;
import service.ProdutoService;

public class Menu {

    // ─── Dependência injetada ────────────────────────────────────────────────────
    private final ProdutoService service;

    public Menu(ProdutoService service) {
        this.service = service;
    }

    // ─── Loop principal ──────────────────────────────────────────────────────────

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            try {
                String input = JOptionPane.showInputDialog(null,
                        "---Scambo2U---\n\n"
                        + "1- Adicionar Produto\n"
                        + "2- Listar meus Produtos\n"
                        + "3- Buscar Produto por Nome\n"
                        + "4- Buscar Produto por Filtros\n"
                        + "5- Remover Produto\n"
                        + "0- Sair do programa\n\n"
                        + "Escolha sua opção",
                        "Scambo2U", JOptionPane.PLAIN_MESSAGE);

                if (input == null) {
                    opcao = 0;
                } else {
                    opcao = Integer.parseInt(input.trim());

                    switch (opcao) {
                        case 1 -> adicionarProduto();
                        case 2 -> listarProdutos();
                        case 3 -> buscarProdutoPorNome();
                        case 4 -> buscarProdutoPorFiltros();
                        case 5 -> removerProduto();
                        case 0 -> JOptionPane.showMessageDialog(null, "Saindo do Sistema. Até mais...");
                        default -> mostrarErro("Opção Inválida! Por favor, tente novamente.");
                    }
                }

            } catch (NumberFormatException e) {
                mostrarErro("Erro: Entrada inválida. Por favor, digite um número.");
                opcao = -1;
            }
        }
    }

    // ─── Adicionar Produto ─────────────────────────────────────────────────

    private void adicionarProduto() {
        try {
            String nome = JOptionPane.showInputDialog(null,
                    "Digite o nome do produto:", "Adicionar Produto", JOptionPane.PLAIN_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {
                mostrarErro("Nome do produto não pode estar vazio.");
                return;
            }

            String categoria = selecionarCategoria();
            if (categoria == null) return;

            String[] opcoesPeso = { "Leve", "Médio", "Pesado" };
            String peso = (String) JOptionPane.showInputDialog(null,
                    "Escolha o peso do produto:\n\n"
                    + "Leve   → até 12kg\n"
                    + "Médio  → de 13kg a 25kg\n"
                    + "Pesado → acima de 25kg",
                    "Adicionar Produto", JOptionPane.PLAIN_MESSAGE,
                    null, opcoesPeso, opcoesPeso[1]);
            if (peso == null) { mostrarErro("Nenhum peso selecionado."); return; }

            String[] opcoesTamanho = { "Pequeno", "Médio", "Grande" };
            String tamanho = (String) JOptionPane.showInputDialog(null,
                    "Escolha o tamanho do produto:",
                    "Adicionar Produto", JOptionPane.PLAIN_MESSAGE,
                    null, opcoesTamanho, opcoesTamanho[1]);
            if (tamanho == null) { mostrarErro("Nenhum tamanho selecionado."); return; }

            int quantidade = lerQuantidade();
            if (quantidade == -1) return;

            Produto novoProduto = new Produto(nome.trim(), categoria, peso, tamanho, quantidade);
            service.adicionar(novoProduto);

            JOptionPane.showMessageDialog(null,
                    "Produto adicionado com sucesso!\n\n" + novoProduto,
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            mostrarErro("Erro inesperado: " + e.getMessage());
        }
    }

    // ─── Listar Produtos ─────────────────────────────────

    private void listarProdutos() {
        if (service.estaVazio()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.",
                    "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=========== LISTA DE PRODUTOS ===========\n\n");
        List<Produto> lista = service.listarTodos();

        for (int i = 0; i < lista.size(); i++) {
            sb.append("Produto ").append(i + 1).append("\n");
            sb.append(lista.get(i)).append("\n");
            sb.append("--------------------------\n");
        }

        sb.append("\nTotal de itens cadastrados: ").append(service.totalCadastrados());

        JTextArea areaTexto = new JTextArea(sb.toString());
        areaTexto.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(700, 400));

        JOptionPane.showMessageDialog(null, scroll, "Lista de Produtos", JOptionPane.PLAIN_MESSAGE);
    }

    // ─── Buscar por Nome ───────────────────────────────────────────────────

    private void buscarProdutoPorNome() {
        if (service.estaVazio()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto para buscar.",
                    "Buscar Produto", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nomeProcurado = JOptionPane.showInputDialog(null,
                "Digite o nome do produto para buscar:",
                "Buscar Produto por Nome", JOptionPane.PLAIN_MESSAGE);

        if (nomeProcurado == null || nomeProcurado.trim().isEmpty()) {
            mostrarErro("Nome para busca não pode estar vazio.");
            return;
        }

        Produto encontrado = service.buscarPorNome(nomeProcurado.trim());

        if (encontrado != null) {
            JOptionPane.showMessageDialog(null,
                    "Produto encontrado!\n\n" + encontrado,
                    "Resultado da Busca", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Produto '" + nomeProcurado + "' não encontrado.",
                    "Resultado da Busca", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ─── Buscar por Filtros ────────────────────────────────────────────────

    private void buscarProdutoPorFiltros() {
        if (service.estaVazio()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto para buscar.",
                    "Buscar por Filtros", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Filtro: categoria
        String[] opcoesCategoria = {
            "Qualquer", "Eletrônicos", "Moda e Acessórios", "Casa e Decoração", "Esportes",
            "Brinquedos e Jogos", "Livros e Papelaria", "Música e Instrumentos",
            "Automóveis e Peças", "Ferramentas", "Acessórios para Animais", "Outros"
        };
        String categoriaFiltro = (String) JOptionPane.showInputDialog(null,
                "Filtrar por categoria:\n(escolha 'Qualquer' para não filtrar)",
                "Buscar por Filtros", JOptionPane.PLAIN_MESSAGE,
                null, opcoesCategoria, opcoesCategoria[0]);
        if (categoriaFiltro == null) return;

        // Filtro: peso 
        String[] opcoesPeso = { "Qualquer", "Leve", "Médio", "Pesado" };
        String pesoFiltro = (String) JOptionPane.showInputDialog(null,
                "Filtrar por peso:\n(escolha 'Qualquer' para não filtrar)",
                "Buscar por Filtros", JOptionPane.PLAIN_MESSAGE,
                null, opcoesPeso, opcoesPeso[0]);
        if (pesoFiltro == null) return;

        // Filtro: tamanho 
        String[] opcoesTamanho = { "Qualquer", "Pequeno", "Médio", "Grande" };
        String tamanhoFiltro = (String) JOptionPane.showInputDialog(null,
                "Filtrar por tamanho:\n(escolha 'Qualquer' para não filtrar)",
                "Buscar por Filtros", JOptionPane.PLAIN_MESSAGE,
                null, opcoesTamanho, opcoesTamanho[0]);
        if (tamanhoFiltro == null) return;

        // Converte "Qualquer" para null para o service ignorar o filtro
        String categoria = categoriaFiltro.equals("Qualquer") ? null : categoriaFiltro;
        String peso      = pesoFiltro.equals("Qualquer")      ? null : pesoFiltro;
        String tamanho   = tamanhoFiltro.equals("Qualquer")   ? null : tamanhoFiltro;

        List<Produto> resultado = service.buscarPorFiltros(categoria, peso, tamanho);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum produto encontrado com os filtros selecionados.",
                    "Resultado da Busca", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== Produtos Encontrados ===\n\n");
        for (int i = 0; i < resultado.size(); i++) {
            sb.append("Produto ").append(i + 1).append("\n");
            sb.append(resultado.get(i)).append("\n");
            sb.append("--------------------------\n");
        }
        sb.append("\nTotal encontrado: ").append(resultado.size()).append(" produto(s)");

        JTextArea areaTexto = new JTextArea(sb.toString());
        areaTexto.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(700, 400));

        JOptionPane.showMessageDialog(null, scroll, "Resultado da Busca", JOptionPane.PLAIN_MESSAGE);
    }

    // ─── Remover Produto ───────────────────────────────────────────────────

    private void removerProduto() {
        if (service.estaVazio()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto para remover.",
                    "Remover Produto", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nomeParaRemover = JOptionPane.showInputDialog(null,
                "Digite o nome do produto para remover:",
                "Remover Produto", JOptionPane.PLAIN_MESSAGE);

        if (nomeParaRemover == null || nomeParaRemover.trim().isEmpty()) {
            mostrarErro("Nome para remoção não pode estar vazio.");
            return;
        }

        boolean removido = service.removerPorNome(nomeParaRemover.trim());

        if (removido) {
            JOptionPane.showMessageDialog(null,
                    "Produto '" + nomeParaRemover + "' removido com sucesso.",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarErro("Produto '" + nomeParaRemover + "' não encontrado.");
        }
    }

    // ─── Helpers privados ────────────────────────────────────────────────────────

    private String selecionarCategoria() {
        String[] opcoes = {
            "Eletrônicos", "Moda e Acessórios", "Casa e Decoração", "Esportes",
            "Brinquedos e Jogos", "Livros e Papelaria", "Música e Instrumentos",
            "Automóveis e Peças", "Ferramentas", "Acessórios para Animais", "Outros"
        };

        String categoria = (String) JOptionPane.showInputDialog(null,
                "Escolha a categoria do produto:", "Adicionar Produto",
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (categoria == null) {
            mostrarErro("Nenhuma categoria selecionada.");
            return null;
        }

        if (categoria.equals("Outros")) {
            String especificacao = JOptionPane.showInputDialog(null,
                    "Especifique a categoria:", "Adicionar Produto", JOptionPane.PLAIN_MESSAGE);
            if (especificacao == null || especificacao.trim().isEmpty()) {
                mostrarErro("Especificação de categoria não pode estar vazia.");
                return null;
            }
            return especificacao.trim();
        }

        return categoria;
    }

    private int lerQuantidade() {
        String input = JOptionPane.showInputDialog(null,
                "Digite a quantidade de produtos:", "Adicionar Produto", JOptionPane.PLAIN_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            mostrarErro("Quantidade não pode estar vazia.");
            return -1;
        }

        try {
            int quantidade = Integer.parseInt(input.trim());
            if (quantidade <= 0) {
                mostrarErro("Quantidade deve ser maior que zero.");
                return -1;
            }
            return quantidade;
        } catch (NumberFormatException e) {
            mostrarErro("Quantidade inválida. Digite apenas números.");
            return -1;
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}
