/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.neoris.gestion_banca.services;

import co.org.neoris.gestion_banca.controllers.exceptions.IllegalOrphanException;
import co.org.neoris.gestion_banca.controllers.exceptions.NonexistentEntityException;
import co.org.neoris.gestion_banca.entities.Personas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DANIEL SOLANO
 * Metodo service logica de negocio de cuentas y movimientos
 */
@Service
public class CuentaMovimientosService {
    @Autowired


    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;


    @Transactional
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public Movimiento crearMovimiento(Movimiento movimiento) {
        Cuentas cuenta = new Cuentas();
        cuenta = this.getCuenta();
        if(movimiento.getDescripcion().equals("INGRESO")){
            Integer saldo = cuenta.getSaldo()+movimiento.getValor();
            cuenta.setSaldo(saldo);
        }else if(movimiento.getDescripcion().equals("TRANSFERENCIA")){
            Integer saldo = cuenta.getSaldo()-movimiento.getValor();
            cuenta.setSaldo(saldo);
        }
        cuentaRepository.update(cuenta);
        return movimientoRepository.save(movimiento);
    }
}