package com.example.building.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import lombok.extern.log4j.Log4j2;

/**
 * BeanUtil
 */
@Log4j2
public class BeanUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(DateTimeFormat.yyyyMMdd.getValue());
        DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern(DateTimeFormat.yyyyMMddHHmmss.getValue());
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern(TimeFormat.HHmmss.getValue());

        SimpleModule module = new SimpleModule()
            .addSerializer(LocalDate.class, new LocalDateSerializer(datePattern))
            .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimePattern))
            .addSerializer(LocalTime.class, new LocalTimeSerializer(timePattern))
            .addDeserializer(LocalDate.class, new LocalDateDeserializer(datePattern))
            .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimePattern))
            .addDeserializer(LocalTime.class, new LocalTimeDeserializer(timePattern));

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(module)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public static final <T, S> T createCopy(S oSrc, Class<T> type) {
        try {
            T copy = type.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(oSrc, copy);
            return copy;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            log.catching(e);
        }
        return null;
    }

    public static final <T, S> List<T> createCopy(List<S> oSrc, Class<T> type) {
        return oSrc.stream().map(o -> createCopy(o, type)).collect(Collectors.toList());
    }

    /**
     * mapConvertToDto
     *
     * @param <T>
     * @param map
     * @param ouputClass
     * @return T
     */
    public static final <T> T mapConvertToDto(Map<String, Object> map, Class<T> ouputClass) {
        T obj = objectMapper.convertValue(map, ouputClass);
        return obj;
    }

    /**
     * mapConvertToStreamDto
     *
     * @param <T>
     * @param map
     * @param ouputClass
     * @return T
     */
    public static final <T> T mapConvertToStreamDto(Map<Object, Object> map, Class<T> ouputClass) {
        T obj = objectMapper.convertValue(map, ouputClass);
        return obj;
    }

    /**
     * dtoConvertToMap
     *
     * @param oSrc
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static final <T, S> Map<String, Object> dtoConvertToMap(S oSrc) {
        Map<String, Object> mapResult = objectMapper.convertValue(oSrc, Map.class);
        return mapResult;
    }
}
