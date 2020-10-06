package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Invoice;
import com.example.demo.model.InvoiceId;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, InvoiceId>{
	
	@Query(value = "select count(*) from invoices where supplier_id=:supplierId", nativeQuery = true)
	public Long findTotalInvoices(@Param("supplierId") String supplierId);
	
	@Query(value = "select count(*) from invoices where supplier_id=:supplierId and (payment_amount is null or payment_amount < invoice_amount) and CURRENT_DATE <= ADDDATE(invoice_date, terms)", nativeQuery = true)
	public Long findOpenInvoices(@Param("supplierId") String supplierId);
	
	@Query(value = "select sum(invoice_amount) from invoices where supplier_id=:supplierId and (payment_amount is null or payment_amount < invoice_amount) and CURRENT_DATE <= ADDDATE(invoice_date, terms)", nativeQuery = true)
	public Double findOpenInvoiceAmount(@Param("supplierId") String supplierId);
	
	@Query(value = "select sum(payment_amount) from invoices where supplier_id=:supplierId and (payment_amount is null or payment_amount < invoice_amount) and CURRENT_DATE <= ADDDATE(invoice_date, terms)", nativeQuery = true)
	public Double findOpenPaymentAmount(@Param("supplierId") String supplierId);
	
	@Query(value = "select count(*) from invoices where supplier_id=:supplierId and payment_amount is null and ADDDATE(invoice_date, terms) < CURRENT_DATE", nativeQuery = true)
	public Long findLateInvoices(@Param("supplierId") String supplierId);
	
	@Query(value = "select sum(invoice_amount) from invoices where supplier_id=:supplierId and payment_amount is null and ADDDATE(invoice_date, terms) < CURRENT_DATE", nativeQuery = true)
	public Double findLateInvoiceAmount(@Param("supplierId") String supplierId);
}