package informatorio.domain;

public abstract class Experimento {
    protected String nombre;
    protected int duracionMinutos;
    protected boolean exito;

    public Experimento(String nombre, int duracionMinutos, boolean exito) {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.exito = exito;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public boolean isExito() {
        return exito;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre + " (" + getTipo() + ") - Duración: " + duracionMinutos + " min - Resultado: " + (exito ? "Éxito" : "Fallo");
    }
}
