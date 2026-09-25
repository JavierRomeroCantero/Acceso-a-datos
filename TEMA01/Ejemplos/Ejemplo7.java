package TEMA01.Ejemplos;

import java.io.RandomAccessFile;

public class Ejemplo7 {
    public static void main(String[] args) {

        try {
            RandomAccessFile file = new RandomAccessFile("C:\\Users\\PC126\\Desktop\\DAM\\2DAM\\GitHub 2\\Acceso-a-datos\\TEMA01\\Recursos\\abecedario.txt", "r");

            file.seek(5);
            byte[] arrayBytes = new byte[3];
            file.read(arrayBytes, 0, 3);

            System.out.println("Bytes leidos: " + arrayBytes.length);

            for (int i = 0; i < arrayBytes.length; i++) {
                System.out.println("\n arrayBytes[" + i + "] = " + arrayBytes[i] + " -> " + (char) + arrayBytes[i]);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
