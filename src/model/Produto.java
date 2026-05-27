package model;

public class Produto {

    // ─── Atributos ───────────────────────────────────────────────────────────────
    private String nome;
    private String categoria;
    private String peso;
    private String tamanho;
    private int quantidade;

    // ─── Construtores ────────────────────────────────────────────────────────────

    public Produto() {}

    public Produto(String nome, String categoria, String peso, String tamanho, int quantidade) {
        this.nome       = nome;
        this.categoria  = categoria;
        this.peso       = peso;
        this.tamanho    = tamanho;
        this.quantidade = quantidade;
    }

    // ─── Getters e Setters ───────────────────────────────────────────────────────

    public String getNome()                  { return nome; }
    public void   setNome(String nome)       { this.nome = nome; }

    public String getCategoria()                     { return categoria; }
    public void   setCategoria(String categoria)     { this.categoria = categoria; }

    public String getPeso()                  { return peso; }
    public void   setPeso(String peso)       { this.peso = peso; }

    public String getTamanho()                   { return tamanho; }
    public void   setTamanho(String tamanho)     { this.tamanho = tamanho; }

    public int  getQuantidade()               { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    // ─── toString ────────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Nome: "       + nome       + "\n"
             + "Categoria: "  + categoria  + "\n"
             + "Peso: "       + peso       + "\n"
             + "Tamanho: "    + tamanho    + "\n"
             + "Quantidade: " + quantidade;
    }
}
