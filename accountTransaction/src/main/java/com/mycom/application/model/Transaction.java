package com.mycom.mybatis.model;

import java.io.Serializable;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 6892108059696826152L;
	
	private String accountNo;
	private String amount;
	private String description;
	private String trxDate;
	private String trxTime;
	private String customerId;
	private String trxTimestamp;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTrxTimestamp() {
		return trxTimestamp;
	}
	public void setTrxTimestamp(String trxTimestamp) {
		this.trxTimestamp = trxTimestamp;
	}
	
}
