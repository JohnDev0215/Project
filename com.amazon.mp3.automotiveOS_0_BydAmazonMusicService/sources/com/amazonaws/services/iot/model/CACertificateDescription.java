package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CACertificateDescription implements Serializable {
    private String autoRegistrationStatus;
    private String certificateArn;
    private String certificateId;
    private String certificateMode;
    private String certificatePem;
    private Date creationDate;
    private Integer customerVersion;
    private String generationId;
    private Date lastModifiedDate;
    private String ownedBy;
    private String status;
    private CertificateValidity validity;

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public CACertificateDescription withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public CACertificateDescription withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public CACertificateDescription withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
    }

    public CACertificateDescription withStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
        return this;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public CACertificateDescription withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public String getOwnedBy() {
        return this.ownedBy;
    }

    public void setOwnedBy(String str) {
        this.ownedBy = str;
    }

    public CACertificateDescription withOwnedBy(String str) {
        this.ownedBy = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public CACertificateDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String getAutoRegistrationStatus() {
        return this.autoRegistrationStatus;
    }

    public void setAutoRegistrationStatus(String str) {
        this.autoRegistrationStatus = str;
    }

    public CACertificateDescription withAutoRegistrationStatus(String str) {
        this.autoRegistrationStatus = str;
        return this;
    }

    public void setAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.autoRegistrationStatus = autoRegistrationStatus.toString();
    }

    public CACertificateDescription withAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.autoRegistrationStatus = autoRegistrationStatus.toString();
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public CACertificateDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public Integer getCustomerVersion() {
        return this.customerVersion;
    }

    public void setCustomerVersion(Integer num) {
        this.customerVersion = num;
    }

    public CACertificateDescription withCustomerVersion(Integer num) {
        this.customerVersion = num;
        return this;
    }

    public String getGenerationId() {
        return this.generationId;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
    }

    public CACertificateDescription withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public CertificateValidity getValidity() {
        return this.validity;
    }

    public void setValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
    }

    public CACertificateDescription withValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
        return this;
    }

    public String getCertificateMode() {
        return this.certificateMode;
    }

    public void setCertificateMode(String str) {
        this.certificateMode = str;
    }

    public CACertificateDescription withCertificateMode(String str) {
        this.certificateMode = str;
        return this;
    }

    public void setCertificateMode(CertificateMode certificateMode) {
        this.certificateMode = certificateMode.toString();
    }

    public CACertificateDescription withCertificateMode(CertificateMode certificateMode) {
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
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getOwnedBy() != null) {
            sb.append("ownedBy: " + getOwnedBy() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getAutoRegistrationStatus() != null) {
            sb.append("autoRegistrationStatus: " + getAutoRegistrationStatus() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getCustomerVersion() != null) {
            sb.append("customerVersion: " + getCustomerVersion() + ",");
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
        return (((((((((((((((((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31) + (getOwnedBy() == null ? 0 : getOwnedBy().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getAutoRegistrationStatus() == null ? 0 : getAutoRegistrationStatus().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getCustomerVersion() == null ? 0 : getCustomerVersion().hashCode())) * 31) + (getGenerationId() == null ? 0 : getGenerationId().hashCode())) * 31) + (getValidity() == null ? 0 : getValidity().hashCode())) * 31) + (getCertificateMode() != null ? getCertificateMode().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CACertificateDescription)) {
            return false;
        }
        CACertificateDescription cACertificateDescription = (CACertificateDescription) obj;
        if ((cACertificateDescription.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (cACertificateDescription.getCertificateArn() != null && !cACertificateDescription.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((cACertificateDescription.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (cACertificateDescription.getCertificateId() != null && !cACertificateDescription.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((cACertificateDescription.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (cACertificateDescription.getStatus() != null && !cACertificateDescription.getStatus().equals(getStatus())) {
            return false;
        }
        if ((cACertificateDescription.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (cACertificateDescription.getCertificatePem() != null && !cACertificateDescription.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((cACertificateDescription.getOwnedBy() == null) ^ (getOwnedBy() == null)) {
            return false;
        }
        if (cACertificateDescription.getOwnedBy() != null && !cACertificateDescription.getOwnedBy().equals(getOwnedBy())) {
            return false;
        }
        if ((cACertificateDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (cACertificateDescription.getCreationDate() != null && !cACertificateDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((cACertificateDescription.getAutoRegistrationStatus() == null) ^ (getAutoRegistrationStatus() == null)) {
            return false;
        }
        if (cACertificateDescription.getAutoRegistrationStatus() != null && !cACertificateDescription.getAutoRegistrationStatus().equals(getAutoRegistrationStatus())) {
            return false;
        }
        if ((cACertificateDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (cACertificateDescription.getLastModifiedDate() != null && !cACertificateDescription.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((cACertificateDescription.getCustomerVersion() == null) ^ (getCustomerVersion() == null)) {
            return false;
        }
        if (cACertificateDescription.getCustomerVersion() != null && !cACertificateDescription.getCustomerVersion().equals(getCustomerVersion())) {
            return false;
        }
        if ((cACertificateDescription.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        if (cACertificateDescription.getGenerationId() != null && !cACertificateDescription.getGenerationId().equals(getGenerationId())) {
            return false;
        }
        if ((cACertificateDescription.getValidity() == null) ^ (getValidity() == null)) {
            return false;
        }
        if (cACertificateDescription.getValidity() != null && !cACertificateDescription.getValidity().equals(getValidity())) {
            return false;
        }
        if ((cACertificateDescription.getCertificateMode() == null) ^ (getCertificateMode() == null)) {
            return false;
        }
        return cACertificateDescription.getCertificateMode() == null || cACertificateDescription.getCertificateMode().equals(getCertificateMode());
    }
}
