package com.gg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.mylib.MyUtils;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/NvAPIServlet")
public class NvAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NvAPIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");   // 한글 처리 위해
		String search =  request.getParameter("search");
//		System.out.println(sch);
//		search = "장마";
		String api = MyUtils.getNvAPI(search);   // 네이버에서 정보 가져오는

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println(api);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
