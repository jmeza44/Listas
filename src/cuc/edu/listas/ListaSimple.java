package cuc.edu.listas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaSimple<E> implements Lista<E>, Serializable {

    protected NodoSimple<E> nodoInicial;

    //Adicionar
    /**
     * Adiciona un elemento a la lista.
     *
     * @param dato Object
     */
    @Override
    public void adicionarElemento(E dato) {
        if (estaVacia()) { //Si está vacía
            NodoSimple<E> nodoEntrada = new NodoSimple<>(dato); //Crea un nodo con el parametro
            nodoInicial = nodoEntrada; //Asigna el nodo como Head
        } else {
            adicionarFinal(dato);
        }
    }

    /**
     * Adiciona un elemento a la lista en una posición especifica.
     *
     * @param dato Object
     * @param posicion int
     */
    @Override
    public void adicionarElemento(E dato, int posicion) {
        if (posicion == 0) {
            adicionarInicio(dato);
        } else if (posicion > 0 && posicion < longitud()) {
            NodoSimple<E> nodoEntrada = new NodoSimple<>(dato); //Crea un nodo con el parametro
            NodoSimple<E> nodoActual = nodoInicial; //Nodo recorredor (Pointer)
            int index = 0; //Indica la posición durante el recorrido
            while (nodoActual != null) { //Recorrido hasta el último nodo
                if (index == posicion - 1) { //Si la posición es la anterior a la buscada
                    //Los nodos posteriores a nodo entrada serán iguales a los nodos posteriores al actual
                    nodoEntrada.siguiente = nodoActual.siguiente;
                    //El nodo siguiente al actual será remplazado con los mismos de antes agregando el nodo
                    //indicado al inicio
                    nodoActual.siguiente = nodoEntrada;
                    break;
                }
                nodoActual = nodoActual.siguiente;
                index++;
            }
        }
    }

    /**
     * Adiciona un elemento al inicio de la lista.
     *
     * @param dato Object
     */
    @Override
    public void adicionarInicio(E dato) {
        if (!estaVacia()) { //Sino está vacía
            NodoSimple<E> nodoEntrada = new NodoSimple<>(dato); //Crea un nodo con el parametro
            nodoEntrada.siguiente = nodoInicial; //El nodo entrada se le agregan el nodo head y posteriores
            nodoInicial = nodoEntrada; //El nodo head es remplazado con el nuevo nodo
        } else { //Si está vacía
            NodoSimple<E> nodo_entrada = new NodoSimple<>(dato); //Crea un nodo con el parametro
            nodoInicial = nodo_entrada; //Asigna el nodo como Head
        }
    }

    /**
     * Adiciona un elemento al final de la lista.
     *
     * @param dato Object
     */
    @Override
    public void adicionarFinal(E dato) {
        if (!estaVacia()) {
            NodoSimple<E> nodo_entrada = new NodoSimple<>(dato); //Crea un nodo con el dato del parametro
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            while (nodo_actual.siguiente != null) { //Recorrido hasta el final de la lista
                nodo_actual = nodo_actual.siguiente;
            }
            nodo_actual.siguiente = nodo_entrada; //Último nodo remplazado con el nodo entrada
        } else {
            NodoSimple<E> nodo_entrada = new NodoSimple<>(dato); //Crea un nodo con el parametro
            nodoInicial = nodo_entrada; //Asigna el nodo como Head
        }
    }

    /**
     * Adiciona un elemento en la posición siguiente al dato indicado, es decir,
     * si el dato existe en la lista lo adiciona justo después de la primera
     * aparición de este. Si el dato no está en la lista adiciona el elemento al
     * inicio.
     *
     * @param dato - Objecto dato a adicionar
     */
    @Override
    public void adicionarAgrupando(E dato) {
        this.adicionarElemento(dato, this.buscar(dato) + 1);
    }

    //Eliminar
    /**
     * Elimina elementos de la lista.
     *
     * @param dato Object
     * @param eliminarTodos Si es true se eliminan todas la apariciones del
     * elemento en la lista
     */
    @Override
    public void eliminarElemento(E dato, boolean eliminarTodos) {
        if (!estaVacia()) {
            if (eliminarTodos) {
                while (apariciones(dato) > 0) { //Mientras quede alguno
                    eliminarElemento(dato, false);
                }
            } else {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                while (nodo_actual != null) {
                    if (nodo_actual.dato.equals(dato)) {
                        if (nodo_preActual != null) {
                            nodo_preActual.siguiente = nodo_actual.siguiente;
                        } else {
                            eliminarInicial();
                        }
                        break;
                    }
                    nodo_preActual = nodo_actual;
                    nodo_actual = nodo_actual.siguiente;
                }
            }
        }
    }

    /**
     * Elimina elementos de la lista en una posición especifica.
     *
     * @param posicion int
     * @param eliminarTodos Si es true se eliminan todas la apariciones del
     * elemento en la lista
     */
    @Override
    public void eliminarElemento(int posicion, boolean eliminarTodos) {
        if (!estaVacia()) {
            if (posicion < 0) {

            } else if (posicion >= longitud()) {

            } else if (posicion == 0) {
                eliminarInicial();
            } else {
                if (eliminarTodos) {
                    E dato = buscar(posicion); //Dato a eliminar
                    while (apariciones(dato) > 0) { //Mientras quede alguno
                        eliminarElemento(dato, false);
                    }
                } else {
                    NodoSimple<E> nodo_actual = nodoInicial.siguiente; //Nodo recorredor (Pointer)
                    NodoSimple<E> nodo_preActual = nodoInicial; //Nodo previo al recorredor (Pointer)
                    int index = 1; //Empezando en la posición 1
                    while (nodo_actual != null) {
                        if (index == posicion) {
                            nodo_preActual.siguiente = nodo_actual.siguiente;
                            break;
                        }
                        nodo_preActual = nodo_actual;
                        nodo_actual = nodo_actual.siguiente;
                        index++;
                    }
                }
            }
        }
    }

    /**
     * Elimina los elementos en un intervalo especifico.
     *
     * @param posicionX int - posición inicial del intervalo
     * @param posicionY int - posición final del intervalo
     */
    @Override
    public void eliminarElemento(int posicionX, int posicionY) {
        if (!estaVacia()) {
            //Si el intervalo está entre los límites de la lista y la posición x es menor o igual a la posición y
            if (posicionX >= 0 && posicionY < longitud() && posicionX <= posicionY) {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int index = 0;
                while (nodo_actual != null) { //Para cada nodo
                    if (nodo_preActual != null) { //Sino es el primer nodo
                        if (index == posicionX) { //Si se llego al primer nodo a eliminar
                            NodoSimple<E> nodo_aux = nodo_actual; //Nodo recorredor auxiliar (Pointer)
                            int index_aux = index;
                            while (index_aux < posicionY) { //Mientras no se haya llegado al último nodo del intervalo
                                nodo_aux = nodo_aux.siguiente;
                                index_aux++;
                            }
                            // El nodo primer nodo del intervalo a eliminar se iguala con
                            // el nodo siguiente al último nodo del intervalo
                            nodo_preActual.siguiente = nodo_aux.siguiente;
                            break; //Tras eliminar cierra el ciclo
                        }
                    } else { //Si es el primer nodo
                        if (index == posicionX) {
                            NodoSimple<E> nodo_aux = nodo_actual; //Nodo recorredor auxiliar (Pointer)
                            int index_aux = index;
                            while (index_aux < posicionY) { //Mientras no se haya llegado al último nodo del intervalo
                                nodo_aux = nodo_aux.siguiente;
                                index_aux++;
                            }
                            // El nodo primer nodo del intervalo a eliminar se iguala con
                            // el nodo siguiente al último nodo del intervalo
                            nodoInicial = nodo_aux.siguiente;
                            break; //Tras eliminar cierra el ciclo
                        }
                    }
                    nodo_preActual = nodo_actual;
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
            }
        }
    }

    /**
     * Elimina el primer elemento de la lista.
     */
    @Override
    public void eliminarInicial() {
        if (!estaVacia()) {
            if (nodoInicial.siguiente == null) {
                nodoInicial = null;
            } else {
                nodoInicial = nodoInicial.siguiente; //Asigna el nodo posterior al Heah como Head
            }
        }
    }

    /**
     * Elimina el último elemento de la lista.
     */
    @Override
    public void eliminarUltimo() {
        if (!estaVacia()) {
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
            while (nodo_actual.siguiente != null) {
                nodo_preActual = nodo_actual;
                nodo_actual = nodo_actual.siguiente;
            }
            if (nodo_preActual != null) {
                nodo_preActual.siguiente = null;
            } else {
                eliminarInicial();
            }
        }
    }

    /**
     * Elimina la n-esima aparición de un dato en la lista. El argumento
     * aparición debe ser menor o igual al número de apariciones del dato en
     * cuestión.
     *
     * @param dato Object - objeto a eliminar
     * @param aparicion int - aparición a eliminar
     */
    @Override
    public void eliminarElemento(E dato, int aparicion) {
        if (!estaVacia()) {
            if (aparicion > 0 && aparicion <= apariciones(dato)) {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int aparicion_aux = 0; //Contador de apariciones del dato en la lista
                while (nodo_actual != null) {
                    if (nodo_actual.dato.equals(dato)) {
                        aparicion_aux++;
                    }
                    if (aparicion_aux == aparicion) {
                        if (nodo_preActual == null) { //Si se llegó a la enésima aparición & es el nodo Head
                            eliminarInicial();
                            break;
                        } else if (nodo_actual.siguiente == null) { //Si se llegó a la enésima aparición & es el nodo Tail
                            eliminarUltimo();
                            break;
                        } else { //Si se llegó a la enésima aparición & es un nodo intermedio
                            nodo_preActual.siguiente = nodo_actual.siguiente;
                            break;
                        }
                    }
                    nodo_preActual = nodo_actual;
                    nodo_actual = nodo_actual.siguiente;
                }
            }
        }
    }

    //Buscar
    /**
     * Busca la posición de un elemento especificado.
     *
     * @param dato Object
     * @return int - posición en la lista
     */
    @Override
    public int buscar(E dato) {
        if (!estaVacia()) {
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            int index = 0;
            while (nodo_actual != null) { //Hasta el último nodo
                if (nodo_actual.dato.equals(dato)) { //Si hay una coincidencia
                    return index;
                }
                nodo_actual = nodo_actual.siguiente;
                index++;
            }
            return -1; //Si completó el recorrido y no encontró el dato
        } else {
            return -1; //Si la lista está vacia
        }
    }

    /**
     * Busca un dato en la lista en una posición especificada.
     *
     * @param posicion int
     * @return Object - dato en la posición especificada
     */
    @Override
    public E buscar(int posicion) {
        if (!estaVacia()) {
            if (posicion >= 0 && posicion < longitud()) {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                int index = 0;
                while (nodo_actual != null) { //Recorrido de nodos
                    if (index == posicion) { //Al llegar a la posición requerida
                        return nodo_actual.dato; //Retorna el dato del nodo
                    }
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
                return null; //Caso imposible (nunca se ejecuta)
            } else {
                return null; //Si la posición no es valida
            }
        } else {
            return null; //Si la lista está vacia
        }
    }

    /**
     * Busca los elementos de la lista en un intervalo dentro de ella.
     *
     * @param posicionX inicio del intervalo a buscar
     * @param posicionY fin del intervalo a buscar
     * @return ListaSimple<> - Sublista en el intervalo determinado
     */
    @Override
    public ListaSimple<E> buscar(int posicionX, int posicionY) {
        if (!estaVacia()) {
            if (posicionX >= 0 & posicionY < longitud() & posicionX <= posicionY) {
                ListaSimple<E> lista_resultado = new ListaSimple<>();
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                int index = 0;
                while (nodo_actual != null) { //Recorrido de nodos
                    if (posicionX <= index && index <= posicionY) { //Si el nodo actual está entre el intervalo
                        lista_resultado.adicionarFinal(nodo_actual.dato); //Agrega los nodos a la nueva lista
                        if (index == posicionY) { //Si llegó a final del intervalo
                            return lista_resultado;
                        }
                    }
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
                return null; //Caso imposible (nunca se ejecuta)
            } else {
                return null; //Posición invalida
            }
        } else {
            return null; //Si la lista está vacia
        }
    }

    /**
     * Busca el primer elemento de la lista.
     *
     * @return Object en la primera posición
     */
    @Override
    public E buscarInicial() {
        if (!estaVacia()) {
            return nodoInicial.dato;
        } else {
            return null; //Si está vacia
        }
    }

    /**
     * Busca el penúltimo elemento de la lista.
     *
     * @return Object en la penúltima posición
     */
    @Override
    public E buscarPreFinal() {
        if (!estaVacia() && longitud() > 1) {
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
            while (nodo_actual.siguiente != null) { //Hasta el último nodo
                nodo_preActual = nodo_actual;
                nodo_actual = nodo_actual.siguiente;

            }
            if (nodo_preActual != null) {
                return nodo_preActual.dato;
            }
            return null; //Caso imposible (nunca se ejecuta)
        } else {
            return null;
        }
    }

    /**
     * Busca el último elemento de la lista.
     *
     * @return Object en la última posición
     */
    @Override
    public E buscarFinal() {
        if (!estaVacia() && longitud() > 1) {
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            while (nodo_actual.siguiente != null) { //Hasta el último nodo
                nodo_actual = nodo_actual.siguiente;
            }
            return nodo_actual.dato;
        } else {
            return null;
        }
    }

    /**
     * Busca el dato intermedio en una lista.
     *
     * @return Object en la posición intermedia
     */
    @Override
    public E buscarIntermedio() {
        return buscar(longitud() / 2); //longitud()/2 da como resultado un entero
        //Ejemplo: 5/2 = 2
    }

    /**
     * Busca todas las posiciones en las que se encuentra un elemento.
     *
     * @param dato Object - dato a buscar
     * @return ListaSimple<Integer> - posiciones
     */
    @Override
    public ListaSimple<Integer> buscarTodos(E dato) {
        if (!estaVacia()) {
            ListaSimple<Integer> lista_apariciones = new ListaSimple<>();
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            int index = 0;
            while (nodo_actual != null) { //Hasta el último nodo
                if (nodo_actual.dato.equals(dato)) { //Si hay una coincidencia
                    lista_apariciones.adicionarElemento(index); //Agrega la posición a la lista
                }
                nodo_actual = nodo_actual.siguiente;
                index++;
            }
            return lista_apariciones;
        } else {
            return null; //Si está vacia
        }
    }

    public Integer buscarMenor() {
        if (!estaVacia() & this.nodoInicial.dato.getClass().toString().equals("class java.lang.Integer")) { //Si la lista contiene números
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            int index = 0;
            int index_menor = 0;
            int menor = (Integer) nodo_actual.dato;
            while (nodo_actual != null) { //Hasta el último nodo
                if ((Integer) nodo_actual.dato < menor) {
                    menor = (Integer) nodo_actual.dato;
                    index_menor = index;
                }
                index++;
                nodo_actual = nodo_actual.siguiente;
            }
            return index_menor;
        }
        return null; //preferible lanzar una excepción
    }

    public Integer buscarMenor(int X, int Y) {
        if (!estaVacia() & this.nodoInicial.dato.getClass().toString().equals("class java.lang.Integer")) { //Si la lista contiene números
            if (X >= 0 && Y < longitud()) {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                int index = 0;
                int index_menor = 0;
                int menor = (Integer) nodo_actual.dato;
                while (nodo_actual != null) { //Hasta el último nodo
                    if (X <= index && index <= Y & (Integer) nodo_actual.dato < menor) {
                        menor = (Integer) nodo_actual.dato;
                        index_menor = index;
                    }
                    index++;
                    nodo_actual = nodo_actual.siguiente;
                }
                return index_menor;
            }
        }
        return null;
    }

    public Integer buscarMayor() {
        if (!estaVacia() & this.nodoInicial.dato.getClass().toString().equals("class java.lang.Integer")) { //Si la lista contiene números
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            int index = 0;
            int index_mayor = 0;
            int mayor = (Integer) nodo_actual.dato;
            while (nodo_actual != null) { //Hasta el último nodo
                if ((Integer) nodo_actual.dato > mayor) {
                    mayor = (Integer) nodo_actual.dato;
                    index_mayor = index;
                }
                index++;
                nodo_actual = nodo_actual.siguiente;
            }
            return index_mayor;
        }
        return null; //preferible lanzar una excepción
    }

    public Integer buscarMayor(int X, int Y) {
        if (!estaVacia() & this.nodoInicial.dato.getClass().toString().equals("class java.lang.Integer")) { //Si la lista contiene números
            if (X >= 0 && Y < longitud()) {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                int index = 0;
                int index_mayor = 0;
                int mayor = 0;
                while (nodo_actual != null) { //Hasta el último nodo
                    if (X <= index && index <= Y & (Integer) nodo_actual.dato > mayor) {
                        mayor = (Integer) nodo_actual.dato;
                        index_mayor = index;
                    }
                    index++;
                    nodo_actual = nodo_actual.siguiente;
                }
                return index_mayor;
            }
        }
        return null;
    }

    //Sustituir
    /**
     * Sustituye un dato especificado con otro.
     *
     * @param datoSustituir Object - dato que será sustituido
     * @param datoSustituto Object - dato que sustituirá al dato sustituir
     * @param sustituirTodos boolean - Si es true sustituye todos los datos
     * iguales al dato_sustituir con el dato_sustituto
     */
    @Override
    public void sustituir(E datoSustituir, E datoSustituto, boolean sustituirTodos) {
        if (!estaVacia()) {
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            int index = 0; //Indica la posición durante el recorrido
            while (nodo_actual != null) {
                if (nodo_actual.dato.equals(datoSustituir)) { //Si el dato coincide con el dato a eliminar
                    nodo_actual.dato = datoSustituto;
                }
                nodo_actual = nodo_actual.siguiente;
            }
        }
    }

    /**
     * Sustituye un dato en una posición especificada con otro especificado.
     *
     * @param posicion int - posición del dato a sustituir
     * @param datoSustituto Object - dato que sustituirá al dato sustituir
     * @param sustituirTodos boolean - Si es true sustituye todos los datos
     * iguales al dato en la posición posicion con el dato_sustituto
     */
    @Override
    public void sustituir(int posicion, E datoSustituto, boolean sustituirTodos) {
        if (!estaVacia()) {
            if (posicion < longitud()) {
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                int index = 0; //Indica la posición durante el recorrido
                while (nodo_actual != null) {
                    if (index == posicion) { //Si la posición es la buscada
                        nodo_actual.dato = datoSustituto;
                    }
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
            }
        }
    }

    //Mover
    /**
     * Intercambia dos elementos de la lista entre sí indicados por sus
     * posiciones.
     *
     * @param posicionX
     * @param posicionY
     */
    @Override
    public void mover(int posicionX, int posicionY) {
        if (!estaVacia()) {
            //Si la posición es valida y la posicion X es mayor que cero
            if (posicionX > 0 & posicionY < longitud() & posicionX <= posicionY) {
                NodoSimple<E> nodoX = new NodoSimple<>(buscar(posicionX)); //Crea un nodo con el dato del nodo de partida
                NodoSimple<E> nodoY = new NodoSimple<>(buscar(posicionY)); //Crea un nodo con el dato del nodo de llegada
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int index = 0;
                while (index < posicionX) { //Hasta llegar al nodoX en la lista
                    nodo_preActual = nodo_actual;
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
                nodoY.siguiente = nodo_actual.siguiente; //Los nodos siguientes a Y serán los nodos siguientes al actual
                if (nodo_preActual != null) {
                    nodo_preActual.siguiente = nodoY; //El nodo actual es remplazado por el nodo Y
                }
                while (index < posicionY) { //Hasta llegar al nodoY en la lista
                    nodo_preActual = nodo_actual;
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
                nodoX.siguiente = nodo_actual.siguiente; //Los nodos siguientes a X serán los nodos siguientes al actual
                if (nodo_preActual != null) {
                    nodo_preActual.siguiente = nodoX; //El nodo actual es remplazado por el nodo X
                }
            } //Si la posición es valida y la posicion X es igual a cero
            else if (posicionX == 0 & posicionY < longitud() & posicionX <= posicionY) {
                NodoSimple<E> nodoX = new NodoSimple<>(buscar(posicionX)); //Crea un nodo con el dato del nodo de partida
                NodoSimple<E> nodoY = new NodoSimple<>(buscar(posicionY)); //Crea un nodo con el dato del nodo de llegada
                NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
                NodoSimple<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int index = 0;

                nodoY.siguiente = nodoInicial.siguiente; //El los nodos siguientes a Y son remplazados con los siguientes al Head
                nodoInicial = nodoY; //El nodo Head es remplazado con el nodo Y
                while (index < posicionY) { //Hasta llegar al nodoY en la lista
                    nodo_preActual = nodo_actual;
                    nodo_actual = nodo_actual.siguiente;
                    index++;
                }
                nodoX.siguiente = nodo_actual.siguiente; //Los nodos siguientes al nodo X son remplazados con los siguientes al actual
                if (nodo_preActual != null) {
                    nodo_preActual.siguiente = nodoX; //El nodo actual es remplazado con el nodo X
                }
            }
        }
    }

    //Información de la lista
    /**
     * Calcula el número de apariciones de un elemento en la lista.
     *
     * @param dato Object
     * @return int - número de apariciones
     */
    @Override
    public int apariciones(E dato) {
        if (!estaVacia()) {
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            int apariciones = 0;
            while (nodo_actual != null) { //Hasta el último nodo
                if (nodo_actual.dato.equals(dato)) { //Si hay una coincidencia
                    apariciones++;
                }
                nodo_actual = nodo_actual.siguiente;
            }
            return apariciones; //Completó el recorrido y retorna el número de apariciones del dato
        } else {
            return -1; //Si la lista está vacia
        }
    }

    /**
     * retorna la longitud de la lista.
     *
     * @return int - longitud de la lista
     */
    @Override
    public int longitud() {
        NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
        int num_nodos = 0;
        while (nodo_actual != null) {
            num_nodos++;
            nodo_actual = nodo_actual.siguiente;
        }
        return num_nodos;
    }

    /**
     * Compara dos listas simples y determina si son iguales dependiendo de la
     * cantidad de elementos contenidos en ellas y los propios elementos.
     *
     * @param lista - ListaSimple
     * @return boolean - true si las listas son iguales
     */
    @Override
    public boolean comparar(ListaSimple<E> lista) {
        if (this.longitud() == lista.longitud()) { //Si tienen la misma longitud
            for (int i = 0; i < this.longitud(); i++) { //Recorrido de las listas
                if (!this.buscar(i).equals(lista.buscar(i))) { //Compara los datos en la posición i en cada lista
                    return false; //Si alguno de los dato no es igual a su correspondiente en la otra lista
                }
            }
            return true; //Si todos son iguales
        } else {
            return false; //Sino tienen la misma longitud
        }
    }

    /**
     * Determina si una lista está contenida en otra.
     *
     * @param lista ListaSimple - lista mayor
     * @return boolean - true si la lista que llama al método está contenida en
     * la lista especificada
     */
    public boolean continidaEn(ListaSimple<E> lista) {
        for (int i = 0; i < this.longitud(); i++) { //Recorrido de la sub-lista
            if (!lista.buscar(i).equals(this.buscar(i))) { //Compara los datos de la sub-lista con los de la lista en i
                return false; //Si algún dato es distinto (No está contenida)
            }
        }
        return true; //Si se completó el recorrido de la sub-lista y todos los elementos en las posiciones indicadas
        //Están en la lista (Está contenida)
    }

    /**
     * Determina si el número resultante de concatenar los números de una lista
     * de Integer's es divisible entre once. Ejemplo: [5,1,4,8], el resultado de
     * la concatenación de estos números es 5148, si 5+4=1+8 es divisible entre
     * once.
     *
     * @return boolean - true si es divisible entre once
     */
    public boolean divisibleOnce() {
        if (!estaVacia()) {
            try {
                int suma_prev = 0; //Guarda el valor correspondiente a la suma previa
                for (int i = 0; i < longitud() - 2; i++) { //Hasta dos posiciones antes del final de la lista
                    int dato_uno = Integer.parseInt(buscar(i).toString()); //Dato en la posición i
                    int dato_dos = Integer.parseInt(buscar(i + 2).toString()); //Dato dos posiciones adelante

                    if (i != 0 && dato_uno + dato_dos != suma_prev) {
                        //Retorna falso en el momento en que la suma de los datos deja de ser igual a
                        //los resultados anteriores
                        return false;
                    }
                    suma_prev = dato_uno + dato_dos;
                }
                return true; //Si todas las sumas tuvieron el mismo resultado
            } catch (NumberFormatException ex) {
                return false; //Si la lista contiene datos != de Integer's
            }
        } else {
            return false; //Si la list está vacia
        }
    }

    /**
     * Retorna el estado de la lista.
     *
     * @return boolean - true si está vacia
     */
    @Override
    public boolean estaVacia() {
        return nodoInicial == null;
    }

    /**
     * Retorna un String con todos los elementos contenidos en la lista.
     *
     * @return String - elementos de la lista
     */
    @Override
    public String toString() {
        if (!estaVacia()) {
            String lista = "[";
            NodoSimple<E> nodo_actual = nodoInicial; //Nodo recorredor (Pointer)
            lista += nodoInicial.toString(); //Agrega el nodo Head
            while (nodo_actual.siguiente != null) { //Siempre que no sea el último nodo
                nodo_actual = nodo_actual.siguiente; //Avanza un nodo
                lista += ", " + nodo_actual.toString(); //Agrega el nodo al String
            }
            lista += "]"; //Cierra la lista
            return lista;
        } else {
            return "";
        }
    }

    //Clase nodo simple
    protected class NodoSimple<E> implements Serializable {

        E dato;
        NodoSimple<E> siguiente;

        public NodoSimple(E dato) {
            this.dato = dato;
        }

        public E getDato() {
            return dato;
        }

        public void setDato(E dato) {
            this.dato = dato;
        }

        public NodoSimple<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoSimple<E> siguiente) {
            this.siguiente = siguiente;
        }

        @Override
        public String toString() {
            return "" + dato;
        }
    }

    //Serialización
    @Override
    public void serializar(String nombreArchivo) {
        FileOutputStream file_output;
        try {
            file_output = new FileOutputStream(nombreArchivo);
            ObjectOutputStream output = new ObjectOutputStream(file_output);
            output.writeObject(this);
            output.close();
            file_output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListaSimple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ListaSimple<E> deserializar(String nombreArchivo) {
        try {
            FileInputStream file_input = new FileInputStream(nombreArchivo);
            ObjectInputStream input = new ObjectInputStream(file_input);
            ListaSimple lista_leida = (ListaSimple) input.readObject();
            input.close();
            file_input.close();
            return lista_leida;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListaSimple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(ListaSimple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaSimple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
