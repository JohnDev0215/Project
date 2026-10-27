package com.amazonaws.services.iotdata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListNamedShadowsForThingResult implements Serializable {
    private String nextToken;
    private List<String> results;
    private Long timestamp;

    public List<String> getResults() {
        return this.results;
    }

    public void setResults(Collection<String> collection) {
        if (collection == null) {
            this.results = null;
        } else {
            this.results = new ArrayList(collection);
        }
    }

    public ListNamedShadowsForThingResult withResults(String... strArr) {
        if (getResults() == null) {
            this.results = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.results.add(str);
        }
        return this;
    }

    public ListNamedShadowsForThingResult withResults(Collection<String> collection) {
        setResults(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListNamedShadowsForThingResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public ListNamedShadowsForThingResult withTimestamp(Long l) {
        this.timestamp = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getResults() != null) {
            sb.append("results: " + getResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getTimestamp() != null) {
            sb.append("timestamp: " + getTimestamp());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getResults() == null ? 0 : getResults().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListNamedShadowsForThingResult)) {
            return false;
        }
        ListNamedShadowsForThingResult listNamedShadowsForThingResult = (ListNamedShadowsForThingResult) obj;
        if ((listNamedShadowsForThingResult.getResults() == null) ^ (getResults() == null)) {
            return false;
        }
        if (listNamedShadowsForThingResult.getResults() != null && !listNamedShadowsForThingResult.getResults().equals(getResults())) {
            return false;
        }
        if ((listNamedShadowsForThingResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listNamedShadowsForThingResult.getNextToken() != null && !listNamedShadowsForThingResult.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listNamedShadowsForThingResult.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        return listNamedShadowsForThingResult.getTimestamp() == null || listNamedShadowsForThingResult.getTimestamp().equals(getTimestamp());
    }
}
