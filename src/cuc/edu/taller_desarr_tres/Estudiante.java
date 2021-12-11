package cuc.edu.taller_desarr_tres;

public class Estudiante {

    protected String nombre;
    protected Double nota_uno, nota_dos, nota_tres, nota_cuatro;

    public Estudiante(String nombre, Double nota_uno, Double nota_dos, Double nota_tres, Double nota_cuatro) {
        this.nombre = nombre;
        this.nota_uno = nota_uno;
        this.nota_dos = nota_dos;
        this.nota_tres = nota_tres;
        this.nota_cuatro = nota_cuatro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getNota_uno() {
        return nota_uno;
    }

    public void setNota_uno(Double nota_uno) {
        this.nota_uno = nota_uno;
    }

    public Double getNota_dos() {
        return nota_dos;
    }

    public void setNota_dos(Double nota_dos) {
        this.nota_dos = nota_dos;
    }

    public Double getNota_tres() {
        return nota_tres;
    }

    public void setNota_tres(Double nota_tres) {
        this.nota_tres = nota_tres;
    }

    public Double getNota_cuatro() {
        return nota_cuatro;
    }

    public void setNota_cuatro(Double nota_cuatro) {
        this.nota_cuatro = nota_cuatro;
    }
}
