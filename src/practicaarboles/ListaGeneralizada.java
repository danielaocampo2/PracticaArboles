package practicaarboles;

import java.util.Stack;

/**
 *
 * @author julian.agudelo1
 * @author Daniela Ocampo
 */
public class ListaGeneralizada {
    private NodoLG primero;
    private NodoLG ultimo;

    public ListaGeneralizada() {
        primero = ultimo = null;
    }

    public NodoLG getPrimero() {
        return primero;
    }

    public void setPrimero(NodoLG primero) {
        this.primero = primero;
    }

    public NodoLG getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoLG ultimo) {
        this.ultimo = ultimo;
    }
    
    public boolean finDeRecorrido(NodoLG x){
        return x == null;
    }
    
    public void ConstruyeArbol(String s){ // construye árbol n-ario en base de la hilera entrada de parentesis, átomos, y comas.
        int n;
        int i;
        NodoLG primeroo;
        NodoLG ultimoo;
        NodoLG x;
        Stack pila = new Stack();
        primeroo = new NodoLG(null); // Raíz del árbol
        ultimoo = primeroo;
        primeroo.setDato(s.charAt(1)); // toma el primer átomo (la raiz, en este caso llamamos primero) , ya que lo que hay en la posición 0 res un (
        n = s.length();
        if(n == 2){ // si la longitud de la hilera es igual a 2, es porque la hilera ingresada fue (átomo), entonces solo agraga el atomo.
            return;
        }
        i = 3; 
        while(i <= n - 3){ // realiza este procedimiento hasta que encuentre el último dato. 
            x = new NodoLG(null);
            ultimoo.setLiga(x);
            ultimoo = x;
            if(s.charAt(i + 1) == '('){ // cada que encuentre un '(' es porque ese nodo debe tener sw=1 y en el camppo dato hay un apuntador hacia el nodo que contiene el átomo.
                ultimoo.setSw(1);
                pila.push(ultimoo); // se guarda en la pila el nodo 
                x = new NodoLG(s.charAt(i));
                ultimoo.setDato(x);
                ultimoo = x;
                i = i + 2;
            } else{
                ultimoo.setDato(s.charAt(i)); // sino se agrega el Dato al nodo y se valida si 
                if(s.charAt(i + 1) == ')'){ // la posicion siguiente es ')' entonces a se le suma 1 
                    i = i + 1;
                    while((i < n - 1) && (s.charAt(i) == ')') && !pila.empty()){ // mientras lo que hay en la posición siguiente sea ')', lo que significa que termina sub-árbol, hace:
                        ultimoo = (NodoLG)pila.pop(); // se desapila actualizando último para continuar donde se habia empezado.
                        i = i + 1;
                    }
                    if(s.charAt(i) == ','){
                        i = i + 1;
                    }
                } else{
                    i = i + 2;
                }
            }
        }
        primero = primeroo;
        ultimo = ultimoo;
    }
    
    public String imprimeArbol(NodoLG l, int err){ // imprime árbol basados en hilera de ( atomo , )
        NodoLG p;
        NodoLG primeroo;
        NodoLG q;
        String hilera = "";
        if(l == null){ 
            hilera = ("LISTA VACIA");
            return hilera;
        }
        primeroo = null;
        if(err == 0){ // la primera vez que se inicializa err es igual a 0, por lo tanto debe abrir ( y evaluar si:
            hilera += ("(" + l.getDato());
            if(l.getLiga() == null){ // si no hay un nodo siguiente debe cerrar ) y retornar 
                hilera += (")");
                return hilera;
            }
            hilera += ("("); // sino solo abre ( 
            primeroo = l;
        }
        p = l.getLiga();
        while(p != null){ 
            if(p.getSw() == 0){
                hilera += (p.getDato());
            } else{
                q = (NodoLG)p.getDato();
                hilera += (q.getDato() + "(");
                hilera += imprimeArbol((NodoLG)p.getDato(), 1); // si el sw del nodo es diferente de cero hace llamada recursiva con el nodo que se almacena en el campo dato 
            }
            if(p.getLiga() != null){ 
                hilera += (",");
            }
            p = p.getLiga();
        }
        hilera += (")");
        if(primeroo == l){
            hilera += (")");
        }
        return hilera;
    }
}
