package com.bancolombia.aplicacionbanco.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@DiscriminatorValue("BASICA")
public class CuentaBasica extends Cuenta{

    private static BigDecimal saldoCuenta ;

    private static BigDecimal depositoCajeroAuto = new BigDecimal(2);
    private static BigDecimal depositoOtraCuenta = new BigDecimal(1.5);
    private static BigDecimal compraPaginaWeb = new BigDecimal(5);
    private static BigDecimal retiroEnCajero = new BigDecimal(1);
    private static BigDecimal sinCosto = new BigDecimal(0);

    public CuentaBasica(BigDecimal saldo, BigInteger numCuenta, String titular){
        super(saldo,numCuenta,titular);
    }

    public CuentaBasica(){}

    @Override
    public String tipoCuenta() {
        return "Cuenta Basica";
    }

    public void depositoCajeroAuto(BigDecimal monto){
        saldoCuenta = this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.subtract(this.depositoCajeroAuto));
        saldoCuenta = this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.add(monto));
    }

    public void depositoOtraCuenta(BigDecimal monto){
        saldoCuenta = this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.subtract(this.depositoOtraCuenta));
        saldoCuenta = this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.add(monto));
    }

    public void comprarDesdePagWeb(BigDecimal monto){
        saldoCuenta=this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.subtract(this.compraPaginaWeb));
        saldoCuenta = this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.subtract(monto));
    }

    public void retiroCajero(BigDecimal monto){
        saldoCuenta=this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.subtract(this.retiroEnCajero));
        saldoCuenta = this.obtenerSaldo();
        this.cambiarSaldo(saldoCuenta.subtract(monto));
    }
}
