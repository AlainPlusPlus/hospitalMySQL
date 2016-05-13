package com.task.first;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@ManagedBean (name="hospitals")
public class Hospitals implements Serializable{
	
	private static final long serialVersionUID = -5481487386828298883L;
	private InitialContext ctx = null;
	private DataSource ds = null;
	private static Connection con = null;
	
    private String text;
	private boolean checked;
    
	private String name;
	private String address;
	private BigDecimal telephone;
	private String info;
	
	private List<Hospital> hospitals=null;
    
@PostConstruct
    public void init() {
    	try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/db");
			con = ds.getConnection();
		} catch (Exception e) {
	        System.out.println(e.getMessage());
		}
    	
        System.out.println("Init DB-hospitals");
		if (hospitals == null)
			hospitals = getHospitalsFromDB();
    }
    
	public static ArrayList<Hospital> getHospitalsFromDB() {
	    try {
	        PreparedStatement ps = con.prepareStatement("select * from hospital");
	        ArrayList<Hospital> al = new ArrayList<Hospital>();
	        ResultSet rs = ps.executeQuery();
	        boolean found = false;
	        while (rs.next()) {
	            Hospital h = new Hospital(rs.getInt("idhospitals"),rs.getString("name"),rs.getString("address"),rs.getBigDecimal("telephone"),rs.getString("info"));
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
	        System.out.println("Error In getHospitalsFromDB() -->" + e.getMessage());
	        return (null);
	    }
	}
	
	public void addHospitaltoDB(){
        System.out.println("Add hospital to DB:");
        int result=0;
        if(!(name.isEmpty()||address.isEmpty()))
        try {
        	String statement= "INSERT INTO hospital "+"VALUES (0, "+"'"+name+"', '"+address+"', "+telephone.toString()+", '"+info+"')";
        	Statement ps = con.createStatement();
	        result= ps.executeUpdate(statement);
	        con.close(); 
	    } catch (Exception e) {
	        System.out.println("Error In addHospitaltoDB() -->" + e.getMessage());
	    }
        if (result==0)
        	text="*please, fill correctly all the required fields";
        else{
        	text=name+" added";
        	name="";
        	address="";
        	telephone=BigDecimal.ZERO;
        	info="";
        }
	}

	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getTelephone() {
		return telephone;
	}

	public void setTelephone(BigDecimal telephone) {
		this.telephone = telephone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    
}