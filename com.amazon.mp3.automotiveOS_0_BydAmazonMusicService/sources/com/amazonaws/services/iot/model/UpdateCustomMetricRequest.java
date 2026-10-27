package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCustomMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private String displayName;
    private String metricName;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public UpdateCustomMetricRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public UpdateCustomMetricRequest withDisplayName(String str) {
        this.displayName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getDisplayName() != null) {
            sb.append("displayName: " + getDisplayName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getDisplayName() != null ? getDisplayName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateCustomMetricRequest)) {
            return false;
        }
        UpdateCustomMetricRequest updateCustomMetricRequest = (UpdateCustomMetricRequest) obj;
        if ((updateCustomMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (updateCustomMetricRequest.getMetricName() != null && !updateCustomMetricRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((updateCustomMetricRequest.getDisplayName() == null) ^ (getDisplayName() == null)) {
            return false;
        }
        return updateCustomMetricRequest.getDisplayName() == null || updateCustomMetricRequest.getDisplayName().equals(getDisplayName());
    }
}
