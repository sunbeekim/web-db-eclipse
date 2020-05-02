package com.db;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Type_Sum{ //설문 1 테이블 조회
   private static Type_Sum survey_print = new Type_Sum();

   public static Type_Sum survey_print() {
      return survey_print;
   }

   private String returns;
   private Connection conn = null;
   private Statement pstmt = null;
   private ResultSet rs = null;
   String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
   String dbId = "sunbee"; 
   String dbPw = "1234";  
   public String select(String typesum) {
      try {
    	   	
        returns ="";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
		pstmt = conn.createStatement();
		
		//typesum = typesum.replaceAll(" ", "");
         
         //https://wanna-b.tistory.com/104
		//db에 디폴트값 넣어줘도 될듯?
         rs = pstmt.executeQuery("select max(typesumcount) typesumcount from type_sumnum where typesum = '"+typesum+"'");
         int into;
         System.out.print("=========================== : ");
         while(rs.next()) {
            returns = rs.getString("typesumcount");
            if(returns != null)into = Integer.parseInt(returns);
            
           
         } 
         
         
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