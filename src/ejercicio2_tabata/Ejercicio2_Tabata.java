package ejercicio2_tabata;

/**
 *
 * @author tllach
 * Course name: Algoritmo y complejidad IST4310
 * Student name: Tabata
 * ID: 200149846
 * Name Actividad: Workshop 2: Counting Duplicates in a Plain Text File
 * Date: 05/08/2022
 * Descprition: Es un programa que, luego de algunas operaciones se obtiene un array con n numeros 
 *              random, cuenta las veces que se repite el nro.
 * 
 */
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Ejercicio2_Tabata {

    static int[] rand;
    static ListaEnlazada key;
    static ListaEnlazada value;
    static int n;
    static int expo;
    static double time;
    static long comp;
    static int cases;
    static String fileCase;
    static long elapsedTime;  //references: https://stackify.com/heres-how-to-calculate-elapsed-time-in-java/
    
    
    /*
        * Algorithms and Programming II                             April 25, 2022
        * IST 2089
        * Prof. M. Diaz-Maldonado
        *
        *
        * Synopsis:
        * Performs rutinary input output operations in Java.
        *
        *
        * Copyright (c) 2022 Misael Diaz-Maldonado
        * This file is released under the GNU General Public License as published
        * by the Free Software Foundation, either version 3 of the License, or
        * (at your option) any later version.
        *
        *
        * References:
        * [0] Files: www.w3schools.com/java/java_files_create.asp
        * [1] PrintWriter: (docs.oracle.com/en/java/javase/11/docs/api/java.base/
        *                   java/io/PrintWriter.html)
        * [2] IOException: (docs.oracle.com/javase/7/docs/api/java/io/
        *                   IOException.html)
        * [3] FileNotFoundException: (docs.oracle.com/javase/7/docs/api/java/io/
        *                             FileNotFoundException.html)
        * [4] Scanner: docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
        * [5] Random: docs.oracle.com/javase/8/docs/api/java/util/Random.html
        * [6] www.javatpoint.com/throw-keyword
        * [7] www.javatpoint.com/post/java-random
        *
     */
   public static void main(String[] args) throws FileNotFoundException {
        repeticiones = 50; 
        //se debe guardar los valores n, comp y tiempo por cada set de n despues de las repeticiones 
        for(cases = 0; cases < 3; cases++){
            changeFileName(cases);
            create(fileCase); //crear los archivos que va a guardar los valores necesarios para la grafica
            PrintWriter out = new PrintWriter(fileCase);
            for(expo = 4; expo < 15; expo++){ //para crear valores de n desde 2^4 hasta 2^14
                time = 0;
                comp = 0;
                for(int j = 0; j < repeticiones; j++){ //200 repiticiones por tamaño
                    create("random.txt");	// creates a file
                    write();	// writes random numbers to the file
                    readIntoArray();// reads random numbers into array
                    printCountOfDuplicate(); //print the count of duplicate
                    time = time + elapsedTime;
                }
                time = time / repeticiones;
                comp = comp / repeticiones;
                String line =  n + " " + comp + " " +  time;
                out.println(line);
            }
            out.close();
            expo = 0;
        }
        
        
        
        return;
    }
     
    private static void changeFileName(int i){
        switch(i){
            case 0:
                fileCase = "bestCase.txt";
                break;
            case 1:
                fileCase = "worstCase.txt";
                break;
            case 2:
                fileCase = "averageCase.txt";
                break;
        }
    }
    
    private static void create(String fname) {
    // creates a file with given name `filename'
        try {
            // creates a new File object
            File f = new File(fname);

            // creates the new file
            String msg = "creating file `" + fname + "' ... ";
            System.out.println();
            System.out.printf("%s", msg);
            f.createNewFile();
            System.out.println("done");

        } catch (IOException err) {
            // complains if there is an Input/Output Error
            System.out.println("IO Error:");
            err.printStackTrace();
        }

        return;
    }

    private static void write() {
        // writes to the file
        try {
            // defines the filename
            String filename = "random.txt";
            // creates new PrintWriter object for writing file
            PrintWriter out = new PrintWriter(filename);

            n = (int) Math.pow(2, Ejercicio2_Tabata.expo);
            // creates random number generator object
            Random rand = new Random();
            String msg = "writing %d random numbers ... ";
            System.out.printf(msg, n);
            
            for (int i = 0; i != n; ++i) {
                int number = -1;
                switch(cases){
                    case 0: //best case: all numbers are equal
                        number = 1;
                        fileCase = "bestCase.txt";
                        break;
                    case 1: //worst case: all numbers are different
                        number = i;
                        fileCase = "worstCase.txt";
                        break;
                    case 2: // average case
                        number = rand.nextInt(n);
                        fileCase = "averageCase.txt";
                        break;
                }
                out.printf("%d\n", number);
            }

            System.out.println("done");

            System.out.printf("closing file ... ");
            out.close();	// closes the file
            System.out.println("done");
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            System.out.println("IO Error:");
            err.printStackTrace();
        }

        return;
    }

    private static void read() {
    // reads the file contents and prints them to the console
    
        // defines the filename
        String filename = "random.txt";
        // creates a File object
        File f = new File(filename);
        try {
            // attempts to create a Scanner object
            Scanner in = new Scanner(f);

            System.out.printf("\nrandom numbers in file:\n");

            int r;
            int count = 0;
            // reads integers in file until EOF
            while (in.hasNextInt()) {
                // reads random number and prints it
                r = in.nextInt();
                System.out.printf("%2d %4d\n", count, r);
                ++count;
            }

            String msg = "%d random numbers have been read\n";
            System.out.printf(msg, count);

        } catch (FileNotFoundException err) {
            // complains if file does not exist
            System.out.println("IO Error:");
            err.printStackTrace();
        }

        return;
    }

    private static void readIntoArray() {
    // reads the file contents into an array and prints the array
        String filename = "random.txt";
        File f = new File(filename);

        try {
            Scanner in = new Scanner(f);

            // allocates for storing the random numbers in file
            int size = lines(filename);
            rand = new int[size];

            int count = 0;
            // reads random numbers into array
            while (in.hasNextInt()) {
                rand[count] = in.nextInt();
                ++count;
            }



        } catch (FileNotFoundException err) {
            // complains if file does not exist
            System.out.println("IO Error:");
            err.printStackTrace();
        }

        return;
    }

    private static int lines(String filename){
    // counts number of lines (or records) in a file
        int count = 0;
        // creates a File object
        File f = new File(filename);
        try {
            // attempts to create a Scanner object
            Scanner in = new Scanner(f);

            // reads integers in file until EOF for counting
            while (in.hasNextInt()) {
                in.nextInt();
                ++count;
            }
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            System.out.println("IO Error:");
            err.printStackTrace();
        }
        return count;
    }

    private static void printCountOfDuplicate(){        
        key = new ListaEnlazada();
        value = new ListaEnlazada();
        
        long start = System.nanoTime();
        for(int i = 0; i < rand.length; i++){
            int index = findIndexNumber(rand[i]);
            if(index == -1){
                //agrega el nro a la lista key
                Nodo n = new Nodo(rand[i]);
                key.insert(n);
                comp = comp + key.size();
                //agrega un uno a la lista value
                Nodo n2 = new Nodo(1);
                value.insert(n2);
            }else{
                //si encuentra un nro repetido suma 1 en el mismo index de el key en value
                value.set(index, value.get(index) + 1);
            }
        }
        long end = System.nanoTime();
        elapsedTime = end - start; 
    }
    
    private static int findIndexNumber(int number){
        int arraySize = key.size();
        for(int i = 0; i < arraySize; i++){
            if(key.get(i) == number){
                comp = comp + 1;
                return i;
            }
        } 
        return -1;
    }
    
}

/*
 * COMMENTS:
 * Reading:
 * Reads integers until the scanner object finds something that it is not
 * an integer, such as a String or an End-Of-File EOF for instance.
 *
 */
