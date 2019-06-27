package com.gg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gg.vo.MovieVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/DaumServlet")
public class DaumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DaumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<MovieVO> getDaum() {
    	ArrayList<MovieVO> list = new ArrayList<MovieVO>();
    	try {
		    // 1. URL 선언
		    String connUrl = "https://movie.daum.net/premovie/released";
		    // 2. HTML 가져오기
		    Document doc = Jsoup.connect(connUrl).get();
		    // 3. 가져온 HTML Document 를 확인하기
//		    System.out.println(doc.toString());
		    String selector = "a.name_movie";
		    Elements titles = doc.select(selector); // 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
		    
		    String selector2 = "img.img_g";
		    Elements img = doc.select(selector2); // 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
	        
		    String delStr = "//img1.daumcdn.net/thumb/C236x340/?fname=";
		    int cnt = 0;
		    
	        for(Element element: titles) { // 3. Elemntes 길이만큼 반복한다.
	        	
	        	Element ele = img.get(cnt++);
	        	String imgsrc = ele.attr("src");
	        	
	        	MovieVO vo = new MovieVO();
	        	vo.setImg(imgsrc.substring(delStr.length())); // 4. 원하는 요소가 출력된다.
	            vo.setTitle(element.text()); // 4. 원하는 요소가 출력된다.
	            vo.setLnk("https://movie.daum.net" + element.attr("href")); // 4. 원하는 요소가 출력된다.
	            list.add(vo);
	        }
		    
		} catch (IOException e) {
		    // Exp : Connection Fail
		    e.printStackTrace();
		}
    	return list;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MovieVO> list = getDaum();   // 위에 메소드 만들어 놓은거를 호출
		Gson gson = new Gson();
		String jsonPlace = gson.toJson(list);
//		System.out.println(jsonPlace);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
//		out.println(jsonPlace);
		request.setAttribute("mvlist", list);
		RequestDispatcher dis = request.getRequestDispatcher("daum.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
