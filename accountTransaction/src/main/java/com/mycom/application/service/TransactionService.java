package com.mycom.application.service;

import java.util.List;
import java.util.Map;

import com.mycom.application.dto.TransactionDTO;
import com.mycom.mybatis.model.Transaction;

public interface TransactionService {
	public List<TransactionDTO> getTransactions(Map<String, String> params);
	
	public List<TransactionDTO> getTransactionsByAccountNo(Map<String, String> params);
	
	public List<TransactionDTO> getTransactionsByDesc(Map<String, String> params);
	
	public List<TransactionDTO> getTransactionsByCustomerId(Map<String, String> params);
	
	public void saveTransaction(Transaction transaction);
}
