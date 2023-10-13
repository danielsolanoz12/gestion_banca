/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.neoris.gestion_banca.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author DANIEL SOLANO
 * Entidad Cliente
 */
@Entity
@Table(name = "cliente", catalog = "db_prueba", schema = "")
@NamedQueries({
        @NamedQuery(name = "cliente.findAll", query = "SELECT c FROM cliente c")
        , @NamedQuery(name = "cliente.findByClienteId", query = "SELECT c FROM cliente c WHERE c.clienteId = :clienteId")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clienteId", nullable = false)
    private Integer clienteId;
    @JoinColumn(name = "personaId", referencedColumnName = "personaId", nullable = false)
    @ManyToOne(optional = false)
    private Personas personas;

    public Cliente() {
    }

    public Cliente(Integer clienteId, Personas personas) {
        this.clienteId = clienteId;
        this.personas = personas;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteId != null ? clienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clienteId == null && other.clienteId != null) || (this.clienteId != null && !this.clienteId.equals(other.clienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.org.neoris.gestion_banca.entities.Cliente[ clienteId=" + clienteId + " ]";
    }
    
}
