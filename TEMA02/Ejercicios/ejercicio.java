package Ejercicios;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ejercicio {
    public static void main(String[] args) {

        String ruta = ".\\Ejercicios\\entrada.txt";

        try {
            // 1. ESCRITURA DE DATOS
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta));
            
            // Línea 1: Hola 123 Mundo
            dos.writeUTF("Hola ");
            dos.writeInt(123);
            dos.writeUTF(" Mundo");
            
            // Línea 2: Prueba 45.67 de texto
            dos.writeUTF("Prueba ");
            dos.writeDouble(45.67);
            dos.writeUTF(" de texto");
            
            // Línea 3: 12345
            dos.writeInt(12345);
            
            dos.close();

            // 2. LECTURA DE DATOS
            DataInputStream dis = new DataInputStream(new FileInputStream(ruta));
            
            // IMPORTANTE: Se debe leer exactamente en el mismo orden en el que se escribió
            String palabra1 = dis.readUTF();
            int numero1 = dis.readInt();
            String palabra2 = dis.readUTF();
            
            String palabra3 = dis.readUTF();
            double numero2 = dis.readDouble();
            String palabra4 = dis.readUTF();
            
            int numero3 = dis.readInt();
            
            dis.close();

            // 3. IMPRIMIR POR CONSOLA
            System.out.println(palabra1 + numero1 + palabra2);
            System.out.println(palabra3 + numero2 + palabra4);
            System.out.println(numero3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
