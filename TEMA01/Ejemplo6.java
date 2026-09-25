package TEMA01;
import java.io.RandomAccessFile;

public class Ejemplo6 {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("C:\\Users\\PC126\\Desktop\\DAM\\2DAM\\Acceso a datos\\Bloque1\\Tema1\\abcd.txt", "rw");
            file.seek(5);
            long puntero = file.getFilePointer();
            System.out.println("Puntero ANTES de leer: " + file.getFilePointer()); //escribira 5 
            int unbyte = file.read();
            System.out.println("Puntero DESPUES de leer: " + file.getFilePointer()); //escribira 6
            System.out.println((char)unbyte);
            file.write('0'); 
            System.out.println("Puntero DESPUES de escribir: " + file.getFilePointer()); //escribira 6

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
