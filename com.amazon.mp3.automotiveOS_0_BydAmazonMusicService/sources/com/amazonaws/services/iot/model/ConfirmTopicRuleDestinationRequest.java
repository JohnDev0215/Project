package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmTopicRuleDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private String confirmationToken;

    public String getConfirmationToken() {
        return this.confirmationToken;
    }

    public void setConfirmationToken(String str) {
        this.confirmationToken = str;
    }

    public ConfirmTopicRuleDestinationRequest withConfirmationToken(String str) {
        this.confirmationToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getConfirmationToken() != null) {
            sb.append("confirmationToken: " + getConfirmationToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getConfirmationToken() == null ? 0 : getConfirmationToken().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ConfirmTopicRuleDestinationRequest)) {
            return false;
        }
        ConfirmTopicRuleDestinationRequest confirmTopicRuleDestinationRequest = (ConfirmTopicRuleDestinationRequest) obj;
        if ((confirmTopicRuleDestinationRequest.getConfirmationToken() == null) ^ (getConfirmationToken() == null)) {
            return false;
        }
        return confirmTopicRuleDestinationRequest.getConfirmationToken() == null || confirmTopicRuleDestinationRequest.getConfirmationToken().equals(getConfirmationToken());
    }
}
