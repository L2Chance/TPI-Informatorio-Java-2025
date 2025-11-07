package informatorio.domain;

import java.util.List;

public class ExperimentoFisico extends Experimento {
    private String instrumento;
    private List<Investigador> investigadores;

    public ExperimentoFisico(String nombre, int duracionMinutos, boolean exito, String instrumento, List<Investigador> investigadores) {
        super(nombre, duracionMinutos, exito);
        this.instrumento = instrumento;
        this.investigadores = investigadores;
    }

    @Override
    public String getTipo() {
        return "Físico";
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    @Override
    public String toString() {
        return super.toString() + " - Instrumento: " + instrumento + " - Investigadores: " +
                investigadores.stream().map(Investigador::getNombre).reduce((a, b) -> a + ", " + b).orElse("");
    }
}
