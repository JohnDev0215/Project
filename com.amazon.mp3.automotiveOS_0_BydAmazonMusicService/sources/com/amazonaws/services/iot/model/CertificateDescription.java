package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CertificateDescription implements Serializable {
    private String caCertificateId;
    private String certificateArn;
    private String certificateId;
    private String certificateMode;
    private String certificatePem;
    private Date creationDate;
    private Integer customerVersion;
    private String generationId;
    private Date lastModifiedDate;
    private String ownedBy;
    private String previousOwnedBy;
    private String status;
    private TransferData transferData;
    private CertificateValidity validity;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public CertificateDescription withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CertificateDescription withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getCaCertificateId() {
        return this.caCertificateId;
    }

    public void setCaCertificateId(String str) {
        this.caCertificateId = str;
    }

    public CertificateDescription withCaCertificateId(String str) {
        this.caCertificateId = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public CertificateDescription withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public CertificateDescription withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public CertificateDescription withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public String getOwnedBy() {
        return this.ownedBy;
    }

    public void setOwnedBy(String str) {
        this.ownedBy = str;
    }

    public CertificateDescription withOwnedBy(String str) {
        this.ownedBy = str;
        return this;
    }

    public String getPreviousOwnedBy() {
        return this.previousOwnedBy;
    }

    public void setPreviousOwnedBy(String str) {
        this.previousOwnedBy = str;
    }

    public CertificateDescription withPreviousOwnedBy(String str) {
        this.previousOwnedBy = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public CertificateDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public CertificateDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public Integer getCustomerVersion() {
        return this.customerVersion;
    }

    public void setCustomerVersion(Integer num) {
        this.customerVersion = num;
    }

    public CertificateDescription withCustomerVersion(Integer num) {
        this.customerVersion = num;
        return this;
    }

    public TransferData getTransferData() {
        return this.transferData;
    }

    public void setTransferData(TransferData transferData) {
        this.transferData = transferData;
    }

    public CertificateDescription withTransferData(TransferData transferData) {
        this.transferData = transferData;
        return this;
    }

    public String getGenerationId() {
        return this.generationId;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
    }

    public CertificateDescription withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public CertificateValidity getValidity() {
        return this.validity;
    }

    public void setValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
    }

    public CertificateDescription withValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
        return this;
    }

    public String getCertificateMode() {
        return this.certificateMode;
    }

    public void setCertificateMode(String str) {
        this.certificateMode = str;
    }

    public CertificateDescription withCertificateMode(String str) {
        this.certificateMode = str;
        return this;
    }

    public void setCertificateMode(CertificateMode certificateMode) {
        this.certificateMode = certificateMode.toString();
    }

    public CertificateDescription withCertificateMode(CertificateMode certificateMode) {
        this.certificateMode = certificateMode.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateArn() != null) {
            sb.append("certificateArn: " + getCertificateArn() + ",");
        }
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getCaCertificateId() != null) {
            sb.append("caCertificateId: " + getCaCertificateId() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getOwnedBy() != null) {
            sb.append("ownedBy: " + getOwnedBy() + ",");
        }
        if (getPreviousOwnedBy() != null) {
            sb.append("previousOwnedBy: " + getPreviousOwnedBy() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getCustomerVersion() != null) {
            sb.append("customerVersion: " + getCustomerVersion() + ",");
        }
        if (getTransferData() != null) {
            sb.append("transferData: " + getTransferData() + ",");
        }
        if (getGenerationId() != null) {
            sb.append("generationId: " + getGenerationId() + ",");
        }
        if (getValidity() != null) {
            sb.append("validity: " + getValidity() + ",");
        }
        if (getCertificateMode() != null) {
            sb.append("certificateMode: " + getCertificateMode());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getCaCertificateId() == null ? 0 : getCaCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31) + (getOwnedBy() == null ? 0 : getOwnedBy().hashCode())) * 31) + (getPreviousOwnedBy() == null ? 0 : getPreviousOwnedBy().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getCustomerVersion() == null ? 0 : getCustomerVersion().hashCode())) * 31) + (getTransferData() == null ? 0 : getTransferData().hashCode())) * 31) + (getGenerationId() == null ? 0 : getGenerationId().hashCode())) * 31) + (getValidity() == null ? 0 : getValidity().hashCode())) * 31) + (getCertificateMode() != null ? getCertificateMode().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CertificateDescription)) {
            return false;
        }
        CertificateDescription certificateDescription = (CertificateDescription) obj;
        if ((certificateDescription.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (certificateDescription.getCertificateArn() != null && !certificateDescription.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((certificateDescription.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (certificateDescription.getCertificateId() != null && !certificateDescription.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((certificateDescription.getCaCertificateId() == null) ^ (getCaCertificateId() == null)) {
            return false;
        }
        if (certificateDescription.getCaCertificateId() != null && !certificateDescription.getCaCertificateId().equals(getCaCertificateId())) {
            return false;
        }
        if ((certificateDescription.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (certificateDescription.getStatus() != null && !certificateDescription.getStatus().equals(getStatus())) {
            return false;
        }
        if ((certificateDescription.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (certificateDescription.getCertificatePem() != null && !certificateDescription.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((certificateDescription.getOwnedBy() == null) ^ (getOwnedBy() == null)) {
            return false;
        }
        if (certificateDescription.getOwnedBy() != null && !certificateDescription.getOwnedBy().equals(getOwnedBy())) {
            return false;
        }
        if ((certificateDescription.getPreviousOwnedBy() == null) ^ (getPreviousOwnedBy() == null)) {
            return false;
        }
        if (certificateDescription.getPreviousOwnedBy() != null && !certificateDescription.getPreviousOwnedBy().equals(getPreviousOwnedBy())) {
            return false;
        }
        if ((certificateDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (certificateDescription.getCreationDate() != null && !certificateDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((certificateDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (certificateDescription.getLastModifiedDate() != null && !certificateDescription.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((certificateDescription.getCustomerVersion() == null) ^ (getCustomerVersion() == null)) {
            return false;
        }
        if (certificateDescription.getCustomerVersion() != null && !certificateDescription.getCustomerVersion().equals(getCustomerVersion())) {
            return false;
        }
        if ((certificateDescription.getTransferData() == null) ^ (getTransferData() == null)) {
            return false;
        }
        if (certificateDescription.getTransferData() != null && !certificateDescription.getTransferData().equals(getTransferData())) {
            return false;
        }
        if ((certificateDescription.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        if (certificateDescription.getGenerationId() != null && !certificateDescription.getGenerationId().equals(getGenerationId())) {
            return false;
        }
        if ((certificateDescription.getValidity() == null) ^ (getValidity() == null)) {
            return false;
        }
        if (certificateDescription.getValidity() != null && !certificateDescription.getValidity().equals(getValidity())) {
            return false;
        }
        if ((certificateDescription.getCertificateMode() == null) ^ (getCertificateMode() == null)) {
            return false;
        }
        return certificateDescription.getCertificateMode() == null || certificateDescription.getCertificateMode().equals(getCertificateMode());
    }
}
