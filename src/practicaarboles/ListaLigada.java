package practicaarboles;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author julian.agudelo1
 * @author Daniela Ocampo
 */
public class ListaLigada {

    private NodoAB primero;
    private NodoAB ultimo;

    public ListaLigada() {
        primero = ultimo = null;
    }

    public NodoAB getPrimero() {
        return primero;
    }

    public void setPrimero(NodoAB primero) {
        this.primero = primero;
    }

    public NodoAB getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoAB ultimo) {
        this.ultimo = ultimo;
    }

    public boolean esVacia() {
        return primero == null;
    }

    public void convierteLgABinario(ListaGeneralizada a) {
        NodoLG p;
        NodoLG q;
        NodoAB x;
        NodoAB ultimoo;
        Stack pila = new Stack();
        p = a.getPrimero();
        x = new NodoAB(p.getDato());
        primero = x;
        ultimoo = x;
        p = p.getLiga();
        while (p != null) {
            if (p.getSw() == 0) {
                x = new NodoAB(p.getDato());
            } else {
                q = (NodoLG) p.getDato();
                x = new NodoAB(q.getDato());
                pila.push(x);
                pila.push(q.getLiga());
            }
            ultimoo.setLi(x);// los hijos de un dato se agregan en la liga izquierda 
            ultimoo = x;
            p = p.getLiga();
            while (p != null) {
                if (p.getSw() == 0) {
                    x = new NodoAB(p.getDato());
                } else {
                    q = (NodoLG) p.getDato();
                    x = new NodoAB(q.getDato());
                    pila.push(x); // cada que encuentra un nodo con sw =1, apila el nodo de la lista ligada con el dato.
                    pila.push(q.getLiga());// apila la posición siguiente al nodo con sw=1
                }
                ultimoo.setLd(x); // los hermanos de un dato se agregan en la liga derecha.
                ultimoo = x;
                p = p.getLiga();
            }
            if (!pila.empty()) {
                p = (NodoLG) pila.pop();
                ultimoo = (NodoAB) pila.pop();
            }
        }
    }

    public int gradoNArioEnBinario(NodoAB x) {
        int grado; // variable donde se va ir contando los hijos de un dato.
        int n = 0;
        NodoAB r;
        Stack pila = new Stack();
        r = x;
        if (r == null) {
            return 0;
        }
        r = r.getLi();
        if (r == null) {
            return 0;
        }
        grado = 1;
        while (r != null) {
            if (r.getLi() == null) {
                r = r.getLd();
                if (r != null) {
                    grado = grado + 1;
                }
            } else {
                pila.push(r);
                r = r.getLd();
                if (r != null) {
                    grado = grado + 1;
                }
            }
        }
        if (!pila.empty()) {
            r = (NodoAB) pila.pop();
            n = gradoNArioEnBinario(r); // se hace llamada recursiba para evaluar los hijos de todos los átomos existentes.
        }
        if (n > grado) { // retorna la cantidad de hijos mayor encontrada. 
            grado = n;
        }
        return grado;
    }

    public int alturaNArioEnBinario(NodoAB r) {
        int altura = 1;
        int alturaTemp; // se guarda la altura temporar arrojada de hacer la llamada recursiva con cada uno de los datos.
        if (r == null) {
            return 0;
        }
        r = r.getLi();
        if (r == null) {
            return altura;
        }
        while (r != null) {
            alturaTemp = 0;
            if (r.getLi() != null) {

                alturaTemp += alturaNArioEnBinario(r);
                if (alturaTemp > altura) { // se verifica que el resultado arrojado por cada uno de los datos sea la mayor altura para actualizar la variable altura 
                    altura = alturaTemp;
                }
            }
            r = r.getLd();
        }
        return altura + 1;
    }

    public int hojasNArioEnBinario(NodoAB r) {
        int hh;
        hh = 0;
        if (r != null) {
            if (r.getLi() == null) { // una hoja del n-ario en binario es solo cuando la liga izquierda del nodo es igual a 0
                hh = hh + 1;
            }
            hh = hh + hojasNArioEnBinario(r.getLi()) + hojasNArioEnBinario(r.getLd()); // hace 2 llamadas recursivas,una para evaluar liga derecha y otra liga izquierda.
        }
        return hh;
    }

    public String inorden(NodoAB r) { // evalua izquierda, raíz, derecha.
        String hilera = ""; 
        if (r != null) {
            hilera += inorden(r.getLi());
            hilera += r.getDato();
            hilera += inorden(r.getLd());
        }
        return hilera;
    }

    public String posorden(NodoAB r) { // evalua izquierda, derecha, raíz.
        String hilera = "";
        if (r != null) {
            hilera += posorden(r.getLi());
            hilera += posorden(r.getLd());
            hilera += r.getDato();
        }
        return hilera;
    }

    public String preorden(NodoAB r) {// evalua raíz izquierda, derecha.
        String hilera = "";
        if (r != null) {
            hilera += r.getDato();
            hilera += preorden(r.getLi());
            hilera += preorden(r.getLd());
        }
        return hilera;
    }

    public char padreNArioEnBinario(NodoAB r, char dato) {
        char pad;
        char d;
        NodoAB p;
        NodoAB q;
        pad = '0';
        p = r.getLi();
        while (p != null && pad == '0') { // busca el dato con p y r apunta al padre de p
            if (p.getDato().equals(dato)) {
                d = (Character) r.getDato();
                pad = d;
                return pad;
            }
            if (pad == '0') {
                if (p.getLi() != null) {
                    q = p;
                    pad = padreNArioEnBinario(q, dato); 
                }
                p = p.getLd();
            }
        }
        return pad;
    }

    public String ancestrosNArioEnBinario(char dato) {
        char p;
        String hilera = "";
        Stack pila;
        pila = new Stack();
        p = padreNArioEnBinario(primero, dato); //  busca el padre del dato ingresado 
        while (p != '0') { // busca el padre de cada uno de los resultados que arroja el padre 
            pila.push(p); // apila todos los padres 
            p = padreNArioEnBinario(primero, p);
        }
        while (!pila.empty()) {
            p = (Character) pila.pop();
            hilera += p; // llena la hilera desde la raíz del arbol hasta el dato ingresado
        }
        return hilera;
    }

    public int gradoDato(char t) {
        NodoAB p;
        Object d = t;
        int grad = 0;
        Stack pila = new Stack();
        p = getPrimero();
        //p = p.getLi();
        while (p != null) {

            while (p != null) { // busca el dato ingresado 
                if (p.getLi() != null) {
                    pila.push(p.getLi());
                }
                boolean res = d.equals(p.getDato());
                if (res == true) { // cuando lo encuentra avanza con la liga izquierda 
                    p = p.getLi();
                    if (p != null) {
                        grad = grad + 1;
                        p = p.getLd();
                        while (p != null) { // cuenta todos los hermanos que tiene el primer hijo
                            grad = grad + 1;
                            p = p.getLd();
                        }
                    }
                    return grad;
                }
                p = p.getLd();
            }
            if (!pila.empty()) {
                p = (NodoAB) pila.pop();
            }

        }
        return -1;

    }

    public String recorridoPorNiveles() {
        String hilera = "";
        NodoAB r = primero;
        if (r == null) {
            hilera += "El árbol esta vacio";
            return hilera;
        }
        hilera += r.getDato();
        Queue cola = new LinkedList();

        r = r.getLi();

        if (r == null) {
            return hilera;
        }

        while (r != null) {
            hilera += r.getDato();
            while (r != null) {
                if (r.getLi() != null) { // si un nodo tiene su li diferente de null significa que tiene hijos entonces lo encola.
                    cola.add(r.getLi());
                }

                r = r.getLd(); // continua evaluando los hermanos 
                if (r != null) {
                    hilera += r.getDato();
                }
            }

            if (!cola.isEmpty()) { // cuando termina de recorrer los hermanos de un dato, desencola para continuar con los hermanos del primer hijo ingresado a la cola.
                r = (NodoAB) cola.remove();
            }
        }

        return hilera;
    }
}
