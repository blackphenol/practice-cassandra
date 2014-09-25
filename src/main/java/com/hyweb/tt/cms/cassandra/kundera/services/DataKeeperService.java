package com.hyweb.tt.cms.cassandra.kundera.services;

import java.util.List;

import com.hyweb.tt.cms.cassandra.kundera.entites.PageDocument;

/**
 * dao service
 */
public interface DataKeeperService
{

	/**
	 * find PageDocument by pkey
	 * @param pkey
	 * @return
	 */
    PageDocument findPageDocumentByPKey(String pkey);

    /**
     * find PageDocument by backendpid
     * @param backendpid
     * @return
     */
    PageDocument findPageDocumentByBackendpid(String backendpid);

    /**
     * find PageDocument and assign the start position and limit the result size
     * @param start
     * @param size
     * @return
     */
    List<PageDocument> findPageDocument(int start, int size);
    
    /**
     * save a entity
     * @param entity
     */
    void insert(Object entity);
    
    /**
     * update a entity
     * @param entity
     */
    void update(Object entity);
    
    /**
     * delete a entity
     * @param entity
     */
    void delete(Object entity);
    
    /**
     * delete a entity by pkey
     * @param pkey
     */
    void deletePageDocument(String pkey);
}
