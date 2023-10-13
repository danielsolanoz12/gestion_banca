/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.neoris.gestion_banca.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;


/**
 *
 * @author DANIEL SOLANO
 * Entidad Cuenta
 */
@Entity
@Table(name = "cuenta", catalog = "db_prueba", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"numeroCuenta"})})

@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByCatId", query = "SELECT c FROM Categoria c WHERE c.catId = :catId")
    , @NamedQuery(name = "Categoria.findByCatNombre", query = "SELECT c FROM Categoria c WHERE c.catNombre = :catNombre")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuentaId", nullable = false)
    @Expose
    private Integer cuentaId;

    @Basic(optional = false)
    @Column(name = "numeroCuenta", nullable = false, length = 20)
    @Expose
    private String numeroCuenta;

    @Basic(optional = false)
    @Column(name = "tipoCuenta", nullable = false, length = 20)
    @Expose
    private String tipoCuenta;

    @Basic(optional = false)
    @Column(name = "saldo", nullable = false, length = 20)
    @Expose
    private Integer saldo;

    @JoinColumn(name = "clienteId", referencedColumnName = "clienteId", nullable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Cuentas() {
    }

    public Cuentas(Integer cuentaId, String numeroCuenta, String tipoCuenta, Integer saldo, Cliente cliente) {
        this.cuentaId = cuentaId;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catId != null ? catId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.catId == null && other.catId != null) || (this.catId != null && !this.catId.equals(other.catId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.org.neoris.gestion_banca.entities.Cuentas[ catId=" + catId + " ]";
    }
    
}
