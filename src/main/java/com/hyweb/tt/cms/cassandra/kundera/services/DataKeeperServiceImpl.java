package com.hyweb.tt.cms.cassandra.kundera.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hyweb.tt.cms.cassandra.kundera.dao.DataKeeperDao;
import com.hyweb.tt.cms.cassandra.kundera.entites.PageDocument;
/**
 * @author Kuldeep.Mishra
 * 
 */
@Service
@Scope("singleton")
public class DataKeeperServiceImpl implements DataKeeperService
{
    /**
     * logger used for logging statement.
     */
    
	private final Logger logger = Logger.getLogger(this.getClass());
	
    @Autowired
    private DataKeeperDao dao;

    public DataKeeperServiceImpl()
    {
    }
    
    


	public DataKeeperDao getDao() {
		return dao;
	}




	public void setDao(DataKeeperDao dao) {
		this.dao = dao;
	}




	public PageDocument findPageDocument(String pkey) {
		
		return dao.findById(PageDocument.class, pkey);
	}




	public List<PageDocument> findPageDocument(int start, int size) {
		return (List<PageDocument>) dao.findByQuery("select * from PageDocument", start, size);
	}



	@Override
	public void insert(Object entity) {
		dao.insert(entity);		
	}




	@Override
	public void update(Object entity) {
		dao.merge(entity);
	}




	@Override
	public void delete(Object entity) {
		dao.remove(entity);
	}




	@Override
	public void deletePageDocument(String pKey) {
		EntityManager em = dao.getEntityManager();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaDelete<PageDocument> delete = cb.createCriteriaDelete(PageDocument.class);
//		  
//		  // set the root class
//		  Root e = delete.from(PageDocument.class);
//		  
//		  // set where clause
//		  delete.where(cb.equal(e.get("key"), pKey));
		  
		  // perform update
		 String delete = "delete from PageDocument where pKey='"+pKey+"'";
		  em.createQuery(delete).executeUpdate(); 
		
	}
}
