package com.rxjava.business.business.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

public class TestUtils {
    public static <T> T generateListOrObject(String path, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(new ClassPathResource(path).getInputStream(), clazz);
    }
}
