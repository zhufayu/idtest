package util;

import java.sql.Timestamp;
import java.util.Random;

public class RandomTest {
    public static  int random(){
        int max=100;
        int min=1;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
       return s;
    }
    public static Timestamp  datatime(){
        Timestamp t = new Timestamp(System.currentTimeMillis());
        return t;
    }
}
