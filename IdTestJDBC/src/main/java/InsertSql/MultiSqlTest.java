package InsertSql;

import java.sql.*;
import java.util.*;

public class MultiSqlTest {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String USER = "dmall_inf";
    String PASS = "369JsZzKs8vu415";
    String DB_URL;
    Connection conn = null;

    public MultiSqlTest(String driver, String dbUrl, String user, String pass) {
        this.JDBC_DRIVER = driver;
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    public void testStatementBatch() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stat = null;
        try {
            stat = conn.createStatement();
            conn.setAutoCommit(false);

            ArrayList<String> nameList = new ArrayList<>();
            for (int i = 0; i < 330; i++) {
                long orderId = System.nanoTime() + Thread.currentThread().getId();
                String name = Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID();
                nameList.add(name);
                stat.addBatch("INSERT INTO tb_emp4 (name,content,salary) VALUES ('" + name + "'," + orderId + ",4 )");
                stat.addBatch("select * from tb_emp4 where salary = 1 limit 100");
                stat.addBatch("update tb_emp4 set salary = 4 where salary = 1 limit 100");

                name = Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID();
                nameList.add(name);
                stat.addBatch("INSERT INTO tb_emp4 (name,content) VALUES ('" + name + "'," + orderId + ")");
                stat.addBatch("select * from tb_emp4 where salary = 1 limit 100");
                stat.addBatch("update tb_emp4 set salary = 4 where salary = 1 limit 100");

                name = Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID();
                nameList.add(name);
                stat.addBatch("INSERT INTO tb_emp2 (name,age) VALUES ('" + name + "',18)");
                stat.addBatch("select * from tb_emp2 where age = 18 limit 100");
                stat.addBatch("update tb_emp2 set age = 4 where age = 18 limit 100");
                stat.addBatch("delete from tb_emp2 where age = 1 limit 1");
            }

            stat.executeBatch();
            conn.commit();
            ResultSet resultSet = stat.getGeneratedKeys();
            ArrayList<String> list = new ArrayList<>();
            HashMap<String, String> keyVMap = new HashMap<>();

            while (resultSet.next()) {
                String queryId = resultSet.getString(1);
                //System.out.println("queryId " + queryId);
                list.add(queryId);
                if ("0".equals(queryId) || "-1".equals(queryId)) {

                } else {
                    int size = keyVMap.size();
                    keyVMap.put(queryId, (size % 3 == 2 ? "tb_emp2," : "tb_emp4,") + nameList.get(size));
                }
            }
            // System.out.println(keyVMap);

            for (String key : keyVMap.keySet()) {
                String value = keyVMap.get(key);
                String[] vs = value.split(",");
                String sql = "select id,name from " + vs[0] + " where id = " + key + " and name = '" + vs[1] + "'";
                List<Map<String, Object>> result = ExcuteSql.executeQuery(JDBC_DRIVER, DB_URL, USER, PASS, sql);
                if (result.size() == 0) {
                    System.out.println("Error in case ===========" + key + "=======" + vs[0] + "==========" + vs[1]);
                    return;
                }
            }

            System.out.println("check success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void preparedStatementBatchStep() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stat = null;
        String sql1 = "INSERT INTO tb_emp4 (name,content,salary) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql1,PreparedStatement.RETURN_GENERATED_KEYS);
            conn.setAutoCommit(false);

            ArrayList<String> nameList = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                long orderId = System.nanoTime() + Thread.currentThread().getId();
                String name = Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID();
                nameList.add(name);
                stat.setString(1,name);
                stat.setString(2,orderId+"");
                stat.setInt(3,4);
                stat.addBatch();
            }

            stat.executeBatch();
            conn.commit();
            ResultSet resultSet = stat.getGeneratedKeys();

            HashMap<String, String> keyVMap = new HashMap<>();
            while (resultSet.next()) {
                String queryId = resultSet.getString(1);
                int size = keyVMap.size();
                keyVMap.put(queryId,  "tb_emp4," + nameList.get(size));
            }

            for (String key : keyVMap.keySet()) {
                String value = keyVMap.get(key);
                String[] vs = value.split(",");
                String sql = "select id,name from " + vs[0] + " where id = " + key + " and name = '" + vs[1] + "'";
                List<Map<String, Object>> result = ExcuteSql.executeQuery(JDBC_DRIVER, DB_URL, USER, PASS, sql);
                if (result.size() == 0) {
                    System.out.println("Error in case ===========" + key + "=======" + vs[0] + "==========" + vs[1]);
                    return;
                }
            }

            System.out.println("check success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void testStatementBatchRewrite() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stat = null;
        try {
            stat = conn.createStatement();
            conn.setAutoCommit(false);

            ArrayList<String> nameList = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                long orderId = System.nanoTime() + Thread.currentThread().getId();
                String name = Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID();
                nameList.add(name);
                stat.addBatch("INSERT INTO tb_emp4 (name,content,salary) VALUES ('" + name + "'," + orderId + ",4 )");
            }

            stat.executeBatch();
            conn.commit();
            ResultSet resultSet = stat.getGeneratedKeys();

            HashMap<String, String> keyVMap = new HashMap<>();
            while (resultSet.next()) {
                String queryId = resultSet.getString(1);
                int size = keyVMap.size();
                keyVMap.put(queryId,  "tb_emp4," + nameList.get(size));
            }

            for (String key : keyVMap.keySet()) {
                String value = keyVMap.get(key);
                String[] vs = value.split(",");
                String sql = "select id,name from " + vs[0] + " where id = " + key + " and name = '" + vs[1] + "'";
                List<Map<String, Object>> result = ExcuteSql.executeQuery(JDBC_DRIVER, DB_URL, USER, PASS, sql);
                if (result.size() == 0) {
                    System.out.println("Error in case ===========" + key + "=======" + vs[0] + "==========" + vs[1]);
                    return;
                }
            }

            System.out.println("check success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    public void multiCreateTable() throws Exception {
        String createTableSQL = "drop table if exists `tb_emp4`;\n" +
                "CREATE TABLE `tb_emp4`(`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(200), `content` varchar(200), `salary` int);" +
                "drop table if exists `tb_emp1`;\n" +
                "CREATE TABLE `tb_emp1`(`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(200), `age` int);" +
                "drop table if exists `tb_emp2`;" +
                "CREATE TABLE `tb_emp2`(`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(200), `age` int);" +
                "drop table if exists `tb_emp3`;" +
                "CREATE TABLE `tb_emp3`(`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(200), `age` int);";
        ExcuteSql.createDbUserTable(JDBC_DRIVER, DB_URL, USER, PASS, createTableSQL);
    }

    public void testMultiSql() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into tb_emp1( name) values( 'test123');\n");
        sb.append("insert into tb_emp1( age) values( 18);\n");
        sb.append("insert into tb_emp1( name,age) values( 'test',1);\n");
//        sb.append("insert into tb_emp1( id,name,age) values( 1,'test',1);\n");
        sb.append("update tb_emp1 set age = 18 where name = 'test' limit 1; \n");

        sb.append("insert into tb_emp2( name) values( 'test123');");
        sb.append("insert into tb_emp2( age) values( 18);");
        sb.append("insert into tb_emp2( name,age) values( 'test',1);");
        sb.append("update tb_emp2 set age = 18 where name = 'test' limit 1; ");
        sb.append("delete from tb_emp2 where name = 'test' limit 1; ");

        sb.append("insert into tb_emp3( name) values( 'test123');");
        sb.append("insert into tb_emp3( name) values( 'test');");
        sb.append("update tb_emp3 set age = 18 where name = 'test' limit 1; ");
        String sql = sb.toString();
        ExcuteSql.insertOneLine(JDBC_DRIVER, DB_URL, USER, PASS, sql);
    }

}
