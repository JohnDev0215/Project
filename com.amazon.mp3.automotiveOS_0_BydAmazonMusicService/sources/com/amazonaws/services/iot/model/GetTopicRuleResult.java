package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleResult implements Serializable {
    private TopicRule rule;
    private String ruleArn;

    public String getRuleArn() {
        return this.ruleArn;
    }

    public void setRuleArn(String str) {
        this.ruleArn = str;
    }

    public GetTopicRuleResult withRuleArn(String str) {
        this.ruleArn = str;
        return this;
    }

    public TopicRule getRule() {
        return this.rule;
    }

    public void setRule(TopicRule topicRule) {
        this.rule = topicRule;
    }

    public GetTopicRuleResult withRule(TopicRule topicRule) {
        this.rule = topicRule;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRuleArn() != null) {
            sb.append("ruleArn: " + getRuleArn() + ",");
        }
        if (getRule() != null) {
            sb.append("rule: " + getRule());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRuleArn() == null ? 0 : getRuleArn().hashCode()) + 31) * 31) + (getRule() != null ? getRule().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetTopicRuleResult)) {
            return false;
        }
        GetTopicRuleResult getTopicRuleResult = (GetTopicRuleResult) obj;
        if ((getTopicRuleResult.getRuleArn() == null) ^ (getRuleArn() == null)) {
            return false;
        }
        if (getTopicRuleResult.getRuleArn() != null && !getTopicRuleResult.getRuleArn().equals(getRuleArn())) {
            return false;
        }
        if ((getTopicRuleResult.getRule() == null) ^ (getRule() == null)) {
            return false;
        }
        return getTopicRuleResult.getRule() == null || getTopicRuleResult.getRule().equals(getRule());
    }
}
