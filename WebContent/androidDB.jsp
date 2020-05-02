<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<%
   request.setCharacterEncoding("UTF-8");
   
   int intreturn;
   String returns = "";
   String type = request.getParameter("type");
   String count = request.getParameter("count");
   String save = request.getParameter("save");
   String survey1 = request.getParameter("survey1");
   String userid = request.getParameter("userid");
   String survey1_result = request.getParameter("survey1_result");
   String typesum = request.getParameter("typesum");   
  
%>

<%
	if (type == null) {
	  return;
   }else if (type.equals("survey1_result")) {
      System.out.println("값을받았습니다."+userid+count+save);
      SurveyResult surveyresult = SurveyResult.getWrite();
      returns = surveyresult.write(userid, save, count);
      
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

   }else if(type.equals("commit")){
	   System.out.println("commit 했습니다.");
	   Commit commit = Commit.survey_print();
	   returns = commit.select();
	   System.out.println(returns);
   }
   else if(type.equals("survey1rc")){
	   System.out.println("survey1resultcount 했습니다."+userid);
	   Survey1ResultCount sRC = Survey1ResultCount.survey_print();
	   returns = sRC.select(userid);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("typeresult")){
	   System.out.println("typeresult 했습니다."+userid+typesum);
	   TypeResult typeResult = TypeResult.getWrite();
	   returns = typeResult.write(userid, typesum);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("surveycheck")){
	   System.out.println("surveycheck 했습니다."+userid);
	   SurveyCheck surveycheck = SurveyCheck.survey_print();
	   returns = surveycheck.select(userid);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("studylist")){
	   System.out.println("studylist 했습니다.");
	   StudyList studylist = StudyList.survey_print();
	   returns = studylist.select();
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("typeget")){
	   System.out.println("typeget 했습니다.");
	   TypeReturn typereturn = TypeReturn.survey_print();
	   returns = typereturn.select(userid);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("log")){
	   System.out.println("log 했습니다.");
	   Log log = Log.getWrite();
	   returns = log.write(userid, typesum, count);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("type_questions")){
	   System.out.println("type_questions 했습니다.");
	   Type_Questions type_q = Type_Questions.survey_print();
	   returns = type_q.select(typesum, count);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("type_sum")){
	   System.out.println("type_sum 했습니다.");
	   Type_Sum type_s = Type_Sum.survey_print();
	   returns = type_s.select(typesum);
	   out.println(returns);
	   System.out.println(returns);
   }
   else if(type.equals("type_log")){
	   System.out.println("type_log 했습니다.");
	   Type_Log type_l = Type_Log.survey_print();
	   returns = type_l.select(typesum, count);
	   out.println(returns);
	   System.out.println(returns);
   }
%>
