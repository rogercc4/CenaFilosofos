/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofos;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

/**
 *
 * @author Roger
 */
public class Filosofo implements Runnable {
    
    private int codigo;
    private EstadoFilosofo estadoFilosofo;
    private Palillo palilloDerecha;
    private Palillo palilloIzquierda;


    @Override
    public void run() {
        try {
            pensar();
            while(true) {
                comer();
                //Thread.sleep(1000L); // para poder ver el estado de cambio a pensando
            }
        } catch (Exception e) {
        }
    }
    
    public void pensar() {
        try {
            this.setEstadoFilosofo(EstadoFilosofo.PENSANDO);
            this.actualizarInterfaz();            
            java.util.Random random = new java.util.Random();
            long tiempoPensar = random.nextInt(3000) + 2000;
            Thread.sleep(tiempoPensar);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarInterfaz() {
        FrmCenaFilosofos.lblsFilosofos[codigo].setText(estadoFilosofo.getDescripcion());
        FrmCenaFilosofos.lblsFilosofos[codigo].setForeground(this.getEstadoFilosofo().getColor());
        FrmCenaFilosofos.lblsPalillos[palilloDerecha.getCodigo()].setVisible((palilloDerecha.semaforo.availablePermits()==0));
        FrmCenaFilosofos.lblsPalillos[palilloIzquierda.getCodigo()].setVisible((palilloIzquierda.semaforo.availablePermits()==0));
    }
    
    public void comer() {
        Random random = new Random();
        long tiempoComer = random.nextInt(3000) + 2000;
        long tiempoEsperar = 1000L;
        boolean existeDerecho=false;
        boolean existeIzquierdo=false;
        try {
            existeDerecho = palilloDerecha.semaforo.tryAcquire();
            existeIzquierdo = palilloIzquierda.semaforo.tryAcquire();
            if ( existeDerecho && existeIzquierdo ) {
                    this.setEstadoFilosofo(EstadoFilosofo.COMIENDO);
                    this.actualizarInterfaz();
                    Thread.sleep(tiempoComer); // tiempo aproximado que demora en comer
                    
                    this.setEstadoFilosofo(EstadoFilosofo.SERVIDO);
                    this.actualizarInterfaz();
                    Thread.sleep(tiempoEsperar); // para poder ver el estado de cambio a pensando
                    
                    palilloDerecha.semaforo.release();
                    palilloIzquierda.semaforo.release();
                    this.pensar();
            } else {
                this.setEstadoFilosofo(EstadoFilosofo.ESPERANDO);
                actualizarInterfaz();
                Thread.sleep(100L); // para poder ver el estado de cambio a pensando
                if(existeDerecho)
                    palilloDerecha.semaforo.release();
                if(existeIzquierdo)
                    palilloIzquierda.semaforo.release();
                actualizarInterfaz();
                Thread.sleep(tiempoEsperar); // tiempo aproximado que demora en comer
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Palillo getPalilloDerecha() {
        return palilloDerecha;
    }

    public void setPalilloDerecha(Palillo palilloDerecha) {
        this.palilloDerecha = palilloDerecha;
    }
    
    public Palillo getPalilloIzquierda() {
        return palilloIzquierda;
    }
    
    public void setPalilloIzquierda(Palillo palilloIzquierda) {
        this.palilloIzquierda = palilloIzquierda;
    }

    public EstadoFilosofo getEstadoFilosofo() {
        return estadoFilosofo;
    }

    public void setEstadoFilosofo(EstadoFilosofo estadoFilosofo) {
        this.estadoFilosofo = estadoFilosofo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Filosofo other = (Filosofo) obj;
        return this.codigo == other.codigo;
    }    
    
}