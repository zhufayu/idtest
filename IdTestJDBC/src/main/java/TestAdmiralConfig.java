import com.dmall.admiral.client.spring.AdmiralPlaceholderConfigurer;

import java.lang.reflect.Method;
import java.util.Set;

public class TestAdmiralConfig {
    public static void main(String[] args) throws Exception {
        String appKey5 = "EBD0C21A8A75086D65566BB56123AD1C103DA68D9061732D6897A426AFCE037D";
        String secretKey5 = "b57a4be3a912e38a2f5693f1";
        String appName5 = "pulsar-bp-id-test";
        try {
            AdmiralPlaceholderConfigurer configurer1 = new AdmiralPlaceholderConfigurer(
                    "remote",
                    "dev",
                    appName5,
                    appKey5,
                    secretKey5,
                    null,
                    1,
                    true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 反射通过admiral获取配置
        Class admiralContextRegistry = Class.forName("com.dmall.admiral.client.ContextRegistry");
        if (null == admiralContextRegistry) {
            return;
        }
        Method contextNamesMethod = admiralContextRegistry.getMethod("getAllRegisteredContextName");
        Method resolvePlaceholderMethod = admiralContextRegistry.getMethod("resolvePlaceholder", String.class, String.class);
        if (null == contextNamesMethod || null == resolvePlaceholderMethod) {
            return;
        }

        Set<String> allContextNames = (Set<String>) contextNamesMethod.invoke(null);

        // 遍历获取配置中心相关配置
        for (String contextName : allContextNames) {
            String configStr = (String) resolvePlaceholderMethod.invoke(null, contextName, "db.jdbc.pulsar");
            System.out.println(configStr);
        }
    }


}
