package com.mycom.application.dto;

import java.io.Serializable;


public class AuthMemberDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -3423640950937524435L;
	private String memId;
	private String memNo;
	private String memStatCd;
	private String memTypCd;
	private String memScrtNo;
	private Long loginFailCnt;
	private String loginFailDate;
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemStatCd() {
		return memStatCd;
	}
	public void setMemStatCd(String memStatCd) {
		this.memStatCd = memStatCd;
	}
	public String getMemTypCd() {
		return memTypCd;
	}
	public void setMemTypCd(String memTypCd) {
		this.memTypCd = memTypCd;
	}
	public String getMemScrtNo() {
		return memScrtNo;
	}
	public void setMemScrtNo(String memScrtNo) {
		this.memScrtNo = memScrtNo;
	}
	public Long getLoginFailCnt() {
		return loginFailCnt;
	}
	public void setLoginFailCnt(Long loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	public String getLoginFailDate() {
		return loginFailDate;
	}
	public void setLoginFailDate(String loginFailDate) {
		this.loginFailDate = loginFailDate;
	}
	
}
