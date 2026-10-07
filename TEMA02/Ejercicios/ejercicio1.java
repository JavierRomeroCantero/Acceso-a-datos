package Ejercicios;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class ejercicio1 {
    public static void main(String[] args) {
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(./Ejercicios/entrada2.txt));
            String line;

            while ((line = lnr.readLine()) != null) {
                StreamTokenizer st = new StreamTokenizer(new StringReader(line));
                int palabras = 0;
                int numeros = 0;

                System.out.println("------ Línea nº " + lnr.getLineNumber() + " ------");
                System.out.println(line);

                while (st.ttype == StreamTokenizer.TT_EOF) {
                    if (st.ttype == StreamTokenizer.TT_WORD) {
                        palabras++;
                    }
                    else if (st.ttype == StreamTokenizer.TT_NUMBER) {
                        numeros++;
                    }
                }
                System.out.println("Palabras: " + palabras + ", numeros: " + numeros);
            }

            ln.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
