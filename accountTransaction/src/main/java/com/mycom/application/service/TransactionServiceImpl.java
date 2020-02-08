package com.mycom.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.application.dto.TransactionDTO;
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
/*		for(TransactionDTO dto:transactions) {
			dto.set String.format("%.2f", dto.getAmount()/100) ;
		}*/
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
}
