package com.task.first;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@ManagedBean (name="staff")
public class Staff implements Serializable{
	private static final long serialVersionUID = 2506993012397290519L;
	private InitialContext ctx = null;
	private DataSource ds = null;
	private static Connection con = null;
	
    private String text;
    private boolean checked;
    
	private String name;
	private String title;
	private String speciality;
	private BigDecimal telephone;
	private String hospital;
	
	private static HashMap<String, Integer> hospitalsName;
	private List<Employee> employees=null;
	
@PostConstruct
    public void init() {
    	try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/db");
			con = ds.getConnection();
		} catch (Exception e) {
	        System.out.println(e.getMessage());
		}
    	
        System.out.println("Init DB-Employees");
		if (employees == null)
			employees = getEmployeesFromDB();
    }
    
	public static ArrayList<Employee> getEmployeesFromDB() {
		HashMap<Integer, String> hospitalsId = getHospitalsHash();
		
	    try {
	        PreparedStatement ps = con.prepareStatement("select * from employee");
	        ArrayList<Employee> al = new ArrayList<Employee>();
	        ResultSet rs = ps.executeQuery();
	        boolean found = false;
	        while (rs.next()) {
	        	Employee h = new Employee(rs.getInt("idemployee"),rs.getString("name"),rs.getString("title"),rs.getString("speciality"),rs.getBigDecimal("telephone"),hospitalsId.get(rs.getInt("hospital_id")));
	            al.add(h);
	            found = true;
	        }
	        rs.close();
	        if (found) {
	            return al;
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("Error In getEmployeesFromDB() -->" + e.getMessage());
	        return (null);
	    }
	    
	}
	
	public static HashMap<Integer, String> getHospitalsHash(){
		
		HashMap<Integer, String>hospitalsID=new HashMap<Integer, String>();
		hospitalsName=new HashMap<String, Integer>();

		try{
			PreparedStatement ps = con.prepareStatement("select * from hospital");
	        ResultSet rs = ps.executeQuery();
	        boolean found = false;
	        while (rs.next()) {
	        	hospitalsID.put(rs.getInt("idhospitals"),rs.getString("name"));
	        	hospitalsName.put(rs.getString("name"), rs.getInt("idhospitals"));
	            found = true;
	        }
	        rs.close();
	        if (found) {
	        	return hospitalsID;
	        } else {
	            return null;
        }
		} catch (Exception e) {
	        System.out.println("Error In getHospitalsFromDB() in staff -->" + e.getMessage());
	        return (null);
	    }
		
	}
	
	public void addEmployeetoDB(){
        System.out.println("Add employee to DB:");
        int result=0;
        if(!name.isEmpty())
        try {
        	String statement= "INSERT INTO employee "+"VALUES (0, "+"'"+hospital+"', '"+name+"', "+telephone.toString()+", '"+title+"', '"+speciality+"')";
        	Statement ps = con.createStatement();
	        result= ps.executeUpdate(statement);
	        con.close(); 
	    } catch (Exception e) {
	        System.out.println("Error In addEmployeetoDB() -->" + e.getMessage());
	    }
        if (result==0)
        	text="*please, fill correctly all the required fields";
        else{
        	text=name+" added";
        	name="";
        	telephone=BigDecimal.ZERO;        	
        }
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public BigDecimal getTelephone() {
		return telephone;
	}

	public void setTelephone(BigDecimal telephone) {
		this.telephone = telephone;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	public String getHospital(){
		return hospital;
	}

	public HashMap<String, Integer> getHospitalsName() {
		return hospitalsName;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
