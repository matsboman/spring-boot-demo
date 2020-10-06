package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.example.demo.exceptions.InvalidTransactionReferenceException;
import com.example.demo.model.Invoice;
import com.example.demo.model.InvoiceId;
import com.example.demo.model.SupplierSummary;
import com.example.demo.service.CSVService;
import com.example.demo.service.InvoiceService;
import com.example.demo.helper.CSVHelper;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	CSVService fileService;

	@PostMapping("/createInvoice")
	public InvoiceId createTransaction(@RequestBody Invoice invoice) {
		invoiceService.saveInvoice(invoice);
		return invoice.getInvoiceId();
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + ". " + e.getMessage() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	@GetMapping("/viewAllInvoices")
	public Iterable<Invoice> viewAllInvoices() {
		return invoiceService.getInvoices();
	}
	
	@GetMapping("/listSupplierSummary")
	public SupplierSummary listSupplierSummary(@RequestParam("supplierId") String supplierId) {
		return invoiceService.listSupplierSummary(supplierId);
	}
}
