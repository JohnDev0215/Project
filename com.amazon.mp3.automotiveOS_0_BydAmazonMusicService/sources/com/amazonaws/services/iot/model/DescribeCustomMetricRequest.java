package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCustomMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private String metricName;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public DescribeCustomMetricRequest withMetricName(String str) {
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
        if (obj == null || !(obj instanceof DescribeCustomMetricRequest)) {
            return false;
        }
        DescribeCustomMetricRequest describeCustomMetricRequest = (DescribeCustomMetricRequest) obj;
        if ((describeCustomMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        return describeCustomMetricRequest.getMetricName() == null || describeCustomMetricRequest.getMetricName().equals(getMetricName());
    }
}
