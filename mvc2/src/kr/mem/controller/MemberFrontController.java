package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;

//@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		// 1. 어떤 요청인지 파악하는 작업 -> *.do
		String reqUrl = request.getRequestURI();
//		System.out.println(reqUrl);
		String ctxPath = request.getContextPath();  // 여기가 contextpath
//	       System.out.println(ctxPath);
	     //클라이언트가 나에게 요청한 명령
	       String command = reqUrl.substring(ctxPath.length());  // contextpath를 점프해서 뒷 부분을 가져오는 것!!
	      System.out.println(command);
	      // 각 요청에 따라 처리 하기(분기작업)
	      Controller controller = null;
	      String nextView = null;
	      HandlerMapping mappings = new HandlerMapping();
	      controller = mappings.getController(command);
	      nextView = controller.requestHandle(request, response);
	      
	      // 핸들러매핑(HandlerMapping)
	      // /list.do --> MemberListController
	      // /insert.do --> MemberInsertController
	      // /insertForm.do --> MemberInsertFormController
	      // /delete.do --> MemberDeleteController
	      
//	      if (command.equals("/list.do")) {
//	    	  controller = new MemberListController();
//	    	  nextView = controller.requestHandle(request,response);
////	    	  RequestDispatcher rd = request.getRequestDispatcher(nextView);
////	    	  rd.forward(request, response);
//	    	  
//	      } else if (command.equals("/insert.do")) {
//	    	controller = new MemberInsertController();
//	    	nextView = controller.requestHandle(request,response);
////	    	response.sendRedirect(nextView);
//	    	  
//	      }else if (command.equals("/insertForm.do")) {
//	    	  controller = new MemberInsertFormController();
//	    	  nextView = controller.requestHandle(request,response);
////	    	  RequestDispatcher rd = request.getRequestDispatcher(nextView);
////	    	  response.sendRedirect(nextView);
//	    	  
//	      } else if (command.equals("/delete.do")) {
//	    	  controller = new MemberDeleteController();
//	    	  nextView = controller.requestHandle(request, response);
////	    	  response.sendRedirect(nextView);
//	      }
	      // View 페이지로 연동하는 부분
		
	      //위에서 페이지 이동 코드가 남아있어서 이중으로 이동하려고 하니 에러가 발생했네요 감사합니당
	      if (nextView != null) {
	    	  if(nextView.indexOf("redirect:")!=-1) {
	    		  String [] sp = nextView.split(":");  // 콜론(:)을 기준으로 뒤에 있는 걸 가져온다
	    		  response.sendRedirect(sp[1]);  
	    	  }else {
	    		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
	    		  rd.forward(request, response);
	    		  }
	      }
	      

	}

}
