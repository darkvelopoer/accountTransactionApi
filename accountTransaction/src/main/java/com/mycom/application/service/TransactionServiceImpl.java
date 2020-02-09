package com.mycom.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.application.dto.TransactionDTO;
import com.mycom.application.model.Transaction;
import com.mycom.mybatis.mapper.TransactionMapper;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionMapper transactionMapper;
	
	public List<TransactionDTO> getTransactions(Map<String, String> params) {
		List<TransactionDTO> transactions = transactionMapper.getTransactions(params);
		return transactions;
	}
	
	public List<TransactionDTO> getTransactionsByAccountNo(Map<String, String> params){
		List<TransactionDTO> transactions = transactionMapper.getTransactionsByAccountNo(params);
		return transactions;
	}
	
	public List<TransactionDTO> getTransactionsByDesc(Map<String, String> params) {
		List<TransactionDTO> transactions = transactionMapper.getTransactionsByDesc(params);
		return transactions;
	}
	
	public List<TransactionDTO> getTransactionsByCustomerId(Map<String, String> params) {
		List<TransactionDTO> transactions = transactionMapper.getTransactionsByCustomerId(params);
		return transactions;
	}
	
	public void saveTransaction(Transaction transaction) {
		transaction.setTrxTimestamp(transaction.getTrxDate().replace("-", "") + transaction.getTrxTime().replace(":", ""));
		double d = Double.parseDouble(transaction.getAmount())*100;
		int amtCents = (int)d;
		transaction.setAmount(amtCents+"");
		transactionMapper.saveTransaction(transaction);
	}
}
