package app;

import service.ProdutoService;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        ProdutoService service = new ProdutoService();
        Menu menu = new Menu(service);
        menu.iniciar();
    }
}
