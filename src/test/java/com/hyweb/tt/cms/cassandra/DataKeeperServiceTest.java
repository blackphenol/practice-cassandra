package com.hyweb.tt.cms.cassandra;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hyweb.tt.cms.cassandra.kundera.entites.PageDocument;
import com.hyweb.tt.cms.cassandra.kundera.services.DataKeeperService;
import com.hyweb.tt.cms.cassandra.kundera.services.DataKeeperServiceImpl;

public class DataKeeperServiceTest extends TestCase {

	static ApplicationContext context =new ClassPathXmlApplicationContext("cassandraContext.xml");
	DataKeeperService service;
	
	protected void setUp() throws Exception {
		service = (DataKeeperService) context.getBean(DataKeeperServiceImpl.class);
	}

	public void testFindPageDocumentString() {
		PageDocument pdoc = service.findPageDocument("p1");
		assertEquals("pd1", pdoc.getPdocument());
	}

	public void testFindPageDocumentIntInt() {
		List<PageDocument> pdocs = service.findPageDocument(1, 2);
		assertEquals(2, pdocs.size());
	}

	public void testInsert() {
		PageDocument newPDoc = new PageDocument();
		newPDoc.setPkey("A");
		newPDoc.setBackendpid("A");
		newPDoc.setPdocument("A");
		service.insert(newPDoc);
		
		PageDocument pdoc = service.findPageDocument("A");
		assertNotNull(pdoc);
	}

	public void testUpdate() {
		String bpid = "test update";
		PageDocument pdoc = service.findPageDocument("p1");
		pdoc.setBackendpid(bpid);
		
		service.update(pdoc);
		pdoc = service.findPageDocument("p1");
		assertEquals(bpid, pdoc.getBackendpid());
	}

	public void testDelete() {
		PageDocument newPDoc = new PageDocument();
		newPDoc.setPkey("delete");
		newPDoc.setBackendpid("delete");
		newPDoc.setPdocument("delete");
		service.insert(newPDoc);
		
		service.delete(newPDoc);
		
		assertNull(service.findPageDocument("delete")); 
	}

	public void testDeletePageDocument() {
		PageDocument newPDoc = new PageDocument();
		newPDoc.setPkey("delete");
		newPDoc.setBackendpid("delete");
		newPDoc.setPdocument("delete");
		service.insert(newPDoc);
		
		service.deletePageDocument("delete");
		
		assertNull(service.findPageDocument("delete"));
	}

	@Override
	protected void tearDown() throws Exception {
//		PageDocument pdoc = service.findPageDocument("A");
//		pdoc.setBackendpid("b1");
//		service.update(pdoc);
		
		service.deletePageDocument("A");
	}
	
	

}
