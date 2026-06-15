package arbol;

import modelo.Materia;

public class ArbolABB {

    private arbol.NodoABB raiz;

    public ArbolABB() {
        raiz = null;
    }

    //ESTA VACIO

    public boolean estaVacio() {
        return raiz == null;
    }

    // INSERTAR

    public void insertar(Materia materia) {
        raiz = insertarRec(raiz, materia);
    }

    private arbol.NodoABB insertarRec(arbol.NodoABB actual, Materia materia) {

        if (actual == null) {
            return new arbol.NodoABB(materia);
        }

        if (materia.getCodigo() < actual.materia.getCodigo()) {

            actual.izquierda =
                    insertarRec(actual.izquierda, materia);

        } else if (materia.getCodigo() > actual.materia.getCodigo()) {

            actual.derecha =
                    insertarRec(actual.derecha, materia);
        }

        return actual;
    }

    // BUSCAR

    public Materia buscar(int codigo) {

        arbol.NodoABB resultado =
                buscarRec(raiz, codigo);

        if (resultado != null) {
            return resultado.materia;
        }

        return null;
    }

    private arbol.NodoABB buscarRec(arbol.NodoABB actual, int codigo) {

        if (actual == null ||
                actual.materia.getCodigo() == codigo) {

            return actual;
        }

        if (codigo < actual.materia.getCodigo()) {

            return buscarRec(
                    actual.izquierda,
                    codigo
            );
        }

        return buscarRec(
                actual.derecha,
                codigo
        );
    }

    // MODIFICAR

    public void modificar(int codigo,
                          String nuevoNombre) {

        Materia materia = buscar(codigo);

        if (materia != null) {
            materia.setNombre(nuevoNombre);
        }
    }

    // ELIMINAR

    public void eliminar(int codigo) {

        raiz = eliminarRec(raiz, codigo);
    }

    private arbol.NodoABB eliminarRec(
            arbol.NodoABB actual,
            int codigo) {

        if (actual == null) {
            return null;
        }

        if (codigo < actual.materia.getCodigo()) {

            actual.izquierda =
                    eliminarRec(
                            actual.izquierda,
                            codigo
                    );

        } else if (codigo >
                actual.materia.getCodigo()) {

            actual.derecha =
                    eliminarRec(
                            actual.derecha,
                            codigo
                    );

        } else {

            // Caso 1: sin hijo izquierdo

            if (actual.izquierda == null) {
                return actual.derecha;
            }

            // Caso 2: sin hijo derecho

            if (actual.derecha == null) {
                return actual.izquierda;
            }

            // Caso 3: dos hijos

            arbol.NodoABB sucesor =
                    encontrarMinimo(
                            actual.derecha
                    );

            actual.materia = sucesor.materia;

            actual.derecha =
                    eliminarRec(
                            actual.derecha,
                            sucesor.materia.getCodigo()
                    );
        }

        return actual;
    }

    private arbol.NodoABB encontrarMinimo(
            arbol.NodoABB nodo) {

        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }

        return nodo;
    }

    // RECORRIDO INORDEN

    public void mostrarInorden() {

        if (raiz == null) {

            System.out.println("No hay materias registradas.");

        } else {

            inorden(raiz);
        }
    }

    public void inorden(arbol.NodoABB nodo) {

        if (nodo != null) {

            inorden(nodo.izquierda);

            System.out.println(
                    nodo.materia
            );

            inorden(nodo.derecha);
        }
    }

}
