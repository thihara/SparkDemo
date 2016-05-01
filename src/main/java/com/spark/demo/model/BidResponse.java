package com.spark.demo.model;

import java.math.BigDecimal;

public class BidResponse {
    private long id;
    private BigDecimal bid;
    private String content;

    public BidResponse() {
    }

    public BidResponse(long id, BigDecimal bid, String content) {
        this.id = id;
        this.bid = bid;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
