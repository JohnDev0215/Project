package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCustomMetricResult implements Serializable {
    private Date creationDate;
    private String displayName;
    private Date lastModifiedDate;
    private String metricArn;
    private String metricName;
    private String metricType;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public DescribeCustomMetricResult withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getMetricArn() {
        return this.metricArn;
    }

    public void setMetricArn(String str) {
        this.metricArn = str;
    }

    public DescribeCustomMetricResult withMetricArn(String str) {
        this.metricArn = str;
        return this;
    }

    public String getMetricType() {
        return this.metricType;
    }

    public void setMetricType(String str) {
        this.metricType = str;
    }

    public DescribeCustomMetricResult withMetricType(String str) {
        this.metricType = str;
        return this;
    }

    public void setMetricType(CustomMetricType customMetricType) {
        this.metricType = customMetricType.toString();
    }

    public DescribeCustomMetricResult withMetricType(CustomMetricType customMetricType) {
        this.metricType = customMetricType.toString();
        return this;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public DescribeCustomMetricResult withDisplayName(String str) {
        this.displayName = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeCustomMetricResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeCustomMetricResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getMetricArn() != null) {
            sb.append("metricArn: " + getMetricArn() + ",");
        }
        if (getMetricType() != null) {
            sb.append("metricType: " + getMetricType() + ",");
        }
        if (getDisplayName() != null) {
            sb.append("displayName: " + getDisplayName() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getMetricArn() == null ? 0 : getMetricArn().hashCode())) * 31) + (getMetricType() == null ? 0 : getMetricType().hashCode())) * 31) + (getDisplayName() == null ? 0 : getDisplayName().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeCustomMetricResult)) {
            return false;
        }
        DescribeCustomMetricResult describeCustomMetricResult = (DescribeCustomMetricResult) obj;
        if ((describeCustomMetricResult.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (describeCustomMetricResult.getMetricName() != null && !describeCustomMetricResult.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((describeCustomMetricResult.getMetricArn() == null) ^ (getMetricArn() == null)) {
            return false;
        }
        if (describeCustomMetricResult.getMetricArn() != null && !describeCustomMetricResult.getMetricArn().equals(getMetricArn())) {
            return false;
        }
        if ((describeCustomMetricResult.getMetricType() == null) ^ (getMetricType() == null)) {
            return false;
        }
        if (describeCustomMetricResult.getMetricType() != null && !describeCustomMetricResult.getMetricType().equals(getMetricType())) {
            return false;
        }
        if ((describeCustomMetricResult.getDisplayName() == null) ^ (getDisplayName() == null)) {
            return false;
        }
        if (describeCustomMetricResult.getDisplayName() != null && !describeCustomMetricResult.getDisplayName().equals(getDisplayName())) {
            return false;
        }
        if ((describeCustomMetricResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeCustomMetricResult.getCreationDate() != null && !describeCustomMetricResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeCustomMetricResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeCustomMetricResult.getLastModifiedDate() == null || describeCustomMetricResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
