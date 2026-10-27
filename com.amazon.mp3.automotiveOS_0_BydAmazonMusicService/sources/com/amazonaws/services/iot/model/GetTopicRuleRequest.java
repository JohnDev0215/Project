package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleRequest extends AmazonWebServiceRequest implements Serializable {
    private String ruleName;

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public GetTopicRuleRequest withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRuleName() != null) {
            sb.append("ruleName: " + getRuleName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getRuleName() == null ? 0 : getRuleName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetTopicRuleRequest)) {
            return false;
        }
        GetTopicRuleRequest getTopicRuleRequest = (GetTopicRuleRequest) obj;
        if ((getTopicRuleRequest.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        return getTopicRuleRequest.getRuleName() == null || getTopicRuleRequest.getRuleName().equals(getRuleName());
    }
}
