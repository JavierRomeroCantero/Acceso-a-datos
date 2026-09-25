package TEMA01.Ejercicios;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ejercicio2 {

    public static void main(String[] args) {
        
        String pathOrigen = "./TEMA01/Recursos Ejercicios/foto.jpg";
        String pathDestino = "./TEMA01/Recursos Ejercicios/foto_copia.jpg";
        int contador = 0;

        try {
            FileInputStream entrada = new FileInputStream(pathOrigen);
            FileOutputStream salida = new FileOutputStream(pathDestino);
            
            int data;
            while ((data = entrada.read()) != -1) {
                contador++;
                salida.write(data);
            }
            
            entrada.close();
            salida.close();
            
            System.out.println("Copia de imagen completada.");
            System.out.println("Total de bytes leídos/copiados: " + contador);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
