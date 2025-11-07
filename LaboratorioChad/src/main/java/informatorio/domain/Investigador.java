package informatorio.domain;

public class Investigador {
    private String nombre;
    private int edad;
    private int cantidadExperimentos;

    public Investigador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantidadExperimentos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getCantidadExperimentos() {
        return cantidadExperimentos;
    }

    public void incrementarExperimentos() {
        cantidadExperimentos++;
    }

    @Override
    public String toString() {
        return nombre + " (Edad: " + edad + ")";
    }
}