package InsertSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcuteSql {
    public static void createDbUserTable(String JDBC_DRIVER, String DB_URL, String USER, String PASS, String createTableSQL) throws Exception {
        Connection conn = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(createTableSQL);
            System.out.println(createTableSQL + "is executed!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void insertOneLine(String JDBC_DRIVER, String DB_URL, String USER, String PASS, String sql) throws Exception {
        Connection conn = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.execute();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            while (rs.next()) {
                Long id = rs.getLong(1);
                System.out.println("insertOneLine " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static List<Map<String, Object>> executeQuery(String JDBC_DRIVER, String DB_URL, String USER, String PASS, String sql) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> dataMap = new HashMap<String, Object>(0);
                for (int i = 1; i <= columnNum; i++) {
                    dataMap.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                resultList.add(dataMap);
            }
           // System.out.println("query_table" + resultList);
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return new ArrayList<Map<String, Object>>();
    }

}
