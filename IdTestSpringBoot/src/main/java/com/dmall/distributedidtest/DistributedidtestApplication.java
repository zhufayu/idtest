/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest;

import com.dmall.admiral.client.AdmiralClientContext;
import com.dmall.admiral.client.springboot.AdmiralSpringbootConfigurationInitializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@MapperScan("com.dmall.distributedidtest.mapper")
@Import(AdmiralSpringbootConfigurationInitializer.class)
//对接配置中心
//@ComponentScan(basePackages = {"com.dmall.distributedidtest",AdmiralSpringbootConfigurationInitializer.ADMIRAL_SCAN_CONFIGURE_PATH })
public class DistributedidtestApplication {
    public static void main(String[] args) {
        try {
            AdmiralClientContext.doModifyClasses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication application = new SpringApplication(DistributedidtestApplication.class);
        application.run(args);
    }
}
