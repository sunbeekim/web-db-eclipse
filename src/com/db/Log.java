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

public class Log {//설문1 결과 쓰기
	private static Log vision = new Log();

	public static Log getWrite() {
		return vision;
	}
	private String returns;
	   private Connection conn = null;
	   private Statement pstmt = null;
	   private ResultSet rs = null;
	   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
	   String dbId = "sunbee"; 
	   String dbPw = "1234";  

	public String write(String userid, String typesum, String count) {
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			pstmt = conn.createStatement();
			System.out.println("연결 성공");
	         
		Statement stmt = conn.createStatement();
		String seq = "select *from log where userid = '"+userid+"' and studynum = "+count+" and rownum = 1 order by click desc";
		ResultSet rs = stmt.executeQuery(seq);
		rs = pstmt.executeQuery(seq);
		
		String check = null;
		String user = null;
		int toint = 0;
		while(rs.next()) {
			check = rs.getString("click");
			toint = Integer.parseInt(check);
		}				
			
		if(check != null) {
			
			seq = "insert into log(userid, typesum, studynum, click, logdate) values('"+userid+"', '"+typesum+"', "+count+", "+(toint+1)+", to_char(sysdate, 'yyyy.mm.dd hh24:mi'))";
			rs = pstmt.executeQuery(seq);
		}else {
			System.out.println("삽입 성공");
			seq = "insert into log(userid, typesum, studynum, click, logdate) values('"+userid+"', '"+typesum+"', "+count+", "+1+", to_char(sysdate, 'yyyy.mm.dd hh24:mi'))";
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