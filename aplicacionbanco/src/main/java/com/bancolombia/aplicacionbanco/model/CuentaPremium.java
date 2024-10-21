package com.bancolombia.aplicacionbanco.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@DiscriminatorValue("PREMIUM")
public class CuentaPremium extends Cuenta{

    private static BigDecimal saldoCuenta ;

    private static BigDecimal depositoOtraCuenta = new BigDecimal(1.5);
    private static BigDecimal compraPaginaWeb = new BigDecimal(5);
    private static BigDecimal sinCosto = new BigDecimal(0);

    public CuentaPremium(BigDecimal saldo, BigInteger numCuenta, String titular){
        super(saldo,numCuenta,titular);
    }

    public CuentaPremium(){}

    @Override
    public String tipoCuenta() {
        return "Cuenta Premium";
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
}
