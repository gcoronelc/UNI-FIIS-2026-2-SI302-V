package pe.edu.uni.clase03.service;

public class Mate2 {
    
    private static int cont; // Variable de clase
    
    static {
        cont = 0;
    }

    public Mate2() {
    }

    public static int getCont() {
        return cont;
    }
    
    public long factorial(int n) {
        cont++;
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

}
