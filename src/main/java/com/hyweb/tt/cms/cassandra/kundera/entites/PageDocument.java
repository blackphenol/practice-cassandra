package com.hyweb.tt.cms.cassandra.kundera.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.impetus.kundera.index.Index;
import com.impetus.kundera.index.IndexCollection;

@Entity
@IndexCollection(columns = { @Index(name = "backendpid")}) //secondary indexes
@Table(name="pagedocument", schema="tt_keyspace@cassandra-pu")
public class PageDocument {

	/**
	 * primary key
	 */
	@Id
	@Column(name="pkey")
	private String pkey;
	@Column(name="backendpid")
	private String backendpid;
	@Column(name="pdocument")
	private String pdocument;
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getBackendpid() {
		return backendpid;
	}
	public void setBackendpid(String backendpid) {
		this.backendpid = backendpid;
	}
	public String getPdocument() {
		return pdocument;
	}
	public void setPdocument(String pdocument) {
		this.pdocument = pdocument;
	}
	
	@Override
	public String toString(){
		return "pkey=" + pkey + ", backendpid=" + backendpid + ", pdocument=" + pdocument;
	}
}
