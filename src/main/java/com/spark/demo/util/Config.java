package com.spark.demo.util;

import com.spark.demo.exception.SparkDemoException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class Config {

    public static final String BIDDER_CONFIG = "spark.demo.bidders";

    private static final Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new SparkDemoException("Unable to load configuration file.",e);
        }
    }

    public static String getConfig(String configName){
        ValidationUtils.notEmpty(configName, "Configuration Name");

        String config = props.getProperty(configName);

        return config;
    }

    public static Collection<String> getBidderURLs(){
        String urls = getConfig(BIDDER_CONFIG);

        ValidationUtils.notEmptyWithException(urls, "Bidder URL configuration not found.");

        return Arrays.asList(urls.split(";"));
    }
}
