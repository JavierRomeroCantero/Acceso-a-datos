package TEMA01.Ejemplos;
import java.io.RandomAccessFile;

public class CasoPractico6 {

    public static void main(String[] args) {

        
        try {

                RandomAccessFile file = new RandomAccessFile("TEMA01/Ejemplos/abcd.txt", "rw");
                file.seek(5);

                long puntero = file.getFilePointer();
                System.out.println("Puntero ANTES de leer: " + puntero);

                int unbyte = file.read();

                System.out.println("Puntero DESPUES de leer: " + puntero);
        
                System.out.println((char)unbyte);
                file.write('0');

        } catch (Exception e) {
        // TODO: handle exception

        e.printStackTrace();

        }
    }
}
