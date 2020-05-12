package practicaarboles;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author julian.agudelo1 
 * @author Daniela Ocampo
 */
public class Validacion {

    public void validar(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char a = e.getKeyChar();
                int caracterProhibido = (int)e.getKeyChar();
                /*
                Valores de caracterProhibido
                32 : Espacio
                40 : (
                41 : )
                44 : ,
                */
                if(Character.isDigit(a) || caracterProhibido == 32
                        || caracterProhibido == 40 || caracterProhibido == 41
                        || caracterProhibido == 44){
                    e.consume();
                }
            }
        });
    }
    
    public void limitarCantidad(JTextField campo, int cantidad) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                int tam = campo.getText().length();
                if(tam >= cantidad){
                    e.consume();
                }
            }
        });
    }
}
