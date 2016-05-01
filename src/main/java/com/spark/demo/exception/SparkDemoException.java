package com.spark.demo.exception;

public class SparkDemoException extends RuntimeException{

    public SparkDemoException() {
        super();
    }

    public SparkDemoException(String message) {
        super(message);
    }

    public SparkDemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SparkDemoException(Throwable cause) {
        super(cause);
    }
}
