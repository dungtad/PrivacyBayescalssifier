/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.connect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MANHKHUC
 */
public class DBConnect {
    public static Connection openConnection(){
        Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://192.168.124.99/KMA", "tad", "123456");
            } catch (Exception e) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,e);
            }
            
        return con;
    }
    public static void closeAll(Connection con, PreparedStatement ps, ResultSet rs){
        try {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        if (openConnection() != null){
            System.out.println("ok");
        } else{
            System.out.println("not ok");
        }
    }
}

