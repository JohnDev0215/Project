package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String scheduledAuditName;

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public DescribeScheduledAuditRequest withScheduledAuditName(String str) {
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
        if (obj == null || !(obj instanceof DescribeScheduledAuditRequest)) {
            return false;
        }
        DescribeScheduledAuditRequest describeScheduledAuditRequest = (DescribeScheduledAuditRequest) obj;
        if ((describeScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        return describeScheduledAuditRequest.getScheduledAuditName() == null || describeScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName());
    }
}
