package com.spark.demo.util;

import com.google.gson.Gson;
import com.spark.demo.model.BidRequest;
import com.spark.demo.model.BidResponse;

public class JSONParser {
    private final static Gson gson = new Gson();

    public static String buildBidRequest(BidRequest bidRequest){
        return gson.toJson(bidRequest);
    }

    public static BidResponse parseBidResponse(String jsonResponse){
        return gson.fromJson(jsonResponse, BidResponse.class);
    }
}
