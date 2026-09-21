package pe.edu.uni.clase03.prueba;

import pe.edu.uni.clase03.service.Mate;

public class Prueba01 {

    public static void main(String[] args) {
        
        Mate mate1 = new Mate();
        System.out.println("Factorial de 3: " + mate1.factorial(3));
        System.out.println("Factorial de 4: " + mate1.factorial(4));
        System.out.println("Contador 1: " + mate1.getCont());
        
        Mate mate2 = new Mate();
        System.out.println("Factorial de 3: " + mate2.factorial(3));
        System.out.println("Factorial de 4: " + mate2.factorial(4));
        System.out.println("Contador 2: " + mate2.getCont());
        
    }

    
}
