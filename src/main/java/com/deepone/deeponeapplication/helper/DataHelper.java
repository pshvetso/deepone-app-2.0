package com.deepone.deeponeapplication.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class DataHelper {
    private final static ObjectMapper mapper = JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .build()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static String readFromResource(String filename) {
        try {
            return Files.readString(Paths.get("src", "test", "resources", filename));
        } catch (IOException e) {
            log.warn("Could not read {} from test resources.", filename);
        }
        return "";
    }

    public static <T> T resourceToObject(String filename, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(readFromResource(filename), valueTypeRef);
        } catch (JsonProcessingException e) {
            log.warn("File {} to object conversion error", filename, e);
        }
        return null;
    }

    public static <T> T stringToObject(String string, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(string, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.warn("String \"{}\" to object conversion error", string, e);
        }
        return null;
    }
}
