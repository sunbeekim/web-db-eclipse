package com.db;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Type_Questions{ //설문 1 테이블 조회
   private static Type_Questions survey_print = new Type_Questions();

   public static Type_Questions survey_print() {
      return survey_print;
   }

   private String returns;
   private Connection conn = null;
   private Statement pstmt = null;
   private ResultSet rs = null;
   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
   String dbId = "sunbee"; 
   String dbPw = "1234";  
   public String select(String typesum, String count) {
      try {
    	   	
        returns ="";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
		pstmt = conn.createStatement();
		
         String query = "SELECT max(num1) num1 FROM type_questions2  where typesum = '"+typesum+"' and studynum = '"+count+"'";
         
         rs = pstmt.executeQuery(query);
         System.out.print("=========================== : ");
         while(rs.next()) {
            returns = rs.getString("num1");
            System.out.println(returns);
         } // end while
        
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