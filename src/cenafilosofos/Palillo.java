/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Roger
 */
public class Palillo {
    
    private int codigo;    
    public Semaphore semaforo;

    public Palillo() {
        semaforo= new Semaphore(1);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Palillo other = (Palillo) obj;
        if (this.getCodigo() != other.getCodigo()) {
            return false;
        }
        return true;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}