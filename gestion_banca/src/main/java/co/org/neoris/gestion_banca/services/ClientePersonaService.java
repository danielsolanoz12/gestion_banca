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
 * Metodo service logica de negocio Clientes
 */
@Service
public class CuentaMovimientosService {
    @Autowired


    private PersonasRepository personaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional
    public Personas crearPersona(Personas personas) {
        return personaRepository.save(persona);
    }

    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        Cuentas cuenta = new Cuentas();
        cuenta = this.getCuenta();
        try {
            if (!cliente.getPersona().isEmpty()) {
                Persona persona = this.crearPersona();
            }
            cliente.setPersona(persona);
            return clienteRepository.save(cliente);
        }catch (exception e){
            return e;
        }
    }
}