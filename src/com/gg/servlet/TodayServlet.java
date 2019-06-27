package com.gg.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/TodayServlet")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void todayJson() {
    	String str = "[{'NO':1,'NAME':'APPLE','KOR':'사과','PRICE':'1000'},{'NO':2,'NAME':'BANANA','KOR':'바나나','PRICE':'500'},{'NO':3,'NAME':'MELON','KOR':'메론','PRICE':'2000'}]";
    	
       	try {
		    // 1. URL 선언
		    String connUrl = "";
            URL url = new URL(connUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
//            System.out.println(response.toString());
//            str = response.toString();
	
       	} catch (Exception e) {
       		e.printStackTrace();
		}
       	
       	System.out.println(str);
    	
    	
    	JsonParser jsonParser = new JsonParser();
    	JsonArray jsonArray = (JsonArray) jsonParser.parse(str);
    	for (int i = 0; i < jsonArray.size(); i++) {
    		JsonObject object = (JsonObject) jsonArray.get(i);
    		String NO = object.get("NO").getAsString();
    		String NAME = object.get("NAME").getAsString();
    		String KOR = object.get("KOR").getAsString();
    		String PRICE = object.get("PRICE").getAsString();

    		System.out.println(NO);
    		System.out.println(NAME);
    		System.out.println(KOR);
    		System.out.println(PRICE);
    		System.out.println();
    		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		todayJson();
//		Gson gson = new Gson();
//		String jsonPlace = gson.toJson(placeList);

		RequestDispatcher dis = request.getRequestDispatcher("today.jsp");
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
