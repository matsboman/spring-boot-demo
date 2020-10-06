package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvoiceId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "invoice_id")
    private String invoiceId;

    @Column(name = "supplier_id")
    private String supplierId;

    public InvoiceId() {
    }
    
    public InvoiceId(String invoiceId, String supplierId) {
        this.invoiceId = invoiceId;
        this.supplierId = supplierId;
    }

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceId that = (InvoiceId) o;
        return invoiceId.equals(that.invoiceId) &&
                supplierId.equals(that.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, supplierId);
    }
}
