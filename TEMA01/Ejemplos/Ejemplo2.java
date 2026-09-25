package TEMA01.Ejemplos;
import java.io.File;

public class Ejemplo2 {

    
    public static void main(String[] args) {
        
        // Mover un fichero 

    File ficheroOrigen = new File(".\\temp\\pruebas1.txt");
    File ficheroDestino = new File(".\\temp\\pruebas\\pruebas2.txt");

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