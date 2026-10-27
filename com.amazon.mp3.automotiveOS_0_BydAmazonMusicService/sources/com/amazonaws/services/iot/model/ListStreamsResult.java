package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListStreamsResult implements Serializable {
    private String nextToken;
    private List<StreamSummary> streams;

    public List<StreamSummary> getStreams() {
        return this.streams;
    }

    public void setStreams(Collection<StreamSummary> collection) {
        if (collection == null) {
            this.streams = null;
        } else {
            this.streams = new ArrayList(collection);
        }
    }

    public ListStreamsResult withStreams(StreamSummary... streamSummaryArr) {
        if (getStreams() == null) {
            this.streams = new ArrayList(streamSummaryArr.length);
        }
        for (StreamSummary streamSummary : streamSummaryArr) {
            this.streams.add(streamSummary);
        }
        return this;
    }

    public ListStreamsResult withStreams(Collection<StreamSummary> collection) {
        setStreams(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListStreamsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreams() != null) {
            sb.append("streams: " + getStreams() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getStreams() == null ? 0 : getStreams().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListStreamsResult)) {
            return false;
        }
        ListStreamsResult listStreamsResult = (ListStreamsResult) obj;
        if ((listStreamsResult.getStreams() == null) ^ (getStreams() == null)) {
            return false;
        }
        if (listStreamsResult.getStreams() != null && !listStreamsResult.getStreams().equals(getStreams())) {
            return false;
        }
        if ((listStreamsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listStreamsResult.getNextToken() == null || listStreamsResult.getNextToken().equals(getNextToken());
    }
}
