package informatorio.service.investigador;

import informatorio.domain.Investigador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class InvestigadorServiceImplement implements InvestigadorService {

    // Lista que almacena todos los investigadores registrados
    private final List<Investigador> investigadores = new ArrayList<>();

    // Registra un nuevo investigador con nombre y edad
    @Override
    public void registrarInvestigador(String nombre, int edad) {
        investigadores.add(new Investigador(nombre, edad));
    }

    // Devuelve la lista completa de investigadores
    @Override
    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    // Busca un investigador por nombre (sin importar mayúsculas/minúsculas)
    @Override
    public Investigador getInvestigadorPorNombre(String nombre) {
        return investigadores.stream()
                .filter(i -> i.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Exporta los datos de los investigadores a un archivo CSV
    @Override
    public void exportarCSV(String rutaArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write("nombre,edad,cantidad_experimentos\n"); // cabecera
            for (Investigador i : investigadores) {
                writer.write(i.getNombre() + "," + i.getEdad() + "," + i.getCantidadExperimentos() + "\n");
            }
        }
    }

    // Devuelve el investigador que realizó más experimentos
    @Override
    public Investigador getInvestigadorConMasExperimentos() {
        return investigadores.stream()
                .max(Comparator.comparingInt(Investigador::getCantidadExperimentos))
                .orElse(null);
    }

    // --- Métodos de interacción con el menú ---

    // Registra un investigador pidiendo datos por consola
    @Override
    public void menuRegistrarInvestigador(Scanner sc) {
        System.out.print("Nombre del investigador: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        registrarInvestigador(nombre, edad);
    }

    // Exporta investigadores a CSV pidiendo la ruta por consola
    @Override
    public void menuExportarCSV(Scanner sc) {
        System.out.print("Nombre del archivo CSV (ej. investigadores.csv): ");
        String ruta = sc.nextLine();
        try {
            exportarCSV(ruta);
            System.out.println("Archivo CSV exportado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    // Muestra el investigador que tiene más experimentos realizados
    @Override
    public void menuMostrarInvestigadorTop() {
        Investigador top = getInvestigadorConMasExperimentos();
        if (top != null) {
            System.out.println("Investigador con más experimentos: " + top + " - Total de experimentos: " + top.getCantidadExperimentos());
        } else {
            System.out.println("No hay investigadores registrados.");
        }
    }
}
