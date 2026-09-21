package pe.edu.uni.clase03.prueba;

import pe.edu.uni.clase03.service.Mate2;

public class Prueba02 {

    public static void main(String[] args) {
        
        Mate2 mate1 = new Mate2();
        System.out.println("Factorial de 3: " + mate1.factorial(3));
        System.out.println("Factorial de 4: " + mate1.factorial(4));
        System.out.println("Contador 1: " + Mate2.getCont());
        
        Mate2 mate2 = new Mate2();
        System.out.println("Factorial de 3: " + mate2.factorial(3));
        System.out.println("Factorial de 4: " + mate2.factorial(4));
        System.out.println("Contador 2: " + Mate2.getCont());
        
    }

    
}
