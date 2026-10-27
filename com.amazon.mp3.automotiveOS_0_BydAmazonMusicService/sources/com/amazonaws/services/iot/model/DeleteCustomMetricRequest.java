package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteCustomMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private String metricName;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public DeleteCustomMetricRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getMetricName() == null ? 0 : getMetricName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteCustomMetricRequest)) {
            return false;
        }
        DeleteCustomMetricRequest deleteCustomMetricRequest = (DeleteCustomMetricRequest) obj;
        if ((deleteCustomMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        return deleteCustomMetricRequest.getMetricName() == null || deleteCustomMetricRequest.getMetricName().equals(getMetricName());
    }
}
