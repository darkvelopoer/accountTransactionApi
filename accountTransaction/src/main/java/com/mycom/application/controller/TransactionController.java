package com.mycom.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.application.dto.TransactionDTO;
import com.mycom.application.service.TransactionService;

@RestController
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/rest/getTransactions")
	public List<TransactionDTO> getTransactions(@RequestParam String startNum, @RequestParam String endNum) {
		Map<String, String> params = new HashMap<>();
		params.put("startNum", startNum);
		params.put("endNum", endNum);
		List<TransactionDTO> transactions = transactionService.getTransactions(params);
		return transactions;
	}
	
	@GetMapping("/rest/getTransactionsByAccountNo")
	public List<TransactionDTO> getTransactionsByAccountNo(@RequestParam String accountNo, @RequestParam String startNum, @RequestParam String endNum) {
		Map<String, String> params = new HashMap<>();
		params.put("accountNo", accountNo);
		params.put("startNum", startNum);
		params.put("endNum", endNum);
		List<TransactionDTO> transactions = transactionService.getTransactionsByAccountNo(params);
		return transactions;
	}
	
	@GetMapping("/rest/getTransactionsByDescription")
	public List<TransactionDTO> getTransactionsByDescription(@RequestParam String description, @RequestParam String startNum, @RequestParam String endNum) {
		Map<String, String> params = new HashMap<>();
		params.put("description", description);
		params.put("startNum", startNum);
		params.put("endNum", endNum);
		List<TransactionDTO> transactions = transactionService.getTransactionsByDesc(params);
		return transactions;
	}
	
	@GetMapping("/rest/getTransactionsByCustomerId")
	public List<TransactionDTO> getTransactionsByCustomerId(@RequestParam String customerId, @RequestParam String startNum, @RequestParam String endNum) {
		Map<String, String> params = new HashMap<>();
		params.put("customerId", customerId);
		params.put("startNum", startNum);
		params.put("endNum", endNum);
		List<TransactionDTO> transactions = transactionService.getTransactionsByCustomerId(params);
		return transactions;
	}
}
