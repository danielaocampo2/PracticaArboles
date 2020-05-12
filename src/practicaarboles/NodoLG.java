package practicaarboles;

/**
 *
 * @author julian.agudelo1
 * @author Daniela Ocampo
 */
public class NodoLG { // nodo de la lista generalizada donde se va a representar el árbol n-ario.
    private Object dato;
    private int sw;
    private NodoLG liga;

    public NodoLG(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public NodoLG getLiga() {
        return liga;
    }

    public void setLiga(NodoLG liga) {
        this.liga = liga;
    }
    
}
