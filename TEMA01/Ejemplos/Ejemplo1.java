package TEMA01.Ejemplos;
import java.io.File;
import java.io.IOException;

public class Ejemplo1 {
    
public static void main(String[] args) throws IOException {

       File fichero = new File(".\\TEMA01\\Ejemplos\\crearFichero.txt");

            if (fichero.createNewFile()) {
    System.out.println("Fichero creado: " + fichero.getName());
} else {
    System.out.println("El fichero ya existe.");
}

        File ficheroOrigen = new File("C:\\temp\\pruebas1.txt");
File ficheroDestino = new File("C:\\temp\\pruebas\\pruebas2.txt");

try {
    if (ficheroOrigen.renameTo(ficheroDestino)) {
        System.out.println("El fichero se movió correctamente");
    } else {
        System.out.println("El fichero no pudo moverse");
    }
} catch (Exception e) {
    e.printStackTrace();
}
}
} 