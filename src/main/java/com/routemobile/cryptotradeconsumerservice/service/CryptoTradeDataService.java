package com.routemobile.cryptotradeconsumerservice.service;

import com.routemobile.cryptotradeconsumerservice.dao.CryptoTradeDao;
import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeData;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CryptoTradeDataService {

  private final CryptoTradeDao cryptoTradeDao;

  public CryptoTradeDataService(CryptoTradeDao cryptoTradeDao) {
    this.cryptoTradeDao = cryptoTradeDao;
  }
  
  public void saveAllCryptoTradeInfo(List<CryptoTradeData> cryptoTradeDataList) {
    cryptoTradeDao.saveAll(cryptoTradeDataList);
  }
}
