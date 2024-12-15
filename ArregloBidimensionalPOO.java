package programacionpoo;

import java.util.Random;
import java.util.Scanner;

class Metodo {

    private char[][] arreglo;
    private int filas;
    private int[] columnas;

    // Constructor
    public Metodo() {
        this.arreglo = null;
        this.filas = 0;
        this.columnas = null;
    }

    // Crear arreglo 
    public void crearArreglo(int filas, int[] columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.arreglo = new char[filas][];

        for (int i = 0; i < filas; i++) {
            this.arreglo[i] = new char[columnas[i]];
        }
    }

    // Getters
    public char[][] getArreglo() {
        return arreglo;
    }

    public int getFilas() {
        return filas;
    }

    public int[] getColumnas() {
        return columnas;
    }

    // Inicializa el arreglo con informacion aleatoria
    public void inicializarAleatorio() {
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas[i]; j++) {
                arreglo[i][j] = (char) (random.nextInt(95) + 32);
            }
        }
    }

    // Inicializa el arreglo de forma manual
    public void inicializarManual(Scanner entrada) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas[i]; j++) {
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                arreglo[i][j] = entrada.nextLine().charAt(0);
            }
        }
    }

    // Agrega un elemento 
    public boolean agregarElemento(int fila, char elemento) {
        if (fila < 0 || fila >= filas) {
            return false;
        }

        int nuevaLongitud = columnas[fila] + 1;
        char[] nuevaFila = new char[nuevaLongitud];

        System.arraycopy(arreglo[fila], 0, nuevaFila, 0, columnas[fila]);
        nuevaFila[nuevaLongitud - 1] = elemento;
        arreglo[fila] = nuevaFila;
        columnas[fila] = nuevaLongitud;
        return true;
    }

    // Busca un elemento en el arreglo y devuelve la cantidad de veces encontrado
    public int buscarElemento(char elemento) {
        int contador = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas[i]; j++) {
                if (arreglo[i][j] == elemento) {
                    contador++;
                }
            }
        }
        return contador;
    }

    // Quita un elemento por posicion
    public boolean quitarElemento(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas[fila]) {
            return false;
        }

        for (int k = columna; k < columnas[fila] - 1; k++) {
            arreglo[fila][k] = arreglo[fila][k + 1];
        }

        char[] nuevaFila = new char[columnas[fila] - 1];
        System.arraycopy(arreglo[fila], 0, nuevaFila, 0, nuevaFila.length);
        arreglo[fila] = nuevaFila;
        columnas[fila]--;
        return true;
    }

    // Modifica un elemento por posicion
    public boolean modificarElemento(int fila, int columna, char nuevoValor) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas[fila]) {
            return false;
        }
        arreglo[fila][columna] = nuevoValor;
        return true;
    }

    // Intercambia dos elementos en posiciones 
    public boolean intercambiarElementos(int fila1, int columna1, int fila2, int columna2) {
        if (fila1 < 0 || fila1 >= filas || columna1 < 0 || columna1 >= columnas[fila1]
                || fila2 < 0 || fila2 >= filas || columna2 < 0 || columna2 >= columnas[fila2]) {
            return false;
        }

        char temp = arreglo[fila1][columna1];
        arreglo[fila1][columna1] = arreglo[fila2][columna2];
        arreglo[fila2][columna2] = temp;
        return true;
    }

    // Muestra el contenido del arreglo
    public void mostrarArreglo() {
        if (arreglo == null) {
            System.out.println("El arreglo no ha sido inicializado.");
            return;
        }

        for (int i = 0; i < filas; i++) {
            System.out.print((i + 1) + ": ");
            for (char elemento : arreglo[i]) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
}

// Clase principal con el metodo main
public class ArregloBidimensionalPOO {

    static int menu(Scanner entrada) {
        System.out.println("""
                               Seleccione una opcion
                               1. Crear arreglo
                               2. Agregar elemento
                               3. Buscar elemento
                               4. Quitar elemento
                               5. Mostrar arreglo
                               6. Modificar elemento
                               7. Intercambiar elementos
                               0. Salir
                               """);
        int opcion = entrada.nextInt();
        entrada.nextLine(); // Consumir el salto de linea pendiente
        return opcion;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Metodo control = new Metodo();
        int opc;
        do {
            opc = menu(entrada);
            switch (opc) {
                case 1: {
                    System.out.print("Ingrese el numero de filas: ");
                    int filas = entrada.nextInt();
                    while (filas <= 0) {
                        System.out.println("Numero invalido. Intente nuevamente.");
                        filas = entrada.nextInt();
                    }

                    int[] columnas = new int[filas];
                    for (int i = 0; i < filas; i++) {
                        System.out.print("Ingrese el numero de columnas para la fila " + (i + 1) + ": ");
                        columnas[i] = entrada.nextInt();
                        while (columnas[i] <= 0) {
                            System.out.println("Numero invalido. Intente nuevamente.");
                            columnas[i] = entrada.nextInt();
                        }
                    }

                    control.crearArreglo(filas, columnas);

                    System.out.println("""
                                           Opciones para inicializar el arreglo:
                                           a) Manualmente
                                           b) Aleatoriamente
                                           """);
                    char opcionInicializar = entrada.next().charAt(0);
                    entrada.nextLine();

                    switch (opcionInicializar) {
                        case 'a':
                            //llama a la funcion ingresarElementos
                            control.inicializarManual(entrada);
                            System.out.println("Arreglo inicializado manualmente.");
                            break;
                        case 'b':
                            //Llama a la funcion para informacionAleatoria
                            control.inicializarAleatorio();
                            System.out.println("Arreglo inicializado aleatoriamente.");
                            break;
                        default:
                            System.out.println("Opcion no valida. No se inicializo el arreglo.");
                            break;
                    }
                }
                break;
                case 2: {
                    if (control.getArreglo() != null) {
                        System.out.print("Ingrese la fila: ");
                        int fila = entrada.nextInt() - 1;
                        entrada.nextLine();
                        System.out.print("Ingrese el elemento: ");
                        char elemento = entrada.nextLine().charAt(0);

                        if (control.agregarElemento(fila, elemento)) {
                            System.out.println("Elemento agregado correctamente.");
                        } else {
                            System.out.println("No se pudo agregar el elemento.");
                        }
                    } else {
                        System.out.println("Debe crear el arreglo primero.");
                    }
                }
                break;
                case 3: {
                    if (control.getArreglo() != null) {
                        System.out.print("Ingrese el elemento a buscar: ");
                        char elemento = entrada.nextLine().charAt(0);
                        int ocurrencias = control.buscarElemento(elemento);
                        System.out.println("El elemento aparece " + ocurrencias + " veces.");
                    } else {
                        System.out.println("Debe crear el arreglo primero.");
                    }
                }
                break;
                case 4: {
                    if (control.getArreglo() != null) {
                        System.out.print("Ingrese la fila del elemento a quitar: ");
                        int fila = entrada.nextInt() - 1;
                        System.out.print("Ingrese la columna del elemento a quitar: ");
                        int columna = entrada.nextInt() - 1;

                        if (control.quitarElemento(fila, columna)) {
                            System.out.println("Elemento eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el elemento.");
                        }
                    } else {
                        System.out.println("Debe crear el arreglo primero.");
                    }
                }
                break;
                case 5:
                    control.mostrarArreglo();
                    break;
                case 6: {
                    if (control.getArreglo() != null) {
                        System.out.print("Ingrese la fila: ");
                        int fila = entrada.nextInt() - 1;
                        System.out.print("Ingrese la columna: ");
                        int columna = entrada.nextInt() - 1;
                        entrada.nextLine();
                        System.out.print("Ingrese el nuevo valor: ");
                        char nuevoValor = entrada.nextLine().charAt(0);

                        if (control.modificarElemento(fila, columna, nuevoValor)) {
                            System.out.println("Elemento modificado correctamente.");
                        } else {
                            System.out.println("No se pudo modificar el elemento.");
                        }
                    } else {
                        System.out.println("Debe crear el arreglo primero.");
                    }
                }
                break;
                case 7: {
                    if (control.getArreglo() != null) {
                        System.out.print("Ingrese la fila del primer elemento: ");
                        int fila1 = entrada.nextInt() - 1;
                        System.out.print("Ingrese la columna del primer elemento: ");
                        int columna1 = entrada.nextInt() - 1;
                        System.out.print("Ingrese la fila del segundo elemento: ");
                        int fila2 = entrada.nextInt() - 1;
                        System.out.print("Ingrese la columna del segundo elemento: ");
                        int columna2 = entrada.nextInt() - 1;

                        if (control.intercambiarElementos(fila1, columna1, fila2, columna2)) {
                            System.out.println("Elementos intercambiados correctamente.");
                        } else {
                            System.out.println("No se pudo intercambiar los elementos.");
                        }
                    } else {
                        System.out.println("Debe crear el arreglo primero.");
                    }
                }
                break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    break;
            }
        } while (opc != 0);

        entrada.close();
    }
}