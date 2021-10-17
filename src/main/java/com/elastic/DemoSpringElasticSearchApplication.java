package com.elastic;

import com.elastic.docs.Company;
import com.elastic.docs.User;
import com.elastic.service.ElasticRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoSpringElasticSearchApplication {

    @Autowired
    ElasticRepositoryService repositoryService;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringElasticSearchApplication.class, args);

    }

    @Bean
    ApplicationRunner runner() {

        return args -> repositoryService.index(new Company()
                .setId("1")
                .setDocNum("123")
                .setDocType("PIN")
                .setName("Tester")
                .setUsers(Arrays.asList(
                        new User().setId("1_1").setDocNum("3").setDocType("DNI"),
                        new User().setId("1_2").setDocNum("2").setDocType("SSN")
                ))
        );
    }

}
