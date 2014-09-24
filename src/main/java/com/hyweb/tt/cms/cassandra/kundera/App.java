package com.hyweb.tt.cms.cassandra.kundera;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hyweb.tt.cms.cassandra.kundera.services.DataKeeperService;
import com.hyweb.tt.cms.cassandra.kundera.services.DataKeeperServiceImpl;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cassandraContext.xml");
		DataKeeperService service = (DataKeeperService) context.getBean(DataKeeperServiceImpl.class);
//		System.out.println(service.findPageDocument("p1"));
		service.deletePageDocument("p2");
	}
}
