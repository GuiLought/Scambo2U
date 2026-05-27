package service;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    // ─── Repositório em memória ──────────────────────────────────────────────────
    private final List<Produto> produtos = new ArrayList<>();

    // ─── Adicionar ───────────────────────────────────────────────────────────────

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    // ─── Listar todos ────────────────────────────────────────────────────────────

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public boolean estaVazio() {
        return produtos.isEmpty();
    }

    public int totalCadastrados() {
        return produtos.size();
    }

    // ─── Buscar por nome ─────────────────────────────────────────────────────────

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    // ─── Buscar por filtros ──────────────────────────────────────────────────────

    /**
     * Retorna todos os produtos que correspondem aos filtros informados.
     * Passe null em qualquer parâmetro para ignorar aquele filtro.
     */
    public List<Produto> buscarPorFiltros(String categoria, String peso, String tamanho) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            boolean matchCategoria = (categoria == null) || p.getCategoria().equalsIgnoreCase(categoria);
            boolean matchPeso      = (peso      == null) || p.getPeso().equalsIgnoreCase(peso);
            boolean matchTamanho   = (tamanho   == null) || p.getTamanho().equalsIgnoreCase(tamanho);

            if (matchCategoria && matchPeso && matchTamanho) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // ─── Remover por nome ────────────────────────────────────────────────────────

    public boolean removerPorNome(String nome) {
        Produto encontrado = buscarPorNome(nome);
        if (encontrado != null) {
            produtos.remove(encontrado);
            return true;
        }
        return false;
    }
}
