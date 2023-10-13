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
 * Entity Movimientos
 * Esta entidad
 */
@Entity
@Table(name = "movimientos", catalog = "db_prueba", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"descripcion"})})

@NamedQueries({
    @NamedQuery(name = "movimientos.findAll", query = "SELECT m FROM movimientos m")
    , @NamedQuery(name = "movimientos.findByMovimientoId", query = "SELECT m FROM movimientos m WHERE m.movimientoId = :movimientoId")
        , @NamedQuery(name = "movimientos.findByCuenta", query = "SELECT m FROM movimientos m WHERE m.cuentas = :m.cuentas")
        , @NamedQuery(name = "movimientos.findByDescripcion", query = "SELECT m FROM movimientos m WHERE m.descripcion = :descripcion")})
public class Movimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movimientoId", nullable = false)
    @Expose
    private Integer movimientoId;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 20)
    @Expose
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "valorMovimiento", nullable = false, length = 20)
    @Expose
    private Integer valorMovimiento;
    @Basic(optional = false)
    @Column(name = "fechaMovimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Expose
    private Date fechaMovimiento;
    @JoinColumn(name = "cuentaId", referencedColumnName = "cuentaId", nullable = false)
    @ManyToOne(optional = false)
    private Cuentas cuentas;

    public Movimientos() {
    }

    public Movimientos(Integer movimientoId, String descripcion, Integer valorMovimiento, Date fechaMovimiento, Cuentas cuentas) {
        this.movimientoId = movimientoId;
        this.descripcion = descripcion;
        this.valorMovimiento = valorMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.cuentas = cuentas;
    }

    public Integer getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(Integer movimientoId) {
        this.movimientoId = movimientoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(Integer valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Cuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuentas cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientoId != null ? movimientoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.movimientoId == null && other.movimientoId != null) || (this.movimientoId != null && !this.movimientoId.equals(other.movimientoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.org.neoris.gestion_banca.entities.Movimientos[ movimientoId=" + movimientoId + " ]";
    }
    
}
