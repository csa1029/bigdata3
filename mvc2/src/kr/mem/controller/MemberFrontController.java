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
		// 1. � ��û���� �ľ��ϴ� �۾� -> *.do
		String reqUrl = request.getRequestURI();
//		System.out.println(reqUrl);
		String ctxPath = request.getContextPath();  // ���Ⱑ contextpath
//	       System.out.println(ctxPath);
	     //Ŭ���̾�Ʈ�� ������ ��û�� ���
	       String command = reqUrl.substring(ctxPath.length());  // contextpath�� �����ؼ� �� �κ��� �������� ��!!
	      System.out.println(command);
	      // �� ��û�� ���� ó�� �ϱ�(�б��۾�)
	      Controller controller = null;
	      String nextView = null;
	      HandlerMapping mappings = new HandlerMapping();
	      controller = mappings.getController(command);
	      nextView = controller.requestHandle(request, response);
	      
	      // �ڵ鷯����(HandlerMapping)
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
	      // View �������� �����ϴ� �κ�
		
	      //������ ������ �̵� �ڵ尡 �����־ �������� �̵��Ϸ��� �ϴ� ������ �߻��߳׿� �����մϴ�
	      if (nextView != null) {
	    	  if(nextView.indexOf("redirect:")!=-1) {
	    		  String [] sp = nextView.split(":");  // �ݷ�(:)�� �������� �ڿ� �ִ� �� �����´�
	    		  response.sendRedirect(sp[1]);  
	    	  }else {
	    		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
	    		  rd.forward(request, response);
	    		  }
	      }
	      

	}

}
