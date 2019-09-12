/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package che;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class SignUp_class {
     conn con = new conn();
    
    public int register(String fname, String lname, String username, String password){
   
    int x = 0;
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);
        String sql = "insert into sign_up values(null,?,?,?,md5(?),0)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        
        pstmt.setString(1, fname);
        pstmt.setString(2, lname);
        pstmt.setString(3, username);
        pstmt.setString(4, password);
  
        
        x = pstmt.executeUpdate();
        
        //System.out.println(x);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUp_class.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignUp_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    return x;
    }
    
    public int confirmPassword(String password, String confirmpassword){
        int x = 0;
        
        if(password.equals(confirmpassword)){
            x = 1;
        }else{
            x = 0;
        }
        return x;
    }
    
    public int checkUsername(String username){
        int x = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);
        
            String sql = "SELECT username FROM `sign_up` WHERE  username= ?;";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                x = 1;
            }else{
                x = 0;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUp_class.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignUp_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    
}
