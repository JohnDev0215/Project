package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRuleDestinationsResult implements Serializable {
    private List<TopicRuleDestinationSummary> destinationSummaries;
    private String nextToken;

    public List<TopicRuleDestinationSummary> getDestinationSummaries() {
        return this.destinationSummaries;
    }

    public void setDestinationSummaries(Collection<TopicRuleDestinationSummary> collection) {
        if (collection == null) {
            this.destinationSummaries = null;
        } else {
            this.destinationSummaries = new ArrayList(collection);
        }
    }

    public ListTopicRuleDestinationsResult withDestinationSummaries(TopicRuleDestinationSummary... topicRuleDestinationSummaryArr) {
        if (getDestinationSummaries() == null) {
            this.destinationSummaries = new ArrayList(topicRuleDestinationSummaryArr.length);
        }
        for (TopicRuleDestinationSummary topicRuleDestinationSummary : topicRuleDestinationSummaryArr) {
            this.destinationSummaries.add(topicRuleDestinationSummary);
        }
        return this;
    }

    public ListTopicRuleDestinationsResult withDestinationSummaries(Collection<TopicRuleDestinationSummary> collection) {
        setDestinationSummaries(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTopicRuleDestinationsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDestinationSummaries() != null) {
            sb.append("destinationSummaries: " + getDestinationSummaries() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getDestinationSummaries() == null ? 0 : getDestinationSummaries().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTopicRuleDestinationsResult)) {
            return false;
        }
        ListTopicRuleDestinationsResult listTopicRuleDestinationsResult = (ListTopicRuleDestinationsResult) obj;
        if ((listTopicRuleDestinationsResult.getDestinationSummaries() == null) ^ (getDestinationSummaries() == null)) {
            return false;
        }
        if (listTopicRuleDestinationsResult.getDestinationSummaries() != null && !listTopicRuleDestinationsResult.getDestinationSummaries().equals(getDestinationSummaries())) {
            return false;
        }
        if ((listTopicRuleDestinationsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTopicRuleDestinationsResult.getNextToken() == null || listTopicRuleDestinationsResult.getNextToken().equals(getNextToken());
    }
}
