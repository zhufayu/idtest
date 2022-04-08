package InsertSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class GEOTest {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String USER = "dmall_inf";
    String PASS = "369JsZzKs8vu415";
    String DB_URL ;
    Connection conn = null;

    public GEOTest(String driver, String dbUrl, String user , String pass){
        this.JDBC_DRIVER = driver;
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    private static String tableName = "t_geo_test";

    private static String createSql = "drop table IF EXISTS " + tableName +  ";\n " +
            "CREATE TABLE " + tableName + "(`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  `geometry_1` geometry NULL,  `point_1` point NULL,  `linestring_1` linestring NULL,  `polygon_1` polygon NULL,  `multipoint_1` multipoint NULL,  `multilinestring_1` multilinestring NULL,  `multipolygon_1` multipolygon NULL,  `geometrycollection_1` geometrycollection NULL)";
    private static String createSql10 = "drop table IF EXISTS " + tableName +  ";\n " +
            "CREATE TABLE " + tableName + "(`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,  `geometry_1` geometry NULL,  `point_1` point NULL,  `linestring_1` linestring NULL,  `polygon_1` polygon NULL,  `multipoint_1` multipoint NULL,  `multilinestring_1` multilinestring NULL,  `multipolygon_1` multipolygon NULL,  `geometrycollection_1` geometrycollection NULL) ENGINE=MyISAM";


    private static String insertSql = "insert into " + tableName + "(`name` ,`geometry_1`,`point_1`,`linestring_1`,`polygon_1`,multipoint_1,multilinestring_1,multipolygon_1,geometrycollection_1) VALUES ('P1', " +
            "ST_GeomFromText('POINT(121.474 31.2329)'), ST_GeomFromText('POINT(121.474 31.2329)'), ST_GeomFromText('LINESTRING(1 3, 12 5, 12 7)'), ST_GeomFromText('POLYGON((121.474 31.2345, 121.472 31.2333, 121.471 31.2315, 121.472 31.2302, 121.473 31.2304, 121.476 31.232, 121.474 31.2345))'), ST_GeomFromText('MULTIPOINT(0 0, 20 20, 60 60)'), ST_GeomFromText('MULTILINESTRING((10 10, 20 20), (15 15, 30 15))'), ST_GeomFromText('MULTIPOLYGON(((0 0, 10 0, 10 10, 0 10, 0 0)), ((5 5, 7 5, 7 7, 5 7, 5 5)))'), ST_GeomFromText('GEOMETRYCOLLECTION(POINT(10 10), POINT(30 30), LINESTRING(15 15, 20 20))'))";
    private static String updateSql = "update " + tableName + " " +
            "set geometry_1=ST_GeomFromText('POINT(121.174 31.2329)'), point_1 =ST_GeomFromText('POINT(121.474 31.2329)'), linestring_1=ST_GeomFromText('LINESTRING(1 3, 12 5, 12 7)'), polygon_1=ST_GeomFromText('POLYGON((121.474 31.2345, 121.472 31.2333, 121.471 31.2315, 121.472 31.2302, 121.473 31.2304, 121.476 31.232, 121.474 31.2345))'),multipoint_1=ST_GeomFromText('MULTIPOINT(0 0, 20 20, 60 60)'),multilinestring_1=ST_GeomFromText('MULTILINESTRING((10 10, 20 20), (15 15, 30 15))'), multipolygon_1= ST_GeomFromText('MULTIPOLYGON(((0 0, 10 0, 10 10, 0 10, 0 0)), ((5 5, 7 5, 7 7, 5 7, 5 5)))'),geometrycollection_1 = ST_GeomFromText('GEOMETRYCOLLECTION(POINT(10 10), POINT(30 30), LINESTRING(15 15, 20 20))')  " +
            "where name ='P1' limit 1; ";
    private static String deleteSql = "delete from " + tableName + " where geometry_1=ST_GeomFromText('POINT(121.174 31.2329)') limit 1;";


    private void insertBatch() throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            System.out.println(insertSql);
            for (int i = 0; i < 400; i++) {
                stmt = conn.prepareStatement(insertSql);
                stmt.addBatch();
            }
            int[] counts = stmt.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void test(String mysqlVersion) throws Exception {
        if(mysqlVersion.contains("10")){
            ExcuteSql.createDbUserTable(JDBC_DRIVER,  DB_URL,  USER,  PASS,  createSql10);
        }else{
            ExcuteSql.createDbUserTable(JDBC_DRIVER,  DB_URL,  USER,  PASS,  createSql);
        }

        ExcuteSql.insertOneLine(JDBC_DRIVER,  DB_URL,  USER,  PASS,  insertSql);

        insertBatch();

        ExcuteSql.insertOneLine(JDBC_DRIVER,  DB_URL,  USER,  PASS,  updateSql);
        ExcuteSql.insertOneLine(JDBC_DRIVER,  DB_URL,  USER,  PASS,  deleteSql);
    }
}
