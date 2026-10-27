package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRulesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private Boolean ruleDisabled;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public ListTopicRulesRequest withTopic(String str) {
        this.topic = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListTopicRulesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTopicRulesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Boolean isRuleDisabled() {
        return this.ruleDisabled;
    }

    public Boolean getRuleDisabled() {
        return this.ruleDisabled;
    }

    public void setRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
    }

    public ListTopicRulesRequest withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTopic() != null) {
            sb.append("topic: " + getTopic() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getRuleDisabled() != null) {
            sb.append("ruleDisabled: " + getRuleDisabled());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTopic() == null ? 0 : getTopic().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getRuleDisabled() != null ? getRuleDisabled().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTopicRulesRequest)) {
            return false;
        }
        ListTopicRulesRequest listTopicRulesRequest = (ListTopicRulesRequest) obj;
        if ((listTopicRulesRequest.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (listTopicRulesRequest.getTopic() != null && !listTopicRulesRequest.getTopic().equals(getTopic())) {
            return false;
        }
        if ((listTopicRulesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listTopicRulesRequest.getMaxResults() != null && !listTopicRulesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listTopicRulesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listTopicRulesRequest.getNextToken() != null && !listTopicRulesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listTopicRulesRequest.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        return listTopicRulesRequest.getRuleDisabled() == null || listTopicRulesRequest.getRuleDisabled().equals(getRuleDisabled());
    }
}
