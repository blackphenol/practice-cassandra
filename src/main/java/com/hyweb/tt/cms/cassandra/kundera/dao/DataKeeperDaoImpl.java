package com.hyweb.tt.cms.cassandra.kundera.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Kuldeep.Mishra
 * 
 */
@Component
@Scope("singleton")
// @Transactional(propagation = Propagation.REQUIRED)
public class DataKeeperDaoImpl implements DataKeeperDao
{
    /**
     * logger used for logging statement.
     */
    private static final Logger log = LoggerFactory.getLogger(DataKeeperDaoImpl.class);

    @PersistenceContext(unitName = "cassandra-pu", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public DataKeeperDaoImpl()
    {
    }

    public void insert(Object entity)
    {
        em.persist(entity);
        em.clear();
    }

    public void merge(Object entity)
    {
        em.merge(entity);
        em.clear();
    }

    public void remove(Object entity)
    {
        em.remove(entity);
        em.clear();
    }

    public <T> T findById(Class<T> entityClazz, Object id)
    {
        T results = em.find(entityClazz, id);
        return results;
    }

    public List<?> findByQuery(String queryString)
    {
        log.info(queryString);
        Query query = em.createQuery(queryString);
        List<?> resultList = query.getResultList();
        return resultList;
    }
    
    public List<?> findByQuery(String queryString, int start, int size)
    {
        log.info(queryString);
        Query query = em.createQuery(queryString);
        query.setFirstResult(start)
        	 .setMaxResults(size);
        List<?> resultList = query.getResultList();
        return resultList;
    }

    public List<?> findByQuery(String queryString, String paramater, Object parameterValue)
    {
        Query query = em.createQuery(queryString);
        query.setParameter(paramater, parameterValue);
        log.info(queryString);
        List<?> resultList = query.getResultList();
        return resultList;
    }

    public EntityManager getEntityManager()
    {
        return em;
    }

    public void closeEntityManager()
    {
        if (em != null)
        {
            em.close();
        }
    }

    public void clearEntityManager()
    {
        if (em != null)
        {
            em.clear();
        }
    }

    public void shutDown()
    {
        if (em != null)
        {
            em.close();
        }
    }

   
}
