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

public class TypeResult {//설문1 결과 쓰기
	private static TypeResult vision = new TypeResult();

	public static TypeResult getWrite() {
		return vision;
	}
	private String returns;
	   private Connection conn = null;
	   private Statement pstmt = null;
	   private ResultSet rs = null;
	   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
	   String dbId = "sunbee"; 
	   String dbPw = "1234";  

	public String write(String userid, String typesum) {
		String[] type;
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			pstmt = conn.createStatement();
			System.out.println("연결 성공");
	         
		Statement stmt = conn.createStatement();
		String seq = "select *from surveycheck where userid = "+"'"+userid+"'";
		ResultSet rs = stmt.executeQuery(seq);
		
		rs = pstmt.executeQuery(seq);
		type = typesum.split("-");
		typesum = typesum.replace("-", "");
		typesum = typesum.replace("\t", "");
		System.out.print(type[0]);
		System.out.print(type[1]);
		System.out.print(type[2]);
		
		String user = null;
		String check = null;
		String date = null;
		int toint = 0;
		while(rs.next()) {
			
			user = rs.getString("userid");
			check = rs.getString("checkposition");
			date = rs.getString("checkdate");
			toint = Integer.parseInt(check);
		}				
			
		if(user!= null && toint != 0) {			
			rs = pstmt.executeQuery("insert into surveycheck(userid, checkposition, checkdate) values('"+userid+"',"+(toint+1)+", to_char(sysdate, 'yyyy.mm.dd.hh24:mm'))");
			rs = pstmt.executeQuery("insert into typeresult(userid, type1, type2, type3, typesum, checkposition) values('"+userid+"', '"+type[0]+"', '"+type[1]+"', '"+type[2]+"', '"+typesum+"', "+ (toint+1) +")");
			
		}else{
			rs = pstmt.executeQuery("insert into surveycheck(userid, checkposition, checkdate) values('"+userid+"',"+1+", to_char(sysdate, 'yyyy.mm.dd.hh24:mm'))");
			rs = pstmt.executeQuery("insert into typeresult(userid, type1, type2, type3, typesum, checkposition) values('"+userid+"', '"+type[0]+"', '"+type[1]+"', '"+type[2]+"', '"+typesum+"', "+ 1 +")");
			
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