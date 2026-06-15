package grafo;

import java.util.ArrayList;

public class Grafo {

    private ArrayList<String> vertices;
    private ArrayList<ArrayList<Integer>> adyacencias;

    public Grafo() {

        vertices = new ArrayList<>();
        adyacencias = new ArrayList<>();
    }

    //AGREGAR VERTICE

    public void agregarVertice(String materia) {

        vertices.add(materia);
        adyacencias.add(new ArrayList<>());
    }

    //AGREGAR ARISTA

    public boolean agregarArista(String origen, String destino) {

        int i = vertices.indexOf(origen);
        int j = vertices.indexOf(destino);

        if(i != -1 && j != -1) {

            adyacencias.get(i).add(j);

            return true;
        }

        return false;
    }

    //MOSTRAR RELACIONES

    public void mostrarRelaciones() {

        for (int i = 0; i < vertices.size(); i++) {

            System.out.print(vertices.get(i) + " -> ");

            for (Integer vecino : adyacencias.get(i)) {
                System.out.print(vertices.get(vecino) + " ");
            }

            System.out.println();
        }
    }

    //RECORRIDO DFS

    public void DFS(String inicio) {

        boolean[] visitado = new boolean[vertices.size()];

        int indice = vertices.indexOf(inicio);

        if (indice != -1) {
            dfsRec(indice, visitado);
        }
    }

    private void dfsRec(int vertice, boolean[] visitado) {

        visitado[vertice] = true;

        System.out.println(vertices.get(vertice));

        for (Integer vecino : adyacencias.get(vertice)) {

            if (!visitado[vecino]) {
                dfsRec(vecino, visitado);
            }
        }
    }

    //MOSTRAR PREREQUISITOS

    public void mostrarPrerequisitos(String materia) {

        int destino = vertices.indexOf(materia);

        if(destino == -1) {
            System.out.println("La materia no existe.");
            return;
        }

        boolean tienePrerequisitos = false;

        System.out.println("Prerequisitos de " + materia + ":");

        boolean encontrado = false;

        for(int i = 0; i < adyacencias.size(); i++) {

            if(adyacencias.get(i).contains(destino)) {

                System.out.println("- " + vertices.get(i));

                encontrado = true;
            }
        }

        if(!encontrado) {
            System.out.println("No tiene prerequisitos.");
        }
    }


    public boolean existeMateria(String materia) {

        for (String v : vertices) {

            if (v.trim().equalsIgnoreCase(materia.trim())) {
                return true;
            }
        }

        return false;
    }

}