package com.example.demo.entity;

import java.util.Date;

/**
 * class để lưu thông tin giao dịch
 * @author nguyễn duy hoài lâm
 */
public class Transaction {
	/**
	 * mã api sử dụng
	 */
	private String command;
	/**
	 * chủ tài khoản
	 */
	private String owner;
	/**
	 * mã thẻ
	 */
	private String cardCode;
	/**
	 * mã cvv
	 */
	private String cvvCode;
	/**
	 * ngày hết hạn
	 */
	private String dateExpired;
	/**
	 * nội dụng giao dịch
	 */
	private String transactionContent;
	/**
	 * số tiền giao dịch
	 */
	private String amount;
	/**
	 * thời điểm tạo giao dịch
	 */
	private Date createdAt;


	public String getTransactionContent () {
		return transactionContent;
	}

	public void setTransactionContent (String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public String getAmount () {
		return amount;
	}

	public void setAmount (String amount) {
		this.amount = amount;
	}

	public Date getCreatedAt () {
		return createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCommand () {
		return command;
	}

	public void setCommand (String command) {
		this.command = command;
	}

	public String getOwner () {
		return owner;
	}

	public void setOwner (String owner) {
		this.owner = owner;
	}

	public String getCardCode () {
		return cardCode;
	}

	public void setCardCode (String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCvvCode () {
		return cvvCode;
	}

	public void setCvvCode (String cvvCode) {
		this.cvvCode = cvvCode;
	}

	public String getDateExpired () {
		return dateExpired;
	}

	public void setDateExpired (String dateExpired) {
		this.dateExpired = dateExpired;
	}

}
