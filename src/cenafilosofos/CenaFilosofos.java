/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofos;

/**
 * Representa la cena de los filósofos
 * @author Roger
 */
public class CenaFilosofos {
    
    /**
     * Número de filósofos en la mesa
     */
    private final static int NUMERO_FILOSOFOS = 5;
    /**
     * Número de palillos en la mesa
     */
    private final static int NUMERO_PALILLOS = 5;
    /**
     * Array de filosofos que van a participar de la cena
     */
    private static Filosofo[] filosofos;
    /**
     * Array de palillos disponibles en la mesa
     */
    private static Palillo[] palillos;
    /**
     * Indica la posicion del palillo en la mesa
     * El primer item del array indica la posicion del palillo derecho
     * El segundo item del array indica la posicion del palillo izquierdo
     */
    private final static int[][] asignacionPalilloFilosofo = {{0, 4}, {1, 0}, {2, 1}, {3, 2}, {4, 3}};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FrmCenaFilosofos.main(null);
    }
    
    /**
     * Da inicio a la cena de los filósofos
     */
    public final static void empezarCena() {
        traerFilosofos();
        traerPalillos();
        arreglarMesa();
        iniciarCena();
    }
    
    /**
     * Trae filosofos a la mesa
     */
    private static void traerFilosofos() {
        filosofos = new Filosofo[NUMERO_FILOSOFOS];
        
        for ( int i=0; i < NUMERO_FILOSOFOS; i++ ) {
            Filosofo filosofo = new Filosofo();
            filosofo.setCodigo(i);
            filosofos[i] = filosofo;
        }
    }
    
    /**
     * Trae palillos a la mesa
     */    
    private static void traerPalillos() {
        palillos = new Palillo[NUMERO_PALILLOS];
        
        for ( int i=0; i < NUMERO_PALILLOS; i++ ) {
            Palillo palillo = new Palillo();
            palillo.setCodigo(i);
            /*palillo.setOcupado(false);
            palillo.setSemaforo(new Semaphore(1));*/
            palillos[i] = palillo;
        }
    }
    
    /**
     * Arregla la mesa para que los filosofos puedan cenar. 
     * A cada filosofo se le va dar una ubicación en la mesa
     * y también se le va indicar la posición de cada palillo .
     */
    private static void arreglarMesa() {
        for ( int i=0; i < NUMERO_FILOSOFOS; i++ ) {
            Filosofo filosofo = filosofos[i];
            int[] posicionPalillos = asignacionPalilloFilosofo[i];
            filosofo.setPalilloDerecha(palillos[posicionPalillos[0]]);
            filosofo.setPalilloIzquierda(palillos[posicionPalillos[1]]);
        }
    }
    
    /**
     * Cada filosofo empieza a cenar
     */
    public static void iniciarCena() {
        for ( int i=0; i < NUMERO_FILOSOFOS; i++ ) {
            Filosofo filosofo = filosofos[i];
            Thread hiloFilosofo = new Thread(filosofo);
            hiloFilosofo.start();
        }
    }    
}