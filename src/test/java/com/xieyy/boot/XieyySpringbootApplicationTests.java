package com.xieyy.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
class XieyySpringbootApplicationTests {

    @Autowired
    private Environment env;

    @Test
    void contextLoads() {
        System.out.println(Arrays.toString(env.getActiveProfiles()));
        System.out.println(Arrays.toString(env.getDefaultProfiles()));
        System.out.println(env.getProperty("xieyy.thread.queueSize"));
    }

    @Test
    public void test1() {
        try {
            Enumeration<URL> systemResources = ClassLoader.getSystemResources("xieyy.properties");
            while (systemResources.hasMoreElements()) {
                URL url = systemResources.nextElement();
                UrlResource urlResource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(urlResource);
                String school = properties.getProperty("school");
                System.out.println(school);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProperties() {
        Properties properties = new Properties();
        PropertiesPropertySource propertySource = new PropertiesPropertySource("xieyy.properties", properties);
        Object age = propertySource.getProperty("age");
        Map<String, Object> source = propertySource.getSource();
        System.out.println(source);
        System.out.println(age);
    }


}
