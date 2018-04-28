package Execucao;

public class EntradaDeTexto{

    public static String readLine() {

        int ch;
        String r = "";
        boolean done = false;
        while (!done) {
            try {
                ch = System.in.read();
                if (ch < 0 || (char) ch == '\n') {
                    done = true;
                } else if ((char) ch != '\r') {
                    r = r + (char) ch;
                }
            } catch (java.io.IOException e) {
                done = true;
            }
        }
        return r;
    }

    public static int readInt() {

        while (true) {
            try {
                return Integer.valueOf(readLine().trim()).intValue();
            } catch (NumberFormatException e) {
                System.out.println("Este nao eh um valor valido! Digite-o novamente: ");
            }
        }
    }
    
    public static double readFloat() {

        while (true) {
            try {
                return Double.parseDouble(readLine());
            } catch (NumberFormatException e) {
                System.out.println("Este nao eh um valor valido! Digite-o novamente: ");
            }
        }
    }
}