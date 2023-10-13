/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.neoris.gestion_banca.controllers;

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
 */
public class PersonasJpaController implements Serializable {

    public PersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Metodo para crear una persona recibe un objeto de tipo persona
     * @param personas
     * **/
    public void create(Personas personas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personas.set
            autor.setAutorLibroList(attachedAutorLibroList);
            em.persist(autor);
            for (AutorLibro autorLibroListAutorLibro : autor.getAutorLibroList()) {
                Autor oldAutIdOfAutorLibroListAutorLibro = autorLibroListAutorLibro.getAutId();
                autorLibroListAutorLibro.setAutId(autor);
                autorLibroListAutorLibro = em.merge(autorLibroListAutorLibro);
                if (oldAutIdOfAutorLibroListAutorLibro != null) {
                    oldAutIdOfAutorLibroListAutorLibro.getAutorLibroList().remove(autorLibroListAutorLibro);
                    oldAutIdOfAutorLibroListAutorLibro = em.merge(oldAutIdOfAutorLibroListAutorLibro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Metodo para editar o actualizar un cliente o persona recibe el cuerpo de tipo personas con los cambios que se van a guardar
     * @param personas
     * **/
    public void edit(Personas personas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
       /*try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas persistentPersonas = em.find(Personas.class, personas.getPersonaId());
            List<AutorLibro> autorLibroListOld = persistentAutor.getAutorLibroList();
            List<AutorLibro> autorLibroListNew = autor.getAutorLibroList();
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
            autor.setAutorLibroList(autorLibroListNew);
            autor = em.merge(autor);
            for (AutorLibro autorLibroListNewAutorLibro : autorLibroListNew) {
                if (!autorLibroListOld.contains(autorLibroListNewAutorLibro)) {
                    Autor oldAutIdOfAutorLibroListNewAutorLibro = autorLibroListNewAutorLibro.getAutId();
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
        }*/
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

    public List<Personas> findAutorEntities() {
        return findAutorEntities(true, -1, -1);
    }

    public List<Personas> findAutorEntities(int maxResults, int firstResult) {
        return findAutorEntities(false, maxResults, firstResult);
    }

    private List<Personas> findAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personas.class));
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

    public Personas findAutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personas> rt = cq.from(Personas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
