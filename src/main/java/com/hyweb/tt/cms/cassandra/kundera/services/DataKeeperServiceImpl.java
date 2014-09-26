package com.hyweb.tt.cms.cassandra.kundera.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hyweb.tt.cms.cassandra.kundera.dao.DataKeeperDao;
import com.hyweb.tt.cms.cassandra.kundera.entites.PageDocument;
/**
 * implement DataKeeper Service
 */
@Service
@Scope("singleton")
public class DataKeeperServiceImpl implements DataKeeperService
{
    /**
     * logger used for logging statement.
     */
    
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * inject dao activities
	 */
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




	public PageDocument findPageDocumentByPKey(String pkey) {
		
		return dao.findById(PageDocument.class, pkey);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageDocument findPageDocumentByBackendpid(String backendpid) {
		String jpql = "select n from PageDocument n where n.backendpid=:backendpid";
		List<PageDocument> result = (List<PageDocument>) dao.findByQuery(jpql, "backendpid", backendpid);
		if(result!=null && result.size()==1){
			return result.get(0);
		}
		
		return null; 
	}
	
	@SuppressWarnings("unchecked")
	public List<PageDocument> findPageDocument(int start, int size) {
		String jpql = "select n from PageDocument n";
		return (List<PageDocument>) dao.findByQuery(jpql, start, size);
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



	/**
	 * @deprecated use {@link #delete(Object)} to delete entity
	 * 先不要用，用此刪除後，如果再新增一個新的 entity, pkey 跟刪除的一樣，會造成無法新增的情況 
	 */
	@Override
	public void deletePageDocument(String pKey) {
		String cql = "delete from pagedocument where pkey='"+pKey+"'";
		dao.executeCQL(cql);
		
	}


}
