# SparkDemo

## Libraries and frameworks used

[Spark web framework](http://sparkjava.com/)
A lightweight web framework running on Jetty

[Apache Http Client library](http://hc.apache.org/httpcomponents-client-ga/)
A library to handle http connections and methods.

[Maven](https://maven.apache.org/)
Maven for building the project

This project is written using Java 8. You will need JDK 1.8 installed to compile and run it.

## Configuration

The bidding endpoints are listed in the `src/main/resources/config.properties` file.
In the properties file edit the `spark.demo.bidders` property to change the bidders.

## Building and running the application

The application can be built easily with Maven.

Simply type in `mvn clean install` after editing the `config.properties` file to add your bidding endpoints.

To run the server and the application, you simply have to run the `Main` class. Spark and Jetty initialization is done in the
`Main.java` file, which is the main class for the entire application.

A convenient way to run it using Maven is provided.

Simply type in `mvn exec:java` and the server will startup and start listening at port `8080`
