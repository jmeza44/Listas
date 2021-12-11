package cuc.edu.taller_desarr_tres;

import cuc.edu.listas.ListaDoble;

public class Calificaciones {

    protected ListaDoble<Estudiante> estudiantes;

    //Constructor
    public Calificaciones() {
        this.estudiantes = new ListaDoble<>();
    }

    //Adicion
    public boolean adicionarEstudainte(Estudiante estudiante) {
        if (buscarPorNombre(estudiante.nombre) == null) {
            estudiantes.adicionarFinal(estudiante);
            return true;
        } else {
            return false;
        }
    }

    public void adicionarEstudiante(Estudiante estudiante, int posicion) {
        estudiantes.adicionarElemento(estudiante, posicion);
    }

    //Eliminar
    public void sacarEstudiante(String nombre) {
        estudiantes.eliminarElemento(buscarPorNombre(nombre), false);
    }

    //Buscar
    public Estudiante buscarPorNombre(String nombre) {
        if (!estudiantes.estaVacia()) {
            for (int i = 0; i < estudiantes.longitud(); i++) {
                if (estudiantes.buscar(i).nombre.equals(nombre)) {
                    return estudiantes.buscar(i);
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public Estudiante buscarPorPosicion(int posicion) {
        return estudiantes.buscar(posicion);
    }

    //informacion
    public int numeroEstudiantes() {
        return estudiantes.longitud();
    }

    public Estudiante menorPromedio() {
        Double promedio_menor = 5.1;
        int posicion_promedio_menor = -1;
        for (int i = 0; i < estudiantes.longitud(); i++) {
            Estudiante estudiante = estudiantes.buscar(i);
            Double promedio = (estudiante.nota_uno + estudiante.nota_dos + estudiante.nota_tres + estudiante.nota_cuatro) / 4;
            if (promedio < promedio_menor) {
                promedio_menor = promedio;
                posicion_promedio_menor = i;
            }
        }
        if (posicion_promedio_menor != -1) {
            return estudiantes.buscar(posicion_promedio_menor);
        } else {
            return null;
        }
    }

    public int numeroAprobados() {
        int aprobados = 0;
        for (int i = 0; i < estudiantes.longitud(); i++) {
            Estudiante estudiante = estudiantes.buscar(i);
            Double promedio = (estudiante.nota_uno + estudiante.nota_dos + estudiante.nota_tres + estudiante.nota_cuatro) / 4;
            if (promedio > 3.0) {
                aprobados++;
            }
        }
        return aprobados;
    }
}
