<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<%
   request.setCharacterEncoding("UTF-8");
   
   String returns = "";
   String type = request.getParameter("type");
   String count = request.getParameter("count");
   String save = request.getParameter("save");
   String survey1 = request.getParameter("survey1");
   String userid = request.getParameter("userid");
   String survey1_result = request.getParameter("survey1_result");
  
%>

<%
	if (type == null) {
	  return;
   }else if (type.equals("survey1_result")) {
      System.out.println("값을받았습니다."+userid+count+save);
      SurveyResult surveyresult = SurveyResult.getWrite();
      returns = surveyresult.write(userid, count, save);
      
      System.out.println(returns);
   }else if (type.equals("survey1")) {
      System.out.println("값을 리턴합니다.");
      Survey1 survey1list = Survey1.survey_print();
      returns = survey1list.select(); 
      out.println(returns);
      System.out.println(returns);

   }else if(type.equals("userid")){
	   System.out.println("값을 받았습니다."+userid);
	   Survey1Get survey1get = Survey1Get.survey_print();
	   returns = survey1get.select(userid);
	   out.println(returns);
	   System.out.println(returns);

   }
%>
