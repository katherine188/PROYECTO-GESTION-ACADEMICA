
package sistema;

import java.util.Scanner;

import arbol.ArbolABB;
import arbol.NodoABB;
import grafo.Grafo;
import modelo.Materia;

    public class SistemaAcademico {

        private ArbolABB arbol;
        private Grafo grafo;
        private Scanner sc;

        public SistemaAcademico() {

            arbol = new ArbolABB();
            grafo = new Grafo();
            sc = new Scanner(System.in);
        }

        public void menu() {

            int opcion;

            do {

                System.out.println("\n===== SISTEMA ACADEMICO =====");
                System.out.println("1. Agregar materia");
                System.out.println("2. Buscar materia");
                System.out.println("3. Modificar materia");
                System.out.println("4. Eliminar materia");
                System.out.println("5. Mostrar materias");
                System.out.println("6. Agregar prerrequisito");
                System.out.println("7. Consultar prerrequisitos");
                System.out.println("8. Ejecutar DFS");
                System.out.println("9. Salir");

                opcion = sc.nextInt();

                switch (opcion) {

                    case 1:
                        agregarMateria();
                        break;

                    case 2:
                        buscarMateria();
                        break;

                    case 3:
                        modificarMateria();
                        break;

                    case 4:
                        eliminarMateria();
                        break;


                        case 5:
                        arbol.mostrarInorden();
                        break;
                    case 6:
                        agregarPrerequisito();
                        break;

                    case 7:
                        consultarPrerequisitos();
                        break;

                    case 8:
                        ejecutarDFS();
                        break;

                }

            } while (opcion != 9);
        }


        //AGREGAR

        private void agregarMateria() {

            int codigo;

            while (true) {

                System.out.print("Codigo (3 cifras): ");

                if (sc.hasNextInt()) {

                    codigo = sc.nextInt();

                    if (codigo < 100 || codigo > 999) {

                        System.out.println(
                                "Error: el codigo debe tener exactamente 3 cifras."
                        );

                        continue;
                    }

                    if (arbol.buscar(codigo) != null) {

                        System.out.println(
                                "Error: ya existe una materia con ese codigo."
                        );

                        continue;
                    }

                    break;

                } else {

                    System.out.println(
                            "Error: solo se permiten numeros."
                    );

                    sc.next();
                }
            }

            sc.nextLine();

            String nombre;

            while (true) {

                System.out.print("Nombre: ");

                nombre = sc.nextLine().trim();

                if (nombre.isEmpty()) {

                    System.out.println(
                            "Error: el nombre no puede estar vacio."
                    );

                } else {

                    break;
                }
            }

            Materia materia = new Materia(codigo, nombre);

            arbol.insertar(materia);

            grafo.agregarVertice(nombre);

            System.out.println(
                    "Materia agregada correctamente."
            );
        }

        //BUSCAR

        private void buscarMateria() {

            if (arbol.estaVacio()) {

                System.out.println(
                        "No hay materias registradas."
                );


                return;
            }

            sc.nextLine();


            System.out.print("Codigo de la materia: ");

            int codigo = sc.nextInt();

            Materia materia = arbol.buscar(codigo);

            if (materia != null) {

                System.out.println("Materia encontrada:");
                System.out.println(materia);

            } else {

                System.out.println("Materia no encontrada.");
            }

        }

        //MODIFICAR

        private void modificarMateria() {

            if (arbol.estaVacio()) {

                System.out.println(
                        "No hay materias registradas."
                );


                return;
            }

            sc.nextLine();


            System.out.print("Codigo de la materia a modificar: ");

            int codigo = sc.nextInt();

            sc.nextLine();

            Materia materia = arbol.buscar(codigo);

            if (materia == null) {

                System.out.println(
                        "Error: no existe una materia con ese codigo."
                );

                return;
            }

            System.out.println(
                    "Materia encontrada: " + materia
            );

            System.out.print("Nuevo nombre: ");

            String nuevoNombre = sc.nextLine();

            arbol.modificar(codigo, nuevoNombre);

            System.out.println(
                    "Materia modificada correctamente."
            );
        }

        //ELIMINAR

        private void eliminarMateria() {

            if (arbol.estaVacio()) {

                System.out.println(
                        "No hay materias registradas."
                );


                return;
            }

            sc.nextLine();


            System.out.print("Codigo de la materia a eliminar: ");

            int codigo = sc.nextInt();

            Materia materia = arbol.buscar(codigo);

            if (materia == null) {

                System.out.println(
                        "Error: no existe una materia con ese codigo."
                );

            } else {

                arbol.eliminar(codigo);

                System.out.println(
                        "Materia eliminada correctamente."
                );
            }
        }

        //AGREGAR PREREQUISITOS

        private void agregarPrerequisito() {

            if (arbol.estaVacio()) {

                System.out.println(
                        "No hay materias registradas."
                );


                return;
            }

            sc.nextLine();

            System.out.print("Materia origen: ");
            String origen = sc.nextLine();

            System.out.print("Materia destino: ");
            String destino = sc.nextLine();

            if (grafo.agregarArista(origen, destino)) {

                System.out.println(
                        "Prerequisito agregado correctamente."
                );

            } else {

                System.out.println(
                        "Error: una o ambas materias no existen."
                );
            }
        }

        //CONSULTAR

        private void consultarPrerequisitos() {

            if(arbol.estaVacio()) {

                System.out.println("Primero debe agregar al menos una materia");

                return;
            }

            sc.nextLine();

            System.out.print("Nombre de la materia: ");

            String materia = sc.nextLine();

            grafo.mostrarPrerequisitos(materia);

        }

        //DFS

        private void ejecutarDFS() {

            if (arbol.estaVacio()) {

                System.out.println(
                        "No hay materias registradas."
                );

                return;
            }

            sc.nextLine();

            System.out.print("Materia inicial: ");

            String materia = sc.nextLine();

            if (!grafo.existeMateria(materia)) {

                System.out.println(
                        "Error: la materia no existe."
                );

                return;
            }

            grafo.DFS(materia);
        }
    }
