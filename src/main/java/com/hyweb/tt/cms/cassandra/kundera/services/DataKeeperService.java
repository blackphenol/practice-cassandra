package com.hyweb.tt.cms.cassandra.kundera.services;

import java.util.List;

import com.hyweb.tt.cms.cassandra.kundera.entites.PageDocument;

/**
 * @author Kuldeep.Mishra
 * 
 */
public interface DataKeeperService
{

    PageDocument findPageDocument(String pkey);
    
    List<PageDocument> findPageDocument(int start, int size);
    
    void insert(Object entity);
    
    void update(Object entity);
    
    void delete(Object entity);
    
    void deletePageDocument(String pKey);
}
