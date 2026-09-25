package TEMA01.Ejemplos;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ejemplo5 {
    public static void main(String[] args) {
       
        String path = "./Tema01/Fotos/image.png";
        String pathEscritura = "./Tema01/Fotos/copia.png";
        int num = 0;

        try {
            FileInputStream entrada = new FileInputStream(path);
            FileOutputStream salida = new FileOutputStream(pathEscritura);
            int data;
            while ((data = entrada.read()) != -1) {
                num ++;
               salida.write(data);
            }
            entrada.close();
            salida.close();
            System.out.println(num);
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

    }
}