/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

import java.awt.Image;

/**
 *
 * @author Bifi
 */
public class cuadroDeAnimacion {

    Image imagen;
    long tiempoFinal;

    public cuadroDeAnimacion(){
            this.imagen = null;
            this.tiempoFinal = 0;
    }

    public cuadroDeAnimacion(Image imagen, long tiempoFinal){
            this.imagen = imagen;
            this.tiempoFinal = tiempoFinal;
    }

    public Image getImagen(){
            return imagen;
    }

    public long getTiempoFinal(){
            return tiempoFinal;
    }

    public void setImagen (Image imagen){
            this.imagen = imagen;
    }

    public void setTiempoFinal(long tiempoFinal){
            this.tiempoFinal = tiempoFinal;
    }
}


