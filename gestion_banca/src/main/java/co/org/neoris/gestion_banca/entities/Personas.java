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
 * Entidad Personas
 */
@Entity
@Table(name = "personas", catalog = "db_prueba", schema = "")
@NamedQueries({
    @NamedQuery(name = "personas.findAll", query = "SELECT p FROM personas p")
    , @NamedQuery(name = "personas.findByPersonaId", query = "SELECT p FROM Autor p WHERE p.personaId = :personaId")
    , @NamedQuery(name = "personas.findByPrimerNombre", query = "SELECT p FROM personas p WHERE p.primerNombre = :primerNombre")
    , @NamedQuery(name = "personas.findByPrimerApellido", query = "SELECT p FROM Autor p WHERE p.primerApellido = :primerApellido")
        , @NamedQuery(name = "personas.findByNumeroDocumento", query = "SELECT p FROM personas p WHERE p.numeroIde = :numeroIde")
        , @NamedQuery(name = "personas.findByFechaNacimiento", query = "SELECT p FROM Autor p WHERE p.fechaNacimiento = :fechaNacimiento")})
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personaId", nullable = false)
    @Expose
    private Integer personaId;
    @Basic(optional = false)
    @Column(name = "primerNombre", nullable = false, length = 30)
    @Expose
    private String primerNombre;
    @Column(name = "segundoNombre", nullable = false, length = 30)
    @Expose
    private String segundoNombre;
    @Column(name = "primerApellido", nullable = false, length = 30)
    @Expose
    private String primerApellido;
    @Column(name = "segundoApellido", nullable = false, length = 30)
    @Expose
    private String segundoApellido;
    @Basic(optional = false)
    @Column(name = "genero", nullable = false, length = 2)
    @Expose
    private String genero;
    @Column(name = "numeroIde", nullable = false, length = 30)
    @Expose
    private String numeroIde;
    @Basic(optional = false)
    @Column(name = "fechaNacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Expose
    private Date fechaNacimiento;

    public Personas() {
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "co.org.neoris.gestion_banca.entities.Personas[ personaId=" + personaId + " ]";
    }
    
}
