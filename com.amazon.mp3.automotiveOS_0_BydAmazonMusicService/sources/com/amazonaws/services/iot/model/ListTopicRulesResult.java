package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRulesResult implements Serializable {
    private String nextToken;
    private List<TopicRuleListItem> rules;

    public List<TopicRuleListItem> getRules() {
        return this.rules;
    }

    public void setRules(Collection<TopicRuleListItem> collection) {
        if (collection == null) {
            this.rules = null;
        } else {
            this.rules = new ArrayList(collection);
        }
    }

    public ListTopicRulesResult withRules(TopicRuleListItem... topicRuleListItemArr) {
        if (getRules() == null) {
            this.rules = new ArrayList(topicRuleListItemArr.length);
        }
        for (TopicRuleListItem topicRuleListItem : topicRuleListItemArr) {
            this.rules.add(topicRuleListItem);
        }
        return this;
    }

    public ListTopicRulesResult withRules(Collection<TopicRuleListItem> collection) {
        setRules(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTopicRulesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRules() != null) {
            sb.append("rules: " + getRules() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRules() == null ? 0 : getRules().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTopicRulesResult)) {
            return false;
        }
        ListTopicRulesResult listTopicRulesResult = (ListTopicRulesResult) obj;
        if ((listTopicRulesResult.getRules() == null) ^ (getRules() == null)) {
            return false;
        }
        if (listTopicRulesResult.getRules() != null && !listTopicRulesResult.getRules().equals(getRules())) {
            return false;
        }
        if ((listTopicRulesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTopicRulesResult.getNextToken() == null || listTopicRulesResult.getNextToken().equals(getNextToken());
    }
}
