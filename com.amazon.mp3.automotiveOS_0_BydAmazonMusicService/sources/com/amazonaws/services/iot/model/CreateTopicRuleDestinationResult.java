package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateTopicRuleDestinationResult implements Serializable {
    private TopicRuleDestination topicRuleDestination;

    public TopicRuleDestination getTopicRuleDestination() {
        return this.topicRuleDestination;
    }

    public void setTopicRuleDestination(TopicRuleDestination topicRuleDestination) {
        this.topicRuleDestination = topicRuleDestination;
    }

    public CreateTopicRuleDestinationResult withTopicRuleDestination(TopicRuleDestination topicRuleDestination) {
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
        if (obj == null || !(obj instanceof CreateTopicRuleDestinationResult)) {
            return false;
        }
        CreateTopicRuleDestinationResult createTopicRuleDestinationResult = (CreateTopicRuleDestinationResult) obj;
        if ((createTopicRuleDestinationResult.getTopicRuleDestination() == null) ^ (getTopicRuleDestination() == null)) {
            return false;
        }
        return createTopicRuleDestinationResult.getTopicRuleDestination() == null || createTopicRuleDestinationResult.getTopicRuleDestination().equals(getTopicRuleDestination());
    }
}
