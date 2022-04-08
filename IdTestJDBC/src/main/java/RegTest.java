import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegTest {

    public static void main(String args[]) {
        String str = "tb_emp1";
        String pattern = ".*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}

