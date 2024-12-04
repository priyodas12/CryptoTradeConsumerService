package com.routemobile.cryptotradeconsumerservice.dao;

import com.routemobile.cryptotradeconsumerservice.model.CryptoTradeData;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoTradeDao extends JpaRepository<CryptoTradeData, UUID> {

}
