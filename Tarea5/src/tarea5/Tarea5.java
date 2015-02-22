/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 *
 * @author Gabriel Berlanga y Rafael Hinojosa
 */
public class Tarea5 extends JFrame implements Runnable, KeyListener {
    // variable integers
    private int iPosBarraX; // posicion de la barra en x
    private int iPosBarraY; // posicion de la barra en y
    private int iPosProyectilX; // posicion del proyectil en x
    private int iPosProyectilY; // posicion del proyectil en y
    private int iPosMethX; // posicion del meth en x
    private int iPosMethY; // posicion del meth en y
    private int iVidas; // numero de vidas restantes
    
    // variables booleanas
    private boolean bPausa; // booleana para pausar y despaudar el juego
    private boolean bGameOver; // booleana para definir el estado del juego
    private boolean bColision; // booleana para saber si el proyectil colisiona
    private boolean bStart; // booleana previo de empezar a jugar
    
    // variables clase Base
    private Base basBarra; // barra de clase base
    private Base basProyectil; // proyectil de clase base
    
    // varialbes linkedlist
    private LinkedList<Base> lklMeth; // LinkedList de clase base para meth
    
    // variables imagenes
    private Image imaGameOver; // imagen de gameover
    private Image imaBackground; // imagen del background
    private Image imaProyectil; // imagen del proyectil
    private Image imaBarra; // imagen de la barra
    private Image imaMeth; // imagen del meth
    
    
    public Tarea5() {
        init();
        start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /** 
     * start
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo
     * para la animacion este metodo es llamado despues del init 
     * <code>Applet</code>
     * 
     */
    public void start () {
        //Declaras un hilo
        Thread th = new Thread (this);
        //empieza el hilo
        th.start();
    }
    
     /** 
     * init
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos
     * a usarse en el <code>Applet</code> y se definen funcionalidades.
     * 
     */
    public void init(){
        //set size del juego
        setSize(800, 500);
        
        // inicializar las booleanas
        bGameOver = false;
        bColision = true;
        bPausa = false;
        bStart = false;
        
        // inicializar vidas
        iVidas = 5;
        
        // inicializacion de posiciones
        iPosBarraX = 300;
        iPosBarraY = 470;
        iPosProyectilX = 400;
        iPosProyectilY = 420;
        iPosMethX = 10;
        iPosMethY = 10;
        
        // poner las imagenes en sus variables
        imaBarra = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("crowbar.png"));
        imaProyectil = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("bullet.png"));
        imaMeth = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("bbcm.jpg"));
        
        // se crea el objeto base para la barra
        basBarra = new tarea5.Base(iPosBarraX, iPosBarraY, getWidth(),
                   getHeight(), imaBarra);
                  
        // se crea el objeto base para el proyectil
        basProyectil = new tarea5.Base(iPosProyectilX, iPosProyectilY, getWidth(),
                       getHeight(), imaProyectil);
        
        // se crean la linkedlist del meth
        lklMeth = new LinkedList();
        
        Base basAux;
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 10; j++) {
                basAux = new Base (50 + j * 75, 50 + i * 75, 60, 60, imaMeth);
                       lklMeth.add(basAux);
            }
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tarea5 juego = new Tarea5();
        juego.setVisible(true);
    }

    @Override
    public void run() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
