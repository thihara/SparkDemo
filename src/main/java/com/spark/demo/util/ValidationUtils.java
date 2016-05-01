package com.spark.demo.util;

import com.spark.demo.exception.SparkDemoException;

import java.util.Collection;


public class ValidationUtils {

    public static void notEmpty(Collection collection, String fieldName){
        notNull(collection, fieldName);

        if(collection.isEmpty())
            throwException(fieldName + " cannot be empty");
    }

    public static void notEmpty(String string, String fieldName){
        notEmptyWithException(string, fieldName + " cannot be empty");
    }

    public static void notEmptyWithException(String string, String exceptionMessage){
        notNullWithException(string, exceptionMessage);

        if(string.isEmpty())
            throwException(exceptionMessage);
    }

    public static void notNull(Object object, String fieldName){
        notNullWithException(object, fieldName + " cannot be null");
    }

    public static void notNullWithException(Object object, String exceptionMessage){
        if(object == null)
            throwException(exceptionMessage);
    }

    private static void throwException(String message) {
        throw new SparkDemoException(message);
    }
}
