package informatorio;

import informatorio.service.menu.MenuService;
import informatorio.service.menu.MenuServiceImplement;

public class App {
    public static void main(String[] args) {
        MenuService menu = new MenuServiceImplement();
        menu.mostrarMenu();
    }
}
