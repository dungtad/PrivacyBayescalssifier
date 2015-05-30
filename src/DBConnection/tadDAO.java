/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;
import DBConnection.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MANHKHUC
 */
public class tadDAO {
    public static List<tad> ListTad() {
        List<tad> list = new ArrayList<tad>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.openConnection();
            ps = con.prepareStatement("SELECT * FROM bayes LIMIT 14");

            rs = ps.executeQuery();
            while (rs.next()) {
                tad u = new tad();
                u.setId(rs.getInt(1));
                u.setOutlook(rs.getString(2));
                u.setTemperature(rs.getString(3));
                u.setHumidity(rs.getString(4));
                u.setWindy(rs.getString(5));
                u.setClasses(rs.getString(6));
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnect.closeAll(con, ps, rs);
        }

        return list;
    }
    public static ResultSet ListRs() {
       // List<tad> list = new ArrayList<tad>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.openConnection();
            ps = con.prepareStatement("SELECT * FROM bayes LIMIT 14");

            rs = ps.executeQuery();
//            while (rs.next()) {
//                tad u = new tad();
//                u.setId(rs.getInt(1));
//                u.setOutlook(rs.getString(2));
//                u.setTemperature(rs.getString(3));
//                u.setHumidity(rs.getString(4));
//                u.setWindy(rs.getString(5));
//                u.setClasses(rs.getString(6));
//                list.add(u);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(tadDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            DBConnect.closeAll(con, ps, rs);
        }

        return rs;
    }
}
