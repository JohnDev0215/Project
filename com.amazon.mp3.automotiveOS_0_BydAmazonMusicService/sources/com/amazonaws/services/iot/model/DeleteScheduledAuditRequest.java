package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String scheduledAuditName;

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public DeleteScheduledAuditRequest withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getScheduledAuditName() != null) {
            sb.append("scheduledAuditName: " + getScheduledAuditName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteScheduledAuditRequest)) {
            return false;
        }
        DeleteScheduledAuditRequest deleteScheduledAuditRequest = (DeleteScheduledAuditRequest) obj;
        if ((deleteScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        return deleteScheduledAuditRequest.getScheduledAuditName() == null || deleteScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName());
    }
}
