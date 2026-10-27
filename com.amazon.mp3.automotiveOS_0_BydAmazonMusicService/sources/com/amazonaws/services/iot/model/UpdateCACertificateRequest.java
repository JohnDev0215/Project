package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCACertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String newAutoRegistrationStatus;
    private String newStatus;
    private RegistrationConfig registrationConfig;
    private Boolean removeAutoRegistration;

    public String getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public UpdateCACertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public String getNewStatus() {
        return this.newStatus;
    }

    public void setNewStatus(String str) {
        this.newStatus = str;
    }

    public UpdateCACertificateRequest withNewStatus(String str) {
        this.newStatus = str;
        return this;
    }

    public void setNewStatus(CACertificateStatus cACertificateStatus) {
        this.newStatus = cACertificateStatus.toString();
    }

    public UpdateCACertificateRequest withNewStatus(CACertificateStatus cACertificateStatus) {
        this.newStatus = cACertificateStatus.toString();
        return this;
    }

    public String getNewAutoRegistrationStatus() {
        return this.newAutoRegistrationStatus;
    }

    public void setNewAutoRegistrationStatus(String str) {
        this.newAutoRegistrationStatus = str;
    }

    public UpdateCACertificateRequest withNewAutoRegistrationStatus(String str) {
        this.newAutoRegistrationStatus = str;
        return this;
    }

    public void setNewAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.newAutoRegistrationStatus = autoRegistrationStatus.toString();
    }

    public UpdateCACertificateRequest withNewAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.newAutoRegistrationStatus = autoRegistrationStatus.toString();
        return this;
    }

    public RegistrationConfig getRegistrationConfig() {
        return this.registrationConfig;
    }

    public void setRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
    }

    public UpdateCACertificateRequest withRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
        return this;
    }

    public Boolean isRemoveAutoRegistration() {
        return this.removeAutoRegistration;
    }

    public Boolean getRemoveAutoRegistration() {
        return this.removeAutoRegistration;
    }

    public void setRemoveAutoRegistration(Boolean bool) {
        this.removeAutoRegistration = bool;
    }

    public UpdateCACertificateRequest withRemoveAutoRegistration(Boolean bool) {
        this.removeAutoRegistration = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificateId() != null) {
            sb.append("certificateId: " + getCertificateId() + ",");
        }
        if (getNewStatus() != null) {
            sb.append("newStatus: " + getNewStatus() + ",");
        }
        if (getNewAutoRegistrationStatus() != null) {
            sb.append("newAutoRegistrationStatus: " + getNewAutoRegistrationStatus() + ",");
        }
        if (getRegistrationConfig() != null) {
            sb.append("registrationConfig: " + getRegistrationConfig() + ",");
        }
        if (getRemoveAutoRegistration() != null) {
            sb.append("removeAutoRegistration: " + getRemoveAutoRegistration());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getNewStatus() == null ? 0 : getNewStatus().hashCode())) * 31) + (getNewAutoRegistrationStatus() == null ? 0 : getNewAutoRegistrationStatus().hashCode())) * 31) + (getRegistrationConfig() == null ? 0 : getRegistrationConfig().hashCode())) * 31) + (getRemoveAutoRegistration() != null ? getRemoveAutoRegistration().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateCACertificateRequest)) {
            return false;
        }
        UpdateCACertificateRequest updateCACertificateRequest = (UpdateCACertificateRequest) obj;
        if ((updateCACertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getCertificateId() != null && !updateCACertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((updateCACertificateRequest.getNewStatus() == null) ^ (getNewStatus() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getNewStatus() != null && !updateCACertificateRequest.getNewStatus().equals(getNewStatus())) {
            return false;
        }
        if ((updateCACertificateRequest.getNewAutoRegistrationStatus() == null) ^ (getNewAutoRegistrationStatus() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getNewAutoRegistrationStatus() != null && !updateCACertificateRequest.getNewAutoRegistrationStatus().equals(getNewAutoRegistrationStatus())) {
            return false;
        }
        if ((updateCACertificateRequest.getRegistrationConfig() == null) ^ (getRegistrationConfig() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getRegistrationConfig() != null && !updateCACertificateRequest.getRegistrationConfig().equals(getRegistrationConfig())) {
            return false;
        }
        if ((updateCACertificateRequest.getRemoveAutoRegistration() == null) ^ (getRemoveAutoRegistration() == null)) {
            return false;
        }
        return updateCACertificateRequest.getRemoveAutoRegistration() == null || updateCACertificateRequest.getRemoveAutoRegistration().equals(getRemoveAutoRegistration());
    }
}
