package com.bancolombia.aplicacionbanco.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_cuenta")
public abstract class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;
    private BigInteger numCuenta;
    private String titular;

    @OneToMany(mappedBy = "idCuenta")
    private List<Transaccion> transaccion;

    public Cuenta() {
    }

    public Cuenta(BigDecimal saldo, BigInteger numCuenta, String titular) {
        this.saldo = saldo;
        this.numCuenta = numCuenta;
        this.titular = titular;
    }

    public List<Transaccion> getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(List<Transaccion> transaccion) {
        this.transaccion = transaccion;
    }

    public abstract String tipoCuenta();

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public void cambiarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Long obtenerId() {
        return id;
    }

    public void cambiarId(Long id) {
        this.id = id;
    }

    public BigInteger obtenerNumCuenta() {
        return numCuenta;
    }

    public void cambiarNumCuenta(BigInteger numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String obtenerTitular() {
        return titular;
    }

    public void cambiarTitular(String titular) {
        this.titular = titular;
    }
}
