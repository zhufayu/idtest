package InsertSql;

import util.RandomTest;

import java.util.UUID;
import java.sql.*;

//测试mysql
public class InterSqlTest {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String USER = "dmall_inf";
    String PASS = "369JsZzKs8vu415";
    String DB_URL;
    Connection conn = null;

    public InterSqlTest(String driver, String dbUrl, String user, String pass) {
        this.JDBC_DRIVER = driver;
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    public void createTable() throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("drop table if exists tb_emp;");
        ExcuteSql.createDbUserTable(JDBC_DRIVER, DB_URL, USER, PASS, sb.toString());

        sb = new StringBuffer();
        sb.append("CREATE TABLE tb_emp(id bigint(20) PRIMARY KEY AUTO_INCREMENT,\tttinyint tinyint(1) unsigned zerofill DEFAULT NULL, ttinyint2 tinyint(2) unsigned zerofill DEFAULT NULL,\n" +
                "\ttsmallint smallint(5),\ttmediumint MEDIUMINT, bbigint BIGINT, salary FLOAT ,tdouble DOUBLE,amount DECIMAL(19,4), ytime YEAR,\tttime TIME,\n" +
                "\ttdate DATE,\ttDATETIME DATETIME,\ttTIMESTAMP TIMESTAMP,\tcchar CHAR,\ttname VARCHAR(2000),content varchar(4000),\tctinytext TINYTEXT,\tctext TEXT,\tcmediumtext MEDIUMTEXT,\tclongtext LONGTEXT,\n" +
                "\tbtext BIT(8), ttext BINARY(16), tvarbinary VARBINARY(16), ttinyblob TINYBLOB, tblob BLOB, tmediumblob MEDIUMBLOB, address longblob ,numbers ENUM('ぬ','て','@'),settest SET('ぬ', 'て', '%', '?')) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;\n");
        ExcuteSql.createDbUserTable(JDBC_DRIVER, DB_URL, USER, PASS, sb.toString());

        sb = new StringBuffer();
        sb.append("drop table if exists `wm_order_ware_00`;");
        ExcuteSql.createDbUserTable(JDBC_DRIVER, DB_URL, USER, PASS, sb.toString());

        sb = new StringBuffer();
        sb.append("CREATE TABLE `wm_order_ware_00` (\n" +
                "\t`id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,\n" +
                "\t`order_id` BIGINT(50) NULL DEFAULT NULL,\n" +
                "\t`shop_id` BIT(20) NULL DEFAULT NULL COMMENT '商家内部门店编号（废弃）',\n" +
                "\t`sku_id` BIGINT(20) NULL DEFAULT NULL,\n" +
                "\t`wm_sku_id` TINYINT(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '物美商品sku',\n" +
                "\t`ware_id` SMALLINT(20) NULL DEFAULT NULL,\n" +
                "\t`wm_ware_id` MEDIUMINT(20) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '物美商品id',\n" +
                "\t`matnr` DOUBLE NULL DEFAULT NULL,\n" +
                "\t`ware_weight` FLOAT NULL DEFAULT 0,\n" +
                "\t`ware_name` VARCHAR(200) NULL DEFAULT '',\n" +
                "\t`ware_img_url` VARCHAR(500) NULL DEFAULT NULL,\n" +
                "\t`item_num` VARCHAR(50) NULL DEFAULT '0',\n" +
                "\t`ware_num` INT(8) NULL DEFAULT NULL,\n" +
                "\t`offline_num` DECIMAL(12,4) NULL DEFAULT NULL COMMENT '线下推送订单的商品数量(支持浮点数,可能是0.6千克，可以精确到0.0001kg)',\n" +
                "\t`ware_type` INT(4) NULL DEFAULT NULL COMMENT '商品类型\\\\n1：普通\\\\n2：赠品\\\\n3：附件',\n" +
                "\t`ware_price` BIGINT(12) NULL DEFAULT NULL COMMENT '商品价格',\n" +
                "\t`promotion_price` DECIMAL(12,0) NULL DEFAULT 0 COMMENT '促销优惠',\n" +
                "\t`promotion_id` VARCHAR(1000) NULL DEFAULT NULL COMMENT '该商品参与的所有促销ID，多个值逗号隔开',\n" +
                "\t`properties` VARCHAR(2000) NULL DEFAULT NULL COMMENT '订单商品扩展字段',\n" +
                "\t`created` DATETIME NULL DEFAULT NULL,\n" +
                "\t`modified` DATETIME NULL DEFAULT NULL,\n" +
                "\t`yn` TINYINT(255) NULL DEFAULT NULL,\n" +
                "\t`sku_type` TINYINT(100) NULL DEFAULT NULL COMMENT '商品类型(1:标品,2:散卖)',\n" +
                "\t`promotion_price_list` VARCHAR(1000) NULL DEFAULT NULL COMMENT '该商品参与的所有促销金额，多个字用(,)隔开',\n" +
                "\t`promotion_type` DECIMAL(10,0) NULL DEFAULT NULL COMMENT '该商品参与的所有促销类型，多个类型用逗号(,)隔开',\n" +
                "\t`coupon_amount` DECIMAL(8,0) NULL DEFAULT NULL COMMENT '优惠券平分到每个商品上面的金额',\n" +
                "\t`invoice_type` DECIMAL(50,0) NULL DEFAULT NULL COMMENT '发票类型',\n" +
                "\t`source_ware_id` DECIMAL(20,0) NULL DEFAULT NULL COMMENT '修改订单中，本商品记录在原订单商品表中的ID',\n" +
                "\t`webuser_id` CHAR(200) NULL DEFAULT NULL,\n" +
                "\t`coupon_code_amount` TINYTEXT NULL DEFAULT NULL COMMENT '优惠码分摊到每个商品上的金额',\n" +
                "\t`catagory_id` TEXT NULL DEFAULT NULL COMMENT '商品品类ID',\n" +
                "\t`ware_tag` MEDIUMTEXT NULL DEFAULT NULL COMMENT '商品标签，以二进制位表示的整数',\n" +
                "\t`custom_tag` LONGTEXT NULL DEFAULT NULL COMMENT '商品自定义扩展标记',\n" +
                "\t`matkl` TINYBLOB NULL DEFAULT NULL COMMENT '商品线下品类id，多个用逗号隔开',\n" +
                "\t`offline_bar_code` VARBINARY(100) NULL DEFAULT NULL COMMENT '商品条形码，多个用逗号隔开',\n" +
                "\t`promotion_sub_type` ENUM('RED','GREEN','BLUE') NULL DEFAULT NULL COMMENT '促销子类型',\n" +
                "\t`ware_promotion_price` SET('a','b','c','d') NULL DEFAULT NULL COMMENT '商品促销后的单价',\n" +
                "\t`offline_coupon_amount` BIGINT(20) NULL DEFAULT 0,\n" +
                "\t`dmall_coupon_detail` GEOMETRYCOLLECTION NULL DEFAULT NULL COMMENT '多点券使用明细',\n" +
                "\t`offline_coupon_detail` GEOMETRY NULL DEFAULT NULL COMMENT '商家券使用明细',\n" +
                "\t`lucky_cut_detail` POINT NULL DEFAULT NULL COMMENT '随机减使用明细',\n" +
                "\t`promotion_detail` MULTIPOINT NULL DEFAULT NULL COMMENT '促销优惠明细',\n" +
                "\t`payment_detail` POLYGON NULL DEFAULT NULL COMMENT '商品支付明细',\n" +
                "\t`MultiLineStringtest` MULTILINESTRING NULL DEFAULT NULL,\n" +
                "\t`LineStringtest` LINESTRING NULL DEFAULT NULL,\n" +
                "\t`bianyrtest` BINARY(100) NULL DEFAULT NULL,\n" +
                "\t`dmall_coupon_detail_new` LONGTEXT NULL DEFAULT NULL COMMENT '多点券使用明细新' COLLATE 'utf8mb4_bin',\n" +
                "\t`dmall_coupon_detailCopy` VARCHAR(500) NULL DEFAULT NULL COMMENT '多点券使用明细',\n" +
                "\t`timetest` TIME NULL DEFAULT NULL,\n" +
                "\t`datatest` DATE NULL DEFAULT NULL,\n" +
                "\t`yeartest` YEAR NULL DEFAULT NULL,\n" +
                "\t`timestamptest` TIMESTAMP NULL DEFAULT NULL,\n" +
                "\t`blobtest` BLOB NULL DEFAULT NULL,\n" +
                "\t`meblob` MEDIUMBLOB NULL DEFAULT NULL,\n" +
                "\t`longblobtest` LONGBLOB NULL DEFAULT NULL,\n" +
                "\t`longblobtestCopy` LONGBLOB NULL DEFAULT NULL,\n" +
                "\tINDEX `order_id_idx` (`order_id`),\n" +
                "\tINDEX `idx_created` (`created`)\n" +
                ")\n" +
                "COLLATE='utf8_general_ci'\n" +
                "ENGINE=InnoDB;");
        ExcuteSql.createDbUserTable(JDBC_DRIVER, DB_URL, USER, PASS, sb.toString());
    }

    // 批量插入带事务指定不合法的值
    public void insertColumnWithInvalidValueByBatch() throws Exception {
        String sql = "insert into tb_emp( tname) values(?)";
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            //conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < 10; i++) {
                stmt.setString(1, UUID.randomUUID().toString());
                stmt.addBatch();
            }
            stmt.setString(1, "invalid value ------------------------------------------------------------------------------------------------------------");
            stmt.addBatch();
            stmt.executeBatch();
            //conn.commit();
            ResultSet result1 = stmt.getGeneratedKeys();
            while (result1.next()) {
                System.out.println("id " + result1.getLong(1));
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

    // 单行插入
    public void insertOneLine() throws Exception {
        String sql = "insert into tb_emp(ttinyint , ttinyint2 ,tsmallint ,tmediumint , bbigint , salary  ,tdouble ,amount , ytime ,ttime ,tdate ,tDATETIME ,tTIMESTAMP ,cchar ,tname ,content ,ctinytext ,ctext ,cmediumtext ,clongtext ,btext , ttext , tvarbinary , ttinyblob , tblob , tmediumblob , address  ,numbers ,settest ) \n" +
                "values(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、\n" +
                "、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て'))";

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.execute();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            //  rs.next();
            if (rs.next()) {
                Long id = rs.getLong(1);
                System.out.println("insertOneLine        " + id);
            } else {
                System.out.println("查询结果为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // 单行插入带反引号
    public void insertOneLineWithQuotation() throws Exception {
        String sql = "insert into `tb_emp`(`ttinyint`,`ttinyint2`,tsmallint ,tmediumint , bbigint , salary  ,tdouble ,amount , ytime ,ttime ,tdate ,tDATETIME ,tTIMESTAMP ,cchar ,tname ,content ,ctinytext ,ctext ,cmediumtext ,clongtext ,btext , ttext , tvarbinary , ttinyblob , tblob , tmediumblob , address  ,numbers ,settest ) \n" +
                "values(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、\n" +
                "、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て'))";

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.execute();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            //  rs.next();
            if (rs.next()) {
                Long id = rs.getLong(1);
                System.out.println("insertOneLine        " + id);
            } else {
                System.out.println("查询结果为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // 批量插入不带事务
    public void insertAllColumnByBatch() throws Exception {
        String sql =
                "insert into tb_emp(ttinyint , ttinyint2 ,tsmallint ,tmediumint , bbigint , salary  ,tdouble ,amount , ytime ,ttime ,tdate ,tDATETIME ,tTIMESTAMP ,cchar ,tname ,content ,ctinytext ,ctext ,cmediumtext ,clongtext ,btext , ttext , tvarbinary , ttinyblob , tblob , tmediumblob , address  ,numbers ,settest ) values(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て')),\n" +
                        "(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て')),\n" +
                        "(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て')),\n" +
                        "(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て')),\n" +
                        "(4,4,123,456,789,2000,2000,2000.00,78,1234,'1999-01-01','98-12-31 11:30:45',20081212121212,'？','121varchar','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*','文字符￥……、、、???？？·。。...·%@$#^&*',b'11111111',x'FA34E10293CB42848573A4E39937F479',x'FA34E10293CB42848573A4E39937F479',UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),UNHEX('FA34E10293CB42848573A4E39937F479'),1,('ぬ,て'))";

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            //conn.setAutoCommit(true);
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < 100; i++) {
                stmt.addBatch();
            }
            stmt.executeBatch();
            //conn.commit();
            ResultSet result1 = stmt.getGeneratedKeys();
            while (result1.next()) {
                System.out.println("insertAllColumnByBatch " + result1.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // 批量插入带事务指定部分列值
    public void insertColumnByBatch() throws Exception {
        String sql = "insert into tb_emp( tname) values(?)";
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < 1000; i++) {
                stmt.setString(1, UUID.randomUUID().toString());
                stmt.addBatch();
            }
            stmt.executeBatch();
            conn.commit();
            ResultSet result1 = stmt.getGeneratedKeys();
            while (result1.next()) {
                System.out.println("id " + result1.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //测试修改能正常
    public void update() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            String sql = "update tb_emp set ttinyint = 4 where ttinyint = 1 limit 1;";
            stmt = conn.prepareStatement(sql);
            int updateValue = stmt.executeUpdate();
            System.out.println("updateValue        " + updateValue);
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

    //测试查询能正常
    public void query() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            String sql = "select * from tb_emp";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String queryId = resultSet.getString(1);
                System.out.println("queryId        " + queryId);
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

    //测试删除能正常
    public void delete() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = null;
        try {
            String sql = "delete from tb_emp where ttinyint = 4 limit 1;";
            stmt = conn.prepareStatement(sql);
            int deleteValue = stmt.executeUpdate();
            System.out.println("deleteValue        " + deleteValue);
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

    public synchronized void testPonit() throws Exception {
        PreparedStatement preparedStmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行插入
            String sql = "INSERT INTO wm_order_ware_00 (" +
                    "lucky_cut_detail,promotion_detail," +
                    "offline_coupon_detail,dmall_coupon_detail,LineStringtest" +
                    ",MultiLineStringtest,payment_detail,ware_name,created)" +
                    " values (ST_PointFromText(?)," +
                    "ST_GeometryFromText(?),ST_GeometryFromText(?)," +
                    "ST_GeometryFromText(?),ST_GeometryFromText(?),ST_GeometryFromText(?),ST_GeometryFromText(?),(?),(?))";
            // PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // preparedStmt.setInt(1, RandomTest.random());

            //测试sql POINT类型
            preparedStmt.setString(1, "POINT(103.11 123.231)");
            //测试sql MULTIPOINT类型
            preparedStmt.setString(2, "MULTIPOINT(2 3)");
            //测试sql Geometry 类型
            preparedStmt.setString(3, "POINT(2 3)");
            //测试sql GeometryCollection 类型
            preparedStmt.setString(4, "MULTIPOINT(2 3)");
            //测试sql LINESTRING
            preparedStmt.setString(5, "LINESTRING(1.1 1.2, 3.1 3.1)");
            //测试sql MultiLineString/
            preparedStmt.setString(6, "MULTILINESTRING((1 1, 3 5), (-5 3, -8 -2))");
            //测试sql Polygon
            preparedStmt.setString(7, "POLYGON((-20 -20, -20 20, 20 20, 20 -20, -20 -20))");
            preparedStmt.setString(8, "mm");
            //System.out.println(RandomTest.datatime());

            preparedStmt.setTimestamp(9, RandomTest.datatime());

            preparedStmt.execute();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                System.out.println("testPonit          " + id);
            } else {
                System.out.println("查询结果为空");
            }

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } finally {
            if (preparedStmt != null) {
                preparedStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //批量写法一
    public void preparedStatementBatch() throws Exception {
        PreparedStatement stat = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO wm_order_ware_00 (" +
                    "promotion_detail," +
                    "offline_coupon_detail,dmall_coupon_detail,LineStringtest" +
                    ",MultiLineStringtest,payment_detail,ware_name,created)" +
                    " values (ST_GeometryFromText(?),ST_GeometryFromText(?)," +
                    "ST_GeometryFromText(?),ST_GeometryFromText(?),ST_GeometryFromText(?),ST_GeometryFromText(?),(?),(?))";
            stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < 10; i++) {
                stat.setString(1, "MULTIPOINT(2 3)");
                stat.setString(2, "POINT(2 3)");
                stat.setString(3, "MULTIPOINT(2 3)");
                stat.setString(4, "LINESTRING(1.1 1.2, 3.1 3.1)");
                stat.setString(5, "MULTILINESTRING((1 1, 3 5), (-5 3, -8 -2))");
                stat.setString(6, "POLYGON((-20 -20, -20 20, 20 20, 20 -20, -20 -20))");
                stat.setString(7, "mm");
                stat.setTimestamp(8, RandomTest.datatime());
                stat.addBatch();
            }
            int[] result = stat.executeBatch();
            ResultSet result1 = stat.getGeneratedKeys();
            while (result1.next()) {
                System.out.println("preparedStatementBatch " + result1.getLong(1));
            }

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

    //批量写法二
    public void testStatementBatch() throws Exception {
        Class.forName(JDBC_DRIVER);

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stat = null;
        try {
            //    		conn.setAutoCommit(false);
            stat = conn.createStatement();
            for (int i = 0; i < 5; i++) {
                stat.addBatch("INSERT INTO wm_order_ware_00 (sku_id) VALUES (1)");
            }
            stat.executeBatch();
            //System.out.println(result.length);
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


    public void statementBatchDiffSql() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stat = null;
        try {
            stat = conn.createStatement();
            conn.setAutoCommit(false);
            long orderId = System.nanoTime() + Thread.currentThread().getId();
            String tname = Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID();

            stat.addBatch("INSERT INTO tb_emp (tname,content,salary) VALUES ('" + tname + "'," + orderId + ",1.0 )");
            stat.addBatch("INSERT INTO tb_emp (tname,content) VALUES ('" + tname + "'," + orderId + ")");

            int[] a = stat.executeBatch();
            conn.commit();
            ResultSet resultSet = stat.getGeneratedKeys();
            while (resultSet.next()) {
                String queryId = resultSet.getString(1);
                System.out.println("queryId        " + queryId);
            }

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
}