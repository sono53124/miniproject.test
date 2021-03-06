package testapp.copy1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url="jdbc:oracle:thin:@localhost:1521:XE";
   private String user="user1104";
   private String password="user1104";
   
   public Connection connect() {
      Connection con = null;
      try {
         Class.forName(driver);
         con = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return con;
   }
   
   public void close(PreparedStatement pstmt) {
      if(pstmt!=null) {
         try {
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   
   public void close(PreparedStatement pstmt, ResultSet rs) {
      if(rs!=null) {
         try {
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(pstmt!=null) {
         try {
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
    

   public void disconnect(Connection con) {
      if(con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}