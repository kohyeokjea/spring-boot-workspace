package com.example.ex22_transaction_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.example.ex22_transaction_manager.dao.Transaction1DAO;
import com.example.ex22_transaction_manager.dao.Transaction2DAO;

@Service
public class BuyTicketServiceImpl implements BuyTicketService {
    @Autowired
    Transaction1DAO transaction1Dao;

    @Autowired
    Transaction2DAO transaction2Dao;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Override
    public int buy(String consumerId, int amount, String error) {

        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            transaction1Dao.pay(consumerId, amount);

            // 의도적인 에러
            if (error.equals("1")) {
                int n = 10 / 0;
            }

            transaction2Dao.pay(consumerId, amount); //추가

            transactionManager.commit(status); //추가

            return 1;
        } catch (Exception e) {
            System.err.println("Rollback!!");
            transactionManager.rollback(status); //추가
            return 0;
        }
    }
}
