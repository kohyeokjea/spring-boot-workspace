package com.example.ex23_transaction_template.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction2DAO {
    public void pay(String consumerId, int amount);
}
