package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListViolationEventsResult implements Serializable {
    private String nextToken;
    private List<ViolationEvent> violationEvents;

    public List<ViolationEvent> getViolationEvents() {
        return this.violationEvents;
    }

    public void setViolationEvents(Collection<ViolationEvent> collection) {
        if (collection == null) {
            this.violationEvents = null;
        } else {
            this.violationEvents = new ArrayList(collection);
        }
    }

    public ListViolationEventsResult withViolationEvents(ViolationEvent... violationEventArr) {
        if (getViolationEvents() == null) {
            this.violationEvents = new ArrayList(violationEventArr.length);
        }
        for (ViolationEvent violationEvent : violationEventArr) {
            this.violationEvents.add(violationEvent);
        }
        return this;
    }

    public ListViolationEventsResult withViolationEvents(Collection<ViolationEvent> collection) {
        setViolationEvents(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListViolationEventsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getViolationEvents() != null) {
            sb.append("violationEvents: " + getViolationEvents() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getViolationEvents() == null ? 0 : getViolationEvents().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListViolationEventsResult)) {
            return false;
        }
        ListViolationEventsResult listViolationEventsResult = (ListViolationEventsResult) obj;
        if ((listViolationEventsResult.getViolationEvents() == null) ^ (getViolationEvents() == null)) {
            return false;
        }
        if (listViolationEventsResult.getViolationEvents() != null && !listViolationEventsResult.getViolationEvents().equals(getViolationEvents())) {
            return false;
        }
        if ((listViolationEventsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listViolationEventsResult.getNextToken() == null || listViolationEventsResult.getNextToken().equals(getNextToken());
    }
}
