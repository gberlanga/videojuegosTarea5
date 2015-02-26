package tarea5;

/**
 * Base
 *
 * Modela la definición de todos los objetos de tipo
 * <code>Base</code>
 *
 * @author Gabriel Berlanga y L. Rafael Hinojosa V.
 * @version 1.9 
 * @date 11/feb/15
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class Base {

    private int iX;     //posicion en x.       
    private int iY;     //posicion en y.
    private int iAncho; //ancho del objeto
    private int iAlto; //largo del objeto
    private Image imaImagen;	//imagen.

    /**
     * Base
     * 
     * Metodo constructor usado para crear el objeto animal
     * creando el icono a partir de una imagen
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param iAncho es el <code>ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Base(int iX, int iY , int iAncho, int iAlto,Image imaImagen) {
        this.iX = iX;
        this.iY = iY;
        this.iAncho = iAncho;
        this.iAlto = iAlto;
        this.imaImagen = imaImagen;
    }

    
    /**
     * setX
     * 
     * Metodo modificador usado para cambiar la posicion en x del objeto
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * 
     */
    public void setX(int iX) {
        this.iX = iX;
    }

    /**
     * getX
     * 
     * Metodo de acceso que regresa la posicion en x del objeto 
     * 
     * @return iX es la <code>posicion en x</code> del objeto.
     * 
     */
    public int getX() {
            return iX;
    }

    /**
     * setY
     * 
     * Metodo modificador usado para cambiar la posicion en y del objeto 
     * 
     * @param iY es la <code>posicion en y</code> del objeto.
     * 
     */
    public void setY(int iY) {
            this.iY = iY;
    }

    /**
     * getY
     * 
     * Metodo de acceso que regresa la posicion en y del objeto 
     * 
     * @return posY es la <code>posicion en y</code> del objeto.
     * 
     */
    public int getY() {
        return iY;
    }

    /**
     * setImagen
     * 
     * Metodo modificador usado para cambiar el icono de imagen del objeto
     * tomandolo de un objeto imagen
     * 
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public void setImagen(Image imaImagen) {
        this.imaImagen = imaImagen;
    }

    /**
     * getImagen
     * 
     * Metodo de acceso que regresa la imagen que representa el icono del objeto
     * 
     * @return la imagen a partide del <code>icono</code> del objeto.
     * 
     */
    public Image getImagen() {
        return imaImagen;
    }

    /**
     * getAncho
     * 
     * Metodo de acceso que regresa el ancho del icono 
     * 
     * @return un <code>entero</code> que es el ancho de la imagen.
     * 
     */
    public int getAncho() {
        return iAncho;
    }

    /**
     * getAlto
     * 
     * Metodo que  da el alto del icono 
     * 
     * @return un <code>entero</code> que es el alto de la imagen.
     * 
     */
    public int getAlto() {
        return iAlto;
    }
    
    /**
     * paint
     * 
     * Metodo para pintar el animal
     * 
     * @param graGrafico    objeto de la clase <code>Graphics</code> para graficar
     * @param imoObserver  objeto de la clase <code>ImageObserver</code> es el 
     *    Applet donde se pintara
     * 
     */
    public void paint(Graphics graGrafico, ImageObserver imoObserver) {
        graGrafico.drawImage(getImagen(), getX(), getY(), getAncho(), getAlto(), imoObserver);
    }

    /**
     * equals
     * 
     * Metodo para checar igualdad con otro objeto
     * 
     * @param objObjeto    objeto de la clase <code>Object</code> para comparar
     * @return un valor <code>boleano</code> que sera verdadero si el objeto
     *   que invoca es igual al objeto recibido como parámetro
     * 
     */
    public boolean equals(Object objObjeto) {
        // si el objeto parametro es una instancia de la clase Base
        if (objObjeto instanceof Base) {
            // se regresa la comparación entre este objeto que invoca y el
            // objeto recibido como parametro
            Base basParam = (Base) objObjeto;
            return this.getX() ==  basParam.getX() && 
                    this.getY() == basParam.getY() &&
                    this.getAncho() == basParam.getAncho() &&
                    this.getAlto() == basParam.getAlto() &&
                    this.getImagen() == basParam.getImagen();
        }
        else {
            // se regresa un falso porque el objeto recibido no es tipo Base
            return false;
        }
    }
    
    /**
     * Metodo intersectaIzq
     * 
     * metodo que revisa si se intersectan dos objetos a la izq
     * 
     * @param objObjeto es un objeto de la clase <code>Objeto</code>
     * @return un balor <code>boolean</code> indicando si se intersectan
     */
    
    public boolean intersectaIzq(Object objObjeto) {
        if (objObjeto instanceof Base) {
            Rectangle rctEste = new Rectangle(this.getX(), this.getY(), 
                                5, this.getAlto());
            Base basTemp = (Base) objObjeto;
            Rectangle rctParam = new Rectangle(basTemp.getX(),basTemp.getY(),
                                basTemp.getAncho(), basTemp.getAlto());
            
            return rctEste.intersects(rctParam);
        }
        return false;
    }
    /**
     * Metodo intersectaDer
     * 
     * metodo que revisa si se intersectan dos objetos por la derecha
     * 
     * @param objObjeto es un objeto de la clase <code>Objeto</code>
     * @return un balor <code>boolean</code> indicando si se intersectan
     */
    
    public boolean intersectaDer(Object objObjeto) {
        if (objObjeto instanceof Base) {
            Rectangle rctEste = new Rectangle(this.getX() + this.getAncho() + 5,
                                    this.getY(), 5, this.getAlto());
            Base basTemp = (Base) objObjeto;
            Rectangle rctParam = new Rectangle(basTemp.getX(),basTemp.getY(),
                                basTemp.getAncho(), basTemp.getAlto());
            
            return rctEste.intersects(rctParam);
        }
        return false;
    }
    /**
     * Metodo intersectaArr
     * 
     * metodo que revisa si se intersectan dos objetos por arriba
     * 
     * @param objObjeto es un objeto de la clase <code>Objeto</code>
     * @return un balor <code>boolean</code> indicando si se intersectan
     */
    
    public boolean intersectaArr(Object objObjeto) {
        if (objObjeto instanceof Base) {
            Rectangle rctEste = new Rectangle(this.getX(), this.getY(), 
                                this.getAncho(), 5);
            Base basTemp = (Base) objObjeto;
            Rectangle rctParam = new Rectangle(basTemp.getX(),basTemp.getY(),
                                basTemp.getAncho(), basTemp.getAlto());
            
            return rctEste.intersects(rctParam);
        }
        return false;
    }
    /**
     * Metodo intersectaAba
     * 
     * metodo que revisa si se intersectan dos objetos por abajo
     * 
     * @param objObjeto es un objeto de la clase <code>Objeto</code>
     * @return un balor <code>boolean</code> indicando si se intersectan
     */
    
    public boolean intersectaAba(Object objObjeto) {
        if (objObjeto instanceof Base) {
            Rectangle rctEste = new Rectangle(this.getX() , this.getY() + this.getAlto() - 5, 
                                this.getAncho(), 5);
            Base basTemp = (Base) objObjeto;
            Rectangle rctParam = new Rectangle(basTemp.getX(),basTemp.getY(),
                                basTemp.getAncho(), basTemp.getAlto());
            
            return rctEste.intersects(rctParam);
        }
        return false;
    }

    /**
     * toString
     * 
     * Metodo para obtener la interfaz del objeto
     * 
      * @return un valor <code>String</code> que representa al objeto
     * 
     */
    public String toString() {
        // regresa los valores de x, y, ancho alto separado por commas
        return this.getX() + ","+ this.getY() + "," + this.getAncho() + 
                "," + this.getAlto();
    }
}