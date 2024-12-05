package com.routemobile.cryptotradeconsumerservice.service;

import static com.routemobile.cryptotradeconsumerservice.util.Constant.DEFAULT_QUEUE_NAME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.routemobile.cryptotradeconsumerservice.exception.CryptoTradeDataMappingException;
import com.routemobile.cryptotradeconsumerservice.exception.CryptoTradeDataPersistanceException;
import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeData;
import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeInfo;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TradingDataConsumerService {

  private static final int BATCH_SIZE = 30;
  private final List<CryptoTradeData> buffer = new ArrayList<>();
  private final CryptoTradeDataService cryptoTradeDataService;
  ReentrantLock reentrantLock = new ReentrantLock();

  @Autowired
  public TradingDataConsumerService(CryptoTradeDataService cryptoTradeDataService) {
    this.cryptoTradeDataService = cryptoTradeDataService;
  }

  @RabbitListener(queues = DEFAULT_QUEUE_NAME)
  public void receiveMessage(String cryptoTradeInfo) {
    try {
      reentrantLock.lock();
      log.debug("Message Pulled from Queue: {}", cryptoTradeInfo);
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      CryptoTradeInfo cryptoTradeInfo1;
      try {
        cryptoTradeInfo1 = objectMapper.readValue(cryptoTradeInfo,
            CryptoTradeInfo.class);
      } catch (JsonProcessingException e) {
        log.warn("Error while Mapping CryptoTradeData: {}", cryptoTradeInfo, e);
        throw new CryptoTradeDataMappingException(e.getMessage());
      }
      buffer.add(produceCryptoTradeData(cryptoTradeInfo1));
      saveCryptoTradeInfoBatch(buffer);
    } catch (Exception e) {
      log.warn("Error while persisting CryptoTradeData: {}", cryptoTradeInfo, e);
      throw new CryptoTradeDataPersistanceException(e.getMessage());
    } finally {
      reentrantLock.unlock();
    }
  }

  @Transactional
  public void saveCryptoTradeInfoBatch(List<CryptoTradeData> cryptoTradeDataList) {
    if (buffer.size() >= BATCH_SIZE) {
      log.info("\n\nPersisting CryptoTradeData :{},BatchIdentifier: {}, QueueSize: {}",
          cryptoTradeDataList,
          Instant.now().getNano(), buffer.size());
      List<CryptoTradeData> savedAllCryptoTradeData = cryptoTradeDataService.saveAllCryptoTradeInfo(
          buffer);
      buffer.removeIf(savedAllCryptoTradeData::contains);
      log.info("\n\nPersisted CryptoTradeData :{},BatchIdentifier: {}, QueueSize: {}",
          cryptoTradeDataList,
          Instant.now().getNano(), buffer.size());
    }
  }

  private CryptoTradeData produceCryptoTradeData(CryptoTradeInfo cti) {
    log.debug("Producing CryptoTradeData: {}", cti.getTransactionId());
    CryptoTradeData ctd = new CryptoTradeData();
    ctd.setApproved(cti.isApproved());
    ctd.setConversionPair(cti.getConversionPair());
    ctd.setReportCreateDate(cti.getReportCreateDate());
    ctd.setTradePrice(cti.getTradePrice());
    ctd.setTransactionId(cti.getTransactionId());
    ctd.setTradeType(cti.getTradeType());
    ctd.setExchangeRate(cti.getExchangeRate());
    ctd.setTradeCountry(cti.getTradeCountry());
    return ctd;
  }
}
