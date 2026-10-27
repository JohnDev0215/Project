package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteFleetMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private String metricName;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public DeleteFleetMetricRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public DeleteFleetMetricRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteFleetMetricRequest)) {
            return false;
        }
        DeleteFleetMetricRequest deleteFleetMetricRequest = (DeleteFleetMetricRequest) obj;
        if ((deleteFleetMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (deleteFleetMetricRequest.getMetricName() != null && !deleteFleetMetricRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((deleteFleetMetricRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteFleetMetricRequest.getExpectedVersion() == null || deleteFleetMetricRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
