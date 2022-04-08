/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest;

import com.dmall.admiral.client.springboot.AdmiralSpringbootConfigurationInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.dmall.distributedidtest",AdmiralSpringbootConfigurationInitializer.ADMIRAL_SCAN_CONFIGURE_PATH })
public class DistributedidtestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributedidtestApplication.class, args);
    }
}
