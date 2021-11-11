package cuc.edu.listas;

import java.util.Objects;

public class ListaDoble<E> extends ListaSimple<E> {

    protected NodoDoble<E> nodoTail;

    //Adicionar
    /**
     * Adiciona un elemento a la lista.
     *
     * @param dato Object
     */
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

    /**
     * Adiciona un elemento a la lista en una posición especifica.
     *
     * @param dato Object
     * @param posicion int
     */
    @Override
    public void adicionarElemento(E dato, int posicion) {
        if (!estaVacia()) {
            if (posicion < longitud()) {
                NodoDoble<E> nodo_entrada = new NodoDoble<>(dato); //Crea un nodo con el parametro
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                NodoDoble<E> nodo_preActual = null; //Nodo recorredor (Pointer)
                int index = 0; //Indica la posición durante el recorrido
                if (posicion == 0) {
                    adicionarInicio(dato);
                } else if (posicion == longitud() - 1) {
                    adicionarFinal(dato);
                } else {
                    while (nodo_actual != null) {
                        if (index == posicion) { //Si la posición es la buscada
                            nodo_entrada.previo = nodo_actual.previo;
                            nodo_entrada.siguiente = nodo_actual;
                            nodo_actual.previo = nodo_entrada;
                            nodo_preActual.siguiente = nodo_entrada;
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

    /**
     * Adiciona un elemento al inicio de la lista.
     *
     * @param dato Object
     */
    @Override
    public void adicionarInicio(E dato) {
        if (!estaVacia()) {
            NodoDoble<E> nodo_entrada = new NodoDoble<>(dato); //Crea un nodo con el parametro
            nodo_entrada.siguiente = nodoHead;
            ((NodoDoble) nodoHead).previo = nodo_entrada;
            nodoHead = nodo_entrada;
        } else {
            adicionarElemento(dato);
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
            NodoDoble<E> nodo_entrada = new NodoDoble<>(dato); //Crea un nodo con el parametro
            nodo_entrada.previo = nodoTail;
            nodoTail.siguiente = nodo_entrada;
            nodoTail = nodo_entrada;
        } else {
            adicionarElemento(dato);
        }
    }

    //Eliminar
    /**
     * Elimina elementos de la lista.
     *
     * @param dato Object
     * @param eliminar_todos Si es true se eliminan todas la apariciones del
     * elemento en la lista
     */
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

    public void eliminarElemento(E dato, int aparicion) {
        if (!estaVacia()) {
            if (aparicion > 0 && aparicion <= apariciones(dato)) {
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int aparicion_aux = 0; //Contador de apariciones del dato en la lista
                while (nodo_actual != null) {
                    if (nodo_actual.dato.equals(dato)) {
                        aparicion_aux++;
                    }
                    if (aparicion_aux == aparicion && nodo_preActual == null) { //Si se llegó a la enésima aparición & es el nodo Head
                        eliminarInicial();
                        break;
                    } else if (aparicion_aux == aparicion && nodo_actual.siguiente == null) { //Si se llegó a la enésima aparición & es el nodo Tail
                        eliminarUltimo();
                        break;
                    } else if (aparicion_aux == aparicion && nodo_preActual != null) { //Si se llegó a la enésima aparición & no es el nodo Head
                        nodo_preActual.siguiente = nodo_actual.siguiente;
                        ((NodoDoble<E>) nodo_actual.siguiente).previo = nodo_preActual;
                        break;
                    }
                    nodo_preActual = nodo_actual;
                    nodo_actual = (NodoDoble) nodo_actual.siguiente;
                }
            }
        }
    }

    /**
     * Elimina elementos de la lista en una posición especifica.
     *
     * @param posicion int
     * @param eliminar_todos Si es true se eliminan todas la apariciones del
     * elemento en la lista
     */
    @Override
    public void eliminarElemento(int posicion, boolean eliminar_todos) {
        if (!estaVacia()) {
            if (posicion >= 0 && posicion < longitud()) { //Si la posición es valida, positiva
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
            } else if (posicion < 0 && (posicion * -1) <= longitud()) { //Si la posición es negativa y menor o igual a la longitud
                NodoDoble<E> nodo_actual = (NodoDoble) nodoTail; //Nodo recorredor (Pointer)
                NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                int index = -1; //Iniciando en la última posición
                if ((posicion * -1) == longitud()) { //Si es el nodo Head
                    eliminarInicial();
                } else if (posicion == -1) { //Si es el nodo Tail
                    eliminarUltimo();
                } else { //Si es un nodo intermedio
                    while (nodo_actual != null) {
                        if (index == posicion) {
                            nodo_preActual.previo = nodo_actual.previo;
                            nodo_actual.previo.siguiente = nodo_actual.siguiente;
                            break;
                        }
                        nodo_preActual = nodo_actual;
                        nodo_actual = nodo_actual.previo;
                        index--;
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

    /**
     * Elimina el primer elemento de la lista.
     */
    @Override
    public void eliminarInicial() {
        if (!estaVacia()) {
            nodoHead = nodoHead.siguiente;
            ((NodoDoble) nodoHead).previo = null;
        }
    }

    /**
     * Elimina el último elemento de la lista.
     */
    @Override
    public void eliminarUltimo() {
        if (!estaVacia()) {
            nodoTail = nodoTail.previo;
            nodoTail.siguiente = null;
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

    /**
     * Busca los elementos de la lista en un intervalo dentro de ella.
     *
     * @param posicionX inicio del intervalo a buscar
     * @param posicionY fin del intervalo a buscar
     * @return ListaDoble<> - Sublista en el intervalo determinado
     */
    @Override
    public ListaDoble buscar(int posicionX, int posicionY) {
        if (!estaVacia()) {
            if (posicionX >= 0 & posicionY < longitud() & posicionX <= posicionY) {
                ListaDoble<E> lista_resultado = new ListaDoble<>();
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

    /**
     * Busca el primer elemento de la lista.
     *
     * @return Object en la primera posición
     */
    @Override
    public E buscarInicial() {
        if (!estaVacia()) {
            return nodoHead.dato;
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
            return nodoTail.previo.dato;
        } else {
            return null; //Si está vacia o si no hay más de un nodo
        }
    }

    /**
     * Busca el último elemento de la lista.
     *
     * @return Object en la última posición
     */
    @Override
    public E buscarFinal() {
        if (!estaVacia()) {
            return nodoTail.dato;
        } else {
            return null; //Si está vacia
        }
    }

    @Override
    public E buscarIntermedio() {
        return buscar(longitud() / 2); //longitud()/2 da como resultado un entero
        //Ejemplo: 5/2 = 2
    }

    /**
     * Busca todas las posiciones en las que se encuentra un elemento.
     *
     * @param dato Object - dato a buscar
     * @return ListaSimple - posiciones
     */
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

    /**
     * Busca el elemento previo a un elemento especifico, si aparece múltiples
     * veces en la lita se tendrá más de un elemento previo, exactamente uno
     * para cada vez que aparezca el dato en la lista.
     *
     * @param dato Object - dato a buscar
     * @return ListaSimple<Integer> - posiciones
     */
    public ListaSimple<E> buscarPrevios(E dato) {
        if (!estaVacia()) {
            ListaSimple<E> lista_previos = new ListaSimple<>();
            NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
            while (nodo_actual != null) { //Hasta el último nodo
                if (nodo_actual.dato.equals(dato)) { //Si hay una coincidencia
                    if (nodo_actual.previo != null) {
                        lista_previos.adicionarElemento(nodo_actual.previo.dato); //Agrega la posición a la lista
                    } else {
                        lista_previos.adicionarElemento(null);
                    }
                }
                nodo_actual = (NodoDoble) nodo_actual.siguiente;
            }
            return lista_previos;
        } else {
            return null; //Si está vacia
        }
    }

    //Sustituir
    /**
     * Sustituye un dato especificado con otro.
     *
     * @param dato_sustituir Object - dato que será sustituido
     * @param dato_sustituto Object - dato que sustituirá al dato sustituir
     * @param sustituir_todos boolean - Si es true sustituye todos los datos
     * iguales al dato_sustituir con el dato_sustituto
     */
    @Override
    public void sustituir(E dato_sustituir, E dato_sustituto, boolean sustituir_todos) {
        if (!estaVacia()) {
            if (sustituir_todos) {
                while (apariciones(dato_sustituir) > 0) {
                    sustituir(dato_sustituir, dato_sustituto, false);
                }
            } else {
                NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                int index = 0; //Indica la posición durante el recorrido
                while (nodo_actual != null) {
                    if (nodo_actual.dato.equals(dato_sustituir)) { //Si el dato coincide con el dato a eliminar
                        nodo_actual.dato = dato_sustituto;
                    }
                    nodo_actual = (NodoDoble) nodo_actual.siguiente;
                }
            }
        }
    }

    /**
     * Sustituye un dato en una posición especificada con otro especificado.
     *
     * @param posicion int - posición del dato a sustituir
     * @param dato_sustituto Object - dato que sustituirá al dato sustituir
     * @param sustituir_todos boolean - Si es true sustituye todos los datos
     * iguales al dato en la posición posicion con el dato_sustituto
     */
    @Override
    public void sustituir(int posicion, E dato_sustituto, boolean sustituir_todos) {
        if (!estaVacia()) {
            if (sustituir_todos) {
                E dato = buscar(posicion); //Dato a sustituir
                while (apariciones(dato) > 0) {
                    sustituir(buscar(posicion), dato_sustituto, false);
                }
            } else {
                if (posicion < longitud()) {
                    NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                    int index = 0; //Indica la posición durante el recorrido
                    while (nodo_actual != null) {
                        if (index == posicion) { //Si la posición es la buscada
                            nodo_actual.dato = dato_sustituto;
                        }
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
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
            if (posicionX >= 0 & posicionY < longitud() & posicionX <= posicionY) {
                if (posicionX == 0) { //Si el nodo de partida es el nodo Head
                    NodoDoble<E> nodoX = new NodoDoble<>(buscar(posicionX)); //Crea un nodo con el dato del nodo de partida
                    NodoDoble<E> nodoY = new NodoDoble<>(buscar(posicionY)); //Crea un nodo con el dato del nodo de llegada
                    NodoDoble<E> nodo_actual = (NodoDoble) nodoHead; //Nodo recorredor (Pointer)
                    NodoDoble<E> nodo_preActual = null; //Nodo previo al recorredor (Pointer)
                    int index = 0;
                    nodoY.siguiente = (NodoDoble) nodoHead.siguiente; //El los nodos siguientes a Y son remplazados con los siguientes al Head
                    nodoY.previo = null;
                    ((NodoDoble) nodoHead.siguiente).previo = nodoY;
                    nodoHead = nodoY; //El nodo Head es remplazado con el nodo Y
                    while (index < posicionY) { //Hasta llegar al nodoY en la lista
                        nodo_preActual = nodo_actual;
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                    nodoX.siguiente = nodo_actual.siguiente; //Los nodos siguientes al nodo X son remplazados con los siguientes al actual
                    nodoX.previo = nodo_actual.previo;
                    if (nodo_actual.siguiente != null) {
                        ((NodoDoble) nodo_actual.siguiente).previo = nodoX;
                    } else {
                        nodoTail = nodoX;
                    }
                    nodo_preActual.siguiente = nodoX; //El nodo actual es remplazado con el nodo X
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
                    nodoY.previo = nodo_actual.previo; //Los nodos previos a Y serán los nodos previos al actual
                    ((NodoDoble) nodo_actual.siguiente).previo = nodoY;
                    nodo_preActual.siguiente = nodoY; //El nodo actual es remplazado por el nodo Y
                    while (index < posicionY) { //Hasta llegar al nodoY en la lista
                        nodo_preActual = nodo_actual;
                        nodo_actual = (NodoDoble) nodo_actual.siguiente;
                        index++;
                    }
                    nodoX.siguiente = nodo_actual.siguiente; //Los nodos siguientes a X serán los nodos siguientes al actual
                    nodoX.previo = nodo_actual.previo; //Los nodos previos a X serán los nodos previos al actaul
                    if (nodo_actual.siguiente != null) {
                        ((NodoDoble) nodo_actual.siguiente).previo = nodoY;
                    } else {
                        nodoTail = nodoX;
                    }
                    nodo_preActual.siguiente = nodoX; //El nodo actual es remplazado por el nodo X
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

    /**
     * retorna la longitud de la lista.
     *
     * @return int - longitud de la lista
     */
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

    /**
     * Retorna el estado de la lista.
     *
     * @return boolean - true si está vacia
     */
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

    /**
     * Retorna un String con todos los elementos contenidos en la lista desde el
     * último al primero.
     *
     * @return String - elementos de la lista
     */
    public String reverseToString() {
        if (!estaVacia()) {
            String lista = "[";
            NodoDoble<E> nodo_actual = nodoTail; //Nodo recorredor (Pointer)
            lista += nodoTail.toString(); //Agrega el nodo Tail
            while (nodo_actual.previo != null) { //Siempre que no sea el primer nodo
                nodo_actual = nodo_actual.previo; //Avanza un nodo
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
