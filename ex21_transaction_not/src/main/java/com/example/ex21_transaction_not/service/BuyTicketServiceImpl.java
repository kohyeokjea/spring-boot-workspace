package com.example.ex21_transaction_not.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex21_transaction_not.dao.Transaction1DAO;
import com.example.ex21_transaction_not.dao.Transaction2DAO;

@Service
public class BuyTicketServiceImpl implements BuyTicketService {
    @Autowired
    Transaction1DAO transaction1Dao;

    @Autowired
    Transaction2DAO transaction2Dao;

    @Override
    public int buy(String consumerId, int amount, String error) {

        try {
            transaction1Dao.pay(consumerId, amount);

            // 의도적인 에러
            if (error.equals("1")) {
                int n = 10 / 0;
            }

            transaction2Dao.pay(consumerId, amount);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
