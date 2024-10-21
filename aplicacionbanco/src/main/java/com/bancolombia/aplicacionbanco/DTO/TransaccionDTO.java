package com.bancolombia.aplicacionbanco.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public class TransaccionDTO {
    @NotNull(message = "el ID de la cuenta es obligatorio!")
    private Long cuentaId;

    @NotNull(message = "el monto de la transacción es obligatorio!")
    @Positive(message = "El monto debe ser mayor que cero")
    private BigDecimal monto;

    @NotEmpty(message = "el tipo de transacción es obligatorio!")
    private String tipTransaccion;

    public TransaccionDTO(Long cuentaId, BigDecimal monto, String tipTransaccion) {
        this.cuentaId=cuentaId;
        this.monto = monto;
        this.tipTransaccion = tipTransaccion;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return tipTransaccion;
    }

    public void setDescripcion(String descripcion) {
        this.tipTransaccion = descripcion;
    }

    @Override
    public String toString() {
        return "TransaccionDTO{" +
                "cuentaId='" + cuentaId + '\'' +
                ", monto=" + monto +
                ", descripcion='" + tipTransaccion + '\'' +
                '}';
    }
}
