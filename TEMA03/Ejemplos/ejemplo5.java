package TEMA03.Ejemplos;
public class ejemplo5 {
    public static void main(String[] args) {
        try {
            int [] numbers = {1, 2, 3};
            System.out.println(numbers[5]);
            System.out.println("Ocurrió una excepcion ArrayIndexOutBoundsExcept: Indice fuera de rango");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Excepcion controlada " + e.toString());
        }
        
    }
}
