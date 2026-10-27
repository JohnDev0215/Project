package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class TopicRuleDestinationSummary implements Serializable {
    private String arn;
    private Date createdAt;
    private HttpUrlDestinationSummary httpUrlSummary;
    private Date lastUpdatedAt;
    private String status;
    private String statusReason;
    private VpcDestinationSummary vpcDestinationSummary;

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public TopicRuleDestinationSummary withArn(String str) {
        this.arn = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public TopicRuleDestinationSummary withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(TopicRuleDestinationStatus topicRuleDestinationStatus) {
        this.status = topicRuleDestinationStatus.toString();
    }

    public TopicRuleDestinationSummary withStatus(TopicRuleDestinationStatus topicRuleDestinationStatus) {
        this.status = topicRuleDestinationStatus.toString();
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public TopicRuleDestinationSummary withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public TopicRuleDestinationSummary withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public String getStatusReason() {
        return this.statusReason;
    }

    public void setStatusReason(String str) {
        this.statusReason = str;
    }

    public TopicRuleDestinationSummary withStatusReason(String str) {
        this.statusReason = str;
        return this;
    }

    public HttpUrlDestinationSummary getHttpUrlSummary() {
        return this.httpUrlSummary;
    }

    public void setHttpUrlSummary(HttpUrlDestinationSummary httpUrlDestinationSummary) {
        this.httpUrlSummary = httpUrlDestinationSummary;
    }

    public TopicRuleDestinationSummary withHttpUrlSummary(HttpUrlDestinationSummary httpUrlDestinationSummary) {
        this.httpUrlSummary = httpUrlDestinationSummary;
        return this;
    }

    public VpcDestinationSummary getVpcDestinationSummary() {
        return this.vpcDestinationSummary;
    }

    public void setVpcDestinationSummary(VpcDestinationSummary vpcDestinationSummary) {
        this.vpcDestinationSummary = vpcDestinationSummary;
    }

    public TopicRuleDestinationSummary withVpcDestinationSummary(VpcDestinationSummary vpcDestinationSummary) {
        this.vpcDestinationSummary = vpcDestinationSummary;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getArn() != null) {
            sb.append("arn: " + getArn() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getCreatedAt() != null) {
            sb.append("createdAt: " + getCreatedAt() + ",");
        }
        if (getLastUpdatedAt() != null) {
            sb.append("lastUpdatedAt: " + getLastUpdatedAt() + ",");
        }
        if (getStatusReason() != null) {
            sb.append("statusReason: " + getStatusReason() + ",");
        }
        if (getHttpUrlSummary() != null) {
            sb.append("httpUrlSummary: " + getHttpUrlSummary() + ",");
        }
        if (getVpcDestinationSummary() != null) {
            sb.append("vpcDestinationSummary: " + getVpcDestinationSummary());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getArn() == null ? 0 : getArn().hashCode()) + 31) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getStatusReason() == null ? 0 : getStatusReason().hashCode())) * 31) + (getHttpUrlSummary() == null ? 0 : getHttpUrlSummary().hashCode())) * 31) + (getVpcDestinationSummary() != null ? getVpcDestinationSummary().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRuleDestinationSummary)) {
            return false;
        }
        TopicRuleDestinationSummary topicRuleDestinationSummary = (TopicRuleDestinationSummary) obj;
        if ((topicRuleDestinationSummary.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (topicRuleDestinationSummary.getArn() != null && !topicRuleDestinationSummary.getArn().equals(getArn())) {
            return false;
        }
        if ((topicRuleDestinationSummary.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (topicRuleDestinationSummary.getStatus() != null && !topicRuleDestinationSummary.getStatus().equals(getStatus())) {
            return false;
        }
        if ((topicRuleDestinationSummary.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (topicRuleDestinationSummary.getCreatedAt() != null && !topicRuleDestinationSummary.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((topicRuleDestinationSummary.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (topicRuleDestinationSummary.getLastUpdatedAt() != null && !topicRuleDestinationSummary.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((topicRuleDestinationSummary.getStatusReason() == null) ^ (getStatusReason() == null)) {
            return false;
        }
        if (topicRuleDestinationSummary.getStatusReason() != null && !topicRuleDestinationSummary.getStatusReason().equals(getStatusReason())) {
            return false;
        }
        if ((topicRuleDestinationSummary.getHttpUrlSummary() == null) ^ (getHttpUrlSummary() == null)) {
            return false;
        }
        if (topicRuleDestinationSummary.getHttpUrlSummary() != null && !topicRuleDestinationSummary.getHttpUrlSummary().equals(getHttpUrlSummary())) {
            return false;
        }
        if ((topicRuleDestinationSummary.getVpcDestinationSummary() == null) ^ (getVpcDestinationSummary() == null)) {
            return false;
        }
        return topicRuleDestinationSummary.getVpcDestinationSummary() == null || topicRuleDestinationSummary.getVpcDestinationSummary().equals(getVpcDestinationSummary());
    }
}
