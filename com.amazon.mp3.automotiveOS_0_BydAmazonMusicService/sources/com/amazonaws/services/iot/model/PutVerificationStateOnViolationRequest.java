package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PutVerificationStateOnViolationRequest extends AmazonWebServiceRequest implements Serializable {
    private String verificationState;
    private String verificationStateDescription;
    private String violationId;

    public String getViolationId() {
        return this.violationId;
    }

    public void setViolationId(String str) {
        this.violationId = str;
    }

    public PutVerificationStateOnViolationRequest withViolationId(String str) {
        this.violationId = str;
        return this;
    }

    public String getVerificationState() {
        return this.verificationState;
    }

    public void setVerificationState(String str) {
        this.verificationState = str;
    }

    public PutVerificationStateOnViolationRequest withVerificationState(String str) {
        this.verificationState = str;
        return this;
    }

    public void setVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
    }

    public PutVerificationStateOnViolationRequest withVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
        return this;
    }

    public String getVerificationStateDescription() {
        return this.verificationStateDescription;
    }

    public void setVerificationStateDescription(String str) {
        this.verificationStateDescription = str;
    }

    public PutVerificationStateOnViolationRequest withVerificationStateDescription(String str) {
        this.verificationStateDescription = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getViolationId() != null) {
            sb.append("violationId: " + getViolationId() + ",");
        }
        if (getVerificationState() != null) {
            sb.append("verificationState: " + getVerificationState() + ",");
        }
        if (getVerificationStateDescription() != null) {
            sb.append("verificationStateDescription: " + getVerificationStateDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getViolationId() == null ? 0 : getViolationId().hashCode()) + 31) * 31) + (getVerificationState() == null ? 0 : getVerificationState().hashCode())) * 31) + (getVerificationStateDescription() != null ? getVerificationStateDescription().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutVerificationStateOnViolationRequest)) {
            return false;
        }
        PutVerificationStateOnViolationRequest putVerificationStateOnViolationRequest = (PutVerificationStateOnViolationRequest) obj;
        if ((putVerificationStateOnViolationRequest.getViolationId() == null) ^ (getViolationId() == null)) {
            return false;
        }
        if (putVerificationStateOnViolationRequest.getViolationId() != null && !putVerificationStateOnViolationRequest.getViolationId().equals(getViolationId())) {
            return false;
        }
        if ((putVerificationStateOnViolationRequest.getVerificationState() == null) ^ (getVerificationState() == null)) {
            return false;
        }
        if (putVerificationStateOnViolationRequest.getVerificationState() != null && !putVerificationStateOnViolationRequest.getVerificationState().equals(getVerificationState())) {
            return false;
        }
        if ((putVerificationStateOnViolationRequest.getVerificationStateDescription() == null) ^ (getVerificationStateDescription() == null)) {
            return false;
        }
        return putVerificationStateOnViolationRequest.getVerificationStateDescription() == null || putVerificationStateOnViolationRequest.getVerificationStateDescription().equals(getVerificationStateDescription());
    }
}
