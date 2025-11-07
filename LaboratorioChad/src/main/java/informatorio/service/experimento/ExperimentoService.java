package informatorio.service.experimento;

import java.util.List;
import java.util.Scanner;

public interface ExperimentoService {

    void mostrarExperimentos();

    void mostrarEstadisticas();

    void registrarExperimentoQuimico(String nombre, int duracion, boolean exito, String tipoReactivo, String nombreInvestigador);

    void registrarExperimentoFisico(String nombre, int duracion, boolean exito, String instrumento, List<String> nombresInvestigadores);

    void menuRegistrarQuimico(Scanner sc);

    void menuRegistrarFisico(Scanner sc);
}
