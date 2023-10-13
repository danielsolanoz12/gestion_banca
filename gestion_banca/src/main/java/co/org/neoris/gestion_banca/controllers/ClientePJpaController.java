/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.neoris.gestion_banca.controllers;

import co.org.neoris.gestion_banca.controllers.exceptions.IllegalOrphanException;
import co.org.neoris.gestion_banca.controllers.exceptions.NonexistentEntityException;
import co.org.neoris.gestion_banca.entities.Personas;
import co.org.neoris.gestion_banca.entities.Cliente;
import co.org.neoris.gestion_banca.controllers.*;
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
 * @author USER
 */
public class ClientePJpaController implements Serializable {

    public ClientePJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private PersonasJpaController personasJpaController;

    /**
     * Metodo para crear un autor recibe un objeto de tipo Autor
     * @param autor
     * **/
    public void create(Cliente cliente) {
        if (cliente.getPersonas== null) {
            cliente.setPersonas() = new Personas();
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas personas = new Personas ();
            personasJpaController.create(cliente.getPersonas());
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Metodo para editar o actualizar un autor recibe el cuerpo de tipo Autor con los cambios que se van a guardar
     * @param autor
     * **/
    public void edit(Cliente clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentAutor = em.find(Autor.class, autor.getAutId());

            List<String> illegalOrphanMessages = null;
            for (AutorLibro autorLibroListOldAutorLibro : autorLibroListOld) {
                if (!autorLibroListNew.contains(autorLibroListOldAutorLibro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("Debe conservar AutorLibro " +
                            " " + autorLibroListOldAutorLibro + " ya que su campo autId no acepta valores NULL");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AutorLibro> attachedAutorLibroListNew = new ArrayList<AutorLibro>();
            for (AutorLibro autorLibroListNewAutorLibroToAttach : autorLibroListNew) {
                autorLibroListNewAutorLibroToAttach = em.getReference(autorLibroListNewAutorLibroToAttach.getClass(), autorLibroListNewAutorLibroToAttach.getAlId());
                attachedAutorLibroListNew.add(autorLibroListNewAutorLibroToAttach);
            }
            autorLibroListNew = attachedAutorLibroListNew;
            clientes.setAutorLibroList(autorLibroListNew);
            clientes = em.merge(autor);
            for (AutorLibro autorLibroListNewAutorLibro : autorLibroListNew) {
                if (!autorLibroListOld.contains(autorLibroListNewAutorLibro)) {
                    Clientes oldAutIdOfAutorLibroListNewAutorLibro = autorLibroListNewAutorLibro.getAutId();
                    autorLibroListNewAutorLibro.setAutId(autor);
                    autorLibroListNewAutorLibro = em.merge(autorLibroListNewAutorLibro);
                    if (oldAutIdOfAutorLibroListNewAutorLibro != null && !oldAutIdOfAutorLibroListNewAutorLibro.equals(autor)) {
                        oldAutIdOfAutorLibroListNewAutorLibro.getAutorLibroList().remove(autorLibroListNewAutorLibro);
                        oldAutIdOfAutorLibroListNewAutorLibro = em.merge(oldAutIdOfAutorLibroListNewAutorLibro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autor.getAutId();
                if (findAutor(id) == null) {
                    throw new NonexistentEntityException("El autor con id " + id + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Metodo para hacer la eliminación de un autor por su id
     * @param id
     * **/
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor autor;
            try {
                autor = em.getReference(Autor.class, id);
                autor.getAutId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El autor con id " + id + " ya no existe.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AutorLibro> autorLibroListOrphanCheck = autor.getAutorLibroList();
            for (AutorLibro autorLibroListOrphanCheckAutorLibro : autorLibroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("Este Autor (" + autor + ") no se puede destruir desde el AutorLibro " + autorLibroListOrphanCheckAutorLibro + " en su campo autorLibroList tiene un campo autId que no acepta valores NULL.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(autor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findAutorEntities() {
        return findAutorEntities(true, -1, -1);
    }

    public List<Cliente> findAutorEntities(int maxResults, int firstResult) {
        return findAutorEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autor.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findAutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autor> rt = cq.from(Autor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
