package com.routemobile.cryptotradeconsumerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@Table(name = "crypto_trade_data")
@Entity
public class CryptoTradeData {

  @Id
  @Column(name = "transaction_id", nullable = false, unique = true)
  private UUID transactionId;

  @Column(name = "conversion_pair")
  private String conversionPair;

  @Column(name = "trade_type")
  private String tradeType;

  @Column(name = "origin_country")
  private String tradeCountry;

  @Column(name = "trade_price", precision = 38, scale = 10)
  private BigDecimal tradePrice;

  @Column(name = "exchange_rate", precision = 38, scale = 10)
  private BigDecimal exchangeRate;

  @Column(name = "is_approved")
  private boolean isApproved;

  @Column(name = "report_create_date")
  private LocalDateTime reportCreateDate;
}
