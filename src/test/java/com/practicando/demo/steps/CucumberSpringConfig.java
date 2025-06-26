package com.practicando.demo.steps;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest
@ActiveProfiles("test")                // ← levanta tu ApplicationContext (con H2)
public class CucumberSpringConfig {
    /* vacío: solo enlaza Cucumber ↔ Spring */
}