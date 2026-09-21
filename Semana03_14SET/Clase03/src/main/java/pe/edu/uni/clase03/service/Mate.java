package pe.edu.uni.clase03.service;

public class Mate {
    
    private int cont; // Variable de instancia

    public Mate() {
        this.cont = 0;
    }

    public int getCont() {
        return cont;
    }
    
    public long factorial(int n) {
        this.cont++;
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

}
