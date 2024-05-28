/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robot;

import objetos.Robot;
import javax.swing.SwingUtilities;

/**
 *
 * @author flare
 */

// Función de ejecución del programa.
public class main {
    
    public static void main(String[] args) {
        // Para evitar problemas de concurrencia con Swing
        SwingUtilities.invokeLater(() -> {
            
            // Creamos la instancia del objeto Robot que torturaremos voluntariamente.
            Robot r = new Robot();
            
            // Y, evidentemente la hacemos visible
            r.setVisible(true);
        });
    }
    
}
