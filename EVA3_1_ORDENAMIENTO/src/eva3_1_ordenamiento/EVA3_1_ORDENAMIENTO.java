/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eva3_1_ordenamiento;

import java.util.Arrays;

/**
 *
 * @author jose kaleb
 */
public class EVA3_1_ORDENAMIENTO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //VAMOS A ORDENAR ARREGLOS (INT)
        int[] datos = new int[10];
        int[] copiaSel = new int[datos.length];
        int[] copiaIns = new int[datos.length];
        int[] copiaBubble = new int[datos.length];
        int[] copiaQuick = new int[datos.length];
        int[] copiaArray = new int[datos.length];
        long iniT, finT;

        llenar(datos);//envio el arreglo a 1lenar con valores aleatorios

        System.out.println("PRUEBA CON SELECTION SORT: ");
        duplicar(datos, copiaSel);
        imprimir(copiaSel);//imprimir contenido
        iniT = System.nanoTime();//tiempo antes de empezar el método
        selectiomSort(copiaSel);
        finT = System.nanoTime();//tiempo al terminar el método
        imprimir(copiaSel);//imprimir contenitio
        System.out.println("Tiempo en ordenar: " + (finT - iniT));

        System.out.println("");

        System.out.println("Prueba con Insertion Sort");
        duplicar(datos, copiaIns);
        imprimir(copiaIns);//imprimir contenido
        iniT = System.nanoTime();
        insertionSort(copiaIns);
        finT = System.nanoTime();
        imprimir(copiaIns);
        System.out.println("Tiempo en ordenar: " + (finT - iniT));

        System.out.println("");

        System.out.println("Prueba con Bubble Sort");
        duplicar(datos, copiaBubble);
        imprimir(copiaBubble);//imprimir contenido
        iniT = System.nanoTime();
        bubbleSort(copiaBubble);
        finT = System.nanoTime();
        imprimir(copiaBubble);
        System.out.println("Tiempo en ordenar: " + (finT - iniT));

        System.out.println("");

        System.out.println("Prueba con Quick Sort");
        duplicar(datos, copiaQuick);
        imprimir(copiaQuick);//imprimir contenido
        iniT = System.nanoTime();
        quicksort(copiaQuick);
        finT = System.nanoTime();
        imprimir(copiaQuick);
        System.out.println("Tiempo en ordenar: " + (finT - iniT));
        System.out.println("");

        System.out.println("Prueba con Array Sort");
        duplicar(datos, copiaArray);
        imprimir(copiaArray);//imprimir contenido
        iniT = System.nanoTime();
        Arrays.sort(copiaArray);
        finT = System.nanoTime();
        imprimir(copiaArray);
        System.out.println("Tiempo en ordenar: " + (finT - iniT));
    }

    //Llenado de arreglos con valores aleatorios 0 - 99
    public static void llenar(int[] datos) {
        for (int i = 0; i < datos.length; i++) {
            datos[i] = (int) (Math.random() * 100);
        }
    }

    public static void duplicar(int[] datos, int[] copia) {
        for (int i = 0; i < datos.length; i++) {
            copia[i] = datos[i];
        }

    }

//IMPRIMIR ARREGLO
    public static void imprimir(int[] datos) {
        for (int i = 0; i < datos.length; i++) {
            System.out.print("[" + datos[i] + "]");
        }
        System.out.println("");
    }

    //Eficiencia o(N^2)
    public static void selectiomSort(int[] datos) {
        for (int i = 0; i < datos.length; i++) {
            int iMin = i; //EMPIEZA EL ALGORITMO, EL MİNIMO ES EL PRIMER ELEMENTO
            for (int j = i + 1; j < datos.length; j++) {//BUSCAR LA POSICIÓN DEL VALOR MÁS PEQUEÑO
                //COMPARAR
                //valor[j] vs valor[min]
                if (datos[j] < datos[iMin]) {//COMPARO ACTUAL VS EL MÍNIMO ACTUAL
                    iMin = j;
                }
            }
            //INTERCAMBIO
            if (i != iMin) {
                //3 PASOS
                //RESPALDAR UN VALOR
                int iTemp = datos[i];
                //INTERCAMBIAR UN VALOR
                datos[i] = datos[iMin];
                //REPONER EL VALOR RESPALDADO
                datos[iMin] = iTemp;
            }

        }
    }

    public static void insertionSort(int[] datos) {
        for (int i = 1; i < datos.length; i++) {
            int temp = datos[i]; //valor a insertar int temp
            int insP = i; //posición donde vamos a insertar
            for (int prev = i - 1; prev >= 0; prev--) {//buscar donde insertar
                if (datos[prev] > temp) {
                    datos[insP] = datos[prev]; // movemos valor de prev a la posición de insP
                    insP--; //retrocede una posición
                } else {
                    break;
                }
            }
            //insertamos
            datos[insP] = temp;
        }

    }

    public static void bubbleSort(int[] datos) {
        for (int i = 0; i < datos.length; i++) {//PASADAS
            for (int j = 0; j < (datos.length - 1); j++) {//COMPARA E INTERCAMBIA
                if (datos[j] > datos[j + 1]) {//comparamos j vs j + 1
                    //intercambiamos
                    int temp = datos[j];
                    datos[j] = datos[j + 1];
                    datos[j + 1] = temp;
                }
            }
        }
    }

    public static void quicksort(int[] datos) {
        quicksortRecu(datos, 0, datos.length - 1);
    }

    private static void quicksortRecu(int[] datos, int ini, int fin) {
        int iPivote; //inicio
        int too_big; //busca a los más grandes que el pivote
        int too_small; //busca a los más pequeños que el pivote
        boolean stopBig = false, stopSmall = false;
        //controlar la recursividad:
        int tama = fin - ini + 1;
        if (tama > 1) { //¿Cuando SI puedo realizar un quicksort?
            iPivote = ini;
            too_big = ini + 1;
            too_small = fin;
            for (int i = ini + 1; i <= fin; i++) {//número de veces a recorre 
                if ((datos[too_big] <= datos[iPivote]) && (!stopBig)) {//avanzo
                    too_big++; //avanzo
                } else {
                    stopBig = true; //me detengo cuando encuentro uno más grande
                }

                if ((datos[too_small] >= datos[iPivote]) && (!stopSmall)) {//retrocedo
                    too_small--; //retrocedo
                } else {
                    stopSmall = true;//me detengo cuando encuentro uno más pequeño
                }
                //ambos se detiene (swap)
                if (stopBig && stopSmall) {
                    if (too_big < too_small) {//intercambio SWAP
                        int temp = datos[too_big];
                        datos[too_big] = datos[too_small];
                        datos[too_small] = temp;
                        stopBig = false; //seguir avanzando
                        stopSmall = false; //seguir avanzando
                    } else {
                        break; //termino el ciclo
                    }
                }
            }
            //intercambio el pivote
            int temp = datos[iPivote];
            datos[iPivote] = datos[too_small];
            datos[too_small] = temp;
            iPivote = too_small; //TAMBIEN CAMBIA LA POSICIÓN DEL PIVOTE
            // quicksort (izq)
            quicksortRecu(datos, ini, iPivote - 1);
            //quicksort (der)
            quicksortRecu(datos, iPivote + 1, fin);

        }
    }
}
