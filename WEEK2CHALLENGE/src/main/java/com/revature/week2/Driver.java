package com.revature.week2;

import java.sql.SQLException;

import com.revature.dao.DAOImp;

public class Driver {
	private static DAOImp servitor = new DAOImp();
	
	public static void main(String[] args) {
		/*try {
			servitor.addEmployee("Thomas", "Moore", 3, 300, "missingHead@buckingham.uk.gov");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			servitor.getAverageSalary();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
