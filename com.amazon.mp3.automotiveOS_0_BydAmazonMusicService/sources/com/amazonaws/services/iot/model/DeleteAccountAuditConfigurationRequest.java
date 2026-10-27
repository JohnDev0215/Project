package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteAccountAuditConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean deleteScheduledAudits;

    public Boolean isDeleteScheduledAudits() {
        return this.deleteScheduledAudits;
    }

    public Boolean getDeleteScheduledAudits() {
        return this.deleteScheduledAudits;
    }

    public void setDeleteScheduledAudits(Boolean bool) {
        this.deleteScheduledAudits = bool;
    }

    public DeleteAccountAuditConfigurationRequest withDeleteScheduledAudits(Boolean bool) {
        this.deleteScheduledAudits = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDeleteScheduledAudits() != null) {
            sb.append("deleteScheduledAudits: " + getDeleteScheduledAudits());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getDeleteScheduledAudits() == null ? 0 : getDeleteScheduledAudits().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteAccountAuditConfigurationRequest)) {
            return false;
        }
        DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest = (DeleteAccountAuditConfigurationRequest) obj;
        if ((deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits() == null) ^ (getDeleteScheduledAudits() == null)) {
            return false;
        }
        return deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits() == null || deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits().equals(getDeleteScheduledAudits());
    }
}
