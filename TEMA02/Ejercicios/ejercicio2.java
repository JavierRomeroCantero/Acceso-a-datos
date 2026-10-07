package Ejercicios;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Scanner;

public class ejercicio2 {
    public static void main(String[] args) {
        //Pongo la ruta en una variable
        String ruta = ".\\Ejercicios\\entrada2.txt";
        Scanner sc = new Scanner(System.in);

        // 1. Pedir al usuario el número de línea
        System.out.print("Indica qué línea quieres leer: ");
        int lineaDeseada = sc.nextInt();

        try {
            // 2. Utilizar LineNumberReader para leer el archivo
            LineNumberReader lnr = new LineNumberReader(new FileReader(ruta));
            String lineaActual;
            boolean encontrada = false;

            // While para leer linea por linea
            while ((lineaActual = lnr.readLine()) != null) {

                // LineNumberReader empieza a contar en 1 automáticamente después del primer readLine()
                if (lnr.getLineNumber() == lineaDeseada) {

                    // 3. Mostrar por consola
                    System.out.println("Contenido de la línea número " + lineaDeseada + ":");
                    System.out.println(lineaActual);
                    encontrada = true;
                    break; 
                }
            }

            lnr.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } +
        
        sc.close();
    }
}
