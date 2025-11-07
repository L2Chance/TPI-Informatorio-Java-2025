package informatorio.service.menu;

import informatorio.service.experimento.ExperimentoService;
import informatorio.service.experimento.ExperimentoServiceImplement;
import informatorio.service.investigador.InvestigadorService;
import informatorio.service.investigador.InvestigadorServiceImplement;

import java.util.Scanner;

public class MenuServiceImplement implements MenuService {

    private final Scanner sc = new Scanner(System.in);
    private final InvestigadorService investigadorService = new InvestigadorServiceImplement();
    private final ExperimentoService experimentoService = new ExperimentoServiceImplement(investigadorService);

    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== Laboratorio Chad ===");
            System.out.println("1. Registrar investigador");
            System.out.println("2. Registrar experimento químico");
            System.out.println("3. Registrar experimento físico");
            System.out.println("4. Mostrar experimentos");
            System.out.println("5. Mostrar estadísticas");
            System.out.println("6. Exportar investigadores a CSV");
            System.out.println("7. Mostrar investigador con más experimentos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    //Registrar investigadores
                    investigadorService.menuRegistrarInvestigador(sc);
                    break;
                case 2:
                    //Registrar experimento químico asociado a investigador
                    experimentoService.menuRegistrarQuimico(sc);
                    break;
                case 3:
                    //Registrar experimento físico asociado a investigadores
                    experimentoService.menuRegistrarFisico(sc);
                    break;
                case 4:
                    //Mostrar listado de experimentos con tipo y resultado
                    experimentoService.mostrarExperimentos();
                    break;
                case 5:
                    //Mostrar total de experimentos exitosos/fallidos, experimento más largo, promedio de duración y porcentaje de éxito
                    experimentoService.mostrarEstadisticas();
                    break;
                case 6:
                    //Exportar investigadores a CSV
                    investigadorService.menuExportarCSV(sc);
                    break;
                case 7:
                    //Mostrar investigador con más experimentos
                    investigadorService.menuMostrarInvestigadorTop();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }
}

