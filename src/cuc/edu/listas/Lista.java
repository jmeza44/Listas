package cuc.edu.listas;

public interface Lista<E> {

    //Adicionar elementos
    void adicionarElemento(E dato);

    void adicionarElemento(E dato, int posicion);

    void adicionarInicio(E dato);

    void adicionarFinal(E dato);

    //Eliminar elementos
    void eliminarElemento(E dato, boolean eliminar_todos);

    void eliminarElemento(int posicion, boolean eliminar_todos); //Posible incompatibilidad

    void eliminarElemento(int posicionX, int posicionY);

    void eliminarInicial();

    void eliminarUltimo();

    //Buscar elementos
    int buscar(E dato);

    E buscar(int posicion);

    ListaSimple<E> buscar(int posicionX, int posicionY);

    E buscarInicial();

    E buscarPreFinal();

    E buscarFinal();

    ListaSimple<Integer> buscarTodos(E dato);

    //Sustituir elementos
    void sustituir(E dato_sustituir, E dato_sustituto, boolean sustituir_todos);

    void sustituir(int posicio, E dato_sustituto, boolean sustituir_todos);

    //Mover elementos
    void mover(int posicionX, int posicionY);

    //Métodos informativos
    int apariciones(E dato);

    int longitud();

    boolean estaVacia();
}
