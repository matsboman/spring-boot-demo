package com.example.demo.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Invoice;
import com.example.demo.model.InvoiceId;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Supplier Id", "Invoice Id", "Invoice Date", "Invoice Amount", "Terms", "Payment Date",
			"Payment Amount" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}


	public static List<Invoice> csvToInvoices(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
						.withIgnoreHeaderCase().withTrim().withNullString(""));) {

			List<Invoice> Invoices = new ArrayList<Invoice>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			Map<String, String> entryMap = new HashMap<String, String>();

			for (CSVRecord csvRecord : csvRecords) {
				Invoice invoice = new Invoice();
				invoice.setInvoiceId(new InvoiceId(csvRecord.get("Invoice Id"), csvRecord.get("Supplier Id")));
				
				if (!entryMap.containsKey(csvRecord.get("Invoice Id") + csvRecord.get("Supplier Id"))) {
					entryMap.put(csvRecord.get("Invoice Id") + csvRecord.get("Supplier Id"), ""); 
				} else {
					throw new IOException("CSV file contains duplicates!");
				}
				
				invoice.setInvoiceDate(java.sql.Date.valueOf(csvRecord.get("Invoice Date")));
				invoice.setInvoiceAmount(Double.parseDouble(csvRecord.get("Invoice Amount")));
				invoice.setTerms(Integer.valueOf(csvRecord.get("Terms")));
				if (csvRecord.get("Payment Date") != null) {
					invoice.setPaymentDate(java.sql.Date.valueOf(csvRecord.get("Payment Date")));
				}
				if (csvRecord.get("Payment Amount") != null) {
					invoice.setPaymentAmount(Double.parseDouble(csvRecord.get("Payment Amount")));
				}
				Invoices.add(invoice);
			}

			return Invoices;
		} catch (IOException e) {
			throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
		}
	}
}