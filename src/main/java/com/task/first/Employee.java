package com.task.first;

import java.io.Serializable;
import java.math.BigDecimal;

public class Employee implements Serializable{	

	private static final long serialVersionUID = 2L;
	
	private Integer idemployee;
	private String name;
	private String title;
	private String speciality;
	private BigDecimal telephone;
	private String hospital;
	
	public Employee(){
		
	}
	
	public Employee ( Integer id, String name, String title,String speciality, BigDecimal telephone, String hospital){
		this.idemployee = id;
		this.name = name;
		this.title = title;
		this.speciality= speciality;
		this.telephone = telephone;
		this.hospital = hospital;
	}
	
	public Integer getIdemployee() {
		return idemployee;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public BigDecimal getTelephone() {
		return telephone;
	}
	
	public String getHospital() {
		return hospital;
	}

	public String getSpeciality() {
		return speciality;
	}
	
}
