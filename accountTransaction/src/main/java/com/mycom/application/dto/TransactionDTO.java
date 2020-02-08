package com.mycom.application.dto;

import java.util.Date;

public class TransactionDTO {
	private Long accountNo;
	private Long amount;
	private String description;
	private String trxDate;
	private String trxTime;
	private Long customerId;
	
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount/100;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	public String getTrxTime() {
		return trxTime;
	}
	public void setTrxTime(String trxTime) {
		this.trxTime = trxTime;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
