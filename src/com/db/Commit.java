package com.db;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Commit{ //설문 1 테이블 조회
   private static Commit survey_print = new Commit();

   public static Commit survey_print() {
      return survey_print;
   }

   private String returns;
   private Connection conn = null;
   private Statement pstmt = null;
   private ResultSet rs = null;
   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
   String dbId = "sunbee"; 
   String dbPw = "1234";  
   public String select() {
      try {
    	   	
        returns ="";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
		pstmt = conn.createStatement();
		System.out.println("연결 성공");
         String query = "commit";
         
         rs = pstmt.executeQuery(query);
         
         
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