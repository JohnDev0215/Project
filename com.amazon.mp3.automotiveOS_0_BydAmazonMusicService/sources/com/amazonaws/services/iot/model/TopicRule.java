package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TopicRule implements Serializable {
    private List<Action> actions;
    private String awsIotSqlVersion;
    private Date createdAt;
    private String description;
    private Action errorAction;
    private Boolean ruleDisabled;
    private String ruleName;
    private String sql;

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String str) {
        this.ruleName = str;
    }

    public TopicRule withRuleName(String str) {
        this.ruleName = str;
        return this;
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String str) {
        this.sql = str;
    }

    public TopicRule withSql(String str) {
        this.sql = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public TopicRule withDescription(String str) {
        this.description = str;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public TopicRule withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(Collection<Action> collection) {
        if (collection == null) {
            this.actions = null;
        } else {
            this.actions = new ArrayList(collection);
        }
    }

    public TopicRule withActions(Action... actionArr) {
        if (getActions() == null) {
            this.actions = new ArrayList(actionArr.length);
        }
        for (Action action : actionArr) {
            this.actions.add(action);
        }
        return this;
    }

    public TopicRule withActions(Collection<Action> collection) {
        setActions(collection);
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

    public TopicRule withRuleDisabled(Boolean bool) {
        this.ruleDisabled = bool;
        return this;
    }

    public String getAwsIotSqlVersion() {
        return this.awsIotSqlVersion;
    }

    public void setAwsIotSqlVersion(String str) {
        this.awsIotSqlVersion = str;
    }

    public TopicRule withAwsIotSqlVersion(String str) {
        this.awsIotSqlVersion = str;
        return this;
    }

    public Action getErrorAction() {
        return this.errorAction;
    }

    public void setErrorAction(Action action) {
        this.errorAction = action;
    }

    public TopicRule withErrorAction(Action action) {
        this.errorAction = action;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRuleName() != null) {
            sb.append("ruleName: " + getRuleName() + ",");
        }
        if (getSql() != null) {
            sb.append("sql: " + getSql() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getCreatedAt() != null) {
            sb.append("createdAt: " + getCreatedAt() + ",");
        }
        if (getActions() != null) {
            sb.append("actions: " + getActions() + ",");
        }
        if (getRuleDisabled() != null) {
            sb.append("ruleDisabled: " + getRuleDisabled() + ",");
        }
        if (getAwsIotSqlVersion() != null) {
            sb.append("awsIotSqlVersion: " + getAwsIotSqlVersion() + ",");
        }
        if (getErrorAction() != null) {
            sb.append("errorAction: " + getErrorAction());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getRuleName() == null ? 0 : getRuleName().hashCode()) + 31) * 31) + (getSql() == null ? 0 : getSql().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getActions() == null ? 0 : getActions().hashCode())) * 31) + (getRuleDisabled() == null ? 0 : getRuleDisabled().hashCode())) * 31) + (getAwsIotSqlVersion() == null ? 0 : getAwsIotSqlVersion().hashCode())) * 31) + (getErrorAction() != null ? getErrorAction().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRule)) {
            return false;
        }
        TopicRule topicRule = (TopicRule) obj;
        if ((topicRule.getRuleName() == null) ^ (getRuleName() == null)) {
            return false;
        }
        if (topicRule.getRuleName() != null && !topicRule.getRuleName().equals(getRuleName())) {
            return false;
        }
        if ((topicRule.getSql() == null) ^ (getSql() == null)) {
            return false;
        }
        if (topicRule.getSql() != null && !topicRule.getSql().equals(getSql())) {
            return false;
        }
        if ((topicRule.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (topicRule.getDescription() != null && !topicRule.getDescription().equals(getDescription())) {
            return false;
        }
        if ((topicRule.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (topicRule.getCreatedAt() != null && !topicRule.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((topicRule.getActions() == null) ^ (getActions() == null)) {
            return false;
        }
        if (topicRule.getActions() != null && !topicRule.getActions().equals(getActions())) {
            return false;
        }
        if ((topicRule.getRuleDisabled() == null) ^ (getRuleDisabled() == null)) {
            return false;
        }
        if (topicRule.getRuleDisabled() != null && !topicRule.getRuleDisabled().equals(getRuleDisabled())) {
            return false;
        }
        if ((topicRule.getAwsIotSqlVersion() == null) ^ (getAwsIotSqlVersion() == null)) {
            return false;
        }
        if (topicRule.getAwsIotSqlVersion() != null && !topicRule.getAwsIotSqlVersion().equals(getAwsIotSqlVersion())) {
            return false;
        }
        if ((topicRule.getErrorAction() == null) ^ (getErrorAction() == null)) {
            return false;
        }
        return topicRule.getErrorAction() == null || topicRule.getErrorAction().equals(getErrorAction());
    }
}
