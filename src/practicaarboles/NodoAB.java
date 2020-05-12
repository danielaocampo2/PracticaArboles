package practicaarboles;

/**
 *
 * @author julian.agudelo1
 * @author Daniela Ocampo
 */
public class NodoAB { // nodo de la lista ligada, donde se va a representar el árbol binario.
    private Object dato;
    private NodoAB Li;
    private NodoAB Ld;

    public NodoAB(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoAB getLi() {
        return Li;
    }

    public void setLi(NodoAB Li) {
        this.Li = Li;
    }

    public NodoAB getLd() {
        return Ld;
    }

    public void setLd(NodoAB Ld) {
        this.Ld = Ld;
    }
}
