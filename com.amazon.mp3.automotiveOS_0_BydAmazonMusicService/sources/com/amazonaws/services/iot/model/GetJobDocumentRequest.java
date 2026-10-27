package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetJobDocumentRequest extends AmazonWebServiceRequest implements Serializable {
    private String jobId;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public GetJobDocumentRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getJobId() == null ? 0 : getJobId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetJobDocumentRequest)) {
            return false;
        }
        GetJobDocumentRequest getJobDocumentRequest = (GetJobDocumentRequest) obj;
        if ((getJobDocumentRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        return getJobDocumentRequest.getJobId() == null || getJobDocumentRequest.getJobId().equals(getJobId());
    }
}
