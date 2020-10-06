package com.example.demo.model;

public class SupplierSummary {
	Long totalInvoices;
	Long openInvoices;
	Long lateInvoices;
	Double totalOpenInvoiceAmount;
	Double totalLateInvoiceAmount;
	
	public Long getTotalInvoices() {
		return totalInvoices;
	}
	public void setTotalInvoices(Long totalInvoices) {
		this.totalInvoices = totalInvoices;
	}
	public Long getOpenInvoices() {
		return openInvoices;
	}
	public void setOpenInvoices(Long openInvoices) {
		this.openInvoices = openInvoices;
	}
	public Long getLateInvoices() {
		return lateInvoices;
	}
	public void setLateInvoices(Long lateInvoices) {
		this.lateInvoices = lateInvoices;
	}
	public Double getTotalOpenInvoiceAmount() {
		return totalOpenInvoiceAmount;
	}
	public void setTotalOpenInvoiceAmount(Double totalOpenInvoiceAmount) {
		this.totalOpenInvoiceAmount = totalOpenInvoiceAmount;
	}
	public Double getTotalLateInvoiceAmount() {
		return totalLateInvoiceAmount;
	}
	public void setTotalLateInvoiceAmount(Double totalLateInvoiceAmount) {
		this.totalLateInvoiceAmount = totalLateInvoiceAmount;
	}
}
