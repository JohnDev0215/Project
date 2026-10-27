package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private String arn;

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public GetTopicRuleDestinationRequest withArn(String str) {
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
        if (obj == null || !(obj instanceof GetTopicRuleDestinationRequest)) {
            return false;
        }
        GetTopicRuleDestinationRequest getTopicRuleDestinationRequest = (GetTopicRuleDestinationRequest) obj;
        if ((getTopicRuleDestinationRequest.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return getTopicRuleDestinationRequest.getArn() == null || getTopicRuleDestinationRequest.getArn().equals(getArn());
    }
}
