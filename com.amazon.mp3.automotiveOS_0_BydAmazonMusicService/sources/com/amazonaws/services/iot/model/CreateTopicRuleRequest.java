package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateTopicRuleRequest extends AmazonWebServiceRequest implements Serializable {
    private String ruleName;
    private String tags;
    private TopicRulePayload topicRulePayload;

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public CreateTopicRuleRequest withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public TopicRulePayload getTopicRulePayload() {
        return this.topicRulePayload;
    }

    public void setTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
    }

    public CreateTopicRuleRequest withTopicRulePayload(TopicRulePayload topicRulePayload) {
        this.topicRulePayload = topicRulePayload;
        return this;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public CreateTopicRuleRequest withTags(String str) {
        this.tags = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRuleName() != null) {
            sb.append("ruleName: " + getRuleName() + ",");
        }
        if (getTopicRulePayload() != null) {
            sb.append("topicRulePayload: " + getTopicRulePayload() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRuleName() == null ? 0 : getRuleName().hashCode()) + 31) * 31) + (getTopicRulePayload() == null ? 0 : getTopicRulePayload().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTopicRuleRequest)) {
            return false;
        }
        CreateTopicRuleRequest createTopicRuleRequest = (CreateTopicRuleRequest) obj;
        if ((createTopicRuleRequest.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (createTopicRuleRequest.getRuleName() != null && !createTopicRuleRequest.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((createTopicRuleRequest.getTopicRulePayload() == null) ^ (getTopicRulePayload() == null)) {
            return false;
        }
        if (createTopicRuleRequest.getTopicRulePayload() != null && !createTopicRuleRequest.getTopicRulePayload().equals(getTopicRulePayload())) {
            return false;
        }
        if ((createTopicRuleRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createTopicRuleRequest.getTags() == null || createTopicRuleRequest.getTags().equals(getTags());
    }
}
