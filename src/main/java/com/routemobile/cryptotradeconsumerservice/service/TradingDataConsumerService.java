package com.routemobile.cryptotradeconsumerservice.service;

import static com.routemobile.cryptotradeconsumerservice.util.Constant.DEFAULT_QUEUE_NAME;

import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeData;
import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeInfo;
import jakarta.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TradingDataConsumerService {

  private final CryptoTradeDataService cryptoTradeDataService;

  @Autowired
  public TradingDataConsumerService(CryptoTradeDataService cryptoTradeDataService) {
    this.cryptoTradeDataService = cryptoTradeDataService;
  }

  @RabbitListener(queues = DEFAULT_QUEUE_NAME)
  public void receiveMessage(List<CryptoTradeInfo> cryptoTradeInfoList) {
    List<CryptoTradeData> cryptoTradeInfoList1 = new LinkedList<>();
    log.info("Received message: {}", cryptoTradeInfoList);

    var list = cryptoTradeInfoList.stream().parallel().map(cti -> {
      CryptoTradeData cryptoTradeData = new CryptoTradeData();

      cryptoTradeData.setTransactionId(cti.getTransactionId());
      cryptoTradeData.setApproved(cti.isApproved());
      cryptoTradeData.setTradePrice(cti.getTradePrice());
      cryptoTradeData.setReportCreateDate(cti.getReportCreateDate());
      cryptoTradeData.setConversionPair(cti.getConversionPair());
      cryptoTradeData.setTradeType(cti.getTradeType());
      cryptoTradeData.setTradeCountry(cti.getTradeCountry());
      cryptoTradeData.setExchangeRate(cti.getExchangeRate());

      return cryptoTradeData;
    }).toList();

    cryptoTradeInfoList1.addAll(list);

    saveCryptoTradeInfoBatch(cryptoTradeInfoList1);

  }

  @Transactional
  public void saveCryptoTradeInfoBatch(List<CryptoTradeData> cryptoTradeDataList) {
    int batchSize = 10;
    for (int i = 0; i < cryptoTradeDataList.size(); i += batchSize) {
      int endIndex = Math.min(i + batchSize, cryptoTradeDataList.size());
      List<CryptoTradeData> cryptoTradeDataList1 = cryptoTradeDataList.subList(i, endIndex);
      cryptoTradeDataService.saveAllCryptoTradeInfo(cryptoTradeDataList1);
    }
  }
}
