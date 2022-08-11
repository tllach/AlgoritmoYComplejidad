
package ejercicio2_tabata;

/**
 *
 * @author tllach
 * Course name: Algoritmo y complejidad IST4310
 * Student name: Tabata
 * ID: 200149846
 * Name Actividad: Workshop 2: Counting Duplicates in a Plain Text File
 * Date: 10/08/2022
 */
public class ListaEnlazada {
    
    private Nodo ptr;
    private Nodo link;
    
    public ListaEnlazada(){
        this.ptr = null;
    }
    
    public void insert(Nodo n){
        if(ptr == null){
            ptr = n;
        }else{
            Nodo p = ptr;
            while(p.getLink() != null){
                p = p.getLink();
            }
            p.setLink(n);
        } 
    }
    
    public int get(int i){
        int index = 0;
        Nodo p = ptr;
        while(p != null){
            if(index == i){
                return p.getDato();
            }
            p = p.getLink();
            index++;
        }
        return -1;
    }
    
    public void set(int index, int num){
        Nodo p = ptr;
        int i = 0;
        while(p != null){
            if(index == i){
                p.setDato(num);
            }
            p = p.getLink();
            i++;
        }
    }
    
    public int size(){
        int i = 0;
        Nodo p = ptr;
        while(p != null){
            i++;
            p = p.getLink();
        }
        return i;
    }
    
    
    public Nodo getPtr(){
        return ptr;
    }
}
