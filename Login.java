package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.DB.DBConnect;
import com.entities.user;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		UserDAO dao=new UserDAO(DBConnect.getConnection());
		user user1=dao.getLogin(email, password);
		
		if(user1!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("user-ob", user1);
			response.sendRedirect("dashboard.jsp");
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("error-msg", "Invalid email & Password");
			response.sendRedirect("Login.jsp");
		}
	}

}