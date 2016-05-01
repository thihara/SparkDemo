package com.spark.demo.service;

import com.spark.demo.exception.SparkDemoException;
import com.spark.demo.util.Config;
import com.spark.demo.util.JSONParser;
import com.spark.demo.model.BidRequest;
import com.spark.demo.model.BidResponse;
import com.spark.demo.util.ValidationUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AuctionService {
    public static final int TIMEOUT = 5;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final Logger logger = Logger.getLogger(getClass().getName());

    public BidResponse sendBiddingRequest(String id, Map<String, String> attributes){

        ValidationUtils.notEmpty(id,"Bid ID");
        String jsonData = JSONParser.buildBidRequest(new BidRequest(Long.parseLong(id), attributes));

        List<Callable<String>> bidders = Config.getBidderURLs().stream()
                .map(url -> new Bidder(url, jsonData))
                .collect(Collectors.toList());

        try {
            Optional<BidResponse> maxBidResponse = executorService.invokeAll(bidders)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get(TIMEOUT, TimeUnit.SECONDS);
                        } catch (InterruptedException | ExecutionException | TimeoutException e) {
                            //Failed requests will be logged and ignored.
                            logger.log(Level.SEVERE, "Bidding request failed.");
                            return null;
                        }
                    })
                    .filter(response -> response != null)
                    .map(JSONParser::parseBidResponse)
                    .max(Comparator.comparing(bidResponse -> bidResponse.getBid()));

            return maxBidResponse.orElseThrow(() -> new SparkDemoException("Failed to retrieve bids from all endpoints"));
        } catch (InterruptedException e) {
            throw new SparkDemoException("Bid request interrupted.", e);
        }
    }
}
