package cuc.edu.taller_de_desarrollo;

import cuc.edu.listas.ListaSimple;

public class Test1 {
    
    public static void main(String[] args) {
        ListaSimple<Integer> numeros1 = new ListaSimple<>();
        ListaSimple<Integer> numeros2 = new ListaSimple<>();
        ListaSimple<Integer> numeros3 = new ListaSimple<>();

        System.out.println("--- Prueba con números ---");
        //Adición
        System.out.println(">> Creando las listas 1, 2 y 3...");
        numeros1.adicionarElemento(1);
        numeros1.adicionarElemento(3);
        numeros1.adicionarElemento(4);
        numeros1.adicionarElemento(5);
        numeros1.adicionarElemento(7);
        numeros1.adicionarElemento(8);
        numeros1.adicionarElemento(9);
        numeros1.adicionarElemento(10);
        numeros1.adicionarElemento(2);
        numeros2.adicionarElemento(5);
        numeros2.adicionarElemento(7);
        numeros2.adicionarElemento(10);
        numeros2.adicionarElemento(5);
        numeros3.adicionarElemento(6);
        numeros3.adicionarElemento(1);
        numeros3.adicionarElemento(3);
        System.out.println("Números 1: " + numeros1.toString());
        System.out.println("Elementos contenidos: " + numeros1.longitud());
        System.out.println("Números 2: " + numeros2.toString());
        System.out.println("Elementos contenidos: " + numeros2.longitud());
        System.out.println("Números 3: " + numeros3.toString());
        System.out.println("Elementos contenidos: " + numeros3.longitud());
        
        System.out.println("\n>> Adicionando en agrupación en números 1 (Num2)...");
        numeros1.adicionarAgrupando(2);
        System.out.println("Números 1: " + numeros1.toString());
        System.out.println("Elementos contenidos: " + numeros1.longitud());
        
        System.out.println("\n>> Adicionando en agrupación en números 2 (Num5)...");
        numeros2.adicionarAgrupando(5);
        System.out.println("Números 2: " + numeros2.toString());
        System.out.println("Elementos contenidos: " + numeros2.longitud());
        
        System.out.println("\n>> Adicionando en agrupación en números 3 (Num1)...");
        numeros3.adicionarAgrupando(1);
        System.out.println("Números 3: " + numeros3.toString());
        System.out.println("Elementos contenidos: " + numeros3.longitud());
    }
}
