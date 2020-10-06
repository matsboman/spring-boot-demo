package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

import com.example.demo.model.SupplierSummary;
import com.example.demo.repository.InvoiceRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class InvoiceServiceTest {
    @Autowired
    InvoiceService invoiceService;

	@MockBean
	InvoiceRepository invoiceRepository;

	@Test
	void listSupplierSummaryTest() {     
       
        String supplierId = "supplier_1";
		when(invoiceRepository.findTotalInvoices(supplierId)).thenReturn(234L);
		when(invoiceRepository.findOpenInvoices(supplierId)).thenReturn(20L);
		when(invoiceRepository.findLateInvoices(supplierId)).thenReturn(10L);
		when(invoiceRepository.findOpenInvoiceAmount(supplierId)).thenReturn(300.33);
		when(invoiceRepository.findOpenPaymentAmount(supplierId)).thenReturn(100.00);
		when(invoiceRepository.findLateInvoiceAmount(supplierId)).thenReturn(100032.34);
				
		SupplierSummary supplierSummary = invoiceService.listSupplierSummary("supplier_1");
        
        assertEquals(10L, supplierSummary.getLateInvoices());
        assertEquals(20L, supplierSummary.getOpenInvoices());
        assertEquals(234L, supplierSummary.getTotalInvoices());
        assertEquals(100032.34, supplierSummary.getTotalLateInvoiceAmount());
        assertEquals(300.33-100.00, supplierSummary.getTotalOpenInvoiceAmount());
	}
}
