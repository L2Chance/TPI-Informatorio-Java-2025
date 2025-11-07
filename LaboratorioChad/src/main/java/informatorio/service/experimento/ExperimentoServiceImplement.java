package informatorio.service.experimento;

import informatorio.domain.Experimento;
import informatorio.domain.ExperimentoFisico;
import informatorio.domain.ExperimentoQuimico;
import informatorio.domain.Investigador;
import informatorio.service.investigador.InvestigadorService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ExperimentoServiceImplement implements ExperimentoService {

    // Lista que almacena todos los experimentos registrados
    private final List<Experimento> experimentos = new ArrayList<>();

    // Referencia al servicio de investigadores para asociar experimentos a ellos
    private final InvestigadorService investigadorService;

    // Constructor recibe el InvestigadorService
    public ExperimentoServiceImplement(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    // Muestra todos los experimentos registrados en consola
    @Override
    public void mostrarExperimentos() {
        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
        } else {
            for (Experimento experimento : experimentos) {
                System.out.println(experimento);
            }
        }
    }

    // Muestra estadísticas de los experimentos
    @Override
    public void mostrarEstadisticas() {
        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos para mostrar estadísticas.");
            return;
        }

        long exitos = experimentos.stream().filter(Experimento::isExito).count();
        long fallos = experimentos.size() - exitos;

        System.out.println("Experimentos exitosos: " + exitos);
        System.out.println("Experimentos fallidos: " + fallos);

        // Encuentra el experimento de mayor duración
        Experimento maxDuracion = experimentos.stream()
                .max(Comparator.comparingInt(Experimento::getDuracionMinutos))
                .orElse(null);

        if (maxDuracion != null) {
            System.out.println("Experimento más largo: " + maxDuracion);
        }

        // Calcula promedio de duración y porcentaje de éxito global
        double promedioDuracion = experimentos.stream()
                .mapToInt(Experimento::getDuracionMinutos)
                .average().orElse(0);

        double porcentajeExito = (double) exitos / experimentos.size() * 100;

        System.out.printf("Promedio de duración: %.2f min%n", promedioDuracion);
        System.out.printf("Porcentaje de éxito: %.2f%%%n", porcentajeExito);
    }

    // Registra un experimento químico internamente
    @Override
    public void registrarExperimentoQuimico(String nombre, int duracion, boolean exito, String tipoReactivo, String nombreInvestigador) {
        Investigador investigador = investigadorService.getInvestigadorPorNombre(nombreInvestigador);
        if (investigador != null) {
            investigador.incrementarExperimentos(); // Aumenta contador del investigador
            experimentos.add(new ExperimentoQuimico(nombre, duracion, exito, tipoReactivo, investigador));
        } else {
            System.out.println("Investigador no encontrado.");
        }
    }

    // Registra un experimento físico internamente, asociado a varios investigadores
    @Override
    public void registrarExperimentoFisico(String nombre, int duracion, boolean exito, String instrumento, List<String> nombresInvestigadores) {
        List<Investigador> listaInv = new ArrayList<>();
        for (String nombreInv : nombresInvestigadores) {
            Investigador inv = investigadorService.getInvestigadorPorNombre(nombreInv);
            if (inv != null) {
                inv.incrementarExperimentos();
                listaInv.add(inv);
            } else {
                System.out.println("Investigador no encontrado: " + nombreInv);
            }
        }
        if (!listaInv.isEmpty()) {
            experimentos.add(new ExperimentoFisico(nombre, duracion, exito, instrumento, listaInv));
        } else {
            System.out.println("No se registró el experimento (sin investigadores).");
        }
    }

    // Métodos de menú para interactuar con el usuario
    @Override
    public void menuRegistrarQuimico(Scanner sc) {
        System.out.print("Nombre del experimento: ");
        String nombre = sc.nextLine();
        System.out.print("Duración (min): ");
        int duracion = sc.nextInt();
        System.out.print("¿Éxito? (true/false): ");
        boolean exito = sc.nextBoolean();
        sc.nextLine();
        System.out.print("Tipo de reactivo: ");
        String reactivo = sc.nextLine();
        System.out.print("Nombre del investigador: ");
        String nombreInv = sc.nextLine();

        // Llama al método interno para registrar el experimento
        registrarExperimentoQuimico(nombre, duracion, exito, reactivo, nombreInv);
    }

    @Override
    public void menuRegistrarFisico(Scanner sc) {
        System.out.print("Nombre del experimento: ");
        String nombre = sc.nextLine();
        System.out.print("Duración (min): ");
        int duracion = sc.nextInt();
        System.out.print("¿Éxito? (true/false): ");
        boolean exito = sc.nextBoolean();
        sc.nextLine();
        System.out.print("Instrumento utilizado: ");
        String instrumento = sc.nextLine();

        // Permite ingresar varios investigadores
        List<String> nombresInv = new ArrayList<>();
        String nombreInv;
        do {
            System.out.print("Nombre del investigador (o 'fin'): ");
            nombreInv = sc.nextLine();
            if (!nombreInv.equalsIgnoreCase("fin")) nombresInv.add(nombreInv);
        } while (!nombreInv.equalsIgnoreCase("fin"));

        // Llama al método interno para registrar el experimento
        registrarExperimentoFisico(nombre, duracion, exito, instrumento, nombresInv);
    }
}

