package cuc.edu.listas;

import java.util.Objects;

public class ListaDoble<E> extends ListaSimple<E> {

    protected NodoDoble<E> nodoTail;

    @Override
    public void adicionarElemento(E dato) {
        NodoDoble<E> nodo_entrada = new NodoDoble<>(dato);
        if (estaVacia()) {
            nodoHead = nodoTail = nodo_entrada;
        } else {
            nodoTail.siguiente = nodo_entrada;
            nodo_entrada.previo = nodoTail;
            nodoTail = nodo_entrada;
        }
    }

    @Override
    public void adicionarElemento(E dato, int posicion) {
        if (!estaVacia()) {
            if (posicion < longitud()) {
                NodoDoble<E> nodo_entrada = new NodoDoble<>(dato); //Crea un nodo con el parametro
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                int index = 0; //Indica la posición durante el recorrido
                while (nodo_actual != null) {
                    if (index == posicion - 1) { //Si la posición es la anterior a la buscada
                        //Los nodos posteriores a nodo entrada serán iguales a los nodos posteriores al actual
                        nodo_entrada.siguiente = nodo_actual.siguiente;
                        //El nodo precio al nodo entrada será el nodo actual
                        nodo_entrada.previo = nodo_actual;
                        //El nodo siguiente al actual será remplazado con el nodo entrada
                        nodo_actual.siguiente = nodo_entrada;
                        break;
                    }
                    nodo_actual = (NodoDoble) nodo_actual.siguiente;
                    nodo_actual.previo = nodo_actual;
                    index++;
                }
            }
        }
    }

    @Override
    public void adicionarInicio(E dato) {
        if (!estaVacia()) {
            NodoDoble<E> nodo_entrada = new NodoDoble<>(dato); //Crea un nodo con el parametro
            nodo_entrada.siguiente = nodoHead;
            nodoHead = nodo_entrada;
        } else {
            adicionarElemento(dato);
        }
    }

    @Override
    public void adicionarFinal(E dato) {
        if (!estaVacia()) {
            NodoDoble<E> nodo_entrada = new NodoDoble<>(dato); //Crea un nodo con el parametro
            nodo_entrada.previo = nodoTail;
            nodoTail.siguiente = nodo_entrada;
            nodoTail = nodo_entrada;
        } else {
            adicionarElemento(dato);
        }
    }

    @Override
    public void eliminarElemento(E dato, boolean eliminar_todos) {
        if (!estaVacia()) {
            if (eliminar_todos) {
                while (apariciones(dato) > 0) { //Mientras quede alguno
                    eliminarElemento(dato, false);
                }
            } else {
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                while (nodo_actual != null) {
                    if (nodo_actual.dato.equals(dato)) { //Si encuentra el dato en la lista
                        if (nodo_actual.previo == null) { //Si es el nodo Head
                            eliminarInicial();
                        } else if (nodo_actual.siguiente == null) { //Si es el nodo Tail
                            eliminarUltimo();
                        } else { //Si es un nodo intermedio
                            nodo_preActual.siguiente = nodo_actual.siguiente;
                            ((NodoDoble<E>) nodo_actual.siguiente).previo = nodo_preActual;
                            break;
                        }
                    }
                    nodo_preActual = nodo_actual;
                    nodo_actual = (NodoDoble) nodo_actual.siguiente;
                }
            }
        }
    }

    @Override
    public void eliminarElemento(int posicion, boolean eliminar_todos) {
        if (!estaVacia()) {
            if (posicion >= 0 && posicion < longitud()) { //Si la posición es valida
                if (eliminar_todos) {
                    E dato = buscar(posicion); //Dato a eliminar
                    while (apariciones(dato) > 0) { //Mientras quede alguno
                        eliminarElemento(dato, false);
                    }
                } else {
                    NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                    NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                    int index = 0;
                    if (posicion == 0) { //Si es el nodo Head
                        eliminarInicial();
                    } else if (posicion == longitud() - 1) { //Si es el nodo Tail
                        eliminarUltimo();
                    } else { //Si es un nodo intermedio
                        while (nodo_actual != null) {
                            if (index == posicion) {
                                nodo_preActual.siguiente = nodo_actual.siguiente;
                                ((NodoDoble<E>) nodo_actual.siguiente).previo = nodo_preActual;
                                break;
                            }
                            nodo_preActual = nodo_actual;
                            nodo_actual = (NodoDoble) nodo_actual.siguiente;
                            index++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void eliminarElemento(int posicionX, int posicionY) {
        if (!estaVacia()) {
            //Si el intervalo está entre los límites de la lista y la posición x es menor o igual a la posición y
            if (posicionX >= 0 && posicionY < longitud() && posicionX <= posicionY) {
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int index = 0;
                if (posicionX == 0) { //Si el intervalo a eliminar inicia en cero (nodoHead)
                    while (nodo_actual != null) {
                        if (index == posicionY) { //Al llegar a la posición Y
                            nodo_actual.previo = null;
                            nodoHead = nodo_actual.siguiente;
                            break; //Tras eliminar cierra el ciclo
                        }
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                } else {
                    while (nodo_actual != null) {
                        if (index == posicionX) { //Si se llego al primer nodo a eliminar
                            NodoSimple<E> nodo_aux = nodo_actual; //Nodo recorredor auxiliar (Pointer)
                            int index_aux = index;
                            while (index_aux < posicionY) { //Mientras no se haya llegado al último nodo del intervalo
                                nodo_aux = nodo_aux.siguiente;
                                index_aux++;
                            }
                            //El nodo siguiente al nodo previo del primer nodo del intervalo a eliminar
                            //es remplazado con el nodo siguiente al último nodo a eliminar
                            nodo_preActual.siguiente = nodo_aux.siguiente;
                            //El nodo previo al nodo siguiente al último nodo a eliminar es remplazado
                            //con el nodo previo al primer nodo a eliminar
                            ((NodoDoble) nodo_aux.siguiente).previo = nodo_actual.previo;
                            break; //Tras eliminar cierra el ciclo

                        }
                        nodo_preActual = nodo_actual;
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                }

            }
        }
    }

    @Override
    public void eliminarInicial() {
        if (!estaVacia()) {
            nodoHead = nodoHead.siguiente;
            ((NodoDoble) nodoHead).previo = null;
        }
    }

    @Override
    public void eliminarUltimo() {
        if (!estaVacia()) {
            nodoTail = nodoTail.previo;
            nodoTail.siguiente = null;
        }
    }

    @Override
    public int buscar(E dato) {
        if (!estaVacia()) {
            NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
            int index = 0;
            while (nodo_actual != null) { //Hasta el último nodo
                if (nodo_actual.dato.equals(dato)) { //Si hay una coincidencia
                    return index;
                }
                nodo_actual = (NodoDoble) nodo_actual.siguiente;
                index++;
            }
            return -1; //Si completó el recorrido y no encontró el dato
        } else {
            return -1; //Si la lista está vacia
        }
    }

    @Override
    public E buscar(int posicion) {
        if (!estaVacia()) {
            if (posicion >= 0 && posicion < longitud()) {
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                int index = 0;
                while (nodo_actual != null) { //Recorrido de nodos
                    if (index == posicion) { //Al llegar a la posición requerida
                        return nodo_actual.dato; //Retorna el dato del nodo
                    }
                    nodo_actual = (NodoDoble) nodo_actual.siguiente;
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

    @Override
    public ListaSimple buscar(int posicionX, int posicionY) {
        if (!estaVacia()) {
            if (posicionX >= 0 & posicionY < longitud() & posicionX <= posicionY) {
                ListaSimple<E> lista_resultado = new ListaSimple<>();
                NodoSimple<E> nodo_actual = nodoHead; //Nodo recorredor (Pointer)
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

    @Override
    public E buscarInicial() {
        if (!estaVacia()) {
            return nodoHead.dato;
        } else {
            return null; //Si está vacia
        }
    }

    @Override
    public E buscarPreFinal() {
        if (!estaVacia() && longitud() > 1) {
            return nodoTail.previo.dato;
        } else {
            return null; //Si está vacia o si no hay más de un nodo
        }
    }

    @Override
    public E buscarFinal() {
        if (!estaVacia()) {
            return nodoTail.dato;
        } else {
            return null; //Si está vacia
        }
    }

    @Override
    public ListaSimple buscarTodos(E dato) {
        if (!estaVacia()) {
            ListaSimple<Integer> lista_apariciones = new ListaSimple<>();
            NodoSimple<E> nodo_actual = nodoHead; //Nodo recorredor (Pointer)
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

    @Override
    public void mover(int posicionX, int posicionY) {
        if (!estaVacia()) {
            if (posicionX >= 0 & posicionY < longitud() & posicionX <= posicionY) {
                if (posicionX == 0) { //Si el nodo de partida es el nodo Head
                    NodoDoble<E> nodoX = new NodoDoble<>(buscar(posicionX)); //Crea un nodo con el dato del nodo de partida
                    NodoDoble<E> nodoY = new NodoDoble<>(buscar(posicionY)); //Crea un nodo con el dato del nodo de llegada
                    NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                    NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                    int index = 0;
                    nodoY.siguiente = (NodoDoble) nodoHead.siguiente; //El los nodos siguientes a Y son remplazados con los siguientes al Head
                    nodoY.previo = null;
                    nodoHead = nodoY; //El nodo Head es remplazado con el nodo Y
                    while (index < posicionY) { //Hasta llegar al nodoY en la lista
                        nodo_preActual = nodo_actual;
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                    nodoX.siguiente = nodo_actual.siguiente; //Los nodos siguientes al nodo X son remplazados con los siguientes al actual
                    nodoX.previo = nodo_actual.previo;
                    if (nodo_preActual != null) {
                        nodo_preActual.siguiente = nodoX; //El nodo actual es remplazado con el nodo X
                    }
                } else {
                    NodoDoble<E> nodoX = new NodoDoble<>(buscar(posicionX)); //Crea un nodo con el dato del nodo de partida
                    NodoDoble<E> nodoY = new NodoDoble<>(buscar(posicionY)); //Crea un nodo con el dato del nodo de llegada
                    NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                    NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                    int index = 0;
                    while (index < posicionX) { //Hasta llegar al nodoX en la lista
                        nodo_preActual = nodo_actual;
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                    nodoY.siguiente = nodo_actual.siguiente; //Los nodos siguientes a Y serán los nodos siguientes al actual
                    nodoY.previo = nodo_actual.previo; //Los nodos previos a Y serán los nodos previos al actaul
                    nodo_preActual.siguiente = nodoY; //El nodo actual es remplazado por el nodo Y
                    while (index < posicionY) { //Hasta llegar al nodoY en la lista
                        nodo_preActual = nodo_actual;
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                    nodoX.siguiente = nodo_actual.siguiente; //Los nodos siguientes a X serán los nodos siguientes al actual
                    nodoX.previo = nodo_actual.previo; //Los nodos previos a X serán los nodos previos al actaul
                    nodo_preActual.siguiente = nodoX; //El nodo actual es remplazado por el nodo X
                }
            }
        }
    }

    @Override
    public int apariciones(E dato) {
        if (!estaVacia()) {
            NodoSimple<E> nodo_actual = nodoHead; //Nodo recorredor (Pointer)
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

    @Override
    public int longitud() {
        NodoSimple<E> nodo_actual = nodoHead; //Nodo recorredor (Pointer)
        int num_nodos = 0;
        while (nodo_actual != null) {
            num_nodos++;
            nodo_actual = nodo_actual.siguiente;
        }
        return num_nodos;
    }

    @Override
    public boolean estaVacia() {
        return nodoHead == null & nodoTail == null;
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
            NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
            lista += nodoHead.toString(); //Agrega el nodo Head
            while (nodo_actual.siguiente != null) { //Siempre que no sea el último nodo
                nodo_actual = (NodoDoble) nodo_actual.siguiente; //Avanza un nodo
                lista += ", " + nodo_actual.toString(); //Agrega el nodo al String
            }
            lista += "]"; //Cierra la lista
            return lista;
        } else {
            return "";
        }
    }

    protected class NodoDoble<E> extends NodoSimple<E> {

        NodoDoble<E> previo;

        public NodoDoble(E dato) {
            super(dato);
        }

        public NodoDoble<E> getPrevio() {
            return previo;
        }

        public void setPrevio(NodoDoble<E> previo) {
            this.previo = previo;
        }

        public E getDato() {
            return dato;
        }

        public void setDato(E dato) {
            this.dato = dato;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + Objects.hashCode(this.previo);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final NodoDoble<?> other = (NodoDoble<?>) obj;
            if (!Objects.equals(this.previo, other.previo)) {
                return false;
            }
            return true;
        }
    }
}
