package com.bancolombia.aplicacionbanco.service;

import com.bancolombia.aplicacionbanco.DTO.CuentaDTO;
import com.bancolombia.aplicacionbanco.DTO.TransaccionDTO;
import com.bancolombia.aplicacionbanco.model.Cuenta;
import com.bancolombia.aplicacionbanco.repository.CuentaRepository;
import com.bancolombia.aplicacionbanco.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;

    public CuentaService(CuentaRepository cuentaRepository,
                         TransaccionRepository transaccionRepository){
        this.cuentaRepository=cuentaRepository;
        this.transaccionRepository=transaccionRepository;
    }

    public BigDecimal obtenerSaldo(CuentaDTO cuentaDTO){
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(cuentaDTO.getId());
        if (cuentaEncontrada.isEmpty()){
            throw new NoSuchElementException("La cuenta con ID "+cuentaDTO.getId()+" no fue encontrada. :C");
        }
        return cuentaEncontrada.get().obtenerSaldo();
    }


    public BigDecimal depositar(TransaccionDTO transaccionDTO) {
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(transaccionDTO.getCuentaId());
        if (cuentaEncontrada.isEmpty()) {
            throw new NoSuchElementException("La cuenta con ID " + transaccionDTO.getCuentaId() + " no fue encontrada. :C");
        } else {
            if (transaccionDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor que cero");
            } else {
                BigDecimal nuevoSaldo = transaccionDTO.getMonto().add(cuentaEncontrada.get().obtenerSaldo());
                cuentaEncontrada.get().cambiarSaldo(nuevoSaldo);
                cuentaRepository.save(cuentaEncontrada.get());
                return cuentaEncontrada.get().obtenerSaldo();
            }
        }
    }

    public BigDecimal retirar(TransaccionDTO transaccionDTO) {
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(transaccionDTO.getCuentaId());
        if (cuentaEncontrada.isEmpty()) {
            throw new NoSuchElementException("La cuenta con ID " + transaccionDTO.getCuentaId() + " no fue encontrada. :C");
        } else {
            if (transaccionDTO.getMonto().compareTo(cuentaEncontrada.get().obtenerSaldo()) >= 0) {
                throw new IllegalStateException("Saldo insuficiente para este retiro");
            } else {
                BigDecimal nuevoSaldo =cuentaEncontrada.get().obtenerSaldo().subtract(transaccionDTO.getMonto());
                cuentaEncontrada.get().cambiarSaldo(nuevoSaldo);
                cuentaRepository.save(cuentaEncontrada.get());
                return cuentaEncontrada.get().obtenerSaldo();
            }
        }
    }

    public BigInteger consultarCuenta(CuentaDTO cuentaDTO){
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(cuentaDTO.getId());
        if (cuentaEncontrada.isEmpty()) {
            throw new NoSuchElementException("La cuenta con ID " + cuentaDTO.getId() + " no fue encontrada. :C");
        } else {
            return cuentaEncontrada.get().obtenerNumCuenta();
        }
    }

    public String consultarTitular(CuentaDTO cuentaDTO){
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(cuentaDTO.getId());
        if (cuentaEncontrada.isEmpty()) {
            throw new NoSuchElementException("La cuenta con ID " + cuentaDTO.getId() + " no fue encontrada. :C");
        } else {
            return ""+cuentaEncontrada.get().obtenerTitular();
        }
    }

    public String consultartipoCuenta(CuentaDTO cuentaDTO){
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(cuentaDTO.getId());
        if (cuentaEncontrada.isEmpty()) {
            throw new NoSuchElementException("La cuenta con ID " + cuentaDTO.getId() + " no fue encontrada. :C");
        } else {
            return ""+cuentaEncontrada.get().tipoCuenta();
        }
    }
}
