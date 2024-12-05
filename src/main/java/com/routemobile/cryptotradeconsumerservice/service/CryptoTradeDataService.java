package com.routemobile.cryptotradeconsumerservice.service;

import com.routemobile.cryptotradeconsumerservice.dao.CryptoTradeDao;
import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CryptoTradeDataService {

  private final CryptoTradeDao cryptoTradeDao;

  @Autowired
  public CryptoTradeDataService(CryptoTradeDao cryptoTradeDao) {
    this.cryptoTradeDao = cryptoTradeDao;
  }

  public List<CryptoTradeData> saveAllCryptoTradeInfo(List<CryptoTradeData> cryptoTradeDataList) {
    return cryptoTradeDao.saveAll(cryptoTradeDataList);
  }

  public Page<CryptoTradeData> getAllCryptoTradeInfo(Pageable pageable) {
    return cryptoTradeDao.findAll(pageable);
  }
}
