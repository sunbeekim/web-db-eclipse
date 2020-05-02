package com.db;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TypeReturn{ //설문 1 테이블 조회
   private static TypeReturn survey_print = new TypeReturn();

   public static TypeReturn survey_print() {
      return survey_print;
   }

   private String returns;
   private Connection conn = null;
   private Statement pstmt = null;
   private ResultSet rs = null;
   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
   String dbId = "sunbee"; 
   String dbPw = "1234";  
   public String select(String userid) {
      try {
    	   	
        returns ="";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
		pstmt = conn.createStatement();
		
         String query = "SELECT max(checkposition) checkposition FROM typeresult where userid = '"+userid+"' and rownum = 1 order by checkposition desc";
         
         rs = pstmt.executeQuery(query);
         while (rs.next()) {
    		 returns = rs.getString("checkposition");
    	 }  
         System.out.println(returns);
         if(returns.equals("null")) {
        	       
         }else {
        	rs = pstmt.executeQuery("select * from typeresult where userid = '"+userid+"' and rownum = 1 order by checkposition desc");
			while (rs.next()) {
			returns = rs.getString("typesum");
			} // end while
         }
         System.out.print("=========================== : ");
      } catch (Exception e) {
         e.printStackTrace();
      } // end try~catch

      finally {
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
   }// end select()

}