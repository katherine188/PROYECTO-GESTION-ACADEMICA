package arbol;

import modelo.Materia;

public class NodoABB {

    Materia materia;
    NodoABB izquierda;
    NodoABB derecha;

    public NodoABB(Materia materia) {
        this.materia = materia;
        this.izquierda = null;
        this.derecha = null;
    }
}