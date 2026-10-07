package Ejemplos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.stream.FileImageInputStream;

public class ejemplo3 {
    public static void main(String[] args) {

        try {
            DataOutputStream dps = new DataOutputStream(new FileOutputStream(".\\Ejemplos\\salida.txt"));
            dps.writeInt(123);
            dps.writeInt(987);
            dps.writeFloat((float) 123.45);
            dps.writeLong(983749823);
            dps.writeDouble(9.3);
            dps.close();

            DataInputStream dis = new DataInputStream(new FileInputStream(".\\Tema2\\salida.txt"));
            int entero1 = dis.readInt();
            int entero2 = dis.readInt();
            float float1 = dis.readFloat();
            long long1 = dis.readLong();
            double double1 = dis.readDouble();
            dis.close();

            System.out.println("El numero entero es " + entero1 + " y " + entero2);
            System.out.println("El decimal es " + float1);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
