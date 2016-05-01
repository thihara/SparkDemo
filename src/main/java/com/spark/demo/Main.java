package com.spark.demo;

import com.spark.demo.model.BidResponse;
import com.spark.demo.service.AuctionService;
import com.spark.demo.util.ValidationUtils;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class Main{

    private static AuctionService auctionService = new AuctionService();

    public static void main(String[] args) {

        Spark.port(8080);

        Spark.get("/:bidID",(request,response) -> {
            String bidID = request.params(":bidID");

            Map<String, String> parameters = new HashMap<>();
            request.queryParams().stream().forEach(param -> parameters.put(param,request.queryParams(param)));

            BidResponse maxBid = auctionService.sendBiddingRequest(bidID, parameters);

            String responseText = generateResponse(maxBid);

            return responseText;
        });
    }

    private static String generateResponse(BidResponse maxBid) {
        return maxBid.getContent().replaceAll("\\$price\\$",maxBid.getBid().toString());
    }
}