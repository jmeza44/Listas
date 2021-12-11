package cuc.edu.taller_de_desarrollo;

import cuc.edu.listas.ListaDoble;

public class Test2 {

    public static void main(String[] args) {
        ListaDoble<Integer> lista_uno = new ListaDoble<>();
        ListaDoble<Integer> lista_dos = new ListaDoble<>();
        ListaDoble<Integer> lista_tres = new ListaDoble<>();

        System.out.println(">> Adicionando elementos a la lista 1... ([1, 5, 3, 6, 7, 3, 8, 5, 6, 2])");
        lista_uno.adicionarElemento(1);
        lista_uno.adicionarElemento(5);
        lista_uno.adicionarElemento(3);
        lista_uno.adicionarElemento(6);
        lista_uno.adicionarElemento(7);
        lista_uno.adicionarElemento(3);
        lista_uno.adicionarElemento(8);
        lista_uno.adicionarElemento(5);
        lista_uno.adicionarElemento(6);
        lista_uno.adicionarElemento(2);
        System.out.println("Lista: " + lista_uno.toString());
        System.out.println("Lista reverso: " + lista_uno.reverseToString());
        System.out.println("Número de elementos: " + lista_uno.longitud());

        System.out.println("\n>> Adicionando elementos a la lista 2... ([2, 6, 5, 8, 3, 7, 6, 3, 5, 1])");
        lista_dos.adicionarElemento(2);
        lista_dos.adicionarElemento(6);
        lista_dos.adicionarElemento(5);
        lista_dos.adicionarElemento(8);
        lista_dos.adicionarElemento(3);
        lista_dos.adicionarElemento(7);
        lista_dos.adicionarElemento(6);
        lista_dos.adicionarElemento(3);
        lista_dos.adicionarElemento(5);
        lista_dos.adicionarElemento(1);
        System.out.println("Lista: " + lista_dos.toString());
        System.out.println("Lista reverso: " + lista_dos.reverseToString());
        System.out.println("Número de elementos: " + lista_dos.longitud());

        System.out.println("\n>> Adicionando elementos a la lista 3... ([2, 6, 5, 8, 3, 8, 6, 3, 5, 1])");
        lista_tres.adicionarElemento(2);
        lista_tres.adicionarElemento(6);
        lista_tres.adicionarElemento(5);
        lista_tres.adicionarElemento(8);
        lista_tres.adicionarElemento(3);
        lista_tres.adicionarElemento(8);
        lista_tres.adicionarElemento(6);
        lista_tres.adicionarElemento(3);
        lista_tres.adicionarElemento(5);
        lista_tres.adicionarElemento(1);
        System.out.println("Lista: " + lista_tres.toString());
        System.out.println("Lista reverso: " + lista_tres.reverseToString());
        System.out.println("Número de elementos: " + lista_tres.longitud());

        
        //Prueba de los métodos correspondientes al taller de desarrollo #2 (Listas dobles)
        System.out.println("\n>>¿Es la lista 2 reverso de la lista 1?");
        if (lista_uno.esReverso(lista_dos)) {
            System.out.println(">> Si");
        } else {
            System.out.println(">> No");
        }
        System.out.println("\n>>¿Es la lista 3 reverso de la lista 1?");
        if (lista_uno.esReverso(lista_tres)) {
            System.out.println(">> Si");
        } else {
            System.out.println(">> No");
        }
        System.out.println("\n>>¿Es la lista 2 reverso de la lista 3?");
        if (lista_tres.esReverso(lista_dos)) {
            System.out.println(">> Si");
        } else {
            System.out.println(">> No");
        }
    }
}
