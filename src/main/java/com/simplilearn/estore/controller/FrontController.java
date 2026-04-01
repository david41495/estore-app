package com.simplilearn.estore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestType = request.getParameter("type");
		
		
		RequestDispatcher dispatcher = null;
		
		if(requestType.equals("login")) {
			dispatcher = request.getRequestDispatcher("login.jsp");
		} else if(requestType.equals("home")) {
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else { 
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		
		dispatcher.forward(request, response);
		
		
	}

}
