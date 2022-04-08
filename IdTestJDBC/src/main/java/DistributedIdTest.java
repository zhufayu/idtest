import InsertSql.GEOTest;
import InsertSql.InterSqlTest;
import InsertSql.MultiSqlTest;
import com.dmall.admiral.client.AdmiralClientContext;
import com.dmall.admiral.client.spring.AdmiralPlaceholderConfigurer;
import util.HttpClientUtil;

public class DistributedIdTest {

    private void testMysql(String driver, String url, String user, String pass) throws Exception {
        InterSqlTest interSql = new InterSqlTest(driver, url, user, pass);
        interSql.createTable();
        interSql.insertOneLine();
        interSql.insertAllColumnByBatch();
        interSql.insertColumnByBatch();
        interSql.insertOneLineWithQuotation();

        interSql.update();
        interSql.query();
        interSql.delete();
        interSql.testPonit();
        interSql.preparedStatementBatch();
        interSql.testStatementBatch();
        interSql.statementBatchDiffSql();
    }

    //mysql 5
    private void testMysql5() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String user = "dmall_inf";
        String pass = "369JsZzKs8vu415";

        String DB_URL5 = "jdbc:mysql://10.248.224.3:11204/BpTestDb?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false";
        String DB_URL5_RewriteBatched = "jdbc:mysql://10.248.224.3:11204/BpTestDb?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&rewriteBatchedStatements=true";

        testMysql(driver, DB_URL5, user, pass);
        testMysql(driver, DB_URL5_RewriteBatched, user, pass);
        //InterSqlTest interSql = new InterSqlTest(DB_URL5);
        //interSql.insertColumnWithInvalidValueByBatch();
    }

    //mysql 10.2.25-MariaDB-log
    private void testMysql10() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String user = "dmall_inf";
        String pass = "369JsZzKs8vu415";

        String DB_URL10 = "jdbc:mysql://10.248.224.12:26102/BpIdTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false";
        String DB_URL10_RewriteBatched = "jdbc:mysql://10.248.224.12:26102/BpIdTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&rewriteBatchedStatements=true";

        testMysql(driver, DB_URL10, user, pass);
        testMysql(driver, DB_URL10_RewriteBatched, user, pass);
    }

    public void testServer() {
        String url = "http://10.248.224.72/pulsar/getid";
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        try {
            httpClientUtil.HttpGetRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url2 = "http://10.248.224.72/pulsar/getid";
        try {
            httpClientUtil.HttpGetRequest(url2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url3 = "http://10.248.224.72/pulsar/getid?increment=100";
        try {
            httpClientUtil.HttpGetRequest(url3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testMutilSql5() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String user = "dmall_inf";
        String pass = "369JsZzKs8vu415";

        String DB_URL5 = "jdbc:mysql://10.248.224.145:11204/BpIdTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&allowMultiQueries=true";
        String DB_URL5_RewriteBatched = "jdbc:mysql://10.248.224.145:11204/BpIdTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&rewriteBatchedStatements=true&allowMultiQueries=true";

        GEOTest geoTest = new GEOTest(driver, DB_URL5, user, pass);
        geoTest.test("mysql5");
        MultiSqlTest multiSqlTest = new MultiSqlTest(driver, DB_URL5, user, pass);
        multiSqlTest.multiCreateTable();
        multiSqlTest.testMultiSql();
        multiSqlTest.testStatementBatch();

        geoTest = new GEOTest(driver, DB_URL5_RewriteBatched, user, pass);
        geoTest.test("mysql5");
        multiSqlTest = new MultiSqlTest(driver, DB_URL5_RewriteBatched, user, pass);
        multiSqlTest.multiCreateTable();
        multiSqlTest.testMultiSql();
        multiSqlTest.testStatementBatch();
    }

    public void testMutilSql10() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String user = "dmall_inf";
        String pass = "123456";

        String DB_URL10 = "jdbc:mysql://10.248.224.12:26102/BpIdTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&allowMultiQueries=true";
        String DB_URL10_RewriteBatched = "jdbc:mysql://10.248.224.73:5000/BpIdTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&rewriteBatchedStatements=true&allowMultiQueries=true";

        GEOTest geoTest = new GEOTest(driver, DB_URL10, user, pass);
        geoTest.test("mysql10");
        geoTest = new GEOTest(driver, DB_URL10_RewriteBatched, user, pass);
        geoTest.test("mysql10");

        MultiSqlTest multiSqlTest = new MultiSqlTest(driver, DB_URL10, user, pass);
        multiSqlTest.multiCreateTable();
        multiSqlTest.testMultiSql();
        multiSqlTest.testStatementBatch();

        multiSqlTest = new MultiSqlTest(driver, DB_URL10_RewriteBatched, user, pass);
        multiSqlTest.multiCreateTable();
        multiSqlTest.testMultiSql();
        multiSqlTest.testStatementBatch();
        multiSqlTest.testStatementBatchRewrite();
        multiSqlTest.preparedStatementBatchStep();
    }

    //-javaagent:C:\code\jdbc-agent.jar -Djdbc.server.url=http://10.248.224.72/pulsar/getid -Dcom.dmall.zone=rz01 -Djdbc.switch=true

    public static void main(String[] args) throws Exception {
        String appKey5 = "EBD0C21A8A75086D65566BB56123AD1C103DA68D9061732D6897A426AFCE037D";
        String secretKey5 = "b57a4be3a912e38a2f5693f1";
        String appName5 = "pulsar-bp-id-test";

//        try {
//          AdmiralPlaceholderConfigurer   configurer1 = new AdmiralPlaceholderConfigurer(
//                    "remote",
//                    "dev",
//                    appName5,
//                    appKey5,
//                    secretKey5,
//                    null,
//                    1,
//                    true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Thread.sleep(1000);

        AdmiralClientContext context =  AdmiralClientContext.build("mid-cloud-test-admiral")
//                .setPropertiesLocations(new String[]{"classpath:application.properties"})
                .setPropertiesLocations(new String[]{"local.properties"})
                .setLocalPropsFirst(false)
                .startUp()
                .getContext();


        DistributedIdTest test = new DistributedIdTest();
//        test.testServer();

        test.testMutilSql5();
//        test.testMutilSql10();

        test.testMysql5();
//        test.testMysql10();
    }
}





