package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class TopicRuleListItem implements Serializable {
    private Date createdAt;
    private String ruleArn;
    private Boolean ruleDisabled;
    private String ruleName;
    private String topicPattern;

    public String getRuleArn() {
        return this.ruleArn;
    }

    public void setRuleArn(String str) {
        this.ruleArn = str;
    }

    public TopicRuleListItem withRuleArn(String str) {
        this.ruleArn = str;
        return this;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public TopicRuleListItem withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public String getTopicPattern() {
        return this.topicPattern;
    }

    public void setTopicPattern(String str) {
        this.topicPattern = str;
    }

    public TopicRuleListItem withTopicPattern(String str) {
        this.topicPattern = str;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public TopicRuleListItem withCreatedAt(Date date) {
        this.createdAt = date;
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

    public TopicRuleListItem withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRuleArn() != null) {
            sb.append("ruleArn: " + getRuleArn() + ",");
        }
        if (getRuleName() != null) {
            sb.append("ruleName: " + getRuleName() + ",");
        }
        if (getTopicPattern() != null) {
            sb.append("topicPattern: " + getTopicPattern() + ",");
        }
        if (getCreatedAt() != null) {
            sb.append("createdAt: " + getCreatedAt() + ",");
        }
        if (getRuleDisabled() != null) {
            sb.append("ruleDisabled: " + getRuleDisabled());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getRuleArn() == null ? 0 : getRuleArn().hashCode()) + 31) * 31) + (getRuleName() == null ? 0 : getRuleName().hashCode())) * 31) + (getTopicPattern() == null ? 0 : getTopicPattern().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getRuleDisabled() != null ? getRuleDisabled().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRuleListItem)) {
            return false;
        }
        TopicRuleListItem topicRuleListItem = (TopicRuleListItem) obj;
        if ((topicRuleListItem.getRuleArn() == null) ^ (getRuleArn() == null)) {
            return false;
        }
        if (topicRuleListItem.getRuleArn() != null && !topicRuleListItem.getRuleArn().equals(getRuleArn())) {
            return false;
        }
        if ((topicRuleListItem.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (topicRuleListItem.getRuleName() != null && !topicRuleListItem.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((topicRuleListItem.getTopicPattern() == null) ^ (getTopicPattern() == null)) {
            return false;
        }
        if (topicRuleListItem.getTopicPattern() != null && !topicRuleListItem.getTopicPattern().equals(getTopicPattern())) {
            return false;
        }
        if ((topicRuleListItem.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (topicRuleListItem.getCreatedAt() != null && !topicRuleListItem.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((topicRuleListItem.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        return topicRuleListItem.getRuleDisabled() == null || topicRuleListItem.getRuleDisabled().equals(getRuleDisabled());
    }
}
