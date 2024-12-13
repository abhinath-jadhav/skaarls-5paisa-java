package com.skaarl.fivepaisa.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketData {

    @JsonProperty("Exch")
    private String exch;

    @JsonProperty("ExchType")
    private String exchType;

    @JsonProperty("Token")
    private int token;

    @JsonProperty("LastRate")
    private double lastRate;

    @JsonProperty("LastQty")
    private int lastQty;

    @JsonProperty("TotalQty")
    private int totalQty;

    @JsonProperty("High")
    private double high;

    @JsonProperty("Low")
    private double low;

    @JsonProperty("OpenRate")
    private double openRate;

    @JsonProperty("PClose")
    private double pClose;

    @JsonProperty("AvgRate")
    private double avgRate;

    @JsonProperty("Time")
    private int time;

    @JsonProperty("BidQty")
    private int bidQty;

    @JsonProperty("BidRate")
    private double bidRate;

    @JsonProperty("OffQty")
    private int offQty;

    @JsonProperty("OffRate")
    private double offRate;

    @JsonProperty("TBidQ")
    private int tBidQ;

    @JsonProperty("TOffQ")
    private int tOffQ;

    @JsonProperty("TickDt")
    private String tickDt;

    @JsonProperty("ChgPcnt")
    private double chgPcnt;
}