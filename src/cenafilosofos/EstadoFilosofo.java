/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofos;

import java.awt.Color;

/**
 * Estados por los que pasa un filósofo durante la cena
 * @author Roger
 */
public enum EstadoFilosofo {
    PENSANDO("Pensando", Color.GRAY), 
    COMIENDO("Comiendo", Color.BLUE), 
    ESPERANDO("Esperando", Color.YELLOW), 
    SERVIDO("Servido", Color.GREEN);
    
    private final String descripcion;
    
    private final Color color;

    public String getDescripcion() {
        return descripcion;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    private EstadoFilosofo(String valDescripcion, Color valColor) {
        this.descripcion = valDescripcion;
        this.color = valColor;
    }
    
}