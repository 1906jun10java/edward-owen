package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.util.ConnFactory;

public class DAOImp {
	public static ConnFactory cF = ConnFactory.getInstance();
	
	public void addEmployee(String fName, String lName, int dpt, long salary, String email) throws SQLException {
		Connection conn = cF.getConnection();
		//This is how we call a stored procedure in java
		String sql = "{call INSERT_EMPLOYEE(?, ?, ?, ?, ?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, fName); 
		call.setString(2, fName); 
		call.setInt(3, dpt); 
		call.setLong(4, salary); 
		call.setString(5, email); 
		call.execute();
		conn.close();
	}
	
	public void getAverageSalary() throws SQLException {
		Connection conn = cF.getConnection();
		String sql =  
				"SELECT DEPARTMENT.DEPARTMENT_NAME, AVG(EMPLOYEE.SALARY) " + 
				"AS AVERAGE_SAL " + 
				"FROM DEPARTMENT INNER JOIN EMPLOYEE " + 
				"    ON DEPARTMENT.DEPARTMENT_ID = EMPLOYEE.DEPARTMENT_ID " + 
				"GROUP BY DEPARTMENT.DEPARTMENT_NAME"; 
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rS = stmt.executeQuery();
		while(rS.next()) {
			System.out.printf("Department: %s   Average: %.4f \n",rS.getString(1), rS.getFloat(2));
		}
		conn.close();
	}
	
	
}
