package TEMA01.Ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ejercicio4 {
    public static void main(String[] args) {

        int tamanoBuffer = 1024;
        byte[] buffer = new byte[tamanoBuffer];

        try {
            //BIS para trabajar con imagenes 
            BufferedInputStream entrada = new BufferedInputStream(new FileInputStream("./TEMA01/RecursosEjercicios/Producto1.png"));

            BufferedOutputStream salida = new BufferedOutputStream(new FileOutputStream("./TEMA01/RecursosEjercicios/Producto1_copia.png"));

            int bytesleidos;
            int bloque = 1;

            while ((bytesleidos = entrada.read(buffer)) != -1) {
                salida.write(buffer, 0, bytesleidos);
                System.out.println("Fin bloque: " + bloque + ", se han leido " + bytesleidos);
                bloque++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
