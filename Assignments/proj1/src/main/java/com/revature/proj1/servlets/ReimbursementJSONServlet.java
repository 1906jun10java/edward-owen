package com.revature.proj1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.proj1.beans.Employee;
import com.revature.proj1.utils.CompanyDBUtilities;

/**
 * Both this and employeJSON no qualifiers only serv the session profile
 */
@WebServlet("/reimbursements")
public class ReimbursementJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate"); //should disable caching
		String username = request.getReader().readLine();
		//System.out.println("ReimsJSON doGet: Username="+username);
		Employee emp = CompanyDBUtilities.getEmployeeByName(username);
		//System.out.println("DoGet @ ReimbursementJSONServlet \n"+emp.getMyReimbursements());
		response.getWriter().write((new ObjectMapper()).writeValueAsString(emp.getMyReimbursements()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getReader().readLine();
		//System.out.println("ReimJSON doPost|| username="+username);
		Employee emp = CompanyDBUtilities.getEmployeeByName(username);
		
		response.getWriter().write((new ObjectMapper()).writeValueAsString(emp.getMyReimbursements()));
	}

}
