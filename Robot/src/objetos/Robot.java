/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;
import javax.swing.*;
import java.awt.*;
import java.io.*;

// Objeto Robot con sus variables y funciones.
public class Robot extends JFrame {
    // Variables del robot:
    
    // Las coordenadas del robot
    private int x, y;
    
    // Dirección del robot
    private String direccion;
    
    // Etiqueta para mostrar el estado del robot
    private JLabel estado;
    
    // Constructor principal del objeto Robot, aqui inicializaremos la vista y las variables.
    public Robot() {
        // Inicializamos variables.
        x = 0;
        y = 0;
        direccion = "Norte";
        
        // Configuramos vista, especificando titulo, tamaño y inidicamos que al cerrar la ventana se cierre el programa.
        setTitle("Control del Robot");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creamos la etiqueta para mostrar el estado del robot a tiempo real.
        estado = new JLabel("Estado del Robot: {y = " + y + ", x = " + x + "} Dirección: " + direccion);
        
        // Creamos los botones de control del robot
        JButton botonFrontal = new JButton("Movimiento Frontal");
        JButton botonTrasero = new JButton("Movimiento Posterior");
        JButton botonIzquierda = new JButton("Girar Izquierda");
        JButton botonDerecha = new JButton("Girar Derecha");
        JButton botonGuardar = new JButton("Guardar Estado");
        JButton botonCargar = new JButton("Cargar Estado");
        
        // Asignamos nuestras funciones a los botones de control
        botonFrontal.addActionListener(e -> movimientoFrontal());
        botonTrasero.addActionListener(e -> movimientoPosterior());
        botonIzquierda.addActionListener(e -> girarIzquierda());
        botonDerecha.addActionListener(e -> girarDerecha());
        botonGuardar.addActionListener(e -> guardarEstado());
        botonCargar.addActionListener(e -> cargarEstado());
        
        // Creamos el panel de control del robot en forma de tabla de tres filas y dos columnas.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(botonFrontal);
        panel.add(botonTrasero);
        panel.add(botonIzquierda);
        panel.add(botonDerecha);
        panel.add(botonGuardar);
        panel.add(botonCargar);
        
        // Asignamos a la vista el BorderLayout y colocamos de forma atractiva los componentes.
        setLayout(new BorderLayout());
        add(estado, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }
    
    /* Funciones de movimiento, gestionadas por un switch cada una, dependiendo de la dirección actual a la que se dirija el robot 
    sumaremos o restaremos en 1 el valor actual de la cordenada pertinente.*/
    
    // Comentaré la frontal:
    private void movimientoFrontal() {
        
        // Switch para distingir la dirreción actual.
        switch (direccion) {
            // Dependiendo de la dirreción sumaremos o restaremos en 1 el valor actual de la cordenada pertinente.
            case "Norte":
                y++;
                break;
            case "Sur":
                y--;
                break;
            case "Este":
                x++;
                break;
            case "Oeste":
                x--;
                break;
        }
        // Como ultimo, actualizaremos el estado al actual en pantalla.
        actualizarEstado();
    }
    // Perdón si es reiterativo la forma de comentar, pero es para que quede claro.

    private void movimientoPosterior() {
        switch (direccion) {
            case "Norte":
                y--;
                break;
            case "Sur":
                y++;
                break;
            case "Este":
                x--;
                break;
            case "Oeste":
                x++;
                break;
        }
        actualizarEstado();
    }

    private void girarIzquierda() {
        switch (direccion) {
            case "Norte":
                direccion = "Oeste";
                break;
            case "Sur":
                direccion = "Este";
                break;
            case "Este":
                direccion = "Norte";
                break;
            case "Oeste":
                direccion = "Sur";
                break;
        }
        actualizarEstado();
    }
    
    /* Funciones de rotación, gestionadas por un switch cada una, dependiendo de la dirección actual a la que se dirija el robot 
    cambiaremos esa misma dirreción hacia la izquierda o derecha*/ 
    
    // Comentaré la derecha:
    private void girarDerecha() {
        
        // Switch para distingir la dirreción actual.
        switch (direccion) {
           // Dependiendo de la dirreción giraremos a la derecha 90 grados.
            case "Norte":
                direccion = "Este";
                break;
            case "Sur":
                direccion = "Oeste";
                break;
            case "Este":
                direccion = "Sur";
                break;
            case "Oeste":
                direccion = "Norte";
                break;
        }
        // Como ultimo, actualizaremos el estado al actual en pantalla.
        actualizarEstado();
    }
    
    // Función guardarEstado, guardara el estado en un fichero que luego podremos utilizar.
    private void guardarEstado() {
        
        // Dentro del try-catch abrimos los Writer por si los errores y le asignamos nombre al fichero.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estado_robot.txt"))) {
            
            // Escribimos el estado en el fichero con un patron separado por comas.
            writer.write(y + "," + x + "," + direccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Función cargarEstado, cargara el ultimo estado guardado segun el fichero que creamos en guardarEstado().
    private void cargarEstado() {
        
        // Dentro del try-catch abrimos los Reader por si los errores y asignamos nombre al fichero que leeremos.
        try (BufferedReader reader = new BufferedReader(new FileReader("estado_robot.txt"))) {
            
            // Dividimos en un array de Strings las secciones separadas por comas.
            String[] seccion = reader.readLine().split(",");
            
            // Asignamos a nuestras variables cada seccion correspondiente.
            y = Integer.parseInt(seccion[0]);
            x = Integer.parseInt(seccion[1]);
            direccion = seccion[2];
            
            // Muy importante, actualizar estado... He estado más tiempo del que estoy dispuesto admitir para descubrir que este era el error del codigo... ( ˘︹˘ ), vaya tonteria.
            actualizarEstado();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Función actualizarEstado, actualiza la vista al estado actual del robot.
    private void actualizarEstado() {
        estado.setText("Estado del Robot: {y = " + y + ", x = " + x + "} Dirección: " + direccion);
    }

}
