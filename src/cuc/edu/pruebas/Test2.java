package cuc.edu.pruebas;

import cuc.edu.listas.ListaSimple;

public class Test2 {

    public static void main(String[] args) {
        /*Prueba de métodos de listas para una lista con elementos repetidos
        Métodos probados: mostrar el total de apariciones de un elemento, eliminar
        por dato y por posición todos los elementos iguales, buscar todas la posiciones en las
        que aparece un dato.*/

        ListaSimple<Integer> numeros = new ListaSimple<>();

        //Adición
        System.out.println(">> Adicionando ([1, 1, 1, 2, 1, 3, 3, 1, 5, 1, 1, 6, 2, 3, 5])...");
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(2);
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(3);
        numeros.adicionarElemento(3);
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(5);
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(6);
        numeros.adicionarElemento(2);
        numeros.adicionarElemento(3);
        numeros.adicionarElemento(5);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());

        Integer a = 1;
        int b = 2;
        
        System.out.println("\n>> Mostrando el número de apariciones de un dato (Num1)...");
        System.out.println("Apariciones: " + numeros.apariciones(a));
        
        System.out.println("\n>> Mostrando una lista con todas las posciciones en las que aparece un dato (Num1)...");
        System.out.println("Lista de apariciones: " + numeros.buscarTodos(a));

        System.out.println("\n>> Eliminando todas las apariciones de un dato (Num1)...");
        System.out.println("Números antes: " + numeros.toString() + " Longitud: " + numeros.longitud());
        numeros.eliminarElemento(1, true);
        System.out.println("Números después: " + numeros.toString() + " Longitud: " + numeros.longitud());
        
        System.out.println("\n>> Eliminando todas las apariciones de un dato en una posición especifica (Pos2)...");
        System.out.println("Dato en la posición indicada: " + numeros.buscar(b));
        System.out.println("Números antes: " + numeros.toString() + " Longitud: " + numeros.longitud());
        numeros.eliminarElemento(2, true);
        System.out.println("Números después: " + numeros.toString() + " Longitud: " + numeros.longitud());
    }
}
