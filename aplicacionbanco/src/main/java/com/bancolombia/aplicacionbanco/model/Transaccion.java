package com.bancolombia.aplicacionbanco.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;
    private Integer idCuenta;
    private String tipoTransaccion;
    private String descripcion;
    private BigDecimal monto;
    private Date fechaTransaccion;

    public Transaccion() {
    }

    public Transaccion(Integer idCuenta, String tipoTransaccion, String descripcion, BigDecimal monto, Date fechaTransaccion) {
        this.idCuenta = idCuenta;
        this.tipoTransaccion = tipoTransaccion;
        this.descripcion = descripcion;
        this.monto=monto;
        this.fechaTransaccion=fechaTransaccion;
    }

    public Long getId() {
        return idTransaccion;
    }

    public void setId(Long id) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}
