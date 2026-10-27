package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateTopicRuleDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private String arn;
    private String status;

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public UpdateTopicRuleDestinationRequest withArn(String str) {
        this.arn = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public UpdateTopicRuleDestinationRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(TopicRuleDestinationStatus topicRuleDestinationStatus) {
        this.status = topicRuleDestinationStatus.toString();
    }

    public UpdateTopicRuleDestinationRequest withStatus(TopicRuleDestinationStatus topicRuleDestinationStatus) {
        this.status = topicRuleDestinationStatus.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getArn() != null) {
            sb.append("arn: " + getArn() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getArn() == null ? 0 : getArn().hashCode()) + 31) * 31) + (getStatus() != null ? getStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateTopicRuleDestinationRequest)) {
            return false;
        }
        UpdateTopicRuleDestinationRequest updateTopicRuleDestinationRequest = (UpdateTopicRuleDestinationRequest) obj;
        if ((updateTopicRuleDestinationRequest.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (updateTopicRuleDestinationRequest.getArn() != null && !updateTopicRuleDestinationRequest.getArn().equals(getArn())) {
            return false;
        }
        if ((updateTopicRuleDestinationRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return updateTopicRuleDestinationRequest.getStatus() == null || updateTopicRuleDestinationRequest.getStatus().equals(getStatus());
    }
}
