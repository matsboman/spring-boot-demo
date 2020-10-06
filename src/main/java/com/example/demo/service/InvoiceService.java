package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Invoice;
import com.example.demo.model.InvoiceId;
import com.example.demo.model.SupplierSummary;
import com.example.demo.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
	
	public void saveInvoice(Invoice invoice) {
		invoiceRepository.save(invoice);
	}
	
	public Iterable<Invoice> getInvoices() {
		return invoiceRepository.findAll();
	}
	
	public Optional<Invoice> getInvoice(InvoiceId id) {
		return invoiceRepository.findById(id);
	}
	
	public SupplierSummary listSupplierSummary(String supplierId) {
		
		Long totalInvoices = invoiceRepository.findTotalInvoices(supplierId);
		Long openInvoices = invoiceRepository.findOpenInvoices(supplierId);
		Long lateInvoices = invoiceRepository.findLateInvoices(supplierId);
		Double openInvoiceAmount = invoiceRepository.findOpenInvoiceAmount(supplierId);
		Double openPaymentAmount = invoiceRepository.findOpenPaymentAmount(supplierId);
		Double lateInvoiceAmount = invoiceRepository.findLateInvoiceAmount(supplierId);

		SupplierSummary summary = new SupplierSummary();
		summary.setTotalInvoices(totalInvoices);
		summary.setOpenInvoices(openInvoices);
		summary.setLateInvoices(lateInvoices);
		summary.setTotalOpenInvoiceAmount(openInvoiceAmount - (openPaymentAmount == null ? 0.0d : openPaymentAmount));
		summary.setTotalLateInvoiceAmount(lateInvoiceAmount);
		return summary;
	}
}
