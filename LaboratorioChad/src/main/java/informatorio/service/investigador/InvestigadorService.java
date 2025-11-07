package informatorio.service.investigador;

import informatorio.domain.Investigador;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface InvestigadorService {

    void registrarInvestigador(String nombre, int edad);

    void exportarCSV(String rutaArchivo) throws IOException;

    Investigador getInvestigadorPorNombre(String nombre);

    List<Investigador> getInvestigadores();

    Investigador getInvestigadorConMasExperimentos();

    void menuRegistrarInvestigador(Scanner sc);

    void menuExportarCSV(Scanner sc);

    void menuMostrarInvestigadorTop();
}
