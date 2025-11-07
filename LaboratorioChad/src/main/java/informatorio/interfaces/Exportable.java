package informatorio.interfaces;

import java.io.IOException;

// Interfaz que define un contrato para exportar datos a un archivo CSV.
// Cualquier clase que la implemente deberá proporcionar su propia lógica
// para exportar información en formato CSV.
public interface Exportable {
    void exportarCSV(String rutaArchivo) throws IOException;
}
