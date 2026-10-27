package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateTopicRuleDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private TopicRuleDestinationConfiguration destinationConfiguration;

    public TopicRuleDestinationConfiguration getDestinationConfiguration() {
        return this.destinationConfiguration;
    }

    public void setDestinationConfiguration(TopicRuleDestinationConfiguration topicRuleDestinationConfiguration) {
        this.destinationConfiguration = topicRuleDestinationConfiguration;
    }

    public CreateTopicRuleDestinationRequest withDestinationConfiguration(TopicRuleDestinationConfiguration topicRuleDestinationConfiguration) {
        this.destinationConfiguration = topicRuleDestinationConfiguration;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDestinationConfiguration() != null) {
            sb.append("destinationConfiguration: " + getDestinationConfiguration());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getDestinationConfiguration() == null ? 0 : getDestinationConfiguration().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTopicRuleDestinationRequest)) {
            return false;
        }
        CreateTopicRuleDestinationRequest createTopicRuleDestinationRequest = (CreateTopicRuleDestinationRequest) obj;
        if ((createTopicRuleDestinationRequest.getDestinationConfiguration() == null) ^ (getDestinationConfiguration() == null)) {
            return false;
        }
        return createTopicRuleDestinationRequest.getDestinationConfiguration() == null || createTopicRuleDestinationRequest.getDestinationConfiguration().equals(getDestinationConfiguration());
    }
}
