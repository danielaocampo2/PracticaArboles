package practicaarboles;
import javax.swing.JOptionPane;

/**
 *
 * @author julian.agudelo1
 * @author Daniela Ocampo
 */

public class PracticaArboles {
    public static void main(String[] args) {
        String s = "(a(b(c,d(e)),f,g(h,i(j,k(l)),m,n)))"; // Hilera que se desea representar en un árbol
        char padre='n'; // Variable para ingresar el dato del cual se quiera saber el padre
        char ancestros= 'm'; // Variable para ingresar el dato del cual se quiera conocer los ancestros.
        char gradoDato='x'; // Variable para ingresar el dato del cual se quiera conocer el grado.
        
        ListaGeneralizada arbol = new ListaGeneralizada();
        arbol.ConstruyeArbol(s); // Contruye el árbol n-arbio con base a la hilera enviada.
        // imprime el árbol n-ario.
        JOptionPane.showMessageDialog(null, arbol.imprimeArbol(arbol.getPrimero(), 0), "Hilera que representa el árbol n-ario", JOptionPane.INFORMATION_MESSAGE);
        
        ListaLigada arbolBinario = new ListaLigada();
        arbolBinario.convierteLgABinario(arbol); // convierte el árbol n-ario en árbol binario.
        
        // Con base a la representación del árbol binario se realizo:
        // Grado del árbol n-ario.
        JOptionPane.showMessageDialog(null, arbolBinario.gradoNArioEnBinario(arbolBinario.getPrimero()), "Grado del árbol n-ario", JOptionPane.INFORMATION_MESSAGE);
        //Altura del árbol n-ario.
        JOptionPane.showMessageDialog(null, arbolBinario.alturaNArioEnBinario(arbolBinario.getPrimero()), "Altura del árbol n-ario", JOptionPane.INFORMATION_MESSAGE);
        //Número de hojas del árbol n-ario.
        JOptionPane.showMessageDialog(null, arbolBinario.hojasNArioEnBinario(arbolBinario.getPrimero()), "Hojas del árbol n-ario", JOptionPane.INFORMATION_MESSAGE);
        // Ancestros de un dato ingresado.
        JOptionPane.showMessageDialog(null, arbolBinario.ancestrosNArioEnBinario(ancestros), "Ancestros de: " + ancestros, JOptionPane.INFORMATION_MESSAGE);
        // Padre de un dato.
        JOptionPane.showMessageDialog(null, arbolBinario.padreNArioEnBinario(arbolBinario.getPrimero(), padre), "Padre de: " + padre, JOptionPane.INFORMATION_MESSAGE);
        // Recorrido inorden. 
        JOptionPane.showMessageDialog(null, arbolBinario.inorden(arbolBinario.getPrimero()), "Recorrido INORDEN del árbol", JOptionPane.INFORMATION_MESSAGE);
        // Recorrido posorden.
        JOptionPane.showMessageDialog(null, arbolBinario.posorden(arbolBinario.getPrimero()), "Recorrido POSORDEN del árbol", JOptionPane.INFORMATION_MESSAGE);
        // Recorrido preorden.
        JOptionPane.showMessageDialog(null, arbolBinario.preorden(arbolBinario.getPrimero()), "Recorrido PREORDEN del árbol", JOptionPane.INFORMATION_MESSAGE);
        // Grado de un dato en el árbol n-ario.
        int grado=arbolBinario.gradoDato(gradoDato);
        if(grado==-1){
                JOptionPane.showMessageDialog(null, "El dato ingresado no se encuentra en el árbol");
        }
        else{
                JOptionPane.showMessageDialog(null, grado, "El grado del dato: " + gradoDato, JOptionPane.INFORMATION_MESSAGE);
        }
        // Recorrido por niveles en un árbol n-ario.
       JOptionPane.showMessageDialog(null, arbolBinario.recorridoPorNiveles(), "Recorrido por niveles del árbol n-ario:  ", JOptionPane.INFORMATION_MESSAGE);

       
    }
}