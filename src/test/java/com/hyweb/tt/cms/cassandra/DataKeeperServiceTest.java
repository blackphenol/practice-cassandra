package com.hyweb.tt.cms.cassandra;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hyweb.tt.cms.cassandra.kundera.entites.PageDocument;
import com.hyweb.tt.cms.cassandra.kundera.services.DataKeeperService;
import com.hyweb.tt.cms.cassandra.kundera.services.DataKeeperServiceImpl;

/**
 * test case of DataKeeperService
 */
public class DataKeeperServiceTest extends TestCase {

	static ApplicationContext context =new ClassPathXmlApplicationContext("cassandraContext.xml");
	DataKeeperService service;
	
	protected void setUp() throws Exception {
		service = (DataKeeperService) context.getBean(DataKeeperServiceImpl.class);
	}

	public void testServiceAPIs() {
		
		PageDocument newPDoc = new PageDocument();
		newPDoc.setPkey("A");
		newPDoc.setBackendpid("A");
		newPDoc.setPdocument("A");
		service.insert(newPDoc);
		
		newPDoc = new PageDocument();
		newPDoc.setPkey("A1");
		newPDoc.setBackendpid("A1");
		newPDoc.setPdocument("A1");
		service.insert(newPDoc);
		
		PageDocument pdoc = service.findPageDocumentByPKey("A");
		assertNotNull(pdoc);
		
		pdoc = service.findPageDocumentByBackendpid("A1");
		assertNotNull(pdoc);
		
		int start = 0;
		int size = 2;
		List<PageDocument> pdocs = service.findPageDocument(start, size);
		assertEquals(size, pdocs.size());
		
		
		pdoc.setPdocument("update A1");
		service.update(pdoc);
		pdoc = service.findPageDocumentByPKey("A1");
		assertEquals("update A1", pdoc.getPdocument());
		
		service.delete(pdoc);
		assertNull(service.findPageDocumentByPKey("A1"));
		
		PageDocument newPDoc2 = new PageDocument();
		newPDoc2.setPkey("B");
		newPDoc2.setBackendpid("B");
		newPDoc2.setPdocument("B");
		service.insert(newPDoc2);
		service.deletePageDocument("B");
		assertNull(service.findPageDocumentByPKey("B"));

	}

	@Override
	protected void tearDown() throws Exception {

	}
	
	

}
