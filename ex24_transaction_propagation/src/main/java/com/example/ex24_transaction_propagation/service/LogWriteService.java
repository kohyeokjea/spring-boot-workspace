package com.example.ex24_transaction_propagation.service;

public interface LogWriteService {
    public int write(String consumerId, int amount);
}
