package com.amazonaws.services.iotdata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListRetainedMessagesResult implements Serializable {
    private String nextToken;
    private List<RetainedMessageSummary> retainedTopics;

    public List<RetainedMessageSummary> getRetainedTopics() {
        return this.retainedTopics;
    }

    public void setRetainedTopics(Collection<RetainedMessageSummary> collection) {
        if (collection == null) {
            this.retainedTopics = null;
        } else {
            this.retainedTopics = new ArrayList(collection);
        }
    }

    public ListRetainedMessagesResult withRetainedTopics(RetainedMessageSummary... retainedMessageSummaryArr) {
        if (getRetainedTopics() == null) {
            this.retainedTopics = new ArrayList(retainedMessageSummaryArr.length);
        }
        for (RetainedMessageSummary retainedMessageSummary : retainedMessageSummaryArr) {
            this.retainedTopics.add(retainedMessageSummary);
        }
        return this;
    }

    public ListRetainedMessagesResult withRetainedTopics(Collection<RetainedMessageSummary> collection) {
        setRetainedTopics(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListRetainedMessagesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRetainedTopics() != null) {
            sb.append("retainedTopics: " + getRetainedTopics() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRetainedTopics() == null ? 0 : getRetainedTopics().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListRetainedMessagesResult)) {
            return false;
        }
        ListRetainedMessagesResult listRetainedMessagesResult = (ListRetainedMessagesResult) obj;
        if ((listRetainedMessagesResult.getRetainedTopics() == null) ^ (getRetainedTopics() == null)) {
            return false;
        }
        if (listRetainedMessagesResult.getRetainedTopics() != null && !listRetainedMessagesResult.getRetainedTopics().equals(getRetainedTopics())) {
            return false;
        }
        if ((listRetainedMessagesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listRetainedMessagesResult.getNextToken() == null || listRetainedMessagesResult.getNextToken().equals(getNextToken());
    }
}
