package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SearchIndexResult implements Serializable {
    private String nextToken;
    private List<ThingGroupDocument> thingGroups;
    private List<ThingDocument> things;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public SearchIndexResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public List<ThingDocument> getThings() {
        return this.things;
    }

    public void setThings(Collection<ThingDocument> collection) {
        if (collection == null) {
            this.things = null;
        } else {
            this.things = new ArrayList(collection);
        }
    }

    public SearchIndexResult withThings(ThingDocument... thingDocumentArr) {
        if (getThings() == null) {
            this.things = new ArrayList(thingDocumentArr.length);
        }
        for (ThingDocument thingDocument : thingDocumentArr) {
            this.things.add(thingDocument);
        }
        return this;
    }

    public SearchIndexResult withThings(Collection<ThingDocument> collection) {
        setThings(collection);
        return this;
    }

    public List<ThingGroupDocument> getThingGroups() {
        return this.thingGroups;
    }

    public void setThingGroups(Collection<ThingGroupDocument> collection) {
        if (collection == null) {
            this.thingGroups = null;
        } else {
            this.thingGroups = new ArrayList(collection);
        }
    }

    public SearchIndexResult withThingGroups(ThingGroupDocument... thingGroupDocumentArr) {
        if (getThingGroups() == null) {
            this.thingGroups = new ArrayList(thingGroupDocumentArr.length);
        }
        for (ThingGroupDocument thingGroupDocument : thingGroupDocumentArr) {
            this.thingGroups.add(thingGroupDocument);
        }
        return this;
    }

    public SearchIndexResult withThingGroups(Collection<ThingGroupDocument> collection) {
        setThingGroups(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getThings() != null) {
            sb.append("things: " + getThings() + ",");
        }
        if (getThingGroups() != null) {
            sb.append("thingGroups: " + getThingGroups());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getThings() == null ? 0 : getThings().hashCode())) * 31) + (getThingGroups() != null ? getThingGroups().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SearchIndexResult)) {
            return false;
        }
        SearchIndexResult searchIndexResult = (SearchIndexResult) obj;
        if ((searchIndexResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (searchIndexResult.getNextToken() != null && !searchIndexResult.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((searchIndexResult.getThings() == null) ^ (getThings() == null)) {
            return false;
        }
        if (searchIndexResult.getThings() != null && !searchIndexResult.getThings().equals(getThings())) {
            return false;
        }
        if ((searchIndexResult.getThingGroups() == null) ^ (getThingGroups() == null)) {
            return false;
        }
        return searchIndexResult.getThingGroups() == null || searchIndexResult.getThingGroups().equals(getThingGroups());
    }
}
