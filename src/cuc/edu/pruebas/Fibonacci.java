package cuc.edu.pruebas;

import cuc.edu.listas.ListaSimple;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("Fibonacci: " + fibonacci(0));
    }
    
    public static ListaSimple<Integer> fibonacci(int terminos) {
        int i = 1;
        ListaSimple<Integer> fibonacci = new ListaSimple<>();
        fibonacci.adicionarInicio(i);
        fibonacci.adicionarInicio(i);
        while (i <= terminos) {
            fibonacci.adicionarFinal(fibonacci.buscar(i-1)+fibonacci.buscar(i));
            i++;
        }
        return fibonacci;
    }
}
