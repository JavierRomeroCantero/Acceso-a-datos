package TEMA01.Ejercicios;

import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        
        String path = "./TEMA01/Recursos Ejercicios/datos.txt";
        Scanner sc = new Scanner(System.in);

        try {

            FileWriter fw = new FileWriter(path);
            for (char letra = 'a'; letra <= 'z'; letra++) {
                fw.write(letra);
            }
            fw.close();
            System.out.println("Abecedario generado en datos.txt");


            System.out.print("Introduce la posición que quieres modificar: ");
            int posicion = sc.nextInt();
            
            System.out.print("Introduce el carácter que quieres escribir: ");
            char nuevoCaracter = sc.next().charAt(0);


            RandomAccessFile file = new RandomAccessFile(path, "rw");
            file.seek(posicion);
            file.write(nuevoCaracter); 
            

            file.close();
            System.out.println("Modificación realizada con éxito.");

        } catch (Exception e) {

            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
