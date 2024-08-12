package com.example.ex24_transaction_propagation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex24_transaction_propagation.dao.Transaction3DAO;

@Service
public class LogWriteServiceImpl implements LogWriteService {
    @Autowired
    Transaction3DAO transaction3Dao;

    @Override
    public int write(String consumerId, int amount) {
        try {
            transaction3Dao.pay(consumerId, amount);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
