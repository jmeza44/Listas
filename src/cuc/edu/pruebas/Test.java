package cuc.edu.pruebas;

import cuc.edu.listas.ListaSimple;

public class Test {

    public static void main(String[] args) {
        /*Prueba de métodos de listas para una lista sin elementos repetidos
        Métodos probados: adicionar elemento, adicionar en posición especifica,
        adicionar al inicio y al final, eliminar una vez, eliminar por posicion una vez,
        eliminar en un intervalo de posiciones, eliminar al inicio y al final, buscar por dato,
        buscar por posición, buscar por intervalo de posiciones, buscar penúltimo y pultimo, 
        mover un elemento de una posición X a una Y.*/
        
        ListaSimple<Integer> numeros = new ListaSimple<>();
        
        //Adición
        System.out.println(">> Adicionando ([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])...");
        numeros.adicionarElemento(1);
        numeros.adicionarElemento(2);
        numeros.adicionarElemento(3);
        numeros.adicionarElemento(4);
        numeros.adicionarElemento(5);
        numeros.adicionarElemento(6);
        numeros.adicionarElemento(7);
        numeros.adicionarElemento(8);
        numeros.adicionarElemento(9);
        numeros.adicionarElemento(10);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());

        System.out.println("\n>> Adicionando en posición especifica (Num2020)(Pos5)...");
        numeros.adicionarElemento(2020, 5);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());

        System.out.println("\n>> Adicionando al inicio (Num0)...");
        numeros.adicionarInicio(0);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());

        System.out.println("\n>> Adicionando al final (Num11)...");
        numeros.adicionarFinal(11);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());
        
        //Eliminación
        System.out.println("\n>> Eliminando dato una vez (Num2020)...");
        Integer a = 2020;
        numeros.eliminarElemento(a, false);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());

        System.out.println("\n>> Eliminando dato por posición una vez (Pos8)...");
        int b =8;
        numeros.eliminarElemento(b, false);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());

        System.out.println("\n>> Eliminando datos en un intervalo de posiciones (Pos3,Pos5)...");
        numeros.eliminarElemento(3, 5);
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());
        
        System.out.println("\n>> Eliminando al inicio...");
        numeros.eliminarInicial();
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());
        
        System.out.println("\n>> Eliminando al final...");
        numeros.eliminarUltimo();
        System.out.println("Números: " + numeros.toString());
        System.out.println("Elementos contenidos: " + numeros.longitud());
        
        //Búsqueda
        System.out.println("\n>> Búsqueda por dato (Num2)...");
        a = 2;
        System.out.println("Posición: " + numeros.buscar(a));

        System.out.println("\n>> Búsqueda por posición (Pos3)...");
        b = 3;
        System.out.println("Dato: " + numeros.buscar(b));

        System.out.println("\n>> Búsqueda por intervalo de posiciones (Pos0, pos2)...");
        System.out.println("Lista resultado: " + numeros.buscar(0, 2));

        System.out.println("\n>> Búsqueda penúltimo dato...");
        System.out.println("Números: " + numeros.toString());
        System.out.println("Penúltimo dato: " + numeros.buscarPreFinal());
        
        System.out.println("\n>> Búsqueda último dato...");
        System.out.println("Números: " + numeros.toString());
        System.out.println("Último dato: " + numeros.buscarFinal());
        
        System.out.println("\n>> Mover dato (Pos0,Pos4)...");
        System.out.println("Datos a mover: " + numeros.buscar((int)0)+ " - " + numeros.buscar((int)4));
        System.out.println("Números antes: " + numeros.toString());
        numeros.mover(0, 4);
        System.out.println("Números después: " + numeros.toString());
    }
}
