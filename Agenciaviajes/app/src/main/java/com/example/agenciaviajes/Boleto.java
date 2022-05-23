package com.example.agenciaviajes;

public class Boleto {
    //Atributos
    public int noBoleto;
    public String fecha;
    public String nombreCliente;
    public String destino;
    public int tipoViaje;
    public float precio;
    public float descuento;
    //Constructores

    //Constructor vacío
    public Boleto() {
    }

    //Constructor de parámetros
    public Boleto(int noBoleto, String fecha, String nombreCliente, String destino, int tipoViaje, float precio) {
        this.noBoleto = noBoleto;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.destino = destino;
        this.tipoViaje = tipoViaje;
        this.precio = precio;
    }

    //Constructor de copia
    public Boleto(Boleto copy){
        this.noBoleto = copy.noBoleto;
        this.fecha = copy.fecha;
        this.nombreCliente = copy.nombreCliente;
        this.destino = copy.destino;
        this.tipoViaje = copy.tipoViaje;
        this.precio = copy.precio;
    }

    //Encapsulamiento
    /**
     * @return the noBoleto
     */
    public int getNoBoleto() {
        return noBoleto;
    }

    /**
     * @param noBoleto the noBoleto to set
     */
    public void setNoBoleto(int noBoleto) {
        this.noBoleto = noBoleto;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the tipoViaje
     */
    public int getTipoViaje() {
        return tipoViaje;
    }

    /**
     * @param tipoViaje the tipoViaje to set
     */
    public void setTipoViaje(int tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }


    //Métodos
    public float calcularSubtotal(){
        float subTotal = 0.0f;
        if(tipoViaje == 1){
            subTotal = precio;
        }
        if(tipoViaje == 2){
            subTotal = precio * 1.8f;
        }
        return subTotal;
    }

    public float calcularImpuesto(){
        return calcularSubtotal() * 0.16f;
    }

    public float calcularDescuento(int edad){
        if(edad>=60){
            descuento = calcularSubtotal() / 2;
            return descuento;
        }
        else{
            return 0;
        }
    }

    public float calcularTotal(){
        float total = 0.0f;
        total = (calcularSubtotal() + calcularImpuesto()) - descuento;
        return total;
    }
}
