package com.example.stockapp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Stock {
    private Date date;
    @JsonProperty("name")
    private String companyName;
    private String ticker;
    @JsonProperty("open")
    private String open;
    @JsonProperty("high")
    private String high;
    @JsonProperty("low")
    private String low;
    @JsonProperty("close")
    private String close;
    @JsonProperty("volume")
    private String volume;

    Stock() {

    }
    @JsonCreator
    public Stock(@JsonProperty("open") String open, @JsonProperty("close") String close,
         @JsonProperty("high") String high, @JsonProperty("low") String low,
                 @JsonProperty("volume") String volume) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    public Date getDate(Date date) {
        return date;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getTicker() {
        return ticker;
    }

    @JsonGetter("open")
    public String getOpen() {
        return open;
    }
    @JsonGetter("close")
    public String getClose() {
        return close;
    }
    @JsonGetter("high")
    public String getHigh() {
        return high;
    }
    @JsonGetter("low")
    public String getLow() {
        return low;
    }
    @JsonGetter("volume")
    public String getVolume() {
        return volume;
    }
    @JsonSetter("name")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String stockToString() {
        return "TEST:\nCompany name: " + companyName + "\nopen: " + open + "\nvolume: " + volume;
    }

}
