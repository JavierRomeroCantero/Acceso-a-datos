package TEMA01.Ejercicios;

import java.io.FileReader;
import java.io.FileWriter;

public class Ejercicio1 {

    public static void main(String[] args) {

        String pathOrigen = "./TEMA01/Recursos Ejercicios/texto.txt";
        String pathDestino = "./TEMA01/Recursos Ejercicios/copia.txt";

        try {
            FileReader fr = new FileReader(pathOrigen);
            FileWriter fw = new FileWriter(pathDestino);

            int data;
            while ((data = fr.read()) != -1) {
                fw.write(data);
            }

            fr.close();
            fw.close();

            System.out.println("El archivo se ha copiado correctamente.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

