
package ejercicio2_tabata;

/**
 *
 * @author tllach
 * Course name: Algoritmo y complejidad IST4310
 * Student name: Tabata
 * ID: 200149846
 * Name Actividad: Workshop 2: Counting Duplicates in a Plain Text File
 * Date: 10/08/2022
 * 
 */
public class Nodo {
    
    private int dato;
    private Nodo link;
    
    public Nodo(int dato){
        this.dato = dato;
    }
    
    public int getDato(){
        return dato;
    }
    
    public void setDato(int num){
        this.dato = num;
    }
    
    public Nodo getLink(){
        return link;
    }
    
    public void setLink(Nodo a){
        this.link = a;
    }
}
