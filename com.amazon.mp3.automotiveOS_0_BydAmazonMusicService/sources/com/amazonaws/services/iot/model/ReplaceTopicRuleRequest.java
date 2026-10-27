package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ReplaceTopicRuleRequest extends AmazonWebServiceRequest implements Serializable {
    private String ruleName;
    private TopicRulePayload topicRulePayload;

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public ReplaceTopicRuleRequest withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public TopicRulePayload getTopicRulePayload() {
        return this.topicRulePayload;
    }

    public void setTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
    }

    public ReplaceTopicRuleRequest withTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRuleName() != null) {
            sb.append("ruleName: " + getRuleName() + ",");
        }
        if (getTopicRulePayload() != null) {
            sb.append("topicRulePayload: " + getTopicRulePayload());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRuleName() == null ? 0 : getRuleName().hashCode()) + 31) * 31) + (getTopicRulePayload() != null ? getTopicRulePayload().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReplaceTopicRuleRequest)) {
            return false;
        }
        ReplaceTopicRuleRequest replaceTopicRuleRequest = (ReplaceTopicRuleRequest) obj;
        if ((replaceTopicRuleRequest.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (replaceTopicRuleRequest.getRuleName() != null && !replaceTopicRuleRequest.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((replaceTopicRuleRequest.getTopicRulePayload() == null) ^ (getTopicRulePayload() == null)) {
            return false;
        }
        return replaceTopicRuleRequest.getTopicRulePayload() == null || replaceTopicRuleRequest.getTopicRulePayload().equals(getTopicRulePayload());
    }
}
