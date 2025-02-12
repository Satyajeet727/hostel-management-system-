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
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		user us = new user();
		us.setName(name);
		us.setBranch(branch);
		us.setEmail(email);
		us.setPassword(password);

		UserDAO dao = new UserDAO(DBConnect.getConnection());
		boolean f = dao.userRegister(us);

		if (true) {
			 HttpSession session=request.getSession();
			 session.setAttribute("reg-msg", "Registration Sucessfully..");
			 response.sendRedirect("Registration.jsp");
		} else {
			HttpSession session=request.getSession();
			session.setAttribute("error-msg", "Something Went Wrong Server");
			response.sendRedirect("Registration.jsp");
		}

	}

}