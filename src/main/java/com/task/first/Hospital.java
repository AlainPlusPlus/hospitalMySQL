package com.task.first;

import java.math.BigDecimal;
import java.io.Serializable;

public class Hospital implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer idhospitals;
	private String name;
	private String address;
	private BigDecimal telephone;
	private String info;
	
	public Hospital (){
	}
	
	public Hospital (Integer id, String name, String address, BigDecimal telephone, String info){
		this.idhospitals =id;
		this.name=name;
		this.address=address;
		this.telephone=telephone;
		this.info=info;
	}
		
	public Integer getIdhospitals() {
		return idhospitals;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public BigDecimal getTelephone() {
		return telephone;
	}
	
	public String getInfo() {
		return info;
	}
	
}
