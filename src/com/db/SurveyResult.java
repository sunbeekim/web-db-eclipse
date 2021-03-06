package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SurveyResult {//설문1 결과 쓰기
	private static SurveyResult vision = new SurveyResult();

	public static SurveyResult getWrite() {
		return vision;
	}
	private String returns;
	   private Connection conn = null;
	   private Statement pstmt = null;
	   private ResultSet rs = null;
	   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
	   String dbId = "sunbee"; 
	   String dbPw = "1234";  

	public String write(String userid, String save, String count) {
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			pstmt = conn.createStatement();
			System.out.println("연결 성공");
	         
		Statement stmt = conn.createStatement();
		String seq = "select *from survey1result where questionsnum = " + count + "and userid = "+"'"+userid+"'";
		ResultSet rs = stmt.executeQuery(seq);
		rs = pstmt.executeQuery(seq);
		//질문 넘버가 존재하면 기존의 넘버를 삭제하고 수행
		String check = null;
		String user = null;
		while(rs.next()) {
			check = rs.getString("questionsnum");
			user = rs.getString("userid");
			System.out.println("조회 성공 : " + check);
		}				
			
		if(check != null&& user!= null) {
			System.out.println("업데이트 성공");
			seq = "update survey1result set survey1num = '"+ save +"' where userid = '"+ userid +"' and questionsnum = '"+ count+"'";
			rs = pstmt.executeQuery(seq);
		}else {
			System.out.println("삽입 성공");
			seq = "insert into survey1result(userid, questionsnum, survey1num) values('"+userid+"', '"+count+"', '"+save+"')";
			rs = pstmt.executeQuery(seq);
		}
		
		System.out.print("=========================== : ");
		returns = "success";

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
	    if (pstmt != null)
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (rs != null)
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (conn != null)
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return returns;
       }
        public static String getCurrentTime(String timeFormat) {
	       return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
        }
}