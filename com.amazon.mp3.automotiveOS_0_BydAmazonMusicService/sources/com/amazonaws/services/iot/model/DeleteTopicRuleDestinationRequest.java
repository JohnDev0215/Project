package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteTopicRuleDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private String arn;

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public DeleteTopicRuleDestinationRequest withArn(String str) {
        this.arn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getArn() != null) {
            sb.append("arn: " + getArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getArn() == null ? 0 : getArn().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteTopicRuleDestinationRequest)) {
            return false;
        }
        DeleteTopicRuleDestinationRequest deleteTopicRuleDestinationRequest = (DeleteTopicRuleDestinationRequest) obj;
        if ((deleteTopicRuleDestinationRequest.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return deleteTopicRuleDestinationRequest.getArn() == null || deleteTopicRuleDestinationRequest.getArn().equals(getArn());
    }
}
