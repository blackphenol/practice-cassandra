package com.hyweb.tt.cms.cassandra.kundera.dao;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * interface of dao activities
 */
public interface DataKeeperDao
{
    EntityManager getEntityManager();

    void closeEntityManager();

    void clearEntityManager();

    void shutDown();

    /**
     * save entity
     * @param entity
     */
    void insert(Object entity);

    /**
     * update entity
     * @param entity
     */
    void merge(Object entity);

    /**
     * delete entity
     * @param entity
     */
    void remove(Object entity);

    /**
     * find the entity by id
     * @param entityClazz
     * @param id
     * @return
     */
    <T> T findById(Class<T> entityClazz, Object id);

    /**
     * find entities by jpql
     * @param Query
     * @return
     */
    List<?> findByQuery(String Query);
    
    /**
     * find entities by jpql, and assign the start position and limit the result size
     * @param Query
     * @param start
     * @param size
     * @return
     */
    List<?> findByQuery(String Query, int start, int size);

    /**
     * find entities by jpql, and assign the parameter name and value
     * @param queryString
     * @param paramater
     * @param parameterValue
     * @return
     */
    List<?> findByQuery(String queryString, String paramater, Object parameterValue);
    
    /**
     * find entities by cql
     * @param cql
     * @return
     */
    List<?>findByCQL(String cql);
    
    /**
     * execute update cql or delete cql
     * @param cql
     * @return
     */
    int executeCQL(String cql);
}
