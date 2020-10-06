package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.CSVHelper;
import com.example.demo.model.Invoice;
import com.example.demo.repository.InvoiceRepository;

@Service
public class CSVService {
  @Autowired
  InvoiceRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Invoice> tutorials = CSVHelper.csvToInvoices(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("Failed to store csv data: " + e.getMessage());
    }
  }
}