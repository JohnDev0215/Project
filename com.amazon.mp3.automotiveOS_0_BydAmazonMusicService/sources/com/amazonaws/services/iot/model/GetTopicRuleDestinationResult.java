package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleDestinationResult implements Serializable {
    private TopicRuleDestination topicRuleDestination;

    public TopicRuleDestination getTopicRuleDestination() {
        return this.topicRuleDestination;
    }

    public void setTopicRuleDestination(TopicRuleDestination topicRuleDestination) {
        this.topicRuleDestination = topicRuleDestination;
    }

    public GetTopicRuleDestinationResult withTopicRuleDestination(TopicRuleDestination topicRuleDestination) {
        this.topicRuleDestination = topicRuleDestination;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTopicRuleDestination() != null) {
            sb.append("topicRuleDestination: " + getTopicRuleDestination());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTopicRuleDestination() == null ? 0 : getTopicRuleDestination().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetTopicRuleDestinationResult)) {
            return false;
        }
        GetTopicRuleDestinationResult getTopicRuleDestinationResult = (GetTopicRuleDestinationResult) obj;
        if ((getTopicRuleDestinationResult.getTopicRuleDestination() == null) ^ (getTopicRuleDestination() == null)) {
            return false;
        }
        return getTopicRuleDestinationResult.getTopicRuleDestination() == null || getTopicRuleDestinationResult.getTopicRuleDestination().equals(getTopicRuleDestination());
    }
}
