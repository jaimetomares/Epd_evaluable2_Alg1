/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package epdevaluableii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * 2ºGIISI
 *
 * @author Víctor Jesús Reina López & Jaime Baquerizo Delgado
 */
public class EPDEvaluableII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Guardamos los ficheros en variables String con la ruta de estos.
        String fichero1 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/berlin52.tsp";
        String fichero2 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/kroA100.tsp";
        String fichero3 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/kroA150.tsp";
        String fichero4 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/kroA200.tsp";
        String fichero5 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/usa13509.tsp";
        String fichero6 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/vm1084.tsp";
        String fichero7 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/vm1748.tsp";
        String fichero8 = "/Users/user/Desktop/Victor/GIISI/2ºGIISI/1ºCuatrimestre/Algoritmica I/EPD/EPD 8 Evaluable 2/Proyecto/data/a280.tsp";
        int camino[] = null;
        double dist[][] = Ficheros(fichero8);
        long start, stop, periodo;
        //Variables auxiliares para el sumatorio de los tiempos de ejecución de cada algoritmo
        long tiemposVoraz = 0;
        long tiemposAleatorio1 = 0;
        long tiemposAleatorio2 = 0;
        //Variable que indica el numero de veces que vamos a llamar los métodos para el calculo de la media de tiempos
        int itCalculoTiempos = 100;

        //Calculo del camino mediante algoritmo Aleatorio1 empezando por la ciudad 1
        camino = algoritmoAleatorio1(dist, 14);
        System.out.println("Camino calculado con el algoritmo Aleatorio1: " + toStringCamino(camino, dist) + "\n" + "Distancia total: " + getDistanciaTotal(dist, camino));
        //Bucle para llamar a la funcion un numero determinado de veces para calcular la media de tiempo de ejecución del algoritmo Alaeatorio1
        for (int i = 0; i < itCalculoTiempos; i++) {
            start = System.nanoTime();
            camino = algoritmoAleatorio1(dist, 14);
            stop = System.nanoTime();
            periodo = stop - start;
            tiemposAleatorio1 += periodo;
        }
        //Calculamos la media de los tiempos de ejecución del algoritmo Aleatorio1 y lo mostramos por pantalla
        long mediaAleatorio1Final = (tiemposAleatorio1 / itCalculoTiempos);
        System.out.println("- Tiempo de ejecución: " + mediaAleatorio1Final + " ns");

        System.out.println("\n");

        //Calculo del camino mediante algoritmo Aleatorio2 empezando por la ciudad 1
        camino = algoritmoAleatorio2(dist, 14);
        System.out.println("Camino calculado con el algoritmo Aleatorio2: " + toStringCamino(camino, dist) + "\n" + "Distancia total: " + getDistanciaTotal(dist, camino));
        //Bucle para llamar a la funcion un numero determinado de veces para calcular la media de tiempo de ejecución del algoritmo Aleatorio2
        for (int i = 0; i < itCalculoTiempos; i++) {
            start = System.nanoTime();  //Capturamos el instante inicial
            camino = algoritmoAleatorio2(dist, 14);
            stop = System.nanoTime();  //Capturamos el instante final
            periodo = stop - start; //Tiempo de ejecución
            tiemposAleatorio2 += periodo;   //Calculamos la suma de todos los tiempos del algoritmo Aleatorio1
        }
        //Calculamos la media de los tiempos de ejecución del algoritmo Aleatorio2 y lo mostramos por pantalla
        long mediaAleatorio2Final = (tiemposAleatorio2 / itCalculoTiempos);
        System.out.println("- Tiempo de ejecución: " + mediaAleatorio2Final + " ns");

        System.out.println("\n");

        //Calculo del camino mediante algoritmo Voraz empezando por la ciudad 1
        camino = algoritmoVoraz(dist, 14);
        System.out.println("Camino calculado con el algoritmo Voraz: " + toStringCamino(camino, dist) + "\n" + "Distancia total: " + getDistanciaTotal(dist, camino));
        //Bucle para llamar a la funcion un numero determinado de veces para calcular la media de tiempo de ejecución del algoritmo Voraz
        for (int i = 0; i < itCalculoTiempos; i++) {
            start = System.nanoTime();  //Capturamos el instante inicial
            camino = algoritmoVoraz(dist, 14);
            stop = System.nanoTime();   //Capturamos el instante final
            periodo = stop - start; //Tiempo de ejecución
            tiemposVoraz += stop - start;   //Calculamos la suma de todos los tiempos del algoritmo Voraz
        }
        //Calculamos la media de los tiempos de ejecución del algoritmo Voraz y lo mostramos por pantalla
        long mediaVorazFinal = (tiemposVoraz / itCalculoTiempos);
        System.out.println(String.format("- Tiempo de ejecución: " + mediaVorazFinal + " ns"));

    }

    //Lectura del fichero8
    public static double[][] Ficheros(String Cad) {

        BufferedReader br = null;
        double[][] distancias = null;
        File file = null;
        FileReader fr = null;

        try {
            //Abrimos el fichero8  y creamos el buffer
            file = new File(Cad);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero8
            boolean coordenadas = false;
            String linea;
            //Contador de las ciudades
            int cont = 0;
            //Vamos a recorrer el fichero8 en busca de ciudades
            while ((linea = br.readLine()) != null) {
                if ("\n".equals(linea) || "EOF".equals(linea)) {
                    coordenadas = false;
                }

                if (coordenadas) {
                    cont++;// aumentamos 1 cada vez que encontramos una ciudad
                }
                if ("NODE_COORD_SECTION".equals(linea)) {
                    coordenadas = true;
                }
            }
            //Creamos una matriz donde vamos a guardar las cordenadas de las ciudades
            distancias = new double[cont][3];
            //Para finalizar el recorrido del fichero8
            boolean end = false;
            while (end == false) {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                int i = 0;
                //Vamos a recorrer el fichero8 rellenando la matriz con todos los datos de cada una de las ciudades
                while ((linea = br.readLine()) != null) {
                    if ("\n".equals(linea) || "EOF".equals(linea)) {
                        coordenadas = false;
                        //"EOF" significa que hemos acabado de leer el fichero8
                        if ("EOF".equals(linea)) {
                            //Lo ponemos a "true" para acabar el bucle while
                            end = true;
                        }
                    }

                    if (coordenadas && i < cont) {
                        // eliminamos todos los espacios que no necesitamos del fichero8 y dibimos los datos de la ciudades por espacios intermedios                        
                        String linea2[] = linea.replace("   ", " ").replace("  ", " ").trim().split(" ");
                        // Guardamos cada valor en su respectivo lugar de la matriz
                        distancias[i][0] = Double.parseDouble(linea2[0]);
                        distancias[i][1] = Double.parseDouble(linea2[1]);
                        distancias[i][2] = Double.parseDouble(linea2[2]);
                        i++;
                    }
                    if ("NODE_COORD_SECTION".equals(linea)) {
                        coordenadas = true;
                    }
                }
            }

            return distancias;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Nos aseguramos de que cerramos bien el fichero8
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
        return distancias;
    }

    //Método algoritmo Voraz
    public static int[] algoritmoVoraz(double distancias[][], int start) {
        // creamos un vector del tamaño de la cantidad de ciudades que hay mas una mas para volver a la posicion inicial
        int[] camino = new int[distancias.length + 1];
        // introducimos el valor inicial en la matriz camino, le restamos uno porque el camino va a estar basado en lalocalizacion de la ciudad seleccionada en la matriz distancia.
        camino[0] = start - 1;
        // creamos un booleano para saber cuando ya esta lleno el camino con las ciudades a las que tenemos que ir
        boolean finCamino = false;
        //ponemos la i a uno ya que la posicion inicial es introducida a mano
        int i = 1;
        // hasta que o haya acabado el camino no termina el bucle
        while (finCamino == false) {
            // anterior es una variable que lo que hace es marcar la anterior ciudad a la nueva seleccionada para elegir la ciudad con menor distancia entre ellas.
            int anterior = camino[i - 1];
            //conseguimos la next ciudad a la que ir obteniendo el mejor tiempo de ir entre la ciudad anterior y ella, sin repetir ciudad en el camino
            camino[i] = getCaminoAlgoritmoVoraz(distancias, camino, anterior, i);
            // sumamos uno para ir guardando el camino completo
            i++;
            // cuando i llega a el stop del camino
            if (i == distancias.length) {
                // introducimos la ciudad inicial para terminar el camino
                camino[i] = start - 1;
                // ponemos el booleano a true para indicar que ya tenemos el camino completo
                finCamino = true;
            }
        }
        //Devolvemos el vector camino con todos los caminos calculados por el algoritmo voraz
        return camino;
    }

    //Método para busqueda de camino mediante el algoritmo voraz
    public static int getCaminoAlgoritmoVoraz(double distancias[][], int[] camino, int start, int tam) {
        int actual = 0;
        int next = 0;

        //El método recorrido nos confirma si la ciudad ya la hemos recorrido o no
        while (recorrido(actual, camino, tam)) {
            //Incrementamos uno si la ciudad actual ya esta en camino
            actual++;
        }
        //ponemos next en la ciudad next a la selecionada
        next = actual + 1;
        while (next < camino.length - 1) {// mientras que next no haya llegado a la ultima ciudad seguimos ejecutando el codigo
            while (recorrido(next, camino, tam)) {// Vemos si next no esta ya en nuestro camino
                if (next < camino.length - 1) {
                    next++;
                }
            }// si no esta recorrido  y next sigue  sin superar el maximo de ciudades que comparar 
            if (!recorrido(next, camino, tam) && next < camino.length - 1) {
                // si la distancia de next es mejor que la actual
                if (getDistancia(distancias, start, actual) > getDistancia(distancias, start, next)) {
                    // nuestra ciudad next pasa a ser la nueva ciudad
                    actual = next;
                    // y next suma uno para seguir comparando con todas las ciudades del camino
                    next++;

                } else {
                    //Si entra aqui significa que next no es mejor que la ciudad actual
                    next++;
                }
            }

        }
        return actual;//decuelve actual
    }

    //Este método se encarga de averiguar si la ciudad a comparar ya la hemos recorrido o no
    public static boolean recorrido(int next, int[] camino, int tam) {
        boolean rec = false;// booleano que muestra si ya esta en el camino
        for (int i = 0; i < tam && rec == false; i++) {// recorremos las ciudades en el camino para ver si la ciudad introducida este en el camino
            if (camino[i] == next) {
                rec = true;// si la ciudad esta ya en el camino ponemos el booleano a true
            }
        }
        return rec;//devuelve el booleano 
    }

    //Método para mostrar el camino
    public static String toStringCamino(int[] camino, double[][] distancias) {// imprime el camino 
        String s = "[";
        for (int i = 0; i < camino.length - 1; i++) {// recorre la matriz distancia con el camino creado imprimiendo la ciudad seleccionada en el camino de la matriz distancia
            s += (int) distancias[camino[i]][0] + ",";// convierte en int el numero de la ciudad para que no salga como double quitando el 1.00,2.00,etx
        }
        s += distancias[camino[camino.length - 1]][0] + "]";
        return s;
    }

    //Método del algoritmo aleatorio con criterio de parada con contador
    public static int[] algoritmoAleatorio2(double[][] distancias, int posicionInicial) {
        int[] camino = null;// crea el camino nuevo
        //Segun la experimentacion podemos probar con 25,50,100
        int contador = 0;// pone el cont a 0 e introducimos el valor maximo del cont
        int contLimit = 100;
        while (contador < contLimit) {// mientras no llegue al maximo el cont se sigue ejecutando

            //Nuevo candidato
            int[] caminoCandidato = getCaminoAleatorio(distancias, posicionInicial);// obtiene un nuevo camino aleatorio 
            // Entramos en el if si estamos en el primer camino o si la distancias del nuevo camino es menor que la del camino anterior
            if (camino == null || getDistanciaTotal(distancias, caminoCandidato) < getDistanciaTotal(distancias, camino)) {
                //Entonces el camino candidato sería nuestro mejor camino hasta ahora
                camino = caminoCandidato;
                //Y actualizamos el cont a 0 de nuevo
                contador = 0;
            } else {
                contador++;// si el camino nuevo no es mejor sumamos uno al cont
            }
        }

        return camino;// Una vez que el cont llegue al valor maximo, devuelve el camino obtenido

    }

    //Método para crear un camino aleatorio
    public static int[] getCaminoAleatorio(double[][] distancias, int start) {
        int[] camino = new int[distancias.length + 1];// creamos un vector del tamaño de el camino a realizar

        camino[0] = start - 1;//introducimos el valor inicial en el vector camino, le restamos uno porque el camino va a estar basado en la localizacion de la ciudad seleccionada en la matriz distancias.
        int siguienteCiudad;
        for (int i = 1; i < distancias.length; i++) {// 
            siguienteCiudad = getValorAleatorioEnCamino(distancias.length, camino, i);
            camino[i] = siguienteCiudad;
        }
        camino[camino.length - 1] = start - 1;// introducimos el valor inicial del camino en la ultima posición del vector csmino para indicar la vuelta a la ciudad de start.

        return camino;// devuelve el camino obtenido
    }

    //Método para generar un numero de ciudad aleatorio, es usado en el algoritmo aleatorio 1 y 2
    public static int getValorAleatorioEnCamino(int max, int[] camino, int size) {
        // escogemos un valor aleatorio entre 0 y la última ciudad del fichero8 que estemos calculando el camino
        int valorenCamino = (int) Math.floor(Math.random() * max);
        // llamamos al método "recorrido", para comprobar si la ciudad que hemos obtenido a través del número aleatorio pertenece a nuestro camino
        while (recorrido(valorenCamino, camino, size) == true) {
            // en el caso de pertenecer al camino, se generará otro número aleatorio y se repetirá el procedimiento anterior
            valorenCamino = (int) Math.floor(Math.random() * max);
        }
        return valorenCamino;// en el caso de no pertenecer, devolverá esa ciudad

    }

    //Método del algoritmo aleatorio con criterio de parada con numero límite de iteraciones
    public static int[] algoritmoAleatorio1(double[][] distancias, int posStart) {
        int[] camino = null;
        //Segun la experimentacion podemos probar con 100,500,1000
        int iterador = 0;
        int nItMax = 1000;

        // mientras que el número de iteraciones no sea el número maximo de iteraciones, buscará caminos alternativos.
        while (iterador < nItMax) {

            int[] caminoCandidato = getCaminoAleatorio(distancias, posStart);// guardamos un camino nuevo que será un candidato a mejorar el camino que tenemos actualmente.
            if (camino == null
                    || getDistanciaTotal(distancias, caminoCandidato) < getDistanciaTotal(distancias, camino)) {// para comprobar si un camino es mejor que el camino candidato, se comprobará si el coste de cada uno
                camino = caminoCandidato;// si el coste del camino candidato es menor que nuestro actual camino, pondremos que nuestro actual camino será el camino candidato

            }
            // Si el camino actual es mejor, el iterador seguirá aumentando
            iterador++;
        }

        return camino;// una vez acabado las iteraciones, se devolverá el mejor camino encontrado una vez realizadas todas las iteraciones

    }

    //Con este método calculamos la distancia recorrida
    public static float getDistanciaTotal(double[][] distancias, int[] camino) {
        float dTotal = 0;
        //Como ya tenemos el camino resuelto, vamos a recorrerlo de nuevo para calcular la distancias total recorrida.
        for (int i = 0; i < camino.length - 1; i++) {
            dTotal += getDistancia(distancias, camino[i], camino[i + 1]);//sumo la distancias de ciudad en ciudad obteniendo la distancias total del camino

        }
        //Devolvemos la distancia total
        return dTotal;

    }

    //Método para calcular la distancia euclídea entre dos ciudades y así encontrar la distancia entre ellas dos
    public static double getDistancia(double[][] dist, int start, int next) {
        //Calculamos la distancias entre las X de las dos ciudades
        double xDist = dist[start][1] - dist[next][1];
        //Calculamos la distancias entre las y de las dos ciudades
        double yDist = dist[start][2] - dist[next][2];
        //Fórmula de la distancia Euclídea para calcular la distancia entre dos puntos bidimensionales
        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    }

}
