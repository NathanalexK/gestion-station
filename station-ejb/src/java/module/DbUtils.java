package module;

import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

    public static List<Map<String, Object>> rechercher(String sql, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("SQL: " + sql);


        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            int nbCol = metaData.getColumnCount();
//            String[] colNames = ps.getMetaData().getColumnName().

            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for(int i = 0; i < nbCol; i++) {
                    map.put(metaData.getColumnName(i + 1), rs.getObject(i + 1));
                }
                list.add(map);
//                for(rs.get)
            }
            return list;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
        }
    }

    public static List<Map<String, Object>> rechercher(String table, String[] colAffiche, String apresWhere, Connection conn) throws Exception {
        String sql = "SELECT " + String.join("," , colAffiche) + " FROM " + table + " WHERE 1=1 " + apresWhere;
        System.out.println("SQL: " + sql);
        return rechercher(sql, conn);
    }

    public static List<Map<String, Object>> rechercher(String table, String[] colAffiche, String apresWhere) throws Exception {
        Connection conn = new UtilDB().GetConn();
        String sql = "SELECT " + String.join("," , colAffiche) + " FROM " + table + " WHERE 1=1 " + apresWhere;
        System.out.println(sql);
        List<Map<String, Object>> list = rechercher(sql, conn);
        if(conn != null) conn.close();
        return list;
    }




}
