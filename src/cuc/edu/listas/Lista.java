package cuc.edu.listas;

public interface Lista<E> {

    //Adicionar elementos
    void adicionarElemento(E dato);

    void adicionarElemento(E dato, int posicion);

    void adicionarInicio(E dato);

    void adicionarFinal(E dato);
    
    void adicionarAgrupando(E dato);

    //Eliminar elementos
    void eliminarElemento(E dato, boolean eliminarTodos);

    void eliminarElemento(int posicion, boolean eliminarTodos);

    void eliminarElemento(int posicionX, int posicionY);

    void eliminarInicial();

    void eliminarUltimo();
    
    void eliminarElemento(E dato, int aparicion);

    //Buscar elementos
    int buscar(E dato);

    E buscar(int posicion);

    ListaSimple<E> buscar(int posicionX, int posicionY);

    E buscarInicial();

    E buscarPreFinal();

    E buscarFinal();
    
    E buscarIntermedio();

    ListaSimple<Integer> buscarTodos(E dato);

    //Sustituir elementos
    void sustituir(E datoSustituir, E datoSustituto, boolean sustituirTodos);

    void sustituir(int posicion, E datoSustituto, boolean sustituirTodos);

    //Mover elementos
    void mover(int posicionX, int posicionY);

    //Métodos informativos
    int apariciones(E dato);

    int longitud();

    boolean comparar(ListaSimple<E> lista);
    
    boolean estaVacia();
    
    //Serialización
    void serializar(String nombreArchivo);
    
    ListaSimple<E> deserializar(String nombreArchivo);
}
