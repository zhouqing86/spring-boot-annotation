package com.example;

import com.example.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AnnotationConfigApplicationContextTest {

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    @Test
    public void testGetBeanMessage() throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);
        assertEquals("Hello,World!", ctx.getBean("message"));
    }

    @Test
    public void testScanPackages() throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.example");
        ctx.refresh();
        assertEquals("Hello,World!", ctx.getBean("message"));
    }

    @Test
    public void testGetBeansByClass() throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(TestConfiguration.class);
        ctx.refresh();
        String message = ctx.getBean(String.class);
        assertEquals("Hello,World!", message);
    }

    @Test
    public void testEnvironmentString() throws Exception {
        environmentVariables.set("BEAN_NAME", "Bean Name");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(TestConfiguration.class);
        ctx.refresh();
        User user = ctx.getBean(User.class);
//        System.out.println("testEnvironmentString" + user.getFirstName());
        assertEquals("Bean Name", user.getFirstName());
    }
}
