/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
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
    private int iBarSpeed; // velocidad de movimiento de la barra
    private int iProyVSpeed; // velocidad vertical del proyectil
    private int iProyHSpeed; // velocidad horizontal del proyectil
    
    //tiempos
    private long tiempoActual; // el tiempo actual
    private long tiempoInicial; // el tiempo en el que se inicio
    
    // variables booleanas
    private boolean bPausa; // booleana para pausar y despaudar el juego
    private boolean bGameOver; // booleana para definir el estado del juego
    private boolean bColision; // booleana para saber si el proyectil colisiona
    private boolean bStart; // booleana previo de empezar a jugar
    private boolean bLeft; // booleana que indica que se mueve a la izq
    private boolean bRight; // booleana que indica que se mueve a la der
    
    // variables clase Base
    private Base basBarra; // barra de clase base
    private Base basProyectil; // proyectil de clase base
    
    // varialbes linkedlist
    private LinkedList<Base> lklMeth; // LinkedList de clase base para meth
    private LinkedList<Animacion> lklAniMeth; // Lista de clase animacion
    
    // variables para animacion
    private Animacion aniBala; // animacion de la bala
    
    // variables imagenes
    private Image    imaImagenApplet;   // Imagen a proyectar en Applet	
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen
    private Image imaGameOver; // imagen de gameover
    private Image imaBackground; // imagen del background
    private Image imaProyectil1; // imagen del proyectil
    private Image imaProyectil2; // imagen del proyectil
    private Image imaProyectil3; // imagen del proyectil
    private Image imaProyectil4; // imagen del proyectil
    private Image imaBarra; // imagen de la barra
    private Image imaMeth1; // imagen del meth1
    private Image imaMeth2; // imagen del meth2
    private Image imaMeth3; // imagen del meth3
    private Image imaMeth4; // imagen del meth4
    private Image imaMeth5; // imagen del meth5
    
    
    
    
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
        setSize(600, 400);
       
        // inicializar las booleanas
        bGameOver = false;
        bColision = true;
        bPausa = false;
        bStart = false;
        
        // inicializar vidas
        iVidas = 5;
        
        // inicializacion de posiciones
        iPosBarraX = 0;
        iPosBarraY = 350;
        iPosProyectilX = 400;
        iPosProyectilY = 420;
        iPosMethX = 10;
        iPosMethY = 10;
        
        //inicializacion de velocidades;
        iBarSpeed = 3;
        iProyHSpeed = 0;
        iProyVSpeed = 3;
        
        // poner las imagenes en sus variables
        imaBarra = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("barra.png"));
        
        imaProyectil1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("bala1.png"));
       
        imaProyectil2 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("bala2.png"));
        
        imaProyectil3 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("bala3.png"));
        imaProyectil4 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("bala4.png"));
        
        imaMeth1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("Meth1.png"));
        
        imaMeth2 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("Meth2.png"));
        
        imaMeth3 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("Meth3.png"));
        
        imaMeth4 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("Meth4.png"));
        
        imaMeth5 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("Meth5.png"));
        

        aniBala = new Animacion();
        aniBala.sumaCuadro(imaProyectil1, 100);
        aniBala.sumaCuadro(imaProyectil2, 100);
        aniBala.sumaCuadro(imaProyectil3, 100);
        aniBala.sumaCuadro(imaProyectil4, 100);
        
        
        // se crea el objeto base para la barra
        basBarra = new tarea5.Base(iPosBarraX, iPosBarraY, 100,
                   20, imaBarra);
        
        basBarra.setX(getWidth()/2 - basBarra.getAncho()/2);
                  
        // se crea el objeto base para el proyectil
        basProyectil = new tarea5.Base(iPosProyectilX, iPosProyectilY, 15,
                       15, imaProyectil1);
        
        // se crean la linkedlist del meth
        lklMeth = new LinkedList();
        lklAniMeth = new LinkedList();
        
        Base basAux;
        Animacion aniAux;
        int iTempX = 0;
        int iTempY = 70;
        for (int i = 1; i <= 10; i++) {
            
            basAux = new Base (iTempX, iTempY, 60, 20, imaMeth1);
            iTempX += 60;
            lklMeth.add(basAux);       

            // se crea la animacion
            aniAux = new Animacion();
            aniAux.sumaCuadro(imaMeth1, 100);
            aniAux.sumaCuadro(imaMeth2, 100);
            aniAux.sumaCuadro(imaMeth3, 100);
            aniAux.sumaCuadro(imaMeth4, 100);
            aniAux.sumaCuadro(imaMeth5, 100);
            lklAniMeth.add(aniAux);
        }
        
        addKeyListener(this);
        
    }
    
        /**
     * paint
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor y 
     * define cuando usar ahora el paint1
     * 
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     * 
     */
    public void paint (Graphics graGrafico) {
        // Inicializan el DoubleBuffer
        if (imaImagenApplet == null){
                imaImagenApplet = createImage (this.getSize().width,
                        this.getSize().height);
                graGraficaApplet = imaImagenApplet.getGraphics ();
        }
 
        // Actualiza la imagen de fondo.
        URL urlImagenFondo = this.getClass().getResource("background.png");
        Image imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
         graGraficaApplet.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);
 
        // Actualiza el Foreground.
        graGraficaApplet.setColor (getForeground());
        paint1(graGraficaApplet);
 
        // Dibuja la imagen actualizada
        graGrafico.drawImage (imaImagenApplet, 0, 0, this);
    }
    
    /**
     * paint1
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada,
     * ademas que cuando la imagen es cargada te despliega una advertencia.
     * 
     * @param graDibujo es el objeto de <code>Graphics</code> usado para dibujar.
     * 
     */
    public void paint1(Graphics graDibujo){
        if(imaBackground != null) {
                        graDibujo.drawImage (imaBackground, 0, 0, this);
                }
                
                if (basProyectil != null && basBarra != null && lklMeth != null) {
                        //Dibuja la imagen del proyectil en el JFrame
                        basProyectil.paint(graDibujo, this);
                        
                        //Dibuja la imagen de la barra en el JFrame
                        basBarra.paint(graDibujo, this);
                        
                        for(Base basMeth : lklMeth) {
                                //dibuja la imagen de meth en el JFrame
                                basMeth.paint(graDibujo, this);
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
        /* mientras dure el juego, se actualizan posiciones de jugadores
           se checa si hubo colisiones para desaparecer jugadores o corregir
           movimientos y se vuelve a pintar todo
        */
        tiempoActual = System.currentTimeMillis();
        
        while (!bGameOver) {
            if(!bPausa) {
                actualiza();
                checaColision();
            }
            repaint();
            try {
                // El thread se duerme.
                Thread.sleep (20);
            }
            catch (InterruptedException iexError) {
                System.out.println("Hubo un error en el juego " +
                        iexError.toString());
            }
        }
        while(bGameOver){
            repaint();
            actualiza();

        }
       
        
    }
    
    public void actualiza() {
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        tiempoActual += tiempoTranscurrido;

        aniBala.actualiza(tiempoTranscurrido);
        basProyectil.setImagen(aniBala.getImagen());


        for (int iI = 0; iI < lklMeth.size(); iI ++ ) {
            Animacion aniObjeto = lklAniMeth.get(iI);
            Base basObjeto = lklMeth.get(iI);
            basObjeto.setImagen(aniObjeto.getImagen());
        }        

        if (bLeft) {
            basBarra.setX(basBarra.getX() - iBarSpeed);
        }
        if (bRight) {
            basBarra.setX(basBarra.getX() + iBarSpeed);
        }
        basProyectil.setY(basProyectil.getY() - iProyVSpeed);
        basProyectil.setX(basProyectil.getX() - iProyHSpeed);

        if (iVidas <= 0) {
            bGameOver = true;
        }
    }
    
    public void checaColision() {
        if(basProyectil.getY() < 0) { // y esta pasando el limite
                basProyectil.setY(0);
                iProyVSpeed *= -1;
            }
            if(basProyectil.getY() + basProyectil.getAlto() > getHeight()) {
                iProyVSpeed *= -1;
                basProyectil.setX(basBarra.getX() + basBarra.getAncho()/2);
                basProyectil.setY(basBarra.getY() - basProyectil.getAlto());
                iVidas --;
            }
            if(basProyectil.getX() < 0) { // y se sale del applet
                basProyectil.setX(0);
                iProyHSpeed *= -1;
            }
            if(basProyectil.getX() + basProyectil.getAncho() > getWidth()) { 
                basProyectil.setX(getWidth()- basProyectil.getAncho());
                iProyHSpeed *= -1;
            }
            if(basBarra.getX() < 0) { // y se sale del applet
                basBarra.setX(0);
            }
            if(basBarra.getX() + basBarra.getAncho() > getWidth()) { 
                basBarra.setX(getWidth() - basBarra.getAncho());
            }
            Base basTemp = null;
            Animacion aniTemp = null;

            
            for (int iI = 0; iI < lklMeth.size(); iI ++ ) {
                Base basMeth = lklMeth.get(iI);
                if (basMeth.intersectaIzq(basProyectil)) {
                    iProyHSpeed *= -1;
                    if (basMeth.getImagen() == imaMeth3) {
                        basMeth.setImagen(advAnim(iI));
                        basMeth.setImagen(advAnim(iI));
                        basTemp = basMeth;
                        aniTemp = lklAniMeth.get(iI);
                    }
                    else {
                        basMeth.setImagen(advAnim(iI));
                    }
                }
                else if (basMeth.intersectaDer(basProyectil)) {
                    iProyHSpeed *= -1;
                    if (basMeth.getImagen() == imaMeth3) {
                        basMeth.setImagen(advAnim(iI));
                        basMeth.setImagen(advAnim(iI));
                        basTemp = basMeth;
                        aniTemp = lklAniMeth.get(iI);
                    }
                    else {
                        basMeth.setImagen(advAnim(iI));
                    }
                }
                else if (basMeth.intersectaAba(basProyectil)) {
                    iProyVSpeed *= -1;
                    if (basMeth.getImagen() == imaMeth3) {
                        basMeth.setImagen(advAnim(iI));
                        basMeth.setImagen(advAnim(iI));
                        basTemp = basMeth;
                        aniTemp = lklAniMeth.get(iI);
                    }
                    else {
                        basMeth.setImagen(advAnim(iI));
                    }
                }
                else if (basMeth.intersectaArr(basProyectil)) {
                    iProyVSpeed *= -1;
                    if (basMeth.getImagen() == imaMeth3) {
                        basMeth.setImagen(advAnim(iI));
                        basMeth.setImagen(advAnim(iI));
                        basTemp = basMeth;
                        aniTemp = lklAniMeth.get(iI);
                    }
                    else {
                        basMeth.setImagen(advAnim(iI));
                    }
                }
            } 
       
            if (basTemp != null) {
                lklAniMeth.remove(aniTemp);
                lklMeth.remove(basTemp);
                
            }
            if (basBarra.intersectaArr(basProyectil)) {
                colisionBarra();
            }
    }
    
    public Image advAnim(int iIndex) {
        
    Animacion aniTemp = lklAniMeth.get(iIndex);
    aniTemp.actualiza(101);

    return aniTemp.getImagen();
    }
    
    public void colisionBarra() {
                iProyVSpeed *= -1;
                double iPosX = basProyectil.getX() - basBarra.getX();
                double iPercent = (iPosX / (basBarra.getAncho() - basProyectil.getAncho())) - 0.5;
                iProyHSpeed += (int) -(iPercent * 8);
                if (iProyHSpeed > 6) {
                    iProyHSpeed = 6;
                }
                if (iProyHSpeed < -6) {
                    iProyHSpeed = -6;
                }
        }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //key pressed para mover la barra <--->
        if(keyEvent.getKeyCode() == keyEvent.VK_LEFT){
            bLeft = true;
        }
        if(keyEvent.getKeyCode() == keyEvent.VK_RIGHT){
            bRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == keyEvent.VK_P) {
            bPausa = !bPausa;
        }
        if(keyEvent.getKeyCode() == keyEvent.VK_LEFT){
            bLeft = false;
        }
        if(keyEvent.getKeyCode() == keyEvent.VK_RIGHT){
            bRight = false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (bGameOver) {
                                init();
                        }
}
    }
    
}