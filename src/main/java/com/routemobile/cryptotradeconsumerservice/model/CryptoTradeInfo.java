package com.routemobile.cryptotradeconsumerservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * {
 * "transactionId": "8b9d27db-2562-47aa-8dfc-f87e0d472fb5",
 * "conversionPair": "SOL/YEN",
 * "tradeType": "BUY",
 * "tradeCountry": "US",
 * "tradePrice": 435.6243975776997,
 * "exchangeRate": 147.24248431600714,
 * "isApproved": true,
 * "reportCreateDate": "2024-12-05T00:45:37.777112300"
 * }
 * </pre>
 */
@Getter
@Setter
@Builder
public class CryptoTradeInfo implements Serializable {

  private UUID transactionId;

  private String conversionPair;

  private String tradeType;

  private String tradeCountry;

  private BigDecimal tradePrice;

  private BigDecimal exchangeRate;

  private boolean isApproved;

  private LocalDateTime reportCreateDate;

  public CryptoTradeInfo() {
  }

  @JsonCreator
  public CryptoTradeInfo(
      @JsonProperty("transactionId") UUID transactionId,
      @JsonProperty("conversionPair") String conversionPair,
      @JsonProperty("tradeType") String tradeType,
      @JsonProperty("tradeCountry") String tradeCountry,
      @JsonProperty("tradePrice") BigDecimal tradePrice,
      @JsonProperty("exchangeRate") BigDecimal exchangeRate,
      @JsonProperty("isApproved") boolean isApproved,
      @JsonProperty("reportCreateDate") LocalDateTime reportCreateDate
  ) {
    this.transactionId = transactionId;
    this.conversionPair = conversionPair;
    this.tradeType = tradeType;
    this.tradeCountry = tradeCountry;
    this.tradePrice = tradePrice;
    this.exchangeRate = exchangeRate;
    this.isApproved = isApproved;
    this.reportCreateDate = reportCreateDate;
  }
}

