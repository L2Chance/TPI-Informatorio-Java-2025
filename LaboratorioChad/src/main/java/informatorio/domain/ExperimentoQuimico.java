package informatorio.domain;

public class ExperimentoQuimico extends Experimento {
    private String tipoReactivo;
    private Investigador investigador;

    public ExperimentoQuimico(String nombre, int duracionMinutos, boolean exito, String tipoReactivo, Investigador investigador) {
        super(nombre, duracionMinutos, exito);
        this.tipoReactivo = tipoReactivo;
        this.investigador = investigador;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    @Override
    public String getTipo() {
        return "Químico";
    }

    @Override
    public String toString() {
        return super.toString() + " - Reactivo: " + tipoReactivo + " - Investigador: " + investigador.getNombre();
    }
}
