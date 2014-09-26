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
		
		PageDocument newPDoc2 = new PageDocument();
		newPDoc2.setPkey("A2");
		newPDoc2.setBackendpid("A2");
		newPDoc2.setPdocument("A2");
		service.insert(newPDoc2);

		PageDocument newPDoc = new PageDocument();
		newPDoc.setPkey("A1");
		newPDoc.setBackendpid("A1");
		newPDoc.setPdocument("A1");
		service.insert(newPDoc);
		
	}

	public void testServiceAPIs() {
		
		PageDocument pdoc = service.findPageDocumentByPKey("A1");
		assertNotNull(pdoc);
		
		PageDocument pdoc2 = service.findPageDocumentByBackendpid("A2");
		assertNotNull(pdoc2);
		
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
		
		service.delete(pdoc2);
//		service.deletePageDocument("A2");
		assertNull(service.findPageDocumentByPKey("A2"));
		
	}

	@Override
	protected void tearDown() throws Exception {

	}
	
	

}
