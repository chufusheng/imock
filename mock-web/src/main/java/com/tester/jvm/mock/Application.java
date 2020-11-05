package com.tester.jvm.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>
 *
 * @author fusheng.chu
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tester.jvm.mock.dal.repository")
@EntityScan("com.tester.jvm.mock.dal.model")
@ComponentScan("com.tester.jvm.mock.*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
