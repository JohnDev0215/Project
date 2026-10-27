package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditFindingRequest extends AmazonWebServiceRequest implements Serializable {
    private String findingId;

    public String getFindingId() {
        return this.findingId;
    }

    public void setFindingId(String str) {
        this.findingId = str;
    }

    public DescribeAuditFindingRequest withFindingId(String str) {
        this.findingId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFindingId() != null) {
            sb.append("findingId: " + getFindingId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getFindingId() == null ? 0 : getFindingId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditFindingRequest)) {
            return false;
        }
        DescribeAuditFindingRequest describeAuditFindingRequest = (DescribeAuditFindingRequest) obj;
        if ((describeAuditFindingRequest.getFindingId() == null) ^ (getFindingId() == null)) {
            return false;
        }
        return describeAuditFindingRequest.getFindingId() == null || describeAuditFindingRequest.getFindingId().equals(getFindingId());
    }
}
